'use strict';

/* Controllers */

angular.module('app')
    //app
    .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', '$route', '$rootScope', '$resource', '$location', '$state', '$stateParams', 'CustomerDto', 'HotelDto', '$anchorScroll', '$timeout', 'HotelNotification', 'SessionCustomer', 'HotelLogin', 'LogoutCustomer', 'HotelState', 'HotelService', 'HotelCheckin', 'NotificationService', 'Upload', '$q', '$mdDialog', '$modal', '$log', '$filter',
        function(              $scope,   $translate,   $localStorage,   $window, $route,  $rootScope, $resource, $location, $state, $stateParams,  CustomerDto, HotelDto, $anchorScroll, $timeout, HotelNotification, SessionCustomer, HotelLogin, LogoutCustomer, HotelState, HotelService,  HotelCheckin, NotificationService, Upload, $q, $mdDialog, $modal, $log, $filter) {

            // REGISTER SERVICES IN SCOPE ///////////////////////////

            var startWerbung = null;

            $rootScope.closeStartWerbung = function()
            {
                if(!startWerbung)
                {
                    startWerbung = document.getElementById("startWerbung");
                }

                if(startWerbung)
                {
                    startWerbung.style.display = "none";
                }

                //enable staticLoading
                var staticLoading = document.getElementById("staticLoading");
                if(staticLoading)
                {
                    staticLoading.style.display = "block";
                }

                //disable animationLoading
                var animationLoading = document.getElementById("circleG");
                if(animationLoading)
                {
                    animationLoading.style.display = "none";
                }

                //startWerbung.parentNode.removeChild(startWerbung);
                //$rootScope.hideLoading();
            }

            $scope.hotelState = HotelState;
            $scope.hotelService = HotelService;
            $scope.loginService = HotelLogin;
            $scope.checkinService = HotelCheckin;
            $scope.hotelNotification = HotelNotification;

            ///####### GLOBAL ROOTSCOPE FIELDS: ####################################  
            
            $scope.initMainState = function(stateObj)
            {
                if(!stateObj)
                {
                    return;
                }

                $rootScope.closeStartWerbung();

                if(($scope.hotelState.getPrefferedLanguage()+"").indexOf("_")>=0)
                {
                    var viewKey = $scope.hotelService.convertLanguageKey($scope.hotelState.getPrefferedLanguage());
                    $scope.hotelState.setPrefferedLanguage(viewKey);
                }

                if($translate.use()!=$scope.hotelState.getPrefferedLanguage())
                    $translate.use($scope.hotelState.getPrefferedLanguage());

                $rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key:  $scope.hotelState.getRequesterId()});

                //if($scope.hotelState.profileData.checkedIn && $rootScope.isChromeDevice && !$scope.hotelService.chromePushRegistrationId && !$scope.hotelState.profileData.hideChromePushPopup && $scope.hotelService.noPushIdRegistered)
                //{
                //    if(!$scope.hotelService.cancelPushRequest)
                //    {
                //        $scope.openModal("showPush", null, "sm");
                //    }
                //}
            };

            HotelState.getDeferredState().then(function(stateObj){

                $scope.hotelState = stateObj;

                //$scope.hotelCheckin = HotelCheckin;

                $scope.initMainState(stateObj);

                NotificationService.resubscribeWithCustomerData(stateObj);
                //NotificationService.resubscribeWithHotelData(stateObj);

                $rootScope.startGlobalInterval();
                
                $scope.hotelState.submitHotelGuestPush();
                
                $scope.hotelState.detectHotelStaffOrAdmin();
                
            });


            //$scope.serverCommunicator = ServerCommunicator;

            $scope.mainState = {

                successMsg : false,
                errorMsg : false,
                hidePopup: false,
                showCheckinWellcomePopup: false,
                globalIntervalCheck : 0,
                tempUploadFiles: [],
                hotelArray: [],
                //messages read by customer, but not checked yet in Server
                readUncheckedMessages: $localStorage.readUncheckedMessages? $localStorage.readUncheckedMessages : [],
                dummyAllHotelsObj : $scope.hotelService.getInitHotel()
            };

            //////////////////////////////////////////////////////////


            /////////////////////////////////////////////////////////////

            $rootScope.rootErrorMsg = false;

            $rootScope.cutMessageIndex = 25;


            //var globalBlock = false;

            var loadingElt = null;

            $scope.openHotelCodePopup = function(ev)
            {
                var confirm = $mdDialog.confirm( {
                        clickOutsideToClose: true
                    })
                    .title($filter('translate')('page.checkin.realHotelDescriptionHeader'))
                    .content($filter('translate')('page.checkin.realHotelDescription'))
                    .ariaLabel('Lucky day')
                    .ok('OK')
                    //.cancel($filter('translate')('alert.noPopupMore'))
                    .targetEvent(ev);

                $mdDialog.show(confirm).then(function() {
                    ;

                }, function() {
                    //$scope.hotelState.profileData.hideWallPopup = true;
                    //$scope.hotelService.setCookie('hotelicohideWallPopup', true);
                    //
                    //$scope.hotelState.submitProfileData(true);
                });
            }

            $scope.openNeedCheckinPopup = function(ev)
            {
                var confirm = $mdDialog.confirm( {
                        clickOutsideToClose: true
                    })
                    .title($filter('translate')('page.checkin.needCheckinPopupHeader'))
                    .content($filter('translate')('page.checkin.needCheckinPopupContent'))
                    .ariaLabel('Lucky day')
                    .ok($filter('translate')('system.cancel'))
                    .cancel($filter('translate')('alert.info.demoCheckin'))
                    .targetEvent(ev);

                $mdDialog.show(confirm).then(function() {
                    ;

                }, function() {
                    $scope.checkinService.resetWholeInfo();
                    $scope.checkinService.hotelCode = "demo";

                    $scope.checkinService.doCheckin().then(function () {

                    });

                });
            }


            $scope.showErrorPopup = function(ev) {

                if(!$rootScope.isSmartDevice)
                {
                    return;
                }

                if($scope.mainState.errorMsg)
                {
                    var confirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        }
                        )
                        .title('Error')
                        .content($scope.mainState.errorMsg)
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        //.cancel('Nicht mehr zeigen')
                        .targetEvent(ev);
                    $mdDialog.show(confirm).then(function() {
                            var t =0;
                        }
                    );
                }
                else if($rootScope.rootErrorMsg)
                {
                    var confirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        }
                        )
                        .title('Error')
                        .content($rootScope.rootErrorMsg)
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        //.cancel('Nicht mehr zeigen')
                        .targetEvent(ev);

                    $mdDialog.show(confirm).then(function() {
                            var t =0;

                        }
                        //    , function() {
                        //    $scope.hotelState.profileData.hideCheckinPopup = true;
                        //    $scope.hotelState.submitProfileData(true);
                        //}
                    );
                }
            };

            
            $scope.tryOpenPush = function()
            {

                if($rootScope.isChromeDevice && !$scope.hotelService.cancelPushRequest  && !$scope.hotelService.chromePushRegistrationId && !$scope.hotelState.profileData.hideChromePushPopup && $scope.hotelService.noPushIdRegistered)
                {
                    setTimeout(function()
                    {
                        $scope.mainPushRequest();

                    }, 1000*30);
                }

                if($rootScope.isChromeDevice)
                {
                    setTimeout(function()
                    {
                        $rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key:  $scope.hotelState.getRequesterId()});

                    }, 5000);
                }

            }

            $rootScope.topPage = function() {

                document.body.scrollTop =-20;


                document.documentElement.scrollTop =-20;
                document.body.parentNode.scrollTop =-20;

                $scope.showErrorPopup();

            };

            $rootScope.showLoading = function(isShown, block)
            {
                if(!$scope.loadingElt)
                {
                    $scope.loadingElt = document.getElementById('loadingElt');
                }

                if($scope.loadingElt)
                {
                    $scope.loadingElt.style.display = isShown? 'block' : 'none';
                }

                if(arrowBackElt && arrowBackElt.classList.contains("clickedBackArrow") )
                {
                    arrowBackElt.classList.remove("clickedBackArrow");
                }
            }


            //####### ROOTSCOPE GLOBAL INTERVALL ////////////////////////////////////

            $rootScope.activeRequestInterval;

            $rootScope.startGlobalInterval = function (){

                workerTimer.clearAllIntervals();

                $rootScope.activeRequestInterval = workerTimer.setInterval(
                    function(){

                        //NotificationService.checkSocketConnection();

                        //$rootScope.$broadcast('checkActivitySocketConnection', []);

                        //if($state.current.name == "app.chat")
                        //{
                        //    $rootScope.$broadcast('checkChatSocketConnection', []);
                        //}else if($state.current.name == "app.wall")
                        //{
                        //    $rootScope.$broadcast('checkWallSocketConnection', []);
                        //}

                        $scope.hotelNotification.pingRequest();
                        $scope.mainState.globalIntervalCheck = (new Date).getTime();

                        //$rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key: $scope.hotelState.profileData.id});

                        $scope.markMainMessagesRead();
                    },
                    $scope.hotelNotification.PING_TIMEOUT);

            };

            $rootScope.stopGlobalInterval = function(){

                workerTimer.clearInterval($rootScope.activeRequestInterval)
            };


            //###### GLOBSL config ##################################################

            $scope.app = {
                name: 'HoteliCo',
                version: '2.0.1',
                // for chart colors
                color: {
                    primary: '#7266ba',
                    orange: '#f1592a',
                    hotelico: '#7266ba',
                    info:    '#23b7e5',
                    success: '#27c24c',
                    warning: '#fad733',
                    danger:  '#f05050',
                    light:   '#e8eff0',
                    dark:    '#3a3f51',
                    black:   '#1c2b36'
                },
                settings: { //saved by client localStore
                    themeID: 1,
                    //navbarHeaderColor: 'bg-black',
                    navbarCollapseColor: 'bg-white-only',
                    asideColor: 'bg-black',
                    headerFixed: true,
                    asideFixed: false,
                    asideFolded: false,
                    asideDock: false,
                    container: false,
                    blockGuestInWall: false
                },
                rootSettings: {
                    navbarHeaderColor: 'bg-orange',
                    darkBg : false
                    //prefferedLanguage: $translate.use()? $translate.use() : "en"
                },
                header : {
                    showTitle : 'HoteliCo',
                    //showTitle : 'Virtual Internet Plaza',
                    //defaultTitle : 'HoteliCo',
                    showTitleStaff: false,
                    titleAvatarSrc : false,
                    titleUrl : '#/app/hotel',
                    showBackArrow : false,
                    showTab : true,
                    tabIndex : -1,
                    chatPartnerId:undefined
                }
            };


            $rootScope.header = $scope.app.header;
            //$rootScope.rootErrorMsg = $scope.mainState.errorMsg;

            //TODO eugen: localStorage
            // save settings to local storage
            if ( angular.isDefined($localStorage.settings) ) {
                $scope.app.settings = $localStorage.settings;
            }
            else
            {
                $localStorage.settings = $scope.app.settings;
            }


            $scope.$watch('app.settings', function(){
                if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
                    // aside dock and fixed must set the header fixed.
                    $scope.app.settings.headerFixed = true;
                }
                // save to local storage
                $localStorage.settings = $scope.app.settings;
            }, true);


            // angular translate
            $scope.lang = { isopen: false };

            $scope.selectedLanguageValue = $scope.hotelService.getDefaultSystemLanguageLabel();

            $scope.setNewLanguage = function(langFullKey, $event) {

                var langViewKey = $scope.hotelService.getDefaultAllowedLanguageViewKey(langFullKey);

                // set the current lang
                $scope.selectedLanguageValue = $scope.hotelService.systemLanguagesArray[langViewKey];

                // You can change the language during runtime
                //$translate.use($scope.hotelService.convertToTranslateKey(langViewKey));
                $translate.use(langViewKey);

                $scope.lang.isopen = !$scope.lang.isopen;

                //store language in localStorage
                //var curKeySplit = langViewKey.split(_);
                //var curLang = curKeySplit[curKeySplit.length-1];
                //var curLang = curKeySplit[curKeySplit.length-1];

                //$scope.app.rootSettings.prefferedLanguage = curLang;
                $localStorage.settings = $scope.app.settings;

                $scope.hotelState.setPrefferedLanguage(langViewKey);

            };


            //$rootScope.getMobileHeight = function(){
            //  return $(window).innerHeight();
            //};


            //$rootScope.showBackArrow = false;

            //###############################

            NotificationService.receiveNotification().then(null, null, function(notification) {

                if(  $scope.hotelState.getRequesterId() &&  $scope.hotelState.getRequesterId()>0
                    &&
                    notification.receiverId== $scope.hotelState.getRequesterId()
                )
                {
                    //_self.currentNotification = notification;
                    $scope.hotelNotification.handleNotification(notification);
                }
            });

            //////// GLOBAL SERVICES ######################################################

            var HotelCitiesService = $resource('./hotels/customer/:customerId/hotelCities', {customerId:'@customerId', hotelId:'@hotelId' }, {'getCities': {method: 'GET', isArray: true}});

            var citiesAreLoadingNow = false;



            $scope.getUpdateMainHotelCitiesSelectorList = function(){

                var hotelCitiesSelectorList = $q.defer();

                //if(citiesAreLoadingNow)
                //{
                //    return $scope.getUpdateMainHotelCitiesSelectorList();
                //}

                if(!$scope.mainState.hotelCitiesArray || $scope.mainState.hotelCitiesArray.length==0)
                {
                    var requesterId = $scope.hotelState.getRequesterId();

                    citiesAreLoadingNow = true;

                    HotelCitiesService.getCities({customerId:requesterId}, function(cityArray) {

                        var allCitiesItem = new CustomerDto();
                        allCitiesItem.name = $filter('translate')('alert.info.allCities');
                        allCitiesItem.city = allCitiesItem.name;

                        // remove empty city entries
                        for (var c=0; c<cityArray.length; c++)
                        {
                            if(!cityArray[c] || !cityArray[c].city)
                            {
                                cityArray.splice(c,1);
                            }
                        }
                        
                        $scope.mainState.hotelCitiesArray = [].concat(allCitiesItem).concat(cityArray);

                        hotelCitiesSelectorList.resolve($scope.mainState.hotelCitiesArray);

                        //filterStateParam();
                        citiesAreLoadingNow = false;
                    }, function(error){
                        hotelCitiesSelectorList.reject(error);
                        citiesAreLoadingNow = false;
                    });
                }
                else{
                    hotelCitiesSelectorList.resolve($scope.mainState.hotelCitiesArray);

                    //filterStateParam();
                }

                return hotelCitiesSelectorList.promise;
            };

            var HotelByCityService = $resource('./hotels/customer/:customerId/hotel/city/:city', {customerId:'@customerId', city:'@city' }, {'getHotelsByCity': {method: 'GET', isArray: true}});

            $scope.getMainHotelsByCity = function(filterCity, allHotelsEntry)
            {
                //emptyPreviousSearch();

                var hotelsFilterdByCityDeffered = $q.defer();

                var requesterId = $scope.hotelState.getRequesterId();

                $scope.mainState.dummyAllHotelsObj.name = allHotelsEntry && filterCity!='-1' ? "All Hotels" + (filterCity!=$filter('translate')('alert.info.allCities')? " in " + filterCity : "") : null;

                if(filterCity==$filter('translate')('alert.info.allCities'))
                {
                    filterCity = "-1";
                }

                HotelByCityService.getHotelsByCity({customerId: requesterId, city: filterCity}, function(hotelsArray) {

                    for(var h=0; h<hotelsArray.length; h++)
                    {
                        hotelsArray[h] = $scope.hotelService.decodeHotel(hotelsArray[h]);
                    }

                    var resultArray = $scope.mainState.dummyAllHotelsObj.name ? [].concat($scope.mainState.dummyAllHotelsObj).concat(hotelsArray) : hotelsArray;

                    hotelsFilterdByCityDeffered.resolve(resultArray);

                });

                return hotelsFilterdByCityDeffered.promise;
            };


            /**
             * Load hotels based on the available hotel identification indicators
             */
            $scope.getUpdateMainHotelList = function() {

                var hotelArrayDeffered = $q.defer()

                if(!$scope.mainState.hotelArray || $scope.mainState.hotelArray.length == 0){

                    var requesterId = $scope.hotelState.getRequesterId();

                    HotelDto.getList({customerId: requesterId}, function (responseList) {

                        $scope.mainState.hotelArray = [];

                        for (var h = 0; h < responseList.length; h++) {

                            $scope.mainState.hotelArray.push($scope.hotelService.decodeHotel(responseList[h]));
                        }

                        hotelArrayDeffered.resolve($scope.mainState.hotelArray);
                    });

                }
                else {
                    hotelArrayDeffered.resolve($scope.mainState.hotelArray);
                }

                return hotelArrayDeffered.promise;

            };

            $scope.getUpdateMainHotelObject = function(requestedHotelId)
            {
                var defferredHotel = $q.defer();

                if($scope.mainState.hotelArray && $scope.mainState.hotelArray.length>0)
                {
                    for(var h=0; h < $scope.mainState.hotelArray.length; h++ )
                    {
                        var nextHotel = $scope.mainState.hotelArray[h];
                        if(nextHotel.id == requestedHotelId)
                        {
                            defferredHotel.resolve(nextHotel);
                            return defferredHotel.promise;
                        }
                    }
                }

                var requesterId = $scope.hotelState.getRequesterId();
                HotelDto.get({customerId: requesterId, hotelId: requestedHotelId}, function (hotelObj) {
                    defferredHotel.resolve($scope.hotelService.decodeHotel(hotelObj));
                }, function(error){
                    defferredHotel.reject(error);
                });

                return defferredHotel.promise;
            };

            /////////################################### state machine ############################################

            $scope.relocateAdmin = function(entityPath, entityId) {

                if(!$scope.hotelState.profileData || !$scope.hotelState.profileData.logged || !$scope.hotelState.profileData.admin || !entityPath || !entityId)
                {
                    return;
                }

                if($location.path() == entityPath)
                {
                    return;
                }

                return $rootScope.clickLoading(null, null, "#" + entityPath + "/" + entityId);
            }

            $rootScope.updateHeaderTitle = function(nowState, newHotelName)
            {
                if(!nowState)
                {
                    nowState = $state.current.name;
                }

                //no avatar in header per default!
                $scope.app.header.titleAvatarSrc = false;
                $scope.app.header.showTitleStaff = false;

                //reset last chat Partner name!
                $scope.app.header.showTitle = "";

                if(nowState=="app.chat" && $rootScope.chatPartner)
                {
                    var chatPartnerId = $stateParams.receiverId;

                    $rootScope.chatPartner.$promise.then(function(data) {
                        //$scope.app.titleAvatarSrc = $rootScope.getProfileImageUrl(data.id);
                        $scope.app.header.titleAvatarSrc = data.avatarUrl;
                        $scope.app.header.showTitle = data.firstName + " " + (data.lastName!=null? data.lastName : "");// + (data.hotelStaff? " <span class=\"bg-orange-border\">Hotel Staff</span>": "");
                        $scope.app.header.showTitleStaff = data.hotelStaff;
                        $scope.app.header.titleUrl = "#/app/user/"+chatPartnerId;
                        $scope.app.header.chatPartnerId = chatPartnerId;

                        if($rootScope.isSmartDevice)
                        {
                            $scope.app.rootSettings.navbarHeaderColor = "bg-white";
                        }
                    });

                }
                //TODO Eugen: if already checkedIn, why show it????
                else if(nowState=="app.checkin" && $scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.checkedIn)
                {

                    $scope.app.header.showTitle = "CHECK-IN";
                    $scope.app.header.titleUrl = "#/app/checkin";
                    //$scope.app.titleAvatarSrc = false;

                }
                else if(nowState=="app.hotelLogin")
                {

                    $scope.app.header.showTitle = "Hotel Login";
                    $scope.app.header.titleUrl = "#/app/hotelLogin";
                    //$scope.app.titleAvatarSrc = false;

                }
                else if($scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.hotelName || newHotelName)
                {
                    var localShowTitle = newHotelName? newHotelName : $scope.hotelState.profileData.hotelName;

                    $scope.app.header.showTitle = localShowTitle;
                    $scope.app.header.titleUrl = $scope.hotelState.profileData.logged? "#/app/hotel" : "#/app/hotelPreview" ;
                    $scope.app.header.titleAvatarSrc = false;

                }
                else if($scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.checkedIn && ($scope.app.header.tabIndex==0 || $scope.app.header.tabIndex==2))
                {

                    $scope.app.header.showTitle = $scope.mainState.hotelArray && $stateParams.hotelId && $scope.mainState.hotelArray[$stateParams.hotelId]? $scope.mainState.hotelArray[$stateParams.hotelId].name : "Hotelico";
                    $scope.app.header.titleUrl = $scope.hotelState.profileData.logged? "#/app/hotel" : "#/app/hotelPreview" ;
                    //$scope.app.titleAvatarSrc = false;

                }
                else {
                    $scope.app.header.showTitle = "Hotelico";
                    $scope.app.header.titleUrl = $scope.hotelState.profileData.logged? "#/app/hotel" : "#/app/hotelPreview" ;
                    //$scope.app.titleAvatarSrc = false;

                }
            }

            $rootScope.isJson = function(str) {
                try {
                    JSON.parse(str);
                } catch (e) {
                    return false;
                }
                return true;
            }
            
            $rootScope.showErrorObj = function(errorObj, isErrorMsg)
            {
                if(isErrorMsg || (typeof errorObj === 'string') )
                {
                    $rootScope.rootErrorMsg = errorObj;
                }
                else if(errorObj.data && errorObj.data.error)
                {
                    $rootScope.rootErrorMsg = errorObj.data.error;
                }
                else
                {
                    $rootScope.rootErrorMsg = $filter('translate')('system.noConnection');
                }
                
                $rootScope.showLoading(false);
            }

            $scope.closeMyAlert = function (msg) {
                if (msg == "successMsg") {
                    $scope.mainState.successMsg = false;
                }
                else if (msg == "errorMsg") {
                    $scope.mainState.errorMsg = false;
                }
                else if (msg == "rootErrorMsg") {
                    $rootScope.rootErrorMsg = false;
                }
            }

            //$rootScope.setSelectorIndexValue = function(selectorId, selectIndexValue){
            //  
            //  if(!selectorId || !selectIndexValue)
            //  {
            //    return;
            //  }
            //  
            //  var selectorElt = document.getElementById(selectorId);
            //  
            //  if(selectorElt && selectIndexValue < selectorElt.options.length)
            //  {
            //    selectorElt.selectedIndex = selectIndexValue;
            //  }
            //  
            //}
            
            
            
            $scope.getProfileImageUrl = function(userId, isAvatar, isOrange)
            {

                var avatar_m = isOrange? "angulr/img/build/avatar/incognito-orange-m.png" : "angulr/img/build/avatar/incognito-m.png";
                var avatar_f = isOrange? "angulr/img/build/avatar/incognito-orange-f.png" : "angulr/img/build/avatar/incognito-f.png";

                if(userId && $scope.hotelState.allCustomersById[userId] && $scope.hotelState.allCustomersById[userId].hotelStaff)
                {
                    return "angulr/img/build/avatar/staff.png";
                }

                if(!isAvatar && (!userId || userId==$scope.hotelState.profileData.id))
                {
                    if($scope.hotelState.profileData && $scope.hotelState.profileData.avatarUrl && $scope.hotelState.profileData.showAvatar && $scope.hotelState.profileData.avatarUrl.length>0)
                    {
                        return $scope.hotelState.profileData.avatarUrl;
                    }
                }

                if(!isAvatar && userId && $scope.hotelState.allCustomersById && $scope.hotelState.allCustomersById[userId] && $scope.hotelState.allCustomersById[userId].avatarUrl.length>0 && $scope.hotelState.allCustomersById[userId].showAvatar)
                {
                    return $scope.hotelState.allCustomersById[userId].avatarUrl;
                }

                if(userId && $scope.hotelState.allCustomersById[userId] && $scope.hotelState.allCustomersById[userId].sex && $scope.hotelState.allCustomersById[userId].sex=="w")
                {
                    return avatar_f;
                }

                if(userId == undefined)//current user
                {
                    return  $scope.hotelState.profileData.sex &&  $scope.hotelState.profileData.sex=="w" ? avatar_f : avatar_m;
                }

                return avatar_m;
            }

            //////////////////// PING

            function showStaffElement(elt)
            {
                if(elt && elt.classList.contains("hidden"))
                {
                    elt.classList.remove("hidden");
                }
            };

            $scope.removeNotStaffElts = function()
            {

                if(!$scope.hotelState || $scope.hotelState.profileData || $scope.hotelState.profileData.id<=0 || !$scope.hotelState.profileData.firstName)
                {
                    return;
                }

                if(!$scope.hotelState.profileData.hotelStaff && !$scope.hotelState.profileData.admin)
                {
                    return;
                }

                var staffContents = document.getElementsByClassName("staffContent");

                for(var s=0; s<staffContents.length; s++)
                {
                    showStaffElement(staffContents[s]);
                }
            };

            var initFilter = {

                hotelStaffCustomerFilter : {hotelStaff: true},
                hotelCustomerFilter : {checkedIn: true, hotelStaff: false},
                //every not empty value
                chatCustomerFilter : {chatWithMe: true},

                //has 
                outerChatCustomerFilter : {chatWithMe: false}

            };

            $scope.getInitHotelStaffCustomerFilter = function()
            {
                var filter =  angular.copy(initFilter.hotelStaffCustomerFilter);
                filter.id= '!' + $scope.hotelState.profileData.id;
                return filter;
            };

            $scope.getInitHotelCustomerFilter = function()
            {
                var filter =  angular.copy(initFilter.hotelCustomerFilter);
                filter.id= '!' + $scope.hotelState.profileData.id;
                return filter;
            };

            $scope.getInitChatCustomerFilter = function()
            {
                var filter = angular.copy(initFilter.chatCustomerFilter);
                filter.id= '!' + $scope.hotelState.profileData.id;
                return filter;
            };

            $scope.getInitOuterChatCustomerFilter = function()
            {
                var filter =  angular.copy(initFilter.outerChatCustomerFilter);
                filter.id= '!' + $scope.hotelState.profileData.id;
                return filter;
            };


            //
            //var serverRequestInterval;
            //function startServerCommunication() {
            //  //init();
            //  serverRequestInterval = workerTimer.setInterval($rootScope.initPingService, 8000);
            //};
            //function stopServerCommunication() {
            //  serverRequestInterval = workerTimer.clearInterval(serverRequestInterval);
            //};

            $scope.scrollDownById = function(elemId)
            {
                var elem = document.getElementById(elemId);
                if(elem)
                {
                    setTimeout(function () {elem.scrollTop = elem.scrollHeight;}, 600);
                }
            }

            ///################### WATCHERS ######################################################   

            $rootScope.$on('$stateChangeStart', function(next, current) {
                $rootScope.showLoading(true);
            });

            $rootScope.$on('$stateChangeSuccess', function(ev, to, toParams, from, fromParams) {

                //if($rootScope.relocateLogin())
                //{
                //    $scope.loginService.resetRequestedLoginState();
                //    $rootScope.showLoading(false);
                //    return;
                //}

                $rootScope.showLoading(false);

                //TODO Eugen: startState checkin????
                if(to && to.name != "access.login")
                {
                    //$scope.hotelState.setPreviousRouteState(from, fromParams);
                    $scope.hotelState.setPreviousRouteState(from, fromParams);

                    //$scope.hotelState.updateState();
                    //$scope.hotelState.checkNextRouteState(to.name);
                    $rootScope.updateHeaderTitle(to.name);
                    $rootScope.setNavbarHeaderColor($rootScope.isSmartDevice ? "bg-orange" : "bg-black");
                }

                $scope.mainState.successMsg = false;
                $scope.mainState.errorMsg = false;
                $rootScope.rootErrorMsg = false;
                $rootScope.closeStartWerbung();

                if(to.name=="app.wall" && !$scope.hotelState.profileData.logged)
                {
                    return;
                }

                var stateString = to.name;

                if(stateString=="app.chat")
                {
                    stateString +="#"+ toParams.receiverId;
                }

                $rootScope.sendServiceWorkerPostMessage({ command: 'updateState', key: stateString});

                $rootScope.closeModal();
            });

            //$scope.$watch(function() {
            //  return $rootScope.header.tabIndex;
            //}, function() {
            //  $scope.userPLevel = $rootScope.uPLevel;
            //}, true);

            ////TODO Eugen: remove!
            //$scope.clickTab = function(index){
            //  $scope.app.header.tabIndex = index;
            //}

            ////TODO Eugen: remove!
            $scope.$watch('app.header.tabIndex', function(newTabIndex, oldTabIndex) {

                if(newTabIndex!=oldTabIndex && newTabIndex>=0 && oldTabIndex>=0 && $scope.hotelState && $scope.hotelState.selectHeaderTab)
                {
                    $scope.hotelState.selectHeaderTab(newTabIndex, oldTabIndex);
                }

                return;
            });




            //Init automaticly
            //$rootScope.initPingService(true);

            $rootScope.myWait = function(sec)
            {
                var currentTime = new Date().getTime();

                while (currentTime + sec >= new Date().getTime()) {
                }
            }

            $rootScope.clickLoading = function(newState, eltId, href, stateParams) {

                //$rootScope.showLoading(true);

                var curState = $state.current.name;

                if(newState && curState==newState && (!stateParams || $stateParams == stateParams))
                {
                    //$rootScope.showLoading(false);            
                    return;
                }

                $rootScope.closeModal();

                //if(!newState && href && (href+"").indexOf('#/app/')>=0)
                //{
                //    var t_newState = href.replace('#/app/','app.');
                //
                //    newState = t_newState.split("/")[0];
                //}

                if(newState && !$scope.hotelState.profileData.checkedIn && $scope.hotelState.requireCheckinStates.indexOf(newState)>=0)
                {
                    $scope.openNeedCheckinPopup();
                    $scope.hotelState.checkHeaderTab();
                    return;
                }

                if(newState && !$scope.hotelState.profileData.logged && $scope.hotelState.requireLoginStates.indexOf(newState)>=0)
                {
                    if(!stateParams)
                    {
                        stateParams = {};
                    }
                    //TODO Eugen: This state can be reached only with valid login! Otherwise open a login popup!!!
                    //save requested state in loginService
                    $scope.loginService.setLoginRequestedState(newState, stateParams);
                    $scope.openModal("login");
                    return;
                }

                //Eugen: we have the same url prefix now, no need for page reload => exit!
                if(href && (window.location.href+"").indexOf("/" + $rootScope.HOST_SUFFIX + href)>=0)
                {
                    //$rootScope.showLoading(false);
                    return;
                }

                if(eltId)
                {
                    var elt = document.getElementById(eltId);

                    if(elt)
                    {
                        var newStateAttr = elt.getAttribute("ui-sref")

                        if(newStateAttr && curState==newStateAttr)
                        {
                            //$rootScope.showLoading(false);
                            return
                        }

                        elt.classList.add("clickedItem");
                    }
                }

                if(href)
                {
                    //TODO Eugen!!!
                    //var hasSuffix = (href+"").indexOf("/" + $rootScope.HOST_SUFFIX)>=0;
                    window.location.href =  "/" + $rootScope.HOST_SUFFIX + href;
                    return false;
                }

                if(newState)
                {
                    if(!stateParams)
                    {
                        stateParams = {};
                    }

                    setTimeout(function(){$state.go(newState, stateParams);}, 10);
                    //$state.go(newState);
                }
            };

            $rootScope.setNavbarHeaderColor = function(newColor)
            {
                $scope.app.rootSettings.navbarHeaderColor = newColor;
            }

            var arrowBackElt = null;

            $scope.mainStepBack = function()
            {
                $rootScope.showLoading(true);

                if(!arrowBackElt)
                {
                    arrowBackElt = document.getElementById("hback");
                }
                if(arrowBackElt && !arrowBackElt.classList.contains("clickedBackArrow") )
                {
                    arrowBackElt.classList.add("clickedBackArrow");
                }
                //alert($location.url());
                //colorOnClick('hback');

                $scope.hideAside() ;

                setTimeout($scope.hotelState.goStateBack, 500);

            }

            //$rootScope.$on( "$routeChangeStart", function(event, next, current) {
            //
            //  event.preventDefault();
            //  $rootScope.$evalAsync(function() {
            //    //$location.path("/access/login");
            //    $state.go("app.login");
            //
            //  });
            //
            //  //if ($scope.hotelState.profileData.id == 0) {
            //  //    $rootScope.relocate(event, "/access/login");
            //  //}
            //  //
            //  ////if not checkIn page and sill no current hotel -> go to checkin
            //  //else if( (current+"").indexOf("login")<0 && (next+"").indexOf("checkIn")<0 && $scope.hotelState.currentHotelId==0)
            //  //{
            //  //    $rootScope.relocate(event, "/app/checkIn");
            //  //}
            //  ////if on login page, but user is already registered in hotel
            //  //else if(((current+"").indexOf("login")>0 || (current+"").indexOf("checkin")>0) && $scope.hotelState.currentHotelId>0)
            //  //{
            //  //    $rootScope.relocate(event, "/app/hotel");
            //  //}
            //});


            //$scope.isAccessPage = function()
            //{
            //  var nowState = $state.current.name
            //  return (nowState+"").indexOf("access")>=0;
            //}


            /**
             * start this function only from local initStates : checkin, hotelPreview, hotel!!!
             * */
            $rootScope.relocateLogin = function()
            {

                if($scope.hotelState.profileData.systemMessages && $scope.hotelState.profileData.systemMessages["relocateState"])
                {
                    //on social login -> start relocate!!!
                    var relocateState = $scope.hotelState.profileData.systemMessages["relocateState"];

                    var relocateStateParams = $scope.hotelState.profileData.systemMessages["relocateStateParams"]? $scope.hotelState.profileData.systemMessages["relocateStateParams"] : "";

                    try
                    {
                        relocateStateParams = JSON.parse(relocateStateParams);
                    }
                    catch(e)
                    {
                        relocateStateParams = $scope.hotelState.profileData.systemMessages["relocateStateParams"]? $scope.hotelState.profileData.systemMessages["relocateStateParams"] : "";
                    }


                    $scope.hotelState.profileData.systemMessages = {};

                    //$state.go(relocateState, relocateStateParams);

                    setTimeout(function(){
                        if(relocateState)
                        {
                            $state.go(relocateState, relocateStateParams);
                        }
                        $scope.loginService.resetRequestedLoginState();
                    }, 600);

                    return true;
                }
                else
                if($scope.hotelState.profileData.systemMessages && $scope.hotelState.profileData.systemMessages["requestedState"])
                {
                    //on social login -> start relocate!!!
                    var relocateState = $scope.hotelState.profileData.systemMessages["requestedState"];

                    var relocateStateParams = $scope.hotelState.profileData.systemMessages["requestedStateParams"]? $scope.hotelState.profileData.systemMessages["requestedStateParams"] : "";

                    try
                    {
                        relocateStateParams = JSON.parse(relocateStateParams);
                    }
                    catch(e)
                    {
                        relocateStateParams = $scope.hotelState.profileData.systemMessages["requestedStateParams"]? $scope.hotelState.profileData.systemMessages["requestedStateParams"] : "";
                    }


                    $scope.hotelState.profileData.systemMessages = {};

                    //$state.go(relocateState, relocateStateParams);

                    setTimeout(function(){
                        if(relocateState)
                        {
                            $state.go(relocateState, relocateStateParams);
                        }
                        $scope.loginService.resetRequestedLoginState();
                    }, 600);

                    return true;
                }
                else
                {
                    var requestedState = $scope.loginService.getLoginRequestedState();

                    if(requestedState)
                    {
                        var requestedStateParams = $scope.loginService.getLoginRequestedStateParams();
                        //$scope.loginService.resetRequestedLoginState();

                        $rootScope.showLoading(true);

                        //$state.go(requestedState, requestedStateParams);

                        setTimeout(function(){
                            if(requestedState)
                            {
                                $state.go(requestedState, requestedStateParams);
                            }
                            $scope.loginService.resetRequestedLoginState();
                        }, 600);

                        return true;
                    }
                }

                return false;
            }


            var aside = document.getElementById('app-aside-div');

            $scope.setHeaderColor = function(){

                //if(!aside)/
                //{
                aside = document.getElementById('app-aside-div');
                //}

                if(aside)
                {

                    var openMenu = aside.classList.contains ("off-screen");

                    if(openMenu)
                    {
                        $scope.app.rootSettings.navbarHeaderColorCopy = $scope.app.rootSettings.navbarHeaderColor;
                        $scope.app.rootSettings.navbarHeaderColor = "bg-black";
                    }
                    else if($scope.app.rootSettings.navbarHeaderColorCopy)
                    {
                        //$scope.app.rootSettings.navbarHeaderColor = $scope.app.rootSettings.navbarHeaderColorCopy;
                        $scope.app.rootSettings.navbarHeaderColor = $state.current.name=="app.chat"? "bg-white" : "bg-orange";

                    }
                }
            }

            $rootScope.hideAside = function()
            {
                return $scope.hideAside();
            }

            $scope.hideAside = function()
            {
                if(!aside)
                {
                    aside = document.getElementById('app-aside-div');
                }

                if(aside)
                {
                    aside.className = aside.className.replace('show','').replace('off-screen','');
                }
            };


            //var localWorker = false;  

            var worker = null;

            try{
                worker = new Worker( '/' + $rootScope.HOST_SUFFIX + 'angulr/js/lib/timer-worker.js');
            }
            catch(error)
            {
                worker = new Worker($rootScope.HOST + $rootScope.HOST_SUFFIX + 'angulr/js/lib/timer-worker.js');
            }

            var workerTimer = {
                id: 0,
                callbacks: {},

                setInterval: function(cb, interval, context) {
                    this.id++;
                    var id = this.id;
                    this.callbacks[id] = { fn: cb, context: context };
                    worker.postMessage({ command: 'interval:start', interval: interval, id: id });
                    return id;
                },

                onMessage: function(e) {
                    switch (e.data.message) {
                        case 'interval:tick':
                            var callback = this.callbacks[e.data.id];
                            if (callback && callback.fn) callback.fn.apply(callback.context);
                            break;
                        case 'interval:cleared':
                            delete this.callbacks[e.data.id];
                            break;
                    }
                },

                clearInterval: function(id) {
                    worker.postMessage({ command: 'interval:clear', id: id });
                },

                clearAllIntervals: function() {
                    worker.postMessage({ command: 'interval:clearAll'});
                }
            };

            worker.onmessage = workerTimer.onMessage.bind(workerTimer);

            $rootScope.getTimeWebWorker = function()
            {
                return workerTimer;
            }
            ///////////////////////////////////////////////////////////////

            //  onchange="angular.element(this).scope().newFile_changed(this)"
            $scope.newFile_changed = function(element, entityName, entityId) {

                if(!entityId)
                {
                    entityId = (element.parentNode.id+"").indexOf("img")>=0? element.parentNode.id.replace("img","") : undefined;
                };

                $rootScope.setEntityLogo = function(entity, entityId, logoUrl)
                {
                    var arrayObj;

                    if(entity=="hotel")
                    {
                        arrayObj = $scope.mainState.hotelArray;
                    }
                    else if(entity == "activity")
                    {
                        arrayObj = $scope.hotelState.allActivities;
                    }

                    if(arrayObj)
                    {
                        for(var e=0; e<arrayObj.length; e++)
                        {
                            if(arrayObj[e].id && arrayObj[e].id == entityId || arrayObj[e].dtoType=='activity' && arrayObj[e].initId == entityId)
                            {
                                arrayObj[e].pictureUrl = logoUrl;
                                break;
                            }
                        }
                    }

                }



                $scope.$apply(function(scope) {
                    //$scope.localState.newFile = element.files[0];

                    $scope.mainState.tempUploadFiles[entityName+"_"+entityId] = {
                        file: element.files[0],
                        entityName: entityName
                    }
                    //$scope.mainState.tempUploadFiles = element.files[0];
                    //$scope.mainState.tempUploadEntityName = entityName;

                    //var reader = new FileReader();
                    //reader.onload = function(e) {
                    //};
                    //reader.readAsDataURL(photofile);
                });
            };

            $scope.scrollTo = function(id) {
                $location.hash(id);
                $anchorScroll();
            }

            $scope.decodeActivityList = function(activityList)
            {
                var activityImageCacher = document.getElementById("activityImageCache");

                //TODO Eugen: hier is null??
                if(activityImageCacher)
                {
                    activityImageCacher.innerHTML="";
                }

                for(var a=0; a<activityList.length; a++)
                {
                    activityList[a] = $scope.hotelService.decodeActivity(activityList[a]);

                    if(activityImageCacher)
                    {
                        activityImageCacher.innerHTML += "<img src='" + activityList[a].pictureUrl + "' />"
                    }
                }

                return activityList;
            }

            $scope.decodeDealList = function(dealList)
            {
                var activityImageCacher = document.getElementById("activityImageCache");

                //TODO Eugen: hier is null??
                if(activityImageCacher)
                {
                    activityImageCacher.innerHTML="";
                }

                for(var a=0; a<dealList.length; a++)
                {
                    dealList[a] = $scope.hotelService.decodeDeal(dealList[a]);

                    if(activityImageCacher)
                    {
                        activityImageCacher.innerHTML += "<img src='" + dealList[a].pictureUrl + "' />"
                    }
                }

                return dealList;
            }

            $scope.uploadNewImage = function(entityName, entityId, senderId) {

                var defer = $q.defer();

                if(!senderId)
                {
                    senderId = $scope.hotelState.profileData.id;
                }

                if(!$scope.mainState.tempUploadFiles || !$scope.mainState.tempUploadFiles[entityName + "_" + entityId] )
                {
                    //$scope.mainState.tempUploadEntityName = undefined;
                    //$scope.mainState.tempUploadFiles = undefined;
                    defer.reject();
                    return defer.promise;
                }

                //var blob = dataURItoBlob($scope.myCroppedImage);
                //blob.name = "avatar" + $scope.hotelState.profileData.id;
                var files = [$scope.mainState.tempUploadFiles[entityName + "_" + entityId].file];

                $scope.mainState.uploadFiles = files;
                //$scope.files=[blob];
                angular.forEach(files, function(file) {
                    if (file && !file.$error) {
                        file.upload = Upload.upload({
                            url: '/'+$rootScope.HOST_SUFFIX+'files/upload/customer/'+senderId+'/model/'+entityName+'/'+entityId,
                            file: file
                        });

                        file.upload.then(function (response) {
                            $timeout(function () {
                                file.result = response.data;
                                if(response.status == 200)
                                {
                                    //SessionCustomer.get(function(response) {
                                    //    $scope.hotelState.setProfileData(response);
                                    //    //$state.go("app.me");
                                    //});
                                    defer.resolve(file.result);
                                }
                                else{
                                    defer.reject();
                                }
                            });
                        }, function (response) {

                            defer.reject(response.status);

                            if (response.status > 0)
                                $scope.mainState.errorMsg = "FILE UPLOAD ERROR: " + response.status + ': ' + response.data;
                        });

                        file.upload.progress(function (evt) {
                            file.progress = Math.min(100, parseInt(100.0 *
                                evt.loaded / evt.total));
                        });
                    }
                });

                $scope.mainState.tempUploadFiles[entityName + "_" + entityId] = undefined;

                return defer.promise;
            };


            //######################################

            $scope.markMainMessagesRead = function()
            {
                if(!$scope.mainState.readUncheckedMessages || $scope.mainState.readUncheckedMessages.length==0)
                {
                    return;
                }

                $localStorage.readUncheckedMessages = $scope.mainState.readUncheckedMessages;

                var CheckReadMessageService = $resource('./chat/markReadMessage/receiverId/:customerId/messageIds/:messageIds', {customerId:'@customerId', messageIds:'@messageIds' }, {'confirm': {method: 'GET', isArray: true,
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }}});

                CheckReadMessageService.confirm({customerId: $scope.hotelState.profileData.id, messageIds: $scope.mainState.readUncheckedMessages},

                    function(resOk){
                        $scope.mainState.readUncheckedMessages = [];
                        $localStorage.readUncheckedMessages = $scope.mainState.readUncheckedMessages;
                        //if(delIndex > -1)
                        //{
                        //    $scope.mainState.readUncheckedMessages.splice(delIndex, 1);
                        //}
                    },

                    function(error){
                        ;
                    }
                );
            }

            $scope.onHotelCheckinClick = function (clickHotelId, ev) {

                if (!$scope.checkinService) {
                    $scope.checkinService = HotelCheckin;
                }

                $scope.checkinService.resetWholeInfo();


                if (!clickHotelId) {
                    $scope.mainState.errorMsg = $filter('translate')('alert.checkin.chooseHotel');
                    $rootScope.topPage();
                    $rootScope.showLoading(false);
                    return;
                }

                // ###############

                {
                    var confirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        })
                        .title($filter('translate')('page.checkin.sureCheckinHeader'))
                        .content($filter('translate')('page.checkin.sureCheckin'))
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        .cancel($filter('translate')('system.cancel'))
                        .targetEvent(ev);

                    $mdDialog.show(confirm).then(function() {

                        //######## OK BUTTON ##############

                        if (clickHotelId)
                            $scope.checkinService.hotelId = clickHotelId;

                        $rootScope.showLoading(true);

                        $scope.hotelState.emptyCheckinData();

                        $scope.mainState.disableCheckin = true;

                        $scope.checkinService.doCheckin().then(function () {
                            $scope.mainState.disableCheckin = false;
                            //$scope.localState.showManualCheckin = true;
                            $scope.mainState.showCheckinWellcomePopup = true;
                            $state.go($scope.hotelState.profileData.logged? 'app.hotel' : 'app.hotelPreview');
                        }, function (error) {
                            $scope.mainState.disableCheckin = false;
                            if($scope.localState)
                            {
                                $scope.localState.showManualCheckin = true;
                            }
                            $rootScope.showLoading(false);
                            //setTimeout(showManualSelection, 1000);
                        });

                        // ########## END OK BUTTON ###############


                    }, function() {
                        //$scope.mainState.showCheckinWellcomePopup = false;
                    });
                }




                // #################

            };
            
            //######################################


            // Generate the user private channel
            var channel = generateUserChannel();

            var client;
            var isPushEnabled = false;

            //window.addEventListener('load', function() 

            $scope.mainPushRequest = function(param, ev)
            {

                if(param == "noMore")
                {
                    $scope.hotelState.profileData.hideChromePushPopup = true;
                    $scope.hotelService.setCookie('hideChromePushPopup', true);

                    $scope.hotelState.submitProfileData(true);

                    return;
                }

                //var agreePushAlert = $mdDialog.confirm( {
                //        clickOutsideToClose: true
                //    })
                //    .title("Hotel Chrome-Notifications")
                //    .content($filter('translate')('alert.info.agreePush'))
                //    .ariaLabel('Lucky day')
                //    .ok($filter('translate')('OK'))
                //    //.cancel($filter('translate')('alert.info.demoCheckin'))
                //    .targetEvent(ev);
                

                //$mdDialog.show(agreePushAlert).then(function() {
                //    ;
                //
                //}, function() {
                //
                //});

                if($rootScope.isChromeDevice && !$scope.hotelService.cancelPushRequest  && !$scope.hotelService.chromePushRegistrationId && !$scope.hotelState.profileData.hideChromePushPopup && $scope.hotelService.noPushIdRegistered)
                {
                    
                    $scope.openModal("showPush", null, "sm");

                    // start Chrome Push Manager to obtain device id and register it with Realtime
                    // a service worker will be launched in background to receive the incoming push notifications
                    var chromePushManager = new ChromePushManager('/service-worker.js', function(error, registrationId){

                        //if(agreePushAlert && (registrationId || error))
                        //{
                        //    $mdDialog.hide(agreePushAlert);
                        //}

                        $rootScope.sendServiceWorkerPostMessage({ command: 'updateCustomerId', key: $scope.hotelState.getRequesterId()});

                        var stateString = $state.current.name;

                        if(stateString=="app.chat")
                        {
                            stateString +="#"+ $stateParams.receiverId;
                        }


                        $rootScope.sendServiceWorkerPostMessage({ command: 'updateState', key: stateString});

                        window.addEventListener("beforeunload", function (e) {

                            var stateString = $state.current.name;

                            if(stateString=="app.chat")
                            {
                                stateString +="#"+$stateParams.receiverId;
                            }

                            $rootScope.sendServiceWorkerPostMessage({ command: 'unloadState', key: stateString});
                        });

                        //Eugen: If the promise returned by the subscribe() method resolves, youâ€™ll be given a PushSubscription object which will contain an endpoint.
                        //Eugen: "registrationId" - The endpoint should be saved on your server for each user, since youâ€™ll need them to send push messages at a later date.
                        //https://developers.google.com/cloud-messaging/http-server-ref#downstream-http-messages-json

                        $scope.hotelService.chromePushRegistrationId = registrationId;
                        $scope.hotelService.noPushIdRegistered = false;
                        $scope.hotelService.setCookie('chromePushRegistrationId', registrationId);

                        $rootScope.closeModal();
                        
                        if($scope.hotelState.checkedIn && !$scope.hotelState.logged)
                        {
                            //TODO add guestId to Hotel!
                        }

                        if(error && error.message)
                        {
                            //alert("Factory ok: " + error.message);
                        }

                        $scope.hotelState.profileData.systemMessages["chromePushRegistrationId"]  = registrationId;
                        //reset systemMessages!!
                        $scope.hotelState.submitProfileData(true);

                        if ('serviceWorker' in navigator) {
                            // Set up a listener for messages posted from the service worker.
                            // The service worker is set to post a message to all its clients once it's run its activation
                            // handler and taken control of the page, so you should see this message event fire once.
                            // You can force it to fire again by visiting this page in an Incognito window.
                            navigator.serviceWorker.addEventListener('message', function(event) {
                                $rootScope.listenServiceWorkerPostMessage(event.data);
                            });
                        }
                    }); 
                }

                
            }
                //)
            ;

            // generate a GUID
            function S4() {
                return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
            }

            // generate the user private channel and save it at the local storage
            // so we always use the same channel for each user
            function generateUserChannel(){
                var userChannel = localStorage.getItem("channel");
                if (userChannel == null || userChannel == "null"){
                    var guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
                    userChannel = 'channel-' + guid;
                    localStorage.setItem("channel", userChannel);
                }
                return userChannel;
            }

            $rootScope.sendPush = function()
            {
                if (client) {
                    client.send(channel, "This will trigger a push notification");
                };
            }

            //######################################

            // Service worker communication

            $rootScope.sendServiceWorkerPostMessage = function(commandMessage) {
                // This wraps the message posting/response in a promise, which will resolve if the response doesn't
                // contain an error, and reject with the error if it does. If you'd prefer, it's possible to call
                // controller.postMessage() and set up the onmessage handler independently of a promise, but this is
                // a convenient wrapper.
                return new Promise(function(resolve, reject) {
                    var messageChannel = new MessageChannel();
                    messageChannel.port1.onmessage = function(event) {
                        if (event.data.error) {
                            reject(event.data.error);
                        } else {
                            resolve(event.data);
                        }
                    };

                    // This sends the message data as well as transferring messageChannel.port2 to the service worker.
                    // The service worker can then use the transferred port to reply via postMessage(), which
                    // will in turn trigger the onmessage handler on messageChannel.port1.
                    // See https://html.spec.whatwg.org/multipage/workers.html#dom-worker-postmessage
                    if(navigator.serviceWorker.controller)
                    {
                        navigator.serviceWorker.controller.postMessage(commandMessage,
                            [messageChannel.port2]);
                    }
                });
            }

            $rootScope.sendServiceWorkerPostMessage({ command: 'updateHostSuffix', key: $rootScope.HOST_SUFFIX});

            $rootScope.listenServiceWorkerPostMessage = function(data){
                //TODO Eugen: event from service worker;
            }
            //######################################
            $rootScope.unlockModal = function()
            {
                var allFocusable = document.getElementsByClassName("focusModal");

                for(var f=0; f<allFocusable.length; f++)
                {
                    var elem = allFocusable[f];

                    elem.addEventListener('touchstart', function(e) {
                        e.target.focus();
                        //e.preventDefault();
                        e.stopPropagation();
                    });
                }
            }

            $rootScope.closeModal = function(){
                //Eugen: dummy function, that will be rewritten in modal controller!!!
            };

            $scope.openModal = function (templateType, modalEntityId, size) {

                $rootScope.closeModal();

                $rootScope.unlockModal();
                
                $scope.mainState.successMsg = false;

                $rootScope.showLoading(true);

                var loginTemplateUrl = 'angulr/tpl/hotel/blocks/modal.loginform.html';
                var checkinTemplateUrl = 'angulr/tpl/hotel/blocks/modal.hotelCodeForm.html';
                var editActivityTemplateUrl = 'angulr/tpl/hotel/blocks/modal.editActivityForm.html';
                var editDealTemplateUrl = 'angulr/tpl/hotel/blocks/modal.editDealForm.html';
                var editHotelTemplateUrl = 'angulr/tpl/hotel/blocks/modal.editHotelForm.html';
                var editMenuOrderTemplateUrl = 'angulr/tpl/hotel/blocks/modal.editMenuOrderForm.html';
                var hintTemplateUrl = 'angulr/tpl/hotel/blocks/modal.hint.html';
                var feedActivityTemplateUrl = 'angulr/tpl/hotel/blocks/modal.hotelActivityFeedForm.html';
                var showPushTemplateUrl = 'angulr/tpl/hotel/blocks/modal.showPushForm.html';

                var selectTemplateUrl = loginTemplateUrl;

                if(templateType)
                {
                    switch (templateType){

                        case "checkin":
                        {
                            selectTemplateUrl = checkinTemplateUrl;
                            break;
                        }

                        case "editActivity":
                        {
                            selectTemplateUrl = editActivityTemplateUrl;
                            break;
                        }
                        
                        case "inviteActivity":
                        {
                            selectTemplateUrl = feedActivityTemplateUrl;
                            break;
                        }

                        case "editHotel":
                        {
                            selectTemplateUrl = editHotelTemplateUrl;
                            break;
                        } 
                        
                        case "showPush":
                        {
                            selectTemplateUrl = showPushTemplateUrl;
                            
                            //setTimeout(mainPushRequest, 2000);
                            
                            break;
                        }

                        case "editDeal":
                        {
                            selectTemplateUrl = editDealTemplateUrl;
                            break;
                        }

                        case "hint":
                        {
                            selectTemplateUrl = hintTemplateUrl;
                            break;
                        }
                        
                        case "editMenuOrder":
                        {
                            selectTemplateUrl = editMenuOrderTemplateUrl;
                            break;
                        }

                        case "login":
                        default:
                        {
                            selectTemplateUrl = loginTemplateUrl;
                            break;
                        }
                    }
                }

                var modalInstance = $modal.open({
                    //templateUrl: 'myModalContent.html', //TODO Eugen: var! The value of it was saved in templateCache before!!!
                    templateUrl: selectTemplateUrl, //REAL basic MODEL-Instance Controller
                    controller: 'MyModalInstanceCtrl', //REAL basic MODEL-Instance Controller
                    size: size? size : 'lg', //MY Custom size
                    scope: $scope,
                    resolve: {
                        modalEntityType : function () {
                            return  templateType;
                        },
                        modalEntityId: function () {
                            return  modalEntityId? modalEntityId : -1;
                        },
                        deps: ['$ocLazyLoad',
                            function( $ocLazyLoad){

                                if(templateType=="editActivity")
                                {
                                    return $ocLazyLoad.load('angulr/js/controllers/hotel/datumPicker.js')
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/activityCtrl.js');
                                            }
                                        ).then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/editActivity.js');
                                            }
                                        );
                                }
                                else if(templateType=="editDeal")
                                {
                                    //TODO Eugen: deal

                                    return $ocLazyLoad.load('angulr/js/controllers/hotel/datumPicker.js')
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/activityCtrl.js');
                                            }
                                        ).then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/editDeal.js');
                                            }
                                        );
                                }
                                else if(templateType=="editHotel")
                                {
                                    return $ocLazyLoad.load('angulr/js/controllers/hotel/datumPicker.js')
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/hotelCtrl.js');
                                            }
                                        )
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/editHotel.js');
                                            }
                                        )
                                        ;
                                }
                                else if(templateType=="inviteActivity")
                                {
                                    return $ocLazyLoad.load('angulr/js/controllers/hotel/feed.js')
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/activityCtrl.js');
                                            }
                                        )
                                        .then(
                                            function(){
                                                return $ocLazyLoad.load('angulr/js/controllers/hotel/editHotel.js');
                                            }
                                        )
                                        ;
                                }
                                else if(templateType=="editMenuOrder")
                                {
                                    return $ocLazyLoad.load('angulr/js/controllers/hotel/menuList.js')
                                        //.then(
                                        //    function(){
                                        //        return $ocLazyLoad.load('angulr/js/controllers/hotel/menuList.js');
                                        //    }
                                        //)
                                        //.then(
                                        //    function(){
                                        //        return $ocLazyLoad.load('angulr/js/controllers/hotel/editHotel.js');
                                        //    }
                                        //)
                                        ;
                                }
                                else{
                                    return null;
                                }

                            }]
                    }
                });

                //setTimeout($rootScope.unlockModal, 500);
                //setTimeout($rootScope.unlockModal, 1500);

                modalInstance.result.then(function (newState) {
                    //TODO Eugen: LISTENER ON OK Close actions. WITH CUSTOM READY-PARAM!!!!

                    //if(!stateParams)
                    //{
                    //    stateParams = {};
                    //}

                    if(newState)
                    {
                        $state.go(newState);
                    }

                }, function () {
                    //TODO Eugen CANCEL ERROR Close!!!
                    $log.info('Modal dismissed at: ' + new Date());

                    //Eugen: onModalClse:
                    $scope.mainState.successMsg = false;
                    $scope.mainState.errorMsg = false;
                    $rootScope.rootErrorMsg = false;

                    $scope.hotelState.checkHeaderTab();

                    var curState = $state.current.name;

                    if(curState=="app.wall" && !$scope.hotelState.profileData.logged)
                    {
                        $scope.hotelState.goStateBack();
                    }

                    //Eugen: I cannot update a hotel in editHotel state
                    if(curState=="app.editHotel")
                    {
                        $scope.hotelState.goStateBack();
                    }

                    //Eugen: I cannot update a hotel in editHotel state
                    if(curState=="app.dealList")
                    {
                        $rootScope.$broadcast('dealListUpdate', []);
                    } 
                    
                    if(curState=="app.menuList" && $scope.hotelState.currentMenuOrder && $scope.hotelState.currentMenuOrder.orderStatus == "ACCEPTED")
                    {
                        $rootScope.$broadcast('menuOrderAccepted', []);

                    }

                    if(curState=="app.hotel" || curState=="app.hotelPreview" || curState=="app.activityList")
                    {
                        $rootScope.$broadcast('activityListUpdate', []);

                    }
                });
            };

        }])

    //TODO Eugen: MyModalInstanceCtrl- ROOT CONTROLLER FOR MODAL inhalt!!!!

    //TODO itmes -> custom resolve Parameter ubergeben

    .controller('MyModalInstanceCtrl', ['$scope', '$rootScope', '$modalInstance', 'modalEntityType', 'modalEntityId', function($scope, $rootScope, $modalInstance, modalEntityType, modalEntityId)
    {

        $rootScope.showLoading(false);

        if(modalEntityType)
        {
            switch (modalEntityType){

                case "editActivity":
                {
                    $scope.defaultEditOpen = true;
                    $scope.editActivityId = modalEntityId;
                    break;
                }  
                
                case "inviteActivity":
                {
                    $scope.defaultEditOpen = true;
                    $scope.inviteActivityId = modalEntityId;
                    break;
                }

                case "editDeal":
                {
                    $scope.defaultEditOpen = true;
                    $scope.editDealId = modalEntityId;
                    //$scope.editActivityId = modalEntityId;
                    break;
                }
                
                case "editMenuOrder":
                {
                    $scope.defaultEditOpen = true;
                    $scope.editMenuOrderId = modalEntityId;
                    //$scope.editActivityId = modalEntityId;
                    break;
                }

                case "editHotel":
                {
                    $scope.defaultEditOpen = true;
                    $scope.editHotelId = modalEntityId;
                    break;
                }

                default:
                {
                    break;
                }
            }
        }

        $scope.customParam = null;

        $rootScope.closeModal = function(){
            if($modalInstance.opened)
            {
                $modalInstance.close();
            }
        };

        $scope.ok = function (newState) {
            //TODO Eugen: INIT OK Close actions!!!!
            $modalInstance.close(newState);
        };

        $scope.cancel = function () {

            //TODO Eugen: INIT CANCEL Close actions!!!!

            $modalInstance.dismiss('cancel');
        };

    }])

    //TODO Eugen: this Controller creates MyModalInstanceCtrl

    //TODO Eugen: ALL WE NEED -> OPEN() in ModalPopupCtrl !!!!
    .controller('ModalPopupCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {


    }])

//TODO Eugen: myModalContent.html - variable for current model tpl inhalt!!!!
//TODO Eugen: open('lg') - lg is size of model window!!!!

//<span ng-controller="ModalDemoCtrl">
//<script type="text/ng-template" id="myModalContent.html">
//<div ng-include="'tpl/modal.form.html'"></div>
//</script>
//<button class="btn btn-success" ng-click="open('lg')">Form in a modal</button>
//</span>

; 