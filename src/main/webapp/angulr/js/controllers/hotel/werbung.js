'use strict';
//app
angular.module('hotelApp.werbung', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', 'hotelApp.hotelState', 'hotelApp.templates'])

    .controller('WerbungController',
    ['$scope', '$rootScope', '$location', '$resource', '$state', 'SessionCustomer', 'HotelDto', 'HotelState',
    function($scope, $rootScope, $location, $resource, $state, SessionCustomer, HotelDto, HotelState) {

        $scope.ready = 0;

        $rootScope.closeStartWerbung();

        $rootScope.showLoading(false);

        $scope.relocate = function(hotelObject)
        {
            if(($location.url()+"").indexOf("werbung")>=0)
                $state.go("access.login");

        }
        
        //var sessionCustomer = SessionCustomer.get(function (responseObject) {
        //    
        //    HotelState.setProfileData(responseObject);
        //
        //    //$scope.relocate(responseObject);
        //    setTimeout( $scope.relocate, 8000);
        //
        //});

        HotelState.getDeferredState().then(function(hotelState){
            
            
            var customer = hotelState.profileData;

            if(customer && customer.id>0)
            {
                $state.go("app.hotel");
            }
            else{
                $state.go("access.login", {stopLogin: true});
            }
        });

}]);