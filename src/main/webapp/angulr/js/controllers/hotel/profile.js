'use strict';
app
//angular.module('hotelApp.profile', ['ngRoute', 'hotelApp.customerDto'])
	
	.controller('ProfileController', ['$scope', '$rootScope', '$routeParams', '$location', '$resource','CustomerDto','$translate', '$stateParams', 'editableOptions', 'editableThemes', '$state', '$filter',
		function($scope, $rootScope, $routeParams, $location, $resource, CustomerDto, $translate, $stateParams, editableOptions, editableThemes, $state, $filter) {

			editableThemes.bs3.inputClass = 'input-sm';
			editableThemes.bs3.buttonsClass = 'btn-sm';
			editableOptions.theme = 'bs3';
			
			$scope.localState = {
				
				profileBirthday : null,
				userData: undefined,

                updateProfileData : undefined,

            // var sessionCustomer = SessionCustomer.get(function(){
				 languages : $scope.hotelService.getLanguageArray(),

				 birthday : {
					day: undefined,
					month: undefined,
					year: undefined
				 },
                
                generatedProfileBirthdayString : null

			}
			
		
			$scope.getProfileBirthdayString = function()
			{
				if(!$scope.localState.profileBirthday)
				{
                    $scope.localState.generatedProfileBirthdayString = null;
				}
				else 
                {
                    var monthNames = [
                        "January", "February", "March",
                        "April", "May", "June", "July",
                        "August", "September", "October",
                        "November", "December"
                    ];

                    var day = $scope.localState.profileBirthday.getDate();
                    var monthIndex = $scope.localState.profileBirthday.getMonth();
                    var year = $scope.localState.profileBirthday.getFullYear();

                    $scope.localState.generatedProfileBirthdayString = day+"."+(monthIndex+1)+"."+year;
                }
                
                return $scope.localState.generatedProfileBirthdayString;
            }

            $scope.$watch('localState.profileBirthday', function() {

                $scope.getProfileBirthdayString();

            });
            
			$scope.getDateFormat = function (tdate)
			{
				if(!tdate)
				return tdate;

				//var year = tdate.slice(-4),
				//	month = ['Jan','Feb','Mar','Apr','May','Jun',
				//			'Jul','Aug','Sep','Oct','Nov','Dec'].indexOf(tdate.substr(4,3))+1,
				//	day = tdate.substr(8,2);
				
				var nDate = new Date();
				
				nDate.setTime(tdate);

				$scope.localState.birthday.day  = nDate.getDate();
				$scope.localState.birthday.month  = nDate.getMonth() +1;
				$scope.localState.birthday.year  = nDate.getFullYear();
				
				$scope.localState.profileBirthday  = nDate;

                $scope.getProfileBirthdayString();
				//return nDate.getDate()+"." + (nDate.getMonth()+1) + "." + nDate.getFullYear();
				//return tdate;
			}

			 
			$scope.submitProfile = function() {

				$scope.mainState.errorMsg = false;
				
				$scope.mainState.successMsg = false;
				
				var error =$scope.hotelService.validateCustomerInfo($scope.localState.updateProfileData);
				
				if(error.length>0)
				{
					$scope.mainState.errorMsg = error;
					$scope.topPage();
					return;
				}
				
				if($scope.localState.profileBirthday  != null)
				{
					$scope.localState.updateProfileData.birthdayTime = $scope.localState.profileBirthday.getTime();
				}
				else{
					$scope.localState.updateProfileData.birthdayTime = undefined;
				}

                //EUGEN!!! confirmPassword not aeaited from serializer!
				$scope.localState.updateProfileData.confirmPassword = undefined;

                $rootScope.showLoading(true);
                
                $scope.hotelState.submitProfileData(true, $scope.localState.updateProfileData).then(function(updatedCustomer)
                {
                    $rootScope.showLoading(false);

                    $rootScope.topPage();
                    $scope.mainState.successMsg = $filter('translate')('alert.success.dataSaved');
                    reInitProfileData(updatedCustomer);
                }, function(error){
                    $scope.mainState.errorMsg = error;
                    $rootScope.showLoading(false);
                })

			}
			
	
            function reInitProfileData(initProfile)
            {
                if(!initProfile)
                {
                    initProfile = $scope.hotelState.profileData;
                }
                
                $scope.localState.updateProfileData = angular.copy(initProfile);
                $scope.localState.updateProfileData.age = undefined;

                $scope.localState.updateProfileData.confirmPassword = undefined;
                $scope.hotelState.profileData.confirmPassword = undefined;

                $scope.localState.goodEmail = (initProfile.email+"").indexOf("@")>0;
            }
            
			$scope.initProfile = function(sessionCustomer)
			{

                reInitProfileData(sessionCustomer);
				
				setTimeout($scope.hideAside, 500);
				setTimeout($scope.hideAside, 1000);

				if(sessionCustomer && sessionCustomer.id && sessionCustomer.id>0)
				{
					$scope.hotelState.setProfileData(sessionCustomer);

					$scope.localState.hotelId =  sessionCustomer.hotelId;
					$scope.localState.ownProfile = $scope.localState.userData && $scope.localState.userData.id!=sessionCustomer.id? false : true;

					if(sessionCustomer.hotelId && sessionCustomer.hotelId>0) {
						$scope.localState.hotel = $scope.hotelState.getCurrentHotel(sessionCustomer.hotelId);
					}

					$scope.localState.goodEmail = (sessionCustomer.email+"").indexOf("@")>0;
                    
					$scope.getDateFormat(sessionCustomer.birthdayTime);

					if($scope.hotelState.profileData.languages!=undefined)
					{
						for (var l = 0; l < $scope.hotelState.profileData.languages.length; l++) {

							for (var i = 0; i < $scope.localState.languages.length; i++) {
								if ($scope.localState.languages[i].name === $scope.hotelState.profileData.languages[l]) {
									$scope.localState.languages[i].ticked = true;
								}
							}
						}
					}

					$rootScope.showLoading(false);

				}
                
			};


			$scope.hotelState.getDeferredState().then(function() {

				if($stateParams.userId)
				{
					var user = CustomerDto.get({id:$stateParams.userId, requesterId: $scope.hotelState.profileData.id}, function() {
						$scope.localState.userData = user;
					});
				}
				//else
				{
					$scope.initProfile($scope.hotelState.profileData);
				}
			});

		}])
;