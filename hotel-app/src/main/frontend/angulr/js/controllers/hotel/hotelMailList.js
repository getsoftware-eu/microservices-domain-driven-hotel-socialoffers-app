'use strict';
app
//angular.module('hotelApp.profile', ['ngRoute', 'hotelApp.customerDto'])

    .controller('MailListCtrl', ['$scope', '$rootScope', '$filter',
        function($scope, $rootScope, $filter) {

            
        $scope.localState = {
            mailList : "",
            mailListString : "",
            fromMail : $scope.hotelState.profileData.email? $scope.hotelState.profileData.email : "",
            mailContent : "",
            fromName : $scope.hotelState.profileData.firstName? $scope.hotelState.profileData.firstName + " " + $scope.hotelState.profileData.lastName: "",
            customSubject : "Willkommen in " + ($scope.hotelState.profileData.hotelName? $scope.hotelState.profileData.hotelName : " unserem Hotel")
        };
            
        $scope.submitMailList = function() {

            $scope.mainState.errorMsg = false;
        
            $scope.mainState.successMsg = false;
        
            var sender = $scope.hotelState.profileData;
            
            if($scope.localState.mailListString && $scope.localState.mailListString != undefined)
            {
                if(!sender.systemMessages)
                {
                    sender.systemMessages = {};
                }
                
                if($scope.localState.fromName)
                {
                    sender.systemMessages["fromName"] = $scope.localState.fromName;
                } 
                
                if($scope.localState.fromMail)
                {
                    sender.systemMessages["fromMail"] = $scope.localState.fromMail;
                }  
                
                if($scope.localState.customSubject)
                {
                    sender.systemMessages["customSubject"] = $scope.localState.customSubject;
                } 
                
                if($scope.localState.mailContent)
                {
                    sender.systemMessages["mailContent"] = encodeURIComponent($scope.localState.mailContent);
                }

                if($scope.localState.mailList && $scope.localState.mailList.length>0)
                {
                    $scope.localState.mailListString = "";
                    
                    for(var i=0; i < $scope.localState.mailList.length; i++)
                    {
                        if(!$scope.localState.mailList[i] || !$scope.localState.mailList[i].text)
                        {
                            continue;
                        }
                        
                        if($scope.localState.mailListString)
                        {
                            $scope.localState.mailListString += ",";    
                        }

                        $scope.localState.mailListString += $scope.localState.mailList[i].text;
                    }
                }

                if($scope.localState.mailListString)
                {
                    $scope.localState.mailListString = $scope.localState.mailListString.replace(/;/g,",");
                    
                    var split = $scope.localState.mailListString.split(",");
                    
                    var allMailsValid = true;
                    
                    for(var s=0; s < split.length; s++)
                    {
                        allMailsValid = allMailsValid && validateEmail(split[s]);
                    }
                    
                    if(!allMailsValid)
                    {
                        $rootScope.showErrorObj("Mail List is wrong");
                        return;
                    }
                    
                    sender.systemMessages["mailList"] = $scope.localState.mailListString;
                }
                else {
                    $rootScope.showErrorObj("Mail List is wrong");
                    return;
                }

            }
            else{
                $rootScope.showErrorObj("Mail List is wrong");
                return;
            }
        
            $rootScope.showLoading(true);
        
            $scope.hotelState.submitProfileData(false, sender).then(function(updatedCustomer){
        
                if(!$scope.hotelState.profileData)
                {
                    $rootScope.topPage();
                    $scope.localState.feedbackText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.mailListMsgOk');
                }
                else if(updatedCustomer && updatedCustomer.id>0)
                {
                    $rootScope.topPage();
        
                    $scope.localState.feedbackText = undefined;
                    $scope.mainState.successMsg = $filter('translate')('alert.success.mailListMsgOk');
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

        function validateEmail(email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }
            
        function init()
        {
            setTimeout(function () {
                //prevent modal onTouch error
                $rootScope.unlockModal();

                //var allFocusable = document.getElementsByClassName("focusModal");
                //
                //for(var f=0; f<allFocusable.length; f++)
                //{
                //    var elem = allFocusable[f];
                //
                //    elem.removeEventListener('click', function(e) {
                //        e.target.focus();
                //        //e.preventDefault();
                //        e.stopPropagation();
                //    });
                //}
                
            }, 300);
        }
            
        init();

}]);