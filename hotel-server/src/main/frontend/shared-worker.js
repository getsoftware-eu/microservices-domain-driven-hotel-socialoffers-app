(function () {
    var t, n, e, o, i = {}.hasOwnProperty;
    t = {LF: "\n", NULL: "\x00"}, e = function () {
        function n(t, n, e) {
            this.command = t, this.headers = null != n ? n : {}, this.body = null != e ? e : ""
        }

        return n.prototype.toString = function () {
            var n, e, o, r;
            n = [this.command], r = this.headers;
            for (e in r)i.call(r, e) && (o = r[e], n.push("" + e + ":" + o));
            return this.body && n.push("content-length:" + ("" + this.body).length), n.push(t.LF + this.body), n.join(t.LF)
        }, n._unmarshallSingle = function (e) {
            var o, i, r, s, u, a, c, h, p, f, d, l, g, b, v, y;
            for (s = e.search(RegExp("" + t.LF + t.LF)), u = e.substring(0, s).split(t.LF), r = u.shift(), a = {}, l = function (t) {
                return t.replace(/^\s+|\s+$/g, "")
            }, f = h = null, c = g = 0, v = u.length; v >= 0 ? v > g : g > v; c = v >= 0 ? ++g : --g)f = u[c], h = f.indexOf(":"), a[l(f.substring(0, h))] = l(f.substring(h + 1));
            if (o = "", d = s + 2, a["content-length"])p = parseInt(a["content-length"]), o = ("" + e).substring(d, d + p); else for (i = null, c = b = d, y = e.length; (y >= d ? y > b : b > y) && (i = e.charAt(c), i !== t.NULL); c = y >= d ? ++b : --b)o += i;
            return new n(r, a, o)
        }, n.unmarshall = function (e) {
            var o;
            return function () {
                var i, r, s, u;
                for (s = e.split(RegExp("" + t.NULL + t.LF + "*")), u = [], i = 0, r = s.length; r > i; i++)o = s[i], (null != o ? o.length : void 0) > 0 && u.push(n._unmarshallSingle(o));
                return u
            }()
        }, n.marshall = function (e, o, i) {
            var r;
            return r = new n(e, o, i), r.toString() + t.NULL
        }, n
    }(), n = function () {
        function n(t) {
            this.ws = t, this.ws.binaryType = "arraybuffer", this.counter = 0, this.connected = !1, this.heartbeat = {outgoing: 1e4, incoming: 1e4}, this.subscriptions = {}
        }

        return n.prototype.debug = function (t) {
            var n;
            return null != (n = console) ? n.log(t) : void 0
        }, n.prototype._transmit = function (t, n, o) {
            var i;
            return i = e.marshall(t, n, o), "function" == typeof this.debug && this.debug(">>> " + i), this.ws.send(i)
        }, n.prototype._setupHeartbeat = function (n) {
            var e, i, r, s, u, a, c = this;
            return (u = n.version) === o.VERSIONS.V1_1 || u === o.VERSIONS.V1_2 ? (a = function () {
                var t, e, o, i;
                for (o = n["heart-beat"].split(","), i = [], t = 0, e = o.length; e > t; t++)s = o[t], i.push(parseInt(s));
                return i
            }(), i = a[0], e = a[1], 0 !== this.heartbeat.outgoing && 0 !== e && (r = Math.max(this.heartbeat.outgoing, e), "function" == typeof this.debug && this.debug("send PING every " + r + "ms"), this.pinger = setInterval(function () {
                return c.ws.send(t.LF), "function" == typeof c.debug ? c.debug(">>> PING") : void 0
            }, r)), 0 !== this.heartbeat.incoming && 0 !== i ? (r = Math.max(this.heartbeat.incoming, i), "function" == typeof this.debug && this.debug("check PONG every " + r + "ms"), this.ponger = setInterval(function () {
                var t;
                return t = Date.now() - c.serverActivity, t > 2 * r ? ("function" == typeof c.debug && c.debug("did not receive server activity for the last " + t + "ms"), c.ws.close()) : void 0
            }, r)) : void 0) : void 0
        }, n.prototype.connect = function (n, i, r, s, u) {
            var a = this;
            return this.connectCallback = r, "function" == typeof this.debug && this.debug("Opening Web Socket..."), this.ws.onmessage = function (n) {
                var o, i, r, u, c, h, p, f, d;
                if (r = "undefined" != typeof ArrayBuffer && n.data instanceof ArrayBuffer ? (o = new Uint8Array(n.data), "function" == typeof a.debug ? a.debug("--- got data length: " + o.length) : void 0, function () {
                        var t, n, e;
                        for (e = [], t = 0, n = o.length; n > t; t++)i = o[t], e.push(String.fromCharCode(i));
                        return e
                    }().join("")) : n.data, a.serverActivity = Date.now(), r === t.LF)return void("function" == typeof a.debug && a.debug("<<< PONG"));
                for ("function" == typeof a.debug && a.debug("<<< " + r), f = e.unmarshall(r), d = [], h = 0, p = f.length; p > h; h++)switch (u = f[h], u.command) {
                    case"CONNECTED":
                        "function" == typeof a.debug && a.debug("connected to server " + u.headers.server), a.connected = !0, a._setupHeartbeat(u.headers), d.push("function" == typeof a.connectCallback ? a.connectCallback(u) : void 0);
                        break;
                    case"MESSAGE":
                        c = a.subscriptions[u.headers.subscription], d.push("function" == typeof c ? c(u) : void 0);
                        break;
                    case"RECEIPT":
                        d.push("function" == typeof a.onreceipt ? a.onreceipt(u) : void 0);
                        break;
                    case"ERROR":
                        d.push("function" == typeof s ? s(u) : void 0);
                        break;
                    default:
                        d.push("function" == typeof a.debug ? a.debug("Unhandled frame: " + u) : void 0)
                }
                return d
            }, this.ws.onclose = function () {
                var t;
                return t = "Whoops! Lost connection to " + a.ws.url, "function" == typeof a.debug && a.debug(t), a._cleanUp(), "function" == typeof s ? s(t) : void 0
            }, this.ws.onopen = function () {
                var t;
                return "function" == typeof a.debug && a.debug("Web Socket Opened..."), t = {"accept-version": o.VERSIONS.supportedVersions(), "heart-beat": [a.heartbeat.outgoing, a.heartbeat.incoming].join(",")}, u && (t.host = u), n && (t.login = n), i && (t.passcode = i), a._transmit("CONNECT", t)
            }
        }, n.prototype.disconnect = function (t) {
            return this._transmit("DISCONNECT"), this.ws.onclose = null, this.ws.close(), this._cleanUp(), "function" == typeof t ? t() : void 0
        }, n.prototype._cleanUp = function () {
            return this.connected = !1, this.pinger && clearInterval(this.pinger), this.ponger ? clearInterval(this.ponger) : void 0
        }, n.prototype.send = function (t, n, e) {
            return null == n && (n = {}), null == e && (e = ""), n.destination = t, this._transmit("SEND", n, e)
        }, n.prototype.subscribe = function (t, n, e) {
            return null == e && (e = {}), e.id || (e.id = "sub-" + this.counter++), e.destination = t, this.subscriptions[e.id] = n, this._transmit("SUBSCRIBE", e), e.id
        }, n.prototype.unsubscribe = function (t) {
            return delete this.subscriptions[t], this._transmit("UNSUBSCRIBE", {id: t})
        }, n.prototype.begin = function (t) {
            return this._transmit("BEGIN", {transaction: t})
        }, n.prototype.commit = function (t) {
            return this._transmit("COMMIT", {transaction: t})
        }, n.prototype.abort = function (t) {
            return this._transmit("ABORT", {transaction: t})
        }, n.prototype.ack = function (t, n, e) {
            return null == e && (e = {}), e["message-id"] = t, e.subscription = n, this._transmit("ACK", e)
        }, n.prototype.nack = function (t, n, e) {
            return null == e && (e = {}), e["message-id"] = t, e.subscription = n, this._transmit("NACK", e)
        }, n
    }(), o = {
        libVersion: "2.0.0-next", VERSIONS: {
            V1_0: "1.0", V1_1: "1.1", V1_2: "1.2", supportedVersions: function () {
                return "1.1,1.0"
            }
        }, client: function (t, e) {
            var i, r;
            return null == e && (e = ["v10.stomp", "v11.stomp"]), i = o.WebSocketClass || WebSocket, r = new i(t, e), new n(r)
        }, over: function (t) {
            return new n(t)
        }, Frame: e
    }, "undefined" != typeof window && null !== window ? window.Stomp = o : "undefined" != typeof exports && null !== exports ? (exports.Stomp = o, o.WebSocketClass = require("./test/server.mock.js").StompServerMock) : self.Stomp = o
}).call(this);

