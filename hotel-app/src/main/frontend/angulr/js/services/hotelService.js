angular.module("hotelApp.hotelService", ["pascalprecht.translate" ])
    .factory("HotelService", function($translate, $filter, $resource, $q , $rootScope, $window) {

        var _self = this;

        var initCustomer = {
            id: 0,
            firstName: undefined,
            lastName: undefined,
            email: undefined,
            languageArray: [],
            jobTitle: undefined,
            company: undefined,
            //authPassword: false,
            hotelStaff: false,
            logged: false,
            showInGuestList: true,
            checkedIn: false,
            showAvatar: true,
            city: undefined,
            originalCity: undefined,
            avatarUrl: undefined,
            sex: "m",
            dtoType : "customer",
            fullCheckin: false,
            online: false,
            country: undefined,
            password: undefined,
            status: undefined,
            birthdayTime: undefined,
            systemMessages: {},
            hideHotelListPopup: false,
            hideCheckinPopup: false,
            hotelId: 0
            //confirmPassword: undefined
            //authLinkedIn: false,
            //linkedIn: {
            //	memberId: undefined
            //}
        };

        var initActivity = {
            description: undefined,
            shortDescription: undefined,
            title: undefined,
            active: true,
            dtoType : "activity",
            activeBoolean: true,
            dealAllowed: true,
            timeValid: true,
            pictureUrl: undefined
        };

        var initMenuItem = {
            orderId: 0,
            orderIndex: 0,
            hotelId: 0,
            senderId: 0,
            active: true,
            price: 0,
            amount: 1,
            title: undefined,
            shortDescription: undefined,
            description: undefined,
            dtoType : "menuItem",
            pictureUrl: undefined
        };
        
        var initMenuOrder = {
            menuItems : [],
            itemAmount : 0,
            orderInRoom : true,
            totalPrice : 0
        };

        var initialHotel = {
            id : -1,
            name : "New Hotel",
            dtoType : "hotel",
            currentHotelAccessCode : undefined,
            description : undefined,
            info : undefined,
            postalCode : undefined,
            city : undefined,
            house : undefined,
            street : undefined,
            email : undefined,
            fax : undefined,
            phone : undefined,
            pictureUrl : undefined
        };


        var menuOrderService = $resource('./menu/order/customer/:customerId/hotelId/:hotelId/cafeId/:cafeId/orderId/:orderId/showClosed/:showClosed',
            {customerId:'@customerId', hotelId:'@hotelId', cafeId:'@cafeId', orderId:'@orderId', showClosed:'@showClosed'},
            {
                'getMenu': {method: 'GET', isArray: true},
                'addUpdate': {method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }, isArray: false},
                'delete': {method: 'DELETE', isArray: false}
            }
        );  
        
        var hotelGuestActionService = $resource('./hotels/action/:action/hotelId/:hotelId/customer/:guestCustomerId/',
            {action:'@action', hotelId:'@hotelId', guestCustomerId:'@guestCustomerId'},
            {
                //'getMenu': {method: 'GET', isArray: true},
                'addAction': {method: 'POST',
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }, isArray: false}
                //,
                //'delete': {method: 'DELETE', isArray: false}
            }
        );

        var hotelDealActionService = $resource('./deals/deals/customer/:customerId/activity/:activityId/hotel/:hotelId/limitByRequester/:limitByRequester/showClosed/:showClosed', {customerId:'@customerId', activityId:'@activityId', hotelId:'@hotelId', limitByRequester:'@limitByRequester', showClosed:'@showClosed'}, {'getDeals': {method: 'GET', isArray: true}});


        var service = {

            initService: function(){
                _self = this;
                
                _self.detectServerConstants();
                _self.detectClientDevice($window);
                _self.getGenerateguestCustomerId();
                //_self.getChromePushRegistrationId();
            },

            getInitCustomer: function () {
                var initCustomerInstance = angular.copy(initCustomer);

                return initCustomerInstance;
            },

            getInitHotel: function () {
                var newHotel = angular.copy(initialHotel);
                newHotel.creationTime = (new Date()).getTime();
                return newHotel;
            },
            
            getInitMenuOrder: function () {
                var newMenuOrder = angular.copy(initMenuOrder);
                return newMenuOrder;
            },

            getInitMenuItem: function () {
                var newMenuItem = angular.copy(initMenuItem);
                newMenuItem.initId = Math.floor(Math.random() * 1000000);
                //newMenuItem.sender = _self.getRequesterId();
                return newMenuItem;
            },

            getMenuOrderService: function()
            {
              return menuOrderService;
            }, 
            
            
            getHotelGuestActionService: function()
            {
              return hotelGuestActionService;
            }, 
            
            getHotelDealActionService: function()
            {
              return hotelDealActionService;
            },
            
            //getDemoHotel: function (){
            //    
            //    var defer = $q.defer();
            //    
            //    var demoHotelIdElt = document.getElementById("demoHotelId");
            //
            //    if(demoHotelIdElt && demoHotelIdElt.innerText)
            //    {
            //        var demoHotelId = demoHotelIdElt.innerText;
            //
            //        $scope.getUpdateMainHotelObject(demoHotelId).then(function(demoHotel)
            //        {
            //            defer.resolve(demoHotel);
            //        }, function(err)
            //        {
            //            defer.reject();
            //        });
            //    }
            //    else {
            //        defer.reject();
            //    }
            //    
            //    return defer.promise;
            //},

            getHostSuffix: function(){

                var hostSuffixElt = document.getElementById("hostSuffixId");

                if(hostSuffixElt && hostSuffixElt.innerText)
                {
                    return hostSuffixElt.innerText;
                }

                return "";
            },

            getInitActivity: function () {

                var newActivity = angular.copy(initActivity);
                newActivity.initId = (new Date()).getTime();
                return newActivity;
            },

            encodeCustomer : function(customerObj){

                ///################# Languages ##############################

                var encodedCustomer = angular.copy(customerObj);

                if(encodedCustomer.languages && encodedCustomer.languages.length>0 && encodedCustomer.languages[0].name)
                {
                    var languageKeys = [];

                    for (var l = 0; l < encodedCustomer.languages.length; l++) {
                        languageKeys.push(encodedCustomer.languages[l].name);
                    }

                    encodedCustomer.languages =  languageKeys;
                }

                if(!encodedCustomer.systemMessages)
                {
                    encodedCustomer.systemMessages = {};
                }

                if(encodedCustomer.systemMessages["feedbackMessage"])
                {
                    encodedCustomer.systemMessages["feedbackMessage"] = encodeURIComponent(encodedCustomer.systemMessages["feedbackMessage"]);
                }

                if(_self.chromePushRegistrationId)
                {
                    encodedCustomer.systemMessages["chromePushRegistrationId"]  = _self.chromePushRegistrationId;
                }

                if(_self.geoLocation && _self.geoLocation.latitude && _self.geoLocation.longitude)
                {
                    encodedCustomer.systemMessages["latitude"]  = _self.geoLocation.latitude;
                    encodedCustomer.systemMessages["longitude"]  = _self.geoLocation.longitude;
                }

                if(_self.tempCreationHotel && _self.tempCreationHotel.creationTime)
                {
                    encodedCustomer.systemMessages["staffHotelCreationTime"]  = _self.tempCreationHotel.creationTime;
                }

                if(/*!customerObj.logged &&*/ _self.guestCustomerId)
                {
                    encodedCustomer.systemMessages["guestCustomerId"]  = _self.guestCustomerId;
                }

                ///#################### END Languages ##########################

                //EUGEN: PROTECT AGAINST SERIALIZATION ERRORS, ALL TEMP FIELDS WEG
                encodedCustomer.languageArray = undefined;
                encodedCustomer.confirmPassword = undefined;
                encodedCustomer.age = undefined;

                return encodedCustomer;
            },

            myDecode: function(encodeObj){

                var decodeObj = null;

                try{
                    decodeObj = decodeURIComponent(encodeObj);
                }
                catch(error){
                    decodeObj = encodeObj;
                };

                return decodeObj;
            },

            decodeChatMessage: function(chatMsg)
            {
                if(!chatMsg)
                {
                    return chatMsg;
                }

                chatMsg.message = _self.myDecode(chatMsg.message);

                if(chatMsg.sendTime && !(chatMsg.sendTime instanceof Date))
                {
                    chatMsg.sendTime = new Date(chatMsg.sendTime);
                }

                if(chatMsg.specialContent && chatMsg.specialContent.name)
                {
                    chatMsg.specialContent.name = _self.myDecode(chatMsg.specialContent.name);
                }  
                
                return chatMsg;
            },
            
            decodeWall: function(wallMsg) {

                if(!wallMsg)
                {
                    return wallMsg;
                }

                wallMsg.message = _self.myDecode(wallMsg.message);

                if(wallMsg.sendTime && !(wallMsg.sendTime instanceof Date))
                {
                    wallMsg.sendTime = new Date(wallMsg.sendTime);
                } 
                
                if(wallMsg.specialContent && wallMsg.specialContent.name)
                {
                    wallMsg.specialContent.name = _self.myDecode(wallMsg.specialContent.name);
                }

                return wallMsg;
            },

            encodeWall: function(wallObj){

                if(!wallObj)
                {
                    return wallObj;
                }

                var encodedWall = angular.copy(wallObj);

                if(encodedWall.message)
                {
                    encodedWall.message = encodeURIComponent(encodedWall.message);
                }

                return encodedWall;
            },

            decodeCustomer : function(customerObj) {

                if(!customerObj)
                {
                    return customerObj;
                    
                }

                if(customerObj.lastMessageToMe)
                {
                    customerObj.lastMessageToMe = _self.myDecode(customerObj.lastMessageToMe);
                }

                if (customerObj.lastMessageTimeToMe && !(customerObj.lastMessageTimeToMe instanceof Date))
                    customerObj.lastMessageTimeToMe = new Date(customerObj.lastMessageTimeToMe);

                return customerObj;
            },

            encodeHotel: function(hotelObj){

                if(!hotelObj)
                {
                    return hotelObj;
                }

                var encodedHotel = angular.copy(hotelObj);

                if (encodedHotel.description) {
                    encodedHotel.description = encodeURIComponent(encodedHotel.description);
                }
                else{
                    encodedHotel.description = "";
                }

                if (encodedHotel.info) {
                    encodedHotel.info = encodeURIComponent(encodedHotel.info);
                }

                if (encodedHotel.title) {
                    encodedHotel.title = encodeURIComponent(encodedHotel.title);
                }

                return encodedHotel;
            },

            decodeHotel: function (hotelObj) {

                if (!hotelObj) {
                    return hotelObj;
                }

                if (hotelObj.description)
                {
                    hotelObj.description = _self.myDecode(hotelObj.description);
                }

                if (hotelObj.title)
                {
                    hotelObj.title = _self.myDecode(hotelObj.title);
                }

                if (hotelObj.info)
                {
                    hotelObj.info = _self.myDecode(hotelObj.info);
                }

                return hotelObj;
            },

            encodeActivity: function(activityObj) {

                if(!activityObj)
                {
                    return activityObj;
                }

                var encodedActivity = angular.copy(activityObj);

                if(encodedActivity.lastMinute)
                {
                    encodedActivity.validFrom = new Date();
                    encodedActivity.validTo = new Date();
                }

                if(encodedActivity.description == undefined)
                {
                    encodedActivity.description = "";
                }

                if(encodedActivity.activeBoolean)
                {
                    encodedActivity.activeBoolean = undefined;
                }

                if(encodedActivity.shortDescription == undefined)
                {
                    encodedActivity.shortDescription = "";
                }

                encodedActivity.shortDescription = encodeURIComponent(encodedActivity.shortDescription);
                encodedActivity.description = encodeURIComponent(encodedActivity.description);
                encodedActivity.title = encodeURIComponent(encodedActivity.title);

                //Remove not needed elts for serialization
                encodedActivity.validFromString = undefined;
                encodedActivity.validToString = undefined;
                encodedActivity.pictureUrl = undefined;
                //encodedActivity.customerCounter = undefined;
                encodedActivity.active = undefined;
                encodedActivity.timeValid = undefined;
                encodedActivity.hotelName = undefined;

                return encodedActivity;
            },

            decodeDeal : function(dealObj)
            {
                var decodedDeal =  _self.decodeActivity(dealObj);

                return decodedDeal;
            },  
            
            decodeMenuOrder : function(orderObj)
            {
                var decodedOrder =  angular.copy(orderObj);

                return decodedOrder;
            },

            decodeActivity: function(activityObj) {

                if(!activityObj)
                {
                    return activityObj;
                }

                if(activityObj.shortDescription)
                {
                    activityObj.shortDescription = _self.myDecode(activityObj.shortDescription);
                }

                if(activityObj.description)
                {
                    activityObj.description = _self.myDecode(activityObj.description);
                }

                if(activityObj.title)
                {
                    activityObj.title = _self.myDecode(activityObj.title);
                }

                if(activityObj.description == "undefined")
                {
                    activityObj.description = "";
                }

                return activityObj;
            },

            //removeActivity : function(profileData, activityId) {
            //
            //    ActivityRemoveService.remove({customerId: profileData.id, activityId: activityId}, function(response){
            //        response.$promise.then(function(value){
            //            $scope.removeLocalActivity(activityId);
            //        });
            //    });
            //},

            setCookie: function (name, value)//, expires, path, domain, secure) {
            {
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 60);
                var expires = expireDate.toUTCString();
                var path = false;//'/';
                var domain = false;//'hoteliCo';
                var secure = false;

                document.cookie = name + "=" + escape(value) +
                    ((expires) ? "; expires=" + expires : "") +
                    ((path) ? "; path=" + path : "") +
                    ((domain) ? "; domain=" + domain : "") +
                    ((secure) ? "; secure" : "");
            },

            // ?????????? cookie ? ?????? name, ???? ????, ???? ???, ?? undefined
            getCookie: function (name) {
                var matches = document.cookie.match(new RegExp(
                    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
                ));
                return matches ? decodeURIComponent(matches[1]) : undefined;
            },

            delCookie: function (name) {
                document.cookie = name + "=" + "; expires=Thu, 01 Jan 1970 00:00:01 GMT";
            },

            validateCustomerInfo: function(customerObj, checkPasswordMode, guestMode) {

                if (customerObj.email == undefined) {
                    return $filter('translate')('alert.error.emptyMail');
                }
                if ((customerObj.email + "").indexOf("@") < 0) {
                    return $filter('translate')('alert.error.wrongMail');
                }

                if(customerObj.languages=="undefined" && !guestMode)
                {
                    return $filter('translate')('alert.error.noLanguage');
                }

                if (checkPasswordMode && (customerObj.password == undefined || customerObj.password != customerObj.confirmPassword)) {
                    return $filter('translate')('alert.error.passwordNotEqual');
                }
                if (checkPasswordMode && customerObj.password.length < 6) {
                    return $filter('translate')('alert.error.shortPassword');
                }

                return "";
            },

            validateHotelInfo: function(hotelObj){

                if (hotelObj.currentHotelAccessCode == undefined) {
                    return $filter('translate')('alert.error.emptyCode');
                }

                if (hotelObj.name == undefined) {
                    return $filter('translate')('alert.error.emptyName');
                }

                if (hotelObj.email == undefined) {
                    return $filter('translate')('alert.error.emptyMail');
                }

                //if(!hotelObj.description || hotelObj.description.length> _self.validationUtils.maxHotelDescription)
                //{
                //    return $filter('translate')('alert.error.descriptionLong');
                //}
                return "";
            },
            
            validateMenuInfo: function(menuObj){

                if (menuObj.orderInRoom && !menuObj.orderLocation) {
                    return $filter('translate')('alert.error.emptyRoomNumber');
                } 
                
                if (!menuObj.firstName) {
                    return $filter('translate')('alert.error.emptyFirstName');
                }
                
                if (!menuObj.lastName) {
                    return $filter('translate')('alert.error.emptyLastName');
                }
                
                if (menuObj.itemAmount==0) {
                    return $filter('translate')('alert.error.itemAmount');
                }
                
                if(menuObj.menuItems.length==0)
                {
                    return $filter('translate')('alert.error.noMenuItemsSelected');
                }
                
                return "";
            },

            getEntityLogo: function(requesterId, entityType, entityId)
            {
                var getEntityLogoService = $resource("./files/logo/requester/:requesterId/entityType/:entityType/entityId/:entityId", {
                        requesterId: '@requesterId',
                        entityType: '@entityType',
                        entityId: '@entityId'
                    },
                    {
                        getImage: {
                            method: 'get',
                            headers: {
                                'Content-Type': 'application/json;charset=UTF-8',
                                'Accept': 'application/json, text/plain, */*'
                            }
                        }
                    }
                );

                var imageDefer = $q.defer();

                var imageResponseCustomerObj = getEntityLogoService.getImage({requesterId: requesterId, entityType: entityType, entityId: entityId}, function(){

                        //If pictureUrl was set
                        if(imageResponseCustomerObj.systemMessages.pictureUrl)
                        {
                            imageDefer.resolve(imageResponseCustomerObj.systemMessages)
                        }
                        else{
                            imageDefer.reject();
                        }
                    },
                    function(error)
                    {
                        imageDefer.reject(error);

                    }
                );

                return imageDefer.promise;

            },

            getGenerateguestCustomerId : function()
            {
                var clientguestCustomerId = _self.getCookie('guestCustomerId');

                //if no value in cookies
                if(!clientguestCustomerId)
                {
                    clientguestCustomerId = Math.floor(Math.random() * 1000000);
                    _self.setCookie('guestCustomerId', clientguestCustomerId);
                }

                _self.guestCustomerId = clientguestCustomerId;
            },

            detectServerConstants: function()
            {
                $rootScope.HOST = 'http://hotelico.de/';

                var hostIdElt = document.getElementById("hostId");

                if(hostIdElt && (hostIdElt.innerText || hostIdElt.innerHTML))
                {
                    $rootScope.HOST = hostIdElt.innerText? hostIdElt.innerText : hostIdElt.innerHTML;
                }

                if(!$rootScope.HOST_SUFFIX)
                {
                    $rootScope.HOST_SUFFIX = _self.getHostSuffix();
                }
            },

            //ask checkedIn chrome user to accept push    
            cancelPushRequest : false,

            doCancelPushRequest: function()
            {
                _self.cancelPushRequest = true;

                _self.setCookie('cancelPushRequest', "true");
            },
            
            detectClientDevice: function( $window )
            {
                // Adapted from http://www.detectmobilebrowsers.com
                var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'];// || $window['opera'];
                // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices

                $rootScope.isSmartDevice = (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);

                $rootScope.isChromeDevice = (/Android|Chrome/).test(ua);

                // add 'ie' classes to html
                var isIE = !!navigator.userAgent.match(/MSIE/i);
                isIE && angular.element($window.document.body).addClass('ie');
                $rootScope.isSmartDevice && angular.element($window.document.body).addClass('smart');
                
                if($rootScope.isChromeDevice)
                {
                    var cancelPushRequest_temp = _self.getCookie('cancelPushRequest');

                    _self.cancelPushRequest = cancelPushRequest_temp && cancelPushRequest_temp=="true";
                    
                    if(!_self.cancelPushRequest)
                    {
                        _self.detectNoPushIdRegistered();
                    }
                }

                $rootScope.isSmallDevice = document.documentElement.clientWidth <= 500;

            },
            
            detectNoPushIdRegistered : function()
            {
                if(!$rootScope.isChromeDevice)
                {
                    return;
                }

                _self.noPushIdRegistered = false;
                
                var checkExistingPush = new ChromePushManager('/service-worker.js', function(error, registrationId)
                    {
                        _self.noPushIdRegistered = (!registrationId && !error) || error=="notSubscribed";
                        
                        if(registrationId)
                        {
                            _self.chromePushRegistrationId = registrationId;
                        }
                    }
                , true);
            },
            
            //getChromePushRegistrationId : function()
            //{
            //    
            //    if(!$rootScope.isChromeDevice)
            //    {
            //        return;
            //    }
            //
            //    var t_chromePushRegistrationId = _self.getCookie('chromePushRegistrationId');
            //
            //    //if no value in cookies
            //    if(t_chromePushRegistrationId)
            //    {
            //        _self.chromePushRegistrationId = t_chromePushRegistrationId;
            //    }
            //
            //},

            
            
            /**
             * Validation utils
             */
            validationUtils : {

                maxHotelDescription: 5000,

                maxActivityDescription: 1000,
                maxActivityShortDescription: 300
            },

            /**
             * save the chrome push id
             */
            chromePushRegistrationId: null,

            /**
             * save the lang lot gps location of client
             */
            geoLocation: null,

            /**
             * If a user no logged, we save his initId, for recognation
             */
            guestCustomerId: null,

            /**
             * save the detected gps city of customer
             */
            gpsCity: null,

            //setGpsCity: function(gpsCity)
            //{
            //    _self.gpsCity = gpsCity;
            //    $rootScope.$broadcast("gpsCityUpdated", _self.gpsCity);
            //},

            validateActivityInfo: function(activityObj){

                if(!activityObj.shortDescription || activityObj.shortDescription.length==0)
                {
                    return $filter('translate')('alert.error.emptyShortDescription');
                }

                //if(!activityObj.description || activityObj.description.length == 0)
                //{
                //    return $filter('translate')('alert.error.emptyDescription');
                //}

                if(!activityObj.shortDescription || activityObj.shortDescription.length> _self.validationUtils.maxActivityShortDescription)
                {
                    return $filter('translate')('alert.error.shortDescriptionLong');
                }

                if(!activityObj.description || activityObj.description.length> _self.validationUtils.maxActivityDescription)
                {
                    return $filter('translate')('alert.error.descriptionLong');
                }

                return "";
            },

            getNextSundayMiddayDate: function(checkinDateTo)
            {
                if(!checkinDateTo)
                {
                    checkinDateTo = new Date();
                }

                var sundayOfLastWeek = checkinDateTo.getDate() - checkinDateTo.getDay();
                var sundayOfThisWeek = sundayOfLastWeek + 7; //letzte tag is Samstag, aber wir brauchen Sonntag in DE
                //End of week: Sunday at 12:00
                checkinDateTo.setDate(sundayOfThisWeek);
                checkinDateTo.setHours(12);

                return checkinDateTo;
            },

            getLanguageArray : function(){

                var languages = [{icon: "<img src='angulr/icons/flags/at.png' />", name: "at", ticked: false},
                    {icon: "<img src='angulr/icons/flags/de.png' />", name: "de", ticked: false},
                    {icon: "<img src='angulr/icons/flags/en.png' />", name: "en", ticked: false},
                    {icon: "<img src='angulr/icons/flags/fr.png' />", name: "fr", ticked: false},
                    {icon: "<img src='angulr/icons/flags/nl.png' />", name: "nl", ticked: false},
                    {icon: "<img src='angulr/icons/flags/pl.png' />", name: "pl", ticked: false},
                    {icon: "<img src='angulr/icons/flags/ru.png' />", name: "ru", ticked: false},
                    {icon: "<img src='angulr/icons/flags/us.png' />", name: "us", ticked: false}];

                return languages;
            },

            systemLanguagesArray : {"en":'English', "de":'German' /*, "ru":'Russian'*/},

            _getSystemLanguagesArray: function(){
                return angular.copy(_self.systemLanguagesArray);
            },

            convertLanguageKey: function(langFullKey){

                if(!langFullKey)
                {
                    return undefined;
                }

                var underscoreIndex = langFullKey.indexOf("_"); //de_DE

                var langKey = "";

                if (underscoreIndex !== -1) {
                    langKey = langFullKey.substring(underscoreIndex + 1).toLowerCase();
                } else {
                    langKey = (langFullKey+"").toLowerCase();
                }

                return langKey;
            },

            convertLangLabelToAvailableKey: function(langLabel){
                for (var key in _self.systemLanguagesArray) {
                    if (key === 'length' || !_self.systemLanguagesArray.hasOwnProperty(key)) continue;

                    if(_self.systemLanguagesArray[key] == langLabel){
                        return key;
                    };
                };

                _self.getDefaultLanguageViewKey();
            },

            getDefaultLanguageViewKey: function (){
                return  _self.convertLanguageKey($translate.preferredLanguage());
            },

            getSystemLanguageViewKeys: function(){

                var keys = [];

                for (var key in _self.systemLanguagesArray) {
                    if (key === 'length' || !_self.systemLanguagesArray.hasOwnProperty(key)) continue;
                    keys.push(key);
                }

                return keys;
            },

            getDefaultAllowedLanguageViewKey: function (initKey)
            {
                var defSysLangKey = "";

                if(initKey)
                {
                    defSysLangKey = _self.convertLanguageKey(initKey)
                }
                else
                {
                    defSysLangKey = _self.convertLanguageKey($translate.preferredLanguage());
                }

                //for(var l=0; l < _self.systemLanguagesArray.length; l++)
                //{
                if( _self.systemLanguagesArray[defSysLangKey])
                {
                    return defSysLangKey;
                }
                //}

                defSysLangKey = "en";

                return defSysLangKey;
            },

            getDefaultSystemLanguageLabel : function(){

                var usedLanguageKey = _self.getDefaultLanguageViewKey();
                return _self.systemLanguagesArray[usedLanguageKey] || 'English';

                //return usedLanguage;
            },

            getSystemLanguagesArray: function(){

                var availableSystemLanguages = [];

                var sysLangKeys = _self.getSystemLanguageViewKeys();

                for (var i = 0; i < sysLangKeys.length; i++) {

                    var langKey = sysLangKeys[i];

                    availableSystemLanguages[i] = {
                        name: _self.systemLanguagesArray[langKey],
                        langKey: langKey,
                        langKeyLabel: langKey.charAt(0).toUpperCase() + langKey.slice(1),
                        icon: "<img src='angulr/icons/flags/" + langKey + ".png' />",
                        ticked: false
                    };

                    //if (langKey == $scope.localState.selectSystemLanguage) {
                    //    $scope.localState.availableSystemLanguages[i].ticked = true;
                    //}
                }

                return availableSystemLanguages;
            }

            //}
        };

        service.initService();

        return service;

    });
 
