'use strict';
//app
angular.module('hotelApp.checkIn', ['ngRoute','ngCookies', 'ui.bootstrap', 'hotelApp.hotelDto', 'hotelApp.sessionCustomerService'])

.controller('CheckInController', 
['$scope', '$rootScope' , '$routeParams', '$http', '$q', '$location', '$cookies', 'HotelDto', 'SessionCustomer', '$state', '$stateParams', '$resource', '$mdDialog', '$filter', 'HotelCheckin',
function($scope, $rootScope, $routeParams, $http, Q, $location, $cookies, HotelDto, SessionCustomer, $state, $stateParams, $resource, $mdDialog, $filter, HotelCheckin) {
	
	$scope.localState = {

		ready: 0,

		disableAllHotelsInCity: true, //disable all hotels in city
		selectedHotelCity: null,
		selectedFilterHotel: null,

		availableSystemLanguages : $scope.hotelService.getSystemLanguagesArray(),

		selectSystemLanguage : $scope.hotelState.getPrefferedLanguage(),
		
		showManualCheckin: false,

		hideCodeInput: true,
		
		//$scope.selectedHotelCode = "PO3AX";
		disableCheckin: false,

		selectedHotelCode: "",
		pending: 0,
         
        hotelSelectCheckinButtonShow : true,
        
		checkinDates: {
			checkinFrom: null,
			checkinTo: null
		},

		hotelcodeFocus: false
	};

	$scope.carousel = {
		topic : "checkin",
		length : 4
	};
	
	$scope.accordionStatus = {
		infoHotelOpen : [] 
	}
	
	$scope.$watch('localState.selectSystemLanguage', function(){

		if($scope.localState.selectSystemLanguage && $scope.localState.selectSystemLanguage.length>0)
		{
			var langValue = $scope.localState.selectSystemLanguage[0].name;
			var langKey = $scope.hotelService.convertLangLabelToAvailableKey(langValue);
			if(langKey)
			{
				$scope.setNewLanguage(langKey);
			}
		}

	}, true);
	
	//SERVICE PRE-CALLER
	$scope.onHotelCodeSubmit = function (inputHotelCode) {
		
		if(!inputHotelCode)
		{
			inputHotelCode = $scope.localState.selectedHotelCode;
		}
		
		var nowHotelId = $scope.hotelState.profileData.hotelId;

		$scope.checkinService.checkHotelCode(nowHotelId, inputHotelCode).then(function(okHotelObj)
		{
			$scope.hotelState.profileData.hotelCode = inputHotelCode;
			
			if($scope.hotelState.profileData.logged)
			{
				$scope.onCheckinSubmit(inputHotelCode);
			}
			else{
				$scope.hotelState.profileData.fullCheckin = true;
			}
		},
		function(error){
			//$rootScope.rootErrorMsg = error;
			$rootScope.showErrorObj(error, true);
		});
		
		
	}

	 
	
	$scope.onCheckinSubmit = function (forceHotelCode) {

		//if($scope.hotelCheckin.checkinSuccess || $scope.hotelCheckin.checkinResponse)
		//{
		//	return;
		//}

		$rootScope.rootErrorMsg = false;
		$scope.errorMsg = false;
		
		//if(!$scope.hotelCheckin)
		//{
		//	$scope.hotelCheckin = HotelCheckin;	
		//}
		if (!$scope.checkinService) {
			$scope.checkinService = HotelCheckin;
		}

		$scope.checkinService.resetWholeInfo();

		var hotelId = $scope.localState.selectedFilterHotel != null ? $scope.localState.selectedFilterHotel.id : null;

		var hotelCode = $scope.localState.selectedHotelCity ? "" : $scope.localState.selectedHotelCode;

		if (forceHotelCode) {
			hotelCode = forceHotelCode;
		}

		var checkinDateFrom = $scope.localState.checkinDates.checkinFrom != null ? new Date($scope.localState.checkinDates.checkinFrom) : null;
		var checkinDateTo = $scope.localState.checkinDates.checkinTo != null ? new Date($scope.localState.checkinDates.checkinTo) : null;

		if (hotelId == null && hotelCode == null) {
			$scope.mainState.errorMsg = $filter('translate')('alert.checkin.chooseHotel');
			$rootScope.topPage();
			$rootScope.showLoading(false);
			return;
		}

		if (checkinDateFrom == null) {
			checkinDateFrom = new Date();
			$scope.checkinService.checkinDateFrom = checkinDateFrom;

		}

		if (checkinDateTo == null) {
			checkinDateTo = $scope.hotelService.getNextSundayMiddayDate();

			$scope.checkinService.checkinDateTo = checkinDateTo;
		}

		if (hotelId)
			$scope.checkinService.hotelId = hotelId;

		if (hotelCode) {

			$scope.checkinService.hotelCode = hotelCode;

			//if(hotelCode=="demo")
			//{
			//	var demoHotelIdElt = document.getElementById("demoHotelId");
			//
			//	if(demoHotelIdElt && demoHotelIdElt.innerText)
			//	{	var demoHotelId = demoHotelIdElt.innerText;
			//		$scope.getUpdateMainHotelObject(demoHotelId).then(function(hotelObj){
			//			$scope.localState.selectedFilterHotel = hotelObj;
			//		});
			//	}
			//}
		}

		$rootScope.showLoading(true);

		$scope.hotelState.emptyCheckinData();

		$scope.localState.disableCheckin = true;

		$scope.checkinService.doCheckin().then(function () {
			$scope.localState.disableCheckin = false;
			$scope.mainState.showCheckinWellcomePopup = true;
			//$scope.localState.showManualCheckin = true;
			$scope.localState.selectedHotelCity= null;
			$scope.closeModal();
			$scope.showLoading(false);

			//Eugen: always show manual checkin, if reset hidden hotelCode
			if($scope.localState.hideCodeInput)
			{
				//$scope.checkinService.hotelCode = "";
				$scope.localState.showManualCheckin = true;
				showManualSelection();
			}

		}, function (error) {
			$scope.localState.disableCheckin = false;
			$scope.localState.showManualCheckin = true;
			$scope.mainState.showCheckinWellcomePopup = true;
			$scope.localState.selectedHotelCity= null;
            
            if(error!="noLogin")
            {
                $rootScope.showErrorObj(error, true);
            }
			
            $rootScope.showLoading(false);
			setTimeout(showManualSelection, 1000);
		});

	};


	function showManualSelection() {
		var hiddenContents = document.getElementsByClassName("hiddenContent");
		
		var eltsToRemove = [];

		if (hiddenContents && hiddenContents.length > 0) {
			for (var i = 0; i < hiddenContents.length; i++) {
				var nextElt = hiddenContents[i];
				eltsToRemove.push(nextElt)
			}

			for (var i = 0; i < eltsToRemove.length; i++) {
				var nextElt = eltsToRemove[i];

				if (nextElt.classList.contains("hiddenContent")) {
					nextElt.classList.remove("hiddenContent");
					nextElt.className = nextElt.className.replace("hiddenContent", '');
					//$log,info
				}
			}
		}
	}

	
	
	//############# START FILTER ####################
	$scope.emptyPreviousSearch = function () {
		$scope.localState.searchFilter = {};
		$scope.localState.selectedFilterHotel = null;
	};

	$scope.updateLocalFilter = function(onlySetIfEmpty, subSetToSearch)
	{
		$scope.mainState.errorMsg = false;

		$scope.showHotel = $scope.localState.selectedFilterHotel;
		
		//-----------------------------------
			
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
			//$scope.localState.searchFilter.city = $scope.mainState.selectedCity;
			//$scope.localState.searchFilter = {};
			$scope.localState.searchFilter.currentHotelAccessCode = "-1";
		}
		else { //nothing show
			//$scope.localState.searchFilter = {};
			$scope.localState.searchFilter.currentHotelAccessCode = "-1";
		}

		if(!subSetToSearch)
		{
			subSetToSearch = $scope.mainState.hotelArray;
		}

		$scope.localState.filterHotels = $filter('filter')(subSetToSearch, $scope.localState.searchFilter, true);//,  $scope.localState.strictSearch);

		$scope.localState.noHotelFound = $scope.localState.filterHotels.length==0 && $scope.mainState.hotelArray.length>0;

		$scope.localState.copyHotels = angular.copy($scope.localState.filterHotels);


	};
	
	 

	//############ END FILTER ########################

	$scope.hideFocus = function (style) {
		
		if(!$rootScope.isSmartDevice)
		{
			return;
		}
		
		var focusElts =document.getElementsByClassName("hideOnFocus");

		if(focusElts && focusElts.length>0)
		{
			for(var f=0; f<focusElts.length; f++)
			{
				focusElts[f].style.display = style;
			}
		}
	}
	
	$scope.onHotelCodeInput = function () {
		$scope.checkinService.hotelCode = $scope.localState.selectedHotelCode;
		
		
		
	}

	//##################### WATCHERS ####################

	$scope.$on('mainState.globalIntervalCheck', function (next, current) {

		if ($state.current.name == "app.checkin") {
			setTimeout(function () {
				if ($scope.hotelState.profileData) {
					$scope.getUpdateModuleData();
				}
				else {
					$scope.hotelState.initState().then(function (reponse) {
						$scope.getUpdateModuleData();
					});
				}
			}, 1000)

		}

	});

	
	
	////###################### INIT BLOCK ###############

	$scope.getUpdateModuleData = function () {

		$scope.hotelState.updateState();
		$scope.hotelState.checkNextRouteState();

		$rootScope.showLoading(false);

		$scope.checkinService.resetWholeInfo();

		//in main.js
		$scope.getUpdateMainHotelList();

		$scope.localState.showCityLoading = true;
		$scope.getUpdateMainHotelCitiesSelectorList().then(function () {
			$scope.localState.showCityLoading = false;
		});

		setTimeout($scope.hideAside, 1000);
	};

	var popupInitiated = false;

	$scope.showPopup = function (ev) {

		if ($scope.mainState.hidePopup || $stateParams.hotelCode) {
			return;
		}

		if ($scope.hotelState.profileData.hideCheckinPopup || $scope.hotelService.getCookie('hotelicohideCheckinPopup') == "true") {
			return;
		}

		if(popupInitiated){
			return;
		}

		popupInitiated = true;

		var confirm = $mdDialog.confirm( {
			clickOutsideToClose: true
			})
			.title($filter('translate')('page.checkin.popupHeader'))
			.content($filter('translate')('page.checkin.popupText'))
			.ariaLabel('Lucky day')
			.ok('OK')
			.cancel($filter('translate')('alert.noPopupMore'))
			.targetEvent(ev);

		$mdDialog.show(confirm).then(function () {
			;
		}, function () {
			$scope.hotelState.profileData.hideCheckinPopup = true;
			$scope.hotelService.setCookie('hotelicohideCheckinPopup', true);

			
			$scope.hotelState.submitProfileData(true);
		});
	};

	
	
	$scope.hotelState.getDeferredState().then(function(stateObj)
	{

		$scope.initMainState(stateObj);
		
		//if($rootScope.relocateLogin())
		//{
		//	//$scope.loginService.resetRequestedLoginState();
		//	$rootScope.showLoading(false);
		//	return;
		//}

	});
	
	function initSystemLanguages()
	{
		for (var i = 0; i < $scope.localState.availableSystemLanguages.length; i++) {

			if ($scope.localState.availableSystemLanguages[i].langKey == $scope.hotelState.getPrefferedLanguage()) {
				$scope.localState.availableSystemLanguages[i].ticked = true;
			}
		}
	};
	
	function preLoadDemoHotel()
	{
		var demoHotelIdElt = document.getElementById("demoHotelId");
		
		if(demoHotelIdElt && (demoHotelIdElt.innerText || demoHotelIdElt.innerHTML))
		{
			var demoHotelId = demoHotelIdElt.innerText? demoHotelIdElt.innerText : demoHotelIdElt.innerHTML;

			$scope.getUpdateMainHotelObject(demoHotelId).then(function(demoHotel)
			{
				$scope.updateLocalFilter(null, [demoHotel])
			});
		}
		
	}

	$rootScope.$on('gpsCityUpdated', function(event, obj) {
		
		if(obj)
		{
			$scope.localState.preFilterCityParam = obj;

			$rootScope.$broadcast('hotelCitySelectorChangeEvent', obj );
		}
	});
	
	function initCheckin() {

		if($scope.hotelState.requestCheckinGPS && navigator && navigator.geolocation)
		{
			navigator.geolocation.getCurrentPosition(function(position) {
				$scope.hotelService.geoLocation = {latitude: position.coords.latitude, longitude: position.coords.longitude};
				$scope.hotelState.submitProfileData(true);
			});
		}
		
		
		//$scope.showPopup();
		$scope.getUpdateModuleData();

        //$rootScope.relocateLogin();
		
		initSystemLanguages();
		
		$scope.updateLocalFilter(null, []);
		
		setTimeout(preLoadDemoHotel, 500);

		setTimeout(function(){
			if($scope.hotelState.profileData.checkedIn && $state.current.name=="app.checkin")
			{
				$scope.hotelState.checkNextRouteState();
			}
		}, 1500);
	}

	initCheckin();
	
	if(!$stateParams.hotelCode && !$scope.hotelState.profileData.hotelCode && $scope.hotelState.profileData.id==0)
	{
		$scope.hotelState.profileData.hotelCode = $scope.hotelService.getCookie('hotelicoNoLoginHotelCode');
	}
	
	//CHECK HOTELCODE Param
	if($stateParams.hotelCode || $scope.hotelState.profileData.hotelCode)
	{
		if($scope.hotelState.profileData.checkedIn && $scope.hotelState.profileData.hotelCode)
		{
			return;
		}
		
		$scope.localState.showManualCheckin = false;

		$rootScope.showLoading(true);
		
		if(!$scope.checkinService)
		{
			$scope.checkinService = HotelCheckin;
		}
		
		$scope.localState.selectedHotelCode = $stateParams.hotelCode? $stateParams.hotelCode : $scope.hotelState.profileData.hotelCode;
		$scope.onHotelCodeInput();
		$scope.onCheckinSubmit($scope.localState.selectedHotelCode);
	}
	else{
		$scope.localState.showManualCheckin = true;
		setTimeout(showManualSelection, 1000);
	}
	
	//RELOCATE BLOCK IF ALREADY CHECKEDIN
	if($scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.checkedIn)
	{
		$scope.getUpdateMainHotelObject($scope.hotelState.profileData.hotelId).then(function(hotelObj){
			
			if(hotelObj.id>0)
			{
				$state.go($scope.hotelState.profileData.logged? "app.hotel" : "app.hotelPreview");
			}
		});
		
	}
	
	 
	
}]);
 
