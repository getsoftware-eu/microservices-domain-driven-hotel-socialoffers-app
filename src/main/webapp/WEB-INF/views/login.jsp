<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Html version | Angulr</title>
  <meta name="description" content="Angularjs, Html5, Music, Landing, 4 in 1 ui kits package" />
  <meta name="keywords" content="AngularJS, angular, bootstrap, admin, dashboard, panel, app, charts, components,flat, responsive, layout, kit, ui, route, web, app, widgets" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

  <link rel="stylesheet" href="libs/bootstrap/dist/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="libs/animate.css/animate.css" type="text/css" />
  <link rel="stylesheet" href="libs/font-awesome/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="libs/simple-line-icons/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="media/css/font.css" type="text/css" />
  <link rel="stylesheet" href="media/css/app.css" type="text/css" />

</head>
<body>
<div class="app app-header-fixed  ">


  <div class="container w-xxl w-auto-xs" ng-controller="SigninFormController" ng-init="app.settings.container = false;">
    <a href class="navbar-brand block m-t">Angulr</a>
    <div class="m-b-lg">
      <div class="wrapper text-center">
        <strong>Sign in to get in touch</strong>
      </div>
      <form name="form" class="form-validation">
        <div class="text-danger wrapper text-center" ng-show="authError">

        </div>
        <div class="list-group list-group-sm">
          <div class="list-group-item">
            <input type="email" placeholder="Email" class="form-control no-border" ng-model="user.email" required>
          </div>
          <div class="list-group-item">
            <input type="password" placeholder="Password" class="form-control no-border" ng-model="user.password" required>
          </div>
        </div>
        <button type="submit" class="btn btn-lg btn-primary btn-block" ng-click="login()" ng-disabled='form.$invalid'>Log in</button>
        <div class="text-center m-t m-b"><a ui-sref="access.forgotpwd">Forgot password?</a></div>
        <div class="line line-dashed"></div>
        <p class="text-center"><small>Do not have an account?</small></p>
        <a ui-sref="access.signup" class="btn btn-lg btn-default btn-block">Create an account</a>
      </form>
    </div>
    <div class="text-center" ng-include="'media/tpl/blocks/page_footer.html'">
      <p>
        <small class="text-muted">Web app framework base on Bootstrap and AngularJS<br>&copy; 2015</small>
      </p>
    </div>
  </div>


</div>

<script type="text/javascript" src="libs/angular/angular.min.js"></script>
<script type="text/javascript" src="libs/angular-resource/angular-resource.min.js"></script>
<script type="text/javascript" src="libs/sockjs/sockjs.min.js"></script>
<script type="text/javascript" src="libs/stomp-websocket/lib/stomp.min.js"></script>
<script type="text/javascript" src="libs/showdown/compressed/showdown.js"></script>
<script type="text/javascript" src="app/app.js"></script>
<script type="text/javascript" src="app/services.js"></script>
<script type="text/javascript" src="app/controllers.js"></script>
<script type="text/javascript" src="app/filters.js"></script>

<script src="libs/jquery/dist/jquery.min.js"></script>
<script src="libs/bootstrap/dist/js/bootstrap.js"></script>
<script src="angulr/js/ui-load.js"></script>
<script src="angulr/js/ui-jp.config.js"></script>
<script src="angulr/js/ui-jp.js"></script>
<script src="angulr/js/ui-nav.js"></script>
<script src="angulr/js/ui-toggle.js"></script>

<script src="angulr/js/services/user.js"></script>


</body>
</html>
