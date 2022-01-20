'use strict';
app
//angular.module('hotelApp.hotel', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto'])

    .controller('ActivityListController', ['$scope', '$rootScope', '$location', '$resource', '$stateParams', 'HotelDto', 'HotelActivityDto', '$window', '$state', '$filter', '$mdDialog',
        function($scope, $rootScope, $location, $resource, $stateParams, HotelDto, HotelActivityDto, $window, $state, $filter, $mdDialog) {

            $scope.localState = {
                ready : 0,
                
                showActivityHotelLink:true, //Show Hotel Link in activities!!!
                //showPeFilteredHotelBlock:false, //Show selected Hotel Block!!!
                //showHotelCityFilterBlock:false, //Show selected Hotel Block!!!
                
                selectedFilterHotel : undefined,
                selectedCityHotel : undefined,

                //hotelCitySelectorUpdator: 0, //watcher down controller
                activitiesLoading: true,
                //newActivityFile : null,

                noActivityFound: false,
                searchFilter : {},
                strictSearch : true,

                initFilterActivityId: undefined,
                
                initCity : undefined,
                
                filterActivities : []
            };

            $scope.accordionStatus = {
                editHotelOpen: false,
                infoHotelOpen: false,
                descriptionHotelOpen: false,
                newActivityOpen: false,
                isEditHotelDisabled: false,
                isInfoHotelDisabled: false,
                isDescriptionHotelDisabled: false,
                isNewActivityDisabled: false
            };

            var HotelActivitiesService = $resource('./activity/activities/customer/:customerId/hotel/:hotelId', {customerId:'@customerId', hotelId:'@hotelId'}, {'getActivities': {method: 'GET', isArray: true}});
            

            //################ filter Cities and Hotels #########################
            $rootScope.$on('activityListUpdate', function(event, mass) {
                $scope.updateLocalFilter();
            });
            $scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch){



                
                
                if(onlySetIfEmpty && $scope.localState.filterActivities && $scope.localState.filterActivities.length>0)
                {
                    return;
                }
                //var subSetToSearch = null;
                
                if($scope.localState.initFilterActivityId && $scope.localState.initFilterActivityId>0){
                    $scope.localState.searchFilter.id = parseInt($scope.localState.initFilterActivityId);
                }
                else if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0){
                    $scope.localState.searchFilter.hotelId = $scope.localState.selectedFilterHotel.id;
                }
                else if($scope.mainState.selectedCity != "-1" && $scope.mainState.selectedCity != undefined && $scope.mainState.selectedCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase())
                {
                    $scope.localState.searchFilter.hotelCity = $scope.mainState.selectedCity;
                }
                
                //Eugen: Check if it is activityList, not hotelList
                if(!subSetToSearch || subSetToSearch.length==0 || subSetToSearch[0].dtoType!='activity')
                {
                    subSetToSearch = $scope.hotelState.allActivities;
                }
                
                $scope.localState.filterActivities = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, true);//, $scope.localState.strictSearch);

                $scope.localState.noActivityFound = $scope.localState.filterActivities.length==0 && $scope.hotelState.allActivities.length>0;
                
                if($scope.localState.noActivityFound || $scope.localState.filterActivities.length>0)
                {
                    $scope.localState.activitiesLoading = false;
                }
                
                $scope.localState.copyActivities = angular.copy($scope.localState.filterActivities);
                

            }
			
			
			function resetInitFilterActivityIdOnSelectChange()
			{
			  
			    //save my city
			    if($scope.localState.initFilterActivityId && $scope.mainState.selectedCity && $scope.mainState.selectedCity!="-1" && !$scope.localState.initCity)
                {
                    $scope.localState.initCity = $scope.mainState.selectedCity;
                }

				//detect city change
                if($scope.localState.initFilterActivityId && $scope.localState.initCity && $scope.mainState.selectedCity && $scope.localState.initCity != $scope.mainState.selectedCity){
                    $scope.localState.initFilterActivityId = "";
                    $scope.getUpdateAllActivities();
                };

				//detect hotel change
                if($scope.localState.initFilterActivityId && $scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id != $stateParams.filterHotelId){
                    $scope.localState.initFilterActivityId = "";
                    $scope.getUpdateAllActivities();
                };
			}

            ////######################## HOTEL LIST -> UPDATE HOTEL ######################
            
            $scope.addUpdateActivity = function(newActivity)
            {
                var activityUpdated = false;

                if(newActivity.initId>0)
                {
                    for(var a=0; a<$scope.hotelState.allActivities.length; a++)
                    {
                        if($scope.hotelState.allActivities[a].initId == newActivity.initId)
                        {
                            $scope.hotelState.allActivities[a] = newActivity;
                            activityUpdated = true;
                            break;
                        }
                    }
                }

                if(!activityUpdated)
                {
                    $scope.hotelState.allActivities.push(newActivity);
                }

                $scope.updateLocalFilter();
            };

            //################# SERVICE LISTENER //////////////////////////////////////////////////////

          
            // #################### POPUP BLOCK #################

            $scope.showPopup = function(ev) {

                if($scope.mainState.hidePopup || !$scope.hotelState.profileData)
                {
                    return;
                }

                if($state.current.name == "app.activityList")
                {
                 
                    if($scope.hotelState.profileData.hideHotelListPopup || $scope.hotelService.getCookie('hotelicohideHotelListPopup')=="true")
                    {
                        return;
                    }
                    
                    var confirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        })
                        .title($filter('translate')('page.activities.popupHeader'))
                        .content($filter('translate')('page.activities.popupText'))
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        .cancel($filter('translate')('alert.noPopupMore'))
                        .targetEvent(ev);

                    $mdDialog.show(confirm).then(function() {

                    }, function() {
                        $scope.hotelState.profileData.hideHotelListPopup = true;
                        $scope.hotelService.setCookie('hotelicohideHotelListPopup', true);

                        $scope.hotelState.submitProfileData(true);
                    });
                }
                
            };


            //##################### WATCHERS ####################

            //$scope.$watch('mainState.globalIntervalCheck', function() {
            //
            //    if($state.current.name == "app.activityList" )
            //    {
            //        //if($scope.hotelState.profileData)
            //        {
            //            $scope.getUpdateModuleData();
            //        }
            //        //else {
            //        //    $scope.hotelState.initState().then(function(reponse){
            //        //        $scope.getUpdateModuleData();
            //        //    });
            //        //}
            //    }
            //
            //});

            

            /// ###################### GET-UPDATE BLOCK ////////////////////////
            $scope.emptyPreviousSearch = function()
            {
                $scope.localState.searchFilter = {};
                //$scope.localState.selectedCityHotel = null;
                $scope.localState.selectedFilterHotel = null;
            }
            
            $scope.getUpdateAllActivities = function()
            {
                //load {All} Activities
                if(!$scope.hotelState.allActivities || $scope.hotelState.allActivities.length==0)
                {
                    var requesterId = $scope.hotelState.profileData.id>0? $scope.hotelState.profileData.id : $scope.hotelService.guestCustomerId;
                    
                    HotelActivitiesService.getActivities({customerId: requesterId, hotelId: -1}, function(responseArray){

                        $scope.hotelState.allActivities = $scope.decodeActivityList(responseArray);

                        //Eugen: filterCitySelector Watcher!!!
                        //$scope.localState.hotelCitySelectorUpdator++;
                        $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                        //$scope.onChangeHotelCitySelector();
                        
                        $scope.showLoading(false);

                    });

                }
                else{
                    
                    //Eugen: filterCitySelector Watcher!!!
                    //$scope.localState.hotelCitySelectorUpdator++;
                }
            };
            
            $scope.getUpdateOneHotelActivities = function(hotelId)
            {
                //load {All} Activities
                if(!$scope.hotelState.allActivities || $scope.hotelState.allActivities.length==0)
                {
                    var requesterId = $scope.hotelState.profileData.id>0? $scope.hotelState.profileData.id : $scope.hotelService.guestCustomerId;
                    
                    HotelActivitiesService.getActivities({customerId: requesterId, hotelId: hotelId}, function(responseArray){

                        $scope.hotelState.allActivities = $scope.decodeActivityList(responseArray);

                        //Eugen: filterCitySelector Watcher!!!
                        //$scope.localState.hotelCitySelectorUpdator++;
                        $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                        //$scope.onChangeHotelCitySelector();
                        
                        $scope.showLoading(false);

                        if($scope.localState.initFilterActivityId)
                        {
                            //TODO Eugen: test it!!!
                            $scope.hotelState.getActivityById($scope.localState.initFilterActivityId).then(function(activityObj){

                                var subSet = [activityObj];
                                $scope.addUpdateActivity(activityObj)
                                //$scope.hotelState.allActivities.push(subSet);

                                $scope.updateLocalFilter(false, subSet);

                                $scope.showLoading(false);
                                
                                //RESET
                                resetInitFilterActivityIdOnSelectChange();
                            });
                        }

                    });

                }
                else{
                    
                    //Eugen: filterCitySelector Watcher!!!
                    //$scope.localState.hotelCitySelectorUpdator++;
                }
            };

            ///######################## INIT BLOCK ##############

            $scope.getUpdateModuleData = function()
            {
                    $scope.getUpdateAllActivities();
            };

            function initActivityList(){

                $scope.localState.initFilterActivityId = $stateParams.filterActivityId? $stateParams.filterActivityId : null;

                var previewHotelId = $stateParams.filterHotelId? $stateParams.filterHotelId : $scope.localState.initFilterActivityId? -1 : $scope.hotelState.profileData.hotelId; 
                
                if(previewHotelId>0)
                {  
                    //if(!$scope.mainState.hotelArray || $scope.mainState.hotelArray.length==0)
                    //{
                    //    $scope.getUpdateMainHotelList();
                    //}
                    
                    $scope.getUpdateMainHotelObject(previewHotelId).then(function(hotelObj){
                        
                            
                        $scope.localState.selectedFilterHotel = hotelObj;
                        $scope.localState.selectedCityHotel = hotelObj;
                        $scope.showHotel = hotelObj;
                        $scope.getUpdateOneHotelActivities(hotelObj.id);

                        //setTimeout(function(){
                        //    
                        //    var previewHotelId = $stateParams.filterHotelId? $stateParams.filterHotelId : $scope.localState.initFilterActivityId? -1 : $scope.hotelState.profileData.hotelId;
                        //    
                        //    if(!previewHotelId)
                        //    {
                        //        return;
                        //    }
                        //    
                        //    if(!$scope.localState.selectedCityHotel)
                        //    {                
                        //        $scope.getUpdateMainHotelObject(previewHotelId).then(function(hotelObj) {
                        //            $scope.localState.selectedCityHotel = hotelObj;
                        //        });
                        //
                        //    }
                        //    
                        //    if(!$scope.localState.selectedFilterHotel)
                        //    {
                        //        $scope.localState.initFilterActivityId = $stateParams.filterActivityId? $stateParams.filterActivityId : null;
                        //        $scope.getUpdateOneHotelActivities(previewHotelId);
                        //    }
                        //}, 3500);
                    });
                }
                else 
                if($scope.localState.initFilterActivityId)
                {

                    //TODO Eugen: test it!!!
                    $scope.hotelState.getActivityById($scope.localState.initFilterActivityId).then(function(activityObj){

                        var subSet = [activityObj];
                        $scope.hotelState.allActivities.push(subSet);

                        $scope.updateLocalFilter(false, subSet);
                        
                        $scope.showLoading(false);
                        
                        //RESET
                        resetInitFilterActivityIdOnSelectChange();
                    });
                }
                else
                {
                    //$scope.showPopup();
                    $scope.getUpdateModuleData();
                    $scope.updateLocalFilter();
                }
                
                $scope.hotelState.checkHeaderTab();
            };

            initActivityList();
            
            //wait for hotel state
            //$scope.hotelState.getDeferredState().then(function() {
            //    initActivityList();
            //});
        }
    ]
)
;