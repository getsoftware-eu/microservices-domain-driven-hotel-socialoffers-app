'use strict';
//app
angular.module('hotelApp.filterHotelCity', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', 'hotelApp.activityDto'])

    //Eugen: the container controller has to have $scope.updateLocalFilter(); $scope.emptyPreviousSearch(); and 'localState.hotelCitySelectorUpdator' and localState.disableAllHotelsInCity
    
    .controller('filterHotelCityCtrl', ['$scope', '$rootScope', '$stateParams', 'HotelDto', '$state', '$filter', '$mdDialog',
        function($scope, $rootScope, $stateParams, HotelDto, $state, $filter, $mdDialog) {
            
            
        $scope.onChangeHotelCitySelector = function()
        {
            $scope.emptyPreviousSearch();
        
            $scope.localState.showCityHotelLoading = true;
        
            $scope.mainState.selectedCity = $scope.localState.selectedCityHotel && $scope.localState.selectedCityHotel.city? $scope.localState.selectedCityHotel.city : "-1";

            //if($scope.mainState.selectedCity == $filter('translate')('alert.info.allCities'))
            //{
            //    $scope.mainState.selectedCity = $scope.mainState.selectedCity.name+"".charAt(0).toUpperCase() + $scope.mainState.selectedCity.name+"".slice(1);
            //}
            
            //$scope.localState.allCitiesLabel = $filter('translate')('alert.info.allCities');
            //
            ////if all cities
            //if($scope.mainState.selectedCity == $scope.localState.allCitiesLabel)
            //{
            //    $scope.mainState.selectedCity = "-1"; 
            //}
            
            var permitAllCitiesEntry = $scope.localState.disableAllHotelsInCity? false : true;
            
            $scope.getMainHotelsByCity($scope.mainState.selectedCity, permitAllCitiesEntry).then(function(hotelsInSelectedCityArray){
        
                $scope.localState.hotelsFilteredByCityArray = hotelsInSelectedCityArray;
        
                $scope.localState.showCityHotelLoading = false;
        
                if($scope.localState.preFilterHotelIdParam)
                {
                    for(var h=0; h<hotelsInSelectedCityArray.length; h++)
                    {
                        if(hotelsInSelectedCityArray[h].id == $scope.localState.preFilterHotelIdParam)
                        {
                            $scope.localState.selectedFilterHotel = hotelsInSelectedCityArray[h];
                            $scope.onChangeFilterHotelSelector($scope.localState.selectedFilterHotel.id);
                            break;
                        }
                    }
        
                    //one time usage!!!
                    setTimeout(function(){$scope.localState.preFilterHotelIdParam = false;}, 1000);
                }

                //watcher city
                $scope.updateLocalFilter(false, hotelsInSelectedCityArray);
        
            });
        };
        
        $scope.initHotelCitiesSelector = function()
        {
            $scope.emptyPreviousSearch();
        
            $scope.localState.showCityLoading = true;

            //$scope.hotelState.getDeferredState().then(function(profile){
               
           //load hotelCities only if hotelState is ready!
            $scope.getUpdateMainHotelCitiesSelectorList().then(function(hotelCitiesArray){
                
                $rootScope.showLoading(false);

                preFilterCityParam();

                $scope.updateLocalFilter();

                $scope.localState.showCityLoading = false;
                setTimeout(function(){$scope.localState.showCityLoading = false;}, 1000);

            }, function(error){

                $scope.mainState.errorMsg = error;
                $rootScope.showLoading(false);

            });
            //});
            
            
        };
        
        
        $scope.openCityPopup = function(ev)
        {
            var confirm = $mdDialog.confirm( {
                clickOutsideToClose: true
            })
                .title($filter('translate')('alert.info.cityPopupHeader'))
                .content($filter('translate')('alert.info.cityPopupText'))
                .ariaLabel('Lucky day')
                .ok('OK')
                //.cancel($filter('translate')('alert.noPopupMore'))
                .targetEvent(ev);

            $mdDialog.show(confirm).then(function() {
                ;

            }, function() {
                //$scope.hotelState.profileData.hideWallPopup = true;
                //$scope.hotelService.setCookie('hotelicohideWallPopup', true);
                //
                //$scope.hotelState.submitProfileData(true);
            });
        }
            
        /**
         * pre-select param city
         */
        function preFilterCityParam(){
        
            if($scope.localState.preFilterCityParam)
            {
                for(var c=0; c<$scope.mainState.hotelCitiesArray.length; c++)
                {
                    if($scope.mainState.hotelCitiesArray[c].city == $scope.localState.preFilterCityParam)
                    {
                        $scope.localState.selectedCityHotel = $scope.mainState.hotelCitiesArray[c];
        
                        $scope.emptyPreviousSearch();
        
                        $scope.onChangeHotelCitySelector();

                        $scope.localState.preFilterCityParam = "";
                        
                        break;
                    }
                }
        
                //one time usage!!!
                setTimeout(function(){$scope.localState.preFilterCityParam = false;}, 1000);
            }
        };

        $scope.onChangeFilterHotelSelector = function(filterHotelId)
        {
            $scope.localState.searchFilter = {};
            
            $scope.updateLocalFilter();
        }   
            
        //$scope.$watch('localState.hotelCitySelectorUpdator', function() {
        //
        //    
        //
        //});

        $rootScope.$on('hotelCitySelectorChangeEvent', function(event, mass) { 
            $scope.initHotelCitiesSelector(); 
        });
            
        function initFilter(){
            //PRE-FILTER Params save local!!! One time usage!!!
            $scope.localState.preFilterCityParam = $stateParams.filterCity;
            $scope.localState.preFilterHotelIdParam = $stateParams.filterHotelId;
                
            //get city from HotelId
            if($scope.localState.preFilterHotelIdParam && !$scope.localState.preFilterCityParam)
            {
                $scope.getUpdateMainHotelObject($scope.localState.preFilterHotelIdParam).then(function(filterHotel){
                    $scope.localState.preFilterCityParam = filterHotel.city;
                    $scope.initHotelCitiesSelector();
                });
            }
            
            $scope.emptyPreviousSearch();
            $scope.initHotelCitiesSelector();
        }

        //wait for hotel state
        //$scope.hotelState.getDeferredState().then(function() {
            initFilter();
        //});
        //
        //$rootScope.$on('preFilterHotelCity', function(event, mass) {
        //    initFilter();
        //});
    }
]
);