//////////////////////////////////////////////

var WsWorker = {
    stomp: "",
    subscription: "",
    connections: [],
    //config -> login, password, ulr!!!
    config: {},
    innerEvent: {
        NEW_MESSAGE: "BB.Event.new_message",
        MESSAGE_CLOSED: "BB.Event.message_closed",
        MESSAGES_CLOSED: "BB.Event.messages_closed",
        INIT_CONNECTION: "BB.Event.init_connection",
        CLOSE_CONNECTION: "BB.Event.close_connection"
    },
    debugMode: 0,
    conInterval : false

};

self.addEventListener("connect", function (event) {

    var thisPort = event.ports[0];

    function connect(e) {
        if (!WsWorker.stomp || !WsWorker.stomp.connected) {

            //WsWorker.debugMode = 1;

            if (WsWorker.debugMode)
                console.log("Connecting...");

            WsWorker.config = e ? e.data.config : WsWorker.config;

            var ws = new WebSocket(WsWorker.config.wsUrl);
            WsWorker.stomp = Stomp.over(ws);

            if (!WsWorker.debugMode)
                WsWorker.stomp.debug = null;

            WsWorker.stomp.heartbeat.outgoing = 20000;
            WsWorker.stomp.heartbeat.incoming = 20000;
            WsWorker.stomp.connect(WsWorker.config.login, WsWorker.config.passcode,
                /*onConnect*/function () {
                    WsWorker.subscription =
                        // #### START WsWorker.subscription
                        WsWorker.stomp.subscribe(WsWorker.config.queue,
                            /*onMessage*/
                            function (message) {
                                if (WsWorker.debugMode)
                                    console.log(WsWorker.connections.length + " Ports open");

                                for (var i = 0; i < WsWorker.connections.length; i++) {
                                    WsWorker.connections[i].postMessage({
                                        innerEvent: WsWorker.innerEvent.NEW_MESSAGE,
                                        message: message
                                    });
                                }
                                
                                //TODO Eugen: acknolidge Server, about receive!!!!
                                WsWorker.stomp.ack(message.headers["message-id"], message.subscription, message.headers);
                            },
                            WsWorker.config.headers);
                        // ### END WsWorker.subscription

                    if (WsWorker.debugMode)
                        console.log("Connected");
                },
                /*onError*/function (error) {

                    if (WsWorker.debugMode)
                        console.log("Error: " + (error ? error : "unknown"));

                    if ((!WsWorker.stomp || !WsWorker.stomp.connected) && !WsWorker.conInterval) {
                        WsWorker.conInterval = setInterval(function () {
                            if (WsWorker.stomp && WsWorker.stomp.connected) {
                                clearInterval(WsWorker.conInterval);
                                WsWorker.conInterval = false;
                            }
                            connect();
                        }, 30000);
                    }

                },
                WsWorker.config.host
            );
        }
    }

    thisPort.addEventListener("message", function (e) {

        switch (e.data.innerEvent) {
            //#############################################//
            case (WsWorker.innerEvent.INIT_CONNECTION) :
            {
                connect(e);
                break;
            }
            //#############################################//
            case (WsWorker.innerEvent.CLOSE_CONNECTION) :
            {
                WsWorker.connections.splice(WsWorker.connections.indexOf(thisPort), 1);

                if (WsWorker.connections.length <= 0) {
                    if (WsWorker.debugMode)
                        console.log("Closing...");

                    if (WsWorker.stomp) {
                        //WsWorker.stomp.unsubscribe(WsWorker.subscription ? WsWorker.subscription.id : undefined);
                        WsWorker.stomp.disconnect(WsWorker.subscription ? WsWorker.subscription.id : undefined);
                    }

                    if (WsWorker.debugMode)
                        console.log("Closed");
                }

                thisPort.close();
                break;
            }
            //#############################################//
            case(WsWorker.innerEvent.MESSAGE_CLOSED) :
            {
                for (var i = 0; i < WsWorker.connections.length; i++) {
                    WsWorker.connections[i].postMessage({
                        innerEvent: WsWorker.innerEvent.MESSAGE_CLOSED,
                        msgId: e.data.msgId
                    });
                }

                break;
            }
            //#############################################//
            case(WsWorker.innerEvent.MESSAGES_CLOSED) :
            {
                for (var i = 0; i < WsWorker.connections.length; i++) {
                    WsWorker.connections[i].postMessage({
                        innerEvent: WsWorker.innerEvent.MESSAGES_CLOSED
                    });
                }
                break;
            }
        }
    }, false);


    thisPort.start();
    WsWorker.connections.push(thisPort);
});


