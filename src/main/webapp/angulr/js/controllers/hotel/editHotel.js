'use strict';
app
//angular.module('hotelApp.activities', ['ngCookies', 'ngResource', 'hotelApp.activityService', 'hotelApp.sessionCustomerService'])

    .controller('EditHotelContr',
    ['$scope', '$rootScope', '$cookies', '$location', '$resource', 'SessionCustomer', '$state', '$stateParams', '$filter',

        function($scope, $rootScope, $cookies, $location, $resource, SessionCustomer, $state, $stateParams, $filter) {
            
        //TODO eugen: load old messages from DB
       $scope.showHotel = $scope.hotelState.tempCreationHotel && $state.current.name == "app.hotelRegister"? $scope.hotelState.tempCreationHotel : angular.copy($scope.hotelState.currentHotel);
    
       if(!$scope.showHotel && $scope.editHotelId)     
       {
           for(var h=0; h<$scope.mainState.hotelArray.length; h++)
           {
               if($scope.mainState.hotelArray[h].id==$scope.editHotelId)
               {
                   $scope.showHotel = $scope.mainState.hotelArray[h];
                   break;
               }
           }
       }
            
        $scope.localState = {
           //ready : 0,
           // activities : [],
           //
            filterHotels : [],
            copyHotels : [],
            editHotelAccordionTitle: "'" + ($scope.showHotel? $scope.showHotel.name : "Hotel") + "' " + $filter('translate')('system.edit'),
            hotelBlockInternActivities : false,
            showHotelBlockFullInfo : true
           // //newActivity : undefined,
           // maxHotelShortDescription : $scope.hotelService.validationUtils.maxHotelShortDescription,
           // maxHotelDescription : $scope.hotelService.validationUtils.maxHotelDescription
       }

        $scope.accordionStatus = {
            editHotelOpen: true,
            //isFirstOpen: true,
            //isFirstDisabled: false,
            //
            ////newActivityOpen: false,
            ////isNewActivityDisabled: false,
            //
            //openEditHotels: []
            descriptionHotelOpen : [],
            infoHotelOpen : []
        };

        //$scope.HotelActivitiesService = $resource('./activity/activities/customer/:customerId/hotel/:hotelId', {customerId:'@customerId', hotelId:'@hotelId'}, {'getActivities': {method: 'GET', isArray: true}});
        //
        //$scope.addUpdateActivity = function(newActivity)
        //{
        //    var activityUpdated = false;
        //    var allActivityUpdated = false;
        //
        //    if(newActivity.activityId>0)
        //    {
        //        for(var a=0; a<$scope.hotelState.hotelActivities.length; a++)
        //        {
        //            if($scope.hotelState.hotelActivities[a].activityId == newActivity.activityId)
        //            {
        //                $scope.hotelState.hotelActivities[a] = newActivity;
        //                activityUpdated = true;
        //                break;
        //            }
        //        }
        //
        //        for(var a=0; a<$scope.hotelState.allActivities.length; a++)
        //        {
        //            if($scope.hotelState.allActivities[a].activityId == newActivity.activityId)
        //            {
        //                $scope.hotelState.allActivities[a] = newActivity;
        //                allActivityUpdated = true;
        //                break;
        //            }
        //        }
        //    }
        //
        //    if(!activityUpdated)
        //    {
        //        $scope.hotelState.hotelActivities.push(newActivity);
        //    }
        //    
        //    if(!allActivityUpdated)
        //    {
        //        $scope.hotelState.allActivities.push(newActivity);
        //    }
        //
        //    $scope.showActivity = newActivity;
        //    
        //    $scope.updateLocalFilter();
        //
        //};
        
        $scope.updateLocalFilter = function()
        {
            $scope.localState.filterHotels = [$scope.showHotel];
            $scope.localState.copyHotels = angular.copy($scope.localState.filterHotels);
        };
            
        function init()
        {
            //if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
            //{
            //    var searchHotelId = $stateParams.hotelId? $stateParams.hotelId : $scope.hotelState.profileData.hotelId;
            //    
            //    //$scope.HotelActivitiesService.getActivities({customerId: $scope.hotelState.profileData.id, hotelId: searchHotelId}, function(responseArray){
            //    //    $scope.localState.activities = $scope.decodeActivityList(responseArray);
            //    //    
            //    //    $scope.loadDetailActivity();
            //    //    $rootScope.showLoading(false);
            //    //
            //    //})
            //}
            //$scope.localState.filterHotels.push(angular.copy($scope.showHotel));
            //$scope.localState.copyHotels.push(angular.copy($scope.showHotel));
            
            //if($stateParams.edit || $scope.defaultEditOpen)
            //{
            //    setTimeout(function () { $scope.accordionStatus.editHotelOpen = true; }, 1000);
            //    
            //}

            //if($scope.hotelState.tempCreationHotel && $scope.hotelState.tempCreationHotel.creationTime == $scope.showHotel.creationTime)
            {
                $scope.accordionStatus.descriptionHotelOpen[-1] = true;
                $scope.accordionStatus.infoHotelOpen[-1] = true;
            }
        }

        $rootScope.$on('hotelUpdated', function(event, obj) {
            if(obj && obj.creationTime == $scope.showHotel.creationTime)
            {
                $scope.showHotel = obj;
                $scope.hotelState.currentHotel = $scope.showHotel;
            }
        });


        //    $scope.getFormatDate = function(time)
        //{
        //    if(!time)
        //        return "";
        //    
        //    var d = new Date(time);
        //    
        //    if(!d)
        //        return "";
        //    
        //    var day = d.getDate();
        //    var monthIndex = d.getMonth();
        //    var year = d.getFullYear();
        //    return day+"."+(monthIndex+1)+"."+year;
        //}
            
            
        //$scope.loadDetailHotel = function()
        //{
        //    if($scope.editHotelId == "new")
        //    {
        //        $scope.showHotel = $scope.hotelService.getInitHotel();
        //    
        //        $scope.showHotel.hotelId =  $scope.hotelState.profileData.hotelId;
        //        
        //        $scope.editHotelId = $scope.showHotel.hotelId;
        //    
        //        $scope.localState.showHotelId = $scope.editHotelId;
        //    
        //        setTimeout(function () {
        //            //default open edit activity accordion
        //            $scope.accordionStatus.openEditActivities[$scope.showHotel.hotelId] = true;
        //            //default open hotel content
        //            if(!$scope.accordionStatus.openActivities)
        //            {
        //                $scope.accordionStatus.openActivities = [];
        //            }
        //            $scope.accordionStatus.openActivities[$scope.showHotel.hotelId] = true;
        //            //prevent modal onTouch error
        //            $rootScope.unlockModal();
        //        }, 100);
        //    
        //        return;
        //    }
        //    else 
        //    if($stateParams.hotelId || $scope.editHotelId && $scope.editHotelId!="new")
        //    {
        //        var searchHotelId = $scope.editHotelId? $scope.editHotelId : $stateParams.hotelId;
        //
        //        $scope.localState.showHotelId = searchHotelId;
        //
        //        for(var a=0; a<$scope.localState.activities.length; a++)
        //        {
        //            if($scope.localState.activities[a].hotelId == searchHotelId)
        //            {
        //                $scope.showHotel = $scope.localState.activities[a];
        //               
        //                setTimeout(function () { 
        //                    //default open edit activity accordion
        //                    //$scope.accordionStatus.openEditActivities[$scope.showHotel.activityId] = true;
        //                    //default open activity content
        //                    if(!$scope.accordionStatus.openHotels)
        //                    {
        //                        $scope.accordionStatus.openHotels = [];
        //                    }
        //                    //$scope.accordionStatus.openActivities[$scope.showHotel.activityId] = true;
        //
        //                    //prevent modal onTouch error
        //                    $rootScope.unlockModal();
        //                }, 500);
        //
        //                break;
        //            }
        //        }
        //    }
        //}

            $scope.hotelState.getDeferredState().then(function() {
                init();
            }, function(error){
                init();

            });
            
            //if($scope.hotelState && $scope.hotelState.profileData.id>0)
            {
                init();
            }
            
            
            
            
    }])
; 
