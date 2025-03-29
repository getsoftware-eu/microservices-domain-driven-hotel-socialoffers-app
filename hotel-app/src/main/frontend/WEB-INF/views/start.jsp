<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Hotelico</title>
  <meta name="description" content="Hotelico, Sonderangebote im Hotel und in der Umgebung, Und lerne andere Hotelg&auml;&szlig;te kennen" />
  <meta name="keywords" content="Hotel, events, guests, chat, order, communication" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <%--<link rel="icon" type="image/png" href="img/realtime-logo.jpg">--%>

    <style>
        /*ANIMATION CIRCLE*/
        #circleG{
            width:52px;
            margin:auto;
        }

        .circleG{
            background-color:rgb(255,255,255);
            float:left;
            height:11px;
            margin-left:6px;
            width:11px;
            animation-name:bounce_circleG;
            -o-animation-name:bounce_circleG;
            -ms-animation-name:bounce_circleG;
            -webkit-animation-name:bounce_circleG;
            -moz-animation-name:bounce_circleG;
            animation-duration:2.24s;
            -o-animation-duration:2.24s;
            -ms-animation-duration:2.24s;
            -webkit-animation-duration:2.24s;
            -moz-animation-duration:2.24s;
            animation-iteration-count:infinite;
            -o-animation-iteration-count:infinite;
            -ms-animation-iteration-count:infinite;
            -webkit-animation-iteration-count:infinite;
            -moz-animation-iteration-count:infinite;
            animation-direction:normal;
            -o-animation-direction:normal;
            -ms-animation-direction:normal;
            -webkit-animation-direction:normal;
            -moz-animation-direction:normal;
            border-radius:7px;
            -o-border-radius:7px;
            -ms-border-radius:7px;
            -webkit-border-radius:7px;
            -moz-border-radius:7px;
        }

        #circleG_1{
            animation-delay:0.45s;
            -o-animation-delay:0.45s;
            -ms-animation-delay:0.45s;
            -webkit-animation-delay:0.45s;
            -moz-animation-delay:0.45s;
        }

        #circleG_2{
            animation-delay:1.05s;
            -o-animation-delay:1.05s;
            -ms-animation-delay:1.05s;
            -webkit-animation-delay:1.05s;
            -moz-animation-delay:1.05s;
        }

        #circleG_3{
            animation-delay:1.35s;
            -o-animation-delay:1.35s;
            -ms-animation-delay:1.35s;
            -webkit-animation-delay:1.35s;
            -moz-animation-delay:1.35s;
        }
        
        @keyframes bounce_circleG{
            0%{}
            50%{  background-color:rgb(241,88,42);  }
            100%{}
        }

        @-o-keyframes bounce_circleG{
            0%{}
            50%{  background-color:rgb(241,88,42);  }
            100%{}
        }

        @-ms-keyframes bounce_circleG{
            0%{}
            50%{  background-color:rgb(241,88,42);  }
            100%{}
        }

        @-webkit-keyframes bounce_circleG{
            0%{}
            50%{  background-color:rgb(241,88,42);  }
            100%{}
        }

        @-moz-keyframes bounce_circleG{
            0%{}
            50%{  background-color:rgb(241,88,42);  }
            100%{}
        }
    </style>
    
    
    <link rel="stylesheet" href="libs/components-font-awesome/css/font-awesome.min.css">

    <link rel="manifest" href="manifest.json">
    <%--<script src="https://cdn.onesignal.com/sdks/OneSignalSDK.js" async></script>--%>
    <%--<script>--%>
        <%--var OneSignal = OneSignal || [];--%>
        <%--OneSignal.push(["init", {--%>
            <%--//hotelico/test32--%>
            <%--appId: "abbfe934-615f-4259-97cb-d8aaa805a927",--%>
            <%--safari_web_id: 'web.onesignal.auto.0bc7de6a-1858-40cb-a30f-b71482b7a86d',--%>
            <%--autoRegister: true--%>
        <%--}]);--%>
    <%--</script>--%>
    
    <%--<link rel="stylesheet" href="angulr/js/lib/ng-img-crop.css" type="text/css" />--%>

  <%--<link rel="stylesheet" href="libs/animate.css/animate.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="libs/font-awesome/css/font-awesome.min.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="libs/simple-line-icons/css/simple-line-icons.css" type="text/css" />--%>
  <%--<!-- material css -->--%>
  <%--<link rel="stylesheet" href="libs/angular-material/angular-material.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/material-design-icons.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/md.css" type="text/css" />--%>
  <%----%>
  <%--<link rel="stylesheet" href="libs/bootstrap/dist/css/bootstrap.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="libs/animate.css/animate.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="libs/isteven-angular-multiselect/isteven-multi-select.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/font.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/app.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/eugen.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/whiteApp.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/chat.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/bootstrap-social.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/css/dropzone/dropzone.css" type="text/css" />--%>
  <%--<link rel="stylesheet" href="angulr/angular-carousel/dist/angular-carousel.min.css" />--%>
  <%--<link rel="stylesheet" type="text/css" href="/wro/bundledCss_v22.css" />--%>
    
  <link rel="stylesheet" type="text/css" href="angulr/css/app.min.css" />

