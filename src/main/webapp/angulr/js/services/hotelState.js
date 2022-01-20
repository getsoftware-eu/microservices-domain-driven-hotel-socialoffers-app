angular.module("hotelApp.hotelState", [ "ngResource", "hotelApp.sessionCustomerService", "hotelApp.customerDto", "hotelApp.hotelDto" ])
    .factory("HotelState", function($q, $rootScope, $timeout, $resource, $state, $stateParams, $location, $cookies, SessionCustomer, CustomerDto, HotelDto, HotelService) {

        var _self = this;
        
        var _service = HotelService;
        
        var _deferredState = $q.defer();
        
            var getUserWithLastMessagesService = $resource("./customers/customerwithmessage/:getId/sender/:senderId", {
            getId: '@getId',
            senderId: '@senderId'
            }, 
            {
            getCustomerWithLastMessage: {
                method: 'get',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                    'Accept': 'application/json, text/plain, */*'
                }
            }
        });

        var getAnonymGeoLocationCityService = $resource("./hotels/getGpsHotelCity/requesterId/:requesterId/lat/:lat/lon/:lon", {
                lat: '@lat',
                lon: '@lon'
            },
            {
                getGeoLocationCity: {
                    method: 'get',
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
            });
        
        
        var state = {
            
            //defaultCustomState : "app.activityList",

            /**
             * complex states with onInitCheck, that can be loaded after login/checkin prufung, otherwise rederection to other state
             */
            checkTransitionStates : ["tryaccess.login", "tryaccess.checkin", "access.login", "app.hotelLogin", "access.werbung", "app.hotel", "app.hotelPreview", "app.checkin"],

            /**
             * simple rendering states, that have no login checking transition
             */
            noLoginCheckStates : ["app.activityList", "app/hotelPreview", "app.hotelList", "app.register", "app.hotelLogin", "app.hotelRegister", "app.forgotpwd", "app.agb", "app.impressum"],

            /**
             * States that can be called (clicked) only with valid login or with login-popup!!! 
             */
                
            requireLoginStates: ["app.chat", /*"app.chatList",*/ "app.hotel", "app.me", "app.avatar", "app.wall"],

            /**
             * States that can be called (clicked) only with checkin with hotelId>0!!!
             */
            requireCheckinStates: ["app.wall", "app.chatList"],
            
            /**
             * allowed first request url, before $state exists. WITHOUT CHECKED LOGIN!
             */
            noLoginUrls: ["app/activityList", "app/register", "access/hotelLogin", "app/hotelRegister", "app/forgotpwd", "app/about", "app/about/eugen", "app/checkin"],
            
            currentHotel: null,
            
            profileData: _service.getInitCustomer(),
            
            prefferedLanguage : "en",

            //pushRelocatePrefix : "chatlist/false",
            
            initState: function (initData) //, initRelocateState, initRelocateStateParams) {
            {
                _self = this;

                _service = HotelService;

                //_self.relocateOnLoginState = initRelocateState;
                //_self.relocateOnLoginStateParams = initRelocateStateParams;
                
                _self.initProfileData(initData).then(function () 
                {
                    if(_deferredState.promise.$$state && _deferredState.promise.$$state.status==2) // if already rejected
                    {
                        _self.resetDeferredState();
                    }
                    
                    if (_self.profileData && _self.profileData.id > 0) 
                    {

                        _self.detectHotelStaffOrAdmin();
                        
                        //TODO relocate on social login
                        var relocateLogin = $rootScope.relocateLogin();
                        
                        if(relocateLogin)
                        {
                            _deferredState.resolve(_self);
                        }
                        else
                        {
                            var requestedUrl = $location.path();

                            var pathPrefixSplit = requestedUrl.split("/");
                            var pathPrefix = pathPrefixSplit.length>2? pathPrefixSplit[1] + "/" + pathPrefixSplit[2] : "";
                            
                            //Push Redirect
                            if((requestedUrl+"").indexOf("chatList/false")>=0)
                            {
                                //Push Redirect
                                $state.go("app.chatList");
                            }
                            else if((requestedUrl+"").indexOf("chat/")>=0)
                            {
                                var urlSplit = requestedUrl.split("chat/");
                                
                                var partnerId = "";
                                
                                if(urlSplit.length>1)
                                {
                                    partnerId = urlSplit[1];
                                }
                                //Push Redirect
                                $state.go("app.chat", {receiverId: partnerId});
                            }
                            //else if(pathPrefix && _self.noLoginUrls.indexOf(pathPrefix)>0)
                            else if(pathPrefix && (pathPrefix+"").indexOf("app/about")>=0)
                            {
                                //load current allowed url
                                ;
                            }
                            else if(pathPrefix && pathPrefix=="app/activityList" && pathPrefixSplit.length>5)
                            {
                                var hotelId = pathPrefixSplit[4];
                                var activityId = pathPrefixSplit[5];
                                $state.go("app.activityList", {filterHotelId:hotelId, filterActivityId:activityId});
                            }
                            else{
                                $state.go(_self.profileData.checkedIn ? "app.hotel" : "app.checkin");
                            }
                        }
                            
                        _deferredState.resolve(_self);
                        //return _self.getDeferredState();
                    }
                    else {

                        if(_self.unloggedCheckinHotelId)
                        {
                            _self.profileData.checkedIn = true;
                            _self.profileData.hotelId = _self.unloggedCheckinHotelId;
                        }
                        
                        _deferredState.reject("noLogin");
                    }
                    
                    return _deferredState.promise;

                });
                
                return _deferredState.promise;
            },

            noCustomerFound: false,

            //virtualHotel: null,

            //defaultTitle: "Hotelico",

            //prefferedLanguage: HotelService.getDefaultSystemLanguageLabel(),

            /**
             * Load hotels based on the available hotel identification indicators
             */
            allCustomersById: [],

            //#######  CHAT ARRAYS ###########
            /**
             * all hotel customers, with or without contact with me
             * //TODO Eugen: EMPTY ON CHECKOUT OR HOTEL CHANGE!!!!
             */
            hotelCustomers: [],

            /**
             * only hotel staff, not visible outside of hotel scope
             * //TODO Eugen: EMPTY ON CHECKOUT OR HOTEL CHANGE!!!!
             */
            hotelStaffCustomers: [],
               
            ///**
            // * all other (virtual) customers for chatting
            // */
            //allChatCustomers: [],
            ///**
            // * All customers with messages with me
            // */
            //allContactCustomers: [],

            /**
             * All contacts, that are not in the same real hotel with me<br>
             * //TODO Eugen: EMPTY ON CHECKOUT OR HOTEL CHANGE!!!!
             */
            contactCustomers: [],
            //################################

            hotelActivities: [],

            /**
             * all deals of a customer
             * key: activityId : value deals array
             */
            customerDeals: [],

            /**
             * hotelDeals -> for staff view
             */
            hotelDeals: [],
            
            allActivities : [],
            
            historyRouteStates: [],

            /**
             * save the temp creation hotel
             */
            tempCreationHotel: null,
            
            /**
             * whole chat lists by partnerId
             */
            chatHistoryByPartnerId: [],
            
            currentMenuOrder : _service.getInitMenuOrder(),

            hotelMenuOrders : [],
            
            getDeferredState: function () {
                return _deferredState.promise;
            }, 
            
            resetDeferredState: function () {
                _deferredState = $q.defer();
            },
            
            initProfileData: function (initCustomerData) {

                var initDeferred = $q.defer();

                initCustomerData = _service.decodeCustomer(initCustomerData);
                
                if (initCustomerData && initCustomerData.id > 0) {
                    _self.setProfileData(initCustomerData);
                    initDeferred.resolve(_self.profileData);
                }
                //realProfileFrom Server
                else //if(!initCustomerData)
                {
                    SessionCustomer.get(function (serverCustomer) {

                        if (serverCustomer && serverCustomer.id > 0 && serverCustomer.firstName) {
                            _self.setProfileData(serverCustomer);
                            initDeferred.resolve(_self.profileData);
                        }
                        else {
                            var cookieId = _service.getCookie('hotelicoUserId');

                            if (!cookieId || cookieId <= 0) {
                                _self.noCustomerFound = true;
                                
                                //if (cookieCustomer.errorResponse) {
                                //    $rootScope.rootErrorMsg = cookieCustomer.errorResponse;
                                //    $rootScope.topPage();
                                //}

                                //_service.setCookie('hotelicoUserId', 0);

                                //if($state.name!="access.login")
                                //    $state.go("access.login", {stopLogin: true});

                                initDeferred.reject("noLogin");

                                var pathPrefixSplit = $location.path().split("/");
                                var pathPrefix = pathPrefixSplit.length>2? pathPrefixSplit[1] + "/" + pathPrefixSplit[2] : "";
                                
                                var hotelCode = pathPrefixSplit.length>3? pathPrefixSplit[3] : "";
                                
                                if(pathPrefix == "app/checkin")
                                {
                                    $state.go("app.checkin", {hotelCode: hotelCode});
                                }
                                else
                                if(!pathPrefix || _self.noLoginUrls.indexOf(pathPrefix)<0)
                                //if(!$state.current.name || _self.noLoginCheckStates.indexOf($state.current.name)<0)
                                {
                                    //TODO Eugen: defaultState
                                    $state.go(_self.getDefaultStartState(), {stopLogin: true});
                                }

                                _self.unloggedCheckinHotelId  = _service.getCookie('hotelicoNoLoginHotelId');

                                //if(unloggedCheckinHotelId)
                                //{
                                //    _self.hotelState.profileData.hotelId = unloggedCheckinHotelId;
                                //    _self.hotelState.profileData.checkedIn = true;
                                //}
                                
                                return;
                            }

                            //try to load sessionCustomer from cookies
                            var cookieCustomer = CustomerDto.get({id: cookieId, requesterId: cookieId}, function () {

                                if (cookieCustomer.id > 0 && cookieCustomer.firstName) {
                                    SessionCustomer.save({id: cookieCustomer.id}, cookieCustomer);
                                    {
                                        _self.setProfileData(cookieCustomer);
                                        initDeferred.resolve(_self.profileData);
                                    }
                                    ;
                                    //, 
                                    //    function(error){
                                    //        initDeferred.reject(error);
                                    //});

                                }
                                else {

                                    _self.noCustomerFound = true;

                                    if (cookieCustomer.errorResponse) {
                                        $rootScope.rootErrorMsg = cookieCustomer.errorResponse;
                                        $rootScope.topPage();
                                    }

                                    _service.setCookie('hotelicoUserId', 0);

                                    $state.go(_self.getDefaultStartState(), {stopLogin: true});

                                    initDeferred.reject("noLogin");
                                }

                            }, function (error) {
                                _self.noCustomerFound = true;
                                initDeferred.reject(error);
                            });
                        }
                    });
                }

                return initDeferred.promise;
            },

            getDefaultStartState: function()
            {
                var defaultState = _self.profileData && _self.profileData.checkedIn ? (_self.profileData.logged? "app.hotel" : "app.hotelPreview") : "app.checkin";

                return defaultState;
            },
            
            setPreviousRouteState: function (routeState, params) {

                if(_self.ignoreNextHistoryState)
                {
                    _self.ignoreNextHistoryState = false;
                    return;
                }
                
                var stateName = routeState.name;

                //we have 3 root states
                if(stateName=="app.hotel" || stateName=="app.checkin" || stateName=="access.login")
                {
                    _self.historyRouteStates = [];
                    return;
                }

                var stateObj = [];
                stateObj["stateName"] = routeState.name;
                stateObj["stateParams"] = params;

                if(!_self.historyRouteStates)
                {
                    _self.historyRouteStates = [];
                }

                var lastState = _self.historyRouteStates.length>0? _self.historyRouteStates[_self.historyRouteStates.length-1] : null;

                if(!lastState || stateObj["stateName"]!=lastState["stateName"] || stateObj["stateParams"]!=lastState["stateParams"])
                {
                    _self.historyRouteStates.push(stateObj);
                }
                //_self.checkNextRouteState(routeState.name);
            },

            resetHistoryRouteStates: function () {
                _self.historyRouteStates = [];
            },
            
            //updateProfileData: function () {
            //    return _self.setProfileData(_self.profileData);
            //},

            setPrefferedLanguage : function (langKey) {
               
                var viewKey = _service.convertLanguageKey(langKey);

                _self.prefferedLanguage = viewKey;

                if(_self.profileData && _self.profileData.id>0 && _self.profileData.prefferedLanguage != viewKey)
                {
                    _self.profileData.prefferedLanguage = viewKey;
                    _self.submitProfileData(true);
                }
                
                return;
            },
            
            getPrefferedLanguage : function(){
                
                if(_self.profileData && _self.profileData.prefferedLanguage)
                {
                    return _self.profileData.prefferedLanguage;
                }
                
                return _self.prefferedLanguage;
            },
            
            //getNotLoggedCookieSettings: function()
            //{
            //    _self.profileData.hideCheckinPopup = _service.getCookie('hotelicohideCheckinPopup')=="true";
            //    _self.profileData.hideWallPopup = _service.getCookie('hotelicohideWallPopup')=="true";
            //    _self.profileData.hideHotelListPopup = _service.getCookie('hotelicohideHotelListPopup')=="true";
            //},
            
            submitHotelGuestPush: function()
            {
                if(_self.profileData.hotelId>0 && _service.chromePushRegistrationId && (!_self.profileData.systemMessages.hasPushRegistrationId || _self.profileData.systemMessages.hasPushRegistrationId=="false" ))
                {
                    var requesterId = _self.getRequesterId();
                    var hotelId = _self.profileData.hotelId;
                    //TODO Eugen: reset guest pushId in hotel actin

                    _self.profileData.systemMessages["chromePushRegistrationId"] = _service.chromePushRegistrationId;
                    
					if(_self.profileData.id<=0)
                    {
                        var encodedProfile = _service.encodeCustomer(_self.profileData);
                        
						_service.getHotelGuestActionService().addAction({action: "addGuestPushId", hotelId: hotelId, guestCustomerId: requesterId}, encodedProfile, function (profObj) {

						});
					}
					else {
						_self.submitProfileData();
					}
                }
            },

            getActivityById: function(activityId)
            {
                var activityByIdService = $resource('./activity/activities/customer/:customerId/activityId/:activityId', {customerId:'@customerId', activityId:'@activityId'}, {'getActivityById': {method: 'GET', isArray: false}});

                var defer = $q.defer();
                
                activityByIdService.getActivityById({customerId: _self.getRequesterId(), activityId: activityId}, function(activityObj){

                    defer.resolve(_service.decodeActivity(activityObj));

                }, function(error){
                    defer.reject(error);
                });
                
                return defer.promise;
            },

            getPreviousRouteState: function () {

                var prevState = null;

                if (_self.historyRouteStates && _self.historyRouteStates.length > 0) {
                    prevState = _self.historyRouteStates.splice(-1, 1)[0];
                    //_self.historyRouteStates = _self.historyRouteStates.slice(_self.historyRouteStates.length-1,_self.historyRouteStates.length);

                    if (prevState["stateName"] == $state.current.name && prevState["stateParams"] == $state.params )
                    {
                        return _self.getPreviousRouteState();
                    }
                }
                else {
                    prevState = [];
                    prevState["stateName"] = "app.hotel";
                    prevState["stateParams"] = {};
                }

                return prevState;

            },

            setProfileData: function (customerData, onlyStateUpdate) {

                //var nowState = $state.current.name;

                //if (nowState && (nowState+"").indexOf("access")<0 && $rootScope.setNavbarHeaderColor) {
                //    $rootScope.setNavbarHeaderColor($rootScope.isSmartDevice ? "bg-orange" : "bg-black");
                //}

                if (!customerData || !customerData.id || customerData.id == 0 || !customerData.firstName) {
                    return _self.initProfileData();
                }

                //if user is authentificated
                if (customerData && customerData.id && customerData.logged && customerData.id > 0) {

                    //$rootScope.header.showTab = true;

                    if (!onlyStateUpdate) {
                        if (!_self.profileData || !_self.profileData.id || _self.profileData.id == 0) {
                            //_self.profileId = customerData.id;
                            _service.setCookie('hotelicoUserId', customerData.id);
                            $rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key: customerData.id});

                            //If logged IN -> forget not logged actions!!!
                            _service.setCookie('hotelicoNoLoginHotelCode', "");
                            _service.setCookie('hotelicoNoLoginHotelId', "");
                        }

                        _self.profileData = customerData;
                        _self.prefferedLanguage = _self.profileData.prefferedLanguage;
                        _self.showProfile = true;
                        
                    }
                }
                else {
                    return _self.emptyLoginData();
                }

            },
            
            submitProfileData: function (saveOnServerResponse, changedProfile) {
                
                var submitDeffered = $q.defer();

                if (!changedProfile) {
                    changedProfile = _self.profileData;
                }

                if(!changedProfile || !changedProfile.firstName)
                {
                    //$rootScope.rootErrorMsg = "No Data initialized";
                    $rootScope.showLoading(false);
                    submitDeffered.reject("No Data initialized");
                    return submitDeffered.promise;
                }
                
                var encodedProfile = _service.encodeCustomer(changedProfile);

                //if(_self.chromePushRegistrationId)
                //{
                //    encodedProfile.systemMessages["chromePushRegistrationId"] = _self.chromePushRegistrationId;
                //}
                
                var updatedId = changedProfile.id? changedProfile.id : -1;
                var requesterId = changedProfile.id? changedProfile.id : -1;
                
                CustomerDto.update({id: updatedId, requesterId: requesterId}, encodedProfile, function (responseProfile) {

                        var decodedProfile = _service.decodeCustomer(responseProfile);

                        if (saveOnServerResponse) {
                            _self.setProfileData(decodedProfile)
                        }

                        submitDeffered.resolve(decodedProfile);
                    },
                    function (error) {
                        
                        if(encodedProfile.systemMessages && encodedProfile.systemMessages["latitude"])
                        {
                            var requesterId = _self.profileData.id>0? _self.profileData.id : _service.guestCustomerId;
                            
                            getAnonymGeoLocationCityService.getGeoLocationCity({requesterId: requesterId, lat: encodedProfile.systemMessages["latitude"], lon: encodedProfile.systemMessages["longitude"]},
                                function(notif)
                                {
                                    if(notif.customerEvent && notif.customerEvent.pushMessage)
                                    {
                                        _service.gpsCity = notif.customerEvent.pushMessage;
                                        $rootScope.$broadcast("gpsCityUpdated", _service.gpsCity);
                                    }
                                }
                            )
                        }
                        submitDeffered.reject(error);
                    }
                )

                return submitDeffered.promise;
            },
            
            
            registerCustomer: function(customerObj){

                var registerDeffered = $q.defer();

                var errorObj = _service.validateCustomerInfo(customerObj);
                
                if(errorObj && errorObj.length>0)
                {
                    $rootScope.rootErrorMsg = errorObj;
                    $rootScope.topPage();
                    registerDeffered.reject(errorObj);
                    return registerDeffered.promise;
                }
                
                var encodedCustomerObj = _service.encodeCustomer(customerObj);
                
                
                var regCustomer = new CustomerDto();
                //$scope.mainState.errorMsg = false;
                regCustomer.firstName = encodedCustomerObj.firstName;
                regCustomer.lastName = encodedCustomerObj.lastName;

                regCustomer.jobTitle = encodedCustomerObj.jobTitle;
                regCustomer.jobDescriptor = encodedCustomerObj.jobDescriptor;
                regCustomer.password = encodedCustomerObj.password;
                regCustomer.email = encodedCustomerObj.email;
                regCustomer.city = encodedCustomerObj.city;
                regCustomer.originalCity = encodedCustomerObj.originalCity;

                regCustomer.company = encodedCustomerObj.company;

                regCustomer.sex = encodedCustomerObj.sex;

                regCustomer.languages = encodedCustomerObj.languages;

                regCustomer.prefferedLanguage = encodedCustomerObj.prefferedLanguage? encodedCustomerObj.prefferedLanguage : _service.getDefaultAllowedLanguageViewKey();

                //case staff register
                regCustomer.hotelStaff = encodedCustomerObj.hotelStaff;
                regCustomer.hotelId = encodedCustomerObj.hotelId;
                
                regCustomer.$save(function(createdCustomer){
                    
                    createdCustomer = _service.decodeCustomer(createdCustomer);

                    registerDeffered.resolve(createdCustomer);
                }, function(error){
                    registerDeffered.reject(error);
                });
                    
                return registerDeffered.promise;
                
            },

            submitHotelData: function(hotelObject, senderId) {

                var submitHotelDeffered = $q.defer();

                if (!hotelObject) {
                    hotelObject = _self.currentHotel;
                }

                if(!senderId)
                {
                    senderId = _self.profileData && _self.profileData.id>0? _self.profileData.id : -1;
                }

                if (hotelObject.info == undefined) {
                    hotelObject.info = "";
                }
                
                var encodedHotel = _service.encodeHotel(hotelObject);
                
                HotelDto.update({customerId: senderId, hotelId: encodedHotel.id}, encodedHotel, function(response) {

                    var decodedHotel = _service.decodeHotel(response);
                    
                    submitHotelDeffered.resolve(decodedHotel);
                    
                }, function(error){
                    submitHotelDeffered.reject(error);
                });
                
                return submitHotelDeffered.promise;
            },
            
            selectHeaderTab: function (newTabIndex, oldTabIndex) {

                if (oldTabIndex == newTabIndex) {
                    return;
                }

                var newState = $state.current.name;

                //if (newState == "app.chatList") // && newTabIndex != 2) 
                //{
                //    if (!_self.profileData.checkedIn && newTabIndex == 1) {
                //        //$rootScope.clickLoading("app.checkin");
                //        $state.go("app.checkin");
                //        return;
                //    }
                //    else 
                //    if (newTabIndex == 1 && _self.profileData.checkedIn) {
                //        if(newState!="app.chatList" || $stateParams.hotelScope!="true")
                //        {
                //            $state.go("app.chatList", {hotelScope: true, filterCity: "", filterHotelId:""});
                //        }
                //        return;
                //    }
                //    else 
                //    if (newTabIndex == 2) {
                //        
                //        return;
                //    }
                //    //else {
                //    //    if(newState!="app.chatList" || $stateParams.hotelScope!="false")
                //    //    {
                //    //        $state.go("app.chatList", {hotelScope: false, filterCity: "", filterHotelId:""});
                //    //    }
                //    //    return;
                //    //}
                //
                //    //return;
                //}
                //else 
                if (newTabIndex == 0) {
                    
                    if(newState == 'app.dealList' || newState == 'app.activityList')
                    {
                        return
                    }
                    
                    if(newState == 'app.menuList')
                    {
                        return
                    }
                    
                    var nextState =  _self.getDefaultStartState();
                    $state.go(nextState);
                    return;
                }
                else if (newTabIndex == 1) {
                    
                    $rootScope.clickLoading("app.chatList");
                    return;
                }
                else if (newTabIndex == 2) {

                    //if(newState == 'app.menuList')
                    //{
                    //    return
                    //}
                    //$state.go("app.about");
                    $rootScope.clickLoading("app.wall");
                }

                //$timeout(function(){
                //    $rootScope.header.tabIndex = newTabIndex;
                //}, 100);
                return;
            },
            
            checkHeaderTab: function()
            {
                var newState = $state.current.name;
                
                if(newState == "app.wall")
                {
                    $rootScope.header.tabIndex = 2;
                }
                else if(newState == "app.chat" || newState == "app.chatList")
                {
                    $rootScope.header.tabIndex = 1;
                }
                else 
                    $rootScope.header.tabIndex = 0;
            },
            /**
             * ALL IMPORTANT STATES should call it at start, to check (hotelState)
             * @param nowState
             * @returns {boolean}
             */
            checkNextRouteState: function (nowState) {
                //############## Calculate new State ###########################

                var stateChanged = false;

                if (!nowState) {
                    nowState = $state.current.name;
                }

                if (nowState == "app.hotelRegister") {
                    //return  - because I don't want to get standard state for the current tab!
                    return;
                }

                if (nowState == "") {
                    if ((window.location.href + "").indexOf("access/login") >= 0) {
                        nowState = "tryaccess.login";
                    }
                    else if ((window.location.href + "").indexOf("app/checkin") >= 0) {
                        nowState = "tryaccess.checkin";
                    }
                }

                if (!_self.profileData || _self.profileData.id <= 0 || !_self.profileData.firstName) {

                    $state.go(_self.getDefaultStartState(), {stopLogin: true});
                    return false;
                }
                //IF TRANSITIONAL STATES, MAYBE NEW STATE!!!!
                else if (_self.profileData && _self.profileData.logged && (_self.checkTransitionStates.indexOf(nowState)>=0)) {
                    var nextState = _self.profileData.checkedIn ? "app.hotel" : "app.checkin";

                    if (nextState == nowState) {
                        return true;
                    }

                    stateChanged = true;
                    $state.go(nextState);

                    return false;
                }
                else if (nowState == "tryaccess.login" || nowState == "tryaccess.checkin") {
                    $state.go(_self.getDefaultStartState());
                }

                //if(!stateChanged && nowState && (nowState+"").indexOf("access")<0)
                //{
                //    $rootScope.updateHeaderTitle(nowState);
                //}

                return true;
            },

            emptyCheckinData: function () {

                _self.hotelCustomers = [];
                _self.hotelStaffCustomers = [];
                _self.contactCustomers = [];

                _self.hotelActivities = [];
                _self.currentHotel = _service.getInitHotel();
            },

            getRequesterId : function()
            {
                return _self.profileData.id>0? _self.profileData.id : _service.guestCustomerId? parseInt(_service.guestCustomerId) : null;  
            },
            
            //
            emptyLoginData: function () {

                _self.resetDeferredState();
                
                var nowState = $state.current.name;
                $rootScope.header.showTab = false;

                _self.emptyCheckinData();

                _self.profileData = _service.getInitCustomer();
                _self.showProfile = false;

                $rootScope.updateHeaderTitle(nowState);

                _service.delCookie('hotelicoUserId');

                $cookies.hotelicoLogout = true;

                if ( (nowState + "").indexOf("about") < 0 && _self.noLoginCheckStates.indexOf(nowState)<0)
                {
                    $state.go(_self.getDefaultStartState(), {stopLogin: true});
                }

                return false;
            },

            addUpdateMenuOrder : function(menuOrder)
            {
                if(!_self.hotelMenuOrders)
                {
                    _self.hotelMenuOrders = [];
                }
    
                var menuUpdated = false;
    
                for(var n=0; n<_self.hotelMenuOrders.length; n++)
                {
                    if(_self.hotelMenuOrders[n].initId == menuOrder.initId)
                    {
                        _self.hotelMenuOrders[n].orderStatus = menuOrder.orderStatus;
    
                        menuUpdated = true;
                    }
                }
    
                if(!menuUpdated)
                {
                    _self.hotelMenuOrders.push(menuOrder);
                }
    
                //$scope.updateLocalFilter();
            },
            
            getCurrentHotel: function (hotelId, customerObject, forceUpdate) {

                var deferredCurHotel = $q.defer();

                if (!customerObject) {
                    if (!_self.profileData || !_self.profileData.logged) {
                        deferredCurHotel.reject("NoLogin");
                        return deferredCurHotel.promise;
                    }

                    customerObject = _self.profileData;
                }

                if (!hotelId) {
                    hotelId = _self.profileData.hotelId;
                }

                //Current hotel already loaded
                if (!forceUpdate && hotelId == customerObject.hotelId && _self.currentHotel && _self.currentHotel.id == hotelId) {
                    deferredCurHotel.resolve(_self.currentHotel);
                    return deferredCurHotel.promise;
                }

                var requesterId = _self.getRequesterId();
                
                if (hotelId && hotelId > 0 && requesterId > 0) {
                    _self.currentHotel = HotelDto.get({customerId: requesterId, hotelId: hotelId}, function (hotelObj) {

                        _self.currentHotel = _service.decodeHotel(hotelObj);
                        //_self.currentHotelId = _self.currentHotel.id;

                        //ON PROMISE: always update title of app on hotel change
                        if ($rootScope.updateHeaderTitle && !hotelObj.virtual) {
                            $rootScope.updateHeaderTitle($state.current.name, _self.currentHotel.name);
                        }

                        var hotelImageCacher = document.getElementById("hotelImageCache");

                        if (hotelImageCacher) {
                            hotelImageCacher.innerHTML = "";
                            hotelImageCacher.innerHTML += "<img src='" + hotelObj.pictureUrl + "' />"
                        }

                        deferredCurHotel.resolve(_self.currentHotel);

                    });
                }
                else {
                    //_self.currentHotel = _service.getInitHotel();
                    deferredCurHotel.reject("noCheckin");
                }

                return deferredCurHotel.promise;
            },

            //getCurrentHotelId: function () {
            //
            //    if (_self.currentHotel && _self.currentHotel.id > 0) {
            //        return _self.currentHotel.id;
            //    }
            //    else {
            //
            //        //TODO Eugen: load current hotel
            //        return _self.getVirtualHotel().id;
            //    }
            //
            //},

            //getVirtualHotel: function () {
            //
            //    if (_self.virtualHotel) {
            //        return _self.virtualHotel;
            //    }
            //
            //    if (!_self.profileData) {
            //        return null;
            //    }
            //
            //    var virtualHotelPromise = HotelDto.get({customerId: _self.profileData.id, hotelId: -999}, function (virtualHotel) {
            //
            //        if (virtualHotel.id > 0) {
            //            _self.virtualHotel = _service.decodeHotel(virtualHotel);
            //
            //            var hotelImageCacher = document.getElementById("hotelImageCache");
            //
            //            if (hotelImageCacher) {
            //                hotelImageCacher.innerHTML = "";
            //                hotelImageCacher.innerHTML += "<img src='" + virtualHotel.pictureUrl + "' />"
            //            }
            //        }
            //        else {
            //            _self.virtualHotel = null;
            //        }
            //        
            //        _deferredState.resolve(_self);
            //
            //        return _self.virtualHotel;
            //
            //    });
            //
            //    return virtualHotelPromise;
            //},

            getHotelCustomers: function (hotelId, forceUpdate) {

                var deferredHotelCustomers = $q.defer();

                if (!hotelId) {
                    hotelId = _self.profileData.hotelId;
                }

                if (!forceUpdate && _self.hotelCustomers && _self.hotelCustomers.length > 0 && _self.hotelCustomers[0].hotelId == hotelId) {
                    deferredHotelCustomers.resolve(_self.hotelCustomers);
                    return deferredHotelCustomers.promise;
                }

                var requesterId = _self.getRequesterId();
                
                if (hotelId > 0 && requesterId > 0) {

                    var CustomersByHotelService = $resource('./customers/customers/:customerId/hotel/:hotelId/addStaff/:addStaff', {customerId: '@customerId', hotelId: '@hotelId', addStaff: '@addStaff'}, {'getCustomers': {method: 'GET', isArray: true}});

                    var hotelCustomers = CustomersByHotelService.getCustomers({customerId: requesterId, hotelId: hotelId, addStaff: true}, function () {

                        _self.hotelCustomers = [];
                        _self.hotelStaffCustomers = [];

                        for (var c = 0; c < hotelCustomers.length; c++) {
                            
                            hotelCustomers[c] = _service.decodeCustomer(hotelCustomers[c]);
                            
                            //ADD all on central array
                            _self.allCustomersById[hotelCustomers[c].id] = hotelCustomers[c];

                            if (hotelCustomers[c].id != _self.profileData.id) {
                                if (hotelCustomers[c].hotelStaff)
                                    _self.hotelStaffCustomers[_self.hotelStaffCustomers.length] = hotelCustomers[c];
                                else if(hotelCustomers[c].showInGuestList || hotelCustomers[c].chatWithMe){
                                    //All hotel customers, egal ob kontakt mit mir oder nicht
                                    _self.hotelCustomers[_self.hotelCustomers.length] = hotelCustomers[c];
                                }

                            }
                        }

                        deferredHotelCustomers.resolve(hotelCustomers);
                    });
                }
                else {
                    _self.hotelCustomers = [];
                    _self.hotelStaffCustomers = [];
                    //_self.staffCustomer = null;
                    deferredHotelCustomers.reject("noLogin");
                }

                return deferredHotelCustomers.promise;

            },

            /// add new customer to chatList

            waitingCustomerEvents : [],

            handleHotelEvent: function (eventObject) 
            {
                var newCustomerId = eventObject && eventObject["senderId"] ? eventObject["senderId"] : 0;

                if (newCustomerId && newCustomerId > 0) {
                    if (!_self.waitingCustomerEvents) {
                        _self.waitingCustomerEvents = [];
                    }

                    _self.waitingCustomerEvents[newCustomerId] = eventObject;

                    var changedCustomer = getUserWithLastMessagesService.getCustomerWithLastMessage({getId: newCustomerId, senderId: _self.profileData.id}, function () {

                        if (!changedCustomer || changedCustomer.id <= 0 || !_self.waitingCustomerEvents[changedCustomer.id]) {
                            return;
                        }

                        //var newCustomerEvent = _self.waitingCustomerEvents[changedCustomer.id];

                        var newEventType = eventObject["event"];

                        var eventHotelId = eventObject["hotelId"];

                        var entityId = eventObject["entityId"];

                        var entity = eventObject["entity"];

                        var eventMessage = eventObject["message"];

                        changedCustomer = _service.decodeCustomer(changedCustomer);
                        
                        //SET TO WHOLE CUSTOMERS ARRRAY
                        _self.allCustomersById[changedCustomer.id] = changedCustomer;

                        var addedToChatHotel = false;
                        var removedFromChatHotel = false;
                        var addedToChatPartners = false;
                        var removedFromChatPartners = false;

                        ////if new message to me, and chatPartner not in my real hotel
                        //if (newEventType == "EVENT_WALL_NEW_MESSAGE") {
                        //    //TODO Eugen: alert about wall message  
                        //
                        //    if($state.current.name == "app.wall")
                        //    {
                        //        return;
                        //    }
                        //    
                        //    if (eventHotelId == _self.profileData.hotelId && _self.profileData.checkedIn) {
                        //
                        //    }
                        //
                        //    return;
                        //}
                        //else 
                        if ((newEventType+"").indexOf("EVENT_LOGO_")>=0) {
                            if (entity == "Hotel") {
                                
                                if(eventHotelId == _self.profileData.hotelId)
                                {
                                    _self.currentHotel.pictureUrl = eventMessage;
                                }
                                
                                $rootScope.setEntityLogo(entity, entityId, eventMessage);
                                
                            }
                            else if (entity == "HotelActivity") {
                                
                                if(eventHotelId == _self.profileData.hotelId)
                                {
                                    for (var a = 0; a < _self.hotelActivities.length; a++) {
                                        if (_self.hotelActivities[a].initId == entityId) {
                                            _self.hotelActivities[a].pictureUrl = eventMessage;
                                            break;
                                        }
                                    } 
                                }

                                for (var a = 0; a < _self.allActivities.length; a++) {
                                    if (_self.allActivities[a].initId == entityId) {
                                        _self.allActivities[a].pictureUrl = eventMessage;
                                        break;
                                    }
                                }
                                
                                $rootScope.setEntityLogo(entity, entityId, eventMessage);
                            }
                            else if (entity == "Customer" && _self.allCustomersById[entityId]) {
                                _self.allCustomersById[entityId].avatarUrl = eventMessage;
                                
                                if(_self.profileData && _self.profileData.id == entityId)
                                {
                                    _self.profileData.avatarUrl = eventMessage;
                                }
                            }


                            return;
                        }
                        //if new message to me, and chatPartner not in my real hotel
                        else if (newEventType == "EVENT_CHAT_NEW_MESSAGE") {
                            if (!changedCustomer.lastMessageToMe) // || (_self.profileData.checkedIn && changedCustomer.hotelId == _self.profileData.hotelId))
                            {
                                return;
                            }

                            if(_self.profileData.checkedIn && changedCustomer.hotelId == _self.profileData.hotelId)
                            {
                                for (var c = 0; c < _self.hotelCustomers.length; c++) {
                                    if (_self.hotelCustomers[c].id == changedCustomer.id) {
                                        _self.hotelCustomers[c] = changedCustomer;
                                        return;
                                        //return;
                                    }
                                }

                                _self.hotelCustomers.push(changedCustomer);

                                addedToChatHotel = true;
                            }
                            else{
                                
                                for (var c = 0; c < _self.contactCustomers.length; c++) {
                                    if (_self.contactCustomers[c].id == changedCustomer.id) {
                                        _self.contactCustomers[c] = changedCustomer;
                                        return;
                                        //return;
                                    }
                                }

                                _self.contactCustomers.push(changedCustomer);

                                addedToChatPartners = true;
                            }

                            $rootScope.$broadcast('chatListChangeEvent', []);

                        }
                        //if the new customer is checkin in my real hotel
                        else if (newEventType == "EVENT_CHECKIN") {
                            if (entityId != _self.profileData.hotelId || !_self.profileData.checkedIn || changedCustomer.id == _self.profileData.id) {
                                return;
                            }

                            $rootScope.addToasterMessage('success', eventMessage, changedCustomer.firstName + " " + changedCustomer.lastName);

                            for (var c = 0; c < _self.hotelCustomers.length; c++) {
                                if (_self.hotelCustomers[c].id == changedCustomer.id) {
                                    _self.hotelCustomers[c] = changedCustomer;
                                    return;
                                }
                            }

                            _self.hotelCustomers.push(changedCustomer);
                            addedToChatHotel = true;
                        }
                        //if new customer checkout and not in my hotel more
                        else if (newEventType == "EVENT_CHECKOUT") {
                            if (!_self.hotelCustomers || entityId != _self.profileData.hotelId || !_self.profileData.checkedIn) {
                                return;
                            }

                            for (var c = 0; c < _self.hotelCustomers.length; c++) {
                                if (_self.hotelCustomers[c].id == changedCustomer.id) {
                                    //remove checkout customer from my real hotel
                                    _self.hotelCustomers.splice(c, 1);
                                    return;
                                }
                            }

                            removedFromChatHotel = true;
                        }
                        ////If not in my real hotel and has chat with me
                        //if(changedCustomer.lastMessageToMe && (eventHotelId!=_self.currentHotel.id || _self.currentHotel.virtual) && _self.contactCustomers) //chat with me
                        //{
                        //    for(var c=0; c<_self.contactCustomers.length; c++)
                        //    {
                        //        if(_self.contactCustomers[c].id == changedCustomer.id)
                        //        {
                        //            _self.contactCustomers[c] = changedCustomer;
                        //            return;
                        //        }
                        //    }
                        //
                        //    _self.contactCustomers.push(changedCustomer);
                        //    addedToChatPartners = true;
                        //}
                        else if (newEventType == "EVENT_REGISTER" && _self.allCustomersById)//other chat vip users
                        {
                            $rootScope.addToasterMessage('success', eventMessage, changedCustomer.firstName + " " + changedCustomer.lastName);

                            //_self.allCustomersById.push(changedCustomer);
                        }
                        else if (newEventType == "EVENT_REMOVE_ACTIVITY"){
                            
                            if(eventHotelId == _self.profileData.hotelId)
                            {
                                for (var a = 0; a < _self.hotelActivities.length; a++) {
                                    if (_self.hotelActivities[a].initId == entityId) {
                                        _self.hotelActivities.splice(a, 1);
                                        break;
                                    }
                                }
                            }

                            for (var a = 0; a < _self.allActivities.length; a++) {
                                if (_self.allActivities[a].initId == entityId) {
                                    _self.allActivities.splice(a, 1);
                                    break;
                                }
                            }
                        }
                        
                        ////if added to real hotel chat list, remove from chat list
                        //if( addedToChatHotel && _self.contactCustomers)
                        //{
                        //    //TODO Eugen: remove from chatPartners
                        //    for(var c=0; c<_self.contactCustomers.length; c++)
                        //    {
                        //        if(_self.contactCustomers[c].id == changedCustomer.id)
                        //        {
                        //            _self.contactCustomers.splice(c, 1);
                        //            removedFromChatPartners = true;
                        //        }
                        //    }
                        //}

                        ////remove from vip lists
                        //if((addedToChatPartners && !removedFromChatHotel) || (addedToChatHotel && !removedFromChatPartners))
                        //{
                        //    //TODO Eugen: remove from chatPartners -> with filter
                        //    //for(var c=0; c<_self.allChatCustomers.length; c++)
                        //    //{
                        //    //    if(_self.allChatCustomers[c].id == changedCustomer.id)
                        //    //    {
                        //    //        _self.allChatCustomers.splice(c, 1);
                        //    //        removedFromOtherList = true;
                        //    //    }
                        //    //}
                        //}

                        ////if removed from chatHotel, and has no messages to me -> so add to vip list
                        //if(removedFromChatHotel && !addedToChatPartners)
                        //{
                        //    _self.allChatCustomers.push(changedCustomer);
                        //}
                        ////}

                    });
                }

            },
            
        requestCheckinGPS : false,
            
        isHotelStaffOrAdmin : false,    
            
        detectHotelStaffOrAdmin : function(customHotelId)
        {
            var isStaffOrAdmin = _self.profileData.id>0 && _self.profileData.logged && (_self.profileData.hotelStaff && _self.profileData.checkedIn && (!customHotelId || _self.profileData.hotelId == customHotelId) || _self.profileData.admin);
        
            if(!customHotelId)
            {
                _self.isHotelStaffOrAdmin = isStaffOrAdmin;
            }
        
        },

        updateState : function (newProfil) {
                if (newProfil && newProfil.id > 0 && newProfil.firstName && _self.setProfileData) {
                    _self.setProfileData(newProfil);
                }
                else if (_self.profileData && _self.profileData.id > 0 && _self.setProfileData) {
                    _self.setProfileData(_self.profileData);
                }
    
            },

            goStateBack: function () {

                _self.ignoreNextHistoryState = true;
                
                var nextStateObj = [];
                nextStateObj["stateName"] = _self.getDefaultStartState();
                nextStateObj["stateParams"] = {};
    
                if (_self.profileData && _self.profileData.logged && _self.profileData.id > 0 && _self.getPreviousRouteState) {
                    var prevStateObj = _self.getPreviousRouteState();
    
                    if (prevStateObj && prevStateObj["stateName"] && nextStateObj["stateParams"]) {
                        nextStateObj = prevStateObj;
                    }

                    if(prevStateObj && prevStateObj.stateName || nextStateObj["stateName"]!=$state.current.name)
                    {
                        //Go in old state, if old state exists or zielState is another than now
                        $state.go(nextStateObj["stateName"], nextStateObj["stateParams"]);
                    }
                    else{
                        $rootScope.showLoading(false);    
                    }
                    
                    return;
                }
                else {
    
                    if($state.current.name != _self.getDefaultStartState())
                    {
                        $state.go(_self.getDefaultStartState());
                    }
                    else{
                        $rootScope.showLoading(false);
                    }
                }
    
                return;
            }
    };
        
    state.initState();  
    
    return state;
        
});
 
