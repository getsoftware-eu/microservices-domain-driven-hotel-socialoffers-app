


//(function(angular, SockJS, Stomp, _, undefined) {
//	angular.module("hotelApp.wallService", [])
app.service("WallService", function($q, $timeout, $rootScope) {

	var wallSocketService = {}, wallListener = $q.defer(), wallSocket = {
			client: null,
			stomp: null
		},

	//List of already sended but jet not received message IDs 
	messageIds = [], notSendMessageObjs = [];

	//Write in console!!!
	wallSocketService.debugMode = 0;
	
	//ONLY IF SAVE THE CREATED wallSubscription, WE CAN unsubscribe IT !!!!
	wallSocketService.wallSubscription = "";
	
	// conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
	wallSocketService.conInterval = false;

	wallSocketService.configObj = {

		//wallSubscription url
		wallSubscriptionQueue : null,

		useHeartbeat: false,

		headers: {

		},

		connectHeaders: {
			//login: {},
			//passcode: {},
			//host: ""
		}
	};
	
	wallSocketService.WALL_RECONNECT_TIMEOUT = 3000;

	//Path to Spring Controller
	wallSocketService.WALL_SOCKET_URL = "/" + ($rootScope.HOST_SUFFIX? $rootScope.HOST_SUFFIX : "") + "wall/wall";
	//wallSocketService.WALL_SOCKET_URL = "/wall/wall";

	//Unique Topic of this Chat
	wallSocketService.WALL_TOPIC = "/walltopic/message";

	// TODO ???
	wallSocketService.WALL_BROKER = "/app/wall";

	//receive to Controller
	wallSocketService.receiveWall = function() {
		// send server-promise to client controller
		return wallListener.promise;
	};

	//receive to Controller
	wallSocketService.getNotSendWall = function() {
		// send server-promise to client controller
		return notSendMessageObjs;
	};

	wallSocketService.resubscribeWithHotelData = function (hotelState) {
		wallSocketService.hotelState = hotelState;

		if (wallSocket.stomp && wallSocket.stomp.connected) 
		{
			//### RE-SUBSCRIBE wallS, IF LOGGED IN
			var wallSubscriptionQueueTemp = wallSocketService.hotelState && wallSocketService.hotelState.profileData.hotelId > 0 ? wallSocketService.WALL_TOPIC + "/" + wallSocketService.hotelState.profileData.hotelId : null;

			//check if subscription exists or was changed
			if(wallSocketService.configObj && wallSocketService.configObj.wallSubscriptionQueue && wallSocketService.configObj.wallSubscriptionQueue==wallSubscriptionQueueTemp)
			{
				return;
			}
			
			if (wallSocketService.wallSubscription) {
				wallSocketService.wallSubscription.unsubscribe();
				$rootScope.myWait(2000);
			}
        
			if (wallSocketService.hotelState && wallSocketService.hotelState.profileData.hotelId > 0) {
				subscribeWallTopic();
			}
        
			// ###########################################
		}
		else 
		{
			initConnect();
		}
	}
	wallSocketService.checkSocketConnection = function () {

		var defer = $q.defer();
		
		setTimeout(function(){
			if (!wallSocket.stomp || !wallSocket.stomp.connected)
			{
				initConnect();
				defer.resolve();
			}
			else{
				defer.resolve();
			}
		}, 1500);
		
		return defer.promise;
	};
	//send from Controller
	wallSocketService.sendWall = function(message, senderId, hotelId, hotelStaff, initId) {

		var isNew = false;

		if(!initId)
		{
			isNew = true;
			initId = Math.floor(Math.random() * 1000000);
		}

		var msgObj = JSON.stringify({ //MESSAGE CONTENT
			message: encodeURIComponent(message),
			hotelId : hotelId,
			senderId: senderId,
			hotelStaff: hotelStaff,
			creationTime: (new Date).getTime(),
			initId: initId
		});
		
		//Socket SEND Methode
		if(!wallSocket.stomp  || !wallSocket.stomp.connected)
		{
			wallSocketService.checkSocketConnection().then(function()
			{
				setTimeout(function() {
					wallSocket.sendWall(message, senderId, hotelId, hotelStaff, initId);
				}, 500);
			});
		}
		else
		//Socket SEND Methode
		wallSocket.stomp.send(wallSocketService.WALL_BROKER, {
			priority: 9
		}, msgObj);
		//messageIds.push(id);

		var msgJson = JSON.parse(msgObj);

		msgJson.decodeMessage = decodeURIComponent(msgJson.message);
		msgJson.senderName = "Waiting";
		msgJson.sendTime = new Date();

		if(isNew)
		{
			messageIds.push(initId);
			notSendMessageObjs.push(msgJson);
		}


		return msgJson;
	};

	//var reconnect = function() {
	//	$timeout(function() {
	//		initialize();
	//	}, this.WALL_RECONNECT_TIMEOUT);
	//};

	//entferne nicht gesendeten element
	var removeMsgFromArray = function(initId)
	{
		for(var u=0; u < notSendMessageObjs.length; u++)
		{
			try
			{
				var nextMsgObjJson = JSON.parse(notSendMessageObjs[u]);
			}
			catch(e)
			{
				nextMsgObjJson = notSendMessageObjs[u];
			}

			if(nextMsgObjJson.initId == initId)
			{
				notSendMessageObjs.splice(u, 1);
				return;
			}
		}
	}

	wallSocketService.removeWallMsg = function(initId)
	{
		return removeMsgFromArray(initId);
	}

	var getMessage = function(data) {
		var message = JSON.parse(data);
		var out = message;
		out.message = decodeURIComponent(message.message);
		out.sendTime = new Date(message.sendTime);
		if (_.contains(messageIds, message.initId)) {
			out.self = true;
			removeMsgFromArray(message.initId);
			messageIds = _.remove(messageIds, message.initId);
		}
		return out;
	};

	//var startListener = function() {
    //
	//	// SUBSCRIBE a UNIQUE TOPIC!!! Other i will not get
	//	wallSocket.stomp.subscribe(wallSocketService.WALL_TOPIC, function(data) {
	//		wallListener.notify(getMessage(data.body));
	//	});
	//};
	
	var subscribeWallTopic = function () {
		
		wallSocketService.configObj.wallSubscriptionQueue = wallSocketService.hotelState && wallSocketService.hotelState.profileData.hotelId > 0 ? wallSocketService.WALL_TOPIC + "/" + wallSocketService.hotelState.profileData.hotelId : null;
        
		if (!wallSocketService.configObj.wallSubscriptionQueue) {
			//NO wallSubscription WITHOUT CUSTOMER_ID
			return;
		}

		wallSocketService.wallSubscription =
			// #### START WsWorker.wallSubscription
			//wallSocket.stomp.subscribe(wallSocketService.configObj.wallSubscriptionQueue,
			wallSocket.stomp.subscribe(wallSocketService.configObj.wallSubscriptionQueue,
				/*onMessage*/
				function (message) {

					wallListener.notify(getMessage(message.body));

					//TODO Eugen: acknolidge Server, about receive!!!! 
					//TODO Eugen: -> DELIEVERED TO CUSTOMER!!!!!
					wallSocket.stomp.ack(message.headers["message-id"], message.wallSubscription, message.headers);
				}
				//TODO Eugen: //, socketService.configObj.headers
			);
	}
	
	//var initConnect = function() {
	//	wallSocket.client = new SockJS(wallSocketService.WALL_SOCKET_URL);
	//	wallSocket.stomp = Stomp.over(wallSocket.client);
	//	wallSocket.stomp.connect({}, startListener);
	//	wallSocket.stomp.onclose = reconnect;
	//};

	var initConnect = function (e) {
		//ONLY IF NOW NOT CONNECTED!!!
		if (!wallSocket.stomp || !wallSocket.stomp.connected) {

			//WsWorker.debugMode = 1;

			if (wallSocketService.debugMode)
				console.log("Connecting...");

			//TODO Eugen test it!!!
			wallSocketService.configObj = e ? e.data.config : wallSocketService.configObj;

			//var ws = new WebSocket(wallSocket.config.wsUrl);
			//wallSocket.stomp = Stomp.over(ws);

			if(!wallSocket.client && !wallSocket.stomp)
			{
				wallSocket.client = new SockJS(wallSocketService.WALL_SOCKET_URL);
				wallSocket.stomp = Stomp.over(wallSocket.client);
			}

			if (!wallSocketService.debugMode)
				wallSocket.stomp.debug = null;

			//TODO Eugen test it!!!
			if (wallSocketService.configObj.useHeartbeat) {
				wallSocket.stomp.heartbeat.outgoing = 20000;
				wallSocket.stomp.heartbeat.incoming = 20000;
			}

			wallSocket.stomp.connect(wallSocketService.configObj.connectHeaders,
				/*onConnect*/function () {

					subscribeWallTopic();
					//subscribeActivityTopic();

					// ### END WsWorker.wallSubscription

					if (wallSocketService.debugMode)
						console.log("Connected");
				},
				/*onError*/function (error) {

					if (wallSocketService.debugMode)
						console.log("Error: " + (error ? error : "unknown"));

					if ((!wallSocket.stomp || !wallSocket.stomp.connected) && !wallSocketService.conInterval) {

						//if error -> TRY TO RE-CONNECT EVERY 30 sec !!
						// conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
						
						/*
						wallSocketService.conInterval = setInterval(function () {
							if (wallSocket.stomp && wallSocket.stomp.connected) {
								clearInterval(wallSocketService.conInterval);
								wallSocketService.conInterval = false;
							}
							initConnect();
						}, wallSocketService.RECONNECT_TIMEOUT);
						*/
						
						var timerWorker = $rootScope.getTimeWebWorker();
						wallSocketService.conInterval = timerWorker.setInterval(
							function(){
								if (wallSocket.stomp && wallSocket.stomp.connected) {
									timerWorker.clearInterval(wallSocketService.conInterval);
									//clearInterval(socketService.conInterval);
									wallSocketService.conInterval = false;
								}
								initConnect();
							}
							,wallSocketService.RECONNECT_TIMEOUT);
					}

				}
				//TODO Eugen: HOST -> , wallSocketService.configObj.host
			);
		}
	}
	
	//wallSocketService.startNew = function()
	//{
	//	wallSocket.client.close();
	//	initConnect();
	//}
	//wallSocket.stomp.

	initConnect();
	return wallSocketService;
});
//})(angular, SockJS, Stomp, _);