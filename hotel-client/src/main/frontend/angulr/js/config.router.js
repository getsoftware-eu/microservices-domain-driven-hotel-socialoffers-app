'use strict';

/**
 * Config for the router
 */
angular.module('app')

    ////Let's now 'teach' the putHttpCache method to take translations from $templateCache:
    ////http://fadeit.dk/blog/post/preload-angular-translate-partial-loader
    //.run(function run ($cacheFactory, $templateCache) {
    //    
    //    var httpCache = $cacheFactory.get('$http');
    //    
    //    var putHttpCache = function putHttpCache(cacheKey){
    //        var cacheObj = [200, $templateCache.get(cacheKey), {}, "OK"];
    //        httpCache.put('/' + cacheKey, cacheObj); //prepend '/' to make url's match
    //    };
    //    putHttpCache('angulr/l10n/en.json');
    //    putHttpCache('angulr/l10n/de.json');
    //})
    
.run(
    [        '$rootScope', '$location', '$window',
    function ($rootScope, $location, $window) 
    {
        // initialise google analytics
        $window.ga('create', 'UA-70951324-1', 'auto');
    
        // track pageview on state change
        $rootScope.$on('$stateChangeSuccess', function (event) {
            $window.ga('send', 'pageview', $location.path());
        });

    }])
        
    .run(
    [          '$rootScope', '$state', '$stateParams',
        function ($rootScope,   $state,   $stateParams) {
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ]
)
    .config(
    [          '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 'MODULE_CONFIG',
        function ($stateProvider,   $urlRouterProvider, JQ_CONFIG, MODULE_CONFIG) {
            

          $urlRouterProvider
              //.otherwise('access/werbung'); //URL, not state!!!
              .otherwise('/app/checkin'); //URL, not state!!!
          
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'angulr/tpl/app.html',
                  
                  resolve: load(['toaster', 'angulr/js/controllers/toaster.js'
                      //'angulr/js/controllers/toaster.js'
                      //,
                      ////'angulr/js/services/logoutCustomerService.js',
                      //'angulr/js/services/notificationService.js',
                      //'angulr/js/services/loginCustomerService.js'
                  ])
                  
              })
              .state('app.hotel', {
                  url: '/hotel/:hotelId/:activityId/:reloadHotel',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_hotel.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  }
              })              
              .state('app.editHotel', {
                  url: '/editHotel/:hotelId/:reloadHotel',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_editHotel.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  }
              })
              .state('app.hotelPreview', {
                  url: '/hotelPreview/:hotelId/:activityId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_hotelPreview.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/hotelPreview.js'])
              })
              .state('app.hotelList', {
                  url: '/hotelList/:filterCity/:filterHotelId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_hotelList.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },

                  resolve: load(['angulr/js/controllers/hotel/hotelList.js'])
                  //resolve: {
                  //
                  //    deps: ['$ocLazyLoad',
                  //        function( $ocLazyLoad){
                  //            //return $ocLazyLoad.load('angulr/js/services/ .js')
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/services/activityDtoService.js');
                  //               // }
                  //               //)
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/filterHotelCity.js');
                  //               // }
                  //               // )
                  //              //  .then(
                  //              //  function(){
                  //              //      return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/hotelCtrl.js');
                  //              //  }
                  //              //)
                  //            //    .then(
                  //            //    function(){
                  //            //        return $ocLazyLoad.load('angulr/js/controllers/hotel/hotelList.js');
                  //            //    }
                  //            //)
                  //                ;
                  //        }]
                  //}

              })
              .state('app.menuList', {
                  url: '/menuList/:filterCity/:filterHotelId/:filterCafeId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_menuList.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },

                  resolve: load(['angulr/js/controllers/hotel/menuList.js'])
                  //resolve: {
               
                  //}

              })
              .state('app.activityList', {
                  url: '/activityList/:filterCity/:filterHotelId/:filterActivityId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_activityList.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/activityList.js'])

                  //resolve: {
                  //
                  //    deps: ['$ocLazyLoad',
                  //        function( $ocLazyLoad){
                  //            //return $ocLazyLoad.load('angulr/js/services/ .js')
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/services/activityDtoService.js');
                  //               // }
                  //               //)
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/controllers/hotel/datumPicker.js');
                  //               // }
                  //               // )
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/filterHotelCity.js');
                  //               // }
                  //               // )
                  //               // .then(
                  //               // function(){
                  //               //     return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/activityCtrl.js');
                  //               // }
                  //               //)
                  //            //    .then(
                  //            //    function(){
                  //            //        return $ocLazyLoad.load('angulr/js/controllers/hotel/activityList.js');
                  //            //    }
                  //            //)
                  //                ;
                  //        }]
                  //}
              })
              .state('app.dealList', {
                  url: '/dealList/:staffScope/:filterActivityId/:closed',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_dealList.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/menuList.js',
                                'angulr/js/controllers/hotel/dealList.js'])
              })
              .state('app.register', {
                  url: '/register',
                  templateUrl: 'angulr/tpl/hotel/page_register.html',
                  resolve: load(['angulr/js/controllers/hotel/register.js'])
              })  
              .state('app.hotelMailList', {
                  url: '/hotelMailList',
                  templateUrl: 'angulr/tpl/hotel/page_hotelMailList.html',
                  resolve: load(['angulr/js/controllers/hotel/hotelMailList.js'])
              })
              //.state('app.login', {
              //    url: '/login/:stopLogin',
              //    templateUrl: 'angulr/tpl/hotel/page_login.html',
              //    resolve: load(['angulr/js/controllers/hotel/login.js'])
              //})     
              
              .state('app.hotelRegister', {
                  url: '/hotelRegister/:userId',
                  templateUrl: 'angulr/tpl/hotel/page_hotelRegister.html',
                  resolve: load([
                                //'angulr/js/controllers/hotel/datumPicker.js',
                                'angulr/js/controllers/hotel/hotelRegister.js'
                  ])
              })  
              //.state('app.linkedIn', {
              //    url: '/linkedIn',
              //    templateUrl: 'angulr/tpl/page_login.html'
              //})  
              .state('app.checkin', {
                  url: '/checkin/:hotelCode',
                  //templateUrl: 'angulr/tpl/hotel/page_checkIn.html'

                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_checkIn.html'
                      }
                      //,
                      //'footer': {
                      //    templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      //}
                  }
                  //,
                  //resolve: load([
                  //               //'angulr/js/controllers/hotel/blocks/filterHotelCity.js',
                  //               //'angulr/js/controllers/hotel/datumPicker.js',
                  //               'angulr/js/controllers/hotel/checkIn.js'
                  //])
              })
              .state('app.editActivity', {
                  url: '/editActivity/:hotelId/:activityId/:edit',

                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_editActivity.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/editActivity.js'])
                  //resolve: {
                  //    deps: ['$ocLazyLoad',
                  //        function( $ocLazyLoad){
                  //            //return $ocLazyLoad.load('angulr/js/controllers/hotel/datumPicker.js')
                  //            //    .then(
                  //            //    function(){
                  //            //        return $ocLazyLoad.load('angulr/js/controllers/hotel/blocks/activityCtrl.js');
                  //            //    }
                  //            //)
                  //            //    .then(
                  //            //    function(){
                  //            //        return $ocLazyLoad.load('angulr/js/controllers/hotel/editActivity.js');
                  //            //    }
                  //            //)
                  //                ;
                  //        }]
                  //}
              })
              .state('app.wall', {
                  url: '/wall',
                  //templateUrl: 'angulr/tpl/hotel/page_wall.html',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_wall.html',
                          data: {
                              css: 'angulr/css/whiteApp.css'
                          }
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve:{
                      deps: ['$ocLazyLoad',
                          function( $ocLazyLoad){
                              return $ocLazyLoad.load('angulr/js/services/wallService.js').then(
                                  function(){
                                      return $ocLazyLoad.load('angulr/js/controllers/hotel/wall.js');
                                  }
                              );
                          }]
                  }
              })
              .state('app.feedback', {
                  url: '/feedback',
                  templateUrl: 'angulr/tpl/hotel/page_feedback.html',
                  resolve: load(['angulr/js/controllers/hotel/feedback.js'])
              })
              .state('app.feed', {
                  url: '/feed',
                  templateUrl: 'angulr/tpl/hotel/page_feed.html',
                  resolve: load(['angulr/js/controllers/hotel/feed.js'])
              })
              
              .state('app.me', {
                  url: '/me',
                  //templateUrl: 'angulr/tpl/page_profile.html',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_profile.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/profile.js'
                             //, 'angulr/js/services/customerDtoService.js'
                  ])
              })
              .state('app.user', {
                  url: '/user/:userId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_userInfo.html'
                          }
                      ,
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/profile.js'
                             //, 'angulr/js/services/customerDtoService.js'
                            ])

              })
              .state('app.chat', {
                  url: '/chat/:receiverId',
                  //templateUrl: 'angulr/tpl/hotel/page_chat.html',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_chat.html'
                      }
                      //,
                      //'footer': {
                      //    templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      //}
                  },
                  resolve: {
                      deps: ['$ocLazyLoad',
                          function( $ocLazyLoad){
                              return $ocLazyLoad.load('angulr/js/services/chatService.js').then(
                                  function(){
                                      return $ocLazyLoad.load('angulr/js/controllers/hotel/chat.js');
                                  }
                              );
                          }]
                  }
              })
              .state('app.forgotpwd', {
                  url: '/forgotpwd/email/:email/resetcode/:resetcode',
                  templateUrl: 'angulr/tpl/hotel/page_forgotpwd.html',
                  resolve: load(['angulr/js/controllers/hotel/forgotpwd.js'])
              })
              .state('app.about', {
                  url: '/about',
                  templateUrl: 'angulr/tpl/hotel/page_about.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              })  
              .state('app.aboutEugen', {
                  url: '/about/eugen',
                  templateUrl: 'angulr/tpl/hotel/page_about_eugen.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              }) 
              .state('app.privacy', {
                  url: '/about/privacy',
                  templateUrl: 'angulr/tpl/hotel/page_privacy.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              })
              .state('app.agb', {
                  url: '/about/agb',
                  templateUrl: 'angulr/tpl/hotel/page_agb.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              }).state('app.dse', {
                  url: '/about/dse',
                  templateUrl: 'angulr/tpl/hotel/page_dse.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              }).state('app.impressum', {
                  url: '/about/impressum',
                  templateUrl: 'angulr/tpl/hotel/page_impressum.html'
                  //,
                  //controller: {
                  //    $scope.hotelState.setProfileData(null, $location.url());
                  //}
              })
              .state('app.avatar', {
                  url: '/avatar',
                  templateUrl: 'angulr/tpl/hotel/page_avatar.html',
                  //controller: 'ImageCropCtrl',
                  resolve: load(['angulr/js/controllers/hotel/imageCrop.js'])
              })
              .state('app.language', {
                  url: '/language',
                  templateUrl: 'angulr/tpl/hotel/page_language.html',
                  controller: 'LanguageController',
                  resolve: load(['angulr/js/controllers/hotel/language.js'])
              })
              .state('app.chatList', {
                  url: '/chatList/:hotelScope/:filterCity/:filterHotelId',
                  views: {
                      '': {
                          templateUrl: 'angulr/tpl/hotel/page_chatList.html'
                      },
                      'footer': {
                          templateUrl: 'angulr/tpl/layout_footer_mobile.html'
                      }
                  },
                  resolve: load(['angulr/js/controllers/hotel/chatList.js'
                  
                  ])
              })
              .state('app.hotelLogin', {
                  url: '/hotelLogin/:stopLogin',
                  templateUrl: 'angulr/tpl/hotel/page_hotelLogin.html'
                  //,
                  //resolve: load(['angulr/js/controllers/hotel/login.js'])
              })
              .state('access', {
                  url: '/access',
                  template: '<div ui-view class="fade-in-right-big smooth"></div>'
              })
              .state('access.werbung', {
                  url: '/werbung',
                  templateUrl: 'angulr/tpl/hotel/page_werbung.html',
                  resolve: load(['angulr/js/controllers/hotel/werbung.js'])
              })
              .state('access.login', {
                  url: '/login/:stopLogin',
                  templateUrl: 'angulr/tpl/hotel/page_login.html'
                  //,
                  //resolve: load( [
                  //              //'angulr/js/services/loginCustomerService.js',
                  //              'angulr/js/controllers/hotel/login.js'
                  //] )

                  //resolve: {
                  //    deps: ['$ocLazyLoad',
                  //        function( $ocLazyLoad){
                  //            return $ocLazyLoad.load('angulr/js/services/loginCustomerService.js').then(
                  //                function(){
                  //                    return $ocLazyLoad.load('angulr/js/controllers/hotel/login.js');
                  //                }
                  //            );
                  //        }]
                  //}
              })
              
              
              
              //.state('access.hotelLogin', {
              //    url: '/hotelLogin/:stopLogin',
              //    templateUrl: 'angulr/tpl/hotel/page_hotelLogin.html'
              //    //,
              //    //resolve: load(['angulr/js/controllers/hotel/login.js'])
              //})
              //.state('access.signup', {
              //    url: '/signup',
              //    templateUrl: 'angulr/tpl/page_signup.html',
              //    resolve: load( ['angulr/js/controllers/signup.js'] )
              //})
              //.state('access.forgotpwd', {
              //    url: '/forgotpwd',
              //    templateUrl: 'angulr/tpl/page_forgotpwd.html'
              //})
              //.state('access.404', {
              //    url: '/404',
              //    templateUrl: 'angulr/tpl/hotel/page_404.html'
              //})
              ;

function load(srcs, callback) {
    return {
        deps: ['$ocLazyLoad', '$q',
            function( $ocLazyLoad, $q ){
                var deferred = $q.defer();
                var promise  = false;
                srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                if(!promise){
                    promise = deferred.promise;
                }
                angular.forEach(srcs, function(src) {
                    promise = promise.then( function(){
                        if(JQ_CONFIG[src]){
                            return $ocLazyLoad.load(JQ_CONFIG[src]);
                        }
                        angular.forEach(MODULE_CONFIG, function(module) {
                            if( module.name == src){
                                name = module.name;
                            }else{
                                name = src;
                            }
                        });
                        return $ocLazyLoad.load(name);
                    } );
                });
                deferred.resolve();
                return callback ? promise.then(function(){ return callback(); }) : promise;
            }]
    }
}


}
]
);