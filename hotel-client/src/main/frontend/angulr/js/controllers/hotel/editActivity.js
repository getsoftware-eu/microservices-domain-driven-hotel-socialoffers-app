'use strict';
app
//angular.module('hotelApp.activities', ['ngCookies', 'ngResource', 'hotelApp.activityService', 'hotelApp.sessionCustomerService'])

    .controller('EditActivityContr',
    ['$scope', '$rootScope', '$cookies', '$location', '$resource', 'SessionCustomer', '$stateParams', '$window',

        function($scope, $rootScope, $cookies, $location, $resource, SessionCustomer, $stateParams, $window) {


        //TODO eugen: load old messages from DB
           
       $scope.localState = {
           //ready : 0,
            activities : [],

           filterActivities : [],
           copyActivities : [],
           
            showActivity : undefined,
            //newActivity : undefined,
            maxActivityShortDescription : $scope.hotelService.validationUtils.maxActivityShortDescription,
            maxActivityDescription : $scope.hotelService.validationUtils.maxActivityDescription
       }

        $scope.accordionStatus = {
            open: true,
            openEditActivity: true,
            isFirstOpen: true,
            isFirstDisabled: false

            //newActivityOpen: false,
            //isNewActivityDisabled: false,
            
            //openEditActivities: []
        };

        $scope.HotelActivitiesService = $resource('./activity/activities/customer/:customerId/hotel/:hotelId', {customerId:'@customerId', hotelId:'@hotelId'}, {'getActivities': {method: 'GET', isArray: true}});

        $scope.addUpdateActivity = function(newActivity)
        {
            var activityUpdated = false;
            var allActivityUpdated = false;

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

                for(var a=0; a<$scope.hotelState.allActivities.length; a++)
                {
                    if($scope.hotelState.allActivities[a].initId == newActivity.initId)
                    {
                        $scope.hotelState.allActivities[a] = newActivity;
                        allActivityUpdated = true;
                        break;
                    }
                }
            }

            if(!activityUpdated)
            {
                $scope.hotelState.hotelActivities.push(newActivity);
            }
            
            if(!allActivityUpdated)
            {
                $scope.hotelState.allActivities.push(newActivity);
            }

            $scope.showActivity = newActivity;
            
            $scope.updateLocalFilter();

        };
        
        $scope.updateLocalFilter = function()
        {
            $scope.localState.filterActivities = [$scope.showActivity];
            $scope.localState.copyActivities = angular.copy($scope.localState.filterActivities);
        };
            
        function init()
        {
            if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
            {
                var searchHotelId = $stateParams.hotelId? $stateParams.hotelId : $scope.hotelState.profileData.hotelId;
                
                $scope.HotelActivitiesService.getActivities({customerId: $scope.hotelState.profileData.id, hotelId: searchHotelId}, function(responseArray){
                    $scope.localState.activities = $scope.decodeActivityList(responseArray);
                    
                    $scope.loadDetailActivity();
                    $rootScope.showLoading(false);

                })
            }
            
            if($stateParams.edit || $scope.defaultEditOpen)
            {
                setTimeout(function () { $scope.accordionStatus.open = true; }, 1000);
                
            }
        }
            
        $scope.getFormatDate = function(time)
        {
            if(!time)
                return "";
            
            var d = new Date(time);
            
            if(!d)
                return "";
            
            var day = d.getDate();
            var monthIndex = d.getMonth();
            var year = d.getFullYear();
            return day+"."+(monthIndex+1)+"."+year;
        }

        //$scope.submitNewActivity = function() {
        //
        //    $scope.mainState.successMsg = false;
        //
        //    var errorObj = $scope.hotelService.validateActivityInfo($scope.localState.newActivity);
        //    if(errorObj && errorObj.length>0)
        //    {
        //        $scope.mainState.errorMsg = errorObj;
        //        $rootScope.topPage();
        //        return;
        //    };
        //
        //
        //    //newActivity.title = $scope.localState.newActivity.title;
        //    $scope.localState.newActivity.hotelId = $scope.hotelState.currentHotel.id;
        //    $scope.localState.newActivity.senderId = $scope.hotelState.profileData.id;
        //
        //    //Encode separatly, not display encoding bindings
        //    var encodedActivity = $scope.hotelService.encodeActivity($scope.localState.newActivity);
        //
        //    ActivityService.send(encodedActivity, $scope.localState.newActivity.activityId);
        //
        //    $scope.myWait(500);
        //
        //    $scope.uploadNewImage("activity", $scope.localState.newActivity.activityId);
        //
        //    $scope.localState.newActivity = $scope.hotelService.getInitActivity();
        //
        //    $scope.mainState.successMsg = $filter('translate')("activityUpdated");
        //
        //    $scope.accordionStatus.newActivityOpen = false;
        //
        //    //$window.location.reload();
        //
        //};
            
        $scope.loadDetailActivity = function()
        {
            if($scope.showActivity && $scope.showActivity.initId == $scope.editActivityId)
            {
                //already opened
                return;
            }
            
            if($scope.editActivityId == "new")
            {
                $scope.showActivity = $scope.hotelService.getInitActivity();

                $scope.showActivity.hotelId =  $scope.hotelState.profileData.hotelId;
                
                $scope.editActivityId = $scope.showActivity.initId;

                $scope.localState.showActivityId = $scope.editActivityId;

                setTimeout(function () {
                    //default open edit activity accordion
                    //$scope.accordionStatus.openEditActivities[$scope.showActivity.activityId] = true;
                    //default open activity content
                    if(!$scope.accordionStatus.openActivities)
                    {
                        $scope.accordionStatus.openActivities = [];
                    }
                    $scope.accordionStatus.openActivities[$scope.showActivity.initId] = true;
                    //prevent modal onTouch error
                    $rootScope.unlockModal();
                }, 100);

                return;
            }
            else 
            if($stateParams.activityId || $scope.editActivityId && $scope.editActivityId!="new")
            {
                var searchActivityId = $scope.editActivityId? $scope.editActivityId : $stateParams.activityId;

                $scope.localState.showActivityId = searchActivityId;

                for(var a=0; a<$scope.localState.activities.length; a++)
                {
                    if($scope.localState.activities[a].initId == searchActivityId)
                    {
                        $scope.showActivity = $scope.localState.activities[a];
                        
                        if(!$scope.accordionStatus.openActivities)
                        {
                            $scope.accordionStatus.openActivities = [];
                        }
                        $scope.accordionStatus.openActivities[$scope.showActivity.initId] = true;
                        
                        setTimeout(function () { 
                            //default open edit activity accordion
                            //$scope.accordionStatus.openEditActivities[$scope.showActivity.activityId] = true;
                            //default open activity content
                            if(!$scope.accordionStatus.openActivities)
                            {
                                $scope.accordionStatus.openActivities = [];
                            }
                            $scope.accordionStatus.openActivities[$scope.showActivity.initId] = true;

                            //prevent modal onTouch error
                            $rootScope.unlockModal();
                        }, 500);

                        break;
                    }
                }
            }
        }

            ////TODO Eugen:
            $scope.$watchGroup(['showActivity.lastMinute','showActivity.validFrom','showActivity.validTo','showActivity.hidden'], function(newValues, oldValues, scope) {

                if(newValues[0]==undefined)
                {
                    return;
                }
                
                var newHidden = newValues[3];
                var newLastMinute = newValues[0];
                var dateFrom = new Date(newValues[1]);
                var dateTo = new Date(newValues[2]);
                
                $scope.showActivity.timeValid = !newHidden && (newLastMinute || ( dateFrom <= new Date() && dateTo > new Date()));
                    
             });
            
            if($scope.hotelState && $scope.hotelState.profileData.id>0)
            {
                init();
            }
            
            
            
            
    }])
; 
