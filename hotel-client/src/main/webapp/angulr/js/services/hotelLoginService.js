//app
angular.module("hotelApp.hotelLoginService", [ "ngResource", "hotelApp.sessionCustomerService", "hotelApp.sessionCustomerService", "hotelApp.hotelState", "hotelApp.customerDto", 'hotelApp.hotelService' ])
    .factory("HotelLogin", function($q, $rootScope, $resource, $state, $location, $cookies, $timeout, SessionCustomer, HotelState, $filter, $window, CustomerDto, HotelService, LogoutCustomer, NotificationService, HotelNotification) {
        
        var _self = this;
        
        var _hotelState = HotelState;
        var _hotelService = HotelService;
        
        var deferredLogin = $q.defer();

        var service = {

            loginRestService : $resource("./customers/login/email/:loginemail/password/:loginpassword", {
                loginemail: '@loginemail',
                loginpassword: '@loginpassword'
            }, {
                get: {
                    method: 'GET' ,
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
                ,check: {
                    method: 'POST' ,
                    headers: {
                        //Problems here
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
                ,update: {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
            }),

            loginSocialService : $resource("./social/login/auth/:type/:loginId", {
                type: '@type',
                loginId: '@loginId'
            }, {
                get: {
                    method: 'GET' ,
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
                ,check: {
                    method: 'POST' ,
                    headers: {
                        //Problems here
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
                ,update: {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
            }),
            
            initLogin: function(){

                _self = this;
                
            },

            /**
             * If user requested State, then requires login, save the requested State hier and open login popup!
             */
            loginRequestedState : null,
            loginRequestedStateParams : {},

            getLoginRequestedState: function(){

                //if (_self.loginRequestedState)
                {
                    return _self.loginRequestedState;
                }
                
                //if there no requested state, return default
                //return _hotelState.getDefaultStartState();
            },
            
            getLoginRequestedStateParams: function(){

                return _self.loginRequestedStateParams;
            },

            setLoginRequestedState: function(requestedState, requestedStateParams)
            {
                _self.loginRequestedState = requestedState;
                _self.loginRequestedStateParams = requestedStateParams;

            },
            
            resetRequestedLoginState: function(){
                _self.loginRequestedState = null;
                _self.loginRequestedStateParams = {}
            },

            fillPreLoginProperties: function(logingObj)
            {
                // ############ hide popup ###################
                if(_hotelState.profileData.hideCheckinPopup)
                    logingObj.hideCheckinPopup = true;
                
                if(_hotelState.profileData.hideHotelListPopup)
                    logingObj.hideHotelListPopup = true;

                if(_hotelState.profileData.hideWallPopup)
                    logingObj.hideWallPopup = true;

                // ########## checkin Info ###################
                if(!logingObj.hotelCode && _hotelState.profileData.hotelCode)
                {
                    logingObj.hotelCode = _hotelState.profileData.hotelCode;
                }

                if((!logingObj.hotelId || logingObj.hotelId==0) && (_hotelState.profileData.hotelId!="0" || _hotelState.profileData.hotelId>0))
                {
                    logingObj.hotelId = _hotelState.profileData.hotelId;
                }
                
                logingObj.checkinFrom = _hotelState.profileData.checkinFrom;
                logingObj.checkinTo = _hotelState.profileData.checkinTo;
                
                if(HotelService.guestCustomerId)
                {
                    if(!logingObj.systemMessages)
                    {
                        logingObj.systemMessages = {};
                    }

                    //TODO eugen: test it!!!
                    logingObj.systemMessages["guestCustomerId"] = HotelService.guestCustomerId;
                }
                
                return logingObj;
            },

            loginSocial : function (type, eltId) {

                //TODO Eugen: remove hotel guest push id


                $rootScope.clickLoading(null, eltId);
                $rootScope.showLoading(true);

                /**
                 * CREATE A CLONe OF PROFILE OBJECT, WITH THEIR SETTINGS
                 */
                var socialLogin = new _self.loginSocialService();

                $rootScope.rootErrorMsg = false;

                socialLogin.prefferedLanguage = _hotelState.getPrefferedLanguage();

                socialLogin = _self.fillPreLoginProperties(socialLogin);

                socialLogin.guestAccount = true;

                var requestedState = _self.getLoginRequestedState();

                if(requestedState)
                {
                    var requestedStateParams = _self.getLoginRequestedStateParams();

                    if(!socialLogin.systemMessages)
                    {
                        socialLogin.systemMessages = {};
                    }
                    
                    //TODO eugen: test it!!!
                    socialLogin.systemMessages["requestedState"] = requestedState;
                    socialLogin.systemMessages["requestedStateParams"] = JSON.stringify(requestedStateParams);
                }
                
                _self.loginId = new Date().getTime()+"";
                _self.socType = type+"";
                
                _self.loginSocialService.check({type: type, loginId: _self.loginId}, socialLogin, function(resp){
                   
                   if(_self.socType && _self.loginId)
                   {
                       $rootScope.clickLoading(null, null, 'social/login/auth/'+_self.socType+'/'+_self.loginId);
                   }
                    //_self.loginSocialService.get({type: type, loginId: _self.loginId});
                    _self.loginId = null;
                    _self.socType = null;
                }, 
                    
                $rootScope.showErrorObj);
                
            },
            
            guestRegister : function (guestUser) {

                //TODO Eugen: remove hotel guest push id
                
                var guest = new CustomerDto();
                
                $rootScope.rootErrorMsg = false;
                
                guest.firstName = guestUser.firstName;
                guest.sex = guestUser.sex;
    
                guest.prefferedLanguage = _hotelState.getPrefferedLanguage();
    
                if (guest.sex == undefined) {
                    //if (_self.loginTabState.guest) {
                        $rootScope.rootErrorMsg = $filter('translate')('alert.error.noGender');
                    //}
                    return;
                }
    
                if (guest.firstName == undefined || guest.firstName == "") {
                    //guest.firstName = "anonymous";
                    //if (_self.loginTabState.guest) {
                        $rootScope.rootErrorMsg = $filter('translate')('alert.error.noName');
                    //}
                    return;
                }
    
                if (guest.lastName == undefined) {
                    guest.lastName = "";
                }

                guest = _self.fillPreLoginProperties(guest);
                
                guest.guestAccount = true;
    
                $rootScope.showLoading(true);
                $rootScope.rootErrorMsg = false;

                guest.$save(function (responseUser) {
    
                    $rootScope.showLoading(false);
    
                    if (responseUser.id && responseUser.id > 0) {
                       
                        //TODO Eugen???
                        //_self.localState.currentUser = responseUser;
    
                        if (responseUser.id>0) {
                            //$cookies.hotelicoUserId = $scope.localState.currentUser.id;
                            HotelService.setCookie('hotelicoUserId', responseUser.id);
    
                            $cookies.hotelicoUserEmail = responseUser.eMail;
    
                            $cookies.hotelicoLogout = false;
    
                            //if(_hotelState)
                            //{
                            //    _hotelState.setProfileData(responseUser);
                            //}
                            ////TODO Eugen:
                            //else
                            {
                                _hotelState.initState(responseUser);
                            }
    
                            //$location.path('/app/checkin');
                            $rootScope.closeModal();
                            
                            //var requestedState = _self.getLoginRequestedState();
                            //var requestedStateParams = _self.getLoginRequestedStateParams();
                            //$state.go(requestedState, requestedStateParams);
                            //_self.resetRequestedLoginState();
                        }
    
                    }
                    else if (responseUser.errorResponse) {
                        $rootScope.rootErrorMsg = responseUser.errorResponse;
                        $rootScope.showLoading(false);
                        //$rootScope.topPage();
                    }
                }, function (errorObj) {
                    
                    $rootScope.showErrorObj(errorObj);

                    $rootScope.showLoading(false);
                    _self.resetRequestedLoginState();
                });

            },
                
            loginPassword : function (checkUser) {

                //TODO Eugen: remove hotel guest push id

                $rootScope.showLoading(true);
                
                var loginError = "";
                
                if (checkUser.password.length < 6) {
                    loginError = $filter('translate')('alert.error.shortPassword');
                }
                
                if(loginError)
                {
                    $rootScope.rootErrorMsg = loginError;
                    $rootScope.showLoading(false);
                    //$rootScope.topPage();
                    return;
                }

                checkUser = _self.fillPreLoginProperties(checkUser);

                $rootScope.rootErrorMsg = false;

                _self.loginRestService.update({loginemail: checkUser.email, loginpassword: checkUser.password}, checkUser, function (loggedUser) {
                    if (loggedUser && loggedUser.id && loggedUser.id > 0) {
                        //$cookies.hotelicoUserId = loggedUser.id;
                        //$cookies.hotelicoUserEmail = loggedUser.eMail;
                        //$cookies.hotelicoLogout = false;
                    }
                    else if (loggedUser.errorResponse) {
                        $rootScope.rootErrorMsg = loggedUser.errorResponse;
                        $rootScope.showLoading(false);
                        return;
                    }
                    else {
                        $rootScope.rootErrorMsg =  $filter('translate')('alert.error.wrongMailPassword');
                        $rootScope.showLoading(false);
                        return;
                    }
                    //$rootScope.showLoading(false);

                    //var requestedState = _self.getLoginRequestedState();
                    //var requestedStateParams = _self.getLoginRequestedStateParams();

                    //_hotelState.relocateOnLoginState = requestedState;
                    //_hotelState.relocateOnLoginStateParams = requestedStateParams;
                    
                    //_hotelState.initState(loggedUser, requestedState, requestedStateParams);

                    $rootScope.closeModal();
                    $rootScope.showLoading(false);
                    
                    //$state.go(requestedState, requestedStateParams);
                    //_self.resetRequestedLoginState();
                    
                   return _hotelState.initState(loggedUser);

                }, function (errorObj) {

                    $rootScope.showErrorObj(errorObj);

                    $rootScope.showLoading(false);
                    _self.resetRequestedLoginState();

                });
            },
            
            getLogin: function(email, password){
                
                //TODO Eugen:
               return _self.loginRestService.get({loginemail: email, loginpassword: password});
            },
            
            resetLogin : function()
            {
                if(_hotelState)
                {
                    _hotelState.emptyLoginData();
                }
    
                LogoutCustomer.get();

                _self.resetRequestedLoginState();
                
                _hotelService.delCookie("hotelicoUserId");
    
                NotificationService.resubscribeWithCustomerData();

                $rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key: -1});
                
                //Reset notifications, if logout!!!  
                HotelNotification.notificationObj = {};

                $window.location.reload();

                $state.go(_hotelState.getDefaultStartState(), {stopLogin : true});

            }
        
     }
        
     service.initLogin();  
        
     return service;
        
});
 
