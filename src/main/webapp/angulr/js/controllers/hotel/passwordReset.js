'use strict';
app
//angular.module('hotelApp.passwordReset', ['ngRoute','ngCookies', 'hotelApp.customerDto','hotelApp.loginCustomerService','hotelApp.hotelDto', 'hotelApp.sessionCustomerService'])

.controller('PasswordController', 
['$scope','$rootScope','$http','$q','$location', '$cookies', 'CustomerDto', 'HotelDto', 'SessionCustomer', '$stateParams',
function($scope, $rootScope, $http, Q, $location, $cookies, CustomerDto, HotelDto, SessionCustomer, $stateParams) {

	$scope.ready = 0;

	
	if($stateParams.resetCode)
	{
		$scope.resetCode = $stateParams.resetCode;
	}
	
	else {
		
		var sessionCustomer = SessionCustomer.get(function () {
			if (sessionCustomer && sessionCustomer.id && sessionCustomer.id > 0) {
				$scope.ready = 1;
			}

			$scope.hotelState.setProfileData(sessionCustomer);

		});
	}

	$rootScope.showLoading(false);


	$scope.loginPassword = function() {

		$scope.loginService.getLogin($scope.checkUser.email, $scope.checkUser.password).then(function(loggedUser){
			if(loggedUser && loggedUser.id && loggedUser.id>0)
			{
				//$cookies.hotelicoUserId = loggedUser.id;
				//$cookies.hotelicoUserEmail = loggedUser.eMail;
				//$cookies.hotelicoLogout = false;
			}
			else{
				$scope.authError = $filter('translate')('alert.error.wrongMailPassword');
			}

			$scope.hotelState.setProfileData(loggedUser);

		});		
	};
	
	$scope.model = {
		users : CustomerDto.query(),
		newUser: {
			firstName: null,
			lastName: null,
			mail: null,
			password: null
		}
		 
	};

	$scope.checkUser = {
		eMail : null,
		password : null
	}

	$scope.authError = null;

	
	
	$scope.initiator = false;

	$scope.socket = {
		client: null,
		stomp: null
	};

	//$scope.add = function() {
	//	$scope.initiator = true;
	//	var user = new CustomerDto();
	//	user.firstName = $scope.model.newUser.firstName;
	//	user.lastName = $scope.model.newUser.lastName;
	//	user.authLinkedIn = false;
    //
	//	user.votes = 0;
	//	user.$save(function(response) {
	//		$scope.model.users.push(response);
	//		$scope.model.currentUser = response;
	//	});
	//	$scope.model.newUser.firstName = '';
	//	$scope.model.newUser.secondName = '';
	//};

	//$scope.testAdd = function() {
	//	var usr = new Hoteluser();
	//	//usr.firstName = 'f';
	//	usr.$save(function(response) {
	//		$scope.model.usr = response;
	//	});
	//	
	//	 
	//	var ht = new Hotel();
	//	//ht.title = 'f';
	//	//ht.description = 'f';
	//	ht.$save(function(response) {
	//		$scope.model.ht = response;
	//	});
    //
	//	var s = new User();
	//	//s.title = 'f';
	//	//ht.description = 'f';
	//	s.$save(function(response) {
	//		$scope.model.ht = response;
	//	});
	//	
	//};

	//$scope.remove = function(/** User */ user, /** Integer */ index) {
	//	$scope.initiator = true;
	//	$scope.model.users.splice(index, 1);
	//	user.$remove();
	//};
    //
	//$scope.addVotes = function(/** User */ user, /** Integer */ votes) {
	//	$scope.initiator = true;
	//	user.votes += votes;
	//	user.$update();
	//};
	
	////TODO eugen! Always at start!!!!

	//$scope.notify = function(/** Message */ message) {
	//	if (!$scope.initiator) {
	//		User.query(function(users) {
	//			$scope.model.users = users;
	//		});
	//	}
	//	$scope.initiator = false;
	//};
    //
	//$scope.reconnect = function() {
	//	setTimeout($scope.initSockets, 10000);
	//};
    //
	////TODO eugen! Always at start!!!!
	//$scope.initSockets = function() {
	//	$scope.socket.client = new SockJS('/hotelico/notify');
	//	$scope.socket.stomp = Stomp.over($scope.socket.client);
	//	$scope.socket.stomp.connect({}, function() {
	//		$scope.socket.stomp.subscribe("/topic/notify", $scope.notify);
	//	});
	//	$scope.socket.client.onclose = $scope.reconnect;
	//};

	//$scope.initSockets();

	
	
    //
    ///**
	 //* React to LinkedIn SSO by performing app login using the authentication 
	 //* cookie set by LinkedIn
	 //*/
    //$scope.onLinkedInAuth = function() { 
		//loginClient.promiseDoLoginLinkedIn($scope); 
    //};
    //
    //
    //$scope.user = {};
    //$scope.authError = null;
    //
    //$scope.ready = 0;
    //$scope.user.eMail = '';
    //$scope.user.password = '';
    //
    //$scope.errorMessage = undefined;
    ////
    ////$scope.login = function() {
    ////    $scope.authError = null;
    ////    // Try to login
    ////    $http.post('api/login', {email: $scope.user.email, password: $scope.user.password})
    ////        .then(function(response) {
    ////            if ( !response.data.user ) {
    ////                $scope.authError = 'Email or Password not right';
    ////            }else{
    ////                $state.go('app.dashboard-v1');
    ////            }
    ////        }, function(x) {
    ////            $scope.authError = 'Server Error';
    ////        });
    ////};
    //
    //stateClient.promiseState($scope)
    //.then(function(resData) {
		//if (resData.state === 'login' && resData.linkedIn.memberId) {
		//	// If LinkedIn login is possible, this will be triggered by the LinkedIn onAuth script
		//	// No action necessary here
		//}
		//if (resData.state === 'login' && resData.userId) {
		//	// TODO: possibly pre-fill the "eMail" field
		//}
		//if (resData.state === 'checkIn') {
		//	// TODO: possibly pass additional checkIn parameters passed to login
		//	$location.path('/checkIn');
		//}
    //
		//	var data = {};
    //
		//	$http.get('/auth/linkedin', data);
    //});
    //
    //loginClient.promiseDoLogout($scope);
}]);