'use strict';
//app
angular.module('hotelApp.hotelCtrl', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', 'hotelApp.activityDto'])

    //Eugen: the container controller has to have $scope.updateLocalFilter(); $scope.emptyPreviousSearch(); and 'localState.hotelCitySelectorUpdator' and localState.disableAllHotelsInCity

    .controller('hotelCtrl', ['$scope', '$rootScope', '$stateParams', 'HotelDto', '$state', '$resource', '$filter', '$mdDialog',
        function($scope, $rootScope, $stateParams, HotelDto, $state, $resource, $filter, $mdDialog) {

            $scope.submitHotelChange = function(hotelObj)
            {
                $scope.mainState.successMsg = false;
                $scope.mainState.errorMsg = false;

                var hotelError = $scope.hotelService.validateHotelInfo(hotelObj);

                if(hotelError.length>0)
                {
                    $scope.mainState.errorMsg = hotelError;
                    $rootScope.topPage();
                    return;
                }
                
                //we now handle with editHotel!!!!
                var editHotel = angular.copy(hotelObj);

                //revert activity
                hotelObj = revertHotel(hotelObj);
                
                if(editHotel.id>0) //upload image first, if hotel exists in db!
                {
                    $scope.uploadNewImage("hotel", editHotel.creationTime).then(function(res){
                        updateHotelLogo(editHotel);
                    });
                    $rootScope.myWait(500);
                }

                $scope.hotelState.submitHotelData(editHotel).then(function(changedHotel){

                    if(editHotel.id<0) //upload image then, if hotel created in db!
                    {
                        $rootScope.myWait(500);
                        $scope.uploadNewImage("hotel", editHotel.creationTime).then(function(res){
                            updateHotelLogo(editHotel);
                        });
                        $rootScope.myWait(500);
                    }
                    
                    $scope.mainState.successMsg =  $filter('translate')('alert.success.hotelUpdated');

                    if($scope.accordionStatus.openEditHotels)
                        $scope.accordionStatus.openEditHotels[changedHotel.id] = false;
                    
                    if($scope.accordionStatus.editHotelOpen)
                        $scope.accordionStatus.editHotelOpen = false;
                    //TODO Eugen: ???
                    //$scope.accordionStatus.descriptionHotelOpen = false;

                    editHotel = changedHotel;
                    if($state.current.name=="app.hotel" || $state.current.name=="app.hotelRegister" || $state.current.name=="app.editHotel")
                    {
                        $scope.showHotel = changedHotel;
                    }
                    
                    $scope.hotelState.currentHotel = changedHotel;

                    $scope.updateLocalFilter();
                    
                    $rootScope.topPage();

                    $rootScope.$broadcast('hotelUpdated', $scope.showHotel);
                    
                    $rootScope.closeModal();
                    
                },  
                $rootScope.showErrorObj);

            };
            
            function updateHotelLogo(editHotel)
            {
                var requesterId = $scope.hotelState.profileData.id;

                $scope.hotelService.getEntityLogo(requesterId, "hotel", editHotel.id).then(function(imageJson){

                    if(imageJson)
                    {
                        //if(split.length>2)
                        {
                            var entity = imageJson.entityType;
                            var entityId = imageJson.entityId;
                            var entityPictureUrl = imageJson.pictureUrl;

                            if($scope.showHotel && $scope.showHotel.id == entityId && entityPictureUrl)
                                $scope.showHotel.pictureUrl = entityPictureUrl;

                            if(editHotel && editHotel.id == entityId && entityPictureUrl)
                                editHotel.pictureUrl = entityPictureUrl;

                            if($state.current.name=="app.hotelRegister")
                            {
                                $scope.hotelState.tempCreationHotel = $scope.showHotel;
                            }

                            $rootScope.$broadcast('hotelUpdated', $scope.showHotel);

                        }

                    }
                })
            }
            
            function revertHotel(hotelObj)
            {
                for(var a=0; a<$scope.localState.copyHotels.length; a++)
                {
                    if($scope.localState.copyHotels[a].id == hotelObj.id && $scope.localState.filterHotels[a].id == hotelObj.id)
                    {
                        $scope.localState.filterHotels[a] = angular.copy($scope.localState.copyHotels[a]);
                        //hotelObj = $scope.localState.filterHotels[a];
                        if($state.current.name == "app.hotel" || $state.current.name == "app.editHotel")
                        {
                            $scope.showHotel = angular.copy($scope.localState.copyHotels[a]);
                            return $scope.showHotel;
                        }
                        break;
                    }
                }
               
                return hotelObj;
                
                //TODO Eugen: revert
                //  return activity;
            };
            
            $scope.openHotel = function(hotelObj){

                if(!$scope.localState.openHotels)
                {
                    $scope.localState.openHotels=[];
                }
                
                var isHotelOpen = $scope.localState.openHotels[hotelObj.id];

                $scope.localState.openActivities[hotelObj.id] = !(isHotelOpen);
            };

            $scope.openEditHotel = function(hotelObj){

                if(!$scope.localState.openEditHotels)
                {
                    $scope.localState.openEditHotels=[];
                }
                
                var isHotelEditOpen = $scope.localState.openEditHotels[hotelObj.id];
                
                if(isHotelEditOpen==undefined)
                {
                    isHotelEditOpen = false;
                }

                if(isHotelEditOpen)
                {
                    revertHotel(hotelObj);
                }

                $scope.localState.openEditHotels[hotelObj.id] = !(isHotelEditOpen);
            };

            var HotelRemoveService = $resource('./hotels/customer/:customerId/hotel/:hotelId', {customerId:'@customerId', hotelId:'@hotelId' }, {'remove': {method: 'DELETE'}});

            $scope.removeHotel = function(hotelId, ev) {


                {
                    var hotelRemoveConfirm = $mdDialog.confirm(
                        {
                            clickOutsideToClose: true
                        })
                        .title($filter('translate')('page.hotel.sureRemoveHeader'))
                        .content($filter('translate')('page.hotel.sureRemove'))
                        .ariaLabel('Lucky day')
                        .ok('OK')
                        .cancel($filter('translate')('system.cancel'))
                        .targetEvent(ev);

                    $mdDialog.show(hotelRemoveConfirm).then(function() {

                        //######## OK BUTTON ##############

                        HotelRemoveService.remove({customerId: $scope.hotelState.profileData.id, hotelId: hotelId}, function(response){
                            response.$promise.then(function(value){

                                //$scope.showActivity = undefined;
                                //$scope.localState.showActivity = undefined;

                                if($scope.showHotel && $scope.showHotel.id == hotelId)
                                {
                                    $scope.showHotel = undefined;
                                }

                                for(var a=0; a<$scope.mainState.hotelArray.length; a++)
                                {
                                    if($scope.mainState.hotelArray[a].id == hotelId)
                                    {
                                        $scope.mainState.hotelArray.splice(a, 1);
                                        $scope.mainState.successMsg = $filter('translate')('alert.success.hotelRemoved');

                                        break;
                                    }
                                }

                                for(var a=0; a<$scope.hotelState.allActivities.length; a++)
                                {
                                    if($scope.hotelState.allActivities[a].hotelId == hotelId)
                                    {
                                        $scope.hotelState.allActivities.splice(a, 1);
                                        //$scope.mainState.successMsg = $filter('translate')('alert.success.activityRemoved');

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

            function initHotelCtrl(){
                
                //if(!$scope.localState)
                //{
                //    $scope.localState = {}
                //}
                
                $scope.localState.editHotel = null;
                $scope.localState.maxHotelDescription = $scope.hotelService.validationUtils.maxHotelDescription;
            }

            initHotelCtrl();
        }
    ]
);