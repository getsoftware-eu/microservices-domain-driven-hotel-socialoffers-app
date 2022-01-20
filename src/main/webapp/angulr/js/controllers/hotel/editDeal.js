'use strict';
app
//angular.module('hotelApp.activities', ['ngCookies', 'ngResource', 'hotelApp.activityService', 'hotelApp.sessionCustomerService'])

    .controller('EditDealContr',
    ['$scope', '$rootScope', '$cookies', '$location', '$resource', 'SessionCustomer', '$stateParams', '$window',

        function($scope, $rootScope, $cookies, $location, $resource, SessionCustomer, $stateParams, $window) {

        $scope.activityDealDescrWidth = (document.documentElement.clientWidth - 100 - 25 - 10 - 15) + "px" ;

        //TODO eugen: load old messages from DB
           
       $scope.localState = {
           //ready : 0,
            deals : [],

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

         
        
        $scope.updateLocalFilter = function()
        {
            $scope.localState.filterActivities = [$scope.showActivity];
            $scope.localState.copyActivities = angular.copy($scope.localState.filterActivities);
        };
            
        function init()
        {
            //if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
            {
                var searchHotelId = $stateParams.hotelId? $stateParams.hotelId : $scope.hotelState.profileData.hotelId;
                
                //$scope.HotelActivitiesService.getActivities({customerId: $scope.hotelState.profileData.id, hotelId: searchHotelId}, function(responseArray){
                //    $scope.localState.activities = $scope.decodeActivityList(responseArray);
                //    
                //    $scope.loadDetailActivity();
                //    $rootScope.showLoading(false);
                //
                //})

                $scope.loadDetailDeal();

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
            
        $scope.loadDetailDeal = function()
        {
            //if($scope.editDealId == "new")
            //{
            //    $scope.showDeal = $scope.hotelService.getInitDeal();
            //
            //    $scope.showActivity.hotelId =  $scope.hotelState.profileData.hotelId;
            //    
            //    $scope.editActivityId = $scope.showActivity.activityId;
            //
            //    $scope.localState.showActivityId = $scope.editActivityId;
            //
            //    setTimeout(function () {
            //        //default open edit activity accordion
            //        //$scope.accordionStatus.openEditActivities[$scope.showActivity.activityId] = true;
            //        //default open activity content
            //        if(!$scope.accordionStatus.openActivities)
            //        {
            //            $scope.accordionStatus.openActivities = [];
            //        }
            //        $scope.accordionStatus.openActivities[$scope.showActivity.activityId] = true;
            //        //prevent modal onTouch error
            //        $rootScope.unlockModal();
            //    }, 100);
            //
            //    return;
            //}
            //else 
            if($stateParams.dealId || $scope.editDealId && $scope.editDealId!="new")
            {
                var searchDealId = $scope.editDealId? $scope.editDealId : $stateParams.dealId;

                $scope.localState.showDealId = searchDealId;

                for(var a=0; a<$scope.hotelState.customerDeals.length; a++)
                {
                    if($scope.hotelState.customerDeals[a] && $scope.hotelState.customerDeals[a].initId == searchDealId)
                    {
                        $scope.showDeal = $scope.hotelState.customerDeals[a];
                        
                        //if(!$scope.accordionStatus.openActivities)
                        //{
                        //    $scope.accordionStatus.openActivities = [];
                        //}
                        //$scope.accordionStatus.openActivities[$scope.showActivity.activityId] = true;
                        
                        setTimeout(function () { 
                            //default open edit activity accordion
                            //$scope.accordionStatus.openEditActivities[$scope.showActivity.activityId] = true;
                            //default open activity content
                            //if(!$scope.accordionStatus.openActivities)
                            //{
                            //    $scope.accordionStatus.openActivities = [];
                            //}
                            //$scope.accordionStatus.openActivities[$scope.showActivity.activityId] = true;
                        
                            //prevent modal onTouch error
                            $rootScope.unlockModal();
                        }, 500);

                        break;
                    }
                }
            }
        }

            //$scope.acceptDealAction = function(submitDeal, action){
            //
            //    var customerId = $scope.hotelState.getRequesterId();
            //
            //    $rootScope.showLoading(true);
            //
            //    var dealActionService = $resource('./deals/action/:action/customer/:customerId/activityId/:activityId/dealId/:dealId', {action: '@action', customerId:'@customerId', activityId:'@activityId', dealId: '@dealId' }, {'addAction': {method: 'GET'
            //        ,headers: {
            //            'Content-Type': 'application/json;charset=UTF-8'
            //            ,'Accept': 'application/json, text/plain, */*'
            //        }
            //    }});
            //    
            //    
            //    var tempDialId = -1;
            //
            //    dealActionService.addAction({action: action, customerId: customerId, activityId: submitDeal.activityId, dealId: submitDeal.dealCode},
            //        function(updatedDeal)
            //        {
            //            if(updatedDeal.activityId && updatedDeal.activityId>0)
            //            {
            //                var decodedDeal = $scope.hotelService.decodeDeal(updatedDeal);
            //
            //                //if(!$scope.hotelState.customerDeals[submitActivity.entityId])
            //                //{
            //                //    $scope.hotelState.customerDeals[submitActivity.entityId] = [];
            //                //}
            //
            //                $scope.hotelState.customerDeals[submitDeal.activityId] = decodedDeal;
            //
            //                $scope.showDeal = decodedDeal;
            //
            //                //$scope.openModal('editDeal', decodedDeal.dealId)
            //
            //                //if($scope.showDetailActivity && $scope.showDetailActivity.activityId == decodedActivity.activityId)
            //                //{
            //                //    $scope.showDetailActivity = decodedActivity;
            //                //}
            //                //
            //                //$scope.addUpdateActivity(decodedActivity);
            //
            //                for(var a=0; a<$scope.hotelState.hotelActivities.length; a++)
            //                {
            //                    if($scope.hotelState.hotelActivities[a].id == decodedDeal.activityId)
            //                    {
            //                        $scope.hotelState.hotelActivities[a].validDealId = decodedDeal.initId;
            //                        break;
            //                    }
            //                }
            //                
            //            }
            //            $rootScope.showLoading(false);
            //        }
            //        ,function(error)
            //        {
            //            $scope.mainState.errorMsg = error;
            //            $rootScope.showLoading(false);
            //        }
            //    );
            //};
            
            $scope.hotelState.getDeferredState().then(function() {
                init();
            }, function(error){
                init();

            });
            
            if($scope.hotelState && $scope.hotelState.profileData.checkedIn)
            {
                init();
            }
            else{
                $rootScope.closeModal();
                $scope.openNeedCheckinPopup();
            }
            
            
            
            
    }])
; 
