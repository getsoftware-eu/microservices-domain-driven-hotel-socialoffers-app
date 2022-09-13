'use strict';
//app
angular.module('hotelApp.login', ['ngRoute','ngCookies', 'hotelApp.customerDto', 'hotelApp.hotelState', 'hotelApp.hotelService', 'hotelApp.hotelLoginService', 'hotelApp.templates', 'angular-carousel' /*, 'hotelApp.initTranslations'/*, 'hotelApp.initTranslate'*/])

.controller('LoginController', 
['$scope','$rootScope','$state','$stateParams', '$filter','$location', '$cookies', 'CustomerDto', 'HotelState', 'HotelService', 'HotelLogin',
function($scope, $rootScope, $state, $stateParams, $filter, $location, $cookies, CustomerDto, HotelState, HotelService, HotelLogin) {

	//$scope.loginTabState = {
	//	guest: true,
	//	login: false,
	//	signup: false,
	//	ready: 0
	//};

	var requestedLoginState = HotelLogin.loginRequestedState;
	
	$scope.showTopic = requestedLoginState? requestedLoginState.replace("app.", "login").toLowerCase() : "checkin";
	
	if($state.current.name=='app.hotelLogin')
    {
		$scope.showTopic = "hotellogin"
    }
	
	var carouselLength = 1;
	
	if($scope.showTopic == "loginhotelpreview")
	{
		$scope.showTopic = "loginactivity"
		carouselLength = 2;
	}
	
	if($scope.showTopic == "loginwall")
	{
		$scope.showTopic = "loginchatlist"
	}
	
	$scope.carousel = {
		topic : $scope.showTopic,
		length : carouselLength
	};

	//$scope.loginTabState.guest = true;
	
	$scope.localState = {

		currentUser: undefined,

		//availableSystemLanguages : HotelService.getSystemLanguagesArray(),
		//
		//selectSystemLanguage : HotelState.getPrefferedLanguage(),
		
		guestUser: {
			firstName: undefined,
			sex: undefined, //"m",
			email: undefined,
			guestAccount: true
		},

		checkUser: {
			email: null,
			password: null
		},

		guestLogin: {
			firstName: undefined,
			lastName: undefined,
			email: undefined

		}
	};

	//if($state.current.name=='app.hotelLogin')
	//{
	//	//$scope.loginTabState.guest = false;
	//	//$scope.loginTabState.login = true;
	//	$scope.hotelLoginTabIndex = 1;
    //
	//}

	$scope.setLoginTabState = function (newState) {
		//$scope.loginTabState.guest = false;
		//$scope.loginTabState.login = false;
		//$scope.loginTabState.signup = false;
    
		$rootScope.rootErrorMsg = false;
    
		switch (newState) {
    
			case "login":
				//$scope.loginTabState.login = true;
                $scope.loginTabIndex = 1;
                $scope.hotelLoginTabIndex = 1;
				break;
    
			case "signup":
				//$scope.loginTabState.signup = true;
                $scope.loginTabIndex = 2;
                $scope.hotelLoginTabIndex = 2;
                break;
    
			case "guest":
			default:
				//$scope.loginTabState.guest = true;
                $scope.loginTabIndex = 0;
                $scope.hotelLoginTabIndex = 0;
                break;
		}
	}

	$scope.guestRegister = function () {

		$scope.loginService.guestRegister($scope.localState.guestUser);
		
		//var guest = new CustomerDto();
		//$rootScope.rootErrorMsg = false;
		//guest.firstName = $scope.localState.guestUser.firstName;
		//guest.sex = $scope.localState.guestUser.sex;
        //
		//guest.prefferedLanguage = $scope.app.rootSettings.prefferedLanguage;
        //
		//if (guest.sex == undefined) {
		//	if ($scope.loginTabState.guest) {
		//		$rootScope.rootErrorMsg = $filter('translate')('alert.error.noGender');
		//	}
		//	return;
		//}
        //
		//if (guest.firstName == undefined || guest.firstName == "") {
		//	//guest.firstName = "anonymous";
		//	if ($scope.loginTabState.guest) {
		//		$rootScope.rootErrorMsg = $filter('translate')('alert.error.noName');
		//	}
		//	return;
		//}
        //
		//if (guest.lastName == undefined) {
		//	guest.lastName = "";
		//}
        //
		////guest.lastName = $scope.localState.guestUser.lastName;
        //
		////guest.email = $scope.localState.guestUser.email;
        //
		//guest.guestAccount = true;
        //
		//$rootScope.showLoading(true);
        //
		//guest.$save(function (response) {
		//	
		//	$rootScope.showLoading(false);
        //
		//	if (response.id && response.id > 0) {
		//		$scope.localState.currentUser = response;
        //
		//		if ($scope.localState.currentUser.id) {
		//			//$cookies.hotelicoUserId = $scope.localState.currentUser.id;
		//			HotelService.setCookie('hotelicoUserId', $scope.localState.currentUser.id);
        //
		//			$cookies.hotelicoUserEmail = response.eMail;
        //
		//			$cookies.hotelicoLogout = false;
        //
		//			if($scope.hotelState)
		//			{
		//				$scope.hotelState.setProfileData(response);
		//			}
		//			else{
		//				HotelState.initState(response);
		//			}
        //
		//			$location.path('/app/checkin');
		//			//$location.path('/app/community');
		//		}
        //
		//	}
		//	else if (response.errorResponse) {
		//		$rootScope.rootErrorMsg = response.errorResponse;
		//		$rootScope.showLoading(false);
		//		$rootScope.topPage();
		//	}
		//}, function (error) {
		//	$rootScope.rootErrorMsg = error;
		//	$rootScope.showLoading(false);
        //
		//});

	};
    
	$scope.loginPassword = function (submit) {

		$rootScope.showLoading(true);

		//if(!$scope.localState.checkUser.email)
		{
			$scope.localState.checkUser.email= $("#hotelicoLoginMail").val();
		}
	
		//if(!$scope.localState.checkUser.password)
		{
			$scope.localState.checkUser.password= $("#hotelicoLoginPassword").val();
		}

		//function ajaxCallback() {
		//	$rootScope.showLoading(true);
		//	submit();
		//	$rootScope.showLoading(true);
		//}

		if(!$scope.localState.checkUser.email || !$scope.localState.checkUser.password)
		{
			return;
		}

		$rootScope.showLoading(true);
		
		$scope.loginService.loginPassword($scope.localState.checkUser);

		//var loggedUser = LoginCustomer.get({email: $scope.localState.checkUser.email}, {password: $scope.localState.checkUser.password}, function () {
		//	if (loggedUser && loggedUser.id && loggedUser.id > 0) {
		//		//$cookies.hotelicoUserId = loggedUser.id;
		//		//$cookies.hotelicoUserEmail = loggedUser.email;
		//		//$cookies.hotelicoLogout = false;
		//	}
		//	else if (loggedUser.errorResponse) {
		//		$rootScope.rootErrorMsg = loggedUser.errorResponse;
		//		$rootScope.showLoading(false);
		//		return;
		//	}
		//	else {
		//		$rootScope.rootErrorMsg =  $filter('translate')('alert.error.wrongMailPassword');
		//		$rootScope.showLoading(false);
		//		return;
		//	}
		//	//$rootScope.showLoading(false);
        //
		//	HotelState.initState(loggedUser);
		//}, function (error) {
		//	$rootScope.rootErrorMsg = error;
		//	$rootScope.showLoading(false);
        //
		//});
	};
    
    // ######################### START LANGUAGE ###########################################

	//function initSystemLanguages()
	//{
	//	for (var i = 0; i < $scope.localState.availableSystemLanguages.length; i++) {
	//		
	//		if ($scope.localState.availableSystemLanguages[i].langKey == HotelState.getPrefferedLanguage()) {
	//			$scope.localState.availableSystemLanguages[i].ticked = true;
	//		}
	//	}
	//};
	//
	//$scope.$watch('localState.selectSystemLanguage', function(){
	//	
	//	if($scope.localState.selectSystemLanguage && $scope.localState.selectSystemLanguage.length>0)
	//	{
	//		var langValue = $scope.localState.selectSystemLanguage[0].name;
	//		var langKey = HotelService.convertLangLabelToAvailableKey(langValue);
	//		if(langKey)
     //       {
     //           $scope.setNewLanguage(langKey);
     //       }
	//	}
	//	
	//}, true);

    //############################ END LANUAGE ##########################################

	function checkLoginTab()
	{
		//PRE-SELCT LOGIN TAB
		if($("#hotelicoLoginMail").val() && $("#hotelicoLoginPassword").val())
		{
			$scope.setLoginTabState("login");
		}
	}
	
	function initLogin(stateObj) {
        
		//if(($location.host()+"").indexOf("www")>=0)
		//{
		//	window.location = $rootScope.HOST;// + "#/access/login";
		//}
		
		if(!$scope.loginService)
		{
			$scope.loginService = HotelLogin;
		}
		
        if(!$scope.hotelState)
        {
            $scope.hotelState = stateObj;
        }
		
		var stopLogin = $stateParams.stopLogin || !$scope.hotelState || $state.current.name=="app.hotelLogin";

		//$rootScope.closeStartWerbung();
		$rootScope.showLoading(false);

		if (stopLogin) {
			HotelService.delCookie("hotelicoUserId");
		}
		else {

			if ($scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.id > 0) {
				 
				if ($scope.hotelState.profileData.errorResponse) {
					$rootScope.rootErrorMsg = $scope.hotelState.profileData.errorResponse;
					$rootScope.topPage();
				}
				else{
					
					var newState = $scope.hotelState.profileData.checkedIn? "app.hotel" : "app.checkin";
					
					$state.go(newState);
				}
			}
			//else {
			//	$scope.loginTabState.ready = 1;
			//}
		}
        
		setTimeout(checkLoginTab, 500);
		
		//$rootScope.unlockModal();
		
	};
	
	$scope.scrollModalDown = function()
	{
		var modalElt = document.querySelector(".modal.fade.ng-isolate-scope");

		if(modalElt)		
		{
			setTimeout(function(){
				//alert('jj');
				modalElt.scrollTop = modalElt.scrollHeight;
			}, 800);
		}
	}

	HotelState.getDeferredState().then(function(stateObj) {
        $scope.hotelState = stateObj;
		initLogin(stateObj);
	}, function(error){
		initLogin();
		
	});
	
	//if($stateParams.stopLogin)
	{
		initLogin();
	}

	//initSystemLanguages();

}])
; 