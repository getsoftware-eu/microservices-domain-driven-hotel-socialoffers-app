'use strict';
app
//angular.module('hotelApp.hotel', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto'])

    .controller('DealListController', ['$scope', '$rootScope', '$location', '$resource', '$stateParams', 'HotelDto', 'HotelActivityDto', '$window', '$state', '$filter', '$mdDialog',
        function($scope, $rootScope, $location, $resource, $stateParams, HotelDto, HotelActivityDto, $window, $state, $filter, $mdDialog) {

            $scope.localState = {
                ready : 0,
                
                showActivityHotelLink:true, //Show Hotel Link in activities!!!
                //showPeFilteredHotelBlock:false, //Show selected Hotel Block!!!
                //showHotelCityFilterBlock:false, //Show selected Hotel Block!!!
                
                selectedFilterHotel : undefined,
                selectedCityHotel : undefined,

                dealsLoading: true,

                noDealFound: false,
                noMenuFound: false,
                searchFilter : {},
                searchMenuFilter : {},
                strictSearch : true,
                
                filterDealCode : "",

                showClosed: false,
                
                staffView : false,
                
                filterDeals : [],
                filterMenus : []
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

            //var HotelDealsService = $resource('./deals/deals/customer/:customerId/activity/:activityId/hotel/:hotelId/limitByRequester/:limitByRequester', {customerId:'@customerId', activityId:'@activityId', hotelId:'@hotelId', limitByRequester:'@limitByRequester'}, {'getDeals': {method: 'GET', isArray: true}});

            //################ filter Cities and Hotels #########################

            $scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch){


                if(onlySetIfEmpty && $scope.localState.filterDeals && $scope.localState.filterDeals.length>0)
                {
                    return;
                }
                //var subSetToSearch = null;
                
                var sourceDeals = $scope.localState.staffView ? $scope.hotelState.hotelDeals : $scope.hotelState.customerDeals;

                $scope.localState.searchFilter.active = true;
                $scope.localState.searchMenuFilter.active = true;

                $scope.localState.searchFilter.closed = $scope.localState.showClosed;
                $scope.localState.searchMenuFilter.closed = $scope.localState.showClosed;
                
                //FILTER BY DEAL_CODE!!!
                if($scope.localState.filterDealCode && $scope.localState.filterDealCode>0){
                    $scope.localState.searchFilter.dealCode = $scope.localState.filterDealCode;
                    $scope.localState.searchMenuFilter.orderCode = $scope.localState.filterDealCode;
                } 
                if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0){
                    $scope.localState.searchFilter.hotelId = $scope.localState.selectedFilterHotel.id;
                    $scope.localState.searchMenuFilter.hotelId = $scope.localState.selectedFilterHotel.id;
                }
                //else if($scope.mainState.selectedCity != "-1" && $scope.mainState.selectedCity != undefined && $scope.mainState.selectedCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase())
                //{
                //    $scope.localState.searchFilter.hotelCity = $scope.mainState.selectedCity;
                //}
                
                //Eugen: Check if it is activityList, not hotelList
                if(!subSetToSearch || subSetToSearch.length==0 || subSetToSearch[0].dtoType!="activityId")
                {
                    subSetToSearch = sourceDeals;
                }
                
                $scope.localState.filterDeals = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, false);//, $scope.localState.strictSearch);
                
                $scope.localState.filterMenus = $filter('filter')($scope.hotelState.hotelMenuOrders, $scope.localState.searchMenuFilter, false);//, $scope.localState.strictSearch);

                $scope.localState.noDealFound = $scope.localState.filterDeals.length==0 && sourceDeals.length>=0;
                $scope.localState.noMenuFound = $scope.localState.filterMenus.length==0 && $scope.hotelState.hotelMenuOrders.length>=0;
                
                if($scope.localState.noDealFound || $scope.localState.filterDeals.length>0)
                {
                    $scope.localState.dealsLoading = false;
                }
                
                if($scope.localState.noMenuFound || $scope.localState.filterMenus.length>0)
                {
                    $scope.localState.menusLoading = false;
                }
                
                $scope.localState.copyDeals = angular.copy($scope.localState.filterDeals);


            }
            
            

            $scope.addUpdateDeal = function(newDeal)
            {
                //var dealUpdated = false;
                //
                //if(newDeal.activityId>0)
                //{
                //    //for(var a=0; a<$scope.hotelState.customerDeals.length; a++)
                //    {
                //        if($scope.hotelState.customerDeals[newDeal.activityId])
                //        {
                //            $scope.hotelState.customerDeals[a] = newDeal;
                //            dealUpdated = true;
                //            //break;
                //        }
                //    }
                //}
                //
                //if(!dealUpdated)
                {
                    $scope.hotelState.customerDeals[newDeal.activityId] = newDeal;
                }

                $scope.updateLocalFilter();
            };

            // #################### POPUP BLOCK #################

            $scope.showPopup = function(ev) {

                //if($scope.mainState.hidePopup || !$scope.hotelState.profileData)
                //{
                //    return;
                //}
                //
                //if($state.current.name == "app.dealList")
                //{
                // 
                //    if($scope.hotelState.profileData.hideHotelListPopup || $scope.hotelService.getCookie('hotelicohideHotelListPopup')=="true")
                //    {
                //        return;
                //    }
                //    
                //    var confirm = $mdDialog.confirm(
                //        {
                //            clickOutsideToClose: true
                //        })
                //        .title($filter('translate')('page.activities.popupHeader'))
                //        .content($filter('translate')('page.activities.popupText'))
                //        .ariaLabel('Lucky day')
                //        .ok('OK')
                //        .cancel($filter('translate')('alert.noPopupMore'))
                //        .targetEvent(ev);
                //
                //    $mdDialog.show(confirm).then(function() {
                //
                //    }, function() {
                //        $scope.hotelState.profileData.hideHotelListPopup = true;
                //        $scope.hotelService.setCookie('hotelicohideHotelListPopup', true);
                //
                //        $scope.hotelState.submitProfileData(true);
                //    });
                //}
                
            };


            //##################### WATCHERS ####################
            $rootScope.$on('dealListUpdate', function(event, mass) {
                $scope.updateLocalFilter();
            });  
            
            $rootScope.$on('dealListServerUpdate', function(event, mass) {
                $scope.getUpdateModuleData(true);
            });
            
            $scope.$watch('mainState.globalIntervalCheck', function() {

                if($state.current.name == "app.dealList" )
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
            
            $scope.getUpdateAllCustomerDeals = function(forceUpdate)
            {
                //load {All} Activities
                //if(!$scope.hotelState.customerDeals || $scope.hotelState.customerDeals.length==0 || forceUpdate)
                {
                    var requesterId = $scope.hotelState.getRequesterId();

                    //if(!showClosed)
                    //{
                    //    showClosed = false;
                    //}                    
                    $scope.hotelService.getHotelDealActionService().getDeals({customerId: requesterId, activityId: -1, hotelId: -1, limitByRequester: true, showClosed: $scope.localState.showClosed}, function(responseArray){

                        var dealList = $scope.decodeDealList(responseArray);
                       
                        var myDealsNumber = 0;

                        for(var d=0; d<dealList.length; d++)
                        {
                            var nextDeal = dealList[d];
                            $scope.hotelState.customerDeals[nextDeal.activityId] = nextDeal;
                            
                            if(nextDeal.dealStatus=='ACCEPTED')
                            {
                                myDealsNumber++; 
                            }
                        }
                        
                        if($scope.hotelNotification.notificationObj) 
                        {
                            $scope.hotelNotification.notificationObj.myDealsNumber = myDealsNumber;
                        }
                        //Eugen: filterCitySelector Watcher!!!
                        //$scope.localState.hotelCitySelectorUpdator++;
                        $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                        //$scope.onChangeHotelCitySelector();
                        $scope.updateLocalFilter();
                        $scope.showLoading(false);
                        
                        if(!$scope.localState.staffView)
                        {
                            $scope.localState.dataLoaded = true;
                        }

                    });

                }
                //else{
                //    
                //    //Eugen: filterCitySelector Watcher!!!
                //    //$scope.localState.hotelCitySelectorUpdator++;
                //}
            };
            
            $scope.getUpdateAllHotelDeals = function(forceUpdate)
            {
                
                //load {All} Activities
                //if(!$scope.hotelState.hotelDeals || $scope.hotelState.hotelDeals.length==0 || forceUpdate)
                {
                    var requesterId = $scope.hotelState.getRequesterId();
                    
                    var hotelId = $scope.hotelState.profileData.hotelId>0? $scope.hotelState.profileData.hotelId : -1;
                    
                    var activityId = $stateParams.filterActivityId? $stateParams.filterActivityId : -1;

                    //if(!showClosed)
                    //{
                    //    showClosed = false;
                    //}                    
                    $scope.hotelService.getHotelDealActionService().getDeals({customerId: requesterId, activityId: activityId, hotelId: hotelId, limitByRequester:false, showClosed: $scope.localState.showClosed}, function(responseArray){

                        var dealList = $scope.decodeDealList(responseArray);

                        //$scope.hotelState.hotelDeals = [];
                        
                        for(var d=0; d<dealList.length; d++)
                        {
                            var nextDeal = dealList[d];
                            
                            var dealUpdated = false;
                            
                            for(var n=0; n<$scope.hotelState.hotelDeals.length; n++)
                            {
                               if($scope.hotelState.hotelDeals[n] && $scope.hotelState.hotelDeals[n].initId == nextDeal.initId)
                               {
                                   $scope.hotelState.hotelDeals[n].dealStatus = nextDeal.dealStatus;
                                   $scope.hotelState.hotelDeals[n].closed = nextDeal.closed;

                                   dealUpdated = true;
                               }
                            }
                            
                            if(!dealUpdated)
                            {
                                $scope.hotelState.hotelDeals.push(nextDeal);
                            }
                        }
                        
                        //Eugen: filterCitySelector Watcher!!!
                        if(!$scope.hotelState.profileData.checkedIn)
                        {
                            $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                        }
                        
                        $scope.showLoading(false);

                        $scope.localState.dataLoaded = true;

                    });

                }
                //else{
                //    
                //    //Eugen: filterCitySelector Watcher!!!
                //    //$scope.localState.hotelCitySelectorUpdator++;
                //}
            };

            $scope.addUpdateMenuOrder= function(menuOrder)
            {
                if(!$scope.hotelState.hotelMenuOrders)
                {
                    $scope.hotelState.hotelMenuOrders = [];
                }

                var menuUpdated = false;

                for(var n=0; n<$scope.hotelState.hotelMenuOrders.length; n++)
                {
                    if($scope.hotelState.hotelMenuOrders[n].initId == menuOrder.initId)
                    {
                        $scope.hotelState.hotelMenuOrders[n].orderStatus = menuOrder.orderStatus;
                        $scope.hotelState.hotelMenuOrders[n].closed = menuOrder.closed;

                        menuUpdated = true;
                    }
                }

                if(!menuUpdated)
                {
                    $scope.hotelState.hotelMenuOrders.push(menuOrder);
                }

                $scope.updateLocalFilter();
            }
            
            
            $scope.getUpdateAllHotelMenus = function(forceUpdate)
            {
                
                //load {All} Activities
                //if(!$scope.hotelState.hotelDeals || $scope.hotelState.hotelDeals.length==0 || forceUpdate)
                {
                    var requesterId = $scope.hotelState.getRequesterId();
                    
                    var hotelId = $scope.hotelState.profileData.hotelId>0? $scope.hotelState.profileData.hotelId : -1;
                    
                    var cafeId = 0;

                    //if(!showClosed)
                    //{
                    //    showClosed = false;
                    //}
                    
                    $scope.hotelService.getMenuOrderService().getMenu({customerId: requesterId, hotelId: hotelId, cafeId: cafeId, orderId: -1, showClosed: $scope.localState.showClosed}, function(respArray)
                        {

                        var menuList = /*$scope.decodeMenulList*/(respArray);

                        //$scope.hotelNotification.notificationObj.myDealsNumber = dealList.length;
                        //$scope.hotelState.hotelMenuOrders = [];
                        
                        for(var d=0; d<menuList.length; d++)
                        {
                            var nextMenu = menuList[d];

                            $scope.hotelState.addUpdateMenuOrder(nextMenu)
                            $scope.updateLocalFilter();

                        }
                        
                        //Eugen: filterCitySelector Watcher!!!
                        //$scope.localState.hotelCitySelectorUpdator++;
                        //$rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
                        //$scope.onChangeHotelCitySelector();
                        
                        $scope.showLoading(false);

                        $scope.localState.menuLoaded = true;

                    });

                }
                //else{
                //    
                //    //Eugen: filterCitySelector Watcher!!!
                //    //$scope.localState.hotelCitySelectorUpdator++;
                //}
            };

            ///######################## INIT BLOCK ##############

            $scope.getUpdateModuleData = function(forceUpdate)
            {                
                if(forceUpdate)
                {
                    $scope.showLoading(true);
                }

                $scope.getUpdateAllCustomerDeals(forceUpdate, false);

                $scope.localState.staffView = $stateParams.staffScope && $stateParams.staffScope=='true';
            
                if($scope.localState.staffView)
                {
                    $scope.getUpdateAllHotelDeals(forceUpdate, false);
                    
                    $scope.getUpdateAllHotelMenus(forceUpdate, false);
                }
                    
             };

            
            function setDealCountDown()
            {
                // variables for time units
                var days, hours, minutes, seconds;

                var countdowns = document.getElementsByClassName("countdown_deal");

                for (var i = 0; i < countdowns.length; i++) {

                    var split = countdowns[i].id.split("_");

                    if(split.length>3)
                    {
                        var activityId = split[2];
                        var dealId = split[3];

                        var target_deal = $scope.hotelState.customerDeals[activityId];

                        var target_date = target_deal? target_deal.validTo : null;

                        var target_countdown = document.getElementById('countdown_deal_' + activityId + '_' + dealId);

                        if (target_date && target_countdown) {
                            var current_date = new Date().getTime();
                            // find the amount of "seconds" between now and target

                            //var target_date = null;

                            //var timeValues = target_date_elt.innerHTML.split(",");
                            //if(timeValues.length > 4)
                            //{
                            //    target_date = new Date(timeValues[0], timeValues[1], timeValues[2], timeValues[3], timeValues[4]).getTime();
                            //}

                            if(!target_date)// || !target_deal.lastMinute)
                            {
                                continue;
                            }

                            var seconds_left = (target_date - current_date) / 1000;

                            // do some time calculations
                            days = parseInt(seconds_left / 86400);
                            seconds_left = seconds_left % 86400;

                            hours = parseInt(seconds_left / 3600);
                            seconds_left = seconds_left % 3600;

                            minutes = parseInt(seconds_left / 60);
                            seconds = parseInt(seconds_left % 60);

                            if (days > 0) {
                                target_countdown.innerHTML = $filter('translate')('system.expiresIn') + ": " + days + " " + (days>1?$filter('translate')('system.days'):$filter('translate')('system.day'));
                            }
                            // format countdown string + set tag value
                            if (days == 0 && seconds >= 0) {
                                target_countdown.innerHTML = "<b>"+ $filter('translate')('system.expiresIn') + ": " + hours + "h, "
                                + minutes + "m, " + seconds + "s</b>";
                            }
                            else if (days == 0 && seconds < 0) {
                                target_countdown.innerHTML = "(" + "expired!" + ")";
                            }
                        }
                    }


                }
            }
            
            function initDealList(){

                $scope.localState.showClosed = $stateParams.closed=='true';

                $scope.showPopup();
                $scope.getUpdateModuleData();
                $scope.updateLocalFilter();
                $scope.hotelState.checkHeaderTab();

                var t = setInterval(setDealCountDown, 1000);
            };

            initDealList();
            
        }
    ]
)
;