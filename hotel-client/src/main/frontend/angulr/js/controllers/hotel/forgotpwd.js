'use strict';
app
//angular.module('hotelApp.forgotpwd', ['ngRoute','ngCookies', 'hotelApp.customerDto','hotelApp.loginCustomerService','hotelApp.hotelDto', 'hotelApp.sessionCustomerService'])

.controller('ForgotpwdController', 
['$scope','$rootScope', '$cookies', '$state', '$resource', '$stateParams', '$filter', 'CustomerDto', 'SessionCustomer',
function($scope, $rootScope, $cookies, $state, $resource, $stateParams, $filter, CustomerDto, SessionCustomer) {

	$scope.ready = 0;
	
	$scope.isCollapsed = true;
	
	$scope.resetInfo = {
		email: undefined,
		resetcode: undefined,
		password : undefined,
		repeatPassword : undefined
	}
	
	
	$scope.submitResetPassword = function()
	{
		$scope.mainState.errorMsg = false;
		
		//if(!$stateParams.email || !$stateParams.email )
		//{
		//	return;
		//}
	
		var ResetPasswordService = $resource('./customers/resetPassword/email/:email/resetcode/:resetcode', {email:'@email', resetcode:'@resetcode' }, {'resetPassword': {method: 'GET', isArray: false}});

		ResetPasswordService.resetPassword({email: $stateParams.email, resetcode: $stateParams.resetcode}, function(response) {
			if(response && response.id && response.id>0)
			{
				$scope.resetProfile = $scope.hotelService.decodeCustomer(response);
				$scope.hotelState.initState($scope.resetProfile);
				$scope.showNewPasswordField = true;
			}
			else {
				$scope.mainState.errorMsg = $filter('translate')('alert.error.wrongResetCode');

			}
		}, function(error){
			$scope.mainState.errorMsg = $filter('translate')('alert.error.mailError');
		});
	}
	
	$scope.submitNewPassword = function()
	{
		$rootScope.showLoading(true);
		CustomerDto.get({id: $scope.resetProfile.id, requesterId: -1}, function(responseObj){
			responseObj.password = $scope.resetInfo.password;
			//CustomerDto.update({ id : responseObj.id, requesterId: responseObj.id }, responseObj, function(){
			$scope.hotelState.submitProfileData(true, responseObj).then(function(user)
				{
					$scope.hotelState.initState(user);
					$state.go("app.hotel");
				}
			)
					
		}, function(errorObj){
			$rootScope.showErrorObj(errorObj);
			$rootScope.showLoading(false);
		});
		 
	}

	if($stateParams.resetcode)
	{
		$scope.submitResetPassword();
	}
	
	$scope.sendResetCode = function()
	{
		$scope.mainState.errorMsg = false;
		$scope.success = false;

		if(! $scope.resetInfo.email)
		{
			return;
		}
		
		var RequestResetCodeService = $resource('./customers/requestPasswordReset/email/:email', {email:'@email'}, {'getResetCode': {method: 'GET'}});

		$rootScope.showLoading(true);
		
		RequestResetCodeService.getResetCode({email:$scope.resetInfo.email}, function(response) {
			if(response.errorResponse && response.errorResponse!="ok")
			{
				$scope.mainState.errorMsg = response.errorResponse;
			}
			else{
				//$scope.responseMsg = response.message;
				$scope.success = true;
				$scope.isCollapsed = false;

			}

			$rootScope.showLoading(false);

		}, function(errorObj){
			
			$rootScope.showErrorObj(errorObj);
			
			$scope.success = true;
			$scope.isCollapsed = false;
			$rootScope.showLoading(false);
		});

		$scope.resetInfo.email = "";
	}
	
	var sessionCustomer = SessionCustomer.get(function () {

		if (sessionCustomer && sessionCustomer.id && sessionCustomer.id > 0 && sessionCustomer.logged) {
			$scope.showNewPasswordField = true;
			$scope.hotelState.initState(sessionCustomer);
		}

		$rootScope.showLoading(false);

		$rootScope.closeModal();

	});


}]);