//##################### START USAGE ####################################################
//##################### START USAGE ####################################################
//##################### START USAGE ####################################################
//##################### START USAGE ####################################################
//##################### START USAGE ####################################################

var BB = {
    Events: {
        NEW_MESSAGE: "BB.Event.new_message",
        MESSAGE_CLOSED: "BB.Event.message_closed",
        MESSAGES_CLOSED: "BB.Event.messages_closed",
        INIT_CONNECTION: "BB.Event.init_connection",
        CLOSE_CONNECTION: "BB.Event.close_connection"
    }
};

var hotelicoSocketConfig = {
    wsUrl : "",
    login : "",
    passcode : "",
    headers : "",
    queue: "", //for socket subscribe,
    host: ""
}

BB.Connector = (function () {

    this.worker = undefined;
    var that = this;

    function Connector(cfg) {

        //localStorage.setItem(BB.Constants.STORED_MESSAGES_KEY_BIND, Tapestry.WsConfig.key);
        if (typeof(Worker) !== "undefined" && typeof(that.worker) == "undefined") {
            that.worker = new SharedWorker('/shared-worker.js');

            that.worker.port.start();
            that.worker.port.postMessage({
                //config -> login, password, ulr!!!
                config: hotelicoSocketConfig,
                innerEvent: BB.Events.INIT_CONNECTION
            });
            window.addEventListener("beforeunload", function (e) {
                that.worker.port.postMessage({
                    innerEvent: BB.Events.CLOSE_CONNECTION
                });
            });
        }

    }

    //############ proto ############
    Connector.prototype.onWsWorkerMessage = function (onMessageClosed, onMessagesClosed) {
        that.worker.port.addEventListener("message", function (event) {
            switch (event.data.innerEvent) {
                case (BB.Events.NEW_MESSAGE) :
                {
                    var msg = event.data.message;
                    console.log("Message...");

                    try {
                        var notif = JSON.parse(msg.body);
                        notif.id = msg.headers["message-id"];

                        BB.Notification(notif).broadcast();
                    }
                    catch (e) {

                        // Kein Nack - ansonsten endlos schleife
                        // msg.nack();
                        console.warn(e);

                        BB.Notification(
                            {
                                subject: "Error",
                                from: "JavaScript",
                                message: "Unable to process received message (" + e.message + "): \n'" + msg.body + "'",
                                date: Date.now(),
                                duration: BB.Duration.SINGLE,
                                severity: BB.Severity.ERROR
                            }
                        ).broadcast();
                    }
                    break;
                }
                case(BB.Events.MESSAGE_CLOSED) :
                {
                    onMessageClosed(event.data.msgId);
                    break;
                }
                case(BB.Events.MESSAGES_CLOSED) :
                {
                    onMessagesClosed();
                    break;
                }
            }
        });
    };
    Connector.prototype.sendMessageClosed = function (msgId) {
        if (!msgId) {
            return;
        }
        that.worker.port.postMessage({
            innerEvent: BB.Events.MESSAGE_CLOSED,
            msgId: msgId
        })
    };
    Connector.prototype.sendMessagesClosed = function () {
        that.worker.port.postMessage({
            innerEvent: BB.Events.MESSAGES_CLOSED
        })
    };

    //################################################

    return Connector;
})();

//##################### END USAGE ####################