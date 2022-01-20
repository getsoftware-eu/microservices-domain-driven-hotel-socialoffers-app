//app
angular.module("hotelApp.hotelNotification", [ "ngResource", "hotelApp.hotelState", "hotelApp.notificationService" ])
    .factory("HotelNotification", function($q, $rootScope, $resource, $state, $location, NotificationService, HotelState) {

        var _self = this;
        
        var deferredState = $q.defer();

        /////////////////// Notification
        var PingService = $resource('./customers/:customerId/customerPing', {customerId:'@customerId', hotelId:'@hotelId' }, {'ping': {method: 'GET'}});
        
        var notificationInitObj = {
            unreadChatsCounter : 0,
            hotelUnreadActivitiesNumber : 0,
            lastMessagesToMe : [],
            lastMessageTimesToMe : [],
            lastMessageSeenByCustomer : [],
            unreadChatProSenderCount : [],
            hotelOnlineGuestIds : [],
            lastSeenOnlineHotelGuests : [],
            newRegisteredCustomerIds: []
        };

        var notificationService = {

             notificationObj : null,
            
             PING_TIMEOUT : 8000,
            
             activeRequestInterval : null,
            
             intervalWorker : null,

             lastSuccesfulPingTime : null,

            config: function()
            {
                _self = this;
                
                HotelState.getDeferredState().then(function(hotelState){
                    _self.hotelState = hotelState;
                    _self.emptyNotification();
                    //_self.setPingListener();
                    deferredState.resolve(_self);
                });

                return;
            },
            
            getDeferredState: function () {
                return deferredState.promise;
            },
                
             emptyNotification : function()
             {
                 _self.notificationObj = angular.copy(notificationInitObj);
                 return;
             },

             pingRequest : function(activate)  {
                 
                //EUGEN: check if last ping time is too old
                if(_self.lastSuccesfulPingTime && (_self.lastSuccesfulPingTime + 5000 > new Date().getTime()))
                {
                    return;
                }
                 
                console.log("ping - " + new Date());
    
                //$scope.app.rootSettings.prefferedLanguage= $translate.use()? $translate.use() : "en";
    
                if( _self.hotelState.profileData &&  _self.hotelState.profileData.id>0)// && _self.hotelState.profileData.hotelId>0)
                {
                    PingService.ping({customerId: _self.hotelState.profileData.id}, function()
                        {
                            console.log("ping Response - " + new Date());
                            _self.lastSuccesfulPingTime = new Date().getTime();
                        },
                        function(error){
                        
                            //$rootScope.rootErrorMsg = error;
                        
                            //TODO EUGEN: show no internet connection
                        }
                    );
                }
            },
            
            handleNotification: function(notification) {

                if(!_self.notificationObj)
                {
                    _self.notificationObj = angular.copy(notificationInitObj);
                }
                
                if(notification!=undefined)
                {
                    //if(notification.unreadDeals)
                    //    _self.notificationObj.unreadChatsCounter = notification.unreadChatsCounter;
                     
                    if(notification.unreadChatsCounter)
                        _self.notificationObj.unreadChatsCounter = notification.unreadChatsCounter;
                   
                    if(notification.hotelUnreadActivitiesNumber)
                        _self.notificationObj.hotelUnreadActivitiesNumber = notification.hotelUnreadActivitiesNumber;
                    
                    if(notification.lastMessagesToMe)
                    {
                        _self.notificationObj.lastMessagesToMe = notification.lastMessagesToMe;

                        for (var n in notification.lastMessagesToMe)
                        {
                            try{
                                _self.notificationObj.lastMessagesToMe[n] = decodeURIComponent( notification.lastMessagesToMe[n]);
                            }
                            catch(e)
                            {
                                _self.notificationObj.lastMessagesToMe[n] = "";
                            }

                            if(_self.notificationObj.lastMessagesToMe[n].length> $rootScope.cutMessageIndex + 1)
                            {
                                _self.notificationObj.lastMessagesToMe[n] = _self.notificationObj.lastMessagesToMe[n].substring(0,$rootScope.cutMessageIndex) + "..";
                            }
                        }
                    }

                    if(notification.lastMessageTimesToMe)
                         _self.notificationObj.lastMessageTimesToMe = notification.lastMessageTimesToMe;

                    if(notification.lastMessageSeenByCustomer && notification.lastMessagesToMe)
                         _self.notificationObj.lastMessageSeenByCustomer = notification.lastMessageSeenByCustomer;

                    if(notification.unreadChatProSenderCount)
                         _self.notificationObj.unreadChatProSenderCount = notification.unreadChatProSenderCount;                   
                    
                    if(notification.myMenuOrdersNumber)
                         _self.notificationObj.myMenuOrdersNumber = notification.myMenuOrdersNumber;
    
                    var tempHotelOnlineGuestIds = notification.hotelOnlineGuestIds;

                    if(notification.lastSeenOnlineHotelGuests)
                        _self.notificationObj.lastSeenOnlineHotelGuests = notification.lastSeenOnlineHotelGuests;
                    
                    
                    //if new message to me, and chatPartner not in my real hotel
                    if (notification.customerEvent) 
                    {
                        if(notification.customerEvent["menuOrderInRoomNumber"])
                        {
                            _self.notificationObj.menuOrderInRoomNumber = notification.customerEvent["menuOrderInRoomNumber"];
                        }
                        
                        var newEventType = notification.customerEvent["event"];
                        var eventHotelId = notification.customerEvent["hotelId"];

                        if (newEventType == "EVENT_WALL_NEW_MESSAGE") {
                        
                            if ($state.current.name != "app.wall" && _self.hotelState.profileData && eventHotelId == _self.hotelState.profileData.hotelId && _self.hotelState.profileData.checkedIn) {
                                _self.notificationObj.hotelUnreadWallNumber = "+1";
                            }
                        }
                        else if (newEventType == "EVENT_DEAL_NEW_UPDATE") {
                        
                            //if ($state.current.name == "app.dealList") 
                            {
                                $rootScope.$broadcast('dealListServerUpdate', []);
                            }
                        }
                        else
                        {
                            _self.hotelState.handleHotelEvent(notification.customerEvent);
                        }
                    }
    
                    //EUGEN: send CHAT EVENTS only in chatList State!!!!!!
                    var curState = $state.current.name;
                    if(curState=="app.chatList")
                    {
                        //Find new Chat partners
                        for (var key in notification.unreadChatProSenderCount) {
                            if (key === 'length' || !notification.unreadChatProSenderCount.hasOwnProperty(key)) continue;
                           
                            var nextUnreadSenderId = key;
    
                            if(nextUnreadSenderId && nextUnreadSenderId>0 && nextUnreadSenderId!=_self.hotelState.profileData.id)
                            {
                                var newHotelChatEvent = [];

                                newHotelChatEvent["event"] = "EVENT_CHAT_NEW_MESSAGE";
                                newHotelChatEvent["entityId"] = nextUnreadSenderId;
                                newHotelChatEvent["senderId"] = nextUnreadSenderId;
                                newHotelChatEvent["entity"] = "Customer";

                                //newHotelChatEvent["hotelId"] = ;
                                 //newHotelChatEvent["entityId"];
                                //newHotelChatEvent["message"];
                                
                                //EUGEN: new chat event!
                                _self.hotelState.handleHotelEvent(newHotelChatEvent);

                            }
                        }
                    }
                }
                else {
                   _self.emptyNotification();
                }
    
                if(tempHotelOnlineGuestIds)
                {
                    _self.notificationObj.hotelOnlineGuestIds = [];
    
                    for(var t=0; t<tempHotelOnlineGuestIds.length; t++)
                    {
                        var nextOnlineId = tempHotelOnlineGuestIds[t];

                        _self.notificationObj.hotelOnlineGuestIds[nextOnlineId] = true;
    
                        var regularFound = false;
                        var notHotelCustomer = false;
    
                        //regular
                        for(var r=0; r< _self.hotelState.hotelCustomers.length; r++)
                        {
                            if( _self.hotelState.hotelCustomers[r].id == nextOnlineId)
                            {
                                _self.hotelState.hotelCustomers[r].online = true;
                                regularFound=true;
                                break;
                            }
                        }
    
                        if(!regularFound &&  _self.hotelState.contactCustomers)
                        {
                            for(var h=0; h< _self.hotelState.contactCustomers.length; h++)
                            {
                                if( _self.hotelState.contactCustomers[h].id == nextOnlineId)
                                {
                                    _self.hotelState.contactCustomers[h].online = true;
                                    notHotelCustomer=true;
                                    break;
                                }
                            }
                        }
    
                    }
                }
                
                
                if(_self.notificationObj.unreadChatsCounter=="0")
                    _self.notificationObj.unreadChatsCounter = null;
    
                if(_self.notificationObj.hotelUnreadActivitiesNumber=="0")
                    _self.notificationObj.hotelUnreadActivitiesNumber = null;
    
            }
            
        };

        notificationService.config();
        
        return notificationService;
        

    });