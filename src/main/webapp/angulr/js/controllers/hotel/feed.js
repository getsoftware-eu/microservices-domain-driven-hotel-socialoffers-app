'use strict';
app
//angular.module('hotelApp.profile', ['ngRoute', 'hotelApp.customerDto'])

    .controller('FeedController', ['$scope', '$rootScope', '$filter',
        function($scope, $rootScope, $filter) {

        
            
        $scope.localState = {
            feedText : undefined,
            availableFeedCustomers: [],
            selectedFeedCustomers: [],
            customerView: !$scope.hotelState.isHotelStaffOrAdmin ,
            inviteToActivity: false,
            inviteActivity: null,
            selectionMode: $scope.hotelState.isHotelStaffOrAdmin?  "multiple" : "single",
            sendToAllNotLoggedInHotel: $scope.hotelState.isHotelStaffOrAdmin
            //hotelFeedCustomersIds: "",
            //hotelFeedCustomersNames: "",
            //anonymName : "",
            //anonymMail : ""
        };
            
        $scope.submitFeed = function() {

            $scope.mainState.errorMsg = false;
        
            $scope.mainState.successMsg = false;
            
            //if(!$scope.hotelState.profileData.hotelStaff && !$scope.hotelState.profileData.admin)
            //{
            //    return;
            //}

            var feedCustomer = $scope.hotelState.profileData;

            if(!$scope.localState.feedText)
            {
                $scope.localState.feedText = "Invitation from " + feedCustomer.firstName;
            }
            
            {
                if(!feedCustomer.systemMessages)
                {
                    feedCustomer.systemMessages = {};
                }
                
                var hotelFeedCustomersIds = "";
                
                for(var i=0; i <  $scope.localState.selectedFeedCustomers.length; i++)
                {
                    if(hotelFeedCustomersIds!="")
                    {
                        hotelFeedCustomersIds += ",";
                    }
                    
                    hotelFeedCustomersIds += $scope.localState.selectedFeedCustomers[i].id;
                }

                var encodedFeedText = encodeURIComponent($scope.localState.feedText);
                
                if(encodedFeedText)
                {
                    feedCustomer.systemMessages["hotelFeedMessage"] = encodedFeedText;
                }
                
                if(hotelFeedCustomersIds)
                {
                    feedCustomer.systemMessages["hotelFeedCustomerIds"] = hotelFeedCustomersIds;
                }

                if($scope.localState.inviteToActivity && $scope.localState.inviteActivity)
                {
                    feedCustomer.systemMessages["inviteActivityId"] = $scope.inviteActivityId;

                    if($scope.localState.sendToAllNotLoggedInHotel)
                    {
                        feedCustomer.systemMessages["sendToAllNotLoggedInHotel"] = $scope.localState.sendToAllNotLoggedInHotel;
                    }
                }  
                
                
                
            }
        
            $rootScope.showLoading(true);
        
        
            $scope.hotelState.submitProfileData(false, feedCustomer).then(function(updatedCustomer){
        
                if(!$scope.hotelState.profileData)
                {
                    $rootScope.topPage();
                    $scope.localState.feedText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.feedMsgOk');
                }
                else if(updatedCustomer && updatedCustomer.id>0)
                {
                    $rootScope.topPage();
        
                    $scope.localState.feedText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.feedMsgOk');
                }
                else{
                    //$scope.localState.feedText = decodeURIComponent($scope.hotelState.profileData.feed);
                    $scope.hotelState.profileData.systemMessages = {};
                    $scope.mainState.errorMsg = $filter('translate')('alert.error.connectionError');
                }
        
                $rootScope.showLoading(false);
                
                $rootScope.closeModal();
            }, 
            $rootScope.showErrorObj
            );
    
        }

        function initFeed()
        {
            if($scope.inviteActivityId && $scope.inviteActivityId>0)
            {
                $scope.localState.inviteToActivity = true;

                //TODO Eugen: test it!!!
                $scope.hotelState.getActivityById($scope.inviteActivityId).then(function(activityObj){

                    $scope.localState.inviteActivity = activityObj;

                    $scope.showLoading(false);

                });
            }
            
            $scope.hotelState.getHotelCustomers($scope.hotelState.profileData.hotelId).then(function(respArray){

                if(respArray)
                {
                    for(var i=0; i< respArray.length; i++)
                    {
                        var nextCustomer = respArray[i];

                        if(nextCustomer.id == $scope.hotelState.profileData.id)
                        {
                            continue;
                        }

                        var nextFeedCustomer = {};
                        nextFeedCustomer.id = respArray[i].id;
                        nextFeedCustomer.name = respArray[i].firstName + (respArray[i].lastName? " " + respArray[i].lastName: "");
                        nextFeedCustomer.icon = "<img src='"+respArray[i].avatarUrl+"' />";
                        nextFeedCustomer.ticked = !$scope.localState.customerView;

                        $scope.localState.availableFeedCustomers.push(nextFeedCustomer);
                        
                        //if(!$scope.localState.customerView)
                        {
                            $scope.localState.selectedFeedCustomers.push(nextFeedCustomer);
                        }
                    }
                }

                //if($scope.localState.customerView)
                //{
                //    setTimeout(function(){
                //        $scope.localState.selectedFeedCustomers = [];
                //    }, 1500);
                //}
                
            }, $rootScope.showErrorObj);//.then( function(customers)
        }

        initFeed();
       
}]);