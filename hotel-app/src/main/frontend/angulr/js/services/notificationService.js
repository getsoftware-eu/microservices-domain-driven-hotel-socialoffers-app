//app
angular.module("hotelApp.notificationService", [])
	.service("NotificationService", function($q, $timeout, $rootScope, HotelState, HotelService) {

		var socketService = {}, notificationListener = $q.defer(), activityListener = $q.defer(), notificationSocket = {
				client: null,
				stomp: null
        }, 
			
        //List of already sended but jet not received message IDs 
        messageIds = [];
        
        //Write in console!!!
        socketService.debugMode = 0;
        
        //ONLY IF SAVE THE CREATED notificationSubscription, WE CAN unsubscribe IT !!!!
        socketService.notificationSubscription = "";        
        socketService.activitySubscription = "";

        // conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
        socketService.conInterval = false;
		
        socketService.configObj = {
           
            //notificationSubscription url
            notificationSubscriptionQueue : null,
            activitySubscriptionQueue : null,
           
            useHeartbeat: false,
            
            headers: {
                
            },

            connectHeaders: {
                //login: {},
                //passcode: {},
                //host: ""
            }
        };

        //reconnect setInterval timeout
        socketService.RECONNECT_TIMEOUT = 30000;

        if(!$rootScope.HOST_SUFFIX)
        {
            $rootScope.HOST_SUFFIX = HotelService.getHostSuffix();
        }        
        //Path for basic SocjkJs -> to Spring Controller
        socketService.NOTIFICATION_SOCKET_URL = "/"+($rootScope.HOST_SUFFIX? $rootScope.HOST_SUFFIX : "")+"notify";
         
		//Unique Topic of this Chat // part of SUBSCRIBE_URL
		socketService.NOTIFICATION_TOPIC = "/notificationtopic/message";
        socketService.ACTIVITY_TOPIC = "/activitytopic/message";
        
		// TODO ???
        // stomp.send() URL
		socketService.NOTIFICATION_BROKER = "/app/notification";
		socketService.ACTIVITY_BROKER = "/app/activity";
        
		//########################################

		//receive to Controller
		socketService.disconnectSocket = function() {

            if(notificationSocket.stomp && notificationSocket.stomp.connected)
            {
                notificationSocket.stomp.disconnect(function() {
                    //alert("See you next time!");
                });
            }
            
        }
        
		socketService.receiveNotification = function() {
			// send server-promise to client controller
			return notificationListener.promise;
		};
        
        socketService.receiveActivity = function() {
			// send server-promise to client controller
			return activityListener.promise;
		};

		socketService.resubscribeWithCustomerData = function(hotelState)
		{
            if(!hotelState)
            {
                hotelState = HotelState;
            }
            
			socketService.hotelState = hotelState;

            if(notificationSocket.stomp && notificationSocket.stomp.connected)
            {
                //### RE-SUBSCRIBE NOTIFICATIONS, IF LOGGED IN ############################
                var tempNotificationSubscriptionQueue = socketService.hotelState && socketService.hotelState.getRequesterId()>0? socketService.NOTIFICATION_TOPIC + "/" + socketService.hotelState.getRequesterId() : null;

                //check if subscription exists or was changed
                if(!socketService.configObj || !socketService.configObj.notificationSubscriptionQueue || socketService.configObj.notificationSubscriptionQueue!=tempNotificationSubscriptionQueue)
                {
                    if(socketService.notificationSubscription)
                    {
                        socketService.notificationSubscription.unsubscribe();
                    }

                    if(socketService.hotelState && socketService.hotelState.profileData.id>0)
                    {
                        subscribeNotificationTopic();
                    }
                }
                
                // ###########################################

                //### RE-SUBSCRIBE ACTIVITIES, IF CHECKED IN HOTEL

                var activitySubscriptionQueueTemp = socketService.hotelState && socketService.hotelState.profileData.hotelId>0? socketService.ACTIVITY_TOPIC + "/" + socketService.hotelState.profileData.hotelId : null;

                //check if subscription exists or was changed
                if(!socketService.configObj || !socketService.configObj.activitySubscriptionQueue || socketService.configObj.activitySubscriptionQueue!=activitySubscriptionQueueTemp)
                {
                    if(socketService.activitySubscription)
                    {
                        socketService.activitySubscription.unsubscribe();
                        $rootScope.myWait(2000);
                    }

                    if(socketService.hotelState && socketService.hotelState.profileData.hotelId>0)
                    {
                        subscribeActivityTopic();
                    }
                }

                // ###########################################
                
            }
            else{
                initConnect();
            }
        }
        
        socketService.resubscribeWithHotelData = function(hotelState)
        {
            return socketService.resubscribeWithCustomerData(hotelState);
            
			//socketService.hotelState = hotelState;
        //
        //    if(notificationSocket.stomp && notificationSocket.stomp.connected)
        //    {
        //        //### RE-SUBSCRIBE ACTIVITIES, IF CHECKED IN HOTEL
        //
        //        var activitySubscriptionQueueTemp = socketService.hotelState && socketService.hotelState.profileData.hotelId>0? socketService.ACTIVITY_TOPIC + "/" + socketService.hotelState.profileData.hotelId : null;
        //
        //        //check if subscription exists or was changed
        //        if(socketService.configObj && socketService.configObj.activitySubscriptionQueue && socketService.configObj.activitySubscriptionQueue==activitySubscriptionQueueTemp)
        //        {
        //            return;    
        //        }
        //        
        //        if(socketService.activitySubscription)
        //        {
        //            socketService.activitySubscription.unsubscribe();
        //            $rootScope.myWait(2000);
        //        }
        //        
        //        if(socketService.hotelState && socketService.hotelState.profileData.hotelId>0)
        //        {
        //            subscribeActivityTopic();
        //        }
        //        
        //        // ###########################################
        //    }
        //    else{
        //        initConnect();
        //    }
        }

        socketService.stopAndEmptyCustomer = function()
        {
            socketService.hotelState = null;

            socketService.disconnectSocket();
        }
    
        //send from Controller
		socketService.send = function(message, senderId, hotelId) {
			//generate random message id
			var id = Math.floor(Math.random() * 1000000);;
        
			//Socket SEND Methode
			notificationSocket.stomp.send(socketService.NOTIFICATION_BROKER, {
				priority: 9
			}, JSON.stringify({ //MESSAGE CONTENT
				message: message,
				hotelId : hotelId,
				senderId: senderId,
				creationTime: (new Date).getTime(),
				id: id
			}));
			messageIds.push(id);
		};

	    socketService.checkSocketConnection = function() {
            
            var defer = defer.resolve();
            
            setTimeout(function(){
                if (!notificationSocket.stomp || !notificationSocket.stomp.connected)
                {
                    initConnect();
                    defer.resolve();
                }
                else{
                    defer.resolve();
                }
            }, 1500);
            
            return defer.promise;
		};  
	
		var getMessage = function(data) {
			var message = JSON.parse(data), out = {};
			
			return message;
		};
        
        var subscribeNotificationTopic = function()
        {
            socketService.configObj.notificationSubscriptionQueue = socketService.hotelState && socketService.hotelState.getRequesterId()>0? socketService.NOTIFICATION_TOPIC + "/" + socketService.hotelState.getRequesterId() : null;

            if(!socketService.configObj.notificationSubscriptionQueue)
            {
                //NO notificationSubscription WITHOUT CUSTOMER_ID
                return;
            }
            
            socketService.notificationSubscription =
                // #### START WsWorker.notificationSubscription
                notificationSocket.stomp.subscribe(socketService.configObj.notificationSubscriptionQueue,
                    /*onMessage*/
                    function (message) {

                        notificationListener.notify(getMessage(message.body));
                        
                        //TODO Eugen: acknolidge Server, about receive!!!! 
                        //TODO Eugen: -> DELIEVERED TO CUSTOMER!!!!!
                        notificationSocket.stomp.ack(message.headers["message-id"], message.notificationSubscription, message.headers);
                    }
                    //TODO Eugen: //, socketService.configObj.headers
                );
        } 
        
        var subscribeActivityTopic = function()
        {
            socketService.configObj.activitySubscriptionQueue = socketService.hotelState && socketService.hotelState.profileData.hotelId>0? socketService.ACTIVITY_TOPIC + "/" + socketService.hotelState.profileData.hotelId : null;

            if(!socketService.configObj.activitySubscriptionQueue)
            {
                //NO notificationSubscription WITHOUT CUSTOMER_ID
                return;
            }
            
            socketService.activitySubscription =
                // #### START WsWorker.notificationSubscription
                notificationSocket.stomp.subscribe(socketService.configObj.activitySubscriptionQueue,
                    /*onMessage*/
                    function (message) {

                        activityListener.notify(getMessage(message.body));
                        
                        //TODO Eugen: acknolidge Server, about receive!!!! 
                        //TODO Eugen: -> DELIEVERED TO CUSTOMER!!!!!
                        notificationSocket.stomp.ack(message.headers["message-id"], message.activitySubscription, message.headers);
                    } 
                    //TODO Eugen: //,socketService.configObj.headers
            );
        }
        
        var initConnect = function(e)
        {
            //ONLY IF NOW NOT CONNECTED!!!
            if (!notificationSocket.stomp || !notificationSocket.stomp.connected) {

                //WsWorker.debugMode = 1;

                if (socketService.debugMode)
                    console.log("Connecting...");

                //TODO Eugen test it!!!
                socketService.configObj = e ? e.data.config : socketService.configObj;

                //var ws = new WebSocket(notificationSocket.config.wsUrl);
                //notificationSocket.stomp = Stomp.over(ws);

                if(!notificationSocket.stomp && !notificationSocket.client)
                {
                    notificationSocket.client = new SockJS(socketService.NOTIFICATION_SOCKET_URL);
                    notificationSocket.stomp = Stomp.over(notificationSocket.client);
                }
                
                if (!socketService.debugMode)
                    notificationSocket.stomp.debug = null;

                //TODO Eugen test it!!!
                if(socketService.configObj.useHeartbeat)
                {
                    notificationSocket.stomp.heartbeat.outgoing = 20000;
                    notificationSocket.stomp.heartbeat.incoming = 20000;
                }

                notificationSocket.stomp.connect(socketService.configObj.connectHeaders,
                    /*onConnect*/function () {
   
                        subscribeNotificationTopic();
                        subscribeActivityTopic();
                        
                        // ### END WsWorker.notificationSubscription

                        if (socketService.debugMode)
                            console.log("Connected");
                    },
                    /*onError*/function (error) {

                        if (socketService.debugMode)
                            console.log("Error: " + (error ? error : "unknown"));

                        if ((!notificationSocket.stomp || !notificationSocket.stomp.connected) && !socketService.conInterval) {
                            
                            //##########################################################
                            /*
                            //if error -> TRY TO RE-CONNECT EVERY 30 sec !!
                            // conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
                            socketService.conInterval = setInterval(function () {
                                if (notificationSocket.stomp && notificationSocket.stomp.connected) {
                                    clearInterval(socketService.conInterval);
                                    socketService.conInterval = false;
                                }
                                initConnect();
                            }, socketService.RECONNECT_TIMEOUT);
                            */
                            
                            var timerWorker = $rootScope.getTimeWebWorker();
                            socketService.conInterval = timerWorker.setInterval(
                                function(){
                                    if (notificationSocket.stomp && notificationSocket.stomp.connected) {
                                        timerWorker.clearInterval(socketService.conInterval);
                                        //clearInterval(socketService.conInterval);
                                        socketService.conInterval = false;
                                    }
                                    initConnect();
                                }
                                ,socketService.RECONNECT_TIMEOUT);
                        }

                    }
                    //TODO Eugen: HOST -> , socketService.configObj.host
                );
            }
        }
        
		return socketService;
	});