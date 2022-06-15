'use strict';
app
//angular.module('hotelApp.chatList', ['ngCookies', 'ngResource', 'hotelApp.chatService'])

.controller('ChatListContr', 
	['$scope', '$rootScope', '$location', '$resource', '$modal', '$stateParams', '$filter', '$state', 'CustomerDto',
	function($scope, $rootScope, $location, $resource, $modal, $stateParams, $filter, $state, CustomerDto) {

		$scope.localState = {
			
			ready : 0,
			showChatDiv : false,
	
			filterCity : "-1",
			filterHotelId :-1,
			
			selectedFilterHotel: undefined,
			selectedCustomerCity: undefined,
			
            hotelScope: $stateParams.hotelScope && $stateParams.hotelScope=="true",
            
			showCityLoading: false,
		 	showAllCustomersLoading: false,
		 	//searchFilter : {} ,
			
			strictSearch : true,

			notChatPartnerPageNumber: 1,
			//Hotel scope
			filterHotelStaffCustomers: [],
			filteredHotelCustomers: [],
			filteredOutOfHotelContactCustomers: [],
			
            //noContactFound: false,
            //noOtherContactFound: false,
			dataLoaded : false,
			
			//NOT Hotel scope
			filteredAllContactCustomers : [],
			filteredOtherCustomers : []
		};
		
		$scope.accordionStatus = {
			chatVipOpen: false,
			isChatVipDisabled: false
		};

		//GET ALL OTHER CHAT PARTNERS, THAT HAVE NO MESSAGES WITH ME
		var allNotContactCustomerService = $resource('./chat/allNotChatPartners/customer/:customerId/city/:filterCity/hotel/:filterHotelId/page/:pageNumber', {customerId:'@customerId', filterCity: '@filterCity', filterHotelId : '@filterHotelId', pageNumber: '@pageNumber'}, {'getCustomers': {method: 'GET', isArray: true}});
		var CustomerCitiesService = $resource('./customers/customers/:customerId/cities', {customerId:'@customerId'}, {'getCities': {method: 'GET', isArray: true}});
		//GET not Hotel CHAT PARTNERS, THAT HAVE MESSAGES WITH ME
		//var notHotelChatPartnerService = $resource('./chat/notHotelChatPartners/customer/:customerId/city/:filterCity/hotel/:filterHotelId', {customerId:'@customerId', filterCity: '@filterCity',filterHotelId : '@filterHotelId'}, {'getCustomers': {method: 'GET', isArray: true}});
		var allContactCustomerService = $resource('./chat/allContactChatPartners/customer/:customerId/city/:filterCity/hotel/:filterHotelId', {customerId:'@customerId', filterCity: '@filterCity',filterHotelId : '@filterHotelId'}, {'getCustomers': {method: 'GET', isArray: true}});


		$scope.loadMoreVip = function(){

			var nowSize = $scope.localState.filteredOtherCustomers.length;
			$scope.localState.notChatPartnerPageNumber = ((nowSize-nowSize%25)/25) + 1;

			if($scope.localState.notChatPartnerPageNumber>1)
			{
				$scope.loadVipChat();
			}
		};
		
		$scope.loadVipChat = function(sessionCustomer, filterCity, filterHotelId)
		{
			if($scope.accordionStatus.chatVipOpen || filterCity || filterHotelId)// && (!$scope.hotelState.allNotContactCustomers ||$scope.hotelState.allNotContactCustomers.length==0))
			{
				$rootScope.showLoading(true);
				
				if(!sessionCustomer)
				{
					sessionCustomer = $scope.hotelState.profileData;
				}
				
				var filterCityParam = filterCity? filterCity : $scope.localState.filterCity;
				var filterHotelIdParam = filterHotelId? filterHotelId: $scope.localState.filterHotelId;
				
				var allNotContactCustomers = allNotContactCustomerService.getCustomers({customerId:sessionCustomer.id, filterCity: filterCityParam, filterHotelId: filterHotelIdParam, pageNumber: $scope.localState.notChatPartnerPageNumber }, function() {

                    for(var n=0; n<allNotContactCustomers.length; n++)
                    {
						allNotContactCustomers[n] = $scope.hotelService.decodeCustomer(allNotContactCustomers[n]);
						$scope.hotelState.allCustomersById[allNotContactCustomers[n].id] = allNotContactCustomers[n];
                    }

					//$scope.hotelState.allNotContactCustomers = allNotContactCustomers;
					$scope.localState.showAllCustomersLoading = false;

					updateLocalFilter();
					
					$rootScope.showLoading(false);


				}, function(error)
				{					
					$rootScope.showLoading(false);
					$scope.mainState.errorMsg = error;
				});
			}
		}

		/**
		 * all contacts, without hotel scope
		 * @param sessionCustomer
		 */
        $scope.loadAllContactList = function(sessionCustomer)
		{
			//if($scope.accordionStatus.chatVipOpen && (!$scope.hotelState.allChatCustomers ||$scope.hotelState.allChatCustomers.length==0))
			{
				$rootScope.showLoading(true);
				
				if(!sessionCustomer)
				{
					sessionCustomer = $scope.hotelState.profileData;
				}
				
				//if($scope.localState.hotelScope)
				{
					//GET not Hotel CHAT PARTNERS, THAT HAVE MESSAGES WITH ME
					var contactCustomers = allContactCustomerService.getCustomers({customerId:$scope.hotelState.getRequesterId(), filterCity: $scope.localState.filterCity, filterHotelId: $scope.localState.filterHotelId}, function() {

						for(var n=0; n<contactCustomers.length; n++)
						{
							contactCustomers[n] = $scope.hotelService.decodeCustomer(contactCustomers[n]);
							$scope.hotelState.allCustomersById[contactCustomers[n].id] = contactCustomers[n];
						}

						$scope.hotelState.contactCustomers = contactCustomers;
						$scope.localState.showAllContactCustomersLoading = false;

						updateLocalFilter();
						$scope.localState.dataLoaded = true;
						$rootScope.showLoading(false);

					}, function(error)
					{					
						$rootScope.showLoading(false);

						$scope.mainState.errorMsg = error;
					});
					//in hotelScope we have already all contact customers
					return;
				}
			}
		}
		
		//################ filter Cities and Hotels #########################
		
		$scope.initCustomerCitiesSelector = function()
		{
			var senderId = ($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)? $scope.hotelState.profileData.id : -1;
			
			CustomerCitiesService.getCities({customerId: senderId}, function(cityArray) {
				
				var dummyAllCity = new CustomerDto();

				dummyAllCity.name = $filter('translate')('alert.info.allCities');
				dummyAllCity.city = dummyAllCity.name;
				
				$scope.localState.customerCitiesArray = [].concat(dummyAllCity);
				
				for(var c=0; c<cityArray.length; c++)
				{
					if(cityArray[c].city && cityArray[c].city!="null")
					{
						$scope.localState.customerCitiesArray.push(cityArray[c]);
					}
				}
				
				$scope.localState.showCityLoading=false;
				preFilterCityParam();
			});

			preFilterCityParam();
		};
		
		/**
		 * pre-select param city
		 */
		function preFilterCityParam(){

			if($scope.localState.preFilterCityParam && $scope.localState.customerCitiesArray)
			{
				for(var c=0; c<$scope.localState.customerCitiesArray.length; c++)
				{
					if($scope.localState.customerCitiesArray[c].city == $scope.localState.preFilterCityParam)
					{
						$scope.localState.selectedCustomerCity = $scope.localState.customerCitiesArray[c];

						//$scope.emptyPreviousSearch();

						$scope.onChangeCustomerCitySelector();

						break;
					}
				}

				//one time usage!!!
				$scope.localState.preFilterCityParam = false;
			}
		};
		
		$scope.onChangeCustomerCitySelector = function()
		{            
			var selectedCity = $scope.localState.selectedCustomerCity && $scope.localState.selectedCustomerCity.city? $scope.localState.selectedCustomerCity.city : "-1";
			//var senderId = ($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)? $scope.hotelState.profileData.id : -1;

			$scope.localState.showCityHotelLoading = selectedCity && selectedCity!="-1";
			
			if(selectedCity && selectedCity!="-1")
			{
				//$scope.searchFilter.hotelCity = selectedCity;
				$scope.localState.filterCity = selectedCity ;
			}
			else {
				resetLocalFilterValues();
			}

			//TODO Eugen: update vip, if city was changed :((((
			if(!$scope.localState.hotelScope)
			{
				$scope.loadVipChat();
			}
			
			//##### new Search json
			updateLocalFilter();
			
			//EUGEN: this array already loaded in hotelList!!!
			$scope.getMainHotelsByCity(selectedCity, false).then(function(hotelsArray) {
				$scope.localState.hotelsFilteredByCityArray = hotelsArray;
				$scope.localState.filteredHotelsNOTEmpty = hotelsArray.length>0;
				$scope.localState.showCityHotelLoading = false;

				if($scope.localState.preFilterHotelIdParam)
				{
					for(var h=0; h<hotelsArray.length; h++)
					{
						if(hotelsArray[h].id == $scope.localState.preFilterHotelIdParam)
						{
							$scope.localState.selectedFilterHotel = hotelsArray[h];
							$scope.onChangeFilterHotelSelector($scope.localState.selectedFilterHotel.id);
							break;
						}
					}

					//one time usage!!!
					$scope.localState.preFilterHotelIdParam = false;
					
					//open other contacts, if hotelId pre filter Param!!!
					$scope.accordionStatus.chatVipOpen = true;
					$scope.loadVipChat();
				}

			});
			
		};

		$rootScope.$on('chatListChangeEvent', function(event, mass) { updateLocalFilter(); });

		function updateLocalFilter(){

			 if($scope.localState.hotelScope)
			 {
				 //HotelStaff, but only from my hotel
				 var hotelStaffCustomerFilter = $scope.getInitHotelStaffCustomerFilter();
				 hotelStaffCustomerFilter.hotelId = parseInt($scope.hotelState.profileData.hotelId);
				 

				 //normal customers, but only from my hotel
				 var hotelCustomerFilter = $scope.getInitHotelCustomerFilter();
				 hotelCustomerFilter.hotelId = parseInt($scope.hotelState.profileData.hotelId);
				 hotelCustomerFilter.inMyHotel = true;


				 //contact customers, but not from my hotel
				 var outOfHotelCustomerFilter = $scope.getInitChatCustomerFilter();
				 //TODO Eugen: not in my hotel!!!!
				 outOfHotelCustomerFilter.inMyHotel = false;


				 ////not in hotelScope filter on hotel!
				 //if($scope.localState.filterHotelId){
					// hotelStaffCustomerFilter.hotelId = $scope.localState.filterHotelId;
					// hotelCustomerFilter.hotelId = $scope.localState.filterHotelId;
					// outOfHotelCustomerFilter.hotelId = $scope.localState.filterHotelId;
				 //}
				 $scope.localState.filterHotelStaffCustomers = $filter('filter')($scope.hotelState.hotelStaffCustomers, hotelStaffCustomerFilter, true);//,  $scope.localState.strictSearch);

				 $scope.localState.filteredHotelCustomers = $filter('filter')($scope.hotelState.hotelCustomers, hotelCustomerFilter, true);//,  $scope.localState.strictSearch);
				 
				 if($scope.localState.filteredHotelCustomers.length>0 && $scope.hotelState.profileData.id>0)
				 {
					 for(var i=0; i<$scope.localState.filteredHotelCustomers.length; i++) 
					 {
						 if($scope.localState.filteredHotelCustomers[i].id == $scope.hotelState.profileData.id)
						 {
							 //remove mySelf from array!
							 $scope.localState.filteredHotelCustomers.splice(i, 1);
							 break;
						 }
					 }
				 }
				 
                 $scope.localState.filteredOutOfHotelContactCustomers = $filter('filter')($scope.hotelState.contactCustomers, outOfHotelCustomerFilter, true);//,  $scope.localState.strictSearch);

				 //for(var e=0; e<$scope.hotelState.contactCustomers; e++){
					// if($scope.hotelState.contactCustomers[e]!=$scope.hotelState.profileData.hotelId)
				 //}
				 
			 }
			 else{

				 //all contact customers
				 var contactFilter = $scope.getInitChatCustomerFilter();
				 
				 //all not contact customers
				 var outerChatCustomerFilter = $scope.getInitOuterChatCustomerFilter();

				 if($scope.localState.filterCity && $scope.localState.filterCity!="-1" && $scope.localState.filterCity.toUpperCase() != $filter('translate')('alert.info.allCities').toUpperCase()){
					 outerChatCustomerFilter.city = $scope.localState.filterCity;
					 contactFilter.city = $scope.localState.filterCity;
				 }

				 if($scope.localState.filterHotelId && $scope.localState.filterHotelId!="-1" && $scope.localState.filterHotelId>0){
					 outerChatCustomerFilter.hotelId = $scope.localState.filterHotelId;
					 contactFilter.hotelId = $scope.localState.filterHotelId;
				 }
				 
				  $scope.localState.filteredAllContactCustomers = $filter('filter')($scope.hotelState.contactCustomers, contactFilter, true);//,  $scope.localState.strictSearch);

                  $scope.localState.noContactFound = $scope.localState.filteredAllContactCustomers.length==0 && $scope.hotelState.contactCustomers.length>0;
                 
                 //Eugen: heavy filter use only if accordion is open!!!
                 if($scope.accordionStatus.chatVipOpen)
                 {
                     //TODO Eugen: upload only 20 per request
                     $scope.localState.filteredOtherCustomers = $filter('filter')($scope.hotelState.allCustomersById, outerChatCustomerFilter, true);//,  $scope.localState.strictSearch);
                     $scope.localState.noOtherContactFound = $scope.localState.filteredOtherCustomers.length==0 && $scope.hotelState.allCustomersById.length>0;
                 }
			 }
			
			 
		 }
		
		$scope.onChangeFilterHotelSelector = function()
		{
			$scope.localState.filterHotelId = $scope.localState.selectedFilterHotel && $scope.localState.selectedFilterHotel.id>0? $scope.localState.selectedFilterHotel.id : -1;

			///### new search json
			updateLocalFilter();
		}

		//##################### INIT ###################################
		$scope.initChatListForCustomer = function(sessionCustomer)
		{
			//if(sessionCustomer.id && sessionCustomer.id>0)
			{
				$scope.localState.senderId = $scope.hotelState.getRequesterId();

				//chatlist!!!
				if($scope.localState.hotelScope)
				{
					if(sessionCustomer.hotelId && sessionCustomer.hotelId>0)
					{
						$scope.hotelState.getCurrentHotel(sessionCustomer.hotelId, sessionCustomer).then(function(resp)
						{
							//LOAD EVERY TIME -> HOTEL CAN CHANGE: loads only contacts outer join of hotel scope!
							$scope.hotelState.getHotelCustomers($scope.localState.hotelId).then(function(resp){
								updateLocalFilter();
								$scope.localState.dataLoaded = true;

							});//.then( function(customers)

							$scope.loadAllContactList();

							//);
						});

						$scope.localState.hotelId =  sessionCustomer.hotelId;
						$scope.localState.ready = 1;
					}
					
				}
				else //NOT HOTEL SCOPE
				{
					$scope.initCustomerCitiesSelector();
					$scope.loadAllContactList();
					$scope.loadVipChat($scope.hotelState.profileData, $stateParams.filterCity, $stateParams.filterHotelId);
				}
			}
		}

		$scope.switchVipCustomers = function()
		{
			$scope.accordionStatus.chatVipOpen != $scope.accordionStatus.chatVipOpen; 
			if($scope.accordionStatus.chatVipOpen)
			{
				$scope.loadVipChat();
			}
		}
		
		//##################### WATCHERS ####################

		$scope.$on('mainState.globalIntervalCheck', function(next, current) {

			if($state.current.name == "app.chatList")
			{
				$scope.getUpdateModuleData();
			}

		});

		function resetLocalFilterValues(){
			$scope.localState.filterCity = "-1";
			$scope.localState.filterHotelId = -1;
		}
		
		
		function getUpdateModuleData(){
            
			//Eugen: temporell all is hotelScope
			$scope.localState.hotelScope = $scope.hotelState.profileData.checkedIn;

			$scope.localState.showCityLoading=true;
			$scope.localState.showAllCustomersLoading=true;
			resetLocalFilterValues();

			////#### init chat json

			//$scope.hotelState.checkNextRouteState();
			
			//if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
			{
				$scope.initChatListForCustomer($scope.hotelState.profileData);

			}

			setTimeout($rootScope.hideLoading, 5000);
			updateLocalFilter();

		}

		function initChatList()
		{ 
			$rootScope.hideAside();

			$scope.localState.preFilterCityParam = $stateParams.filterCity;
			$scope.localState.preFilterHotelIdParam = $stateParams.filterHotelId;

			getUpdateModuleData();
		}
		
		//$scope.hotelState.getDeferredState().then(function(){
        //
         //  initChatList();
		//}, function(error){
		//	//$rootScope.hideAside();
         //   //
		//	//$scope.localState.preFilterCityParam = $stateParams.filterCity;
		//	//$scope.localState.preFilterHotelIdParam = $stateParams.filterHotelId;
         //   //
		//	//getUpdateModuleData();
		//});

		initChatList();

		setTimeout(function()
        {
            //SHOW LOGIN AFTER RENDERING CHATLIST
            if(!$scope.hotelState.profileData.logged && $state.current.name=="app.chatList")
            {
                var stateParams = {};

                //TODO Eugen: This state can be reached only with valid login! Otherwise open a login popup!!!
                //save requested state in loginService
                $scope.loginService.setLoginRequestedState("app.chatList", stateParams);
                $scope.openModal("login");
                return;
            }
        }, 5000);
       
		
 }])
	 
; 