</head>

<body ng-controller="AppCtrl" style="display: flex; justify-content: center;">

<div  layout="row" ng-class="{'pcContentWidth': !isSmartDevice, 'contentFullSize' : isSmartDevice}">

    <div id="globalBGZone" class="t-zone" ng-class="{'bg-light': isSmartDevice || !app.rootSettings.darkBg , 'bg-dark': !isSmartDevice && app.rootSettings.darkBg}" style="height: 100%; width: 100%; left: 0; top: 0; position: fixed; z-index: -1;" >
    </div>
    
    <div id="loadingElt">

        <div id="globalEmptyZone" class="t-zone" style="height: 100%; width: 100%; left: 0; top: 0; position: fixed; z-index:998;">
            <div class="fullscreenDiv">
                <div id="startWerbung" style="background-color: #fff;">
                    <div class="item m-l-n-xxs m-r-n-xxs">

                        <div class="center text-center w-full" style="margin-top:20px">
                            <img src="angulr/img/build/logo/logoFull2.png" />
                            <%--<div>--%>
                                <%--<img src="angulr/img/build/loading/sloganLoading.gif">--%>
                            <%--</div>--%>
                            <div class="wrapper loginWelcomeMsg myCenter" style="padding: 5px;" ngCloak>
                                <%--Genie&szlig;e mit Hotelico attraktive Sonderangebote im Hotel und in der Umgebung. Und lerne andere Hotelg&auml;&szlig;te kennen!--%>                                 
                                    <div class="myFlex" style="width: 245px;flex-direction: column; -webkit-flex-direction: column; float: left;text-align: left;">
                                        <span id="loadSlogan0" class="loadSlogan" ><i class="fa fa-check" style="margin-top: 3px;"></i>&nbsp;Receive special hotel deals</span>
                                        <span id="loadSlogan1" class="loadSlogan" ><i class="fa fa-check" style="margin-top: 3px;"></i>&nbsp;Chat with other hotel guests</span>
                                        <span id="loadSlogan2" class="loadSlogan" ><i class="fa fa-check" style="margin-top: 3px;"></i>&nbsp;Eat cheaper in your hotel</span>
                                    </div>
                             </div>
                            <img src="angulr/img/build/logo/house.png" style="max-width: 300px;"  class="img-full">
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="centerDiv myFlex" id="m" style="max-width: 400px; z-index:999;  /*  background-color: rgba(255,255,255,0.5);*/ width: 250px;text-align: center;border-radius: 18px;">
            Loading<div id="staticLoading" style="display: none;">...</div>
            <div id="circleG" style="margin: 0; margin-top: 43px;">
                <div id="circleG_1" class="circleG"></div>
                <div id="circleG_2" class="circleG"></div>
                <div id="circleG_3" class="circleG"></div>
            </div>
        </div>
    </div>


    <div layout="column" flex ui-view id="scrollingSuperElt" ng-style="::isSmartDevice && $state.current.name=='app.checkin' && {'background-color': '#fff'}"></div>

    <div id="demoHotelId" style="display: none;">${demoHotelId}</div>
    <div id="hostId" style="display: none;">${host}</div>
    <div id="hostSuffixId" style="display: none;">${hostSuffix}</div>

    <%--<div id="curl" style="display:none; background-color: black; color: white;">--%>
        <%--<code><span id="channel">[ooops, you need Chrome 42+]</span></code>--%>
    <%--</div>--%>
</div>


<%--<script type="text/javascript" src="/wro/bundledJs_v22.js"/>--%>
<script type="text/javascript" src="angulr/js/build/app.src.js" charset="utf-8"></script>
<%--<script type="text/javascript" src="angulr/js/build/app.min.js" charset="utf-8"></script>--%>

<%--START DEBUG MODE--%>


<%--<!-- jQuery -->--%>
<%--<script src="libs/jquery/dist/jquery.min.js"></script>--%>

