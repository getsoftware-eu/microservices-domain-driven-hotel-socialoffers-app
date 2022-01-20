<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <title>Angular version | MyHotelApp</title>
  <meta name="description" content="Angularjs, Html5, Music, Landing, 4 in 1 ui kits package" />
  <meta name="keywords" content="AngularJS, angular, bootstrap, admin, dashboard, panel, app, charts, components,flat, responsive, layout, kit, ui, route, web, app, widgets" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="libs/bootstrap/dist/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="libs/animate.css/animate.css" type="text/css" />
  <link rel="stylesheet" href="libs/font-awesome/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="libs/simple-line-icons/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="angulr/css/font.css" type="text/css" />
  <link rel="stylesheet" href="angulr/css/app.css" type="text/css" />
</head>
<body ng-controller="AppCtrl">

<div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view></div>


<!-- jQuery -->
<script src="libs/jquery/dist/jquery.min.js"></script>

<!-- Angular -->
<script src="libs/angular/angular.js"></script>

<script src="libs/angular-route/angular-route.js"></script>


<script src="libs/angular-animate/angular-animate.js"></script>
<script src="libs/angular-cookies/angular-cookies.js"></script>
<script src="libs/angular-resource/angular-resource.js"></script>
<script src="libs/angular-sanitize/angular-sanitize.js"></script>
<script src="libs/angular-touch/angular-touch.js"></script>

<script src="libs/angular-ui-router/release/angular-ui-router.js"></script>
<script src="libs/ngstorage/ngStorage.js"></script>
<script src="libs/angular-ui-utils/ui-utils.js"></script>

<!-- bootstrap -->
<script src="libs/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<!-- lazyload -->
<script src="libs/oclazyload/dist/ocLazyLoad.js"></script>
<!-- translate -->
<script src="libs/angular-translate/angular-translate.js"></script>
<script src="libs/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
<script src="libs/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
<script src="libs/angular-translate-storage-local/angular-translate-storage-local.js"></script>

<!-- App -->
<script src="angulr/js/app.js"></script>
<script src="angulr/js/config.js"></script>
<script src="angulr/js/config.lazyload.js"></script>
<script src="angulr/js/config.router.js"></script>
<script src="angulr/js/main.js"></script>
<script src="angulr/js/services/ui-load.js"></script>
<script src="angulr/js/services/userService.js"></script>
<script src="angulr/js/services/hotelService.js"></script>
<script src="angulr/js/services/loginHoteluserService.js"></script>
<script src="angulr/js/services/hoteluserService.js"></script>
<script src="angulr/js/filters/fromNow.js"></script>
<script src="angulr/js/directives/setnganimate.js"></script>
<script src="angulr/js/directives/ui-butterbar.js"></script>
<script src="angulr/js/directives/ui-focus.js"></script>
<script src="angulr/js/directives/ui-fullscreen.js"></script>
<script src="angulr/js/directives/ui-jq.js"></script>
<script src="angulr/js/directives/ui-module.js"></script>
<script src="angulr/js/directives/ui-nav.js"></script>
<script src="angulr/js/directives/ui-scroll.js"></script>
<script src="angulr/js/directives/ui-shift.js"></script>
<script src="angulr/js/directives/ui-toggleclass.js"></script>
<script src="angulr/js/controllers/bootstrap.js"></script>
<!-- Lazy loading -->

<script type="text/javascript" src="libs/sockjs/sockjs.min.js"></script>
<script type="text/javascript" src="libs/stomp-websocket/lib/stomp.min.js"></script>
<script type="text/javascript" src="libs/showdown/compressed/showdown.js"></script>

<link rel="stylesheet" type="text/css" href="libs/angular-multi-select/isteven-multi-select.css">

<script src="libs/angular-multi-select/isteven-multi-select.js"></script>
<%--<script src="/socket.io/socket.io.js"></script>--%>

<script src="angulr/js/lib/logService.js"></script>
<script src="angulr/js/lib/shared.js"></script>
<script src="angulr/js/lib/loginClient.js"></script>
<script src="angulr/js/lib/stateClient.js"></script>
<script src="angulr/js/lib/peopleClient.js"></script>
<script src="angulr/js/lib/userClient.js"></script>
<script src="angulr/js/lib/chatClient.js"></script>

<script src="angulr/js/controllers/hotel/chat.js"></script>
<script src="angulr/js/controllers/hotel/login.js"></script>
<script src="angulr/js/controllers/hotel/register.js"></script>
<script src="angulr/js/controllers/hotel/checkIn.js"></script>
<script src="angulr/js/controllers/hotel/home.js"></script>
<script src="angulr/js/controllers/hotel/profile.js"></script>
  
</body>
</html>
