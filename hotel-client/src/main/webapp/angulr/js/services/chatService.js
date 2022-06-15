//	angular.module("hotelApp.chatService", []).
app.service("ChatService", function($q, $timeout, $rootScope) {
   
    var socketService = {}, chatListener = $q.defer(), chatSocket = {
            client: null,
            stomp: null
        },

    //List of already sended but jet not received message IDs 
    messageIds = [], notSendMessageObjs = [];

    //Write in console!!!
    socketService.debugMode = 0;

    //ONLY IF SAVE THE CREATED CHATSubscription, WE CAN unsubscribe IT !!!!
    socketService.chatSubscription = "";

    // conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
    socketService.conInterval = false;

    socketService.configObj = {

        //CHATSubscription url
        chatSubscriptionQueue : null,

        useHeartbeat: false,

        headers: {

        },

        connectHeaders: {
            //login: {},
            //passcode: {},
            //host: ""
        }
    };

    //reconnect setInterval timeout
    socketService.RECONNECT_TIMEOUT = 30000;

    //Path for basic SocjkJs -> to Spring Controller
    socketService.CHAT_SOCKET_URL = "/"+($rootScope.HOST_SUFFIX? $rootScope.HOST_SUFFIX : "")+"chat/chat";

    //Unique Topic of this Chat // part of SUBSCRIBE_URL
    socketService.CHAT_TOPIC = "/chattopic/message";

    // TODO ???
    // stomp.send() URL
    socketService.CHAT_BROKER = "/app/chat";

    //########################################
    
    //################ START INDIVIDUAL #######################
    //receive to Controller
    socketService.disconnectSocket = function () {

        if (chatSocket.stomp && chatSocket.stomp.connected) {
            chatSocket.stomp.disconnect(function () {
                //alert("See you next time!");
            });
        }

    }

    //receive to Controller
    socketService.getNotSendChat = function() {
        // send server-promise to client controller
        return notSendMessageObjs;
    };
    
    socketService.receiveChat = function () {
        // send server-promise to client controller
        return chatListener.promise;
    };

    socketService.resubscribeWithCustomerData = function (hotelState) {
        socketService.hotelState = hotelState;

        if (chatSocket.stomp && chatSocket.stomp.connected) 
        {
            //### RE-SUBSCRIBE CHATS, IF LOGGED IN
            var chatSubscriptionQueueTemp = socketService.hotelState && socketService.hotelState.profileData.id > 0 ? socketService.CHAT_TOPIC + "/" + socketService.hotelState.profileData.id : null;

            //check if subscription exists or was changed
            if(socketService.configObj && socketService.configObj.chatSubscriptionQueue && socketService.configObj.chatSubscriptionQueue==chatSubscriptionQueueTemp)
            {
                return;
            }
            
            if (socketService.chatSubscription) {
                socketService.chatSubscription.unsubscribe();
                $rootScope.myWait(2000);
            }

            if (socketService.hotelState && socketService.hotelState.profileData.id > 0) {
                subscribeChatTopic();
            }

            // ###########################################
        }
        else {
            initConnect();
        }
    }

    //entferne nicht gesendeten element
    var removeMsgFromArray = function (initId) {
        for (var u = 0; u < notSendMessageObjs.length; u++) {
            try {
                var nextMsgObjJson = JSON.parse(notSendMessageObjs[u]);
            }
            catch (e) {
                nextMsgObjJson = notSendMessageObjs[u];
            }

            if (nextMsgObjJson.initId == initId) {
                notSendMessageObjs.splice(u, 1);
                return;
            }
        }
    }

    socketService.removeMsg = function (initId) {
        return removeMsgFromArray(initId);
    }

    var getMessage = function (data) {
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

    //send from Controller
    socketService.sendChat = function(message, senderId, receiverId, hotelStaff, initId) {
        //generate random message id

        var isNew = false;

        if(!initId)
        {
            isNew = true;
            initId = Math.floor(Math.random() * 1000000);
        }

        var msgObj = JSON.stringify({ //MESSAGE CONTENT
            message: encodeURIComponent(message),
            receiverId : receiverId,
            senderId: senderId,
            hotelStaff: hotelStaff,
            creationTime: (new Date).getTime(),
            initId: initId
        });

        //Socket SEND Methode
        if(!chatSocket.stomp || !chatSocket.stomp.connected)
        {
            socketService.checkSocketConnection().then(function()
            {
                setTimeout(function() {
                    socketService.sendChat(message, senderId, receiverId, hotelStaff, initId);
                }, 500);
            });
        }
        else
        chatSocket.stomp.send(socketService.CHAT_BROKER, {
            priority: 9
        }, msgObj);


        var msgJson = JSON.parse(msgObj);

        msgJson.decodeMessage = decodeURIComponent(msgJson.message);


        if(isNew)
        {
            messageIds.push(initId);
            notSendMessageObjs.push(msgJson);
        }

        msgJson.sendTime = new Date();

        return msgJson;
    };


    // ############## END INDIVIDUAL ######################

    socketService.stopAndEmptyCustomer = function () {
        socketService.hotelState = null;

        socketService.disconnectSocket();
    }

    socketService.checkSocketConnection = function () {

        var defer = $q.defer();
        
        setTimeout(function(){
            if (!chatSocket.stomp || !chatSocket.stomp.connected) 
            {
                initConnect();
                defer.resolve();
            }
            else{
                defer.resolve();
            }
        }, 1500)
       
        return defer.promise;
    };

    //var getMessage = function (data) {
    //    var message = JSON.parse(data), out = {};
    //
    //    return message;
    //};

    var subscribeChatTopic = function () {
        socketService.configObj.chatSubscriptionQueue = socketService.hotelState && socketService.hotelState.profileData.id > 0 ? socketService.CHAT_TOPIC + "/" + socketService.hotelState.profileData.id : null;

        if (!socketService.configObj.chatSubscriptionQueue) {
            //NO chatSubscription WITHOUT CUSTOMER_ID
            return;
        }

        socketService.chatSubscription =
            // #### START WsWorker.chatSubscription
            chatSocket.stomp.subscribe(socketService.configObj.chatSubscriptionQueue,
                /*onMessage*/
                function (message) {

                    chatListener.notify(getMessage(message.body));

                    //TODO Eugen: acknolidge Server, about receive!!!! 
                    //TODO Eugen: -> DELIEVERED TO CUSTOMER!!!!!
                    chatSocket.stomp.ack(message.headers["message-id"], message.chatSubscription, message.headers);
                }
                //TODO Eugen: //, socketService.configObj.headers
            );
    }

    var initConnect = function (e) {
        //ONLY IF NOW NOT CONNECTED!!!
        if (!chatSocket.stomp || !chatSocket.stomp.connected) {

            //WsWorker.debugMode = 1;

            if (socketService.debugMode)
                console.log("Connecting...");

            //TODO Eugen test it!!!
            socketService.configObj = e ? e.data.config : socketService.configObj;

            //var ws = new WebSocket(chatSocket.config.wsUrl);
            //chatSocket.stomp = Stomp.over(ws);

            if(!chatSocket.client && !chatSocket.stomp)
            {
                chatSocket.client = new SockJS(socketService.CHAT_SOCKET_URL);
                chatSocket.stomp = Stomp.over(chatSocket.client);
            }

            if (!socketService.debugMode)
                chatSocket.stomp.debug = null;

            //TODO Eugen test it!!!
            if (socketService.configObj.useHeartbeat) {
                chatSocket.stomp.heartbeat.outgoing = 20000;
                chatSocket.stomp.heartbeat.incoming = 20000;
            }

            chatSocket.stomp.connect(socketService.configObj.connectHeaders,
                /*onConnect*/function () {

                    subscribeChatTopic();
                    //subscribeActivityTopic();

                    // ### END WsWorker.chatSubscription

                    if (socketService.debugMode)
                        console.log("Connected");
                },
                /*onError*/function (error) {

                    if (socketService.debugMode)
                        console.log("Error: " + (error ? error : "unknown"));

                    if ((!chatSocket.stomp || !chatSocket.stomp.connected) && !socketService.conInterval) {

                        
                        /*
                        //if error -> TRY TO RE-CONNECT EVERY 30 sec !!
                        // conInterval -> 'setInterval', that will try to reconnect on error untill 'clearInterval'
                        socketService.conInterval = setInterval(function () {
                            if (chatSocket.stomp && chatSocket.stomp.connected) 
                            {
                                clearInterval(socketService.conInterval);
                                socketService.conInterval = false;
                            }
                            initConnect();
                        }, socketService.RECONNECT_TIMEOUT);
                        */
                        var timerWorker = $rootScope.getTimeWebWorker();
                        socketService.conInterval = timerWorker.setInterval(
                            function(){
                                if (chatSocket.stomp && chatSocket.stomp.connected) {
                                    timerWorker.clearInterval(socketService.conInterval);
                                    //clearInterval(socketService.conInterval);
                                    socketService.conInterval = false;
                                }
                                initConnect();
                            }
                            ,socketService.RECONNECT_TIMEOUT);
                    }

                }
                //TODO Eugen: HOST -> , socketService.configObj.host
            );
        }
    }

    return socketService;
});