<%--<!-- Angular -->--%>
<%--<script src="libs/angular/angular.js"></script>--%>
<%--<script src="libs/angular-route/angular-route.js"></script>--%>
<%--<script src="libs/angular-animate/angular-animate.js"></script>--%>
<%--<script src="libs/angular-cookies/angular-cookies.js"></script>--%>
<%--<script src="libs/angular-resource/angular-resource.js"></script>--%>
<%--&lt;%&ndash;<script src="libs/angular-sanitize/angular-sanitize.js"></script>&ndash;%&gt;--%>
<%--<script src="libs/angular-touch/angular-touch.js"></script>--%>

<%--<script src="libs/angular-ui-router/release/angular-ui-router.js"></script>--%>
<%--<script src="libs/ngstorage/ngStorage.js"></script>--%>
<%--<script src="libs/angular-ui-utils/ui-utils.js"></script>--%>
<%--<script src="libs/angular-socialshare/angular-socialshare.js"></script>--%>
<%--<!-- bootstrap -->--%>
<%--<script src="libs/angular-bootstrap/ui-bootstrap-tpls.js"></script>--%>
<%--<!-- lazyload -->--%>
<%--<script src="libs/oclazyload/dist/ocLazyLoad.js"></script>--%>
<%--<!-- translate -->--%>
<%--<script src="libs/angular-translate/angular-translate.js"></script>--%>
<%--<script src="libs/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>--%>
<%--<script src="libs/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>--%>
<%--<script src="libs/angular-translate-storage-local/angular-translate-storage-local.js"></script>--%>

<%--<!-- new material -->--%>
<%--<script src="libs/angular-material/angular-material.js"></script>--%>
<%--<script src="libs/angular-aria/angular-aria.js"></script>--%>

<%--<!-- App -->--%>
<%--<script src="angulr/js/lib/timer-worker.js"></script>--%>
<%--<script src="angulr/js/lib/angular-carousel.js"></script>--%>
<%--<script src="angulr/js/lib/angular-dnd.min.js"></script>--%>
<%--&lt;%&ndash;<script src="angulr/js/lib/worker-timer.js"></script>&ndash;%&gt;--%>


<%--<script src="angulr/js/app.js"></script>--%>
<%--<script src="angulr/js/config.js"></script>--%>
<%--<script src="angulr/js/config.lazyload.js"></script>--%>
<%--<script src="angulr/js/config.router.js"></script>--%>
<%--<script src="angulr/js/main.js"></script>--%>
<%--&lt;%&ndash;<script src="angulr/js/services/ui-load.js"></script>&ndash;%&gt;--%>
<%--<script src="angulr/js/services/ui-load.js"></script>--%>



<%--<script src="angulr/js/filters/fromNow.js"></script>--%>

<%--<script src="angulr/js/directives/loginSubmit.js"></script>--%>
<%--<script src="angulr/js/directives/modalFocus.js"></script>--%>
<%--<script src="angulr/js/directives/ng-img-crop.js"></script>--%>
<%--&lt;%&ndash;<script src="angulr/js/directives/setnganimate.js"></script>&ndash;%&gt;--%>
<%--<script src="angulr/js/directives/insertNotification.js"></script>--%>
<%--<script src="angulr/js/directives/ui-butterbar.js"></script>--%>
<%--<script src="angulr/js/directives/ui-focus.js"></script>--%>
<%--<script src="angulr/js/directives/ui-fullscreen.js"></script>--%>
<%--<script src="angulr/js/directives/ui-jq.js"></script>--%>
<%--<script src="angulr/js/directives/ui-module.js"></script>--%>
<%--<script src="angulr/js/directives/ui-nav.js"></script>--%>
<%--<script src="angulr/js/directives/ui-scroll.js"></script>--%>
<%--<script src="angulr/js/directives/scroll-to-bottom.js"></script>--%>
<%--<script src="angulr/js/directives/ui-shift.js"></script>--%>
<%--<script src="angulr/js/directives/ui-toggleclass.js"></script>--%>
<%--&lt;%&ndash;<script src="angulr/js/controllers/bootstrap.js"></script>&ndash;%&gt;--%>

<%--&lt;%&ndash;textAngular input&ndash;%&gt;--%>
<%--<script src='libs/textAngular/dist/textAngular-sanitize.min.js'></script>--%>
<%--<script src='libs/textAngular/dist/textAngular.min.js'></script>--%>
<%--<script src="libs/angular-ui-sref-fastclick/angular-ui-sref-fastclick.js"></script>--%>
<%--<script src='libs/angular-money-directive/dist/angular-money-directive.min.js'></script>--%>
<%--&lt;%&ndash;<script src='libs/ng-tags-input/ng-tags-input.min.js'></script>&ndash;%&gt;--%>

<%--&lt;%&ndash;xeditable datapicker&ndash;%&gt;--%>
<%--<script src="libs/angular-xeditable/dist/js/xeditable.min.js"></script>--%>

<%--&lt;%&ndash;language multiselect&ndash;%&gt;--%>


