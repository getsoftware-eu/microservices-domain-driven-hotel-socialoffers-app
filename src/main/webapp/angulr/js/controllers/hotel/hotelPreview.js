'use strict';
app
//angular.module('hotelApp.hotel', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto'])

    .controller('HotelPreviewController', ['$scope', '$rootScope', '$location', '$resource', '$stateParams', 'HotelDto', '$window', '$state', '$filter', '$mdDialog',
            function($scope, $rootScope, $location, $resource, $stateParams, HotelDto, $window, $state, $filter, $mdDialog) {

                $scope.localState = {
                    ready : 0,

                    showActivityHotelLink:true, //Show Hotel Link in activities!!!
                    hotelBlockInternActivities: true,

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
                    activitiesLoaded : false,

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

                    $scope.getUpdateHotelActivities(true);

                    $scope.updateLocalFilter();
                });
                
                $scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch){


                    if(onlySetIfEmpty && $scope.localState.filterActivities && $scope.localState.filterActivities.length>0)
                    {
                        return;
                    }
                    //var subSetToSearch = null;

                    if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0){
                        $scope.localState.searchFilter.hotelId = $scope.localState.selectedFilterHotel.id;
                    }
                    //else if($scope.mainState.selectedCity != "-1" && $scope.mainState.selectedCity != undefined && $scope.mainState.selectedCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase())
                    //{
                    //      $scope.localState.searchFilter.hotelCity = $scope.mainState.selectedCity;
                    //}

                    //Eugen: Check if it is activityList, not hotelList
                    if(!subSetToSearch || subSetToSearch.length==0 || subSetToSearch[0].dtoType != 'activity')
                    {
                        subSetToSearch = $scope.hotelState.hotelActivities;
                    }

                    $scope.localState.filterActivities = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, true);//, $scope.localState.strictSearch);

                    $scope.localState.noActivityFound = $scope.localState.filterActivities.length==0 && $scope.hotelState.hotelActivities.length>0;

                    if($scope.localState.noActivityFound || $scope.localState.filterActivities.length>0)
                    {
                        $scope.localState.activitiesLoading = false;
                    }

                    $scope.localState.copyActivities = angular.copy($scope.localState.filterActivities);

                }

                ////######################## HOTEL LIST -> UPDATE HOTEL ######################

                //$scope.isActivityOpened = function(activityId){
                //
                //    return $scope.localState.openActivities[activityId];
                //}
                //
                //$scope.openActivity = function(activityId){
                //
                //    var open = $scope.isActivityOpened(activityId);
                //
                //    if(open)
                //        $scope.localState.openActivities[activityId]=false;
                //    else{
                //        $scope.localState.openActivities[activityId]=true;
                //    }
                //};

                //$scope.submitNewActivity = function()
                //{
                //    $scope.localState.newActivityForm = document.forms['newActivityForm'];
                //
                //    if($scope.localState.newActivityForm)
                //        $scope.localState.newActivityForm.submit();
                //};

                //$scope.submitActivity = function() {
                //
                //    $scope.mainState.successMsg = false;
                //
                //    if(!$scope.localState.newactivity.shortDescription)
                //    {
                //        $scope.mainState.errorMsg = "Activity short description is empty";
                //        return;
                //    };
                //
                //    if($scope.localState.newactivity.shortDescription.length>0)
                //    {
                //        //var newActivity = new HotelActivityDto();
                //
                //        //newActivity.title = $scope.localState.newactivity.title;
                //        $scope.localState.newactivity.hotelId = $scope.hotelState.currentHotel.id;
                //        $scope.localState.newactivity.senderId = $scope.hotelState.profileData.id;
                //
                //        //Encode separatly, not display encoding bindings
                //        var encodedActivity = $scope.hotelService.encodeActivity($scope.localState.newactivity);
                //
                //        ActivityService.send(encodedActivity, $scope.localState.newactivity.activityId);
                //
                //        $scope.myWait(500);
                //
                //        $scope.uploadNewImage("activity", $scope.localState.newactivity.activityId);
                //
                //        $scope.localState.newactivity = $scope.hotelService.getInitActivity();
                //
                //        $scope.mainState.successMsg = "Activity wurde erzeugt";
                //
                //        $scope.accordionStatus.newActivityOpen = false;
                //
                //        //$window.location.reload();
                //
                //    }
                //    else{
                //        $scope.mainState.errorMsg = "Activity description to short";
                //        return;
                //    }
                //
                //};



                //$scope.removeActivity = function(activityId) {
                //
                //    ActivityRemoveService.remove({customerId: $scope.hotelState.profileData.id, activityId: activityId}, function(response){
                //        response.$promise.then(function(value){
                //            
                //            for(var a=0; a<$scope.hotelState.hotelActivities.length; a++)
                //            {
                //                if($scope.hotelState.hotelActivities[a].activityId == activityId)
                //                {
                //                    $scope.hotelState.hotelActivities.splice(a, 1);
                //                    $scope.mainState.successMsg = "Activity wurde entfernt";
                //
                //                    break;
                //                }
                //            }
                //            
                //        });
                //    });
                //};

                $scope.addUpdateActivity = function(newActivity)
                {
                    var activityUpdated = false;

                    if(newActivity.initId>0)
                    {
                        for(var a=0; a<$scope.hotelState.hotelActivities.length; a++)
                        {
                            if($scope.hotelState.hotelActivities[a].initId == newActivity.initId)
                            {
                                $scope.hotelState.hotelActivities[a] = newActivity;
                                activityUpdated = true;
                                break;
                            }
                        }
                    }

                    if(!activityUpdated)
                    {
                        $scope.hotelState.hotelActivities.push(newActivity);
                    }

                    $scope.updateLocalFilter();
                };

                //################# SERVICE LISTENER //////////////////////////////////////////////////////

                //ActivityService.receive().then(null, null, function(activity) {
                //
                //    if( $scope.hotelState.currentHotel
                //        &&
                //        $scope.hotelState.currentHotel.id == activity.hotelId)
                //    {
                //
                //        var decodedActivity = $scope.hotelService.decodeActivity(activity);
                //
                //        if($scope.showDetailActivity && $scope.showDetailActivity.activityId==decodedActivity.activityId)
                //        {
                //            $scope.showDetailActivity = decodedActivity;
                //        }
                //
                //        $scope.addUpdateActivity(decodedActivity);
                //
                //        //$scope.loadDetailActivity();
                //    }
                //});

                // #################### POPUP BLOCK #################

                $scope.showPopup = function(ev) {

                    //if($scope.mainState.showCheckinWellcomePopup)
                    //{
                    //    $scope.mainState.showCheckinWellcomePopup = false;
                    //    
                    //    var confirm = $mdDialog.confirm(
                    //        {
                    //            clickOutsideToClose: true
                    //        })
                    //        .title($filter('translate')('alert.success.newCheckinPopupHeader'))
                    //        .content($filter('translate')('alert.success.newCheckinPopup'))
                    //        .ariaLabel('Lucky day')
                    //        .ok('OK')
                    //        .cancel($filter('translate')('alert.noPopupMore'))
                    //        .targetEvent(ev);
                    //
                    //    $mdDialog.show(confirm).then(function() {
                    //
                    //    }, function() {
                    //        $scope.mainState.showCheckinWellcomePopup = false;
                    //    });
                    //}

                };


                //##################### WATCHERS ####################

                $scope.$watch('mainState.globalIntervalCheck', function() {

                    if($state.current.name == "app.activityList" )
                    {
                        //if($scope.hotelState.profileData)
                        {
                            $scope.getUpdateModuleData();
                        }
                        //else {
                        //    $scope.hotelState.initState().then(function(reponse){
                        //        $scope.getUpdateModuleData();
                        //    });
                        //}
                    }

                });



                /// ###################### GET-UPDATE BLOCK ////////////////////////
                $scope.emptyPreviousSearch = function()
                {
                    $scope.localState.searchFilter = {};
                    //$scope.localState.selectedCityHotel = null;
                    $scope.localState.selectedFilterHotel = null;
                }

                $scope.getUpdateHotelActivities = function(forceUpdate)
                {
                    var activitiesHotelId = $stateParams.hotelId? $stateParams.hotelId : $scope.hotelState.profileData && $scope.hotelState.profileData.hotelId>0 ? $scope.hotelState.profileData.hotelId : -1;

                    //load {All} Activities
                    if(!$scope.hotelState.hotelActivities || $scope.hotelState.hotelActivities.length==0 || $scope.hotelState.hotelActivities[0].hotelId != activitiesHotelId || forceUpdate)
                    {
                        var requesterId = $scope.hotelState.profileData.id>0 ? $scope.hotelState.profileData.id :  $scope.hotelService.guestCustomerId;
                        
                        HotelActivitiesService.getActivities({customerId: requesterId, hotelId: activitiesHotelId}, function(responseArray){

                            $scope.hotelState.hotelActivities = $scope.decodeActivityList(responseArray);
                            $scope.updateLocalFilter();
                            //Eugen: filterCitySelector Watcher!!!
                            //$scope.localState.hotelCitySelectorUpdator++;
                            $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                            //$scope.onChangeHotelCitySelector();
                            $scope.localState.activitiesLoaded = true;
                            
                            $scope.showLoading(false);

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
                    $scope.getUpdateHotelActivities();
                };

                function initActivityList(){

                    //if($rootScope.relocateLogin())
                    //{
                    //    $scope.loginService.resetRequestedLoginState();
                    //    $rootScope.showLoading(false);
                    //    return;
                    //}
                    //else 
                    {
                        if($state.current.name=="app.hotelPreview")
                        {
                            if(!$scope.hotelState.profileData.checkedIn)
                            {
                                $state.go("app.checkin");
                            }
                            else{

                                var previewHotelId = $stateParams.hotelId? $stateParams.hotelId : $scope.hotelState.profileData.hotelId;

                                $scope.getUpdateMainHotelObject(previewHotelId).then(function(hotelObj){
                                    $scope.localState.selectedFilterHotel = hotelObj;
                                    $scope.showHotel = hotelObj;
                                });
                            }

                        }
                        
                        $scope.showPopup();
                        $scope.getUpdateModuleData();
                        $scope.updateLocalFilter();
                    }

                    $scope.tryOpenPush();
                   
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