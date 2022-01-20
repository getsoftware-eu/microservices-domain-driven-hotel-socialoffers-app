'use strict';
//app
angular.module('hotelApp.hotel', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', 'hotelApp.activityDto'])

    .controller('HotelController', ['$scope', '$rootScope', '$location', '$resource', '$stateParams', 'HotelDto', '$window', '$state', '$filter', '$mdDialog',
    function($scope, $rootScope, $location, $resource, $stateParams, HotelDto, $window, $state, $filter, $mdDialog) {
        
        var editHotel = true;
        
        $scope.localState = {
            
            ready : 0,
            //editActivity : $scope.hotelService.getInitActivity(),

            hotelBlockInternActivities: true,
            //,
            //showCityLoading: false
            //editActivityMap : []
            //FOR future: filter local hotel activities
            //searchFilter : {},
            //strictSearch : true,
            //filterActivities : []
            // editHotelAccordionIconClass : 'fa fa-pencil-square-o'
            //hotelScope: true

            activitiesLoaded : false
        };

        $scope.accordionStatus = {
            
            editHotelOpen: false,
            isEditHotelDisabled: false,

            infoHotelOpen: false,
            isInfoHotelDisabled: false,
            
            descriptionHotelOpen: false,
            isDescriptionHotelDisabled: false

            //newActivityOpen: false,
            //isNewActivityDisabled: false
        };

        var HotelActivitiesService = $resource('./activity/activities/customer/:customerId/hotel/:hotelId', {customerId:'@customerId', hotelId:'@hotelId'}, {'getActivities': {method: 'GET', isArray: true}});
        //var ActivityRemoveService = $resource('./activity/customer/:customerId/activity/:activityId', {customerId:'@customerId', activityId:'@activityId' }, {'remove': {method: 'GET'}});

        ////######################## HOTEL LIST -> UPDATE HOTEL ######################
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
        
        $rootScope.$on('activityListUpdate', function(event, mass) {

            $scope.getUpdateCurHotelActivityList(true);
            
            $scope.updateLocalFilter();
        });
        
        $scope.updateLocalFilter = function(onlySetIfEmpty){

            //TODO EUGEN: for every auto update!!!
            if(onlySetIfEmpty && $scope.localState.filterActivities && $scope.localState.filterActivities.length>0)
            {
                return;
            }
            
            $scope.localState.filterActivities = $filter('filter')($scope.hotelState.hotelActivities, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);
            $scope.localState.filterHotels = [$scope.hotelState.currentHotel];

            
            
            $scope.localState.copyActivities = angular.copy($scope.localState.filterActivities);
            $scope.localState.copyHotels = angular.copy($scope.localState.filterHotels);


            $scope.localState.activitiesLoaded = true;
            
            //get copies only for staff and admin!
            //if($scope.hotelState.profileData.admin || $scope.hotelState.profileData.hotelStaff && $scope.hotelState.currentHotel && $scope.hotelState.profileData.hotelId == $scope.hotelState.currentHotel.id)
            //{
            //    for(var a=0; a<$scope.localState.filterActivities.length; a++)
            //    {
            //        $scope.localState.editActivityMap[$scope.localState.filterActivities[a].activityId] = angular.copy($scope.localState.filterActivities[a]);
            //    } 
            //}
            
        };

        // #################### POPUP BLOCK #################

        //$scope.onHotelCodeClick = function()
        //{
        //    $scope.openModal("checkin");
        //
        //}
        
        
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
            
    //        if($scope.hotelState.profileData.hideHotelListPopup)
    //        {
    //            return;
    //        }
    //    
    //        var confirm = $mdDialog.confirm( {
    //    clickOutsideToClose: true
    //})
    //            .title('Planst Du einen Hotelaufenthalt?')
    //            .content('Hier siehst Du, was in anderen Städten und Hotels läuft. Verabrede dich mit Hotelgästen schon vor der Anreise im Hotel oder lasse Dir von denen Tipps zum Aufenthalt geben')
    //            .ariaLabel('Lucky day')
    //            .ok('OK')
    //            .cancel('Nicht mehr zeigen')
    //            .targetEvent(ev);
    //    
    //        $mdDialog.show(confirm).then(function() {
    //            $scope.hotelState.profileData.hideHotelListPopup = true;
    //            $scope.hotelService.setCookie('hotelicohideHotelListPopup', true);
    //
    //            $scope.hotelState.submitProfileData(true);
    //        }, function() {
    //            //$scope.hotelState.profileData.hideHotelListPopup = true;
    //        });
        };
        
        
        //##################### WATCHERS ####################

        $scope.$watch('mainState.globalIntervalCheck', function() {
           
            if($state.current.name == "app.hotel" )
            {
                if($scope.hotelState.profileData)
                {
                    $scope.getUpdateModuleData();
                }
                else {
                    $scope.hotelState.initState().then(function(reponse){
                        $scope.getUpdateModuleData();
                    });
                }
            }
            
        });

        /// ###################### GET-UPDATE BLOCK ////////////////////////

        /**
         * Copy Object for edit and show hotel-object
         * @param rewriteIfExists
         * @param curHotelObj
         */
        function setCurrentHotelAlias(rewriteIfExists, curHotelObj)
        {
            if(!curHotelObj)
            {
                curHotelObj = $scope.hotelState.currentHotel;
            }
            
            if(!$scope.localState.editHotel || rewriteIfExists)
            {
                $scope.localState.editHotel = curHotelObj;
            }
            
            if(!$scope.showHotel || rewriteIfExists)
            {
                $scope.showHotel = curHotelObj;
            }

            if(editHotel)
            {
                $scope.localState.editHotelAccordionTitle =  "'" + $scope.showHotel.name + "' " + $filter('translate')('system.edit');
                $scope.localState.showHotelBlockFullInfo = true;
                $scope.accordionStatus.editHotelOpen = true;
            }
        }
        
        /**
         * load currentHotel only it is not exists
         * @param customerObject
         */
        $scope.getUpdateCurHotel = function(forceUpdate)
        {
            var customerObject = $scope.hotelState.profileData;
            
            if(customerObject.id && customerObject.id>0)
            {
                if(customerObject.hotelId && customerObject.hotelId>0 || $stateParams.hotelId && customerObject.admin)
                {
                    $scope.localState.hotelId =  $stateParams.hotelId && customerObject.admin? $stateParams.hotelId : customerObject.hotelId;

                    //Noch kein currentHotel oder wir brauchen ein anderes als Admin
                    if(!$scope.hotelState.currentHotel || $scope.hotelState.currentHotel.id != $scope.localState.hotelId || forceUpdate)
                    {
                        $scope.hotelState.getCurrentHotel($scope.localState.hotelId, customerObject, forceUpdate).then(function(hotelResponse){

                            setCurrentHotelAlias(true, $scope.hotelState.currentHotel);
                            $rootScope.showLoading(false);

                        });
                    }
                    else //Current Hotel ist bereits geladen! auch admin virtuell
                    {
                        setCurrentHotelAlias(false, $scope.hotelState.currentHotel);

                        $rootScope.showLoading(false);
                    }

                    $scope.localState.ready = 1;

                }
                else //no hotel -> load virtual hotel
                {

                }
            }

        };

        $scope.getUpdateCurHotelActivityList = function(forceUpdate)
        {
            //if load activities from Server
            if($scope.localState.hotelId && $scope.localState.hotelId>0 && (forceUpdate || $scope.hotelState.hotelActivities.length==0 || !$scope.localState.profileData || $scope.hotelState.hotelActivities[0].hotelId != $scope.localState.profileData.hotelId))
            {
                var requesterId = $scope.hotelState.profileData.id>0? $scope.hotelState.profileData.id : $scope.hotelService.guestCustomerId;
                
                HotelActivitiesService.getActivities({customerId: requesterId, hotelId: $scope.localState.hotelId}, function(responseArray){
                  
                    $scope.hotelState.hotelActivities = $scope.decodeActivityList(responseArray);

                    $scope.updateLocalFilter();
                    
                    $scope.showLoading(false);

                });

            }
            else{ //If we have already all activities local

                $scope.updateLocalFilter(true);

                $rootScope.showLoading(false);
            }
        };
        
        ///######################## INIT BLOCK ##############

        $scope.getUpdateModuleData = function()
        {
            $scope.getUpdateCurHotel();
            $scope.getUpdateCurHotelActivityList();
        };
        
        function initHotel(){
            
            //$scope.localState.showCityLoading = true;
            
            $scope.hotelState.updateState();
            $scope.hotelState.checkNextRouteState();
            
            $rootScope.showLoading(true);

            $scope.getUpdateModuleData();
            $scope.showPopup();
            
            $scope.tryOpenPush();

            
        };
        
        //wait for hotel state
        $scope.hotelState.getDeferredState().then(function(stateObj) {
            
            $scope.initMainState(stateObj);

            //if($rootScope.relocateLogin())
            //{
            //    $scope.loginService.resetRequestedLoginState();
            //    $rootScope.showLoading(false);
            //    return;
            //}
            //else
            {
                initHotel();
            }
        });

        if($stateParams.reloadHotel)
        {
            //$scope.getUpdateCurHotel(true);
            $window.location.reload();
        }
      
    }])
//.directive('progressBar', [
//    function () {
//        return {
//            link: function ($scope, el, attrs) {
//                $scope.$watch(attrs.progressBar, function (newValue) {
//                   // el.css('width', newValue.toString() + '%');
//                });
//            }
//        };
//    }
//])
;