<%--<script type="text/javascript" src="libs/isteven-angular-multiselect/isteven-multi-select.js" charset="UTF-8"></script>--%>

<%--&lt;%&ndash;<script src="angulr/js/lib/logService.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="angulr/js/lib/dropzone.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="http://raw.githubusercontent.com/andyshora/angular-image-crop/master/image-crop.js"></script>&ndash;%&gt;--%>

<%--<!-- DTO -->--%>
<%--<script src="angulr/js/services/customerDtoService.js"></script>--%>
<%--<script src="angulr/js/services/activityDtoService.js"></script>--%>
<%--<script src="angulr/js/services/hotelDtoService.js"></script>--%>
<%--<script src="angulr/js/services/sessionCustomerService.js"></script>--%>
<%--<script src="angulr/js/services/logoutCustomerService.js"></script>--%>
<%--<script src="angulr/js/services/hotelService.js"></script>--%>
<%--<script src="angulr/js/services/hotelState.js"></script>--%>
<%--<script src="angulr/js/services/hotelLoginService.js"></script>--%>
<%--<script src="angulr/js/services/hotelCheckinService.js"></script>--%>
<%--<script src="angulr/js/services/hotelNotification.js"></script>--%>
<%--&lt;%&ndash;<script src="angulr/js/services/serverCommunicatorService.js"></script>&ndash;%&gt;--%>

<%--<!-- chat -->--%>
<%--<script src="angulr/js/services/notificationService.js"></script>--%>

<%--<script src="angulr/js/build/template_cache.js"></script>--%>



<%--&lt;%&ndash;<script src="angulr/js/services/chatService.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="angulr/js/services/wallService.js"></script>&ndash;%&gt;--%>
<%--<script src="angulr/js/services/activityService.js"></script>--%>


<%--<script src="libs/sockjs/sockjs.min.js" type="text/javascript"></script>--%>
<%--<script src="libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>--%>
<%--<script src="libs/angular/angular.min.js"></script>--%>
<%--<script src="libs/lodash/lodash.min.js"></script>--%>
<%--<script src="angulr/js/lib/fastclick.js"></script>--%>
<%--<script src="libs/ng-file-upload/ng-file-upload.min.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/hotel.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/blocks/hotelCtrl.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/blocks/activityCtrl.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/blocks/slideCtrl.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/blocks/filterHotelCity.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/datumPicker.js"></script>--%>
<%--<!-- pages -->--%>
<%--<script src="angulr/js/controllers/hotel/language.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/login.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/register.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/checkIn.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/profile.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/hotelList.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/chat.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/chatList.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/imageCrop.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/werbung.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/forgotpwd.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/wall.js"></script>--%>
<%--<script src="angulr/js/controllers/hotel/editActivity.js"></script>--%>

<%--END DEBUG MODE--%>

<%--<script type="text/javascript" src="angulr/js/lib/push/ortc.js"></script>--%>
<script type="text/javascript" src="angulr/js/lib/push/ChromePushManager.js"></script>
<%--<script type="text/javascript" src="angulr/js/lib/push/service-worker.js"/>--%>



<%--<script type="text/javascript">--%>
<%--Dropzone.autoDiscover = false;--%>
<%--</script>--%>

    <%--<form name="hotelicoLoginForm" id="hotelicoLoginForm" method="post" action="" style="display: none;">--%>
      <%--<input type="email" id="login-login" name="hotelicoLoginMail" >--%>
      <%--<input type="password" id="login-password" name="hotelicoLoginPassword">--%>
    <%--</form>--%>

<script type="text/javascript">
    
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date(); a = s.createElement(o),
                m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

//    var slogans = document.getElementsByClassName("loadSlogan");
//
//    var sloganCounter = 0;
//    
//    function switchSlogan()
//    {
//        if(!slogans)
//        {
//            slogans = document.getElementsByClassName("loadSlogan");
//        }
//
//        if(!sloganCounter)
//        {
//            sloganCounter = 0;
//        }
//
////        slogans[sloganCounter].style.visibility = "hidden";
//        
//        if(sloganCounter >= slogans.length)
//        {
//            sloganCounter = 0;
//        }
//
//        
//        slogans[sloganCounter].style.visibility = "visible";
//
//        sloganCounter++;
//    }
//    
//    function initSloganSlide()
//    {
//       if(!slogans)
//       {
//           slogans = document.getElementsByClassName("loadSlogan");
//       }
//        
//        var sloganTimer = setInterval(switchSlogan, 800);
//        
//        setTimeout(function(){clearInterval(sloganTimer)}, 10000);
//        
//    }
//
//    initSloganSlide();
    
</script>

</body>
</html>
