'use strict';
app
//angular.module('hotelApp.register', ['ngRoute', 'ngCookies'])

//.config(['$routeProvider', function($routeProvider) {
//  $routeProvider.when('/register', {
//    templateUrl: 'register/register.html',
//    controller: 'RegisterController'
//  });
//}])

.controller('RegisterController', 
		['$scope', '$rootScope', '$http', '$q', '$location', '$cookies','$routeParams', '$translate', '$state', '$filter','editableOptions', 'editableThemes',
		 function($scope, $rootScope, $http, Q, $location, $cookies, $routeParams, $translate, $state, $filter, editableOptions, editableThemes) {
			
			 
        editableThemes.bs3.inputClass = 'input-sm';
        editableThemes.bs3.buttonsClass = 'btn-sm';
        editableOptions.theme = 'bs3';
          
		$scope.localState = {
            
			  agree : false,

			  registerBirthday : null,

			 languages : $scope.hotelService.getLanguageArray(),

			// Initalize the profile with defaults
			profile : $scope.hotelService.getInitCustomer()
		 };

         $scope.getRegisterBirthdayString = function()
         {
             if(!$scope.localState.registerBirthday)
             {
                 return null;
             }

             var monthNames = [
                 "January", "February", "March",
                 "April", "May", "June", "July",
                 "August", "September", "October",
                 "November", "December"
             ];

             var day = $scope.localState.registerBirthday.getDate();
             var monthIndex = $scope.localState.registerBirthday.getMonth();
             var year = $scope.localState.registerBirthday.getFullYear();
             return day+"."+(monthIndex+1)+"."+year;
         };

			 

         $scope.submitRegister = function() {
    
             var error = $scope.hotelService.validateCustomerInfo($scope.localState.profile);
             
             if(error && error.length>0)
             {
                 $scope.mainState.errorMsg = error;
                 $rootScope.topPage();
                 return;
             }
    
             if(!$scope.localState.agree)
             {
                 $scope.mainState.errorMsg = $filter('translate')('alert.error.agreeTermsConditions');
                 $rootScope.topPage();
                 return;
             }
             
            $scope.mainState.errorMsg = false;
    
            $scope.localState.profile.prefferedLanguage = $scope.hotelService.getDefaultAllowedLanguageViewKey();

             if($scope.localState.registerBirthday  != null)
             {
                 $scope.localState.profile.birthdayTime = $scope.localState.registerBirthday.getTime();
             }
             else{
                 $scope.localState.profile.birthdayTime = undefined;
             }
             
            $scope.hotelState.registerCustomer($scope.localState.profile).then(function(createdCustomer)
            {
                    
                if(createdCustomer.id && createdCustomer.id>0)
                {
                    $scope.localState.profile = createdCustomer;
                    
                    $scope.localState.currentUser = createdCustomer;
    
                    if (createdCustomer.id) {
                        
                        $scope.hotelService.setCookie('hotelicoUserId', createdCustomer.id);
    
    
                        $cookies.hotelicoUserEmail = createdCustomer.eMail;
    
                        $cookies.hotelicoLogout = false;
    
                        $scope.hotelState.initState(createdCustomer);
    
                        $state.go('app.checkin');
                    }
                }
                else if(response && response.errorResponse)
                {
                    $scope.mainState.errorMsg = response.errorResponse;
                }
            }, 
            function(error)
            {
                $scope.mainState.errorMsg = error;
                $rootScope.topPage();
                
            });
            
        };


     function init()
     {
         $scope.localState.profile.confirmPassword =  undefined;
         $scope.localState.profile.authPassword =  false;
    
    
         var defaultLanguageKey = $scope.hotelService.getDefaultLanguageViewKey();
    
         for (var i = 0; i < $scope.localState.languages.length; i++) {
             if ($scope.localState.languages[i].name === defaultLanguageKey) {
                 $scope.localState.languages[i].ticked = true;
             }
         }
    
     };
    
    
     init();
 
 }]); 