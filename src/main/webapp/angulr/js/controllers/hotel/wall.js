'use strict';
app
//angular.module('hotelApp.wall', ['ngCookies', 'ngResource', 'hotelApp.wallService',  'hotelApp.customerDto', 'hotelApp.sessionCustomerService'])

.controller('WallContr', 
	['$scope', '$rootScope', '$cookies', '$location', '$resource', 'WallService', 'SessionCustomer','$mdDialog', '$filter',
	function($scope, $rootScope, $cookies, $location, $resource, WallService, SessionCustomer, $mdDialog, $filter) {

		$scope.localState = {
			 ready : 0,
			//TODO eugen: load old messages from DB
			 wallMessages : [],
			 waitingWallMessages : [],
			newWallMessage: "",
			max: 1000,
			notSendMessageArray: [],
			dataLoaded: false,
			notSendCounter : 0
		}

		var customersByWallService = $resource('./wall/customers/:requesterId/hotel/:hotelId', {requesterId: '@requesterId', hotelId: '@hotelId'}, {'getWallCustomers': {method: 'GET', isArray: true}});

		var WallMessageService = $resource('./wall/messages/customer/:customerId/hotel/:hotelId', { customerId: '@customerId', hotelId:'@hotelId' }, {'getMessages': {method: 'GET', isArray: true}});
		
		var WallRestMessageService = $resource('./wall/messages/:initId', { initId:'@initId' }, {'getMessages': {method: 'GET', isArray: true}, update: {
			method: "PUT",
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		}});

		var inputElt;
		
		 $scope.addMessage = function() {
			 
			 if($scope.localState.hotelId)
			 {
				 var msgObj = null;

				 if($scope.localState.newWallMessage.length==0)
				 {
					 return;
				 }

				 msgObj = WallService.sendWall($scope.localState.newWallMessage, $scope.localState.senderId, $scope.localState.hotelId, $scope.hotelState.profileData.hotelStaff, null);
				 
				 $scope.localState.waitingWallMessages.push(msgObj);


			 }
			 $scope.localState.newWallMessage = "";

			 if(inputElt==null)
			 {
				 inputElt = document.getElementById("wallInput");
			 }

			 if(inputElt!=null)
			 {
				 inputElt.focus();
			 }
			 
			 // WEG TODO Eugen
			 setTimeout(checkOutgoingMsg, 10000);

		 };
		
		function scrollDown()
		{
			$scope.scrollDownById('chatContainer');
		}
		
		$scope.removeWallWaitingMessage = function(message)
		{
			
			for(var w=0; w< $scope.localState.waitingWallMessages.length; w++)
			{
				if($scope.localState.waitingWallMessages[w].initId == message.initId)
				{
					$scope.localState.waitingWallMessages.splice(w,1);
					$scope.localState.notSendCounter = 0;
					$scope.localState.showResend = false;
					WallService.removeWallMsg(message.initId);
					break;
				}
			}
		};
		
		$scope.addUpdateWallMessage = function(message)
		{
			var wallMessageUpdated = false;
			
			for(var w=$scope.localState.wallMessages.length-1; w>=0 ; w--)
			{
				if($scope.localState.wallMessages[w].initId == message.initId || $scope.localState.wallMessages[w].id == message.id)
				{
					$scope.localState.wallMessages[w] = message;
					wallMessageUpdated = true;
					break;
				}
			}

			if(!wallMessageUpdated)
			{
				$scope.localState.wallMessages.push(message);
			}

		};
		
		var checkOutgoingMsg = function()
		{
			$scope.localState.notSendMessageArray = [].concat(WallService.getNotSendWall());

			if($scope.localState.notSendMessageArray.length>0)
			{
				$scope.localState.notSendCounter++;

				if($scope.localState.notSendCounter>3)
				{
					//$scope.openModal();
					$scope.localState.showResend = true;
					$scope.localState.notSendCounter = 0;
				}
				else {
					$scope.localState.showResend = false;
					for(var m=0; m<$scope.localState.notSendMessageArray.length; m++)
					{
						var nextMsg = $scope.localState.notSendMessageArray[m];

						var nextMsgJson = null;

						try
						{
							var nextMsgJson = JSON.parse(nextMsg);
						}
						catch(e)
						{
							nextMsgJson = nextMsg;
						}

						if(nextMsgJson.message)
						{
							nextMsgJson.message = $scope.hotelService.myDecode(nextMsgJson.message);
						}

						$scope.localState.notSendMessageArray[m] = nextMsgJson;
						//alert("try to resend " + nextMsg.message);
						$scope.resendWallRessource(nextMsgJson);
					}
				}
				//check later result
				//setTimeout(checkOutgoingMsg, 3000);
			}
			else {
				$scope.localState.notSendCounter = 0;
			}
		};

		$scope.resendWallRessource = function(msgJson){

			//if(nextMsg)
			{
				var wallObj = new WallRestMessageService();
				wallObj.message = msgJson.message;
				wallObj.senderId = msgJson.senderId;
				wallObj.hotelId = msgJson.hotelId;

				wallObj.hotelStaff = msgJson.hotelStaff;
				wallObj.initId = msgJson.initId;
				
				wallObj = $scope.hotelService.encodeWall(wallObj);
				
				wallObj.$save(function(response){
					if(response && response.initId>0)
					{
						var decodedWall = $scope.hotelService.decodeWall(response);
						
						$scope.addUpdateWallMessage(decodedWall);

						$scope.removeWallWaitingMessage(decodedWall);
					}
				});

				setTimeout(checkOutgoingMsg, 5000);

				return msgJson;
			}
			
		};
        
        // ##################################
        $scope.focusChatInput = function()
        {
            scrollDown();
            $scope.checkVisibleHeight();
            setTimeout(function(){scrollDown()}, 500);
        }
        
        //####################### on virtual keyboard

        function recalculateHeight(contentDivName)
        {
            if(!$rootScope.isSmartDevice)
            {
                return;
            }

            var headerHeight = 52;

            var fullVisibleHeight = document.documentElement.clientHeight;

            //alert(fullVisibleHeight); 559 337
			var hotelicoHeader = document.getElementById("hotelicoHeader");

			if(hotelicoHeader)
			{
				headerHeight = hotelicoHeader.clientHeight;
			}
			
            $scope.localState.chatVisibleHeight = (fullVisibleHeight - headerHeight) + "px";

            var chatContent = document.getElementById("wallContent");

            if(chatContent)
            {
				var changed = chatContent.style.height != $scope.localState.chatVisibleHeight;

				chatContent.style.height = $scope.localState.chatVisibleHeight;

				if(changed){
					scrollDown();
				}
            }

            //scrollDown();
        }

        $scope.checkVisibleHeight = function()
        {
            recalculateHeight();

            setTimeout(recalculateHeight, 400);
			
			//scrollDown();

        }

        //##################### INIT ###################################
        
        // ##################################
		
		WallService.receiveWall().then(null, null, function(message) {
			 
			 if( message.hotelId==$scope.localState.hotelId )
			 {
				 var decodedWall = $scope.hotelService.decodeWall(message);
				 
				 $scope.addUpdateWallMessage(decodedWall);

				 $scope.removeWallWaitingMessage(decodedWall);

			 }
		 });

		$rootScope.$on('checkWallSocketConnection', function(event, mass) {
			WallService.checkSocketConnection();
		});
		
		function initWall()
		{
            $rootScope.hideAside();
			if($scope.hotelNotification.notificationObj)
			{$scope.hotelNotification.notificationObj.hotelUnreadWallNumber = false;}
			//var sessionCustomer = SessionCustomer.get(function () {

				//$scope.hotelState.setProfileData(sessionCustomer);

				if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
				{
					$scope.localState.senderId = $scope.hotelState.profileData.id;

					if($scope.hotelState.profileData.hotelId && $scope.hotelState.profileData.hotelId>0)
					{
						$scope.localState.hotelId =  $scope.hotelState.profileData.hotelId;
						//$scope.hotelState.getCurrentHotel($scope.localState.hotelId);
						//$scope.hotelState.getHotelCustomers($scope.localState.hotelId);
						$scope.localState.ready = 1;
						
						WallService.resubscribeWithHotelData($scope.hotelState);
						
						var requesterId = $scope.hotelState.getRequesterId();
						
						//Get all wall messages
						WallMessageService.getMessages({customerId: requesterId, hotelId: $scope.localState.hotelId}, function(messageArray)
						{
							for(var m=0; m<messageArray.length; m++)
							{
								var nextOldMessage = $scope.hotelService.decodeWall(messageArray[m]);
								$scope.localState.wallMessages.push(nextOldMessage);
							}

							$scope.localState.dataLoaded = true;

							scrollDown();
						});

						//Get Info about current wall customers
						customersByWallService.getWallCustomers({requesterId:$scope.hotelState.profileData.id, hotelId: $scope.localState.hotelId}, function(wallCustomersList){
							if(wallCustomersList && wallCustomersList.length>0)
							{
								for(var w=0; w<wallCustomersList.length; w++)
								{
									var nextWallCustomer = wallCustomersList[w];
									$scope.hotelState.allCustomersById[nextWallCustomer.id] = nextWallCustomer;
								}
							}
						}, function(error){
							var err;
						});
						
						$rootScope.showLoading(false);

					}

				}

				$scope.showPopup();
				//imitate wallMessages change
				setTimeout(scrollDown, 2000);
				setTimeout($rootScope.hideLoading, 5000);
			
				var intervalID = setInterval(recalculateHeight, 1000);

			//});
		};

		$scope.showPopup = function(ev) {

			if($scope.mainState.hidePopup)
			{
				return;
			}

			if($scope.hotelState.profileData.hideWallPopup || $scope.hotelService.getCookie('hotelicohideWallPopup')=="true")
			{
				return;
			}
			
			var confirm = $mdDialog.confirm( {
				clickOutsideToClose: true
			})
				.title($filter('translate')('page.wall.popupHeader'))
				.content($filter('translate')('page.wall.popupText'))
				.ariaLabel('Lucky day')
				.ok('OK')
				.cancel($filter('translate')('alert.noPopupMore'))
				.targetEvent(ev);

			$mdDialog.show(confirm).then(function() {
				;

            }, function() {
				$scope.hotelState.profileData.hideWallPopup = true;
				$scope.hotelService.setCookie('hotelicohideWallPopup', true);

				$scope.hotelState.submitProfileData(true);
			});
		};
		
		
		$scope.hotelState.getDeferredState().then(function(){
            //$scope.showPopup();
            initWall();
		}, function(error)
		{
			//$rootScope
		});
		
		 
 }]);
