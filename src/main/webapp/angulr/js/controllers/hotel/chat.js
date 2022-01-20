'use strict';
app
//angular.module('hotelApp.chat', ['ngCookies', 'ngResource', 'hotelApp.chatService',  'hotelApp.customerDto', 'hotelApp.sessionCustomerService'])

.controller('ChatContr', 
	['$scope', '$rootScope', '$location', '$resource', 'ChatService', 'CustomerDto', 'SessionCustomer', '$modal', '$stateParams', '$state',
	function($scope, $rootScope, $location, $resource, ChatService, CustomerDto, SessionCustomer, $modal, $stateParams, $state) {

		$scope.localState = {
			ready : 0,
			showChatDiv : false,
			senderId: 0,
			chatPartner : null,
	
			//TODO eugen: load old messages from DB
			messages : [],
			waitingMessages : [],
			
			unreadMessagesCounter : [],
	
			filterCity : "all",
			filterHotelId : 0,

			 newMessage : "",
		 	 max : 1000,

		 	 waitingMsg : [],

		 	notSendMessageArray : [],
			
		 	notSendCounter : 0,

            chatVisibleHeight : "90%"
		};
		
		$scope.accordionStatus = {
			chatVipOpen: false,
			isChatVipDisabled: false
		};


		var ChatMessageService = $resource('./chat/messages/sender/:customerId/receiver/:receiverId', {customerId:'@customerId', receiverId:'@receiverId' }, {'getMessages': {method: 'GET', isArray: true,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}}});
		//var CheckReadMessageService = $resource('./chat/markRead/sender/:customerId/messageId/:messageId', {customerId:'@customerId', messageId:'@messageId' }, {'confirm': {method: 'GET'}});
		
		//var CheckReadMessageService = $resource('./chat/markReadMessage/receiverId/:customerId/messageId/:messageId', {customerId:'@customerId', messageId:'@messageId' }, {'confirm': {method: 'GET',
		//	headers: {
		//		'Content-Type': 'application/json;charset=UTF-8',
		//		'Accept': 'application/json, text/plain, */*'
		//	}}});
		
        var CheckReadSenderService = $resource('./chat/markReadChat/receiverId/:receiverId/senderId/:senderId/maxSeenChatMessageId/:maxSeenChatMessageId', {receiverId:'@receiverId', senderId:'@senderId', maxSeenChatMessageId:'@maxSeenChatMessageId' }, {'clear': {method: 'GET',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}}});
		
        var ChatRestMessageService = $resource('./chat/messages/:initId', { initId:'@initId' }, {'getMessages': {method: 'GET', isArray: true,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}}, 
			update: { method: "PUT",
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		}});
		
		function emptyUnreadCounter(noConfirmToService){
			
			//remove unread counter!!!
			if ($scope.hotelNotification.notificationObj && $scope.hotelNotification.notificationObj.unreadChatProSenderCount && $scope.hotelNotification.notificationObj.unreadChatProSenderCount[$scope.localState.receiverId]) {
				
				$scope.hotelNotification.notificationObj.unreadChatProSenderCount[$scope.localState.receiverId] = 0;
				
				//if(!noConfirmToService)
				//{
				//	CheckReadSenderService.clear({receiverId: $scope.hotelState.profileData.id, senderId: $scope.localState.receiverId, maxSeenChatMessageId : });
				//}
			}
		};
		
		$scope.getUpdateModuleData = function(sessionCustomer)
		{
			if(!sessionCustomer)
			{
				sessionCustomer = $scope.hotelState.profileData;
			}
			
			if(sessionCustomer.id && sessionCustomer.id>0)
			{
				$scope.localState.senderId = sessionCustomer.id;

				//if chat with one partner
				if($stateParams.receiverId && !isNaN($stateParams.receiverId) && $stateParams.receiverId>0)
				{
					$rootScope.showLoading(false);
					
					$scope.localState.receiverId = $stateParams.receiverId;

					$scope.localState.chatPartner = CustomerDto.get({id: $scope.localState.receiverId, requesterId: $scope.hotelState.profileData.id});

					$rootScope.chatPartner = $scope.localState.chatPartner;

					$rootScope.updateHeaderTitle();

					$scope.localState.showChatDiv = true;

					//update route info and header title
					$scope.hotelState.checkNextRouteState();
					
					//TODO Eugen: check if already loaded!!!
					if(!$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId] || $scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId].length==0) {
						
						ChatMessageService.getMessages({customerId: sessionCustomer.id, receiverId: $scope.localState.receiverId}, function (messageArray) {
							
							if(!$scope.hotelState.chatHistoryByPartnerId)
							{
								$scope.hotelState.chatHistoryByPartnerId = [];
							}
							
							if(!$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId])
							{
								$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId] = [];
							}
							
							//save seen messageId, no duplicates
							var seenMsgId = [];
							
							var maxReceiverMessageId = 0;
							
							for (var m = 0; m < messageArray.length; m++) {
								
								var nextOldMessage = $scope.hotelService.decodeChatMessage(messageArray[m]);
								
								if(seenMsgId[nextOldMessage.initId])
								{
									continue;
								}								
								
								seenMsgId[nextOldMessage.initId] = true;
								
								//nextOldMessage.message = decodeURIComponent(nextOldMessage.message);
								//nextOldMessage.sendTime = new Date(nextOldMessage.sendTime);
								
								if(nextOldMessage.message == "22")
								{
									var a = 6;
								}
								
								//mark oldMessage as read
								if(maxReceiverMessageId < nextOldMessage.initId && nextOldMessage.receiverId == $scope.hotelState.profileData.id && $state.current.name=="app.chat" && $stateParams.receiverId==nextOldMessage.senderId)
								{
									maxReceiverMessageId = nextOldMessage.initId;
								}
								
								$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId].push(nextOldMessage);
							}

							if(maxReceiverMessageId>0)
							{
								//$scope.mainState.readUncheckedMessages.push(maxReceiverMessageId);
                                CheckReadSenderService.clear({receiverId: $scope.hotelState.profileData.id, senderId: $scope.localState.receiverId, maxSeenChatMessageId : maxReceiverMessageId });

                            }
							
							emptyUnreadCounter();

						});
					}
					else{
						emptyUnreadCounter();
					}
				}
				//else //chatlist!!!
				//{
				//	if(sessionCustomer.hotelId && sessionCustomer.hotelId>0)
				//	{
				//		if(!$scope.hotelState.currentHotel || $scope.hotelState.currentHotel==undefined || $scope.hotelState.currentHotel.id==undefined || $scope.hotelState.currentHotel.id==0 )
				//		{
				//			$scope.hotelState.setCurrentHotel(sessionCustomer.hotelId, sessionCustomer);
				//		}
				//		
				//		$scope.localState.hotelId =  sessionCustomer.hotelId;
				//		$scope.hotelState.getHotelCustomers($scope.hotelId);
				//		$scope.localState.ready = 1;
				//	}
				//	
				//	//GET not Hotel CHAT PARTNERS, THAT HAVE MESSAGES WITH ME
				//	
				//	var contactCustomers = notHotelChatPartnerService.getCustomers({customerId:sessionCustomer.id, filterCity: $scope.localState.filterCity, filterHotelId: $scope.localState.filterHotelId}, function() {
                //
                 //       for(var n=0; n<contactCustomers.length; n++)
                 //       {
				//			$scope.hotelState.allCustomersById[contactCustomers[n].id] = contactCustomers[n];
                 //       }
                //
				//		$scope.hotelState.contactCustomers = contactCustomers;
                //
				//	}, function(error)
				//	{
				//		$scope.mainState.errorMsg = error;
				//	});
                //
                //
				//	//$scope.loadVipChat();
                //
				//	//$scope.loadCustomerCities();
                //
				//}
			}
		}
		
		function scrollDown()
		{
			$scope.scrollDownById('chatContainer');
		}
		
		var inputElt = null;
		
		$scope.addChatMessage = function(message)
		{
			
			for(var w=$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId].length-1; w>=0 ; w--)
			{
				if($scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId][w].initId == message.initId)
				{
					$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId][w] = message;
					return;
				}
			}

			if(!$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId])
			{
				$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId] = [];
			}

			$scope.hotelState.chatHistoryByPartnerId[$scope.localState.receiverId].push(message);

			emptyUnreadCounter(true);
			
			scrollDown();
		};
		
		$scope.focusChatInput = function()
		{
			scrollDown();
            $scope.checkVisibleHeight();
            setTimeout(function(){scrollDown()}, 500);
		}
		
		$scope.removeWaitingMessage = function(message)
		{
			for(var w=0; w< $scope.localState.waitingMessages.length; w++)
			{
				if($scope.localState.waitingMessages[w].initId == message.initId)
				{
					$scope.localState.waitingMessages.splice(w,1);
					$scope.localState.notSendCounter = 0;
					$scope.localState.showResend = false;
					ChatService.removeMsg(message.initId);

					break;
				}
			}
		};
		
		var checkOutgoingMsg = function()
		{
			$scope.localState.notSendMessageArray = [].concat(ChatService.getNotSendChat());
			
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
							nextMsgJson.message = decodeURIComponent(nextMsgJson.message);
						}
						
						$scope.localState.notSendMessageArray[m] = nextMsgJson;
						//alert("try to resend " + nextMsg.message);
						$scope.resendChatRessource(nextMsgJson);
					}
				}
				//check later result
				//setTimeout(checkOutgoingMsg, 3000);
			}
			else {
				$scope.localState.notSendCounter = 0;
			}
		};
		
		//$scope.resend = function(nextMsg){
		//	
		//	if(nextMsg)
		//	{
		//		ChatService.send(nextMsg.message, nextMsg.senderId, nextMsg.receiverId, $scope.hotelState.profileData.hotelStaff, nextMsg.initId);
		//	}
		//	else{
		//		for(var l=0; l<$scope.notSendMessageArray.length; l++)
		//		{
		//			var next = $scope.notSendMessageArray[l];
		//			ChatService.send(next.message, next.senderId, next.receiverId, $scope.hotelState.profileData.hotelStaff, next.initId);
		//		}
		//	}
		//	
		//	setTimeout(checkOutgoingMsg, 10000);
        //
		//}

		$scope.resendChatRessource = function(msgJson){

			


			//var isNew = false;
			//
			//if(!messageId)
			//{
			//	isNew = true;
			//	messageId = Math.floor(Math.random() * 1000000);
			//}
			//
			//var msgObj = JSON.stringify({ //MESSAGE CONTENT
			//	message: encodeURIComponent(message),
			//	hotelId : hotelId,
			//	senderId: senderId,
			//	//hotelStaff: hotelStaff,
			//	//sendTime: new Date(),
			//	messageId: messageId
			//})
			//
			//var msgJson = JSON.parse(msgObj);

			//if(nextMsg)
			{
				var chatRestObj = new ChatRestMessageService();
				chatRestObj.message = msgJson.message;
				chatRestObj.senderId = msgJson.senderId;
				//chatRestObj.hotelId = msgJson.hotelId;
				chatRestObj.receiverId = msgJson.receiverId,

				chatRestObj.hotelStaff = msgJson.hotelStaff;
				chatRestObj.initId = msgJson.initId;
				chatRestObj.$save(function(response){
					if(response && response.initId>0)
					{
						$scope.addChatMessage(response);

						$scope.removeWaitingMessage(response);
					}
				}, function(error){
					console.log(error);
				});

				setTimeout(checkOutgoingMsg, 10000);

				return msgJson;
				//ChatRestMessageService.update({messageId: messageId}, chatRestObj, function(response){
				//	
				//})
				//WallService.send(nextMsg.message, nextMsg.senderId, nextMsg.receiverId, $scope.hotelState.profileData.hotelStaff, nextMsg.initId);
			}

			//WallService.startNew();
			//
			//if(nextMsg)
			//{
			//	WallService.send(nextMsg.message, nextMsg.senderId, nextMsg.receiverId, $scope.hotelState.profileData.hotelStaff, nextMsg.initId);
			//}
			//else{
			//	for(var l=0; l<$scope.notSendMessageArray.length; l++)
			//	{
			//		var next = $scope.notSendMessageArray[l];
			//		WallService.send(next.message, next.senderId, next.receiverId, $scope.hotelState.profileData.hotelStaff, next.initId);
			//	}
			//}

			//setTimeout(checkOutgoingMsg, 1000);

		}

		 $scope.addMessage = function() {
			 
			 var msgObj = null;
			 
			 if($scope.localState.newMessage.length==0)
			 {
				 return;
			 }
			 
			 if($scope.localState.receiverId)
			 {
				 msgObj = ChatService.sendChat($scope.localState.newMessage, $scope.localState.senderId, $scope.localState.receiverId, $scope.hotelState.profileData.hotelStaff, null);
			 }
			
			 $scope.localState.waitingMessages.push(msgObj);
			 $scope.localState.newMessage = "";

			 if(inputElt==null)
			 {
				 inputElt = document.getElementById("chatInput");
			 }

			 if(inputElt!=null)
			 {
				 inputElt.focus();
			 }
			 
			 
			  setTimeout(checkOutgoingMsg, 10000);

		 };
		
		
		$scope.resetCurrentChat = function() {
		
			var url = $location.url();
			var urlSplit = url.split("/chat");
			
			var baseUrl = urlSplit[0] + "/chat";
			$location.path(baseUrl);
		 };
		
		 ChatService.receiveChat().then(null, null, function(message) {
			 
			 if( $scope.localState.receiverId && message.timestamp //ignore doppelt messages
				 &&
				 ((message.senderId==$scope.localState.senderId && message.receiverId==$scope.localState.receiverId)
				 || 
				 (message.receiverId==$scope.localState.senderId && message.senderId==$scope.localState.receiverId))
			 	)
			 {
				 var decodedMessage = $scope.hotelService.decodeChatMessage(message);
				 
				 $scope.addChatMessage(decodedMessage);
				 
				 $scope.removeWaitingMessage(decodedMessage);
				 
				 if(decodedMessage.receiverId == $scope.hotelState.profileData.id && $state.current.name=="app.chat" && $stateParams.receiverId==decodedMessage.senderId)
				 {
					 if(!message.seenByReceiver)
					 {
						 $scope.mainState.readUncheckedMessages.push(decodedMessage.initId);
						 $scope.markMainMessagesRead();
					 }
				 }
			 }
		 });

        
        //####################### on virtual keyboard
        
        function recalculateHeight()
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

            var chatContent = document.getElementById("chatContent");

            if(chatContent)
            {
				var changed = chatContent.style.height != $scope.localState.chatVisibleHeight;
				
                chatContent.style.height = $scope.localState.chatVisibleHeight;
				
				if(changed){
					scrollDown();
				}
            }

            
        }
        
        $scope.checkVisibleHeight = function()
        {
            recalculateHeight();

            setTimeout(recalculateHeight, 400);
			
            setTimeout(recalculateHeight, 1200);

        }
		


		//##################### INIT ###################################

        $rootScope.$on('checkChatSocketConnection', function(event, mass) {
            ChatService.checkSocketConnection();
        });
        
		function init(){

			$scope.localState.showAllCustomersLoading=true;
			$scope.localState.searchFilter = {};

			if($scope.hotelState.profileData && $scope.hotelState.profileData.id>0)
			{
				//$scope.hotelState.setProfileData($scope.hotelState.profileData);

                ChatService.resubscribeWithCustomerData($scope.hotelState);

                $scope.getUpdateModuleData($scope.hotelState.profileData);

				//$scope.hotelState.setProfileData($scope.hotelState.profileData);

				//imitate messages change
				setTimeout(scrollDown, 2000);

				var intervalID = setInterval(recalculateHeight, 1000);
			}
			//else {
			//	var sessionCustomer = SessionCustomer.get(function () {
            //
			//		$scope.hotelState.setProfileData(sessionCustomer);
            //
			//		$scope.initChat(sessionCustomer);
            //
			//		$scope.hotelState.setProfileData(sessionCustomer);
            //
			//		//imitate messages change
			//		setTimeout(scrollDown, 2000);
            //
            //
			//	}, function(error){
			//		$scope.mainState.errorMsg = error;
			//	});
			//}

            $scope.checkVisibleHeight();
            
			setTimeout($rootScope.hideLoading, 5000);

		}
		
		$scope.hotelState.getDeferredState().then(function(){
			init();
		});
		
		//$scope.openModal = function (size) {
		//	var modalInstance = $modal.open({
		//		templateUrl: 'myModalContent.html',
		//		controller: 'ModalInstanceCtrl',
		//		size: size,
		//		resolve: {
		//			items: function () {
		//				
		//				for(var k=0; k<$scope.notSendMessageArray.length; k++)
		//				{
		//					try{
		//						$scope.notSendMessageArray[k] = JSON.parse($scope.notSendMessageArray[k]);
		//					}
		//					catch(e)
		//					{
		//						;
		//					}
		//				}
		//				
		//				return $scope.notSendMessageArray;
		//			}
		//		}
		//	});
        //
		//	modalInstance.result.then(function (selectedItem) {
		//		$scope.selected = selectedItem;
		//	}, function () {
		//		;
		//		//$log.info('Modal dismissed at: ' + new Date());
		//	});
		//};
		
 }])
	 
; 
