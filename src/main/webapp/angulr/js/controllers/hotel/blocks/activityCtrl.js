'use strict';
//app
angular.module('hotelApp.activityCtrl', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityDto'])

    //Eugen: the container controller has to have $scope.addUpdateActivity, $scope.localState.editActivityMap []

    .controller('activityCtrl', ['$scope', '$rootScope', '$stateParams', 'HotelDto', '$state', '$resource', '$filter', 'NotificationService', 'HotelActivityDto', '$mdDialog', '$FB',
        function($scope, $rootScope, $stateParams, HotelDto, $state, $resource, $filter, NotificationService, HotelActivityDto, $mdDialog, $FB) {

            $scope.activityDescrWidth = (document.documentElement.clientWidth - 100 - 25) + "px";
           
            $scope.activityDealDescrWidth = $scope.activityDescrWidth ;

            $FB.init('383626641847356');
            
            var activityActionService = $resource('./activity/action/:action/customer/:customerId/activityId/:activityId', {action: '@action', customerId:'@customerId', activityId:'@activityId' }, {'addAction': {method: 'GET'
                ,headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                    ,'Accept': 'application/json, text/plain, */*'
                }
            }});
            
            var dealActionService = $resource('./deals/action/:action/customer/:customerId/activityId/:activityId/dealId/:dealId/tablePosition/:tablePosition/totalMoney/:totalMoney', {action: '@action', customerId:'@customerId', activityId:'@activityId', dealId: '@dealId' , tablePosition: '@tablePosition' , totalMoney: '@totalMoney' }, {'addAction': {method: 'GET'
                ,headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                    ,'Accept': 'application/json, text/plain, */*'
                }
            }});
            
            //var ActivityRemoveService = $resource('./activity/customer/:customerId/activity/:initId', {customerId:'@customerId', initId:'@initId' }, {'remove': {method: 'DELETE'}});

            $scope.unsubscribeHotelActivityNotifications = function(hotelId, customerId)
            {
                //if anonym
                if(!customerId)
                {
                    var requesterId = _self.hotelState.getRequesterId();

                    var encodeCustomer = _self.hotelService.encodeCustomer(_self.hotelState.profileData);

                    $scope.hotelService.getHotelGuestActionService().addAction({action:"unsubscribeHotelActivityNotifications", hotelId: hotelId, guestCustomerId: requesterId}, encodeCustomer, function(profObj){

                            $scope.mainState.successMsg = "Hotel-Notifications deactivated"

                        },
                        $rootScope.showErrorObj
                    );
                }
                //if logged customer
                else{
                    
                    $scope.hotelState.profileData.allowHotelNotification = false;

                    $rootScope.showLoading(true);

                    $scope.hotelState.submitProfileData(true).then(function(updatedCustomer)
                    {
                        $rootScope.showLoading(false);

                        $rootScope.topPage();
                        $scope.mainState.successMsg = $filter('translate')('alert.success.dataSaved');
                    }, function(error){
                        $scope.mainState.errorMsg = error;
                        $rootScope.showLoading(false);
                    });
                }
               
            }
            
            $scope.submitActivityAction = function(submitActivity, action){

                var customerId = $scope.hotelState.profileData.logged?  $scope.hotelState.profileData.id : -1;
                
                if(customerId<=0)
                {
                    //TODO Eugen: This state can be reached only with valid login! Otherwise open a login popup!!!
                    //save requested state in loginService
                    $scope.loginService.setLoginRequestedState($state.current.name, $stateParams);
                    $scope.openModal("login");
                    return;
                }
                
                $rootScope.showLoading(true);
                
                var activityInitId =  submitActivity? submitActivity.initId : -1;
                
                activityActionService.addAction({action: action, customerId: customerId, activityId: activityInitId},
                    function(updatedActivity)
                    {
                        if(updatedActivity && updatedActivity.initId && updatedActivity.initId>0)
                        {
                            var decodedActivity = $scope.hotelService.decodeActivity(updatedActivity);

                            if($scope.showDetailActivity && $scope.showDetailActivity.initId == decodedActivity.initId)
                            {
                                $scope.showDetailActivity = decodedActivity;
                            }
                            
                            if($scope.showActivity && $scope.showActivity.initId == decodedActivity.initId)
                            {
                                $scope.showActivity = decodedActivity;
                            }

                            $scope.addUpdateActivity(decodedActivity);
                        }
                        $rootScope.showLoading(false);
                    }
                    ,
                    $rootScope.showErrorObj
                );
            }; 
            
            $scope.submitDealAction = function(submitObj, action){
                
                var customerId = $scope.hotelState.getRequesterId();
                
                $rootScope.showLoading(true);
                
                var tempDialId = -1;
                var tempActivityId = -1;
                
                //TODO Eugen: why not submitObj.dtoType????
                if(submitObj.dtoType=="deal")
                {
                    tempDialId = submitObj.id? submitObj.id: submitObj.initId? submitObj.initId : -1;
                    tempActivityId = submitObj.activityId? submitObj.activityId : -1;
                }
                else if(submitObj.dtoType=="activity")
                {
                    tempActivityId = submitObj.id? submitObj.id: submitObj.initId? submitObj.initId : -1;
                }
                
                if(action == "inviteActivity")
                {
                    if(!$scope.hotelState.profileData.logged)
                    {
                        $scope.openModal("login");
                    }
                    else
                    {
                        $scope.openModal(action, tempActivityId);
                    }
                    
                    return;
                }
                
                var tablePosition = submitObj.tablePosition? submitObj.tablePosition : "-";
                var totalMoney = submitObj.totalMoney? submitObj.totalMoney : "0";
                
                dealActionService.addAction({action: action, customerId: customerId, activityId: tempActivityId, dealId: tempDialId, tablePosition: tablePosition, totalMoney: totalMoney},
                    function(updatedDeal)
                    {
                        if(updatedDeal.activityId && updatedDeal.activityId>0)
                        {
                            var decodedDeal = $scope.hotelService.decodeDeal(updatedDeal);
                            
                            $scope.showDetailDeal = decodedDeal;

                            if($scope.showDeal && $scope.showDeal.initId == decodedDeal.initId)
                            {
                                $scope.showDeal = decodedDeal;
                            }
                            if(decodedDeal.dealStatus == 'REJECTED' || decodedDeal.dealStatus == 'CLOSED')
                            {
                                $scope.hotelState.customerDeals[decodedDeal.activityId] = null;
                            }
                            else
                            {                            
                                $scope.hotelState.customerDeals[decodedDeal.activityId] = decodedDeal.timeValid? decodedDeal : null;

                                if(!decodedDeal.dealStatus || decodedDeal.dealStatus == 'ACCEPTED' || decodedDeal.dealStatus == 'EXECUTED')
                                {
                                    $scope.openModal('editDeal', decodedDeal.initId);
                                }
                            }
                            
                            $rootScope.showLoading(false);

                            //set in hotelDeals, if there is something
                            if($scope.hotelState.hotelDeals && $scope.hotelState.hotelDeals[decodedDeal.activityId])
                            {
                                $scope.hotelState.hotelDeals[decodedDeal.activityId] = $scope.hotelState.customerDeals[decodedDeal.activityId];
                            }

                            for(var n=0; n<$scope.hotelState.hotelDeals.length; n++)
                            {
                                if($scope.hotelState.hotelDeals[n] && $scope.hotelState.hotelDeals[n].initId == decodedDeal.initId)
                                {
                                    $scope.hotelState.hotelDeals[n]  = decodedDeal;
                                    break;
                                }
                            }
                            
                            $scope.updateLocalFilter();
                        }
                    }
                    ,
                    $rootScope.showErrorObj
                );
            }; 
            
            //};
            
            
            
            $scope.openEditActivity = function(activityId){

                var isActivityEditOpen = $scope.localState.openEditActivities[activityId];

                revertActivity(activityId);
                
                $scope.localState.openEditActivities[activityId] = !(isActivityEditOpen);
            };
            
            function revertActivity(activityId)
            {
                for(var a=0; a<$scope.localState.copyActivities.length; a++)
                {
                    if($scope.localState.copyActivities[a].initId == activityId && $scope.localState.filterActivities[a].initId == activityId)
                    {
                        $scope.localState.filterActivities[a] = angular.copy($scope.localState.copyActivities[a]);
                        break;
                    }
                }
                
              //TODO Eugen: revert
              //  return activity;
            };
            
            $scope.submitActivityChange = function(activity) {

                var activityError = $scope.hotelService.validateActivityInfo(activity);

                if(activityError.length>0)
                {
                    $scope.mainState.errorMsg = activityError;
                    $rootScope.topPage();
                    return;
                }
                
                if(!$scope.accordionStatus.openEditActivities)
                {
                    $scope.accordionStatus.openEditActivities = [];
                }
                
                if(!$scope.accordionStatus.openEditActivities[activity.initId] && !$scope.accordionStatus.openEditActivity)
                {
                    $scope.accordionStatus.openEditActivities[activity.initId]=true;
                    $scope.accordionStatus.openEditActivity = true;
                    return;
                }
                
                $scope.mainState.successMsg = false;
                $scope.mainState.errorMsg = false;
            
                var editActivity = angular.copy(activity);

                //revert activity
                activity = revertActivity(activity);
                
                var activityError = $scope.hotelService.validateActivityInfo(editActivity);
            
                if(activityError && activityError.length>0)
                {
                    $scope.mainState.errorMsg = activityError;
                    $rootScope.topPage();
                    return;
                }

                editActivity.hotelId = $scope.hotelState.currentHotel.id;
                editActivity.senderId = $scope.hotelState.profileData.id;
            
                //Encode separatly, not display encoding bindings
                //var encodedActivity = $scope.hotelService.encodeActivity(editActivity);
            
                var encodedActivity = $scope.hotelService.encodeActivity(editActivity);
                
                //TODO: REST!!!!
                //ActivityService.send(encodedActivity, encodedActivity.activityId);

                HotelActivityDto.save({customerId: encodedActivity.senderId, activityId: encodedActivity.initId}, encodedActivity, 
                    function(resOk){

                        //function onActivityUpdateSuccess(editActivity)
                        //{
                            $scope.uploadNewImage("activity", editActivity.initId).then(function(resOk){
                                updateActivityLogo(editActivity);
                            });

                            var decodedActivity = $scope.hotelService.decodeActivity(resOk);


                            if($scope.showActivity && $scope.showActivity.initId == decodedActivity.initId)
                            {
                                $scope.showActivity = decodedActivity;
                            }
                        
                            $scope.addUpdateActivity(decodedActivity);
                        
                            $scope.mainState.successMsg = $filter('translate')('alert.success.activityUpdated');

                            $scope.accordionStatus.openEditActivities[editActivity.initId] = false;

                            $rootScope.closeModal();
                        //}
                        
                    },
                    $rootScope.showErrorObj
                );
            
                function updateActivityLogo(editActivity)
                {
                    var requesterId = $scope.hotelState.profileData.id;

                    $scope.hotelService.getEntityLogo(requesterId, "activity", editActivity.initId).then(function(imageJson){

                        if(imageJson)
                        {
                            //var split = imageUrl.split("|");

                            //if(split.length>2)
                            {
                                var entity = imageJson.entityType;
                                var entityId = imageJson.entityId;
                                var entityPictureUrl = imageJson.pictureUrl;
                                
                                if($scope.showActivity && $scope.showActivity.id == entityId && entityPictureUrl)
                                    $scope.showActivity.pictureUrl = entityPictureUrl;

                                if(editActivity && editActivity.id == entityId && entityPictureUrl)
                                    editActivity.pictureUrl = entityPictureUrl;
                            }

                        }
                    })
                }
                
                //$scope.myWait(500);
                //
                //$scope.uploadNewImage("activity", editActivity.activityId);

                //$scope.localState.editActivityMap[activityId] = $scope.hotelService.getInitActivity();
                
            } ;
            
            $scope.removeActivity = function(activityId, ev) {

                {

                    $scope.topPage();
                    
                    var activityRemoveConfirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        })
                        .title($filter('translate')('page.activities.sureRemoveHeader'))
                        .content($filter('translate')('page.activities.sureRemove'))
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        .cancel($filter('translate')('system.cancel'))
                        .targetEvent(ev);

                    $mdDialog.show(activityRemoveConfirm).then(function() {

                        //######## OK BUTTON ##############

                        HotelActivityDto.remove({customerId: $scope.hotelState.profileData.id, activityId: activityId}, function(response){
                            response.$promise.then(function(value){

                                $scope.showActivity = undefined;
                                $scope.localState.showActivity = undefined;

                                for(var a=0; a<$scope.hotelState.hotelActivities.length; a++)
                                {
                                    if($scope.hotelState.hotelActivities[a].initId == activityId)
                                    {
                                        $scope.hotelState.hotelActivities.splice(a, 1);
                                        $scope.mainState.successMsg = $filter('translate')('alert.success.activityRemoved');

                                        break;
                                    }
                                }

                                for(var a=0; a<$scope.hotelState.allActivities.length; a++)
                                {
                                    if($scope.hotelState.allActivities[a].initId == activityId)
                                    {
                                        $scope.hotelState.allActivities.splice(a, 1);
                                        $scope.mainState.successMsg = $filter('translate')('alert.success.activityRemoved');

                                        break;
                                    }
                                }


                            });
                        });

                        // ######## END OK BUTTON #############


                    }, function() {
                        //$scope.mainState.showCheckinWellcomePopup = false;
                    });
                }
            };

            //################# SERVICE LISTENER //////////////////////////////////////////////////////
            
            
            //$rootScope.$on('checkActivitySocketConnection', function(event, mass) {
            //    ActivityService.checkSocketConnection();
            //});

            NotificationService.receiveActivity().then(null, null, function(activity) {

                if( $scope.hotelState.currentHotel
                    &&
                    $scope.hotelState.currentHotel.id == activity.hotelId)
                {

                    var decodedActivity = $scope.hotelService.decodeActivity(activity);

                    if($scope.showDetailActivity && $scope.showDetailActivity.initId == decodedActivity.initId)
                    {
                        $scope.showDetailActivity = decodedActivity;
                    }

                    $scope.addUpdateActivity(decodedActivity);
                }
            });
            
            function initActivities(){

                
                
                //$scope.localState.showCityLoading= false;
                $scope.localState.openActivities= [],
                $scope.localState.openEditActivities= [], 
                $scope.localState.newactivity = $scope.hotelService.getInitActivity();

                $scope.localState.maxActivityShortDescription = $scope.hotelService.validationUtils.maxActivityShortDescription;
                $scope.localState.maxActivityDescription =  $scope.hotelService.validationUtils.maxActivityDescription;
                $scope.localState.maxHotelDescription = $scope.hotelService.validationUtils.maxHotelDescription;
                
                
                $scope.localState.maxActivityShortDescription = $scope.hotelService.validationUtils.maxActivityShortDescription;
                $scope.localState.maxActivityDescription = $scope.hotelService.validationUtils.maxActivityDescription;
                $scope.localState.editHotelAccordionTitle = $filter('translate')('page.hotel.updateHotelInfo');
                $scope.localState.editHotelAccordionIconClass = "fa fa-pencil-square-o";

                $scope.localState.editActivityAccordionTitle = $filter('translate')('page.activities.addActivity'),
                $scope.localState.editActivityAccordionIconClass = "fa fa-plus";
            };            
            
            initActivities();
        }
    ]
);