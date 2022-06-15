'use strict';


angular.module('app', [
    'ngRoute',
    
    
    //Angulr:
    //'ngAnimate',
    'ngAria',
    'textAngular',
    'xeditable',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'ngFileUpload',
    'angular-img-cropper',
    'ui.router',
    'ui.bootstrap',
    'ui.utils',
    'ui.load',
    'ui.jq',
    'oc.lazyLoad',

    'pascalprecht.translate',
    'ngMaterial',
    //drag and drop
    'dnd',
    //'720kb.socialshare',
    'djds4rce.angular-socialshare',
    'angular-carousel',
    //'ngLoginSubmit',

    //money input
    'fiestah.money',
    //'ngTagsInput',
    
    'hj.uiSrefFastclick',
    //'ng-fastclick',
     //HotelApp:
    'isteven-multi-select',
    //'hotelApp.shared',
    //'hotelApp.logService',
    'hotelApp.slideCtrl',
    //'hotelApp.checkInClient',

    'hotelApp.templates',
    
    //TODO Wugwn
    //'hotelApp.initTranslations',
    
    
    //'hotelApp.initTranslate',
    
    //DATA TRANSFER OBJECTS
    'hotelApp.customerDto',
    'hotelApp.hotelDto',
    'hotelApp.activityDto',
    //'hotelApp.userDto',
    //Help DTO Services
    'hotelApp.filterHotelCity',
    
    'hotelApp.hotelState',
    'hotelApp.hotelService',
    'hotelApp.language',
    'hotelApp.hotelLoginService',
    'hotelApp.hotelCheckinService',
    'hotelApp.hotelNotification',
        
    "hotelApp.datepicker",

    //'hotelApp.serverCommunicatorService',
    //'hotelApp.loginCustomerService',
    //'hotelApp.registerCustomerService',
    'hotelApp.sessionCustomerService',
    'hotelApp.logoutCustomerService',
    //'hotelApp.checkinCustomerService',
    'hotelApp.checkIn',
    // page controllers:
    //'hotelApp.language',
    'hotelApp.login',
    //'hotelApp.checkIn',
    //'hotelApp.register',
    'hotelApp.werbung',
    //'hotelApp.profile',
    //'hotelApp.imageCrop',
    'hotelApp.hotel',
    'hotelApp.hotelCtrl',
    'hotelApp.activityCtrl',

    //'hotelApp.chat',
    //'hotelApp.chatList',
    //'hotelApp.wall',
    //'hotelApp.forgotpwd',
    //'hotelApp.activities',
    //'hotelApp.hotelList',
    
    //"hotelApp.fileUploader",
    //"hotelApp.chatService",
    //"hotelApp.wallService",
    //"hotelApp.fileUploadService",
    "hotelApp.notificationService"
    //"hotelApp.activityService"
    
])
    .
    run(function() {
        FastClick.attach(document.body);
    })
;

//##########################################################################
    //.config(configFunction)
    //.run(runFunction);


//configFunction.$inject = ['$locationProvider', '$stateProvider', '$urlRouterProvider'];
//function configFunction($locationProvider, $stateProvider, $urlRouterProvider) {
//    $locationProvider.html5Mode(true);
//
//    $urlRouterProvider.otherwise("/");
//
//    $stateProvider
//
//        .state('home', {
//            url: '/',
//            templateUrl: 'views/home.html',
//            controller: function ($rootScope) {
//                $rootScope.title = 'Snoopex Computer Repairs and Upgrades Campbelltown and Wollongong';
//                $rootScope.metaDescription = 'Professional computer repairs, upgrades and custom built PCs. Providing professional computer repair services to the Campbelltown and Wollongong areas for over 20 years.';
//            }
//        })
//
//        // ... other routes excluded for brevity
//
//    ;
//}

