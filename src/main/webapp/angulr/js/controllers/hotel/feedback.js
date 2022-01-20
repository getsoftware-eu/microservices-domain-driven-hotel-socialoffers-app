'use strict';
app
//angular.module('hotelApp.profile', ['ngRoute', 'hotelApp.customerDto'])

    .controller('FeedbackController', ['$scope', '$rootScope', '$filter',
        function($scope, $rootScope, $filter) {

            
        $scope.localState = {
            feedbackText : undefined,
            anonymName : "",
            anonymMail : ""
        };
            
        $scope.submitFeedback = function() {

            $scope.mainState.errorMsg = false;
        
            $scope.mainState.successMsg = false;
        
            var feedbackCustomer = $scope.hotelService.getInitCustomer();
        
            if($scope.hotelState.profileData)
            {
                feedbackCustomer = $scope.hotelState.profileData;
                //feedbackCustomer.age = undefined;
                //feedbackCustomer.confirmPassword = undefined;
            }
        
            if($scope.localState.feedbackText != undefined)
            {
                if(!feedbackCustomer.systemMessages)
                {
                    feedbackCustomer.systemMessages = {};
                }
                
                if($scope.localState.anonymName)
                {
                    feedbackCustomer.systemMessages["fromName"] = $scope.localState.anonymName;
                } 
                
                if($scope.localState.anonymMail)
                {
                    feedbackCustomer.systemMessages["fromMail"] = $scope.localState.anonymMail;
                }
                
                
                feedbackCustomer.systemMessages["feedbackMessage"] = encodeURIComponent($scope.localState.feedbackText);
            }
        
            $rootScope.showLoading(true);
        
        
            $scope.hotelState.submitProfileData(false, feedbackCustomer).then(function(updatedCustomer){
        
                if(!$scope.hotelState.profileData)
                {
                    $rootScope.topPage();
                    $scope.localState.feedbackText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.feedbackMsgOk');
                }
                else if(updatedCustomer && updatedCustomer.id>0)
                {
                    $rootScope.topPage();
        
                    $scope.localState.feedbackText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.feedbackMsgOk');
                }
                else{
                    //$scope.localState.feedbackText = decodeURIComponent($scope.hotelState.profileData.feedback);
                    $scope.hotelState.profileData.systemMessages = {};
                    $scope.mainState.errorMsg = $filter('translate')('alert.error.connectionError');
                }
        
                $rootScope.showLoading(false);
            }, function(error){
                //TODO eugen: show error?
                $scope.mainState.errorMsg = $filter('translate')('alert.error.connectionError');
                $rootScope.showLoading(false);
            });
    
        }

        //$scope.hotelState.getDeferredState().then(function() {
        //
        //    $scope.initProfile($scope.hotelState.profileData);
        //});
       
}]);