'use strict';
app
//angular.module('hotelApp.hotelList', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto'])

    .controller('HotelListController',
    ['$scope', '$rootScope', '$location', '$resource', '$stateParams' ,'SessionCustomer', 'HotelDto', 'HotelCheckin', '$window', '$state', '$translate', 'editableOptions', 'editableThemes', '$filter', '$mdDialog',
        function($scope, $rootScope, $location, $resource, $stateParams, SessionCustomer, HotelDto, HotelCheckin, $window, $state, $translate, editableOptions, editableThemes, $filter, $mdDialog) {

        editableThemes.bs3.inputClass = 'input-sm';
        editableThemes.bs3.buttonsClass = 'btn-sm';
        editableOptions.theme = 'bs3';

        $scope.localState = {

            ready: 0,
            
            selectedFilterHotel : undefined,
            selectedCityHotel : undefined,
            
            searchFilter : {},
            
            strictSearch: true,
            
            newHotel: $scope.hotelService.getInitHotel(),

            filterHotels : [],
            
            //noHotelFound: false,

            showHotelBlockFullInfo : true,
            
            //hotelCitySelectorUpdator: 0, //watcher down controller

            maxHotelDescription: $scope.hotelService.validationUtils.maxHotelDescription
        };

        $scope.accordionStatus = {
            editHotelOpen: false,
            infoHotelOpen: [],
            descriptionHotelOpen: [],
            newActivityOpen: false,
            isEditHotelDisabled: false,
            isInfoHotelDisabled: false,
            isDescriptionHotelDisabled: false,
            isNewActivityDisabled: false
        };
            
        
        //################ filter Cities and Hotels #########################
        
        $scope.emptyPreviousSearch = function()
        {
            $scope.localState.searchFilter = {};
            $scope.localState.selectedFilterHotel=null;

            $scope.localState.filterHotels = $filter('filter')($scope.mainState.hotelArray, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);

        };
            
        $scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch)
        {            
            
            if(onlySetIfEmpty && $scope.localState.filterHotels && $scope.localState.filterHotels.length>0)
            {
                return;
            }
            
            $scope.localState.searchFilter = {};

            if($scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0)
            {
                $scope.localState.searchFilter.id = $scope.localState.selectedFilterHotel.id;
            }
            else if($scope.mainState.selectedCity != "-1" && $scope.mainState.selectedCity != undefined && $scope.mainState.selectedCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase())
            {
                $scope.localState.searchFilter.city = $scope.mainState.selectedCity;
            }
            
            if(!subSetToSearch)
            {
                subSetToSearch = $scope.mainState.hotelArray;
            }

            $scope.localState.filterHotels = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);

            $scope.localState.noHotelFound = $scope.localState.filterHotels.length==0 && $scope.mainState.hotelArray.length>0;
            
            $scope.localState.copyHotels = angular.copy($scope.localState.filterHotels);


        };
            
        //##################### WATCHERS ####################

        $scope.$on('mainState.globalIntervalCheck', function(next, current) {
           
            if($state.current.name == "app.hotelList")
                 $scope.getUpdateModuleData();

        });
        ///############### INIT BLOCK #######################################

        $scope.getUpdateHotelList = function () {

            $scope.getUpdateMainHotelList().then(function(responseList){

                for (var h = 0; h < responseList.length; h++) {
                    $scope.accordionStatus.infoHotelOpen[responseList[h].id] = false;
                    $scope.accordionStatus.descriptionHotelOpen[responseList[h].id] = false;
                }
                
                $rootScope.showLoading(false);
                $scope.updateLocalFilter();

            });

        };
            
        $scope.getUpdateModuleData = function() {

            if ($state.current.name == "app.hotelList") {
                $scope.getUpdateHotelList();
                //$scope.localState.hotelCitySelectorUpdator++;
                $rootScope.$broadcast('hotelCitySelectorChangeEvent', []);
            }

            setTimeout(function () {
                $rootScope.showLoading(false)
            }, 1000);

        }

        function initHotelList(){
            $scope.getUpdateModuleData();
            $rootScope.showLoading(false);
            $scope.updateLocalFilter();
        }
            
        //$scope.hotelState.getDeferredState().then(function() {
        //    initHotelList();
        //});
            
        initHotelList();
            
        //if ($state.current.name == "app.hotelRegister") {
        //    $scope.getUpdateModuleData();
        //};
        
        
    }]);