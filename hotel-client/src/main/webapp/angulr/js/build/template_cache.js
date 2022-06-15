angular.module('hotelApp.templates', []).run(['$templateCache', function($templateCache) {
  "use strict";
  $templateCache.put("angulr/tpl/app.html",
    "<!------ #################### Material ###################### --->\n" +
    " \n" +
    " <!--//EUGEN: bottom menu fixed height-->\n" +
    " <div id=\"hotelicoHeader\" style=\"/*margin-bottom: 50px;*/ max-width: 800px;\" ng-class=\"{'pcContentWidth': !isSmartDevice}\">\n" +
    "\n" +
    "     <div ng-controller=\"ToasterDemoCtrl\">\n" +
    "         <toaster-container toaster-options=\"{'position-class': 'toast-top-right', 'close-button':true, 'timeOut': '20000'}\"></toaster-container>\n" +
    "     </div>\n" +
    "\n" +
    "     <div ng-init=\"app.settings.container = false;\" id=\"topHeaderElt\" class=\"scrollingSuperElt md-navbar navbar {{app.rootSettings.navbarHeaderColor}} md-whiteframe-z1\" data-ng-include=\" 'angulr/tpl/blocks/material.header.html' \" >\n" +
    "     </div>\n" +
    "     \n" +
    "     <div layout=\"row\" class=\"scrollingSuperElt\">\n" +
    "         <!-- menu -->\n" +
    "         <div flex id=\"app-aside-div\" style=\"z-index: 100;\" class=\"bg-black md-whiteframe-z0 md-aside md-content hidden-xs\" data-ng-include=\" 'angulr/tpl/blocks/material.aside.html' \">\n" +
    "         </div>\n" +
    "         <!-- / menu -->\n" +
    "    \n" +
    "         <!-- view -->\n" +
    "         <div flex layout=\"column\" id=\"angulrContent\" class=\"scrollingSuperElt\">\n" +
    "             <div ui-butterbar></div>\n" +
    "             <a href class=\"off-screen-toggle hide\" ui-toggle-class=\"off-screen\" data-target=\".md-aside\" ></a>\n" +
    "             <div id=\"mdContent\" class=\"md-content scrollingSuperElt\" ui-view></div>\n" +
    "         </div>\n" +
    "         <!-- / view -->\n" +
    "     </div>\n" +
    "\n" +
    "     <!-- footer, hide if checkin-state -->\n" +
    "     <!--<div style=\"position: fixed; bottom: 0; z-index: 99;\" class=\"app-footer navbar navbar-fixed-bottom bg-light lt b-t\" ng-class=\"{'m-n': app.hideAside, 'hide': app.hideFooter || !isSmartDevice}\" ui-view=\"footer\" ng-show=\"false && !app.hideFooter && (app.header.tabIndex!=2) && ( app.header.tabIndex!=0)\">-->\n" +
    "     <!--</div>-->\n" +
    "     \n" +
    "     <div data-ng-include=\" 'angulr/tpl/hotel/blocks/impressumBottomBlock.html' \" ng-if=\"::!isSmartDevice\"/>\n" +
    "\n" +
    "     <!-- /footer -->\n" +
    "\n" +
    " </div>\n" +
    "\n" +
    " ");
  $templateCache.put("angulr/tpl/blocks/aside.html",
    "<!--<div class=\"aside-wrap\">-->\n" +
    "  <!--&lt;!&ndash; if you want to use a custom scroll when aside fixed, use the slimScroll-->\n" +
    "    <!--<div class=\"navi-wrap\" ui-jq=\"slimScroll\" ui-options=\"{height:'100%', size:'8px'}\">-->\n" +
    "  <!--&ndash;&gt;-->\n" +
    "  <!--<div class=\"navi-wrap\">  -->\n" +
    "    <!--&lt;!&ndash; user &ndash;&gt;-->\n" +
    "    <!--<div class=\"clearfix hidden-xs text-center hide\" id=\"aside-user\">-->\n" +
    "      <!--<div class=\"dropdown wrapper\" dropdown>-->\n" +
    "        <!--<div style=\"float: left;\">-->\n" +
    "            <!--<a ui-sref=\"app.me\">-->\n" +
    "              <!--<span class=\"thumb-lg w-auto-folded avatar m-t-sm\" style=\"float: left; margin-right: 5px;\">-->\n" +
    "                <!--&lt;!&ndash;<img src=\"{{getProfileImageUrl()}}\" class=\"img-full\" alt=\"...\">&ndash;&gt;-->\n" +
    "                <!--<img ng-src=\"{{hotelState.profileData.avatarUrl}}\" class=\"img-full\" alt=\"...\">-->\n" +
    "              <!--</span>-->\n" +
    "            <!--</a>-->\n" +
    "         <!--</div>-->\n" +
    "         <!--<div style=\"float: left;\">-->\n" +
    "          <!--<a href class=\"dropdown-toggle hidden-folded\" dropdown-toggle>-->\n" +
    "            <!--<span class=\"clear\">-->\n" +
    "              <!--<span class=\"block m-t-sm\">-->\n" +
    "                <!--<strong class=\"font-bold text-lt\">{{hotelState..profileData.firstName}} {{hotelState.profileData.lastName}}</strong> -->\n" +
    "                <!--<b class=\"caret\"></b>-->\n" +
    "              <!--</span>-->\n" +
    "              <!--&lt;!&ndash;<span class=\"text-muted text-xs block\">{{profileData.jobTitle}} {{profileData.jobDescriptor}}</span>&ndash;&gt;-->\n" +
    "            <!--</span>-->\n" +
    "          <!--</a>-->\n" +
    "        <!--</div>-->\n" +
    "       <!-- -->\n" +
    "        <!--&lt;!&ndash; dropdown &ndash;&gt;-->\n" +
    "        <!--<ul class=\"dropdown-menu animated fadeInRight w hidden-folded\">-->\n" +
    "          <!--&lt;!&ndash;<li class=\"wrapper b-b m-b-sm bg-info m-t-n-xs\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<span class=\"arrow top hidden-folded arrow-info\"></span>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<div>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<p>300mb of 500mb used</p>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<progressbar value=\"60\" type=\"white\" class=\"progress-xs m-b-none dker\"></progressbar>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<li>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a href>Settings</a>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "          <!--<li>-->\n" +
    "            <!--<a ui-sref=\"app.me\">Profile</a>-->\n" +
    "          <!--</li>-->\n" +
    "          <!--&lt;!&ndash;<li>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a href>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<span class=\"badge bg-danger pull-right\">3</span>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;Notifications&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "          <!--<li class=\"divider\"></li>-->\n" +
    "          <!--<li ng-show=\"profileData.hotelId>0\">-->\n" +
    "            <!--<a ng-click=\"resetCheckin()\">CheckOut</a>-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li>-->\n" +
    "            <!--<a ui-sref=\"access.login\" ng-click=\"resetLogin()\">Logout</a>-->\n" +
    "          <!--</li>-->\n" +
    "        <!--</ul>-->\n" +
    "        <!--&lt;!&ndash; / dropdown &ndash;&gt;-->\n" +
    "      <!--</div>-->\n" +
    "      <!--<div class=\"line dk hidden-folded\"></div>-->\n" +
    "    <!--</div>-->\n" +
    "    <!--&lt;!&ndash; / user &ndash;&gt;-->\n" +
    "\n" +
    "    <!--&lt;!&ndash; nav &ndash;&gt;-->\n" +
    "    <!--<nav ui-nav class=\"navi clearfix\" ng-include=\"'angulr/tpl/blocks/nav.html'\"></nav>-->\n" +
    "    <!--&lt;!&ndash; nav &ndash;&gt;-->\n" +
    "\n" +
    "    <!--&lt;!&ndash; aside footer &ndash;&gt;-->\n" +
    "    <!--<div class=\"wrapper m-t\">-->\n" +
    "      <!--&lt;!&ndash;<div class=\"text-center-folded\">&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<span class=\"pull-right pull-none-folded\">60%</span>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<span class=\"hidden-folded\" translate=\"aside.MILESTONE\">Milestone</span>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;<progressbar value=\"60\" class=\"progress-xxs m-t-sm dk\" type=\"info\"></progressbar>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;<div class=\"text-center-folded\">&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<span class=\"pull-right pull-none-folded\">35%</span>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<span class=\"hidden-folded\" translate=\"aside.RELEASE\">Release</span>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;<progressbar value=\"35\" class=\"progress-xxs m-t-sm dk\" type=\"primary\"></progressbar>&ndash;&gt;-->\n" +
    "    <!--</div>-->\n" +
    "    <!--&lt;!&ndash; / aside footer &ndash;&gt;-->\n" +
    "  <!--</div>-->\n" +
    "<!--</div>-->");
  $templateCache.put("angulr/tpl/blocks/aside.music.html",
    "<div class=\"aside-wrap\">\n" +
    "  <div class=\"app-aside-footer dk\">    \n" +
    "    <div class=\"dropdown dropup\" dropdown>\n" +
    "      <a href class=\"pull-right wrapper m-r-xs\" ng-click=\"app.settings.asideFolded = !app.settings.asideFolded\">\n" +
    "        <i class=\"fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw\"></i>\n" +
    "      </a>\n" +
    "      <a href class=\"dropdown-toggle clear hidden-folded wrapper-sm padder\" dropdown-toggle>\n" +
    "        <span class=\"thumb-xxs avatar pull-left m-r-sm\">\n" +
    "          <img src=\"angulr/img/a0.jpg\" alt=\"...\">\n" +
    "        </span>\n" +
    "        <span class=\"hidden-sm hidden-md m-t-xs text-ellipsis\">John.Smith</span>\n" +
    "      </a>      \n" +
    "      <!-- dropdown -->\n" +
    "      <ul class=\"dropdown-menu fadeInRight w\"><!-- Eugen weg: animated -->\n" +
    "        <li class=\"wrapper b-b m-b-sm bg-light m-t-n-xs\">\n" +
    "          <div>\n" +
    "            <p>300mb of 500mb used</p>\n" +
    "          </div>\n" +
    "          <progressbar value=\"60\" class=\"progress-xs m-b-none bg-white\"></progressbar>\n" +
    "        </li>\n" +
    "        <li>\n" +
    "          <a href>\n" +
    "            <span class=\"badge bg-danger pull-right\">30%</span>\n" +
    "            <span>Settings</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li>\n" +
    "          <a ui-sref=\"app.page.profile\">Profile</a>\n" +
    "        </li>\n" +
    "        <li>\n" +
    "          <a ui-sref=\"app.docs\">\n" +
    "            <span class=\"label bg-info pull-right\">new</span>\n" +
    "            Help\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li class=\"divider\"></li>\n" +
    "        <li>\n" +
    "          <a ui-sref=\"app.checkIn\">CheckOut</a>\n" +
    "        </li>        \n" +
    "        <li>\n" +
    "          <a ui-sref=\"access.login\">Logout</a>\n" +
    "        </li>\n" +
    "      </ul>\n" +
    "      <!-- / dropdown -->\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <div class=\"navi-wrap\">\n" +
    "    <!-- nav -->\n" +
    "    <nav ui-nav class=\"navi clearfix\">\n" +
    "      <!-- list -->\n" +
    "      <ul class=\"nav dk\">\n" +
    "        <li class=\"hidden-folded padder m-t m-b-sm text-muted text-u-c text-xs\">\n" +
    "          <span>Discovery</span>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.home\">\n" +
    "            <i class=\"icon-disc icon\"></i>\n" +
    "            <span>Recommendation</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.genres\">\n" +
    "            <i class=\"icon-list icon\"></i>\n" +
    "            <span>Genres</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.mtv\">\n" +
    "            <i class=\"icon-social-youtube icon\"></i>\n" +
    "            <span>MTV</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "      </ul>\n" +
    "      <ul class=\"nav\">\n" +
    "        <li class=\"hidden-folded padder m-t m-b-sm text-muted text-u-c text-xs\">\n" +
    "          <span>Your Music</span>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.playlist({fold:'bookmarkd'})\">\n" +
    "            <i class=\"icon-star icon\"></i>\n" +
    "            <span>Bookmarked</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.playlist({fold:'loved'})\">\n" +
    "            <i class=\"icon-heart icon\"></i>\n" +
    "            <span>Loved</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.playlist({fold:'history'})\">\n" +
    "            <i class=\"icon-clock icon\"></i>\n" +
    "            <span>History</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li class=\"hidden-folded padder m-t m-b-sm text-muted text-u-c text-xs\">\n" +
    "          <span>Playlists</span>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.playlist({fold:'nature'})\">\n" +
    "            <b class=\"badge bg-info pull-right\">3</b>\n" +
    "            <i class=\"icon-playlist icon\"></i>\n" +
    "            <span>Nature</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "        <li ui-sref-active=\"active\">\n" +
    "          <a ui-sref=\"music.playlist({fold:'soundtracks'})\">\n" +
    "            <b class=\"badge dk pull-right\">5</b>\n" +
    "            <i class=\"icon-playlist icon\"></i>\n" +
    "            <span>Soundtracks</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "      </ul>\n" +
    "      <!-- / list -->\n" +
    "    </nav>\n" +
    "    <!-- nav -->    \n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/blocks/aside.right.html",
    "<!--<div ng-if=\"loggedIn\">-->\n" +
    "\n" +
    "<!-- aside right -->\n" +
    "  Chat Inhalt\n" +
    "  <div class=\"app-aside-right pos-fix no-padder w-md w-auto-xs bg-white b-l fadeInRight\" ng-controller=\"ChatCtrl\"><!-- Eugen weg: animated -->\n" +
    "      <!--style=\"top:auto;\">-->\n" +
    "    <div class=\"vbox\">\n" +
    "      <div class=\"wrapper b-b b-t b-light m-b\">\n" +
    "        <a href class=\"pull-right text-muted text-md\" ui-toggle-class=\"show\" target=\".app-aside-right\"><i class=\"icon-close\"></i></a>\n" +
    "        Chat\n" +
    "      </div>\n" +
    "      <div class=\"row-row\">\n" +
    "        <div class=\"cell\">\n" +
    "          <div class=\"cell-inner padder\">\n" +
    "            <!-- chat list -->\n" +
    "            <!--<div class=\"m-b\">-->\n" +
    "              <!--<a href class=\"pull-left thumb-xs avatar\"><img src=\"angulr/img/build/a2.jpg\" alt=\"...\"></a>-->\n" +
    "              <!--<div class=\"clear\">-->\n" +
    "                <!--<div class=\"pos-rlt wrapper-sm b b-light r m-l-sm\">-->\n" +
    "                  <!--<span class=\"arrow left pull-up\"></span>-->\n" +
    "                  <!--<p class=\"m-b-none\">Hi John, What's up...</p>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<small class=\"text-muted m-l-sm\"><i class=\"fa fa-ok text-success\"></i> 2 minutes ago</small>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"m-b\">-->\n" +
    "              <!--<a href class=\"pull-right thumb-xs avatar\"><img src=\"angulr/img/build/a3.jpg\" class=\"img-circle\" alt=\"...\"></a>-->\n" +
    "              <!--<div class=\"clear\">-->\n" +
    "                <!--<div class=\"pos-rlt wrapper-sm bg-light r m-r-sm\">-->\n" +
    "                  <!--<span class=\"arrow right pull-up arrow-light\"></span>-->\n" +
    "                  <!--<p class=\"m-b-none\">Lorem ipsum dolor :)</p>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<small class=\"text-muted\">1 minutes ago</small>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"m-b\">-->\n" +
    "              <!--<a href class=\"pull-left thumb-xs avatar\"><img src=\"angulr/img/build/a2.jpg\" alt=\"...\"></a>-->\n" +
    "              <!--<div class=\"clear\">-->\n" +
    "                <!--<div class=\"pos-rlt wrapper-sm b b-light r m-l-sm\">-->\n" +
    "                  <!--<span class=\"arrow left pull-up\"></span>-->\n" +
    "                  <!--<p class=\"m-b-none\">Great!</p>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<small class=\"text-muted m-l-sm\"><i class=\"fa fa-ok text-success\"></i>Just Now</small>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "              <chat-box></chat-box>\n" +
    "              \n" +
    "            <!-- / chat list -->\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"wrapper m-t b-t b-light\">\n" +
    "        <form class=\"m-b-none\">\n" +
    "          <div class=\"input-group\">\n" +
    "            <input type=\"text\" class=\"form-control\" placeholder=\"Say something\" required ng-max='50' ng-model='message' focus>\n" +
    "            <!--<input type='text' style='width: 80%' required ng-max='50' ng-model='message' focus>-->\n" +
    "            <span class=\"input-group-btn\">\n" +
    "              <button class=\"btn btn-default\" type=\"button\" ng-enable='form.$valid' ng-click=\"sendMessage(message)\">SEND</button>\n" +
    "            </span>\n" +
    "          </div>\n" +
    "        </form>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <!-- / aside right -->\n" +
    "<!--</di-v>-->");
  $templateCache.put("angulr/tpl/blocks/header.html",
    "<!--&lt;!&ndash; navbar header &ndash;&gt;-->\n" +
    "      <!--<div class=\"navbar-header box-shadow {{app.rootSettings.navbarHeaderColor}}\">-->\n" +
    "        <!--&lt;!&ndash;<button class=\"pull-right visible-xs dk\" ui-toggle-class=\"show\" data-target=\".navbar-collapse\">&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<i class=\"glyphicon glyphicon-cog\"></i>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;</button>&ndash;&gt;-->\n" +
    "        <!--<button class=\"pull-right visible-xs topRightMenuButton\" ng-click=\"setHeaderColor()\" ui-toggle-class=\"off-screen\" data-target=\".app-aside\" ui-scroll-to=\"app\">-->\n" +
    "          <!--<i class=\"glyphicon glyphicon-align-justify\"></i>-->\n" +
    "        <!--</button>-->\n" +
    "        <!--&lt;!&ndash; brand &ndash;&gt;-->\n" +
    "        <!--<div style=\"display: flex; justify-content: flex-start;\">-->\n" +
    "\n" +
    "          <!--<i class=\"fa fa-angle-left\"  ng-click=\"mainStepBack();\" id=\"hback\" ng-class=\"{'hide': !app.header.showBackArrow, 'arrow-left': true}\"/>-->\n" +
    "        <!-- -->\n" +
    "          <!--&lt;!&ndash;<a ng-href=\"{{app.titleUrl}}\" class=\"navbar-brand text-lt\" style=\"max-height: 40px;\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<img src=\"angulr/img/build/logoWhite.png\" style=\"background-color: white;border-radius: 3px;border-width: 1px;border-style: solid;\" alt=\".\" ng-class=\"{'hide': showBackArrow}\">&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</a> &ndash;&gt;-->\n" +
    "          <!-- -->\n" +
    "          <!--<a ng-click=\"clickLoading(null, null, app.titleUrl)\" class=\"thumb-xs avatar headerAvatar\" ng-class=\"{'hide': !app.header.titleAvatarSrc || !isSmartDevice}\">-->\n" +
    "            <!--<img ng-src=\"{{app.header.titleAvatarSrc}}\" style=\" background-color: white;\">-->\n" +
    "            <!--<i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[localState.userData.id]\"></i>-->\n" +
    "            <!--<i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[localState.userData.id]\"></i>-->\n" +
    "          <!--</a>-->\n" +
    "          <!-- -->\n" +
    "          <!--<div ng-hide=\"app.header.showBackArrow\" style=\"margin-left: 27px;\"></div>-->\n" +
    "          <!-- -->\n" +
    "          <!--&lt;!&ndash;<div style=\"text-overflow: ellipsis; max-width: 100px; white-space: nowrap;\">&ndash;&gt;-->\n" +
    "            <!--<a ng-click=\"clickLoading(null, null, app.header.titleUrl)\" class=\"hidden-folded m-l-xs headerTitle text-ellipsis\" style=\"max-width: 80%;color: white;\">{{app.header.showTitle}}</a>-->\n" +
    "          <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "          <!-- -->\n" +
    "          <!--<span ng-show=\"app.header.showTitleStaff\" class=\"bg-orange-border\" style=\" margin-left: 5px;color: #f1592a;margin-top: 7px;padding: 5px;padding-bottom: 24px;border-radius: 50px;\">{{'system.hotelStaff' | translate}}</span>-->\n" +
    "\n" +
    "\n" +
    "        <!--</div>-->\n" +
    "       <!-- -->\n" +
    "        <!--&lt;!&ndash; / brand &ndash;&gt;-->\n" +
    "      <!--</div>-->\n" +
    "      <!--&lt;!&ndash; / navbar header &ndash;&gt;-->\n" +
    "\n" +
    "      <!--&lt;!&ndash;&lt;!&ndash; navbar collapse &ndash;&gt;&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;<div class=\"collapse pos-rlt navbar-collapse box-shadow {{app.settings.navbarCollapseColor}}\" ng-show=\"!isSmartDevice\">&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash; buttons &ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<div class=\"nav navbar-nav hidden-xs\" style=\"display: flex;\">&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<a href class=\"btn no-shadow navbar-btn\" ng-click=\"app.settings.asideFolded = !app.settings.asideFolded\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<i class=\"fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw\"></i>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<div ng-show=\"showProfile\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a href class=\"btn no-shadow navbar-btn\" ui-toggle-class=\"show\" target=\"#aside-user\">&ndash;&gt;-->\n" +
    "               <!--&lt;!&ndash;<i class=\"icon-user fa-fw\"></i>&ndash;&gt;-->\n" +
    "             <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash; &ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash; / buttons &ndash;&gt;&ndash;&gt;-->\n" +
    "\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; link and dropdown &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"nav navbar-nav hidden-sm\">&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;<li class=\"dropdown pos-stc\" dropdown>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"dropdown-toggle\" dropdown-toggle>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span>Mega</span> &ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span class=\"caret\"></span>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<div class=\"dropdown-menu wrapper w-full bg-white\">&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<div class=\"row\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-sm-4\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"m-l-xs m-t-xs m-b-xs font-bold\">Pages <span class=\"badge badge-sm bg-success\">10</span></div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"row\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-xs-6\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"list-unstyled l-h-2x\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Profile</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Post</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Search</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Invoice</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-xs-6\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"list-unstyled l-h-2x\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Price</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Lock screen</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sign in</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sign up</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-sm-4 b-l b-light\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"m-l-xs m-t-xs m-b-xs font-bold\">UI Kits <span class=\"label label-sm bg-primary\">12</span></div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"row\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-xs-6\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"list-unstyled l-h-2x\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Buttons</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Icons <span class=\"badge badge-sm bg-warning\">1000+</span></a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Grid</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Widgets</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-xs-6\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"list-unstyled l-h-2x\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Bootstap</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sortable</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Portlet</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Timeline</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"col-sm-4 b-l b-light\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"m-l-xs m-t-xs m-b-sm font-bold\">Analysis</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<div class=\"text-center\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<div class=\"inline\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<div ui-jq=\"easyPieChart\" ui-options=\"{&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;percent: 65,&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;lineWidth: 50,&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;trackColor: '{{app.color.light}}',&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;barColor: '{{app.color.info}}',&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;scaleColor: false,&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;size: 100,&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;rotate: 90,&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;lineCap: 'butt',&ndash;&gt;&ndash;&gt;-->\n" +
    "                          <!--&lt;!&ndash;&lt;!&ndash;animate: 2000&ndash;&gt;&ndash;&gt;-->\n" +
    "                        <!--&lt;!&ndash;&lt;!&ndash;}\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;<li class=\"dropdown\" dropdown>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"dropdown-toggle\" dropdown-toggle>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<i class=\"fa fa-fw fa-plus visible-xs-inline-block\"></i>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span translate=\"header.navbar.new.NEW\">New</span> <span class=\"caret\"></span>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<ul class=\"dropdown-menu\" role=\"menu\">&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li><a href=\"#\" translate=\"header.navbar.new.PROJECT\">Projects</a></li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<a href>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span class=\"badge bg-info pull-right\">5</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span translate=\"header.navbar.new.TASK\">Task</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li><a href translate=\"header.navbar.new.USER\">User</a></li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li class=\"divider\"></li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<a href>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span class=\"badge bg-danger pull-right\">4</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span translate=\"header.navbar.new.EMAIL\">Email</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;</ul>&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; / link and dropdown &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; search form &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;<form class=\"navbar-form navbar-form-sm navbar-left shift\" ui-shift=\"prependTo\" target=\".navbar-collapse\" role=\"search\" ng-controller=\"TypeaheadDemoCtrl\">&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;<div class=\"form-group\">&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<div class=\"input-group\">&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<input type=\"text\" ng-model=\"selected\" typeahead=\"state for state in states | filter:$viewValue | limitTo:8\" class=\"form-control input-sm bg-light no-border rounded padder\" placeholder=\"Search projects...\">&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span class=\"input-group-btn\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<button type=\"submit\" class=\"btn btn-sm bg-light rounded\"><i class=\"fa fa-search\"></i></button>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;</form>&ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; / search form &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "\n" +
    "         <!--&lt;!&ndash; &ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash; #### nabar right &ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<div ng-show=\"!isSmartDevice\">    &ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;<ul class=\"nav navbar-nav navbar-right\" >&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<li class=\"dropdown hidden-sm\" is-open=\"lang.isopen\" dropdown>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a href class=\"dropdown-toggle\" dropdown-toggle>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;{{selectLang}} <b class=\"caret\"></b>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash; dropdown &ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<ul class=\"dropdown-menu animated fadeInRight w\">&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<li ng-repeat=\"(langKey, label) in langs\">&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<a ng-click=\"setLang(langKey, $event)\" href>{{label}}</a>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</ul>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash; / dropdown &ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<li class=\"hidden-xs\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a ui-fullscreen></a>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;<li class=\"dropdown\" dropdown ng-show=\"showProfile\">&ndash;&gt;&ndash;&gt;-->\n" +
    "\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"dropdown-toggle\" dropdown-toggle>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<i class=\"icon-bell fa-fw\"></i>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span class=\"visible-xs-inline\">Notifications</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<span class=\"badge badge-sm up bg-danger pull-right-xs\">2</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; dropdown &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;<div class=\"dropdown-menu w-xl animated fadeInUp\">&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<div class=\"panel bg-white\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"panel-heading b-light bg-light\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<strong>You have <span>2</span> notifications</strong>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"list-group\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"media list-group-item\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<span class=\"pull-left thumb-sm\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<img src=\"angulr/img/build/a0.jpg\" alt=\"...\" class=\"img-circle\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<span class=\"media-body block m-b-none\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;Use awesome animate.css<br>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<small class=\"text-muted\">10 minutes ago</small>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"media list-group-item\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;<span class=\"media-body block m-b-none\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;1.0 initial released<br>&ndash;&gt;&ndash;&gt;-->\n" +
    "                      <!--&lt;!&ndash;&lt;!&ndash;<small class=\"text-muted\">1 hour ago</small>&ndash;&gt;&ndash;&gt;-->\n" +
    "                    <!--&lt;!&ndash;&lt;!&ndash;</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div class=\"panel-footer text-sm\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<a href class=\"pull-right\"><i class=\"fa fa-cog\"></i></a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<a href=\"#notes\" data-toggle=\"class:show animated fadeInRight\">See all the notifications</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash;&lt;!&ndash; / dropdown &ndash;&gt;&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;<li class=\"dropdown\" dropdown ng-show=\"showProfile\">&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<a href class=\"dropdown-toggle clear\" dropdown-toggle>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<span class=\"thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm\" >&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<img ng-src=\"{{getProfileImageUrl()}}\" style=\"border-radius: 500px;\" alt=\"...\">&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<i class=\"on md b-white bottom\"></i>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</span>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<span class=\"hidden-sm hidden-md\">{{profileData.firstName}} {{profileData.lastName}}</span> <b class=\"caret\"></b>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash; dropdown &ndash;&gt;&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<ul class=\"dropdown-menu animated fadeInRight w\">&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li class=\"wrapper b-b m-b-sm bg-light m-t-n-xs\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<p>300mb of 500mb used</p>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</div>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<progressbar value=\"60\" class=\"progress-xs m-b-none bg-white\"></progressbar>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<a href>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span class=\"badge bg-danger pull-right\">30%</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span>Settings</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<li>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<a ui-sref=\"app.me\">Profile</a>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;<li>&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;<a ui-sref=\"app.docs\">&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;<span class=\"label bg-info pull-right\">new</span>&ndash;&gt;&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;&lt;!&ndash;Help&ndash;&gt;&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;&lt;!&ndash;</a>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;&lt;!&ndash;</li>&ndash;&gt;&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<li class=\"divider\"></li>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<li ng-show=\"profileData.hotelId>0\">&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<a ng-click=\"resetCheckin()\">CheckOut</a>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</li> &ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<li>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<a ui-sref=\"access.login\" ng-click=\"resetLogin()\">Logout</a>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;</ul>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;&lt;!&ndash; / dropdown &ndash;&gt;&ndash;&gt;-->\n" +
    "          <!--&lt;!&ndash;</li>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;</ul>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;&lt;!&ndash; / ####navbar right &ndash;&gt;&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "\n" +
    "      <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;&lt;!&ndash; / navbar collapse &ndash;&gt;&ndash;&gt;-->\n" +
    "      <!-- -->\n" +
    "      <!-- -->\n" +
    "      <!--<div style=\"display: none\" id=\"imageCache\">-->\n" +
    "        <!-- -->\n" +
    "        <!--<div id=\"activityImageCache\"></div>-->\n" +
    "        <!--<div id=\"hotelImageCache\"></div>-->\n" +
    "        <!--<div id=\"menuImageCache\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/chat-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/chat-active.png\">          -->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/hotel-active.png\">          -->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/wall-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/wall-active.png\">          -->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/me-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/me-active.png\">          -->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/checkout-active.png\">          -->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/logout-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/loading/loader.gif\">-->\n" +
    "        <!--</div>-->\n" +
    "        <!--<div id=\"profileImageCache\">-->\n" +
    "          <!--<img src=\"angulr/img/build/avatar/incognito-m.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/avatar/incognito-f.png\">-->\n" +
    "          <!--<img src=\"angulr/img/build/staff.png\">-->\n" +
    "        <!--</div>-->\n" +
    "      <!--</div>-->\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/header.music.html",
    "<!-- navbar header -->\n" +
    "      <div class=\"navbar-header {{app.settings.navbarHeaderColor}}\">\n" +
    "        <button class=\"pull-right visible-xs dk\" ui-toggle-class=\"show\" data-target=\".navbar-collapse\">\n" +
    "          <i class=\"glyphicon glyphicon-cog\"></i>\n" +
    "        </button>\n" +
    "        <button class=\"pull-right visible-xs\" ui-toggle-class=\"off-screen\" data-target=\".app-aside\" ui-scroll-to=\"app\">\n" +
    "          <i class=\"glyphicon glyphicon-align-justify\"></i>\n" +
    "        </button>\n" +
    "        <!-- brand -->\n" +
    "        <a href=\"#/\" class=\"navbar-brand text-lt\">\n" +
    "          <i class=\"fa fa-play-circle\"></i>\n" +
    "          <img src=\"angulr/img/logo.png\" alt=\".\" class=\"hide\">\n" +
    "          <span class=\"hidden-folded m-l-xs\">MUSIC</span>\n" +
    "        </a>\n" +
    "        <!-- / brand -->\n" +
    "      </div>\n" +
    "      <!-- / navbar header -->\n" +
    "\n" +
    "      <!-- navbar collapse -->\n" +
    "      <div class=\"collapse pos-rlt navbar-collapse {{app.settings.navbarCollapseColor}}\">\n" +
    "        <!-- search form -->\n" +
    "        <form class=\"navbar-form navbar-form-sm navbar-left no-padder\" role=\"search\" ng-controller=\"TypeaheadDemoCtrl\">\n" +
    "          <div class=\"form-group\">\n" +
    "            <div class=\"input-group w-lg w-auto-xs\">\n" +
    "              <input type=\"text\" ng-model=\"selected\" typeahead=\"state for state in states | filter:$viewValue | limitTo:8\" class=\"form-control input-sm bg-light no-border padder\" placeholder=\"Search songs and artists...\">\n" +
    "              <span class=\"input-group-btn\">\n" +
    "                <button type=\"submit\" class=\"btn btn-sm bg-light\"><i class=\"fa fa-search\"></i></button>\n" +
    "              </span>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "        </form>\n" +
    "        <!-- / search form -->\n" +
    "        <!-- nabar right -->\n" +
    "        <ul class=\"nav navbar-nav navbar-right\">\n" +
    "          <li class=\"hidden-xs\">\n" +
    "            <a ui-fullscreen></a>\n" +
    "          </li>\n" +
    "          <li class=\"dropdown\" dropdown>\n" +
    "            <a href class=\"dropdown-toggle\" dropdown-toggle>\n" +
    "              <i class=\"icon-bell fa-fw\"></i>\n" +
    "              <span class=\"visible-xs-inline\">Notifications</span>\n" +
    "              <span class=\"badge badge-sm up bg-danger pull-right-xs\">2</span>\n" +
    "            </a>\n" +
    "            <!-- dropdown -->\n" +
    "            <div class=\"dropdown-menu w-xl animated fadeInUp\">\n" +
    "              <div class=\"panel bg-white\">\n" +
    "                <div class=\"panel-heading b-light bg-light\">\n" +
    "                  <strong>You have <span>2</span> notifications</strong>\n" +
    "                </div>\n" +
    "                <div class=\"list-group\">\n" +
    "                  <a href class=\"media list-group-item\">\n" +
    "                    <span class=\"pull-left thumb-sm\">\n" +
    "                      <img src=\"angulr/img/a0.jpg\" alt=\"...\" class=\"img-circle\">\n" +
    "                    </span>\n" +
    "                    <span class=\"media-body block m-b-none\">\n" +
    "                      Use awesome animate.css<br>\n" +
    "                      <small class=\"text-muted\">10 minutes ago</small>\n" +
    "                    </span>\n" +
    "                  </a>\n" +
    "                  <a href class=\"media list-group-item\">\n" +
    "                    <span class=\"media-body block m-b-none\">\n" +
    "                      1.0 initial released<br>\n" +
    "                      <small class=\"text-muted\">1 hour ago</small>\n" +
    "                    </span>\n" +
    "                  </a>\n" +
    "                </div>\n" +
    "                <div class=\"panel-footer text-sm\">\n" +
    "                  <a href class=\"pull-right\"><i class=\"fa fa-cog\"></i></a>\n" +
    "                  <a href=\"#notes\" data-toggle=\"class:show animated fadeInRight\">See all the notifications</a>\n" +
    "                </div>\n" +
    "              </div>\n" +
    "            </div>\n" +
    "            <!-- / dropdown -->\n" +
    "          </li>\n" +
    "          <li class=\"bg-light\">\n" +
    "            <a href class=\"bg-info text-white\" tooltip-placement=\"left\" tooltip=\"Upload\"><i class=\"fa fa-plus fa-fw\"></i></a>\n" +
    "          </li>\n" +
    "        </ul>\n" +
    "        <!-- / navbar right -->\n" +
    "\n" +
    "      </div>\n" +
    "      <!-- / navbar collapse -->\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/material.aside.html",
    "<div style=\"position: absolute;\" ng-if=\"::isSmartDevice\">\n" +
    "    <a class=\"hidden-folded m-l-xs headerTitle text-ellipsis\" style=\"color: white; text-decoration: none; margin-left: 35px;\">Hotelico</a>\n" +
    "</div>\n" +
    "\n" +
    "<div ng-style=\"::isSmartDevice && {'margin-top': '55px', 'width': '1px'}\"/>\n" +
    "  \n" +
    "<div class=\"clearfix\" id=\"aside-user\" ng-show=\"hotelState.profileData.id>0\">\n" +
    "  <div class=\"wrapper\" style=\"/*background-color: #445454;*/padding-bottom: 0px;\">\n" +
    "   \n" +
    "    <ul class=\"nav\" style=\"margin-left: -10px;\">\n" +
    "\n" +
    "      <li  ui-sref-active=\"active\" class=\"leftMenuMe\">\n" +
    "        <a ui-sref=\"app.me\" class=\"menuALeft\" style=\"padding-top: 0px; display: flex; display: -webkit-flex; display: -moz-flex;display: -ms-flexbox;align-items: center;\">\n" +
    "          <!--<i class=\"icon-user icon menuIconLeft\"></i>-->\n" +
    "          <div class=\"thumb-md w-auto-folded avatar m-t-sm\" style=\"background-color: #fff;     flex-shrink: 0;   flex-basis: 66px;\">\n" +
    "            <img ng-src=\"{{hotelState.profileData.avatarUrl}}\" class=\"img-full\" alt=\"...\">\n" +
    "          </div>\n" +
    "          <!--<b class=\"badge bg-success pull-right\">30%</b>-->\n" +
    "          <div class=\"menuLabelLeft\" ng-style=\"::!isSmartDevice && {'max-width': '149px'}\" style=\"margin-left: 5px;word-wrap: break-word; white-space: pre-wrap;display: block;text-overflow: ellipsis;white-space: inherit;\">\n" +
    "            {{hotelState.profileData.firstName}} {{hotelState.profileData.lastName}}\n" +
    "            <span class=\"label bg-orange hotelStaff inline\" ng-show=\"hotelState.profileData.hotelStaff\" style=\"position: relative;margin: 0;top: -2px;\">{{::'system.hotelStaff' | translate}}</span>\n" +
    "            <span class=\"label bg-orange hotelStaff inline\" ng-show=\"hotelState.profileData.admin\" style=\"position: relative;margin: 0;top: -2px;\">{{::'system.admin' | translate}}</span>\n" +
    "          </div>\n" +
    "          \n" +
    "          <!--<span class=\"menuLabelLeft\" style=\"margin-left: 5px;\" ng-show=\"hotelState.profileData.admin\">Admin</span>-->\n" +
    "        </a>\n" +
    "      </li>\n" +
    "    </ul>\n" +
    "    \n" +
    "    <!--<a ui-sref=\"app.me\">-->\n" +
    "    <!--<span class=\"thumb-md w-auto-folded avatar m-t-sm\" style=\"background-color: #fff;\">-->\n" +
    "    <!--<img src=\"{{getProfileImageUrl()}}\" class=\"img-full\" alt=\"...\">-->\n" +
    "     <!-- -->\n" +
    "    <!--</span>-->\n" +
    "    <!--</a>-->\n" +
    "    \n" +
    "    \n" +
    "    <a href class=\"hidden-folded\" ui-toggle-class=\"show\" target=\"#user\">\n" +
    "        \n" +
    "      <span class=\"block m-t-sm auto\">\n" +
    "        <span class=\"pull-right\">\n" +
    "          <i class=\"fa fa-fw fa-caret-down text\"></i>\n" +
    "          <i class=\"fa fa-fw fa-caret-up text-active\"></i>\n" +
    "        </span>\n" +
    "        <strong class=\"font-bold text-lt\">User Settings</strong> \n" +
    "      </span>\n" +
    "      <!--<span class=\"text-xs block\">{{hotelState.profileData.jobTitle}} {{hotelState.profileData.jobDescriptor}}</span>-->\n" +
    "    </a>\n" +
    "\n" +
    "    \n" +
    "    \n" +
    "  </div>\n" +
    "</div>\n" +
    "<!-- / user -->\n" +
    "\n" +
    "<!-- nav -->\n" +
    "<nav class=\"navi hide\" id=\"user\" >\n" +
    "  <ul class=\"nav\" ng-show=\"hotelState.profileData && hotelState.profileData.id>0\">\n" +
    "    <li ui-sref-active=\"active\">\n" +
    "      <a ui-sref=\"app.avatar\">\n" +
    "        <i class=\"glyphicon glyphicon-user icon\"></i>\n" +
    "        <span>Change Avatar</span>\n" +
    "      </a>\n" +
    "    </li>\n" +
    "    <!--<li ui-sref-active=\"active\">-->\n" +
    "    <!--<a ng-click=\"resetCheckin()\">-->\n" +
    "    <!--<i class=\"glyphicon glyphicon-cog icon\"></i>-->\n" +
    "    <!--<span>Checkout</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--</li>-->\n" +
    "    <!--<li class=\"m-b-sm\">-->\n" +
    "    <!--<a ui-sref=\"access.signin\">-->\n" +
    "    <!--<i class=\"glyphicon glyphicon-log-out icon\"></i>-->\n" +
    "    <!--<span>Logout</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--</li>-->\n" +
    "  </ul>\n" +
    "</nav>\n" +
    "\n" +
    "<li class=\"line dk\"></li>\n" +
    "<nav ui-nav class=\"navi clearfix\" ng-include=\"'angulr/tpl/blocks/nav.html'\"></nav>\n" +
    "<!-- nav -->\n" +
    "\n" +
    "<!-- aside footer -->\n" +
    "<!--<div class=\"wrapper m-t\">-->\n" +
    "  <!--<div class=\"text-center-folded\">-->\n" +
    "    <!--&lt;!&ndash;<span class=\"pull-right pull-none-folded\">60%</span>&ndash;&gt;-->\n" +
    "    <!--&lt;!&ndash;<span class=\"hidden-folded\" translate=\"aside.MILESTONE\">Impressum</span>&ndash;&gt;-->\n" +
    "      <!--<a class=\"text-muted\"  ui-sref=\"app.impressum\">Impressum</a>-->\n" +
    "  <!--</div>-->\n" +
    "  <!--&lt;!&ndash;<progressbar value=\"60\" class=\"progress-xxs m-t-sm dk\" type=\"info\"></progressbar>&ndash;&gt;-->\n" +
    "  <!--&lt;!&ndash;<div class=\"text-center-folded\">&ndash;&gt;-->\n" +
    "    <!--&lt;!&ndash;<span class=\"pull-right pull-none-folded\">35%</span>&ndash;&gt;-->\n" +
    "    <!--&lt;!&ndash;<span class=\"hidden-folded\" translate=\"aside.RELEASE\">Release</span>&ndash;&gt;-->\n" +
    "  <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "  <!--&lt;!&ndash;<progressbar value=\"35\" class=\"progress-xxs m-t-sm dk\" type=\"primary\"></progressbar>&ndash;&gt;-->\n" +
    "<!--</div>-->\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/material.header.html",
    "<div layout=\"column\" style=\"justify-content: flex-start;\" class=\"angulrHeader\">\n" +
    "<!-- navbar header -->\n" +
    "      <div class=\"navbar-header\" style=\"\">\n" +
    "        <!--<button class=\"pull-right visible-xs dk\" ui-toggle-class=\"show\" data-target=\".navbar-collapse\">-->\n" +
    "          <!--<i class=\"glyphicon glyphicon-cog\"></i>-->\n" +
    "        <!--</button>-->\n" +
    "          \n" +
    "        <button class=\"pull-right visible-xs topRightMenuButton\" ng-click=\"setHeaderColor()\" ui-toggle-class=\"off-screen\" data-target=\".md-aside\" ui-scroll-to=\"app\">\n" +
    "          <i class=\"glyphicon glyphicon-align-justify\"></i>\n" +
    "        </button>\n" +
    "\n" +
    "          <div ng-controller=\"LanguageController\" id=\"languageHeader\" class=\"pull-right visible-xs topRightMenuButton\" ng-if=\"!hotelState.profileData.checkedIn\" style=\"padding: 12px 0px;\">\n" +
    "              <div isteven-multi-select id=\"selectSystemLanguagesFull\" input-model=\"localState.availableSystemLanguages\" output-model=\"localState.selectSystemLanguage\" button-label=\"langKeyLabel\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\" selection-mode=\"single\"></div>\n" +
    "          </div>\n" +
    "          <!-- brand -->\n" +
    "        <!--<a href=\"#/\" class=\"navbar-brand text-lt\">-->\n" +
    "          <!--<i class=\"fa fa-btc\"></i>-->\n" +
    "          <!--<img src=\"img/logo.png\" alt=\".\" class=\"hide\">-->\n" +
    "          <!--<span class=\"hidden-folded m-l-xs\">{{app.name}}</span>-->\n" +
    "        <!--</a>-->\n" +
    "          <!-- / brand -->\n" +
    "\n" +
    "          <!-- brand -->\n" +
    "          <div layout=\"row\" style=\"justify-content: flex-start; white-space: nowrap; flex-wrap: nowrap;\">\n" +
    "\n" +
    "              <i class=\"fa fa-angle-left\"  ng-click=\"mainStepBack();\" id=\"hback\" ng-class=\"{'hide': !app.header.showBackArrow, 'arrow-left': true}\"/>\n" +
    "\n" +
    "              <!--<a ng-href=\"{{app.titleUrl}}\" class=\"navbar-brand text-lt\" style=\"max-height: 40px;\">-->\n" +
    "              <!--<img src=\"angulr/img/build/logoWhite.png\" style=\"background-color: white;border-radius: 3px;border-width: 1px;border-style: solid;\" alt=\".\" ng-class=\"{'hide': showBackArrow}\">-->\n" +
    "              <!--</a> -->\n" +
    "\n" +
    "              <a ng-click=\"clickLoading(null, null, app.header.titleUrl)\" class=\"thumb-xs avatar headerAvatar\" ng-class=\"{'hide': !app.header.titleAvatarSrc || !isSmartDevice}\">\n" +
    "                  <img ng-src=\"{{app.header.titleAvatarSrc}}\" style=\" background-color: white;\">\n" +
    "                  <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"app.header.showTitleStaff || app.header.chatPartnerId && hotelNotification.notificationObj.hotelOnlineGuestIds[app.header.chatPartnerId]\"></i>\n" +
    "                  <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"app.header.showTitleStaff || app.header.chatPartnerId && hotelNotification.notificationObj.hotelOnlineGuestIds[app.header.chatPartnerId]\"></i>\n" +
    "              </a>\n" +
    "\n" +
    "              <div ng-hide=\"app.header.showBackArrow\" style=\"margin-left: 27px;\"></div>\n" +
    "\n" +
    "              <!--<div style=\"text-overflow: ellipsis; max-width: 100px; white-space: nowrap;\">-->\n" +
    "              <a ng-click=\"clickLoading(null, null, app.header.titleUrl)\" class=\"hidden-folded m-l-xs headerTitle text-ellipsis\" style=\"max-width: 80%;color: white;\">{{app.header.showTitle}}</a>\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<span ng-show=\"app.header.showTitleStaff && isSmartDevice\" class=\"bg-orange-border\" style=\" margin-left: 5px;color: #f1592a;margin-top: 7px;padding: 5px;padding-bottom: 24px;border-radius: 50px;\">-->\n" +
    "                  <!--{{'system.hotelStaff' | translate}}-->\n" +
    "              <!--</span>-->\n" +
    "\n" +
    "              <span ng-show=\"app.header.showTitleStaff && isSmartDevice\" class=\"label bg-orange inline hotelStaff\" ng-show=\"nextCustomer.hotelStaff\" style=\"margin-left: 4px;margin-top: 10px;\">{{::'system.hotelStaff' | translate}}</span>\n" +
    "\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "          <!-- / brand -->\n" +
    "          \n" +
    "      </div>\n" +
    "      <!-- / navbar header -->\n" +
    "\n" +
    "      <!--&lt;!&ndash; navbar collapse &ndash;&gt;-->\n" +
    "      <!--<div class=\"collapse navbar-collapse box-shadow blue\">-->\n" +
    "\n" +
    "        <!--&lt;!&ndash; link and dropdown &ndash;&gt;-->\n" +
    "        <!--<ul class=\"nav navbar-nav hidden-sm\">-->\n" +
    "          <!--<li class=\"dropdown pos-stc\" dropdown>-->\n" +
    "            <!--<a href class=\"dropdown-toggle\" dropdown-toggle>-->\n" +
    "              <!--<span>Mega</span> -->\n" +
    "              <!--<span class=\"caret\"></span>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--<div class=\"dropdown-menu wrapper w-full bg-white\">-->\n" +
    "              <!--<div class=\"row\">-->\n" +
    "                <!--<div class=\"col-sm-4\">-->\n" +
    "                  <!--<div class=\"m-l-xs m-t-xs m-b-xs font-bold\">Pages <span class=\"badge badge-sm bg-success\">10</span></div>-->\n" +
    "                  <!--<div class=\"row\">-->\n" +
    "                    <!--<div class=\"col-xs-6\">-->\n" +
    "                      <!--<ul class=\"list-unstyled l-h-2x\">-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Profile</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Post</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Search</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Invoice</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                      <!--</ul>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"col-xs-6\">-->\n" +
    "                      <!--<ul class=\"list-unstyled l-h-2x\">-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Price</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Lock screen</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sign in</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sign up</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                      <!--</ul>-->\n" +
    "                    <!--</div>-->\n" +
    "                  <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<div class=\"col-sm-4 b-l b-light\">-->\n" +
    "                  <!--<div class=\"m-l-xs m-t-xs m-b-xs font-bold\">UI Kits <span class=\"label label-sm bg-primary\">12</span></div>-->\n" +
    "                  <!--<div class=\"row\">-->\n" +
    "                    <!--<div class=\"col-xs-6\">-->\n" +
    "                      <!--<ul class=\"list-unstyled l-h-2x\">-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Buttons</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Icons <span class=\"badge badge-sm bg-warning\">1000+</span></a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Grid</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Widgets</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                      <!--</ul>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"col-xs-6\">-->\n" +
    "                      <!--<ul class=\"list-unstyled l-h-2x\">-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Bootstap</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Sortable</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Portlet</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                        <!--<li>-->\n" +
    "                          <!--<a href><i class=\"fa fa-fw fa-angle-right text-muted m-r-xs\"></i>Timeline</a>-->\n" +
    "                        <!--</li>-->\n" +
    "                      <!--</ul>-->\n" +
    "                    <!--</div>-->\n" +
    "                  <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<div class=\"col-sm-4 b-l b-light\">-->\n" +
    "                  <!--<div class=\"m-l-xs m-t-xs m-b-sm font-bold\">Analysis</div>-->\n" +
    "                  <!--<div class=\"text-center\">-->\n" +
    "                    <!--<div class=\"inline\">-->\n" +
    "                      <!--<div ui-jq=\"easyPieChart\" ui-options=\"{-->\n" +
    "                          <!--percent: 65,-->\n" +
    "                          <!--lineWidth: 50,-->\n" +
    "                          <!--trackColor: '{{app.color.light}}',-->\n" +
    "                          <!--barColor: '{{app.color.info}}',-->\n" +
    "                          <!--scaleColor: false,-->\n" +
    "                          <!--size: 100,-->\n" +
    "                          <!--rotate: 90,-->\n" +
    "                          <!--lineCap: 'butt',-->\n" +
    "                          <!--animate: 2000-->\n" +
    "                        <!--}\">-->\n" +
    "                      <!--</div>-->\n" +
    "                    <!--</div>-->\n" +
    "                  <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li class=\"dropdown\" dropdown>-->\n" +
    "            <!--<a href class=\"dropdown-toggle\" dropdown-toggle>-->\n" +
    "              <!--<i class=\"fa fa-fw fa-plus visible-xs-inline-block\"></i>-->\n" +
    "              <!--<span translate=\"header.navbar.new.NEW\">New</span> <span class=\"caret\"></span>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--<ul class=\"dropdown-menu\" role=\"menu\">-->\n" +
    "              <!--<li><a href=\"#\" translate=\"header.navbar.new.PROJECT\">Projects</a></li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a href>-->\n" +
    "                  <!--<span class=\"badge bg-info pull-right\">5</span>-->\n" +
    "                  <!--<span translate=\"header.navbar.new.TASK\">Task</span>-->\n" +
    "                <!--</a>-->\n" +
    "              <!--</li>-->\n" +
    "              <!--<li><a href translate=\"header.navbar.new.USER\">User</a></li>-->\n" +
    "              <!--<li class=\"divider\"></li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a href>-->\n" +
    "                  <!--<span class=\"badge bg-danger pull-right\">4</span>-->\n" +
    "                  <!--<span translate=\"header.navbar.new.EMAIL\">Email</span>-->\n" +
    "                <!--</a>-->\n" +
    "              <!--</li>-->\n" +
    "            <!--</ul>-->\n" +
    "          <!--</li>-->\n" +
    "        <!--</ul>-->\n" +
    "        <!--&lt;!&ndash; / link and dropdown &ndash;&gt;-->\n" +
    "\n" +
    "        <!--&lt;!&ndash; search form &ndash;&gt;-->\n" +
    "        <!--<form class=\"navbar-form navbar-form-sm navbar-left shift\" ui-shift=\"prependTo\" target=\".navbar-collapse\" role=\"search\" ng-controller=\"TypeaheadDemoCtrl\">-->\n" +
    "          <!--<div class=\"form-group\">-->\n" +
    "            <!--<div class=\"input-group\">-->\n" +
    "              <!--<input type=\"text\" ng-model=\"selected\" typeahead=\"state for state in states | filter:$viewValue | limitTo:8\" class=\"form-control input-sm bg-light no-border rounded padder\" placeholder=\"Search projects...\">-->\n" +
    "              <!--<span class=\"input-group-btn\">-->\n" +
    "                <!--<button type=\"submit\" class=\"btn btn-sm bg-light rounded\"><i class=\"fa fa-search\"></i></button>-->\n" +
    "              <!--</span>-->\n" +
    "            <!--</div>-->\n" +
    "          <!--</div>-->\n" +
    "        <!--</form>-->\n" +
    "        <!--&lt;!&ndash; / search form &ndash;&gt;-->\n" +
    "\n" +
    "        <!--&lt;!&ndash; nabar right &ndash;&gt;-->\n" +
    "        <!--<ul class=\"nav navbar-nav navbar-right\">-->\n" +
    "          <!--<li class=\"dropdown hidden-sm\" is-open=\"lang.isopen\" dropdown>-->\n" +
    "            <!--<a href class=\"dropdown-toggle\" dropdown-toggle>-->\n" +
    "              <!--{{selectLang}} <b class=\"caret\"></b>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--&lt;!&ndash; dropdown &ndash;&gt;-->\n" +
    "            <!--<ul class=\"dropdown-menu animated fadeInRight w\">-->\n" +
    "              <!--<li ng-repeat=\"(langKey, label) in langs\">-->\n" +
    "                <!--<a ng-click=\"setLang(langKey, $event)\" href>{{label}}</a>-->\n" +
    "              <!--</li>-->\n" +
    "            <!--</ul>-->\n" +
    "            <!--&lt;!&ndash; / dropdown &ndash;&gt;-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li class=\"hidden-xs\">-->\n" +
    "            <!--<a ui-fullscreen></a>-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li class=\"dropdown\" dropdown>-->\n" +
    "            <!--<a href class=\"dropdown-toggle\" dropdown-toggle>-->\n" +
    "              <!--<i class=\"icon-bell fa-fw\"></i>-->\n" +
    "              <!--<span class=\"visible-xs-inline\">Notifications</span>-->\n" +
    "              <!--<span class=\"badge badge-sm up bg-danger pull-right-xs\">2</span>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--&lt;!&ndash; dropdown &ndash;&gt;-->\n" +
    "            <!--<div class=\"dropdown-menu w-xl animated fadeInUp\">-->\n" +
    "              <!--<div class=\"panel bg-white\">-->\n" +
    "                <!--<div class=\"panel-heading b-light bg-light\">-->\n" +
    "                  <!--<strong>You have <span>2</span> notifications</strong>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<div class=\"list-group\">-->\n" +
    "                  <!--<a href class=\"list-group-item\">-->\n" +
    "                    <!--<span class=\"pull-left m-r thumb-sm\">-->\n" +
    "                      <!--<img src=\"img/a0.jpg\" alt=\"...\" class=\"img-circle\">-->\n" +
    "                    <!--</span>-->\n" +
    "                    <!--<span class=\"clear block m-b-none\">-->\n" +
    "                      <!--Use awesome animate.css<br>-->\n" +
    "                      <!--<small class=\"text-muted\">10 minutes ago</small>-->\n" +
    "                    <!--</span>-->\n" +
    "                  <!--</a>-->\n" +
    "                  <!--<a href class=\"list-group-item\">-->\n" +
    "                    <!--<span class=\"clear block m-b-none\">-->\n" +
    "                      <!--1.0 initial released<br>-->\n" +
    "                      <!--<small class=\"text-muted\">1 hour ago</small>-->\n" +
    "                    <!--</span>-->\n" +
    "                  <!--</a>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<div class=\"panel-footer text-sm\">-->\n" +
    "                  <!--<a href class=\"pull-right\"><i class=\"fa fa-cog\"></i></a>-->\n" +
    "                  <!--<a href=\"#notes\" data-toggle=\"class:show animated fadeInRight\">See all the notifications</a>-->\n" +
    "                <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "            <!--&lt;!&ndash; / dropdown &ndash;&gt;-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li class=\"dropdown\" dropdown>-->\n" +
    "            <!--<a href class=\"dropdown-toggle clear\" dropdown-toggle>-->\n" +
    "              <!--<span class=\"thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm\">-->\n" +
    "                <!--<img src=\"img/a0.jpg\" alt=\"...\">-->\n" +
    "                <!--<i class=\"on md b-white bottom\"></i>-->\n" +
    "              <!--</span>-->\n" +
    "              <!--<span class=\"hidden-sm hidden-md\">John.Smith</span> <b class=\"caret\"></b>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--&lt;!&ndash; dropdown &ndash;&gt;-->\n" +
    "            <!--<ul class=\"dropdown-menu animated fadeInRight w\">-->\n" +
    "              <!--<li class=\"wrapper b-b m-b-sm bg-light m-t-n-xs\">-->\n" +
    "                <!--<div>-->\n" +
    "                  <!--<p>300mb of 500mb used</p>-->\n" +
    "                <!--</div>-->\n" +
    "                <!--<progressbar value=\"60\" class=\"progress-xs m-b-none bg-white\"></progressbar>-->\n" +
    "              <!--</li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a href>-->\n" +
    "                  <!--<span class=\"badge bg-danger pull-right\">30%</span>-->\n" +
    "                  <!--<span>Settings</span>-->\n" +
    "                <!--</a>-->\n" +
    "              <!--</li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a ui-sref=\"app.page.profile\">Profile</a>-->\n" +
    "              <!--</li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a ui-sref=\"app.docs\">-->\n" +
    "                  <!--<span class=\"label bg-info pull-right\">new</span>-->\n" +
    "                  <!--Help-->\n" +
    "                <!--</a>-->\n" +
    "              <!--</li>-->\n" +
    "              <!--<li class=\"divider\"></li>-->\n" +
    "              <!--<li>-->\n" +
    "                <!--<a ui-sref=\"access.signin\">Logout</a>-->\n" +
    "              <!--</li>-->\n" +
    "            <!--</ul>-->\n" +
    "            <!--&lt;!&ndash; / dropdown &ndash;&gt;-->\n" +
    "          <!--</li>-->\n" +
    "        <!--</ul>-->\n" +
    "        <!--&lt;!&ndash; / navbar right &ndash;&gt;-->\n" +
    "\n" +
    "      <!--</div>-->\n" +
    "      <!--&lt;!&ndash; / navbar collapse &ndash;&gt;-->\n" +
    "\n" +
    "\n" +
    "      <!--<div style=\"display: none\" id=\"imageCache\">-->\n" +
    "\n" +
    "          <!--<div id=\"activityImageCache\"></div>-->\n" +
    "          <!--<div id=\"hotelImageCache\"></div>-->\n" +
    "          <!--<div id=\"menuImageCache\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/chat-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/chat-active.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/hotel-active.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/wall-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/wall-active.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/me-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/me-active.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/checkout-active.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/logout-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/loading/loader.gif\">-->\n" +
    "          <!--</div>-->\n" +
    "          <!--<div id=\"profileImageCache\">-->\n" +
    "              <!--<img src=\"angulr/img/build/avatar/incognito-m.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/avatar/incognito-f.png\">-->\n" +
    "              <!--<img src=\"angulr/img/build/staff.png\">-->\n" +
    "          <!--</div>-->\n" +
    "      <!--</div>-->\n" +
    "\n" +
    "      \n" +
    "      <div id=\"headerTabDiv\" ng-style=\"!isSmartDevice && { 'margin-left' : '250px', 'margin-right': '10px' }\" ng-show=\"app.header.showTab\">\n" +
    "          <md-tabs md-selected=\"app.header.tabIndex\" md-stretch-tabs=\"always\" >\n" +
    "               \n" +
    "              <md-tab id=\"tab0\" class=\"\" aria-controls=\"tab0-content\" >\n" +
    "                  <!--md-on-select=\"hotelState.selectHeaderTab(0, app.header.tabIndex);\">-->\n" +
    "                 <!--{{'system.allHotelsTab' | translate}}-->\n" +
    "                  <span>{{'tab.activities' | translate}}</span>\n" +
    "                  <!--<span ng-show=\"!hotelState.profileData.checkedIn\">Check-In</span>-->\n" +
    "\n" +
    "              </md-tab>\n" +
    "               \n" +
    "              <md-tab id=\"tab1\" aria-controls=\"tab1-content\" >\n" +
    "                  <!--md-on-select=\"hotelState.selectHeaderTab(1, app.header.tabIndex);\"> -->\n" +
    "                   <span ng-class=\"{'tabPseudoDisabled': !hotelState.profileData.checkedIn}\">{{'tab.chat' | translate}}</span>\n" +
    "                   <!--<span ng-show=\"!hotelState.profileData.checkedIn\">Check-In</span>-->\n" +
    "                  <b class=\"badge bg-white tabBadge\" ng-class=\"::{'orange-text': isSmartDevice}\" ng-show=\"hotelNotification.notificationObj.unreadChatsCounter && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.unreadChatsCounter}}</b>\n" +
    "\n" +
    "              </md-tab>\n" +
    "              <md-tab id=\"tab2\" aria-controls=\"tab2-content\" >\n" +
    "                  <!--<span ng-hide=\"$state.current.name=='app.chatList' && app.header.tabIndex==2\">&nbsp;&nbsp;&nbsp;{{'system.aboutHotelico' | translate}}&nbsp;&nbsp;&nbsp;</span>-->\n" +
    "                  <!--<span ng-show=\"$state.current.name=='app.chatList' && app.header.tabIndex==2\">{{'system.contacts' | translate}}</span>-->\n" +
    "                  <!--<span ng-show=\"!hotelState.profileData.checkedIn && $state.current.name=='app.activityList'\">{{'tab.allActivities' | translate}}</span>-->\n" +
    "                  <span  ng-class=\"{'tabPseudoDisabled': !hotelState.profileData.checkedIn}\">{{'tab.lobby' | translate}}</span>\n" +
    "                  <!--<span ng-show=\"$state.current.name=='app.menuList'\">{{'tab.menu' | translate}}</span>-->\n" +
    "\n" +
    "              </md-tab>\n" +
    "          </md-tabs>\n" +
    "\n" +
    "          <!--<ng-switch on=\"tabData.selectedIndex\">-->\n" +
    "              <!--<div role=\"tabpanel\"-->\n" +
    "                   <!--id=\"tab1-content\"-->\n" +
    "                   <!--aria-labelledby=\"tab1\"-->\n" +
    "                   <!--ng-switch-when=\"0\"-->\n" +
    "                   <!--md-swipe-left=\"next()\"-->\n" +
    "                   <!--md-swipe-right=\"previous()\"  class=\"wrapper blue\">-->\n" +
    "                  <!--All Hotels content-->\n" +
    "              <!--</div>-->\n" +
    "              <!--<div role=\"tabpanel\"-->\n" +
    "                   <!--id=\"tab2-content\"-->\n" +
    "                   <!--aria-labelledby=\"tab2\"-->\n" +
    "                   <!--ng-switch-when=\"1\"-->\n" +
    "                   <!--md-swipe-left=\"next()\"-->\n" +
    "                   <!--md-swipe-right=\"previous()\"  class=\"wrapper teal\">-->\n" +
    "                 <!--My Hotel Content-->\n" +
    "              <!--</div>-->\n" +
    "              <!--&lt;!&ndash;<div role=\"tabpanel\"&ndash;&gt;-->\n" +
    "                   <!--&lt;!&ndash;id=\"tab3-content\"&ndash;&gt;-->\n" +
    "                   <!--&lt;!&ndash;aria-labelledby=\"tab3\"&ndash;&gt;-->\n" +
    "                   <!--&lt;!&ndash;ng-switch-when=\"2\"&ndash;&gt;-->\n" +
    "                   <!--&lt;!&ndash;md-swipe-left=\"next()\"&ndash;&gt;-->\n" +
    "                   <!--&lt;!&ndash;md-swipe-right=\"previous()\"  class=\"wrapper cyan\">&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;View for Item #3<br/>&ndash;&gt;-->\n" +
    "                  <!--&lt;!&ndash;data.selectedIndex = 2&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "          <!--</ng-switch>-->\n" +
    "\n" +
    "          <!--<div class=\"b-t wrapper\" layout=\"row\" layout-sm=\"column\" layout-align=\"left center\">-->\n" +
    "              <!--<md-input-container>-->\n" +
    "                  <!--<label for=\"selectedIndex\">Selected Index</label>-->\n" +
    "                  <!--<input type=\"number\" id=\"selectedIndex\" ng-model=\"data.selectedIndex\">-->\n" +
    "              <!--</md-input-container>-->\n" +
    "              <!--<div flex></div>-->\n" +
    "              <!--<md-checkbox ng-model=\"data.secondLocked\" aria-label=\"Disabled\">-->\n" +
    "                  <!--Disabled Item Two-->\n" +
    "              <!--</md-checkbox>-->\n" +
    "\n" +
    "          <!--</div>-->\n" +
    "           \n" +
    "      </div>\n" +
    "  </div>");
  $templateCache.put("angulr/tpl/blocks/material.layout.html",
    "<div ng-init=\"app.settings.container = false;\" class=\"md-navbar navbar blue md-whiteframe-z1\" data-ng-include=\" 'angulr/tpl/blocks/material.header.html' \" >\n" +
    "	</div>\n" +
    "	<div layout=\"row\">\n" +
    "	  <!-- menu -->\n" +
    "	  <div flex class=\"bg-white md-whiteframe-z0 md-aside md-content hidden-xs\" data-ng-include=\" 'angulr/tpl/blocks/material.aside.html' \">\n" +
    "	  </div>\n" +
    "	  <!-- / menu -->\n" +
    "\n" +
    "	  <!-- view -->\n" +
    "	  <div flex layout=\"column\">\n" +
    "	  	<div ui-butterbar></div>\n" +
    "    	<a href class=\"off-screen-toggle hide\" ui-toggle-class=\"off-screen\" data-target=\".md-aside\" ></a>\n" +
    "	  	<div class=\"md-content\" ui-view></div>\n" +
    "	  </div>\n" +
    "	  <!-- / view -->\n" +
    "	</div>\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/material.nav.html",
    "<!--&lt;!&ndash; list &ndash;&gt;-->\n" +
    "<!--<ul class=\"nav\">-->\n" +
    "  <!--<li class=\"hidden-folded padder m-t m-b-sm text-muted text-xs\">-->\n" +
    "    <!--<span translate=\"aside.nav.HEADER\">Navigation</span>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li>-->\n" +
    "    <!--<a ui-sref=\"app.dashboard-v3\" md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-stats icon\"></i>-->\n" +
    "      <!--<span class=\"font-bold\" translate=\"aside.nav.DASHBOARD\">Dashboard</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ui-sref-active=\"active\">-->\n" +
    "    <!--<a ui-sref=\"app.calendar\" md-ink-ripple>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-calendar icon\"></i>-->\n" +
    "      <!--<span class=\"font-bold\" translate=\"aside.nav.CALENDAR\">Calendar</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ui-sref-active=\"active\">-->\n" +
    "    <!--<a ui-sref=\"app.mail.list\" md-ink-ripple>-->\n" +
    "      <!--<b class=\"badge bg-info pull-right\">9</b>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-envelope icon\"></i>-->\n" +
    "      <!--<span class=\"font-bold\" translate=\"aside.nav.EMAIL\">Email</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li>-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-th-large icon\"></i>-->\n" +
    "      <!--<span class=\"font-bold\">Apps</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub dk\">-->\n" +
    "      <!--<li class=\"nav-sub-header\">-->\n" +
    "        <!--<a href md-ink-ripple>-->\n" +
    "          <!--<span>Apps</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.todo\">-->\n" +
    "          <!--<span md-ink-ripple>Todo</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.note\">-->\n" +
    "          <!--<span md-ink-ripple>Note</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "\n" +
    "  <!--<li class=\"line dk\"></li>-->\n" +
    "\n" +
    "  <!--<li class=\"hidden-folded padder m-t m-b-sm text-muted text-xs\">-->\n" +
    "    <!--<span translate=\"aside.nav.components.COMPONENTS\">Components</span>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ng-class=\"{active:$state.includes('app.material')}\">-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<i class=\"pull-right up\"><b class=\"badge bg-success\">14</b></i>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-th icon\"></i>-->\n" +
    "      <!--<span class=\"font-normal\">Material</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub\">-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.button\">Button</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.icon\">Icon</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.color\">Color</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.card\">Card</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.form\">Form</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.list\">List</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a md-ink-ripple ui-sref=\"app.material.ngmaterial\">NgMaterial</a>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ng-class=\"{active:$state.includes('app.ui')}\">-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-briefcase icon\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.components.ui_kits.UI_KITS\">UI Kits</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub dk\">-->\n" +
    "      <!--<li class=\"nav-sub-header\">-->\n" +
    "        <!--<a href>-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.UI_KITS\">UI Kits</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.buttons\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.BUTTONS\">Buttons</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.icons\">-->\n" +
    "          <!--<b class=\"badge bg-info pull-right\">3</b>-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.ICONS\">Icons</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.grid\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.GRID\">Grid</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.widgets\">-->\n" +
    "          <!--<b class=\"badge bg-success pull-right\">13</b>-->\n" +
    "          <!--<span translate=\"aside.nav.WIDGETS\">Widgets</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.bootstrap\">-->\n" +
    "          <!--<b class=\"badge bg-primary pull-right\">16</b>-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.BOOTSTRAP\">Bootstrap</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.sortable\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.SORTABLE\">Sortable</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.scroll\">-->\n" +
    "          <!--<span>Scroll</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.portlet\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.PORTLET\">Portlet</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.timeline\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.ui_kits.TIMELINE\">Timeline</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.tree\">-->\n" +
    "          <!--<span>Tree</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.ui.toaster\">-->\n" +
    "          <!--<span>Toaster</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "          <!--<span class=\"pull-right text-muted\">-->\n" +
    "            <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "            <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "          <!--</span>-->\n" +
    "          <!--<b class=\"label bg-info pull-right\">2</b>-->\n" +
    "          <!--<span>Maps</span>-->\n" +
    "        <!--</a>-->\n" +
    "        <!--<ul class=\"nav dker\">-->\n" +
    "          <!--<li ui-sref-active=\"active\">-->\n" +
    "            <!--<a ui-sref=\"app.ui.jvectormap\">-->\n" +
    "              <!--<span translate=\"aside.nav.components.ui_kits.VECTOR_MAP\">Vector Map</span>-->\n" +
    "            <!--</a>-->\n" +
    "          <!--</li>-->\n" +
    "          <!--<li ui-sref-active=\"active\">-->\n" +
    "            <!--<a ui-sref=\"app.ui.googlemap\">-->\n" +
    "              <!--<span>Google Map</span>-->\n" +
    "            <!--</a>-->\n" +
    "          <!--</li>-->\n" +
    "        <!--</ul>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ng-class=\"{active:$state.includes('app.table')}\">-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<b class=\"label bg-primary pull-right\">2</b>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-list\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.components.table.TABLE\">Table</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub dk\">-->\n" +
    "      <!--<li class=\"nav-sub-header\">-->\n" +
    "        <!--<a href>-->\n" +
    "          <!--<span translate=\"aside.nav.components.table.TABLE\">Table</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.static\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.table.TABLE_STATIC\">Table static</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.datatable\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.table.DATATABLE\">Datatable</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.footable\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.table.FOOTABLE\">Footable</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.grid\">-->\n" +
    "          <!--<span>ngGrid</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.uigrid\">-->\n" +
    "          <!--<span>uiGrid</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.smart\">-->\n" +
    "          <!--<span>Smart table</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.table.editable\">-->\n" +
    "          <!--<span>Editable</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ng-class=\"{active:$state.includes('app.form')}\">-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-edit\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.components.form.FORM\">Form</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub dk\">-->\n" +
    "      <!--<li class=\"nav-sub-header\">-->\n" +
    "        <!--<a href>-->\n" +
    "          <!--<span translate=\"aside.nav.components.form.FORM\">Form</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.elements\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.form.FORM_ELEMENTS\">Form elements</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.validation\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.form.FORM_VALIDATION\">Form validation</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.wizard\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.form.FORM_WIZARD\">Form wizard</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.fileupload\">-->\n" +
    "          <!--<span>File upload</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.imagecrop\">-->\n" +
    "          <!--<span>Image crop</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.select\">-->\n" +
    "          <!--<span>Select</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.slider\">-->\n" +
    "          <!--<span>Slider</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.editor\">-->\n" +
    "          <!--<span>Editor</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.form.xeditable\">-->\n" +
    "          <!--<span>Xeditable</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ui-sref-active=\"active\">-->\n" +
    "    <!--<a ui-sref=\"app.chart\" md-ink-ripple>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-signal\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.components.CHART\">Chart</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li ng-class=\"{active:$state.includes('app.page')}\">-->\n" +
    "    <!--<a href md-ink-ripple class=\"auto\">-->\n" +
    "      <!--<span class=\"pull-right text-muted\">-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-right text\"></i>-->\n" +
    "        <!--<i class=\"fa fa-fw fa-angle-down text-active\"></i>-->\n" +
    "      <!--</span>-->\n" +
    "      <!--<i class=\"glyphicon glyphicon-file icon\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.components.pages.PAGES\">Pages</span>-->\n" +
    "    <!--</a>-->\n" +
    "    <!--<ul class=\"nav nav-sub dk\">-->\n" +
    "      <!--<li class=\"nav-sub-header\">-->\n" +
    "        <!--<a href>-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.PAGES\">Pages</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.page.profile\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.PROFILE\">Profile</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.page.post\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.POST\">Post</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.page.search\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.SEARCH\">Search</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.page.invoice\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.INVOICE\">Invoice</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li ui-sref-active=\"active\">-->\n" +
    "        <!--<a ui-sref=\"app.page.price\">-->\n" +
    "          <!--<span>Price</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a ui-sref=\"lockme\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.LOCK_SCREEN\">Lock screen</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a ui-sref=\"access.signin\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.SIGNIN\">Signin</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a ui-sref=\"access.signup\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.SIGNUP\">Signup</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a ui-sref=\"access.forgotpwd\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.FORGOT_PASSWORD\">Forgot password</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "      <!--<li>-->\n" +
    "        <!--<a ui-sref=\"access.404\">-->\n" +
    "          <!--<span translate=\"aside.nav.components.pages.404\">404</span>-->\n" +
    "        <!--</a>-->\n" +
    "      <!--</li>-->\n" +
    "    <!--</ul>-->\n" +
    "  <!--</li>-->\n" +
    "\n" +
    "  <!--<li class=\"line dk hidden-folded\"></li>-->\n" +
    "\n" +
    "  <!--<li class=\"hidden-folded padder m-t m-b-sm text-muted text-xs\">          -->\n" +
    "    <!--<span translate=\"aside.nav.your_stuff.YOUR_STUFF\">Your Stuff</span>-->\n" +
    "  <!--</li>  -->\n" +
    "  <!--<li>-->\n" +
    "    <!--<a ui-sref=\"app.page.profile\">-->\n" +
    "      <!--<i class=\"icon-user icon text-success-lter\"></i>-->\n" +
    "      <!--<b class=\"badge bg-success pull-right\">30%</b>-->\n" +
    "      <!--<span translate=\"aside.nav.your_stuff.PROFILE\">Profile</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <!--<li>-->\n" +
    "    <!--<a ui-sref=\"app.docs\">-->\n" +
    "      <!--<i class=\"icon-question icon\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.your_stuff.DOCUMENTS\">Documents</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "<!--</ul>-->\n" +
    "<!--&lt;!&ndash; / list &ndash;&gt;-->\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/nav.html",
    "<!-- list -->\n" +
    "<ul class=\"nav\">\n" +
    "\n" +
    "\n" +
    "  <li>\n" +
    "    <a href class=\"auto\">      \n" +
    "      <span class=\"pull-right text-muted\">\n" +
    "        <i class=\"fa fa-fw fa-angle-right text\"></i>\n" +
    "        <i class=\"fa fa-fw fa-angle-down text-active\"></i>\n" +
    "      </span>\n" +
    "      <!--<b class=\"badge bg-info pull-right\" ng-show=\"hotelNotification.notificationObj.myDealsNumber>0\">{{hotelNotification.notificationObj.myDealsNumber}}</b>-->\n" +
    "      <div class=\"menuIconLeft iconActivity\"/>\n" +
    "      <span class=\"font-bold menuLabelLeft\" translate=\"system.activities\">activities</span>\n" +
    "    </a>\n" +
    "    <ul class=\"nav nav-sub dk\">\n" +
    "      \n" +
    "      <li class=\"nav-sub-header\">\n" +
    "        <a href>\n" +
    "          <span translate=\"system.activities\">activities</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "      \n" +
    "      <li ui-sref-active=\"active\" class=\"leftMenuHotel subMenu\" ng-show=\"!hotelState.profileData.checkedIn\">\n" +
    "        <a ui-sref=\"app.activityList\" class=\"menuALeft\">\n" +
    "\n" +
    "          <span class=\"font-bold menuLabelLeft subMenuLabel\" translate=\"tab.allActivities\">All Activities</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "      \n" +
    "      <li ui-sref-active=\"active\" class=\"leftMenuHotel subMenu\" ng-show=\"hotelState.profileData.logged && hotelState.profileData.checkedIn\">\n" +
    "        <a ui-sref=\"app.hotel\" class=\"menuALeft\">\n" +
    "          <b class=\"badge bg-orange pull-right\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "          <span class=\"font-bold menuLabelLeft subMenuLabel\" translate=\"system.hotelActivities\">Hotel Deals</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "\n" +
    "      <li ui-sref-active=\"active\" class=\"leftMenuHotel subMenu\" ng-show=\"hotelState.profileData.hotelStaff || hotelState.profileData.admin\">\n" +
    "        <a ui-sref=\"app.dealList({staffScope:true})\" class=\"menuALeft\">\n" +
    "\n" +
    "          <span class=\"font-bold menuLabelLeft subMenuLabel\">Staff Deals</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "\n" +
    "      <li ui-sref-active=\"active\" class=\"leftMenuHotel subMenu\" ng-show=\"!hotelState.profileData.logged && hotelState.profileData.checkedIn\">\n" +
    "        <a ui-sref=\"app.hotelPreview\" class=\"menuALeft\">\n" +
    "          <b class=\"badge bg-orange pull-right\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "          <span class=\"font-bold menuLabelLeft subMenuLabel\" translate=\"system.hotelActivities\">Hotel Activities</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "\n" +
    "      <li ui-sref-active=\"active\" class=\"leftMenuHotel subMenu\" ng-show=\"!hotelState.profileData.hotelStaff\">\n" +
    "        <a ui-sref=\"app.dealList({staffScope:false})\" class=\"menuALeft\">\n" +
    "            \n" +
    "         <b class=\"badge bg-info pull-right\" ng-show=\"hotelNotification.notificationObj.myDealsNumber>0\">{{hotelNotification.notificationObj.myDealsNumber}}</b>\n" +
    "\n" +
    "          <span class=\"font-bold menuLabelLeft subMenuLabel\" translate=\"tab.myDeals\">My Deals</span>\n" +
    "        </a>\n" +
    "      </li>\n" +
    "      \n" +
    "    </ul>\n" +
    "  </li>\n" +
    "\n" +
    " \n" +
    "  \n" +
    "\n" +
    "  <li  ui-sref-active=\"active\" class=\"leftMenuCheckout\" ng-show=\"hotelState.profileData && !hotelState.profileData.checkedIn && (!hotelState.profileData.hotelStaff)\">\n" +
    "    <a ui-sref=\"app.checkin\" class=\"menuALeft\">\n" +
    "      <!--<i class=\"fa fa-sign-out navIconItem\"></i>-->\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <div class=\"menuIconLeft iconCheckout\"/>\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.checkin\">Checkin</span>\n" +
    "    </a>\n" +
    "  </li>\n" +
    "  \n" +
    "  <!--<li ui-sref-active=\"active\" ng-show=\"hotelState.currentHotelId && hotelState.currentHotel.id>0\">-->\n" +
    "    <!--<a ui-sref=\"app.editActivity\">-->\n" +
    "      <!--<b class=\"badge bg-info pull-right\" ng-show=\"hotelUnreadActivitiesNumber\">{{hotelUnreadActivitiesNumber}}</b>-->\n" +
    "      <!--&lt;!&ndash;<i class=\"fa fa-weibo navIconItem\"></i>&ndash;&gt;-->\n" +
    "      <!--<img src=\"angulr/img/build/menu/iconHotelLeft.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <!--<span class=\"font-bold menuLabelLeft\" translate=\"system.activities\">Activities</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  <li ui-sref-active=\"active\" class=\"leftMenuChat\">\n" +
    "    <!--<a ng-click=\"clickLoading('app.chatList', null, null, {hotelScope: hotelState.profileData.checkedIn && app.header.tabIndex==1})\" class=\"menuALeft uiSrefLink\">-->\n" +
    "    <a ng-click=\"clickLoading('app.chatList', null, null, {hotelScope: hotelState.profileData.checkedIn})\" class=\"menuALeft uiSrefLink\">\n" +
    "      <b class=\"badge bg-orange pull-right menuNotificationLeft\" ng-show=\"hotelNotification.notificationObj.unreadChatsCounter && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.unreadChatsCounter}}</b>\n" +
    "      <!--<i class=\"fa fa-comments-o navIconItem\"></i>-->\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/chat-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <div class=\"menuIconLeft iconChat\"/>\n" +
    "      <!--<span class=\"font-bold menuLabelLeft\" translate=\"system.contacts\" ng-hide=\"app.header.tabIndex==1\">Contacts</span>-->\n" +
    "      <span class=\"font-bold menuLabelLeft\" translate=\"system.chat\">Chat</span>\n" +
    "    </a>\n" +
    "    <a ui-sref=\"app.chatList\" style=\"display: none;\"></a>\n" +
    "  </li>\n" +
    "    \n" +
    "  <li ui-sref-active=\"active\" class=\"leftMenuWall\"  ng-show=\"hotelState.profileData.checkedIn\">\n" +
    "    <a ng-click=\"clickLoading('app.wall')\" class=\"menuALeft uiSrefLink\">\n" +
    "      <!--<b class=\"badge bg-info pull-right\">9</b>-->\n" +
    "      \n" +
    "      <b class=\"badge bg-orange pull-right menuNotificationLeft\" ng-show=\"hotelNotification.notificationObj.hotelUnreadWallNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadWallNumber}}</b>\n" +
    "\n" +
    "      \n" +
    "      <!--<i class=\"fa fa-comment-o  navIconItem\"></i>-->\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/wallpost-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <div class=\"menuIconLeft iconWall\"/>\n" +
    "      <span ui-sref=\"app.wall\" class=\"font-bold menuLabelLeft\" translate=\"system.wall\">Wall</span>\n" +
    "    </a>\n" +
    "    <a ui-sref=\"app.wall\" style=\"display: none;\"></a>\n" +
    "  </li>\n" +
    "\n" +
    "  <li ui-sref-active=\"active\" class=\"leftMenuWall\" >\n" +
    "    <a ui-sref=\"app.menuList({filterCity: hotelState.profileData.hotelCity, filterHotelId: hotelState.profileData.hotelId} )\" class=\"menuALeft uiSrefLink\">\n" +
    "      <!--<b class=\"badge bg-info pull-right\">9</b>-->\n" +
    "\n" +
    "      <!--<b class=\"badge bg-orange pull-right menuNotificationLeft\" ng-show=\"hotelNotification.notificationObj.hotelUnreadWallNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadWallNumber}}</b>-->\n" +
    "\n" +
    "      <i class=\"fa fa-cutlery navIconItem leftMenuIcon\"></i>\n" +
    "\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/wallpost-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <!--<div class=\"menuIconLeft iconWall\"/>-->\n" +
    "      <span class=\"font-bold menuLabelLeft\" translate=\"system.menu\">Menu</span>\n" +
    "    </a>\n" +
    "    <!--<a ui-sref=\"app.menuList\" style=\"display: none;\"></a>-->\n" +
    "  </li>\n" +
    "    \n" +
    "  <li  ui-sref-active=\"active\" class=\"leftMenuCheckout\" ng-show=\"hotelState.profileData.checkedIn && (!hotelState.profileData.hotelStaff)\">\n" +
    "    <a ng-click=\"checkinService.resetCheckin()\" class=\"menuALeft uiSrefLink\">\n" +
    "      <!--<i class=\"fa fa-sign-out navIconItem leftMenuIcon\"></i>-->\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <div class=\"menuIconLeft iconCheckout\"/>\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.checkout\">Checkout</span>\n" +
    "    </a>\n" +
    "  </li> \n" +
    "        \n" +
    "    \n" +
    "        <li  ui-sref-active=\"active\" class=\"leftMenuLogout\" ng-show=\"hotelState.profileData && hotelState.profileData.id>0\">\n" +
    "          <a ng-click=\"loginService.resetLogin()\" class=\"menuALeft uiSrefLink\">\n" +
    "            <!--<i class=\"fa fa-chain-broken navIconItem leftMenuIcon\"></i>-->\n" +
    "            <img ng-src=\"angulr/img/build/menu/bottom/logout-inactive.png\" class=\"menuIconLeft\"/>\n" +
    "            <span class=\"menuLabelLeft\" translate=\"system.logout\">Logout</span>\n" +
    "          </a>\n" +
    "        </li> \n" +
    "    \n" +
    "       <li  ui-sref-active=\"active\" class=\"leftMenuLogout\" ng-show=\"!hotelState.profileData.logged || hotelState.profileData.id==0\">\n" +
    "          <a class=\"menuALeft\" ng-click=\"openModal('login')\">\n" +
    "            <!--<i class=\"fa fa-chain-broken navIconItem leftMenuIcon\"></i>-->\n" +
    "            <img ng-src=\"angulr/img/build/menu/bottom/logout-inactive.png\" class=\"menuIconLeft\"/>\n" +
    "            <span class=\"menuLabelLeft\" translate=\"Login\">Login</span>\n" +
    "          </a>\n" +
    "        </li>\n" +
    "\n" +
    "  <li class=\"leftMenuLanguage\">\n" +
    "    <a ui-sref=\"app.language\" class=\"menuALeft\">\n" +
    "      <b  class=\"badge bg-orange-border pull-right menuNotificationLeft\" style=\"position: absolute;right: 5px; border-width: 2px;\">{{hotelState.prefferedLanguage.charAt(0).toUpperCase() + hotelState.prefferedLanguage.slice(1)}}</b>\n" +
    "      <!--<i class=\"fa fa-chain-broken navIconItem leftMenuIcon\"></i>-->\n" +
    "      <img ng-src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.language\">language</span>\n" +
    "\n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "    <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li>\n" +
    "\n" +
    "  <li class=\"line dk\" ng-show=\"hotelState.profileData\"></li>\n" +
    "\n" +
    "\n" +
    "  <li  ui-sref-active=\"active\" >\n" +
    "    <a ui-sref=\"app.feedback\" class=\"menuALeft\">\n" +
    "      <!--<b  class=\"badge bg-orange-border pull-right menuNotificationLeft\" style=\"position: absolute;right: 5px; border-width: 2px;\">{{gelLanguageKey()}}</b>-->\n" +
    "      <i class=\"fa fa-envelope-o navIconItem leftMenuIcon\"></i>\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.feedback\">Feedback</span>\n" +
    "\n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "    <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li>\n" +
    "\n" +
    "\n" +
    "\n" +
    "  <li  ui-sref-active=\"active\" ng-if=\"hotelState.profileData.checkedIn && (hotelState.profileData.hotelStaff || hotelState.profileData.admin)\">\n" +
    "    <a ui-sref=\"app.feed\" class=\"menuALeft\">\n" +
    "      <!--<b  class=\"badge bg-orange-border pull-right menuNotificationLeft\" style=\"position: absolute;right: 5px; border-width: 2px;\">{{gelLanguageKey()}}</b>-->\n" +
    "      <i class=\"fa fa-rss navIconItem leftMenuIcon\"></i>\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.hotelFeed\">HotelFeed</span>\n" +
    "      \n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "      <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li>\n" +
    "\n" +
    "  <li  ui-sref-active=\"active\" class=\"leftMenuLogout\" ng-show=\"!hotelState.profileData.logged || hotelState.profileData.id==0\">\n" +
    "    <a ui-sref=\"app.hotelLogin\" class=\"menuALeft\">\n" +
    "      <!--<i class=\"fa fa-chain-broken navIconItem leftMenuIcon\"></i>-->\n" +
    "      <div class=\"menuIconLeft iconHotel\"/>\n" +
    "      <span class=\"menuLabelLeft\" translate=\"Hotel Login\">Hotel Login</span>\n" +
    "    </a>\n" +
    "  </li>\n" +
    "  \n" +
    "  <li  ui-sref-active=\"active\" ng-if=\"hotelState.profileData.checkedIn && (hotelState.profileData.hotelStaff || hotelState.profileData.admin)\">\n" +
    "    <a ui-sref=\"app.editHotel\" class=\"menuALeft\">\n" +
    "      <!--<b  class=\"badge bg-orange-border pull-right menuNotificationLeft\" style=\"position: absolute;right: 5px; border-width: 2px;\">{{gelLanguageKey()}}</b>-->\n" +
    "      <i class=\"fa fa-pencil-square-o navIconItem leftMenuIcon\"></i>\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <span class=\"menuLabelLeft\" translate=\"page.hotel.updateHotelInfo\">Update HotelInfo</span>\n" +
    "      \n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "      <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li> \n" +
    "  \n" +
    "  <li  ui-sref-active=\"active\" >\n" +
    "    <a ui-sref=\"app.about\" class=\"menuALeft\">\n" +
    "      <i class=\"fa fa-info-circle navIconItem leftMenuIcon\"></i>\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <span class=\"menuLabelLeft\" translate=\"system.about\">About</span>\n" +
    "      \n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "      <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li> \n" +
    "    \n" +
    "  \n" +
    "  <li  ui-sref-active=\"active\" ng-if=\"::isSmartDevice\">\n" +
    "    <a ui-sref=\"app.impressum\" class=\"menuALeft\">\n" +
    "      <i class=\"fa fa-info navIconItem leftMenuIcon\"></i>\n" +
    "      <!--<img src=\"angulr/img/build/menu/bottom/language-inactive.png\" class=\"menuIconLeft\"/>-->\n" +
    "      <span class=\"menuLabelLeft\">Impressum</span>\n" +
    "      \n" +
    "    </a>\n" +
    "    <!--<a id=\"languageSelector\" editable-select=\"languages\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "      <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "  </li>\n" +
    "  <!--<li>-->\n" +
    "    <!--<a ui-sref=\"app.form.imagecrop\">-->\n" +
    "      <!-- -->\n" +
    "      <!--&lt;!&ndash;<b class=\"badge bg-success pull-right\">30%</b>&ndash;&gt;-->\n" +
    "      <!--<span translate=\"system.profile\">My cccc</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "  \n" +
    "  <!--<li>-->\n" +
    "    <!--<a ui-sref=\"app.docs\">-->\n" +
    "      <!--<i class=\"icon-question icon\"></i>-->\n" +
    "      <!--<span translate=\"aside.nav.your_stuff.DOCUMENTS\">Documents</span>-->\n" +
    "    <!--</a>-->\n" +
    "  <!--</li>-->\n" +
    "</ul>\n" +
    "<!-- / list -->\n" +
    "");
  $templateCache.put("angulr/tpl/blocks/page_footer.html",
    "<p>\n" +
    "  <small class=\"text-muted\">Web app framework base on Bootstrap and AngularJS<br>&copy; 2015</small>\n" +
    "</p>");
  $templateCache.put("angulr/tpl/blocks/settings.html",
    "<!--&lt;!&ndash; settings &ndash;&gt;-->\n" +
    "  <!--<button class=\"btn btn-default no-shadow pos-abt\" ui-toggle-class=\"active\" target=\".settings\">-->\n" +
    "    <!--<i class=\"fa fa-spin fa-gear\"></i>-->\n" +
    "  <!--</button>-->\n" +
    "  <!--<div class=\"panel-heading\">-->\n" +
    "    <!--Settings-->\n" +
    "  <!--</div>-->\n" +
    "  <!--<div class=\"panel-body\"> -->\n" +
    "    <!--<div class=\"m-b-sm\">-->\n" +
    "      <!--<label class=\"i-switch bg-info pull-right\">-->\n" +
    "        <!--<input type=\"checkbox\" ng-model=\"app.settings.headerFixed\">-->\n" +
    "        <!--<i></i>-->\n" +
    "      <!--</label>-->\n" +
    "      <!--Fixed header -->\n" +
    "    <!--</div>-->\n" +
    "    <!--<div class=\"m-b-sm\">-->\n" +
    "      <!--<label class=\"i-switch bg-info pull-right\">-->\n" +
    "        <!--<input type=\"checkbox\" ng-model=\"app.settings.asideFixed\">-->\n" +
    "        <!--<i></i>-->\n" +
    "      <!--</label>-->\n" +
    "      <!--Fixed aside -->\n" +
    "    <!--</div>-->\n" +
    "    <!--<div class=\"m-b-sm\">-->\n" +
    "      <!--<label class=\"i-switch bg-info pull-right\">-->\n" +
    "        <!--<input type=\"checkbox\" ng-model=\"app.settings.asideFolded\">-->\n" +
    "        <!--<i></i>-->\n" +
    "      <!--</label>-->\n" +
    "      <!--Folded aside -->\n" +
    "    <!--</div>-->\n" +
    "    <!--<div class=\"m-b-sm\">-->\n" +
    "      <!--<label class=\"i-switch bg-info pull-right\">-->\n" +
    "        <!--<input type=\"checkbox\" ng-model=\"app.settings.asideDock\">-->\n" +
    "        <!--<i></i>-->\n" +
    "      <!--</label>-->\n" +
    "      <!--Dock aside-->\n" +
    "    <!--</div>-->\n" +
    "    <!--<div>-->\n" +
    "      <!--<label class=\"i-switch bg-info pull-right\">-->\n" +
    "        <!--<input type=\"checkbox\" ng-model=\"app.settings.container\">-->\n" +
    "        <!--<i></i>-->\n" +
    "      <!--</label>-->\n" +
    "      <!--Boxed layout-->\n" +
    "    <!--</div>-->\n" +
    "  <!--</div>-->\n" +
    "  <!--<div class=\"wrapper b-t b-light bg-light lter r-b\">-->\n" +
    "    <!--<div class=\"row row-sm\">-->\n" +
    "      <!--<div class=\"col-xs-6\">-->\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-black'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-black';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" name=\"a\" ng-model=\"app.settings.themeID\" value=\"1\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-black header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-black\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-dark'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" name=\"a\" ng-model=\"app.settings.themeID\" value=\"13\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-dark header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-white-only'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-black';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"2\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-black\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-primary'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"3\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-primary header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-info'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-black';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"4\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-info header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-black\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-success'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"5\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-success header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-danger'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-white-only'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"6\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-danger header\"></b>-->\n" +
    "            <!--<b class=\"bg-white header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "      <!--</div>-->\n" +
    "      <!--<div class=\"col-xs-6\">-->\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-black'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-black'; -->\n" +
    "          <!--app.settings.asideColor='bg-white b-r';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"7\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-black header\"></b>-->\n" +
    "            <!--<b class=\"bg-black header\"></b>-->\n" +
    "            <!--<b class=\"bg-white\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-dark'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-dark'; -->\n" +
    "          <!--app.settings.asideColor='bg-light';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" name=\"a\" ng-model=\"app.settings.themeID\" value=\"14\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-dark header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark header\"></b>-->\n" +
    "            <!--<b class=\"bg-light\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-info dker'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-info dker'; -->\n" +
    "          <!--app.settings.asideColor='bg-light dker b-r';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"8\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-info dker header\"></b>-->\n" +
    "            <!--<b class=\"bg-info dker header\"></b>-->\n" +
    "            <!--<b class=\"bg-light dker\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-primary'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-primary'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"9\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-primary header\"></b>-->\n" +
    "            <!--<b class=\"bg-primary header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-info dker'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-info dk'; -->\n" +
    "          <!--app.settings.asideColor='bg-black';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"10\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-info dker header\"></b>-->\n" +
    "            <!--<b class=\"bg-info dk header\"></b>-->\n" +
    "            <!--<b class=\"bg-black\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "\n" +
    "        <!--<label class=\"i-checks block m-b\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-success'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-success';-->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "          <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"11\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-success header\"></b>-->\n" +
    "            <!--<b class=\"bg-success header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "        <!-- -->\n" +
    "        <!--<label class=\"i-checks block\" ng-click=\"-->\n" +
    "          <!--app.settings.navbarHeaderColor='bg-danger dker bg-gd'; -->\n" +
    "          <!--app.settings.navbarCollapseColor='bg-danger dker bg-gd'; -->\n" +
    "          <!--app.settings.asideColor='bg-dark';-->\n" +
    "         <!--\">-->\n" +
    "          <!--<input type=\"radio\" ng-model=\"app.settings.themeID\" value=\"12\">-->\n" +
    "          <!--<span class=\"block bg-light clearfix pos-rlt\">-->\n" +
    "            <!--<span class=\"active pos-abt w-full h-full bg-black-opacity text-center\">-->\n" +
    "              <!--<i class=\"glyphicon glyphicon-ok text-white m-t-xs\"></i>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<b class=\"bg-danger dker header\"></b>-->\n" +
    "            <!--<b class=\"bg-danger dker header\"></b>-->\n" +
    "            <!--<b class=\"bg-dark\"></b>-->\n" +
    "          <!--</span>-->\n" +
    "        <!--</label>-->\n" +
    "      <!--</div>-->\n" +
    "    <!--</div>-->\n" +
    "  <!--</div>-->\n" +
    "<!--&lt;!&ndash; /settings &ndash;&gt;-->");
  $templateCache.put("angulr/tpl/hotel/blocks/addActivityAccardion.html",
    "<!--<accordion close-others=\"oneAtATime\">-->\n" +
    "    <!--<accordion-group is-open=\"accordionStatus.newActivityOpen\" is-disabled=\"accordionStatus.isNewActivityDisabled\" class=\"b-info\">-->\n" +
    "        <!--<accordion-heading>-->\n" +
    "            <!--<div>-->\n" +
    "                <!--<i ng-class=\"::localState.newActivityAccordionIconClass\"></i>  &nbsp; {{::'page.activities.addActivity' | translate }} <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.newActivityOpen, 'fa-angle-right': !accordionStatus.newActivityOpen}\"></i>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</accordion-heading>-->\n" +
    "\n" +
    "\n" +
    "        <!--<form class=\"m-b-none ng-pristine ng-valid \" ng-submit=\"submitNewActivity()\" name=\"newActivityForm\">-->\n" +
    "\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.activityTitle' | translate}}</label>-->\n" +
    "                <!--<input type=\"text\" class=\"form-control\" ng-model=\"localState.newActivity.title\" placeholder=\"{{::'page.activities.activityTitle' | translate}}\" required>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "           <!---->\n" +
    "            <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.activityShortDescription' | translate}}</label>-->\n" +
    "                    <!--<div style=\"  /*margin-bottom: -20px;*/\">-->\n" +
    "                        <!--&lt;!&ndash;<small>{{::'system.restLength' | translate}}:</small> &ndash;&gt;-->\n" +
    "                        <!--<span class=\"count\" ng-bind=\"localState.maxActivityShortDescription - localState.newActivity.shortDescription.length\" ng-class=\"{danger: localState.newActivity.shortDescription.length > localState.maxActivityShortDescription}\">{{::localState.maxActivityShortDescription}}</span>-->\n" +
    "                    <!--</div>-->\n" +
    "                <!---->\n" +
    "                <!--<textarea class=\"form-control\" rows=\"5\" ng-model=\"localState.newActivity.shortDescription\" placeholder=\"{{::'page.activities.activityShortDescription' | translate}}\"></textarea>-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.activityDescription' | translate}}</label>-->\n" +
    "                    <!--<div style=\"  margin-bottom: -20px;\">-->\n" +
    "                        <!--&lt;!&ndash;<small>{{::'system.restLength' | translate}}:</small> &ndash;&gt;-->\n" +
    "                        <!--<span class=\"count\" ng-bind=\"localState.maxActivityDescription - localState.newActivity.description.length\" ng-class=\"{danger: localState.newActivity.description.length > localState.maxActivityDescription}\">{{::localState.maxActivityDescription}}</span>-->\n" +
    "                    <!--</div>-->\n" +
    "                <!---->\n" +
    "                <!--<div text-angular ng-model=\"localState.newActivity.description\" class=\"btn-groups\"  ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo', 'clear']]\" ></div>-->\n" +
    "                <!--&lt;!&ndash;<textarea class=\"form-control\" rows=\"5\" ng-model=\"localState.newActivity.description\" placeholder=\"{{::'page.activities.activityDescription' | translate}}\"></textarea>&ndash;&gt;-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.validFrom' | translate}}</label>-->\n" +
    "\n" +
    "                <!--<div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">-->\n" +
    "                    <!--<input type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control\" datepicker-popup=\"{{format}}\" ng-model=\"localState.newActivity.validFrom\" is-open=\"opened\" datepicker-options=\"dateOptions\" ng-required=\"true\" close-text=\"Close\" required/>-->\n" +
    "                                        <!--<span class=\"input-group-btn\">-->\n" +
    "                                        <!--<button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>-->\n" +
    "                                    <!--</span>-->\n" +
    "                <!--</div>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.validTo' | translate}}</label>-->\n" +
    "                <!--<div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">-->\n" +
    "                    <!--<input type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control\" datepicker-popup=\"{{format}}\" ng-model=\"localState.newActivity.validTo\" is-open=\"opened\" datepicker-options=\"dateOptions\" ng-required=\"true\" close-text=\"Close\" required/>-->\n" +
    "                                        <!--<span class=\"input-group-btn\">-->\n" +
    "                                        <!--<button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>-->\n" +
    "                                    <!--</span>-->\n" +
    "                <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "            <!---->\n" +
    "            <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<label>{{::'page.activities.activityLogo' | translate}}</label>-->\n" +
    "\n" +
    "                <!--<input ng-model=\"newActivityFile\"-->\n" +
    "                       <!--onchange=\"angular.element(this).scope().newFile_changed(this, 'activity', angular.element(this).scope().localState.newActivity.activityId)\"-->\n" +
    "                       <!--type=\"file\" accept=\"image/*\" />-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"form-group\">-->\n" +
    "                <!--<button type=\"submit\" class=\"btn btn-block btn-lg btn-success\" ng-disabled=\"localState.newActivity.shortDescription.length > localState.maxShortDescription || localState.newActivity.shortDescription.length === 0\">{{::'page.activities.addActivity' | translate }}</button>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</form>-->\n" +
    "\n" +
    "    <!--</accordion-group>-->\n" +
    "<!--</accordion>-->");
  $templateCache.put("angulr/tpl/hotel/blocks/chatListCustomer.html",
    "<li class=\"list-group-item chatListCol\">\n" +
    "    <a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\">\n" +
    "        <div ng-click=\"clickLoading(null, null, '#/app/chat/'+nextCustomer.id)\" style=\"cursor: pointer;\">\n" +
    "            <a href ui-sref=\"app.user({userId: nextCustomer.id})\" class=\"avatar thumb-sm m-r\">\n" +
    "                <!--<img src=\"{{getProfileImageUrl(nextCustomer.id)}}\" class=\"r r-2x\">-->\n" +
    "                <img ng-src=\"{{nextCustomer.avatarUrl}}\" class=\"r r-2x\">\n" +
    "                <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "                <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">\n" +
    "                {{hotelNotification.notificationObj.lastMessageTimesToMe[nextCustomer.id]}}\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">\n" +
    "                <i class=\"fa fa-angle-right\"></i>\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">\n" +
    "                                             <span style=\"margin-left: 4px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]\">\n" +
    "                                              +{{hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]}}\n" +
    "                                            </span>\n" +
    "            </a>\n" +
    "\n" +
    "            <div class=\"chatUserInfo\">\n" +
    "                <small class=\"text-muted clear text-ellipsis lastMessageFrom\">\n" +
    "                    <!-- eugen: ng-class=\"unreadChatProSenderCount[nextCustomer.id]? 'unreadChatMessage':''\" -->\n" +
    "                    <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\">\n" +
    "\n" +
    "                        <a ng-show=\"nextCustomer.hotelName && nextCustomer.city\" class=\"text-ellipsis lastChatMessagesToMe\" style=\"text-decoration: underline; padding-top: 5px; padding-bottom: 5px;\" ui-sref=\"app.hotelList({filterCity: nextCustomer.hotelCity, filterHotelId: nextCustomer.hotelId})\">{{nextCustomer.hotelCity}}, {{nextCustomer.hotelName}}</a>\n" +
    "                        <span ng-hide=\"nextCustomer.hotelName\" class=\"text-ellipsis lastChatMessagesToMe\">{{nextCustomer.city}}</span>\n" +
    "                        <!--{{hotelNotification.notificationObjlastMessagesToMe[nextCustomer.id]}}-->\n" +
    "                    </a>\n" +
    "                </small>\n" +
    "\n" +
    "                <a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"chatListCustomerName\">\n" +
    "                    {{nextCustomer.firstName}} {{nextCustomer.lastName}}\n" +
    "                    <span class=\"label bg-orange inline hotelStaff\" ng-show=\"nextCustomer.hotelStaff\" style=\"margin-left: 3px;\">{{::'system.hotelStaff' | translate}}</span>\n" +
    "\n" +
    "                    <div class=\"inline chatUserLanguages\" ng-hide=\"!nextCustomer.languages || nextCustomer.hotelStaff\">\n" +
    "                        <!--<div class=\"col-xs-6\">-->\n" +
    "                                                        <span ng-repeat=\"nextLanguage in nextCustomer.languages\">\n" +
    "                                                            <img class=\"language-flag\" src=\"angulr/icons/flags/{{nextLanguage}}.png\"\n" +
    "                                                                 alt=\"{{nextLanguage}}\"/>\n" +
    "                                                        </span>\n" +
    "                        <!--</div>-->\n" +
    "                    </div>\n" +
    "                </a>\n" +
    "            </div>\n" +
    "\n" +
    "        </div>\n" +
    "    </a>\n" +
    "</li>");
  $templateCache.put("angulr/tpl/hotel/blocks/dealSubTabBlock.html",
    "<div class=\"ulHorizontal\">\n" +
    "    \n" +
    "    <ul class=\"horizontal myFlex\" ng-style=\"::!isSmartDevice && {'padding-left': '18px'}\">\n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"!hotelState.profileData.checkedIn\">\n" +
    "            <a ui-sref=\"app.activityList\" class=\"menuALeft\">\n" +
    "        \n" +
    "                <span class=\"menuLabelLeftLabel\" translate=\"tab.allActivities\">All Activities</span>\n" +
    "            </a>\n" +
    "        </li>\n" +
    "        \n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"hotelState.profileData.logged && hotelState.profileData.checkedIn\">\n" +
    "            <a ui-sref=\"app.hotel\" class=\"menuALeft\">\n" +
    "                <b class=\"badge bg-orange pull-right\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "                <span class=\"menuLabelLeftLabel\" translate=\"system.hotelActivities\">Hotel Deals</span>\n" +
    "            </a>\n" +
    "        </li>\n" +
    "        \n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"hotelState.profileData.hotelStaff || hotelState.profileData.admin\">\n" +
    "            <a ui-sref=\"app.dealList({staffScope:true})\" class=\"menuALeft\">\n" +
    "        \n" +
    "                <span class=\"menuLabelLeftLabel\">\n" +
    "                    <span>Staff Deals</span>                \n" +
    "                    <!--<span ng-show=\"hotelNotification.notificationObj.myDealsNumber>0\">-->\n" +
    "                        <!--({{hotelNotification.notificationObj.myDealsNumber}})-->\n" +
    "                    <!--</span>-->\n" +
    "                </span>\n" +
    "            </a>\n" +
    "        </li>\n" +
    "        \n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"!hotelState.profileData.logged && hotelState.profileData.checkedIn\">\n" +
    "            <a ui-sref=\"app.hotelPreview\" class=\"menuALeft\">\n" +
    "                <b class=\"badge bg-orange pull-right\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber && hotelState.profileData.id>0\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "                <span class=\"menuLabelLeftLabel\" translate=\"system.hotelActivities\">Hotel Activities</span>\n" +
    "            </a>\n" +
    "        </li>\n" +
    "        \n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"!hotelState.profileData.hotelStaff\">\n" +
    "            <a ui-sref=\"app.dealList({staffScope:false})\" class=\"menuALeft\">\n" +
    "                \n" +
    "                <span class=\"menuLabelLeftLabel\">\n" +
    "                    <span translate=\"tab.myDeals\">My Deals</span>                \n" +
    "                    <span ng-show=\"hotelNotification.notificationObj.myDealsNumber>0\">\n" +
    "                        ({{hotelNotification.notificationObj.myDealsNumber}})\n" +
    "                    </span>\n" +
    "                </span>\n" +
    "\n" +
    "            </a>\n" +
    "        </li>\n" +
    "   \n" +
    "        <li ui-sref-active=\"active\" class=\"leftMenuHotel\" ng-show=\"hotelState.profileData.checkedIn\">\n" +
    "            <a ui-sref=\"app.menuList({filterCity: hotelState.profileData.hotelCity, filterHotelId: hotelState.profileData.hotelId} )\" class=\"menuALeft\">\n" +
    "        \n" +
    "                <!--<b class=\"badge bg-info pull-right\" ng-show=\"hotelNotification.notificationObj.myDealsNumber>0\">{{hotelNotification.notificationObj.myDealsNumber}}</b>-->\n" +
    "                <i class=\"fa fa-cutlery\"></i>\n" +
    "\n" +
    "                <span class=\"menuLabelLeftLabel\" translate=\"system.menu\">Menu</span>\n" +
    "            </a>\n" +
    "        </li>\n" +
    "\n" +
    "    </ul>\n" +
    "    \n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/editActivityAccardion.html",
    "<accordion close-others=\"oneAtATime\">\n" +
    "    <!--<accordion-group is-open=\"accordionStatus.openEditActivities[showActivity.activityId]\" is-disabled=\"accordionStatus.isFirstDisabled\" class=\"b-info\">-->\n" +
    "    <accordion-group is-open=\"accordionStatus.openEditActivity\" is-disabled=\"accordionStatus.isFirstDisabled\" class=\"b-info\">\n" +
    "        <accordion-heading style=\"white-space: nowrap;\">\n" +
    "            <i class=\"fa fa-edit\"></i>  &nbsp; {{'page.activities.editActivity' | translate }} <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.openEditActivity, 'fa-angle-right': !accordionStatus.openEditActivity}\"></i>\n" +
    "        </accordion-heading>\n" +
    "  \n" +
    "\n" +
    "        <form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"submitActivityChange(showActivity)\" name=\"editActivityForm\">\n" +
    "\n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{'page.activities.activityTitle' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <input type=\"text\" class=\"form-control focusModal\" ng-model=\"showActivity.title\" placeholder=\"{{'page.activities.activityTitle' | translate}}\" required>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\" id=\"img{{showActivity.initId}}\">\n" +
    "                <label>{{::'page.activities.activityLogo' | translate}} (visible first after saving)</label>\n" +
    "\n" +
    "                <input ng-model=\"newActivityFile\" ngf-fix-orientation=\"true\"\n" +
    "                       onchange=\"angular.element(this).scope().newFile_changed(this, 'activity')\"\n" +
    "                       type=\"file\" accept=\"image/*\"/>\n" +
    "                <!--<input type=\"button\" ng-click=\"uploadFilesX()\">-->\n" +
    "            </div>\n" +
    "\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{'page.activities.activityShortDescription' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <textarea  class=\"form-control focusModal\" rows=\"5\" ng-model=\"showActivity.shortDescription\" placeholder=\"{{'page.activities.activityShortDescription' | translate}}\" required></textarea>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{'page.activities.activityDescription' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <div  text-angular ng-model=\"showActivity.description\" class=\"btn-groups focusModal\"  ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo']]\" required></div>\n" +
    "                <!--<textarea class=\"form-control\" rows=\"5\" ng-model=\"newactivity.description\" placeholder=\"{{'page.activities.activityDescription' | translate}}\"></textarea>-->\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <!--<label class=\"control-label\" style=\"white-space: nowrap;color:red;\">{{::'page.activities.hidden' | translate}}: </label>-->\n" +
    "                <div class=\"\">\n" +
    "                    <div class=\"btn-group\">\n" +
    "                        <div class=\"checkbox\">\n" +
    "                            <label class=\"i-checks\">\n" +
    "                                <input type=\"checkbox\" ng-model=\"showActivity.hidden\"><i></i> {{::'page.activities.hidden' | translate}}\n" +
    "                            </label>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"control-label\" style=\"white-space: nowrap;\">{{::'page.activities.dealAllowed' | translate}}: </label>\n" +
    "                <div class=\"\">\n" +
    "                    <div class=\"btn-group\">\n" +
    "                        <label class=\"i-switch m-t-xs m-r\">\n" +
    "                            <input type=\"checkbox\" checked=\"true\" ng-model=\"showActivity.dealAllowed\">\n" +
    "                            <i></i>\n" +
    "                        </label>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"control-label\" style=\"white-space: nowrap; color:red;\">{{::'page.activities.lastMinuteActivity' | translate}}: </label>\n" +
    "                <div class=\"\">\n" +
    "                    <div class=\"btn-group\">\n" +
    "                        <label class=\"i-switch m-t-xs m-r\">\n" +
    "                            <input type=\"checkbox\" checked=\"true\" ng-model=\"showActivity.lastMinute\">\n" +
    "                            <i></i>\n" +
    "                        </label>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "            \n" +
    "            <div ng-if=\"showActivity.lastMinute!=true\">\n" +
    "                \n" +
    "                <div class=\"form-group\">\n" +
    "                    <label>{{'page.activities.validFrom' | translate}} <span class=\"requiredField\">*</span></label>\n" +
    "\n" +
    "                    <div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">\n" +
    "                        <input  type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control focusModal\" datepicker-popup=\"{{format}}\" ng-model=\"showActivity.validFrom\" is-open=\"opened\" datepicker-options=\"dateOptions\" close-text=\"Close\" ng-required=\"showActivity.lastMinute!=true\"/>\n" +
    "                    <span class=\"input-group-btn\">\n" +
    "                    <button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>\n" +
    "                    </span>\n" +
    "                    </div>\n" +
    "\n" +
    "                </div>\n" +
    "                <div class=\"form-group\">\n" +
    "                    <label>{{'page.activities.validTo' | translate}} <span class=\"requiredField\">*</span></label>\n" +
    "                    <div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">\n" +
    "                        <input  type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control focusModal\" datepicker-popup=\"{{format}}\" ng-model=\"showActivity.validTo\" is-open=\"opened\" datepicker-options=\"dateOptions\" close-text=\"Close\" ng-required=\"showActivity.lastMinute!=true\"/>\n" +
    "                    <span class=\"input-group-btn\">\n" +
    "                    <button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>\n" +
    "                    </span>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "                \n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\" ng-if=\"showActivity.lastMinute==true\">\n" +
    "                <!--<label class=\"control-label\" style=\"white-space: nowrap;color:red;\">{{::'page.activities.notificateCreation' | translate}}: </label>-->\n" +
    "                <div class=\"\">\n" +
    "                    <div class=\"btn-group\">\n" +
    "                        <div class=\"checkbox\">\n" +
    "                            <label class=\"i-checks\">\n" +
    "                                <input type=\"checkbox\" ng-model=\"showActivity.notificateHotelCustomers\"><i></i> {{::'page.activities.notificateCreation' | translate}}\n" +
    "                            </label>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\" ng-if=\"showActivity.dealDaysDuration>0 && showActivity.dealAllowed==true\">\n" +
    "                <label>{{'page.activities.dealDaysDuration' | translate}}</label>\n" +
    "                <input type=\"number\" min=\"1\" class=\"form-control focusModal\" ng-model=\"showActivity.dealDaysDuration\" placeholder=\"{{'page.activities.activityTitle' | translate}}\">\n" +
    "\n" +
    "            </div>\n" +
    "            \n" +
    "            <!--<div class=\"form-group\" ng-show=\"showActivity.dealDaysDuration>0 && showActivity.dealAllowed\">-->\n" +
    "                <!--<label>{{'page.activities.limitDealNumber' | translate}} (0 : unlimited)</label>-->\n" +
    "                <!--<input type=\"number\" min=\"0\" class=\"form-control focusModal\" ng-model=\"showActivity.limitDealNumber\" placeholder=\"{{'page.activities.activityTitle' | translate}}\" required>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <button type=\"submit\" ng-disabled=\"(showActivity.shortDescription.length > localState.maxActivityShortDescription) || showActivity.shortDescription.length == 0\" class=\"btn btn-block btn-lg btn-success btn-addon\"><i class=\"fa fa-check-square-o\"></i>{{'page.activities.editActivity' | translate }}</button>\n" +
    "            </div>\n" +
    "        </form>\n" +
    "    </accordion-group>\n" +
    "</accordion>");
  $templateCache.put("angulr/tpl/hotel/blocks/editHotelAccardion.html",
    "<accordion close-others=\"oneAtATime\">\n" +
    "\n" +
    "    <accordion-group is-open=\"accordionStatus.editHotelOpen\" is-disabled=\"accordionStatus.isEditHotelDisabled\" class=\"b-info\">\n" +
    "\n" +
    "        <accordion-heading>\n" +
    "            <i ng-class=\"::localState.editHotelAccordionIconClass\"></i> &nbsp; {{localState.editHotelAccordionTitle}} <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.editHotelOpen, 'fa-angle-right': !accordionStatus.editHotelOpen}\"></i>\n" +
    "        </accordion-heading>\n" +
    "\n" +
    "        <form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"submitHotelChange(showHotel)\" name=\"hotelForm\">\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.hotelName' | translate }}<span class=\"requiredField\">*</span></label>\n" +
    "                <input type=\"text\" class=\"form-control\" ng-model=\"showHotel.name\" placeholder=\"{{::'page.hotel.hotelName' | translate }}\" required>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.activities.activityLogo' | translate}} (visible first after saving)</label>\n" +
    "\n" +
    "                <input ng-model=\"newActivityFile\"\n" +
    "                       onchange=\"angular.element(this).scope().newFile_changed(this, 'hotel', angular.element(this).scope().showHotel.creationTime)\"\n" +
    "                       type=\"file\" accept=\"image/*\" />\n" +
    "                <!--<input type=\"button\" ng-click=\"uploadFilesX()\">-->\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.hotelCode' | translate }}<span class=\"requiredField\">*</span></label>\n" +
    "                <!--<input type=\"text\" class=\"form-control\" ng-model=\"newHotel.pictureUrl\" placeholder=\"{{::'page.hotel.hotelLogo' | translate }}\">-->\n" +
    "                <input type=\"text\" class=\"form-control\" ng-model=\"showHotel.currentHotelAccessCode\" placeholder=\"{{::'page.hotel.hotelCode' | translate }}\" required>\n" +
    "\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.rating' | translate }}<span class=\"requiredField\">*</span></label>\n" +
    "                <input type=\"number\" max=\"5\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.rating\" placeholder=\"{{::'page.hotel.rating' | translate }}\" required></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <!--{{::'page.hotel.hotelEmail' | translate }}-->\n" +
    "                <label>Hotel-Staff E-Mail<span class=\"requiredField\">*</span></label>\n" +
    "                <input type=\"email\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.email\" placeholder=\"{{::'page.hotel.hotelEmail' | translate }}\"></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.phone' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.phone\" placeholder=\"{{::'page.hotel.phone' | translate }}\"></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.fax' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.fax\" placeholder=\"{{::'page.hotel.fax' | translate }}\"></in>\n" +
    "            </div>\n" +
    "           \n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.street' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.street\" placeholder=\"{{::'page.hotel.street' | translate }}\"></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.house' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.house\" placeholder=\"{{::'page.hotel.house' | translate }}\"></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.city' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.city\" placeholder=\"{{::'page.hotel.city' | translate }}\"></in>\n" +
    "            </div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.postalCode' | translate }}</label>\n" +
    "                <input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.postalCode\" placeholder=\"{{::'page.hotel.postalCode' | translate }}\"></in>\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.hotelDescription' | translate }}</label>\n" +
    "                <!--<div lazy-load=\"textAngular\">-->\n" +
    "                <div>\n" +
    "                    <!--<small>{{::'system.restLength' | translate}}:</small> -->\n" +
    "                    <span class=\"count\" ng-bind=\"localState.maxHotelDescription - showHotel.description.length\" ng-class=\"{danger: showHotel.description.length > localState.maxHotelDescription}\">1040</span>\n" +
    "                </div>\n" +
    "                <div text-angular ng-model=\"showHotel.description\" class=\"btn-groups\"   ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo', 'clear']]\" ></div>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label>{{::'page.hotel.info' | translate }}</label>\n" +
    "                <!--<div lazy-load=\"textAngular\">-->\n" +
    "                <div>\n" +
    "                    <!--<small>{{::'system.restLength' | translate}}:</small> -->\n" +
    "                    <span class=\"count\" ng-bind=\"localState.maxHotelDescription - showHotel.info.length\" ng-class=\"{danger: showHotel.info.length > localState.maxHotelDescription}\">1040</span>\n" +
    "                </div>\n" +
    "                <div text-angular ng-model=\"showHotel.info\" class=\"btn-groups\"   ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo', 'clear']]\" ></div>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <button type=\"submit\" ng-disabled=\"showHotel.description.length > localState.maxHotelDescription\" class=\"btn btn-block btn-lg btn-success btn-addon\"><i class=\"fa fa-check-square-o\"></i>{{::'page.hotel.updateHotelInfo' | translate }}</button>\n" +
    "            </div>\n" +
    "        </form>\n" +
    "    </accordion-group>\n" +
    "</accordion>");
  $templateCache.put("angulr/tpl/hotel/blocks/fullCheckinRequest.html",
    "<!--//TODO Problems: visualization Problems-->\n" +
    "<!--//TODO: if no hotelCode: chat flag, wall hide names -->\n" +
    "\n" +
    "<!--&lt;!&ndash;### START HotelCode &ndash;&gt;-->\n" +
    "<!--<div class=\"myCenter\" style=\"border-width: 1px;-->\n" +
    "    <!--border-style: solid;-->\n" +
    "    <!--border-radius: 5px;-->\n" +
    "    <!--padding: 5px;-->\n" +
    "    <!--margin-bottom: 10px;-->\n" +
    "    <!--border-color: lightgray;\" ng-if=\"hotelState.profileData.logged && !hotelState.profileData.fullCheckin\">-->\n" +
    "\n" +
    "    <!--<span style=\"margin-right: 10px;margin-top: 3px;font-weight: bolder; \">Your hotel checkin is limited:</span>-->\n" +
    "\n" +
    "    <!--<button ng-click=\"openModal('checkin')\"  class=\"btn btn-orange\" style=\"padding: 2px;height: 25px;\">Input HotelCode</button>-->\n" +
    "\n" +
    "    <!--<i style=\"margin-top: -4px; padding-left: 8px; padding-right: 5px; /*position: absolute;*/\" class=\"fa fa-info-circle navIconItem hotelCitySelectInfo\" ng-click=\"openHotelCodePopup()\"></i>-->\n" +
    "\n" +
    "<!--</div>-->\n" +
    "<!--&lt;!&ndash;### END HotelCode&ndash;&gt;-->");
  $templateCache.put("angulr/tpl/hotel/blocks/impressumBottomBlock.html",
    "<div class=\"text-center\">\n" +
    "    <a class=\"text-muted\"  ui-sref=\"app.impressum\">Impressum</a> |\n" +
    "    <a class=\"text-muted\"  ui-sref=\"app.agb\">AGB</a> |\n" +
    "    <a class=\"text-muted\"  ui-sref=\"app.dse\">Datenschutzerkl&auml;rung</a>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/menuItemBlock.html",
    "<div class=\"myFlex mySpaceBetween\" style=\"margin-bottom: 10px;\" ng-class=\"{'menuPriceItem' : !showMenuItem.delimiter}\">\n" +
    "    <div class=\"v-middle\" ng-if=\"isHotelStaffOrAdmin()\" style=\"width: 25px; padding: 0; text-align: center;\">\n" +
    "        <!-- editable username (text with validation) -->\n" +
    "        <i class=\"fa fa-bars\"></i>\n" +
    "        <div class=\"menuItem_{{showMenuItem.id}}_index menuItem_index hidden\">\n" +
    "            {{ showMenuItem.orderIndex }}\n" +
    "        </div>\n" +
    "    </div>\n" +
    "    <div ng-show=\"showMenuItem.amount>0\" style=\"margin-right: 3px;\">\n" +
    "        <span><b>{{showMenuItem.amount}}</b> x </span>\n" +
    "    </div>\n" +
    "    <div class=\"v-middle\" layout=\"column\" style=\"flex-grow: 1;\" ng-style=\"showMenuItem.delimiter && {'font-size': '17px'}\">\n" +
    "        <!-- editable username (text with validation) -->\n" +
    "        <a href=\"#\" editable-text=\"showMenuItem.title\" e-name=\"title\" e-form=\"rowform\" onbeforesave=\"checkName($data, showMenuItem)\" e-placeholder=\"Name\" e-required>\n" +
    "            <b>{{ showMenuItem.title || 'empty' }}</b>\n" +
    "        </a>        \n" +
    "        <a href=\"#\" editable-text=\"showMenuItem.shortDescription\" e-name=\"shortDescription\" e-form=\"rowform\" onbeforesave=\"checkDescription($data, showMenuItem)\" e-placeholder=\"Description\" e-required>\n" +
    "            {{ showMenuItem.shortDescription || '&nbsp;' }}\n" +
    "        </a>\n" +
    "    </div>\n" +
    "    <div class=\"v-middle\" style=\"width: 65px; padding: 2px; text-align: right;\" ng-style=\"showMenuItem.delimiter && {'visibility':'hidden'}\">\n" +
    "        <!-- editable status (select-local) -->\n" +
    "        <a class=\"menu-price\" href=\"#\" editable-text=\"showMenuItem.price\" e-name=\"price\" e-form=\"rowform\" onbeforesave=\"checkPrice($data, showMenuItem)\" e-placeholder=\"Price\" e-required ng-style=\"showMenuItem.delimiter && {'visibility':'hidden'}\">\n" +
    "            {{ showMenuItem.price | currency:\"\":2}} <i class=\"fa fa-eur menu-euro\"></i>\n" +
    "        </a>\n" +
    "    </div>\n" +
    "    <div class=\"v-middle\" style=\"white-space: nowrap; width: 75px;padding: 0;text-align: right; padding-top: 5px;\" ng-show=\"isHotelStaffOrAdmin()\" >\n" +
    "        <!-- form -->\n" +
    "        <form editable-form name=\"rowform\" onbeforesave=\"saveMenuItem($data, showMenuItem)\" ng-show=\"rowform.$visible\" class=\"form-buttons form-inline\" shown=\"inserted == showMenuItem\">\n" +
    "            <button type=\"submit\" ng-disabled=\"rowform.$waiting\" class=\"btn btn-sm btn-info\">\n" +
    "                save\n" +
    "            </button>\n" +
    "            <button type=\"button\" ng-disabled=\"rowform.$waiting\" ng-click=\"rowform.$cancel()\" class=\"btn btn-sm btn-default\" style=\"padding-left: 2px;padding-right: 2px;\">\n" +
    "                cancel\n" +
    "                <!--<i class=\"fa fa-times\"></i>-->\n" +
    "            </button>\n" +
    "        </form>\n" +
    "        <div class=\"buttons\" ng-show=\"!rowform.$visible\">\n" +
    "            <button class=\"btn btn-sm btn-info\" ng-click=\"rowform.$show()\">edit</button>\n" +
    "            <button class=\"btn btn-sm btn-danger\" ng-click=\"removeMenuItem(showMenuItem)\">del</button>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/menuItemOrderingBlock.html",
    "<div ng-class=\"{'menuPriceItem' : !showMenuItem.delimiter}\"  style=\"margin-bottom: 10px;\" >\n" +
    "    <div class=\"myFlex mySpaceBetween\">\n" +
    "\n" +
    "        <div class=\"v-middle\" style=\"width: 25px; padding: 0; text-align: center;\">\n" +
    "\n" +
    "            <b>{{showMenuItem.amount}}</b> x\n" +
    "\n" +
    "            <div class=\"menuItem_{{showMenuItem.id}}_index menuItem_index hidden\">\n" +
    "                {{ showMenuItem.orderIndex }}\n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"v-middle\" layout=\"column\" style=\"flex-grow: 1;\" ng-style=\"showMenuItem.delimiter && {'font-size': '17px'}\">\n" +
    "            <!-- editable username (text with validation) -->\n" +
    "            <a href=\"#\" >\n" +
    "                <b>{{ showMenuItem.title || 'empty' }}</b>\n" +
    "            </a>\n" +
    "            <a href=\"#\">\n" +
    "                {{ showMenuItem.shortDescription || '&nbsp;' }}\n" +
    "            </a>\n" +
    "        </div>\n" +
    "        <div layout=\"row\" class=\"v-middle\" style=\"width: 75px; padding: 2px; text-align: right;\" ng-style=\"showMenuItem.delimiter && {'visibility':'hidden'}\">\n" +
    "            <!-- editable status (select-local) -->\n" +
    "            <div class=\"menuItemAmountcount myBorder text-orange menuItem-{{showMenuItem.id}}-plus\" ng-click=\"changeOrderingAmount(showMenuItem, '+', 'menuItem-'+showMenuItem.id+'-plus')\">+</div>\n" +
    "            <div class=\"menuItemAmountcount myBorder text-orange menuItem-{{showMenuItem.id}}-minus\" ng-click=\"changeOrderingAmount(showMenuItem, '-', 'menuItem-'+showMenuItem.id+'-minus')\">-</div>\n" +
    "        </div>\n" +
    "\n" +
    "    </div>\n" +
    "    <div>\n" +
    "        <div class=\"v-middle\" style=\"white-space: nowrap; margin-left: 5px; padding: 0;\" >\n" +
    "            <a href=\"#\" ng-style=\"showMenuItem.delimiter && {'visibility':'hidden'}\">\n" +
    "                {{ showMenuItem.price*showMenuItem.amount | currency:\"\":2}} <i class=\"fa fa-eur menu-euro\"></i>\n" +
    "            </a>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.editActivityForm.html",
    "<div class=\"modal-body wrapper-lg\" stop-event=\"touchend\" ng-controller=\"EditActivityContr\" ng-init=\"unlockModal();\">\n" +
    "  \n" +
    "   <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "    \n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    <div class=\"myFlex\" ng-style=\"!isSmartDevice && {'flex-direction':'row', '-webkit-flex-direction': 'row'} || isSmartDevice && {'flex-direction':'column', '-webkit-flex-direction': 'column'}\" ng-controller=\"activityCtrl\">  \n" +
    "    \n" +
    "      <!--<div class=\"row\">-->\n" +
    "      <div style=\"min-width: 50%;\"  >\n" +
    "        \n" +
    "          <!--<div class=\"col-sm-6 b-r\">-->\n" +
    "          <div class=\"b-r\">\n" +
    "              \n" +
    "              <!--<div style=\"display: flex;  display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;justify-content: center; text-align: center;\">-->\n" +
    "                <!--{{:: 'page.checkin.youNeedLogin'| translate}}:-->\n" +
    "              <!--</div>-->\n" +
    "        \n" +
    "        \n" +
    "              <span  ng-if=\"showActivity.hotelId && !((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showActivity.hotelId) || hotelState.profileData.admin)\">You are not a staff for the Hotel {{showActivity.hotelName}}!</span>\n" +
    "        \n" +
    "              <div >\n" +
    "    \n" +
    "                  <!--START HOTEL Edit-->\n" +
    "        \n" +
    "                  <div class=\"hotelEditElt\" ng-if=\"((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showActivity.hotelId) || hotelState.profileData.admin)\" ng-show=\"showActivity.initId\">\n" +
    "                      \n" +
    "                      <!--<div ng-if=\"editActivityId=='new'\" data-ng-include=\" 'angulr/tpl/hotel/blocks/addActivityAccardion.html' \" />-->\n" +
    "                      \n" +
    "                      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/editActivityAccardion.html' \" />\n" +
    "                      \n" +
    "                  </div>\n" +
    "                  \n" +
    "              </div>\n" +
    "    \n" +
    "              <!-- END HOTEL Edit -->\n" +
    "    \n" +
    "          </div>\n" +
    "          \n" +
    "          \n" +
    "      </div>\n" +
    "          \n" +
    "      <!--<div class=\"b-r\">-->\n" +
    "      <div style=\"min-width: 48%; margin-left: 10px;\">\n" +
    "            \n" +
    "          <!-- Start hotel view --->\n" +
    "          <div ng-show=\"localState.showActivityId && showActivity.initId\">\n" +
    "    \n" +
    "             <b>{{::'system.editPreview' | translate}}:</b>\n" +
    "\n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showActivityBlock.html' \" />\n" +
    "\n" +
    "          </div>\n" +
    "        \n" +
    "          <!-- END hotel view --->\n" +
    "    \n" +
    "      </div>  \n" +
    "        \n" +
    "   </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.editDealForm.html",
    "<div class=\"modal-body wrapper-lg\" stop-event=\"touchend\" ng-controller=\"EditDealContr\" ng-init=\"unlockModal();\">\n" +
    "  \n" +
    "   <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "    \n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    <div class=\"row\">\n" +
    "        <div class=\"col-sm-6 b-r\">\n" +
    "\n" +
    "          <!-- Start hotel view --->\n" +
    "          <div ng-show=\"showDeal.activityId\" ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "              <b>DEAL: <span class=\"deal-{{showDeal.dealStatus}}\">{{showDeal.dealStatus}}</span></b>\n" +
    "\n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showDealBlock.html' \" />\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "          <!-- END hotel view --->\n" +
    "\n" +
    "        </div>\n" +
    "        <div class=\"col-sm-6\" ng-style=\"!isSmartDevice && {'margin-top' : '5px'}\">\n" +
    "            \n" +
    "        <!-- TODO EUGEN: DEAL RULES-->\n" +
    "    \n" +
    "      </div>  \n" +
    "        \n" +
    "   </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.editHotelForm.html",
    "<div class=\"modal-body wrapper-lg\" stop-event=\"touchend\" ng-controller=\"EditHotelContr\" ng-init=\"unlockModal();\">\n" +
    "  \n" +
    "   <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "    \n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    <div class=\"myFlex\" ng-style=\"!isSmartDevice && {'flex-direction':'row', '-webkit-flex-direction': 'row'} || isSmartDevice && {'flex-direction':'column', '-webkit-flex-direction': 'column'}\" ng-controller=\"hotelCtrl\">  \n" +
    "    \n" +
    "      <!--<div class=\"row\">-->\n" +
    "      <div style=\"min-width: 50%;\" >\n" +
    "        \n" +
    "          <!--<div class=\"col-sm-6 b-r\">-->\n" +
    "          <div class=\"b-r\">\n" +
    "              \n" +
    "              <!--<div style=\"display: flex;  display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;justify-content: center; text-align: center;\">-->\n" +
    "                <!--{{:: 'page.checkin.youNeedLogin'| translate}}:-->\n" +
    "              <!--</div>-->\n" +
    "        \n" +
    "        \n" +
    "              <span  ng-if=\"!((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showHotel.id) || hotelState.profileData.admin || hotelState.tempCreationHotel && hotelState.tempCreationHotel.creationTime==showHotel.creationTime)\">You are not a staff for the Hotel {{showHotel.name}}!</span>\n" +
    "        \n" +
    "              <div ng-if=\"((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showHotel.id) || hotelState.profileData.admin || hotelState.tempCreationHotel && hotelState.tempCreationHotel.creationTime==showHotel.creationTime)\">\n" +
    "    \n" +
    "                  <!--START HOTEL Edit-->\n" +
    "        \n" +
    "                  <div class=\"hotelEditElt\"  >\n" +
    "                      \n" +
    "                      <!--<div ng-if=\"editActivityId=='new'\" data-ng-include=\" 'angulr/tpl/hotel/blocks/addActivityAccardion.html' \" />-->\n" +
    "                      \n" +
    "                      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/editHotelAccardion.html' \" />\n" +
    "                      \n" +
    "                  </div>\n" +
    "                  \n" +
    "              </div>\n" +
    "    \n" +
    "              <!-- END HOTEL Edit -->\n" +
    "    \n" +
    "          </div>\n" +
    "          \n" +
    "          \n" +
    "      </div>\n" +
    "          \n" +
    "      <!--<div class=\"b-r\">-->\n" +
    "      <div style=\"min-width: 48%; margin-left: 10px;\">\n" +
    "            \n" +
    "          <!-- Start hotel view --->\n" +
    "          <div ng-show=\"showHotel.id>0 || showHotel.id<0\">\n" +
    "    \n" +
    "             <b ng-show=\"hotelState.profileData.hotelStaff || hotelState.profileData.admin\">{{::'system.editPreview' | translate}}:</b>\n" +
    "\n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />\n" +
    "\n" +
    "          </div>\n" +
    "        \n" +
    "          <!-- END hotel view --->\n" +
    "    \n" +
    "      </div>  \n" +
    "        \n" +
    "   </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.editMenuOrderForm.html",
    "<div class=\"modal-body wrapper-lg loginBody\" stop-event=\"touchend\" ng-controller=\"MenuListController\"\n" +
    "     ng-init=\"initLogin();unlockModal();\">\n" +
    "\n" +
    "    <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "\n" +
    "    <div class=\"row\">\n" +
    "        <div>\n" +
    "\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "                <div class=\"wrapper text-center\" style=\"padding-top: 0px;\">\n" +
    "\n" +
    "                    {{::'page.menu.order' | translate }}:\n" +
    "                    <span ng-show=\"localState.orderLoaded || (!hotelState.menuOrder || !hotelState.menuOrder.menuItems || hotelState.menuOrder.menuItems.length==0)\">{{::'page.menu.noItemsFound' | translate }}</span>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "                <div id=\"hotelPart\" ng-controller=\"hotelCtrl\">\n" +
    "\n" +
    "                    <!--<div ng-show=\"selectedFilterHotel.id>0\">-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <ul style=\"list-style-type: none;padding-left: 0px;\">\n" +
    "                        <li ng-repeat = \"showMenuItem in hotelState.menuOrder.menuItems | orderBy: 'index'\">\n" +
    "\n" +
    "                            <div class=\"item_{{showMenuItem.id}}\" data-ng-include=\" 'angulr/tpl/hotel/blocks/menuItemOrderingBlock.html' \"/>\n" +
    "\n" +
    "                        </li>\n" +
    "                    </ul>\n" +
    "\n" +
    "                    <div style=\"float: right; margin: 5px; margin-bottom: 15px;\">\n" +
    "                        <span style=\"font-weight: bolder;margin-right: 10px;margin-top: 10px;\">Gesamtbetrag: </span>\n" +
    "                        <span class=\"menu-price\">\n" +
    "                         {{ hotelState.menuOrder.totalPrice | currency:\"\":2}} <i class=\"fa fa-eur menu-euro\"></i>\n" +
    "                        </span>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <div class=\"line b-b line-lg\" style=\"margin-left: 15px;margin-right: 15px;\"></div>\n" +
    "\n" +
    "                    <div layout=\"row\" style=\"margin: 15px;\">\n" +
    "    \n" +
    "                        <div>\n" +
    "                            <div class=\"radio\" layout=\"row\">\n" +
    "                                <label class=\"i-checks\">\n" +
    "                                    <input type=\"radio\" name=\"inRoom\" data-ng-value=\"true\" ng-model=\"hotelState.menuOrder.orderInRoom\" checked>\n" +
    "                                    <i></i>\n" +
    "                                    {{::'page.menu.orderInRoom' | translate }}\n" +
    "                                </label>\n" +
    "    \n" +
    "                                <div layout=\"row\" style=\"margin-left: 10px;margin-top: -10px;\" ng-show=\"hotelState.menuOrder.orderInRoom\">\n" +
    "                                    <!--<label>{{::'page.menu.roomNumber' | translate }}<span class=\"requiredField\">*</span></label>-->\n" +
    "                                    <input stop-event=\"touchend\" type=\"text\" max=\"5\" class=\"focusModal form-control\" rows=\"5\" ng-model=\"hotelState.menuOrder.roomNumber\" placeholder=\"{{::'page.menu.roomNumber' | translate }}\" style=\"max-width: 115px;\"></in>\n" +
    "                                    <span class=\"requiredField\">*</span>\n" +
    "                                    <!--<button class=\"btn btn-lg btn-blue btn-addon\" ng-click=\"orderMenu(hotelState.menuOrder, 'room')\"><i class=\"fa fa-building-o \"></i>{{::'page.menu.orderInRoom' | translate }}</button>-->\n" +
    "                                </div>\n" +
    "                                \n" +
    "                            </div>\n" +
    "                            <div class=\"radio\">\n" +
    "                                <label class=\"i-checks\">\n" +
    "                                    <input type=\"radio\" name=\"inRest\" data-ng-value=\"false\" ng-model=\"hotelState.menuOrder.orderInRoom\">\n" +
    "                                    <i></i>\n" +
    "                                    {{::'page.menu.orderInRestaurant' | translate }}\n" +
    "                                </label>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <div class=\"line b-b line-lg\" style=\"margin-left: 15px;margin-right: 15px;\"></div>\n" +
    "\n" +
    "                    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "                    <div  ng-class=\"{'container w-xxl w-auto-xs': isSmartDevice, 'myFlex mySpaceBetween': !isSmartDevice }\" style=\"margin: 10px;\">\n" +
    "                        \n" +
    "                        <div ng-show=\"::isSmartDevice\">\n" +
    "                            <input type=\"text\"  stop-event=\"touchend\"  class=\"focusModal\" ng-model=\"hotelState.menuOrder.customerComment\" class=\"btn-groups\"  placeholder=\"{{::'page.menu.customerComment' | translate }}\">\n" +
    "                        </div>\n" +
    "                        \n" +
    "                        <div ng-show=\"::!isSmartDevice\" style=\"flex-grow: 1;margin: 10px;\">\n" +
    "                            <textarea  stop-event=\"touchend\" ng-model=\"hotelState.menuOrder.customerComment\"  placeholder=\"{{::'page.menu.customerComment' | translate }}\" style=\"width:100%;\" class=\"focusModal\"></textarea>                        \n" +
    "                        </div>\n" +
    "                    \n" +
    "                        <div style=\"margin-top: 10px;\">\n" +
    "                            <button class=\"btn btn-lg btn-blue btn-block btn-addon\" ng-click=\"orderMenuAction(hotelState.menuOrder, 'submitMenu')\"><i class=\"fa fa-cutlery\"></i>{{::'page.menu.submitOrder' | translate }}</button>\n" +
    "                        </div>\n" +
    "                        \n" +
    "                    </div>\n" +
    "                    \n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.hotelCodeForm.html",
    "<div class=\"modal-body wrapper-lg\" stop-event=\"touchend\" ng-controller=\"CheckInController\" ng-init=\"unlockModal();\">\n" +
    "  \n" +
    "  <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "  \n" +
    "  <div class=\"row\">\n" +
    "    <div class=\"col-sm-6 b-r\">\n" +
    "      <!--<div style=\"display: flex;  display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;justify-content: center; text-align: center;\">-->\n" +
    "        <!--{{:: 'page.checkin.youNeedLogin'| translate}}:-->\n" +
    "      <!--</div>-->\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "      <div   >\n" +
    "\n" +
    "          <!--START HOTELCODE-->\n" +
    "\n" +
    "          <div id=\"checkinCode\" ng-class=\"{'unvisible': localState.selectedFilterHotel}\" class=\"myCenter\">\n" +
    "           \n" +
    "              <div style=\"margin-bottom: 25px;width: 90%;\">\n" +
    "                  <input stop-event=\"touchend\" ng-change=\"onHotelCodeInput();\" autocomplete=\"off\" ng-focus=\"hideFocus('none');localState.hotelcodeFocus=true; \" ng-blur=\"hideFocus('flex');localState.hotelcodeFocus=false\" type=\"text\" class=\"focusModal form-control\" ng-model=\"localState.selectedHotelCode\" id=\"hotelCode\" placeholder=\"{{'page.checkin.hotelCode' | translate}}\" style=\"height: 80px;font-size: 2em;display: block;margin: 0 auto;text-align: center;border-radius: 10px;outline: 0;\">\n" +
    "                  <div>\n" +
    "                     {{'page.checkin.askReception' | translate}}\n" +
    "                  </div>\n" +
    "                  <!--<button style=\"margin-top: 15px;\" ng-click=\"onCheckinSubmit()\" ng-disabled=\"localState.disableCheckin\" class=\"btn btn-lg btn-block btn-orange\" >{{localState.selectedFilterHotel.name}} {{'page.checkin.doCheckIn' | translate }}</button>-->\n" +
    "                  \n" +
    "                  <div class=\"myCenter\">\n" +
    "                      <div class=\"text-center bg-light lt b-t\" ng-show=\"(localState.selectedFilterHotel || localState.selectedHotelCode || localState.hotelcodeFocus)\" style=\"width: 100%; margin-top: 10px;\">\n" +
    "                          <button ng-click=\"onHotelCodeSubmit()\" ng-disabled=\"localState.disableCheckin || mainState.disableCheckin\" class=\"btn btn-lg btn-block btn-orange hiddenContent\">{{localState.selectedFilterHotel.name}} {{'page.checkin.doCheckIn' | translate }}</button>\n" +
    "                      </div>\n" +
    "                  </div>\n" +
    "                  \n" +
    "              </div>\n" +
    "    \n" +
    "              <!--<i style=\"margin-top: -4px;-->\n" +
    "              <!--padding-left: 8px;-->\n" +
    "              <!--padding-right: 5px;-->\n" +
    "              <!--position: absolute;\" class=\"fa fa-info-circle navIconItem hotelCitySelectInfo\" ng-click=\"openHotelCodePopup()\"></i>-->\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "         \n" +
    "         \n" +
    "\n" +
    "\n" +
    "          <!-- END HOTELCODE -->\n" +
    "\n" +
    "      </div>\n" +
    "      \n" +
    "      <!-- END 3 CONTENT DIV --->\n" +
    "      \n" +
    "    </div>\n" +
    "      \n" +
    "    <div class=\"b-r\">\n" +
    "        TEXT......\n" +
    "    </div>  \n" +
    "    \n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/modal.loginform.html",
    "<div class=\"modal-body wrapper-lg loginBody\" stop-event=\"touchend\" ng-controller=\"LoginController\"\n" +
    "     ng-init=\"initLogin();unlockModal();\">\n" +
    "  \n" +
    "  <a ng-click=\"cancel()\" style=\"position: absolute; top:0; right:5px;\"><i class=\"fa fa-times\"/></a>\n" +
    "  \n" +
    "  <div class=\"row\">\n" +
    "    <div class=\"col-sm-6 b-r\">\n" +
    "\n" +
    " \n" +
    "      <div ng-if=\"::!isSmartDevice\" class=\"myCenter text-orange\" data-ng-include=\" 'angulr/tpl/hotel/blocks/showCarousel.html' \"/>\n" +
    "\n" +
    "      <div ng-if=\"::isSmartDevice\" class=\"myCenter\" style=\"text-align: center; font-weight: bold; margin-bottom: 10px;\">{{'slide.'+showTopic + 1 | translate}}: </div>\n" +
    "      \n" +
    "      \n" +
    "      <!-- END 3 CONTENT DIV --->\n" +
    "      \n" +
    "    </div>\n" +
    "    <div class=\"col-sm-6\" ng-style=\"!isSmartDevice && {'margin-top' : '5px'}\">\n" +
    "\n" +
    "      <!--<div ng-show=\"::isSmartDevice\" class=\"line b-b line-lg pull-in\" style=\"width: auto;\"></div>-->\n" +
    "      \n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "      \n" +
    "      \n" +
    "      <!--<div class=\"wrapper text-center loginHeaderItems\" style=\"padding-left: 0; padding-right: 0;\">-->\n" +
    "      <!--<a href=\"\" ng-click=\"setLoginTabState('guest')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.guest}\">{{ 'page.login.guestTitle' | translate}}</a>-->\n" +
    "      <!--<span style=\"padding: 5px;\">|</span>-->\n" +
    "      <!--<a href=\"\" ng-click=\"setLoginTabState('login')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.login}\">{{ 'page.login.loginTitle' | translate}}</a>-->\n" +
    "      <!--<span style=\"padding: 5px;\">|</span>-->\n" +
    "      <!--<a href=\"\" ng-click=\"setLoginTabState('signup')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.signup}\">{{ 'page.login.signupTitle' | translate}}</a>-->\n" +
    "      <!--</div>-->\n" +
    "\n" +
    "\n" +
    "\n" +
    "      <div class=\"panel panel-default\" style=\"margin-top: 10px;margin-bottom: 10px;\">\n" +
    "\n" +
    "        <md-content>\n" +
    "          <md-tabs md-stretch-tabs=\"always\" md-selected=\"loginTabIndex\">\n" +
    "\n" +
    "            <md-tab label=\"{{ 'page.login.guestTitle' | translate}}\" id=\"loginTab0\" class=\"\" aria-controls=\"loginTab0-content\" >\n" +
    "              <md-content class=\"md-padding\">\n" +
    "\n" +
    "                <!-- START GUEST LOGIN (1) -->\n" +
    "                <!--ng-show=\"loginTabState.guest\"-->\n" +
    "                <div  class=\"\" style=\"margin-top: 6px;\">\n" +
    "\n" +
    "                  <form role=\"form\" name=\"loginGuestForm\" id=\"loginGuestForm\" class=\"form-validation\" ng-submit=\"guestRegister();\">\n" +
    "\n" +
    "                    <div class=\"list-group list-group-sm\">\n" +
    "                      <div class=\"list-group-item text-center\" style=\"height: 52px;padding: 0;margin: 0\">\n" +
    "                        <div style=\"width: 250px;display: table;margin: 0 auto;padding-top: 7px;\">\n" +
    "                          <div style=\"width: 100px; float: left;\">\n" +
    "                            <input stop-event=\"touchend\" type=\"text\" placeholder=\"{{' profile.firstName' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.guestUser.firstName\" ng-focus=\"scrollModalDown();\" required>\n" +
    "                          </div>\n" +
    "                          <div style=\"float: left; margin-top: 7px; margin-left: 10px;\">\n" +
    "                            <label class=\"i-checks\">\n" +
    "                              <!--ng-required=\"!localState.guestUser.sex\"-->\n" +
    "                              <input type=\"radio\" value=\"m\" ng-model=\"localState.guestUser.sex\" ><i></i> {{ 'profile.gender-m' | translate}} &nbsp;\n" +
    "                            </label>\n" +
    "                            <label class=\"i-checks\">\n" +
    "                              <input type=\"radio\" value=\"w\" ng-model=\"localState.guestUser.sex\" ><i></i> {{ 'profile.gender-f' | translate}}\n" +
    "                            </label>\n" +
    "                          </div>\n" +
    "                        </div>\n" +
    "\n" +
    "                      </div>\n" +
    "\n" +
    "                      <button type=\"submit\" class=\"btn btn-sm btn-orange btn-social btn-block\" style=\"text-align: center; padding:6px; padding-left: 0; font-size: 14px;\">{{' page.login.guestRegister'\n" +
    "                        | translate }}</button>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <!--<hr>-->\n" +
    "\n" +
    "                    <!--<div style=\"text-align: justify;\" class=\"text-center\">-->\n" +
    "                    <!--{{' page.login.guestMsg' | translate }}-->\n" +
    "                    <!--<br/>-->\n" +
    "                    <!--{{' page.login.guestMsg2' | translate }}-->\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                  </form>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "                <!-- END GUEST LOGIN -->\n" +
    "\n" +
    "              </md-content>\n" +
    "            </md-tab>\n" +
    "\n" +
    "            <md-tab label=\"{{ 'page.login.loginTitle' | translate}}\" id=\"loginTab1\" aria-controls=\"loginTab1-content\" >\n" +
    "              <md-content class=\"md-padding\">\n" +
    "\n" +
    "                <!-- START LOGIN (2) -->\n" +
    "                <!--ng-show=\"loginTabState.login\"-->\n" +
    "                <div class=\"\" >\n" +
    "                  <!-- TODO Eugen: create Form hier!!! autofill-->\n" +
    "                  <form role=\"form\" name=\"hotelicoLoginForm\"  autocomplete=\"on\" class=\"form-validation\" ng-submit=\"loginPassword()\">\n" +
    "\n" +
    "                    <div class=\"list-group list-group-sm\" >\n" +
    "                      <div class=\"list-group-item\" style=\"padding-top: 2px; padding-bottom: 2px;\">\n" +
    "                        <input stop-event=\"touchend\" type=\"email\" id=\"hotelicoLoginMail\" name=\"hotelicoLoginMail\" placeholder=\"{{' profile.email' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.checkUser.email\" ng-focus=\"scrollModalDown();\" required autocomplete=\"on\">\n" +
    "                      </div>\n" +
    "                      <div class=\"list-group-item\" style=\"padding-top: 2px; padding-bottom: 2px;\">\n" +
    "                        <input stop-event=\"touchend\" type=\"password\" id=\"hotelicoLoginPassword\" name=\"hotelicoLoginPassword\" placeholder=\"{{' profile.password' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.checkUser.password\" ng-focus=\"scrollModalDown();\" required autocomplete=\"on\">\n" +
    "                      </div>\n" +
    "                      <button type=\"submit\" class=\"btn btn-sm btn-orange btn-block\" style=\"padding: 6px; font-size: 14px;\">{{' page.login.doLogin'\n" +
    "                        | translate }}</button>\n" +
    "                    </div>\n" +
    "                  </form>\n" +
    "\n" +
    "                  <div class=\"text-center m-t m-b\"><a ui-sref=\"app.forgotpwd\">Forgot password?</a></div>\n" +
    "\n" +
    "                </div>\n" +
    "                <!-- END LOGIN DIV -->\n" +
    "\n" +
    "              </md-content>\n" +
    "            </md-tab>\n" +
    "              \n" +
    "            <md-tab label=\"{{ 'page.login.signupTitle' | translate}}\" id=\"loginTab2\" aria-controls=\"loginTab2-content\" >\n" +
    "              <md-content class=\"md-padding\">\n" +
    "\n" +
    "                <!-- START REGISTER (3) -->\n" +
    "                <!--ng-show=\"loginTabState.signup\"-->\n" +
    "                <div class=\"panel-body\"  >\n" +
    "\n" +
    "                  <a ng-click=\"ok('app.register')\" class=\"btn btn-sm btn-orange btn-block\" style=\"padding: 6px; font-size: 14px;\">{{' page.login.goToSignUp' | translate}}</a>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "                <!-- END REGISTER -->\n" +
    "\n" +
    "              </md-content>\n" +
    "            </md-tab>\n" +
    "          </md-tabs>\n" +
    "        </md-content>\n" +
    "\n" +
    "      </div>\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/socialLogin.html' \" />\n" +
    "\n" +
    "\n" +
    "      <!--&lt;!&ndash;<h4>Not a member?</h4>&ndash;&gt;-->\n" +
    "      <!--&lt;!&ndash;<p>You can create an account <a href class=\"text-info\">here</a></p>&ndash;&gt;-->\n" +
    "      <!-- -->\n" +
    "      <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/socialLogin.html' \" />-->\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/notLoggedInfoBlock.html",
    "<!--//TODO Problems: visualization Problems-->\n" +
    "<!--//TODO: if no hotelCode: chat flag, wall hide names -->\n" +
    "\n" +
    "<!--### START HotelCode -->\n" +
    "<div layout=\"column\" class=\"myBorder\" style=\"margin-bottom: 10px; border-radius: 5px; background-color: #fff;\" ng-if=\"!hotelState.profileData.logged || isChromeDevice && !hotelService.chromePushRegistrationId && hotelService.noPushIdRegistered\">\n" +
    "\n" +
    "    <div ng-if=\"!hotelState.profileData.logged\" class=\"myFlex mySpaceBetween\">\n" +
    "        \n" +
    "        <span style=\"margin-right: 10px;margin-top: 3px;font-weight: bolder; \"><i class=\"fa fa-exclamation-triangle\"></i> Not logged: you have a limited view and nobody can see you in a hotel:</span>\n" +
    "\n" +
    "        <div style=\"display: table;\" ng-style=\"::isSmartDevice && {'margin-top': '5px'}\">\n" +
    "            <button ng-click=\"openModal('login')\"  class=\"btn btn-orange\" style=\"padding: 2px;height: 25px;vertical-align:middle;display: table-cell;\">Login</button>\n" +
    "        </div>\n" +
    "\n" +
    "    </div>\n" +
    "  \n" +
    "\n" +
    "    <div ng-if=\"isChromeDevice && !hotelService.chromePushRegistrationId && !hotelState.profileData.hideChromePushPopup && hotelService.noPushIdRegistered\" class=\"myFlex mySpaceBetween\" style=\"margin-top: 1px;\">\n" +
    "\n" +
    "        <span style=\"margin-right: 10px;margin-top: 3px;font-weight: bolder; \"><i class=\"fa fa-exclamation-triangle\"></i> You don't get Chrome Notifications:</span> \n" +
    "\n" +
    "        <button ng-click=\"mainPushRequest()\"  class=\"btn btn-orange\" style=\"padding: 2px;height: 25px;\">Activate</button>\n" +
    "\n" +
    "    </div>\n" +
    "    \n" +
    "</div>\n" +
    "<!--### END HotelCode-->");
  $templateCache.put("angulr/tpl/hotel/blocks/pushRequest.html",
    "<!--&lt;!&ndash;//TODO Problems: visualization Problems&ndash;&gt;-->\n" +
    "<!--&lt;!&ndash;//TODO: if no hotelCode: chat flag, wall hide names &ndash;&gt;-->\n" +
    "\n" +
    "<!--&lt;!&ndash;### START HotelCode &ndash;&gt;-->\n" +
    "<!--<div class=\"myCenter\" style=\"border-width: 1px;-->\n" +
    "    <!--border-style: solid;-->\n" +
    "    <!--border-radius: 5px;-->\n" +
    "    <!--padding: 5px;-->\n" +
    "    <!--margin-bottom: 10px;-->\n" +
    "    <!--border-color: lightgray;\" ng-if=\"isChromeDevice && !hotelService.chromePushRegistrationId && !$scope.hotelState.profileData.hideChromePushPopup\">-->\n" +
    "\n" +
    "    <!--<span style=\"margin-right: 10px;margin-top: 3px;font-weight: bolder; \"><i class=\"fa fa-exclamation-triangle\"></i> Do you want a push notifications?</span>-->\n" +
    "\n" +
    "    <!--<button ng-click=\"mainPushRequest()\"  class=\"btn btn-orange\" style=\"padding: 2px;height: 25px;\">Activate Push</button>-->\n" +
    "    <!-- -->\n" +
    "    <!--&lt;!&ndash;<button ng-click=\"mainPushRequest('noMore')\"  class=\"btn btn-orange\" style=\"padding: 2px;height: 25px;\">Request Push</button>&ndash;&gt;-->\n" +
    "\n" +
    "    <!--&lt;!&ndash;<i style=\"margin-top: -4px; padding-left: 8px; padding-right: 5px; /*position: absolute;*/\" class=\"fa fa-info-circle navIconItem hotelCitySelectInfo\" ng-click=\"openHotelCodePopup()\"></i>&ndash;&gt;-->\n" +
    "\n" +
    "<!--</div>-->\n" +
    "<!--&lt;!&ndash;### END HotelCode&ndash;&gt;-->");
  $templateCache.put("angulr/tpl/hotel/blocks/selectHotelCityBlock.html",
    "<div ng-controller=\"filterHotelCityCtrl\">\n" +
    "    <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showCityLoading\">\n" +
    "    \n" +
    "    <div ng-hide=\"localState.showCityLoading\" class=\"myCenter\" >\n" +
    "        <div class=\"hotelCityBlockWrapper\">\n" +
    "\n" +
    "            <div class=\"form-group\" style=\"margin-bottom: 0px;\">\n" +
    "                <!--<label class=\"col-sm-3 control-label text-left\">Default</label>-->\n" +
    "                <div>\n" +
    "                    <select ng-change=\"onChangeHotelCitySelector();\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.selectedCityHotel\" id=\"hotelCity\" ng-options=\"hotel as hotel.city for hotel in mainState.hotelCitiesArray | unique:'city'\" placeholder=\" {{::'page.checkin.hotelCityManualSelect' | translate}}\">\n" +
    "                    <option value=\"\" disabled> {{::'alert.info.chooseHotelCity' | translate}}</option>\n" +
    "                    </select>\n" +
    "                    <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showCityHotelLoading\">\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <!--<span ng-show=\"localState.selectedCityHotel\">Hotels in {{localState.selectedCityHotel.city}}:</span>-->\n" +
    "            \n" +
    "            <!--<select ng-change=\"onChangeFilterHotelSelector()\" ng-show=\"localState.selectedCityHotel && !localState.showCityHotelLoading\" style=\"margin-top: 5px;\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.selectedFilterHotel\" id=\"hotelListByCitySelector\" ng-options=\"hotel as hotel.name for hotel in localState.hotelsFilteredByCityArray\" placeholder=\" {{'page.checkin.hotelManualSelect' | translate}}\">-->\n" +
    "                <!--<option value=\"\" disabled><span ng-show=\"localState.selectedCityHotel.city\">Hotels in {{localState.selectedCityHotel.city}}</span></option>-->\n" +
    "                <!--&lt;!&ndash;<option value=\"\"> {{::'page.checkin.noHotelChoosed' | translate}}</option>&ndash;&gt;-->\n" +
    "            <!--</select>-->\n" +
    "\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <!--<label class=\"col-sm-3 control-label text-left\">With a clear button</label>-->\n" +
    "                <div>\n" +
    "                    <div class=\"input-group\" style=\"display: flex;\">\n" +
    "\n" +
    "                        <select ng-change=\"onChangeFilterHotelSelector()\" ng-show=\"localState.selectedCityHotel && !localState.showCityHotelLoading\" style=\"margin-top: 5px;\" ng-style=\"localState.hotelSelectCheckinButtonShow && {'display':'block'}\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.selectedFilterHotel\" id=\"hotelListByCitySelector\" ng-options=\"hotel as hotel.name for hotel in localState.hotelsFilteredByCityArray\" placeholder=\" {{'page.checkin.hotelManualSelect' | translate}}\">\n" +
    "                            <option value=\"\" disabled><span ng-show=\"localState.selectedCityHotel.city\">Hotels in {{localState.selectedCityHotel.city}}</span></option>\n" +
    "                            <!--<option value=\"\"> {{::'page.checkin.noHotelChoosed' | translate}}</option>-->\n" +
    "                        </select>\n" +
    "\n" +
    "                        <span class=\"input-group-btn\" ng-show=\"localState.hotelSelectCheckinButtonShow && localState.selectedFilterHotel && !localState.showCityHotelLoading\" style=\"width: auto;\">\n" +
    "                          <button ng-click=\"onHotelCheckinClick(localState.selectedFilterHotel? localState.selectedFilterHotel.id : null)\" class=\"btn btn-orange\" style=\"margin-top: 5px;margin-left: 0px;\">\n" +
    "                              <span class=\"fa fa-chevron-right\"></span>\n" +
    "                          </button>\n" +
    "                        </span>\n" +
    "                        \n" +
    "\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            \n" +
    "            \n" +
    "        </div>\n" +
    "        <i style=\"position: absolute;\" class=\"fa fa-info-circle navIconItem hotelCitySelectInfo\" ng-click=\"openCityPopup()\"></i>\n" +
    "    </div>\n" +
    "    \n" +
    "   \n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/blocks/showActivityBlock.html",
    "<div class=\"panel b-a panel-default image-box-shadow activityBlock\" ng-show=\"::(showActivity.timeValid && showActivity.active && !showActivity.hidden || hotelState.profileData.hotelStaff || hotelState.profileData.admin)\">\n" +
    "\n" +
    "    <div style=\"margin-top: -1px;\">\n" +
    "\n" +
    "        <div ng-if=\"showActivity.lastMinute && showActivity.pictureUrl\" class=\"bottom text-white\" style=\"padding: 5px;text-align: left; margin-bottom: -25px;height: 25px;z-index: 9;position: relative;\" >\n" +
    "            <span class=\"label bg-danger lastMinuteDeal\">Last Minute</span>\n" +
    "        </div>\n" +
    "        <div ng-if=\"localState.showActivityHotelLink && showActivity.pictureUrl\" class=\"bottom text-white\" style=\"padding: 5px;text-align: right; margin-bottom: -25px;height: 25px;z-index: 9;position: relative;\" >\n" +
    "            <!--ng-show=\"!localState.selectedFilterHotel\"-->\n" +
    "            <div class=\"hotelInfoOpacity\" ng-show=\"showActivity.pictureUrl\">\n" +
    "                <a class=\"text-muted clear text-ellipsis\" style=\"color: white;\" ui-sref=\"app.hotelList({filterCity: showActivity.hotelCity, filterHotelId: showActivity.hotelId})\">\n" +
    "                    <span style=\"text-decoration: underline; /*font-weight: bold;*/\"> <!-- ng-show=\"!localState.selectedFilterHotel.id\" -->\n" +
    "                        {{::showActivity.hotelName}}\n" +
    "                    </span> \n" +
    "                    <span ng-show=\"!localState.selectedCityHotel.id\">, {{::showActivity.hotelCity}}</span>\n" +
    "                </a>\n" +
    "                <div class=\"myFlex\" style=\"justify-content: flex-end; -webkit-justify-content: flex-end; -moz-justify-content: flex-end; -ms-justify-content: flex-end; font-size: 13px; color: #fff;\">\n" +
    "                    <div class=\"customersLinkInHotel\" ng-show=\"showActivity.hotelCustomerNumber>0\"> \n" +
    "                        <a class=\"clear text-ellipsis\" ng-click=\"clickLoading('app.chatList', null, null, {filterCity: showActivity.hotelCity, filterHotelId: showActivity.hotelId})\">  \n" +
    "                            <span style=\"margin-left: 5px; text-decoration: underline;\">\n" +
    "                                {{showActivity.hotelCustomerNumber}} guest<span ng-show=\"showActivity.hotelCustomerNumber>1\">s</span>\n" +
    "                            </span> \n" +
    "                            in Hotel \n" +
    "                        </a>\n" +
    "                    </div>\n" +
    "                    <div class=\"activitiesLinkInHotel\" ng-show=\"localState.initFilterActivityId && showActivity.otherActivityNumber>0\"> \n" +
    "                        <a class=\"clear text-ellipsis\" ng-click=\"clickLoading('app.activityList', null, null, {filterCity: showActivity.hotelCity, filterHotelId: showActivity.hotelId})\">  \n" +
    "                            <span style=\"margin-left: 5px; text-decoration: underline;\">{{showActivity.otherActivityNumber}} other deals</span> \n" +
    "                            in Hotel \n" +
    "                        </a>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "               \n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"item m-l-n-xxs m-r-n-xxs\">\n" +
    "            <a >\n" +
    "                <img ng-src=\"{{showActivity.pictureUrl}}\" ng-show=\"showActivity.pictureUrl\" class=\"img-full img-activity\">\n" +
    "            </a>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div layout=\"row\" ng-if=\"(hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showActivity.hotelId || hotelState.profileData.admin)\">\n" +
    "        <button style=\"margin-top: 0px;\" class=\"btn btn-block btn-sm btn-info btn-addon\" ng-click=\"editActivityId? submitActivityChange(showActivity) : openModal('editActivity', showActivity.initId)\"><i class=\"fa fa-edit\"></i>{{::'system.edit' | translate}}</button>\n" +
    "        <button style=\"margin-top: 0px;\" class=\"btn btn-block btn-sm btn-danger btn-addon\" ng-click=\"removeActivity(showActivity.initId)\"><i class=\"fa fa-trash-o\"></i> {{::'system.remove' | translate}}</button>\n" +
    "    </div>\n" +
    "    \n" +
    "    <a class=\"text-lt\" style=\"font-weight: bold; margin-left: 10px;\" ng-show=\"!showActivity.timeValid\">\n" +
    "        <span style=\"color:red;\">(not active now: {{showActivity.validFromString}} - {{showActivity.validToString}})</span>\n" +
    "    </a>\n" +
    "    \n" +
    "    <div layout=\"row\" style=\"overflow: hidden;justify-content: space-between; -webkit-justify-content: space-between; -moz-justify-content: space-between;-ms-justify-content: space-between;  margin-top: 8px; margin-right: 3px; margin-left: 10px; font-size: 22px;\">\n" +
    "\n" +
    "        <div ng-style=\"::isSmartDevice? {'width': activityDescrWidth} : {'width':'395px'}\">\n" +
    "            <span class=\"text-lt\" style=\"font-weight: bold;\" ng-click=\"onActivityClick(showActivity.initId)\">{{showActivity.title}}</span>\n" +
    "            <small class=\"text-muted clear text-ellipsis myEllipsis\"><span>{{showActivity.shortDescription}}</span></small>\n" +
    "        </div>\n" +
    "\n" +
    "        <div layout=\"column\" style=\"text-align: right;flex-shrink: 0;flex-basis: 100px;\" ng-show=\"showActivity.dealAllowed\">\n" +
    "\n" +
    "            <div>\n" +
    "                <button style=\"width: 100px; margin-top: 0px;\" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showActivity, 'newDeal')\" ng-hide=\"showActivity.validDealId\">{{::'page.activities.subscribe' | translate}}</button></a>\n" +
    "                <button style=\"width: 100px; margin-top: 0px;\" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showActivity, 'editDeal')\" ng-show=\"showActivity.validDealId\">{{::'page.activities.viewCode' | translate}}</button></a>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div>\n" +
    "                <button style=\"width: 100px; margin-top: 0px; \" class=\"btn btn-vsm btn-info\" ng-click=\"submitActivityAction(showActivity, 'invite')\" ng-disabled=\"true\">{{::'page.activities.invite' | translate}}</button></a>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "        \n" +
    "    </div>\n" +
    "\n" +
    "    <!--<div class=\"customersLinkInHotel\" ng-show=\"(showActivity.likeNumber>0)\">-->\n" +
    "        <!--<a class=\"text-muted clear text-ellipsis\"> {{showActivity.likeNumber}} interested </a>-->\n" +
    "    <!--</div>-->\n" +
    "\n" +
    "   \n" +
    "        <!--<a ui-sref=\"app.editActivity({activityId:showActivity.activityId})\" class=\"text-lt\" style=\"font-weight: bold;\">-->\n" +
    "        <!--&lt;!&ndash;<button class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\">Open</button>&ndash;&gt;-->\n" +
    "        <!--</a>-->\n" +
    "    <!--</div>-->\n" +
    "    \n" +
    "    <div class=\"hotelDescrElt\">\n" +
    "\n" +
    "        <div style=\"text-align: right;margin-right: 1px;margin-bottom: -47px;margin-top: 16px; padding: 3px;\">\n" +
    "\n" +
    "            <a facebook-feed-share class=\"facebookShare\"\n" +
    "               data-url='{{HOST}}{{HOST_SUFFIX}}#/app/activities/{{showActivity.id}}'\n" +
    "               data-shares='shares',\n" +
    "               data-description=\"Example text\"\n" +
    "               data-picture=\"{{HOST}}{{showActivity.pictureUrl}}\"\n" +
    "               data-name=\"{{showActivity.name}}\"\n" +
    "               data-caption=\"caption\"\n" +
    "               data-description=\"{{showActivity.shortDescription}}\"\n" +
    "                    >\n" +
    "                {{ shares }}\n" +
    "            </a>\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "        <accordion close-others=\"oneAtATime\" ng-show=\"showActivity.description\">\n" +
    "\n" +
    "            <accordion-group is-open=\"accordionStatus.openActivities[showActivity.initId]\" is-disabled=\"false\">\n" +
    "\n" +
    "                <accordion-heading>\n" +
    "                    <!--<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen, 'fa-angle-right': !accordionStatus.infoHotelOpen}\"></i>-->\n" +
    "                    <div class=\"text-center\">\n" +
    "                        <!--<i class=\"glyphicon glyphicon-align-justify\"></i> Offer Description-->\n" +
    "                        <button style=\"margin: 0;\" class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\" ng-click=\"openActivity(showActivity.initId)\"><span ng-show=\"localState.openActivities[showActivity.initId]\">Hide</span><span ng-hide=\"localState.openActivities[showActivity.initId]\">Open</span></button>\n" +
    "                    </div>\n" +
    "                   \n" +
    "                </accordion-heading>\n" +
    "\n" +
    "                <div ng-bind-html=\"showActivity.description\" style=\"word-wrap: break-word;\"></div>\n" +
    "\n" +
    "                <hr>\n" +
    "               \n" +
    "\n" +
    "            </accordion-group>\n" +
    "        </accordion>\n" +
    "\n" +
    "       \n" +
    "        \n" +
    "        \n" +
    "    </div>\n" +
    "\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/blocks/showAlertBlock.html",
    "<div class=\"m-b-md\" ng-show=\"mainState.successMsg || mainState.errorMsg || rootErrorMsg\">\n" +
    "    <alert ng-show=\"mainState.successMsg\" type=\"success\" close=\"closeMyAlert('successMsg')\">{{mainState.successMsg}}</alert>\n" +
    "    <alert ng-show=\"mainState.errorMsg\" type=\"warning\" close=\"closeMyAlert('errorMsg')\">{{mainState.errorMsg}}</alert>\n" +
    "    <alert ng-show=\"rootErrorMsg\" type=\"warning\" close=\"closeMyAlert('rootErrorMsg')\">{{rootErrorMsg}}</alert>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/showCarousel.html",
    "<div data-ng-controller=\"SlideCtrl\" style=\"width: 100%;\" ngCloak>\n" +
    "    <div class=\"carousel-demo\" style=\"width: 100%;\">\n" +
    "        <!--index: <input type=\"number\" class=\"tiny\" ng-model=\"carouselIndex2\">-->\n" +
    "        <ul rn-carousel rn-carousel-index=\"carouselIndex2\" rn-carousel-auto-slide=\"100\" rn-carousel-pause-on-hover  class=\"carousel2 \">\n" +
    "            <!--rn-carousel-buffered-->\n" +
    "            <li ng-repeat=\"slide in slides2 track by slide.id\" ng-class=\"'id-' + slide.id\">\n" +
    "                <div class=\"carousel-top-label\" style=\"visibility: hidden;\">\n" +
    "                    <span style=\"white-space: pre-line;\">{{ slide.label }}</span>\n" +
    "                </div>\n" +
    "                <div ng-style=\"{'background-image': 'url(' + slide.img + ')', 'color' : slide.color }\" class=\"bgimage\">\n" +
    "\n" +
    "                </div>\n" +
    "            </li>\n" +
    "        </ul>\n" +
    "        <div rn-carousel-indicators ng-if=\"slides2.length > 1\" slides=\"slides2\" rn-carousel-index=\"carouselIndex2\">\n" +
    "\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/showDealBlock.html",
    "<div class=\"panel b-a panel-default image-box-shadow activityBlock\" ng-if=\"showDeal.active && showDeal.timeValid\">\n" +
    "\n" +
    "    <div style=\"margin-top: -1px;\"  ng-show=\"!localState.staffView\">\n" +
    "\n" +
    "        <div ng-if=\"showDeal.lastMinute && showDeal.pictureUrl\" class=\"bottom text-white\" style=\"padding: 5px;text-align: left; margin-bottom: -25px;height: 25px;z-index: 9;position: relative;\" >\n" +
    "            <span class=\"label bg-danger lastMinuteDeal\">Last Minute</span>\n" +
    "        </div>\n" +
    "        <div ng-if=\"localState.showDealHotelLink  && showDeal.pictureUrl\" class=\"bottom text-white\" style=\"padding: 5px;text-align: right;margin-bottom: -25px;height: 25px;z-index: 9;position: relative;\" >\n" +
    "            <!--ng-show=\"!localState.selectedFilterHotel\"-->\n" +
    "            <div class=\"hotelInfoOpacity\" ng-show=\"showDeal.pictureUrl\">\n" +
    "                <a class=\"text-muted clear text-ellipsis\" style=\"color: white;\" ui-sref=\"app.hotelList({filterCity: showDeal.hotelCity, filterHotelId: showDeal.hotelId})\">\n" +
    "                    <span style=\"text-decoration: underline; /*font-weight: bold;*/\"> <!-- ng-show=\"!localState.selectedFilterHotel.id\" -->\n" +
    "                        {{::showDeal.hotelName}}\n" +
    "                    </span> \n" +
    "                    <span ng-show=\"!localState.selectedCityHotel.id\">, {{::showDeal.hotelCity}}</span>\n" +
    "                </a>\n" +
    "                <div style=\"display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;justify-content: flex-end; font-size: 13px; color: #fff;\">\n" +
    "                    <div class=\"customersLinkInHotel\" ng-show=\"showDeal.hotelCustomerNumber>0\"> \n" +
    "                        <a class=\"clear text-ellipsis\" ng-click=\"clickLoading('app.chatList', null, null, {filterCity: showDeal.hotelCity, filterHotelId: showDeal.hotelId})\">  \n" +
    "                            <span style=\"margin-left: 5px; text-decoration: underline;\">\n" +
    "                                {{showDeal.hotelCustomerNumber}} guest<span ng-show=\"showDeal.hotelCustomerNumber>1\">s</span>\n" +
    "                            </span> \n" +
    "                            in Hotel \n" +
    "                        </a>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "               \n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"item m-l-n-xxs m-r-n-xxs\">\n" +
    "            <a >\n" +
    "                <img ng-src=\"{{showDeal.pictureUrl}}\" ng-show=\"showDeal.pictureUrl\" class=\"img-full img-activity\">\n" +
    "            </a>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "    \n" +
    "    <!--<a class=\"text-lt\" style=\"font-weight: bold; margin-left: 10px;\" ng-show=\"!showDeal.timeValid && showDeal.dealStatus!='rejected'\">-->\n" +
    "        <!--<span style=\"color:red;\">(not active now: {{showDeal.validFromString}} - {{showDeal.validToString}})</span>-->\n" +
    "    <!--</a>-->\n" +
    "    \n" +
    "    <!--<a class=\"text-lt\" style=\"font-weight: bold; margin-left: 10px;\" ng-show=\"showDeal.lastMinute\">-->\n" +
    "        <!--<span class=\"countdown_deal\" id=\"countdown_deal_{{showDeal.activityId}}_{{showDeal.id}}\"></span>-->\n" +
    "    <!--</a>-->\n" +
    "    \n" +
    "    <div layout=\"row\" style=\"overflow: hidden; justify-content: space-between; -webkit-justify-content: space-between; -moz-justify-content: space-between;-ms-justify-content: space-between;  margin-top: 8px; margin-right: 3px; margin-left: 10px; font-size: 22px;\">\n" +
    "\n" +
    "        <div ng-style=\"::isSmartDevice? {'width': activityDealDescrWidth} : {'width':'395px'}\">\n" +
    "            <span class=\"text-lt\" style=\"font-weight: bold;\" ng-click=\"onDealClick(showDeal.id)\">{{showDeal.title}}</span>\n" +
    "            <small class=\"text-muted clear text-ellipsis myEllipsis\"><span>{{showDeal.shortDescription}}</span></small>\n" +
    "            <span style=\"color: red; font-size: 14px;\" ng-if=\"hotelState.profileData.hotelId>0 && hotelState.profileData.hotelId!=showDeal.hotelId\">Not in your hotel!</span>\n" +
    "            <span class=\"countdown_deal\" ng-if=\"showDeal.dealStatus=='accepted'\" id=\"countdown_deal_{{showDeal.activityId}}_{{showDeal.id}}\"></span>\n" +
    "        </div>\n" +
    "\n" +
    "        <div layout=\"column\" style=\"text-align: right;flex-shrink: 0;flex-basis: 85px;\" ng-if=\"showDeal.dealStatus=='accepted'\">\n" +
    "\n" +
    "            <div>\n" +
    "                <button style=\"width: 85px; margin-top: 0px;\" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showDeal, 'execute')\" ng-show=\"localState.staffView\">Fullfill</button></a>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div>\n" +
    "                <button style=\"width: 85px; margin-top: 0px;\" class=\"btn btn-vsm btn-danger\" ng-click=\"submitDealAction(showDeal, 'rejectDeal')\" ng-show=\"showDeal.dealStatus =='accepted'\">Reject</button></a>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div ng-show=\"!localState.staffView\">\n" +
    "                <button style=\"width: 85px; margin-top: 0px; \" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showDeal, 'invite')\" ng-disabled=\"true\">{{::'page.activities.invite' | translate}}</button></a>\n" +
    "            </div>           \n" +
    "            \n" +
    "        </div>\n" +
    "        \n" +
    "    </div>\n" +
    "    \n" +
    "    <div class=\"hotelDescrElt\" ng-show=\"!localState.staffView\">\n" +
    "\n" +
    "        <!--<div ng-show=\"showDeal.dealStatus\" style=\"color: green; font-weight: bold; text-align: left;margin-left: 1px;margin-bottom: -47px;margin-top: 16px; padding: 3px;\">-->\n" +
    "            <!--{{showDeal.dealStatus}}-->\n" +
    "        <!--</div>-->\n" +
    "        <div style=\"text-align: right;margin-right: 1px;margin-bottom: -47px;margin-top: 16px; padding: 3px;\">\n" +
    "\n" +
    "            <a facebook-feed-share class=\"facebookShare\"\n" +
    "               data-url='{{HOST}}{{HOST_SUFFIX}}#/app/activityList//{{showDeal.hotelId}}/{{showDeal.id}}'\n" +
    "               data-shares='shares',\n" +
    "               data-description=\"{{showDeal.shortDescription}}\"\n" +
    "               data-picture=\"{{HOST}}{{showDeal.pictureUrl}}\"\n" +
    "               data-name=\"{{showDeal.name}}\"\n" +
    "               data-caption=\"caption\"\n" +
    "            >\n" +
    "                {{ shares }}\n" +
    "            </a>\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "        <accordion close-others=\"oneAtATime\" ng-show=\"showDeal.description\">\n" +
    "\n" +
    "            <accordion-group is-open=\"accordionStatus.openActivities[showDeal.id]\" is-disabled=\"false\">\n" +
    "\n" +
    "                <accordion-heading>\n" +
    "                    <!--<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen, 'fa-angle-right': !accordionStatus.infoHotelOpen}\"></i>-->\n" +
    "                    <div class=\"text-center\">\n" +
    "                        <!--<i class=\"glyphicon glyphicon-align-justify\"></i> Offer Description-->\n" +
    "                        <button style=\"margin: 0;\" class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\" ng-click=\"openDeal(showDeal.initId)\"><span ng-show=\"localState.openActivities[showDeal.initId]\">Hide</span><span ng-hide=\"localState.openActivities[showDeal.initId]\">Open</span></button>\n" +
    "                    </div>\n" +
    "                   \n" +
    "                </accordion-heading>\n" +
    "\n" +
    "                <div ng-bind-html=\"showDeal.description\" style=\"word-wrap: break-word;\"></div>\n" +
    "\n" +
    "                <hr>\n" +
    "               \n" +
    "\n" +
    "            </accordion-group>\n" +
    "        </accordion>\n" +
    "\n" +
    "       \n" +
    "        \n" +
    "        \n" +
    "    </div>\n" +
    "    \n" +
    "    <div style=\"text-align: center;\">\n" +
    "        <h2 style=\"margin: 0;\">\n" +
    "            \n" +
    "            <span ng-show=\"localState.staffView || showDeal.dealStatus == 'fulfilled'\" class=\"deal-{{showDeal.dealStatus}}\">{{showDeal.dealStatus}}. </span>\n" +
    "            <div ng-show=\"showDeal.dealStatus != 'fulfilled'\" style=\"display: inline;\">\n" +
    "                <span ng-hide=\"localState.staffView\">You personal activation</span> code:\n" +
    "                <span>{{showDeal.dealCode}}</span>\n" +
    "            </div>\n" +
    "           \n" +
    "        </h2>\n" +
    "    </div>\n" +
    "    \n" +
    "    <div ng-show=\"showDeal.dealStatus != 'accepted' && showDeal.dealStatus != 'fulfilled'\">\n" +
    "        <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block btn-addon\" ng-click=\"submitDealAction(showDeal, 'acceptDeal')\"><i class=\"fa fa-plus-square-o\"></i>ACCEPT DEAL</button>\n" +
    "    </div>\n" +
    "\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/blocks/showDealPreviewBlock.html",
    "<!-- START HOTEL DESCR --->\n" +
    "<div class=\"panel b-a panel-default image-box-shadow\">\n" +
    "    \n" +
    "    <div layout=\"row\" style=\"font-size: 25px;\">\n" +
    "        \n" +
    "        <div ng-show=\"showDeal.previewPictureUrl\">\n" +
    "            <!--<div ng-if=\"showDeal.rating>0\" class=\"bottom text-white\" style=\"padding: 5px;text-align: right;margin-bottom: -10px;height: 10px;z-index: 9;position: relative;top: -2px;left: -7px; font-size: 12px;\" >-->\n" +
    "                <!--&lt;!&ndash;ng-show=\"!localState.selectedFilterHotel\"&ndash;&gt;-->\n" +
    "                <!--<div class=\"hotelInfoOpacity\">-->\n" +
    "                    <!--<div class=\"top text-right padder w-full\" style=\"padding-left: 5px; padding-right: 5px;\">-->\n" +
    "                        <!--<rating ng-model=\"showDeal.rating\" readonly=\"true\" max=\"5\" state-on=\"'fa fa-star text-orange'\" state-off=\"'fa fa-star-o text-orange'\"></rating>-->\n" +
    "                    <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "            <img ng-src=\"{{showDeal.previewPictureUrl}}\" style=\"width: 100px;\">\n" +
    "        </div>\n" +
    "\n" +
    "        <div layout=\"column\">\n" +
    "            \n" +
    "            <div layout=\"row\" style=\"justify-content: space-between; -webkit-justify-content: space-between; -moz-justify-content: space-between;-ms-justify-content: space-between;  margin-top: 8px; margin-right: 3px; margin-left: 10px; font-size: 22px;\">\n" +
    "\n" +
    "                <div ng-style=\"isSmartDevice && {'width':'75%'}\">\n" +
    "                    <span class=\"text-lt\" style=\"font-weight: bold;\" ng-click=\"onDealClick(showDeal.id)\">{{showDeal.title}}</span>\n" +
    "                    <small class=\"text-muted clear text-ellipsis myEllipsis\"><span>{{showDeal.shortDescription}}</span></small>\n" +
    "                </div>\n" +
    "\n" +
    "                <div layout=\"column\" style=\"text-align: right;\">\n" +
    "\n" +
    "                    <div>\n" +
    "                        <div ng-show=\"showDeal.timeValid\">\n" +
    "                            <button style=\"width: 100px; margin-top: 0px; \" class=\"btn btn-vsm btn-error\" ng-click=\"submitDealAction(showDeal, 'reject')\" ng-disabled=\"true\">{{::'page.activities.dealReject' | translate}}</button></a>\n" +
    "                        </div>\n" +
    "                        <div ng-hide=\"showDeal.timeValid\">\n" +
    "                            <button style=\"width: 100px; margin-top: 0px; \" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showDeal, 'accept')\" ng-disabled=\"true\">{{::'page.activities.dealAccept' | translate}}</button></a>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <div>\n" +
    "                        <div>\n" +
    "                            <button style=\"width: 100px; margin-top: 0px; \" class=\"btn btn-vsm btn-info\" ng-click=\"submitDealAction(showDeal, 'invite')\" ng-disabled=\"true\">{{::'page.activities.invite' | translate}}</button></a>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <div style=\"text-align: center;\">\n" +
    "                <h2>\n" +
    "                    You personal activation code:\n" +
    "                    <span>{{showDeal.dealCode}}</span>\n" +
    "                </h2>\n" +
    "            </div>\n" +
    "\n" +
    "\n" +
    "            <div class=\"hotelDescrElt\">\n" +
    "\n" +
    "                <div style=\"text-align: right;margin-right: 1px;margin-bottom: -47px;margin-top: 16px; padding: 3px;\">\n" +
    "\n" +
    "                    <a facebook-feed-share class=\"facebookShare\"\n" +
    "                       data-url='{{HOST}}/#/app/activities/{{showDeal.id}}'\n" +
    "                       data-shares='shares',\n" +
    "                       data-description=\"Example text\"\n" +
    "                       data-picture=\"{{HOST}}{{showDeal.pictureUrl}}\"\n" +
    "                       data-name=\"{{showDeal.name}}\"\n" +
    "                       data-caption=\"caption\"\n" +
    "                       data-description=\"{{showDeal.shortDescription}}\"\n" +
    "                    >\n" +
    "                        {{ shares }}\n" +
    "                    </a>\n" +
    "\n" +
    "                </div>\n" +
    "\n" +
    "                <accordion close-others=\"oneAtATime\" ng-show=\"showDeal.description\">\n" +
    "\n" +
    "                    <accordion-group is-open=\"accordionStatus.openActivities[showDeal.id]\" is-disabled=\"false\">\n" +
    "\n" +
    "                        <accordion-heading>\n" +
    "                            <!--<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen, 'fa-angle-right': !accordionStatus.infoHotelOpen}\"></i>-->\n" +
    "                            <div class=\"text-center\">\n" +
    "                                <!--<i class=\"glyphicon glyphicon-align-justify\"></i> Offer Description-->\n" +
    "                                <button style=\"margin: 0;\" class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\" ng-click=\"openDeal(showDeal.initId)\"><span ng-show=\"localState.openActivities[showDeal.initId]\">Hide</span><span ng-hide=\"localState.openActivities[showDeal.initId]\">Open</span></button>\n" +
    "                            </div>\n" +
    "\n" +
    "                        </accordion-heading>\n" +
    "\n" +
    "                        <div ng-bind-html=\"showDeal.description\" style=\"word-wrap: break-word;\"></div>\n" +
    "\n" +
    "                        <hr>\n" +
    "\n" +
    "\n" +
    "                    </accordion-group>\n" +
    "                </accordion>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "\n" +
    "            <div ng-show=\"showDeal.dealStatus != 'accepted'\">\n" +
    "                <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block btn-addon\" ng-click=\"submitDealAction(showDeal, 'acceptDeal')\"><i class=\"fa fa-check\"></i>ACCEPT DEAL</button>\n" +
    "            </div>\n" +
    "            \n" +
    "            \n" +
    "        </div>\n" +
    "        \n" +
    "        \n" +
    "        <!--<div layout=\"column\" class=\"myCenter\" ng-click=\"onHotelCheckinClick(showDeal.id);\">-->\n" +
    "            <!--<i class=\"fa fa-angle-right\" style=\"padding:5px; color: #888; font-size: 24px;\"></i>-->\n" +
    "        <!--</div>-->\n" +
    "   </div>\n" +
    "   <!--<div ng-show=\"accordionStatus.infoHotelOpen[showDeal.id]\" style=\"border-top-width:1px; border-top-style: inherit; border-top-color: lightgray; \">-->\n" +
    "       <!--<div ng-bind-html=\"showDeal.info\" style=\"word-wrap: break-word; font-size: 14px; margin: 5px;\"></div>-->\n" +
    "   <!--</div> -->\n" +
    "    \n" +
    "\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/showHotelBlock.html",
    "<!-- START HOTEL DESCR --->\n" +
    "<div class=\"panel b-a panel-default image-box-shadow\" ng-show=\"showHotel.id>0 || showHotel.id<0\">\n" +
    "\n" +
    "    <div ng-if=\"localState.showHotelBlockFullInfo\">\n" +
    "        \n" +
    "        <div ng-if=\"showHotel.rating>0\" class=\"bottom text-white\" style=\"padding: 5px;text-align: right;margin-bottom: -25px;height: 25px;z-index: 9;position: relative;\" >\n" +
    "            <!--ng-show=\"!localState.selectedFilterHotel\"-->\n" +
    "            <div class=\"hotelInfoOpacity\">\n" +
    "                <div class=\"top text-right padder w-full\">\n" +
    "                    <rating ng-model=\"showHotel.rating\" readonly=\"true\" max=\"5\" state-on=\"'fa fa-star text-orange'\" state-off=\"'fa fa-star-o text-orange'\"></rating>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div style=\"margin-top: -1px;\">\n" +
    "\n" +
    "            <div class=\"item m-l-n-xxs m-r-n-xxs\" ng-click=\"onHotelCheckinClick(showHotel.id)\">\n" +
    "                <img ng-src=\"{{showHotel.pictureUrl}}\" ng-show=\"showHotel.pictureUrl\" class=\"img-full\">\n" +
    "            </div>\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "    </div>\n" +
    "    \n" +
    "\n" +
    "    <div class=\"myFlex\" style=\"justify-content: space-between; -webkit-justify-content: space-between; -moz-justify-content: space-between; -ms-justify-content:space-between; margin: 5px; font-size: 26px;\">\n" +
    "        <span class=\"text-lt\" style=\"font-weight: bold;\" ng-click=\"onHotelCheckinClick(showHotel.id)\">{{showHotel.name}}</span>\n" +
    "        <!--<a class=\"text-lt\" style=\"font-weight: bold;\" ui-sref=\"app.activityList({filterCity: showHotel.city, filterHotelId: showHotel.id})\" ng-show=\"!localState.hotelBlockInternActivities && showHotel.activityNumber>0\">{{showHotel.activityNumber}}<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" /></a>-->\n" +
    "        <!--<a class=\"text-lt\" style=\"font-weight: bold;\" ng-click=\"scrollTo('activitiesAnchor')\" ng-show=\"localState.hotelBlockInternActivities && showHotel.activityNumber>0\">{{showHotel.activityNumber}}<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" /></a>-->\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"text-orange\"style=\"margin-left:5px; font-size: 20px;font-size: 17px;margin-top: -4px;\">\n" +
    "        <a class=\"text-lt\" style=\"text-decoration: underline;\" ui-sref=\"app.activityList({filterCity: showHotel.city, filterHotelId: showHotel.id})\" ng-show=\"!localState.hotelBlockInternActivities && showHotel.activityNumber>0\">{{showHotel.activityNumber}}\n" +
    "            <!--<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" />-->\n" +
    "            <span ng-show=\"showHotel.activityNumber==1\">{{'system.activity' | translate}}</span>\n" +
    "            <span ng-show=\"showHotel.activityNumber>1\">{{'system.activities' | translate}}</span>\n" +
    "        </a>\n" +
    "                    <span ng-show=\"!localState.hotelBlockInternActivities && showHotel.activityNumber>0 && !localState.hotelBlockInternGuests && showHotel.customerNumber>0\">\n" +
    "                        , \n" +
    "                    </span>\n" +
    "        <a class=\"text-lt\" style=\"text-decoration: underline;\" ng-click=\"clickLoading('app.chatList', null, null, {hotelScope: hotelState.profileData.checkedIn && app.header.tabIndex==1, filterCity: showHotel.city, filterHotelId: showHotel.id})\" ng-show=\"!localState.hotelBlockInternGuests && showHotel.customerNumber>0\">{{showHotel.customerNumber}}\n" +
    "            <!--<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" />-->\n" +
    "            <span ng-show=\"showHotel.customerNumber==1\">{{'system.guest' | translate}}</span>\n" +
    "            <span ng-show=\"showHotel.customerNumber>1\">{{'system.guests' | translate}}</span>\n" +
    "        </a>\n" +
    "    </div>\n" +
    "\n" +
    "    <div ng-if=\"hotelState.profileData.admin\" style=\"margin-top: 5px;display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;justify-content: flex-end;\">\n" +
    "        <!--<button style=\"width: 110px;\" class=\"btn m-b-xs btn-sm btn-info btn-addon\" ui-sref=\"app.editActivity({activityId: showActivity.activityId, edit: true})\"><i class=\"fa fa-edit\"></i>{{::'page.activities.edit' | translate}}</button>-->\n" +
    "        <!--<br/>-->\n" +
    "        <button style=\"width: 130px;\" class=\"btn m-b-xs btn-sm btn-orange btn-addon\" ng-click=\"removeHotel(showHotel.id)\"><i class=\"fa fa-trash-o\"></i>Hotel {{::'page.hotel.remove' | translate}}</button>\n" +
    "    </div>\n" +
    "    \n" +
    "    <div class=\"hotelDescrElt\" ng-if=\"localState.showHotelBlockFullInfo && showHotel.description\">\n" +
    "        <accordion close-others=\"oneAtATime\">\n" +
    "            \n" +
    "            <accordion-group is-open=\"accordionStatus.descriptionHotelOpen[showHotel.id]\" is-disabled=\"accordionStatus.isDescriptionHotelDisabled[showHotel.id]\">\n" +
    "\n" +
    "                <accordion-heading>\n" +
    "                    <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.descriptionHotelOpen[showHotel.id], 'fa-angle-right': !accordionStatus.descriptionHotelOpen[showHotel.id]}\"></i>\n" +
    "                    <div class=\"text-center\">\n" +
    "                        <div class=\"hotelAccordion\">\n" +
    "                            <i class=\"glyphicon glyphicon-align-justify\"></i> {{'page.hotel.dataFacts' | translate }}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <!--<i class=\"fa fa-pencil-square-o\"></i> &nbsp; {{::'page.hotel.updateHotelInfo' | translate }} -->\n" +
    "                </accordion-heading>\n" +
    "\n" +
    "                <div ng-bind-html=\"showHotel.description\" style=\"word-wrap: break-word;\"></div>\n" +
    "\n" +
    "            </accordion-group>\n" +
    "            \n" +
    "        </accordion>\n" +
    "        \n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"hotelDescrElt\" ng-show=\"showHotel.info\">\n" +
    "        <accordion close-others=\"oneAtATime\">\n" +
    "\n" +
    "            <accordion-group is-open=\"accordionStatus.infoHotelOpen[showHotel.id]\" is-disabled=\"accordionStatus.isInfoHotelDisabled[showHotel.id]\">\n" +
    "\n" +
    "                <accordion-heading>\n" +
    "\n" +
    "                    <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen[showHotel.id], 'fa-angle-right': !accordionStatus.infoHotelOpen[showHotel.id]}\"></i>\n" +
    "                    \n" +
    "                    <div class=\"text-center\">\n" +
    "                        <div class=\"hotelAccordion\">\n" +
    "                            <i class=\"glyphicon glyphicon-align-justify\"></i> {{'page.hotel.azInfos' | translate }}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <!--<i class=\"fa fa-pencil-square-o\"></i> &nbsp; {{::'page.hotel.updateHotelInfo' | translate }} -->\n" +
    "                \n" +
    "                </accordion-heading>\n" +
    "\n" +
    "                <div ng-bind-html=\"showHotel.info\" style=\"word-wrap: break-word;\"></div>\n" +
    "\n" +
    "            \n" +
    "            </accordion-group>\n" +
    "       \n" +
    "        </accordion>\n" +
    "        \n" +
    "    </div>\n" +
    "\n" +
    "    <!--<div class=\"hotelEditElt\" ng-if=\"(hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showHotel.id || hotelState.profileData.admin)\">-->\n" +
    "\n" +
    "        <!--<accordion close-others=\"oneAtATime\">-->\n" +
    "            <!--<accordion-group is-open=\"accordionStatus.openEditHotels[showHotel.id]\" is-disabled=\"accordionStatus.isFirstDisabled\" class=\"b-info\">-->\n" +
    "                <!--&lt;!&ndash;<accordion-heading>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<i class=\"fa fa-edit\"></i>  &nbsp; {{'page.activities.editActivity' | translate }} <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.open, 'fa-angle-right': !accordionStatus.open}\"></i>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;</accordion-heading>&ndash;&gt;-->\n" +
    "                <!--<accordion-heading>-->\n" +
    "                    <!--&lt;!&ndash;<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen, 'fa-angle-right': !accordionStatus.infoHotelOpen}\"></i>&ndash;&gt;-->\n" +
    "                    <!--<div class=\"text-center\">-->\n" +
    "                        <!--&lt;!&ndash;<i class=\"glyphicon glyphicon-align-justify\"></i> Offer Description&ndash;&gt;-->\n" +
    "                        <!--<button style=\"margin: 0;\" class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\" ng-click=\"openEditHotel(showHotel)\"><span ng-show=\"accordionStatus.openEditHotels[showHotel.id]\">Cancel</span><span ng-hide=\"accordionStatus.openEditHotels[showHotel.id]\">Edit</span></button>-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "                    <!--&lt;!&ndash;<i class=\"fa fa-pencil-square-o\"></i> &nbsp; {{::'page.hotel.updateHotelInfo' | translate }} &ndash;&gt;-->\n" +
    "                <!--</accordion-heading>-->\n" +
    "\n" +
    "                <!--<form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"submitHotelChange(showHotel)\" name=\"hotelForm\">-->\n" +
    "\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.hotelName' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" ng-model=\"showHotel.name\" placeholder=\"{{::'page.hotel.hotelName' | translate }}\">-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                  <!-- -->\n" +
    "\n" +
    "\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.hotelCode' | translate }}</label>-->\n" +
    "                        <!--&lt;!&ndash;<input type=\"text\" class=\"form-control\" ng-model=\"newHotel.pictureUrl\" placeholder=\"{{::'page.hotel.hotelLogo' | translate }}\">&ndash;&gt;-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" ng-model=\"showHotel.currentHotelAccessCode\" placeholder=\"{{::'page.hotel.hotelCode' | translate }}\">-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.rating' | translate }}</label>-->\n" +
    "                        <!--&lt;!&ndash;<input type=\"text\" class=\"form-control\" ng-model=\"newHotel.pictureUrl\" placeholder=\"{{::'page.hotel.hotelLogo' | translate }}\">&ndash;&gt;-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" ng-model=\"showHotel.rating\" placeholder=\"{{::'page.hotel.rating' | translate }}\">-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.phone' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.phone\" placeholder=\"{{::'page.hotel.phone' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.fax' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.fax\" placeholder=\"{{::'page.hotel.fax' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.hotelEmail' | translate }}</label>-->\n" +
    "                        <!--<input type=\"email\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.email\" placeholder=\"{{::'page.hotel.hotelEmail' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.street' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.street\" placeholder=\"{{::'page.hotel.street' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.house' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.house\" placeholder=\"{{::'page.hotel.house' | translate }}\">-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.city' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.city\" placeholder=\"{{::'page.hotel.city' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.postalCode' | translate }}</label>-->\n" +
    "                        <!--<input type=\"text\" class=\"form-control\" rows=\"5\" ng-model=\"showHotel.postalCode\" placeholder=\"{{::'page.hotel.postalCode' | translate }}\"/>-->\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.hotelDescription' | translate }}</label>-->\n" +
    "                        <!--&lt;!&ndash;<div lazy-load=\"textAngular\">&ndash;&gt;-->\n" +
    "                        <!--<div>-->\n" +
    "                            <!--&lt;!&ndash;<small>{{::'system.restLength' | translate}}:</small> &ndash;&gt;-->\n" +
    "                            <!--<span class=\"count\" ng-bind=\"localState.maxHotelDescription - showHotel.description.length\" ng-class=\"{danger: showHotel.description.length > localState.maxHotelDescription}\">1040</span>-->\n" +
    "                        <!--</div>-->\n" +
    "                        <!--<div text-angular ng-model=\"showHotel.description\" class=\"btn-groups\"  ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo', 'clear']          ]\" ></div>-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<label>{{::'page.hotel.info' | translate }}</label>-->\n" +
    "                        <!--&lt;!&ndash;<div lazy-load=\"textAngular\">&ndash;&gt;-->\n" +
    "                        <!--<div>-->\n" +
    "                            <!--&lt;!&ndash;<small>{{::'system.restLength' | translate}}:</small> &ndash;&gt;-->\n" +
    "                            <!--<span class=\"count\" ng-bind=\"localState.maxHotelDescription - showHotel.info.length\" ng-class=\"{danger: showHotel.info.length > localState.maxHotelDescription}\">1040</span>-->\n" +
    "                        <!--</div>-->\n" +
    "                        <!--<div text-angular ng-model=\"showHotel.info\" class=\"btn-groups\"   ta-toolbar=\"[['bold', 'italics', 'underline', 'ul', 'ol', 'redo', 'undo', 'clear']          ]\" ></div>-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\" id=\"img{{showHotel.id}}\">-->\n" +
    "                        <!--<label>{{::'page.activities.activityLogo' | translate}}</label>-->\n" +
    "\n" +
    "                        <!--<input ng-model=\"newHotelFile\"-->\n" +
    "                               <!--onchange=\"angular.element(this).scope().newFile_changed(this, 'hotel')\"-->\n" +
    "                               <!--type=\"file\" accept=\"image/*\" />-->\n" +
    "                        <!--&lt;!&ndash;<input type=\"button\" ng-click=\"uploadFilesX()\">&ndash;&gt;-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"form-group\">-->\n" +
    "                        <!--<button type=\"submit\" ng-disabled=\"showHotel.description.length > localState.maxHotelDescription\" class=\"btn btn-success\">{{::'page.hotel.updateHotelInfo' | translate }}</button>-->\n" +
    "                    <!--</div>-->\n" +
    "                <!--</form>-->\n" +
    "            <!--</accordion-group>-->\n" +
    "        <!--</accordion>-->\n" +
    "    <!--</div>-->\n" +
    "\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/showHotelPreviewBlock.html",
    "<!-- START HOTEL DESCR --->\n" +
    "<div class=\"panel b-a panel-default image-box-shadow\" ng-show=\"previewHotel.id>0 || previewHotel.id<0\">\n" +
    "    \n" +
    "    <div layout=\"row\" style=\"font-size: 25px;\">\n" +
    "        \n" +
    "        <div ng-click=\"onHotelCheckinClick(previewHotel.id)\">\n" +
    "            <div ng-if=\"previewHotel.rating>0\" class=\"bottom text-white\" style=\"padding: 5px;text-align: right;margin-bottom: -10px;height: 10px;z-index: 9;position: relative;top: -2px;left: -7px; font-size: 12px;\" >\n" +
    "                <!--ng-show=\"!localState.selectedFilterHotel\"-->\n" +
    "                <div class=\"hotelInfoOpacity\">\n" +
    "                    <div class=\"top text-right padder w-full\" style=\"padding-left: 5px; padding-right: 5px;\">\n" +
    "                        <rating ng-model=\"previewHotel.rating\" readonly=\"true\" max=\"5\" state-on=\"'fa fa-star text-orange'\" state-off=\"'fa fa-star-o text-orange'\"></rating>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <img ng-src=\"{{previewHotel.previewPictureUrl}}\" ng-show=\"previewHotel.previewPictureUrl\" style=\"width: 100px;\">\n" +
    "        </div>\n" +
    "        \n" +
    "        <div style=\"flex-grow: 1; margin-left: 3px;\" layout=\"column\">\n" +
    "            <div style=\"flex-grow: 1;\" layout=\"column\" ng-click=\"onHotelCheckinClick(previewHotel.id)\">\n" +
    "                <div layout=\"row\" >\n" +
    "                    <span class=\"text-lt\" style=\"font-weight: bold;\" >{{previewHotel.name}}</span>\n" +
    "                    \n" +
    "                </div>\n" +
    "                \n" +
    "                <div class=\"text-orange\" layout=\"column\" style=\"margin-left:5px; font-size: 20px;font-size: 17px;margin-top: -4px;\">\n" +
    "                    <!--ui-sref=\"app.activityList({filterCity: previewHotel.city, filterHotelId: previewHotel.id})\"-->\n" +
    "                   \n" +
    "                    <!--<span ng-show=\"!localState.hotelBlockInternActivities && previewHotel.activityNumber>0 && !localState.hotelBlockInternGuests && previewHotel.customerNumber>0\">-->\n" +
    "                        <!--, -->\n" +
    "                    <!--</span>-->\n" +
    "                    <!--ng-click=\"clickLoading('app.chatList', null, null, {hotelScope: hotelState.profileData.checkedIn && app.header.tabIndex==1, filterCity: previewHotel.city, filterHotelId: previewHotel.id})\"-->\n" +
    "                    <a class=\"text-lt\" style=\"text-decoration: underline;\"  ng-show=\"!localState.hotelBlockInternGuests && previewHotel.customerNumber>0\">{{previewHotel.customerNumber}}\n" +
    "                        <!--<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" />-->\n" +
    "                        <span ng-show=\"previewHotel.customerNumber==1\">{{'system.guest' | translate}}</span>\n" +
    "                        <span ng-show=\"previewHotel.customerNumber>1\">{{'system.guests' | translate}}</span>\n" +
    "                    </a>\n" +
    "                    \n" +
    "                    <a class=\"text-lt\" style=\"text-decoration: underline;\"  ng-show=\"!localState.hotelBlockInternActivities && previewHotel.activityNumber>0\">{{previewHotel.activityNumber}}\n" +
    "                        <!--<img style=\"margin-top: -5px; margin-right: 5px;\" src=\"angulr/img/build/menu/bottom/angebot-active.png\" />-->\n" +
    "                        <span ng-show=\"previewHotel.activityNumber==1\">{{'system.activity' | translate}}</span>\n" +
    "                        <span ng-show=\"previewHotel.activityNumber>1\">{{'system.activities' | translate}}</span>\n" +
    "                    </a>\n" +
    "                </div> \n" +
    "               \n" +
    "             </div>\n" +
    "            \n" +
    "            <!--<div style=\"font-size: 16px;margin-left: 5px;\" ng-show=\"previewHotel.currentHotelAccessCode=='demo'\" ng-click=\"onHotelCheckinClick(previewHotel.id)\">-->\n" +
    "\n" +
    "                <!--<i class=\"fa fa-info-circle \"></i> {{'page.checkin.tryDemo' | translate}}-->\n" +
    "                <!-- -->\n" +
    "            <!--</div> -->\n" +
    "            \n" +
    "            <div layout=\"row\" style=\"font-size: 18px;margin-left: 5px; justify-content: space-between; -webkit-justify-content: space-between; -moz-justify-content: space-between; \" ng-show=\"previewHotel.currentHotelAccessCode!='demo'\">\n" +
    "                 \n" +
    "                <div   ng-click=\"openModal('editHotel', previewHotel.id)\" is-disabled=\"accordionStatus.isInfoHotelDisabled[previewHotel.id]\">\n" +
    "                    <i class=\"fa fa-info-circle \"></i> Details\n" +
    "                </div>\n" +
    "                \n" +
    "                <div ng-click=\"onHotelCheckinClick(previewHotel.id)\">\n" +
    "                    <span ng-show=\"previewHotel.kmFromMe\"  style=\"font-size: 14px; font-weight: bold;\">({{previewHotel.kmFromMe}} km)</span>\n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "            \n" +
    "            <!--<div class=\"hotelDescrElt\" ng-if=\"previewHotel.description\" style=\"font-size: 15px;\">-->\n" +
    "\n" +
    "                <!--<div class=\"text-center\" > -->\n" +
    "                    <!--<div class=\"hotelAccordion\" ng-click=\"accordionStatus.infoHotelOpen[previewHotel.id] = !accordionStatus.infoHotelOpen[previewHotel.id];\" is-disabled=\"accordionStatus.isInfoHotelDisabled[previewHotel.id]\">-->\n" +
    "                        <!--<i class=\"glyphicon glyphicon-align-justify\"></i> {{'page.hotel.dataFacts' | translate }}-->\n" +
    "                    <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "                <!-- -->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "            <!--<div class=\"hotelDescrElt\" ng-show=\"previewHotel.info\">-->\n" +
    "                <!--<accordion close-others=\"oneAtATime\">-->\n" +
    "\n" +
    "                    <!--<accordion-group is-open=\"accordionStatus.infoHotelOpen[previewHotel.id]\" is-disabled=\"accordionStatus.isInfoHotelDisabled[previewHotel.id]\">-->\n" +
    "\n" +
    "                        <!--<accordion-heading>-->\n" +
    "\n" +
    "\n" +
    "                            <!--<div class=\"text-center\">-->\n" +
    "                                <!--<div class=\"hotelAccordion\">-->\n" +
    "                                    <!--<i class=\"glyphicon glyphicon-align-justify\"></i> {{'page.hotel.azInfos' | translate }}-->\n" +
    "                                <!--</div>-->\n" +
    "                            <!--</div>-->\n" +
    "                            <!--&lt;!&ndash;<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen[previewHotel.id], 'fa-angle-right': !accordionStatus.infoHotelOpen[previewHotel.id]}\"></i>&ndash;&gt;-->\n" +
    "\n" +
    "                        <!--</accordion-heading>-->\n" +
    "\n" +
    "                        <!--<div ng-bind-html=\"previewHotel.info\" style=\"word-wrap: break-word; font-size: 14px;\"></div>-->\n" +
    "\n" +
    "\n" +
    "                    <!--</accordion-group>-->\n" +
    "\n" +
    "                <!--</accordion>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "        \n" +
    "        </div>\n" +
    "        <div layout=\"column\" class=\"myCenter\" ng-click=\"onHotelCheckinClick(previewHotel.id);\">\n" +
    "            <i class=\"fa fa-angle-right\" style=\"padding:5px; color: #888; font-size: 24px;\"></i>\n" +
    "        </div>\n" +
    "   </div>\n" +
    "   <!--<div ng-show=\"accordionStatus.infoHotelOpen[previewHotel.id]\" style=\"border-top-width:1px; border-top-style: inherit; border-top-color: lightgray; \">-->\n" +
    "       <!--<div ng-bind-html=\"previewHotel.info\" style=\"word-wrap: break-word; font-size: 14px; margin: 5px;\"></div>-->\n" +
    "   <!--</div> -->\n" +
    "\n" +
    "    \n" +
    "    \n" +
    "\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/blocks/socialLogin.html",
    "<!--<div class=\"panel panel-default\">-->\n" +
    "    <!--<div class=\"panel-heading font-bold\">Or login with social networks:</div>-->\n" +
    "    <!--<div class=\"panel-body\">-->\n" +
    "    \n" +
    "    <div class=\"myBorder\">\n" +
    "        \n" +
    "        <a ng-click=\"loginService.loginSocial('facebook','facebookLoginId')\" id=\"facebookLoginId\" class=\"btn btn-block btn-social btn-facebook\">\n" +
    "            <i class=\"fa fa-facebook\"></i>\n" +
    "            Login with Facebook\n" +
    "        </a>\n" +
    "\n" +
    "        <span> \n" +
    "            - We don't show your private Information <br/>\n" +
    "            - We never post in Facebook\n" +
    "        </span>\n" +
    "    </div>\n" +
    "\n" +
    "       \n" +
    "        \n" +
    "        <!--<a ng-click=\"loginService.loginSocial('linkedIn','linkedinLoginId')\" id=\"linkedinLoginId\" class=\"btn btn-block btn-social btn-linkedin\">-->\n" +
    "            <!--<i class=\"fa fa-linkedin\"></i>-->\n" +
    "            <!--Login with LinkedIn-->\n" +
    "        <!--</a>-->\n" +
    "        \n" +
    "    <!--</div>-->\n" +
    "<!--</div>-->");
  $templateCache.put("angulr/tpl/hotel/page_404.html",
    "<div class=\"container w-xxl w-auto-xs\" ng-init=\"app.settings.container = false;\">\n" +
    "  <div class=\"text-center m-b-lg\">\n" +
    "    <h1 class=\"text-shadow text-white\">404</h1>\n" +
    "  </div>\n" +
    "  <div class=\"list-group bg-info auto m-b-sm m-b-lg\">\n" +
    "    <a href=\"#/\" class=\"list-group-item\">\n" +
    "      <i class=\"fa fa-chevron-right text-muted\"></i>\n" +
    "      <i class=\"fa fa-fw fa-mail-forward m-r-xs\"></i> Goto application\n" +
    "    </a>\n" +
    "    <a ui-sref=\"app.signin\" class=\"list-group-item\">\n" +
    "      <i class=\"fa fa-chevron-right text-muted\"></i>\n" +
    "      <i class=\"fa fa-fw fa-sign-in m-r-xs\"></i> Sign in\n" +
    "    </a>\n" +
    "    <a ui-sref=\"app.signup\" class=\"list-group-item\">\n" +
    "      <i class=\"fa fa-chevron-right text-muted\"></i>\n" +
    "      <i class=\"fa fa-fw fa-unlock-alt m-r-xs\"></i> Sign up\n" +
    "    </a>\n" +
    "  </div>\n" +
    "  <div class=\"text-center\" ng-include=\"'angulr/tpl/blocks/page_footer.html'\">\n" +
    "    {% include 'blocks/page_footer.html' %}\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_about.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "	 app.hideFooter = true;\n" +
    "	 app.header.showTab = false;\n" +
    "	 app.header.showBackArrow = true;\n" +
    "	 app.rootSettings.darkBg = true;\n" +
    "\n" +
    "  \">\n" +
    "\n" +
    "	<!--<div class=\"bg-light lter b-b wrapper-md\">-->\n" +
    "		<!--<h1 class=\"m-n font-thin h3\">hotelico.de About</h1>-->\n" +
    "	<!--</div>-->\n" +
    "	<div class=\"wrapper-md\">\n" +
    "		<div class=\"row\">\n" +
    "			<div style=\"padding: 5px;\">\n" +
    "				<!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "				<span class=\"localHeader\">Lust auf Gesellschaft im Hotel?</span>\n" +
    "				<br/>\n" +
    "				M&ouml;chtest Du Sonderangebote f&uuml;r Hotel-Services und das Restaurant bekommen?\n" +
    "\n" +
    "				Brauchst du Information rund um das Hotel und die Umgebung?\n" +
    "				<br><br>\n" +
    "				Dann gebe in dem Tab \"Check-in\" den Hotel-Code ein (erh&auml;ltlich an der Rezeption), chatte mit\n" +
    "\n" +
    "				anderen G&auml;sten und nutze zusammen mit denen die Sonderangebote des Hotels.\n" +
    "				<br><br>\n" +
    "				Suchst du nach einem Hotel?\n" +
    "				<br><br>\n" +
    "\n" +
    "				In dem Tab \"Alle Hotels\" bekommst Du eine &uuml;bersicht &uuml;ber teilnehmende Hotels.\n" +
    "\n" +
    "				Du kannst dir Angebote und Veranstaltungen dort ansehen,\n" +
    "\n" +
    "				mit der Rezeption, mit aktuellen oder ehemaligen Hotelg&auml;sten chatten.\n" +
    "\n" +
    "				Sich ein Feedback &uuml;ber das Hotel einholen oder direkt zusammen mit Hotelg&auml;sten ein\n" +
    "\n" +
    "				Treffen/Ausflug schon mal einplanen.\n" +
    "				\n" +
    "				<br><br>\n" +
    "				\n" +
    "				Unser Hotelnetz erweitert sich kontinuierlich weiter.\n" +
    "\n" +
    "				Und wir vergr&ouml;&szlig;ern den Funktionsumfang von Hotelico.\n" +
    "\n" +
    "				Als n&auml;chstes geplante Funktionen:\n" +
    "				\n" +
    "				<ul>\n" +
    "					<li>Auswahl der Hotels nach sozialem Bewertungsfaktor</li>\n" +
    "					<li>Bewertungen der Angebote</li>\n" +
    "					<li>Smartphone als Zimmerschl&uuml;ssel</li>\n" +
    "					<li>Navigation innerhalb des Hotels</li>\n" +
    "				</ul>\n" +
    "\n" +
    "			</div>\n" +
    "		</div>\n" +
    "\n" +
    "	</div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_activityList.html",
    "<div data-ng-include=\" 'angulr/tpl/hotel/blocks/dealSubTabBlock.html' \" style=\"display: flex;\"/>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" ng-controller=\"ActivityListController\" class=\"bg-light lter wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "hotelState.checkHeaderTab();\n" +
    "\">\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "  \n" +
    "  <div class=\"wrapper-md\" style=\"padding-top: 0;\">\n" +
    "  <div class=\"row\">\n" +
    "    <!--<div class=\"col-sm-9\">-->\n" +
    "    <div>\n" +
    "      <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    <div class=\"wrapper text-center\" style=\"padding-top: 0px;\">\n" +
    "      \n" +
    "      <div   style=\"text-align: left; margin-top: 10px;\">\n" +
    "\n" +
    "        <!--<div style=\"white-space: pre-line;\">{{::'page.hotel.activityFilterLabel' | translate}}:</div>-->\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />\n" +
    "        \n" +
    "      </div>\n" +
    "      \n" +
    "      <hr style=\"margin: 0;\"/>\n" +
    "      \n" +
    "      <span class=\"hotelicoHeader\" ng-show=\"localState.filterActivities.length>0\">\n" +
    "          {{::'page.hotel.activities' | translate}} \n" +
    "          <span ng-show=\"localState.selectedFilterHotel\"> in {{localState.selectedFilterHotel.name}}</span>\n" +
    "          <span ng-show=\"!localState.selectedFilterHotel && localState.selectedCityHotel\"> in {{localState.selectedCityHotel.city}}</span>\n" +
    "          :\n" +
    "      </span>\n" +
    "      \n" +
    "    </div>\n" +
    "\n" +
    "      <!-- ACTIVITES START -->\n" +
    "      \n" +
    "\n" +
    "      <!-- LIST ALL ACTIVITIES -->\n" +
    "      <div id=\"activityPart\" ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "        <div class=\"list-group list-group-lg list-group-sp\">\n" +
    "          <div ng-repeat=\"showActivity in localState.filterActivities | orderBy: 'validTo'  track by showActivity.initId\">\n" +
    "  \n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showActivityBlock.html' \" />\n" +
    "  \n" +
    "          </div>\n" +
    "\n" +
    "          <div class=\"nothingFoundMsg\" ng-show=\"localState.noActivityFound\">{{::'page.activities.noActivityFound' | translate}}</div>\n" +
    "          <div class=\"nothingFoundMsg\" ng-show=\"localState.activitiesLoading\">{{::'system.loading' | translate}}...</div>\n" +
    "\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "\n" +
    "      <!-- END ACTIVITES START -->\n" +
    "      \n" +
    "      \n" +
    "    </div>\n" +
    "    \n" +
    "    <!--<hr>-->\n" +
    "    <!-- -->\n" +
    "    <!--<div class=\"col-sm-3\">-->\n" +
    "\n" +
    "      <!--<h5 class=\"font-bold\">{{::'page.hotel.hotelGuests' | translate}} ({{::hotelCustomers.length}})</h5>-->\n" +
    "\n" +
    "\n" +
    "      <!--<div class=\"list-group list-group-lg list-group-sp\">-->\n" +
    "\n" +
    "        <!--&lt;!&ndash;<div ng-repeat=\"nextCustomer in hotelStaffCustomers | orderBy: 'checkinFrom' as filtered_result track by nextCustomer.id\">&ndash;&gt;-->\n" +
    "\n" +
    "          <!--&lt;!&ndash;<div ng-show=\"nextCustomer.hotelStaff\">&ndash;&gt;-->\n" +
    "\n" +
    "            <!--<li class=\"list-group-item\" >-->\n" +
    "              <!--<a ui-sref=\"app.user({userId: staffCustomer.id})\" class=\"thumb-sm m-r avatar\" style=\"    margin-left: -10px;\">-->\n" +
    "                <!--<img src=\"{{::getProfileImageUrl(staffCustomer.id)}}\" class=\"r r-2x\">-->\n" +
    "                <!--<i class=\"on md b-white right customerOnlineStatus\"></i>-->\n" +
    "                <!--&lt;!&ndash;<i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObjhotelOnlineGuestIds[nextCustomer.id]\"></i>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObjhotelOnlineGuestIds[nextCustomer.id]\"></i>&ndash;&gt;-->\n" +
    "              <!--</a>-->\n" +
    "            <!-- -->\n" +
    "            <!--<span class=\"pull-right label inline\" style=\"margin-top: 10px;position: absolute;right: 16px;bottom: 21px;\">     -->\n" +
    "              <!--&lt;!&ndash;target=\".app-aside-right\"&ndash;&gt;-->\n" +
    "              <!--<a ui-sref=\"app.chat({receiverId: staffCustomer.id})\" class=\"text-muted\" ui-toggle-class=\"show\" >-->\n" +
    "                <!--<i style=\"color: #1c2b36; font-size: 20px;\" class=\"fa fa-comments-o pull-right text-sm\"></i>-->\n" +
    "                <!--<span style=\"margin-top: 2px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObjunreadChatProSenderCount[staffCustomer.id]\">-->\n" +
    "                  <!--+{{hotelNotification.notificationObjunreadChatProSenderCount[staffCustomer.id]}}-->\n" +
    "                <!--</span>-->\n" +
    "              <!--</a>-->\n" +
    "            <!--</span>-->\n" +
    "              <!--<small class=\"text-muted clear text-ellipsis customerLastSeen\" ng-show=\"hotelNotification.notificationObjlastSeenOnlineHotelGuests[staffCustomer.id]\">last seen {{hotelNotification.notificationObjlastSeenOnlineHotelGuests[staffCustomer.id]}}</small>-->\n" +
    "\n" +
    "              <!--<a class=\"hotelCustomerName\" ui-sref=\"app.user({userId: staffCustomer.id})\">{{staffCustomer.firstName}} {{staffCustomer.lastName}} -->\n" +
    "                <!--&lt;!&ndash;<span ng-show=\"nextCustomer.age && !nextCustomer.hotelStaff\">({{nextCustomer.age}})</span>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<span ng-hide=\"!nextCustomer.jobTitle || nextCustomer.hotelStaff\">({{nextCustomer.jobTitle}})</span>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<span class=\"label bg-primary inline\" ng-show=\"nextCustomer.hotelStaff\">Hotel Staff</span>&ndash;&gt;-->\n" +
    "\n" +
    "              <!--</a>-->\n" +
    "              <!--<small class=\"clear text-ellipsis hotelCustomerJob\">-->\n" +
    "                <!--&lt;!&ndash;{{nextCustomer.status}}&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<span ng-hide=\"!nextCustomer.jobTitle || nextCustomer.hotelStaff\">{{nextCustomer.jobTitle}}</span>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<span ng-hide=\"!nextCustomer.company || nextCustomer.hotelStaff\"><span style=\"margin-left: -3px;\" ng-hide=\"!nextCustomer.jobTitle\">, </span>{{nextCustomer.company}}</span>&ndash;&gt;-->\n" +
    "                <!--<span class=\"label bg-orange hotelStaff inline\" style=\"position: relative;top:-3px;margin-left: 0px;\">{{'system.hotelStaff' | translate}}</span>-->\n" +
    "              <!--</small>-->\n" +
    "\n" +
    "            <!--</li>-->\n" +
    "            <!--&lt;!&ndash;<a href class=\"text-muted\" style=\"position: absolute; bottom: 58px; right: 11px;\" ui-toggle-class=\"show\" target=\".app-aside-right\"><i class=\"fa fa-comment-o pull-right text-sm\"></i></a>&ndash;&gt;-->\n" +
    "\n" +
    "          <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "        <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "        <!-- -->\n" +
    "        <!--<div ng-repeat=\"nextCustomer in hotelCustomers | orderBy: 'checkinFrom' as filtered_result track by nextCustomer.id\">-->\n" +
    "          <!-- -->\n" +
    "          <!--&lt;!&ndash;<div ng-show=\"nextCustomer.id!=profileId && !nextCustomer.hotelStaff\">&ndash;&gt;-->\n" +
    "\n" +
    "          <!--<li class=\"list-group-item\" >-->\n" +
    "            <!--<a ui-sref=\"app.user({userId: nextCustomer.id})\" class=\"thumb-sm m-r avatar\" style=\"    margin-left: -10px;\">-->\n" +
    "              <!--<img src=\"{{getProfileImageUrl(nextCustomer.id)}}\" class=\"r r-2x\">-->\n" +
    "              <!--<i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObjhotelOnlineGuestIds[nextCustomer.id]\"></i>-->\n" +
    "              <!--<i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObjhotelOnlineGuestIds[nextCustomer.id]\"></i>-->\n" +
    "            <!--</a>-->\n" +
    "            <!-- -->\n" +
    "            <!--<span class=\"pull-right label inline\" style=\"margin-top: 10px;position: absolute;right: 16px;bottom: 21px;\">     -->\n" +
    "              <!--&lt;!&ndash;target=\".app-aside-right\"&ndash;&gt;-->\n" +
    "              <!--<a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"text-muted\" ui-toggle-class=\"show\" >-->\n" +
    "                <!--<i style=\"color: #1c2b36; font-size: 20px;\" class=\"fa fa-comments-o pull-right text-sm\"></i>-->\n" +
    "                <!--<span style=\"margin-top: 2px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObjunreadChatProSenderCount[nextCustomer.id]\">-->\n" +
    "                  <!--+{{hotelNotification.notificationObjunreadChatProSenderCount[nextCustomer.id]}}-->\n" +
    "                <!--</span>-->\n" +
    "              <!--</a>-->\n" +
    "            <!--</span>-->\n" +
    "            <!--<small class=\"text-muted clear text-ellipsis customerLastSeen\" ng-show=\"hotelNotification.notificationObjlastSeenOnlineHotelGuests[nextCustomer.id]\">last seen {{hotelNotification.notificationObjlastSeenOnlineHotelGuests[nextCustomer.id]}}</small>-->\n" +
    "\n" +
    "            <!--<a class=\"hotelCustomerName\" ui-sref=\"app.user({userId: nextCustomer.id})\">{{nextCustomer.firstName}} {{nextCustomer.lastName}} -->\n" +
    "              <!--&lt;!&ndash;<span ng-hide=\"!nextCustomer.jobTitle || nextCustomer.hotelStaff\">({{nextCustomer.jobTitle}})</span>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;<span class=\"label bg-primary inline\" ng-show=\"nextCustomer.hotelStaff\">Hotel Staff</span>&ndash;&gt;-->\n" +
    "\n" +
    "            <!--</a>-->\n" +
    "            <!--<small class=\"clear text-ellipsis hotelCustomerJob\">-->\n" +
    "              <!--&lt;!&ndash;{{nextCustomer.status}}&ndash;&gt;-->\n" +
    "              <!--<span ng-hide=\"!nextCustomer.jobTitle\">{{nextCustomer.jobTitle}}</span>-->\n" +
    "              <!--<span ng-hide=\"!nextCustomer.company\"><span style=\"margin-left: -3px;\" ng-hide=\"!nextCustomer.jobTitle\">, </span>{{nextCustomer.company}}</span>-->\n" +
    "              <!--&lt;!&ndash;<span class=\"label bg-primary inline\" ng-show=\"nextCustomer.hotelStaff\">{{'system.hotelStaff' | translate}}</span>&ndash;&gt;-->\n" +
    "            <!--</small>-->\n" +
    "\n" +
    "          <!--</li>-->\n" +
    "          <!--&lt;!&ndash;<a href class=\"text-muted\" style=\"position: absolute; bottom: 58px; right: 11px;\" ui-toggle-class=\"show\" target=\".app-aside-right\"><i class=\"fa fa-comment-o pull-right text-sm\"></i></a>&ndash;&gt;-->\n" +
    "\n" +
    "          <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "        <!--</div>-->\n" +
    "         <!-- -->\n" +
    "      <!--</div>-->\n" +
    "      <!-- -->\n" +
    "    <!--</div>-->\n" +
    "\n" +
    "    <div ng-if=\"!hotelState.profileData.checkedIn\" class=\" text-center app-footer navbar navbar-fixed-bottom bg-light lt b-t\" ng-style=\" ::(isSmartDevice && {'margin': '5px', 'position': 'fixed', 'margin-top': '8px', 'margin-bottom': '2px'}) || (!isSmartDevice && { 'position': 'initial', 'margin-top': '8px', 'margin-left': '0', 'background-color': 'transparent'})\" style=\"background-color: #fff;\">\n" +
    "      <!--ng-hide=\"(localState.selectedFilterHotel || localState.selectedHotelCode || localState.hotelcodeFocus)\" >-->\n" +
    "      <button ui-sref=\"app.checkin\" class=\"btn btn-lg btn-block  btn-blue\">\n" +
    "        <!--{{'page.checkin.demoCheckin' | translate }}-->\n" +
    "        Back to Hotel-Selection\n" +
    "      </button>\n" +
    "    </div>\n" +
    "\n" +
    "  </div>\n" +
    "</div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_agb.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = false;\n" +
    "  app.hideFooter = false;\n" +
    "  app.header.showTab = false;\n" +
    "  app.header.showBackArrow = true;\n" +
    "  app.rootSettings.darkBg = true;\n" +
    "  \">\n" +
    "\n" +
    "	<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "		<h1 class=\"m-n font-thin h3\">hotelico.de AGB</h1>\n" +
    "	</div>\n" +
    "	<div class=\"wrapper-md\">\n" +
    "		<div class=\"row\">\n" +
    "			<div style=\"padding: 5px;\">\n" +
    "				\n" +
    "				<!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "				<p class=\"c9 c13\"><span class=\"c11\">Teilnahmebedingungen</span></p><p class=\"c9 c8 c17\"><span class=\"c5\">&sect; 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Anwendungsbereich und Leistungsumfang</span></p><ol class=\"c4 lst-kix_list_6-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Diese Teilnahmebedingungen, die gelegentlichen Ver&auml;nderungen unterliegen, gelten f&uuml;r die Nutzung aller Dienstleistungen des Hotelico Konsortiums, vertreten durch seinen Federf&uuml;hrer Favoso Consulting GmbH &amp; Co. Team KG, nachfolgend &quot;Hotelico&quot; genannt. Die Angaben zum Konsortium werden durch das Impressum in seiner jeweils aktuellen Fassung erg&auml;nzt. Bestandteil der Teilnahmebedingungen ist ferner die Datenschutzerkl&auml;rung [LINK] in ihrer jeweils geltenden Fassung.</span></li><li class=\"c2 c8\"><span class=\"c0\">Hotelico betreibt im Rahmen ihrer technischen und betrieblichen M&ouml;glichkeiten Telemedien im Internet. F&uuml;r die Nutzung der Dienste k&ouml;nnen jeweils spezielle Voraussetzungen erforderlich sein; der Kunde verpflichtet sich, diese Voraussetzungen zu erf&uuml;llen. Telemedien von Hotelico sind nur insoweit &ouml;ffentlich, wie sie weder durch technische Mittel, gleich welcher Art, noch durch inhaltliche Hinweise in ihrer Nutzung beschr&auml;nkt sind.</span></li><li class=\"c2 c8\"><span class=\"c0\">Jede nat&uuml;rliche voll gesch&auml;ftsf&auml;hige Person, die Hotelgast ist, k&uuml;nftig in einem angeschlossenen Hotel &uuml;bernachten und dann anl&auml;sslich ihres Aufenthaltes mobil auf die vor Ort zur Verf&uuml;gung stehenden Angebote zugreifen oder sich mit angemeldeten Reisenden vor, w&auml;hrend oder nach einer Reise austauschen m&ouml;chte, kann Mitglied der Plattform &bdquo;hotelico.de&ldquo; werden. Hotelbetreiber und ihre G&auml;ste (letztere &bdquo;Nutzer&ldquo; und beide insgesamt nachfolgend auch &bdquo;Kunden&ldquo; genannt) &nbsp;melden sich dort &uuml;ber das Internet oder mobile Endger&auml;te an, um Informationen auszutauschen oder Angebote bereitzustellen, die sich an Teilnehmer richten, welche sich real oder virtuell in einer Hotelumgebung aufhalten (nachfolgend &bdquo;der Service&ldquo;).</span></li><li class=\"c2\"><span class=\"c0\">Mit der Nutzung des Service &nbsp;&ndash; ganz gleich durch welche Mittel &ndash; oder die Inanspruchnahme eines Zugriffscodes, den ein Nutzer in einem angeschlossenen Hotel erh&auml;lt, best&auml;tigt dieser, dass er diese Teilnahmebedingungen sowie die Datenschutzerkl&auml;rung gelesen und verstanden hat und beiden zustimmt. </span></li><li class=\"c2\"><span class=\"c0\">Sofern Hotelico diese Teilnahmebedingungen aktualisiert, wird die neue Version erst Vertragsbestandteil, wenn der Kunde der neuen Version zugestimmt hat. Hierf&uuml;r gen&uuml;gt es, dass der Kunde die neue Version der Teilnahmebedingungen per E-Mail &uuml;bermittelt erh&auml;lt und nicht binnen vier Wochen widerspricht, worauf Hotelico bei &Uuml;bersendung der ge&auml;nderten Teilnahmebedingungen nochmals ausdr&uuml;cklich hinweisen wird.</span></li><li class=\"c2\"><span class=\"c0\">Entgegenstehende oder von diesen Teilnahmebedingungen abweichende Bedingungen von Kunden erkennen wir nicht an, es sei denn, wir h&auml;tten ausdr&uuml;cklich schriftlich ihrer Geltung zugestimmt. Diese Teilnahmebedingungen gelten auch dann, wenn wir in Kenntnis entgegenstehender oder abweichender Bedingungen des Kunden vorbehaltlos Leistungen erbringen.</span></li></ol><p class=\"c3\"><span class=\"c5\">&sect; 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Rechte und Obliegenheiten des Kunden</span></p><ol class=\"c4 lst-kix_list_10-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Jeder Kunde darf f&uuml;r sich bei hotelico.de nur ein Profil erstellen. Das Profil darf nur und ausschlie&szlig;lich vom Ersteller genutzt werden.</span></li><li class=\"c2\"><span class=\"c0\">Jeder Kunde ist f&uuml;r den Inhalt seiner Zugangsdaten allein verantwortlich. Pers&ouml;nliche Daten jeder Art sind unter Verschluss zu halten und d&uuml;rfen nicht an Dritte weiter gegeben sowie nur gegen Zugriff Dritter gesichert abgelegt werden; etwaige Sch&auml;den gehen zu Lasten des berechtigten Nutzers, soweit dieser nicht nachweisen kann, mit der gebotenen Sorgfalt gehandelt zu haben.</span></li><li class=\"c2\"><span class=\"c0\">Der Service steht nur zur privaten und nichtkommerziellen Nutzung zur Verf&uuml;gung. Daher ist es nicht gestattet, Inhalte oder Informationen, Software, Produkte oder Dienstleistungen, die auf der Webseite oder mobil verf&uuml;gbar sind, zu gewerblichen oder wettbewerblichen Zwecken zu vertreiben, mit Unterseiten von Webseiten zu verlinken, zu vervielf&auml;ltigen, zu extrahieren, neu zu ver&ouml;ffentlichen oder sonst anderweitig hochzuladen oder sie zu reproduzieren. Ohne die Erlaubnis von Hotelico ist dem Nutzer jede Werbung auf der Plattform hotelico.de &ndash; gleich in welcher Form &ndash; untersagt. Davon ausgenommen ist die Werbung des Hotelbetreibers f&uuml;r sein jeweiliges Hotel. Die Nutzung personenbezogener Daten anderer Nutzer, insbesondere Namen, Telefonnummern, E-Mail und sonstige Adressdaten, Zugangsdaten zu sozialen Netzen und Bilder ist nur mit deren Zustimmung erlaubt und die gewerbliche Nutzung ist untersagt. Die Bewerbung von anderen Plattformen ist nicht erlaubt.</span></li><li class=\"c2\"><span class=\"c0\">Jeder Kunde ist f&uuml;r die von ihm produzierten, hochgeladenen und ver&ouml;ffentlichten Inhalte selbst verantwortlich. Hotelico stellt mit hotelico.de eine Plattform zum Austausch von Individual- und Gruppenkommunikation sowie von Dienstleistungen zur Verf&uuml;gung, nimmt an diesen aber nicht teil und ist f&uuml;r deren Inhalt insofern als Infrastrukturanbieter auch nicht verantwortlich.</span></li><li class=\"c2\"><span class=\"c0\">Jeder Kunde stellt sicher und erkl&auml;rt hiermit, dass ihm f&uuml;r alle von ihm eingestellten Inhalte die entsprechenden Nutzungsrechte zustehen und Rechte Dritter (gewerbliche Schutzrechte, Urheberrechte und Pers&ouml;nlichkeitsrechte) der Nutzung nicht entgegenstehen. Die Person, die eine Datei hochgeladen hat, gew&auml;hrleistet, dass sie keine Viren, Trojaner, sonstige infizierte Bestandteile, keine pornografischen, illegalen, obsz&ouml;nen, beleidigenden, unzul&auml;ssigen oder unangemessenen Materialien enth&auml;lt. Bei der Einstellung und &ouml;ffentlichen Zug&auml;nglichmachung von Bilddateien, die neben dem jeweiligen Kunden noch andere Personen zeigen, erfolgt dies nur mit der Zustimmung dieser Person.</span></li><li class=\"c2\"><span class=\"c0\">Es ist nicht gestattet, andere Nutzer in unangemessener Weise anzusprechen (unaufgeforderter Massenversand elektronischer Nachrichten &ndash; SPAM -, Kettenbriefe, Schneeballsysteme, Anwerbungen, Beleidigungen, Drohungen, Diskriminierungen, der Versuch der Gruppenbildung zu unlauteren Zwecken etc.). Es ist ferner nicht gestattet, andere Nutzer unangemessen zum Gegenstand von Kommunikation mit Dritten auf der Plattform werden zu lassen (&uuml;ble Nachrede). Bei Verst&ouml;&szlig;en gegen diese Vorschrift kann sich der betroffene Nutzer an das Service-Team von Hotelico wenden und Hotelico ist berechtigt, die Nutzung des Verursachers ohne Ank&uuml;ndigung zu beenden.</span></li><li class=\"c2\"><span class=\"c0\">Die Informationen, die f&uuml;r den Nutzer auf der Plattform hotelico.de abrufbar sind, beruhen auf Daten, die von Hotelbetreibern zur Verf&uuml;gung gestellt werden. Diese haben selbst&auml;ndigen Zugang zum System und tragen somit alleinige Verantwortung daf&uuml;r, dass der Umfang, die Verf&uuml;gbarkeit, die Qualit&auml;t, gegebenenfalls die Bepreisung der von ihnen angebotenen Leistungen und alle anderen Informationen, die sich auf ein Hotel beziehen, zutreffend und aktuell sind. Obwohl Hotelico bei der Auswahl der Hotelbetreiber gewissenhaft vorgeht, ist es nicht m&ouml;glich, die Richtigkeit dieser Informationen zu &uuml;berpr&uuml;fen und zu garantieren. Der Service von Hotelico stellt keine Empfehlung oder Best&auml;tigung der Qualit&auml;t, des Serviceniveaus, der Qualifizierung oder der Klassifikation eines Hotels oder der Richtigkeit von Bewertungen anderer Nutzer des Systems dar. F&uuml;r die Richtigkeit solcher Angaben sind stets die Hotelbetreiber und f&uuml;r die &Uuml;berpr&uuml;fung der jeweilige Nutzer verantwortlich, der die Leistung in Anspruch nehmen m&ouml;chte. Hotelico er&uuml;brigt als Plattform zur Vermittlung von Kommunikation und Dienstleistung anl&auml;sslich des Hotelaufenthalts nicht die gebotene Sorgfalt bei der Hotelauswahl.</span></li><li class=\"c2 c8\"><span class=\"c0\">Bei Nichtzahlung kostenpflichtiger Dienstleistungen ist Hotelico berechtigt, den Kunden mit sofortiger Wirkung von der Nutzung des betreffenden Dienstes oder aller Dienste, auch der anderen kostenpflichtigen und der kostenlosen, auszuschlie&szlig;en. Das schlie&szlig;t ausdr&uuml;cklich Dienstleistungen ein, die &ndash; ungeachtet der Vermittlerrolle von Hotelico &ndash; &uuml;ber die Plattform vertraglich vereinbart und erbracht, aber sodann nachweislich nicht verg&uuml;tet werden. In diesem Zusammenhang entstandene Kosten sind von dem Kunden als Schadensersatz zu ersetzen.</span></li><li class=\"c2\"><span class=\"c0\">Ist Beratungsleistung vereinbart, hat der Kunde sicherzustellen, dass sie am vereinbarten Ort zur vereinbarten Zeit erbracht werden kann. Ist die Leistungserbringung aus Gr&uuml;nden nicht m&ouml;glich, die der Kunde zu vertreten hat, kann einseitig ein neuer Termin mitgeteilt werden. Dem Kunden f&auml;llt der damit verbundene Mehraufwand zur Last. &sect; 3 Absatz 2 bleibt unber&uuml;hrt.</span></li></ol><p class=\"c3\"><span class=\"c5\">&sect; 3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Rechte und Obliegenheiten von Hotelico</span></p><ol class=\"c4 lst-kix_list_2-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Es besteht kein Anspruch auf eine dauerhafte Verf&uuml;gbarkeit von hotelico.de. Sofern f&uuml;r Hotelico aus von ihr nicht zu vertretenden Gr&uuml;nden (beispielsweise Ausfall externer Computernetze, Stromausf&auml;lle, h&ouml;here Gewalt), eine Leistungserbringung nicht oder nicht rechtzeitig m&ouml;glich ist, darf Hotelico die ihr obliegenden Leistungen um die Dauer des hindernden Ereignisses zuz&uuml;glich einer angemessenen Anlauffrist aufschieben.</span></li><li class=\"c2\"><span class=\"c0\">Ist die Leistungserbringung bei Beratungsleistungen aus Gr&uuml;nden nicht m&ouml;glich, die Hotelico nicht zu vertreten hat, wird ein neuer Termin vereinbart, der den Interessen beider Parteien m&ouml;glichst gerecht wird.</span></li><li class=\"c2\"><span class=\"c0\">Hotelico selbst schuldet nur dann Waren oder &uuml;ber den beschriebenen Service hinaus die Bereitstellung technischer Ressourcen, wenn dies ausdr&uuml;cklich vereinbart ist. Sind Waren geschuldet und werden diese nicht von Hotelico selbst zugestellt, geht die Gefahr auf den Kunden &uuml;ber, sobald Hotelico die Lieferung der Transportperson ausgeh&auml;ndigt hat.</span></li><li class=\"c2\"><span class=\"c0\">Bei positiver Kenntnis dar&uuml;ber, dass auf der Plattform hotelico.de durch Inhalte von Nutzern oder durch Verlinkung Rechte verletzt werden, wird Hotelico diese Inhalte oder Verlinkung innerhalb einer angemessenen Zeit entfernen und alle m&ouml;glichen und zumutbaren Vorkehrungen treffen, um eine identische Rechtsverletzung f&uuml;r die Zukunft zu unterbinden. Dateien, die den Kriterien in &sect; 2 Absatz (5) nicht entsprechen, werden nicht ver&ouml;ffentlicht oder k&ouml;nnen durch Hotelico zu jeder Zeit ohne vorherige Mitteilung entfernt werden.</span></li></ol><p class=\"c3\"><span class=\"c5\">&sect; 4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Zustandekommen von Vertr&auml;gen</span></p><ol class=\"c4 lst-kix_list_4-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Der Vertrag &uuml;ber die Nutzung von hotelico.de kommt mit der Registrierung zustande. Er ist jederzeit beidseitig auch ohne Angabe von Gr&uuml;nden k&uuml;ndbar. Der Zugang zu hotelico.de selbst ist f&uuml;r Nutzer kostenfrei, jedoch k&ouml;nnen &uuml;ber die Plattform kostenpflichtige Leistungen angeboten werden.</span></li><li class=\"c2\"><span class=\"c0\">Werden Produkte und Dienstleistungen von anderen Nutzern &uuml;ber hotelico.de oder anl&auml;sslich der Nutzung von hotelico.de angeboten, die kostenpflichtig sind, kommt ein Vertrag dar&uuml;ber nicht mit Hotelico, sondern stets mit dem jeweiligen Anbieter zustande. Sofern der Nutzer ein entsprechendes Vertragsverh&auml;ltnis eingeht, wirkt Hotelico ausschlie&szlig;lich als Vermittler zwischen ihm und dem jeweiligen Anbieter mit und stellt beiden Seiten die zum Vertragsabschluss notwendige technische Grundlage zur Verf&uuml;gung.</span></li><li class=\"c2\"><span class=\"c0\">Werden sonstige Leistungen von Hotelico online angeboten, kommt der Vertrag sp&auml;testens mit deren Nutzung zustande. Im &Uuml;brigen kommen Vertr&auml;ge vorbehaltlich einer gesonderten Regelung mit Zugang einer Auftragsbest&auml;tigung, sp&auml;testens mit Bereitstellung der Leistung zustande. Alle Angebote von Hotelico sind freibleibend, sofern im Angebot nicht ausdr&uuml;cklich etwas Abweichendes bestimmt wird.</span></li><li class=\"c2\"><span class=\"c0\">Bestellungen, die ein Kunde &uuml;ber ein Telemedium bei Hotelico &ndash; also nicht bei Nutzern oder Hotelbetreibern auf der Plattform hotelico.de &ndash; durchf&uuml;hrt, stellen ein unverbindliches Angebot dar, das Hotelico unverz&uuml;glich auf elektronischem Wege best&auml;tigt. Die Bestellbest&auml;tigung ist nur dann eine Angebotsannahme, wenn sie einen entsprechenden Hinweis enth&auml;lt, anderenfalls bleibt sie unverbindlich.</span></li></ol><p class=\"c3\"><span class=\"c5\">&sect; 5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Haftung</span></p><ol class=\"c4 lst-kix_list_9-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Im Rahmen der gesetzlichen Vorschriften haftet weder das Hotelico-Konsortium, seine Mitglieder, der Federf&uuml;hrer Favoso Consulting GmbH &amp; Co. Team KG, noch Mitglieder der Gesch&auml;ftsleitung oder Vertreter, Partnerunternehmen, Bevollm&auml;chtigte oder andere Personen, die in die Entwicklung oder den Betrieb von hotelico.de oder ihres Inhaltes eingebunden sind oder auf eine andere Weise die Seite und ihre Inhalte zur Verf&uuml;gung stellen, f&uuml;r</span></li></ol><ol class=\"c4 lst-kix_list_11-0 start\" start=\"1\"><li class=\"c10 c9\"><span class=\"c0\">Schadensersatzverpflichtungen, konkrete oder mittelbare Sch&auml;den sowie Folgesch&auml;den, den Ausfall von Einnahmen, Gewinnen oder Leistungen, den Untergang von Anspr&uuml;chen oder Vertr&auml;gen und etwaigen Ansehensverlust,</span></li><li class=\"c10 c9\"><span class=\"c0\">Ungenauigkeiten bei beschreibenden Informationen einschlie&szlig;lich der Informationen zu kostenpflichtigen Angeboten in Bezug auf Hotels,</span></li><li class=\"c9 c10\"><span class=\"c0\">den Inhalt und die Qualit&auml;t der angebotenen Dienstleistungen oder der Produkte von Hotelbetreibern oder anderer Nutzer,</span></li><li class=\"c10 c9\"><span class=\"c0\">Sch&auml;den (mittelbare, unmittelbare, Folgesch&auml;den oder Schadensersatzverpflichtungen) und Verluste oder Kosten, die aufgrund der Nutzung oder der nicht m&ouml;glichen Nutzung der Telemedien von Hotelico entstehen oder</span></li><li class=\"c9 c16\"><span class=\"c0\">K&ouml;rper-, Eigentums- oder andere Sch&auml;den, gleich ob unmittelbar, mittelbar, Folgesch&auml;den oder Schadensersatzverpflichtungen und Verluste oder Aufwendungen, die infolge der Inanspruchnahme von Leistungen entstehen, die auf hotelico.de durch Dritte &ndash; Hotelbetreiber oder Nutzer &ndash; angeboten worden sind.</span></li></ol><ol class=\"c4 lst-kix_list_9-0\" start=\"2\"><li class=\"c2\"><span class=\"c0\">Der Kunde nimmt zur Kenntnis und erkl&auml;rt sich damit einverstanden, dass nicht Hotelico, sondern in jedem Fall der Hotelbetreiber oder sonstige Anbieter kostenpflichtiger Leistungen auf hotelico.de oder anl&auml;sslich der Nutzung von hotelico.de f&uuml;r die Vereinnahmung von Entgelten und daf&uuml;r verantwortlich ist, darauf anfallende Steuern an die zust&auml;ndige Steuerbeh&ouml;rde abzuf&uuml;hren. Hotelico steht daf&uuml;r nicht ein.</span></li><li class=\"c2\"><span class=\"c0\">Der Nutzer stellt Hotelico von s&auml;mtlichen Anspr&uuml;chen frei, die Dritte gegen Hotelico aufgrund einer Verletzung durch den Nutzer geltend machen, wenn es sich bei der Verletzung um einen Versto&szlig; gegen gesetzliche Vorschriften, gegen Rechte Dritter oder gegen die Teilnahmebedingungen handelt. Der Nutzer hat dann die Kosten einer notwendigen Rechtsverteidigung von Hotelico zu tragen. Dar&uuml;ber hinausgehende Schadensersatzanspr&uuml;che von Hotelico gegen den Nutzer bleiben davon unber&uuml;hrt. Die Verpflichtung in diesem Absatz (3) gilt nicht, wenn der Nutzer die Verletzung nicht zu vertreten hat.</span></li></ol><p class=\"c3\"><span class=\"c5\">&sect; 6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Rechte, Datenschutz</span></p><ol class=\"c4 lst-kix_list_7-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Die Inhalte des Systems, die Software, die f&uuml;r die Dienstleistungen von Hotelico und insbesondere die Plattform hotelico.de genutzt wird und auf dieser verf&uuml;gbar ist, die Darstellung und der Aufbau unserer Web-Pr&auml;senz sowie die der darin enthaltene Vermittlungsplattform f&uuml;r Kontakte und Dienstleistungen sind &ndash; soweit es sich nicht ausdr&uuml;cklich um gekennzeichnete Angebote und Inhalte von Hotelbetreibern handelt &ndash; Eigentum von Hotelico und ihren Partnern und unterliegen deren jeweiligem Urheberrecht. Der Kunde ist ohne unsere ausdr&uuml;ckliche schriftliche Erlaubnis nicht berechtigt, Inhalte oder die Marke &quot;Hotelico&quot; [DURCH LOGO ERSETZEN] zu kopieren, zu verlinken, zu ver&ouml;ffentlichen, daf&uuml;r zu werben, sie zu vermarkten, zu integrieren, zu benutzen, zu verbinden oder anderweitig zu nutzen. </span></li><li class=\"c2\"><span class=\"c0\">Durch das Hochladen von Dateien stimmt der Kunde zu, dass Hotelico diese Dateien auf ihrer Webseite und der mobilen Version davon &ndash; einschlie&szlig;lich etwaiger Apps sowie f&uuml;r Werbematerial und Ver&ouml;ffentlichungen &ndash; nach eigenem Ermessen verwenden kann. Er gew&auml;hrt Hotelico ein nicht exklusives, weltweites, unwiderrufliches, uneingeschr&auml;nktes, unbefristetes Recht, die Dateien entsprechend zu nutzen, zu reproduzieren, anzuzeigen, zu verbreiten, zu unterlizenzieren, zu kommunizieren und verf&uuml;gbar zu machen.</span></li><li class=\"c2\"><span class=\"c0\">S&auml;mtliche von Kunden &uuml;bermittelte Daten unterliegen der Datenschutzregelung von Hotelico. Unter der Adresse [LINK] kann jederzeit Einblick in diese Datenschutzerkl&auml;rung genommen werden.</span></li></ol><p class=\"c3 c9\"><span class=\"c5\">&sect; 7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c1\">Sonstiges</span></p><ol class=\"c4 lst-kix_list_5-0 start\" start=\"1\"><li class=\"c2\"><span class=\"c0\">Ein Recht zur Aufrechnung steht dem Kunden nur wegen unbestrittener oder rechtskr&auml;ftig festgestellter Gegenanspr&uuml;che zu. Ein Zur&uuml;ckbehaltungsrecht besteht f&uuml;r den Kunden nur hinsichtlich Gegenanspr&uuml;chen aus demselben Rechtsverh&auml;ltnis.</span></li><li class=\"c2\"><span class=\"c0\">Kommt der Kunde in Annahmeverzug oder verletzt er schuldhaft sonstige Mitwirkungspflichten, so ist Hotelico berechtigt, den ihr insoweit entstehenden Schaden, einschlie&szlig;lich etwaiger Mehraufwendungen, ersetzt zu verlangen. Weitergehende Anspr&uuml;che oder Rechte bleiben vorbehalten. Ist eine Lieferung oder Leistung geschuldet, geht in diesem Fall die Gefahr eines zuf&auml;lligen Unterganges oder einer zuf&auml;lligen Verschlechterung der geschuldeten Sache oder Leistung in dem Zeitpunkt auf den Kunden &uuml;ber, in dem dieser in Annahme- oder Schuldnerverzug geraten ist.</span></li><li class=\"c2\"><span class=\"c0\">Hotelico ist berechtigt, sich bei der Erbringung ihrer Dienste der Leistungen Dritter zu bedienen.</span></li><li class=\"c2\"><span class=\"c0\">Nebenabreden oder Abweichungen von diesen Teilnahmebedingungen bed&uuml;rfen der Schriftform, wobei eine best&auml;tigte E-Mail oder ein best&auml;tigtes Telefax ausreichend ist.</span></li><li class=\"c2\"><span class=\"c0\">Gerichtsstand und Erf&uuml;llungsort ist der jeweilige Sitz von Hotelico, wenn der Vertragspartner Kaufmann, eine juristische Person des &ouml;ffentlichen Rechts oder &ouml;ffentlich-rechtliches Sonderverm&ouml;gen ist. Hotelico ist jedoch berechtigt, den Kunden auch am Gericht seines Wohn-/ Gesch&auml;ftssitzes zu verklagen.</span></li><li class=\"c2\"><span class=\"c0\">Es gilt das Recht der Bundesrepublik Deutschland unter Ausschluss der Regelungen des internationalen Privatrechts (IPR) und des UN-Kaufrechts (CISG). Diese Rechtswahl gilt gegen&uuml;ber Verbrauchern jedoch nur insoweit, als nicht zwingende Bestimmungen des Rechts des Staates, in dem der Verbraucher seinen gew&ouml;hnlichen Aufenthalt hat, entgegenstehen.</span></li><li class=\"c2\"><span class=\"c0\">Sollten einzelne Bestimmungen dieser Teilnahmebedingungen unwirksam sein oder werden, ber&uuml;hrt dies die G&uuml;ltigkeit der &uuml;brigen Bestimmungen dieser Vereinbarung nicht. Bei Nichtigkeit einzelner oder mehrerer Bestimmungen gelten dann die gesetzlichen Regelungen.</span></li></ol><div><p class=\"c6\"><span class=\"c0\"></span></p><p class=\"c7\"><span class=\"c0\">-</span><span class=\"c14\">&nbsp;</span><span class=\"c14\">&nbsp;-</span></p><p class=\"c12\"><span class=\"c0\"></span></p></div>\n" +
    "			\n" +
    "			</div>\n" +
    "		</div>\n" +
    "\n" +
    "	</div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_avatar.html",
    "<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "    <h1 class=\"m-n font-thin h3\">Avatar Image</h1>\n" +
    "</div>\n" +
    "<div class=\"wrapper-md\" ng-controller=\"ImageCropCtrl\"  ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "   \n" +
    "   \n" +
    "\n" +
    "    <div ng-show=\"!cropper.sourceImage\">\n" +
    "        <br/>\n" +
    "        <!--<input type=\"file\" name=\"fileInput\" id=\"fileInput\" onchange=\"angular.element(this).scope().fileChanged(event)\" />-->\n" +
    "        <input type=\"file\" accept=\"image/*\" img-cropper-fileread image=\"cropper.sourceImage\"/>\n" +
    "\n" +
    "    </div>\n" +
    "\n" +
    "    <div ng-show=\"cropper.sourceImage && !localState.loading\" style=\"overflow: hidden;\">\n" +
    "        <div>\n" +
    "            <canvas width=\"325\" height=\"300\" id=\"canvas\" image-cropper image=\"cropper.sourceImage\" cropped-image=\"cropper.croppedImage\" crop-width=\"200\" crop-height=\"200\" min-width=\"50\" min-height=\"50\" keep-aspect=\"true\" crop-area-bounds=\"bounds\"></canvas>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div ng-show=\"cropper.sourceImage && cropper.croppedImage && !localState.loading\" style=\"display:flex;  display: -webkit-flex;display: -moz-flex;display: -ms-flexbox; justify-content: space-between; width: 325px;\">\n" +
    "         \n" +
    "        <!--<button ng-click=\"initCrop = true\">Crop</button>-->\n" +
    "        <!--<div style=\"margin-top: 5px;\">-->\n" +
    "            <button  ng-click=\"uploadAvatar()\" class=\"btn-orange\">{{::'system.save' | translate}} Avatar</button>\n" +
    "            <!--<br/>-->\n" +
    "            <!--<button ng-click=\"clear()\">Clear</button>-->\n" +
    "        <!--</div>-->\n" +
    "\n" +
    "        <button ng-click=\"clear()\" style=\"margin-left: 10px;\">{{::'system.cancel' | translate}}</button>\n" +
    "\n" +
    "    </div>\n" +
    "\n" +
    "    <div ng-show=\"localState.loading\">\n" +
    "\n" +
    "        <h2 ng-show=\"localState.loading && !localState.loadComplete\">{{::'page.avatar.loading' | translate}}</h2>\n" +
    "        <h2 ng-show=\"localState.loadComplete\">{{::'page.avatar.loadComplete' | translate}}:</h2>\n" +
    "\n" +
    "        <!--<div>Cropped Image (Left: {{bounds.left}} Right: {{bounds.right}} Top: {{bounds.top}} Bottom: {{bounds.bottom}})</div>-->\n" +
    "        <div ng-show=\"cropper.croppedImage!=null\">\n" +
    "            <img ng-src=\"{{cropper.croppedImage}}\" />\n" +
    "        </div>\n" +
    "        \n" +
    "        <button ng-click=\"goBack()\" style=\"margin-top: 10px;\">{{::'system.back' | translate}}</button>\n" +
    "\n" +
    "        <!--<p>The data-result-blob property is a Blob object, which is necessary in some upload libraries like <a href=\"https://github.com/nervgh/angular-file-upload\" target=\"_blank\">Angular File Upload</a></p>-->\n" +
    "        <!--<p>Image using the data uri:</p>-->\n" +
    "        <!--<img ng-src=\"{{result}}\"></img>-->\n" +
    "        <!--<p>The Base64 String used in the image above:</p>-->\n" +
    "        <!--<textarea class=\"result-datauri\">{{ result }}</textarea>-->\n" +
    "        <!--<div style=\"margin-top: 5px;\">-->\n" +
    "            <!--<button ng-show=\"resultBlob\" ng-click=\"uploadAvatar()\" class=\"btn-orange\">Set Avatar</button>-->\n" +
    "            <!--&lt;!&ndash;<br/>&ndash;&gt;-->\n" +
    "            <!--&lt;!&ndash;<button ng-click=\"clear()\">Clear</button>&ndash;&gt;-->\n" +
    "        <!--</div>-->\n" +
    "        \n" +
    "\n" +
    "\n" +
    "    </div>\n" +
    "    \n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_chat.html",
    "<style media=\"screen\" type=\"text/css\">\n" +
    "    .app:before {  background-color: white !important;}\n" +
    "</style>\n" +
    "\n" +
    "<div style=\"height: 100%;\" class=\"hbox hbox-auto-xs bg-white-only\" ng-controller=\"ChatContr\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.header.tabIndex = 1;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "    <!-- modal -->\n" +
    "    <!--<div class=\"panel b-a\">-->\n" +
    "        <!--<script type=\"text/ng-template\" id=\"myModalContent.html\">-->\n" +
    "            <!--<div ng-include=\"'angulr/tpl/hotel/chat_modal.html'\"></div>-->\n" +
    "        <!--</script>-->\n" +
    "        <!--<button class=\"btn btn-success\" ng-click=\"openModal()\">Open me!</button>-->\n" +
    "    <!--</div>-->\n" +
    "    <!-- /modal -->\n" +
    "    \n" +
    "    <div class=\"hbox hbox-auto-xs hbox-auto-sm\">\n" +
    "        <!-- main -->\n" +
    "        <div ng-class=\"{'col' : isSmartDevice}\">\n" +
    "            \n" +
    "            <!-- / main -->\n" +
    "            <!-- right col -->\n" +
    "            <div ng-class=\"{'col w-md bg-white-only b-l bg-auto no-border-xs': isSmartDevice, 'bg-white-only bg-auto no-border-xs' : !isSmartDevice}\">\n" +
    "                <div id=\"chatContent\" ng-class=\"{'app-aside-right pos-fix no-padder w-md w-auto-xs bg-white b-l fadeInRight chatWindow' : isSmartDevice, 'bg-white b-l chatWindow' : !isSmartDevice}\" ng-style=\" localState.chatVisibleHeight? {'height': localState.chatVisibleHeight}:{'height':'90%'}\"><!-- Eugen weg: animated -->\n" +
    "                    <!--style=\"top:auto;\">-->\n" +
    "                    <div class=\"vbox\" ng-style=\"::isSmartDevice? {}:{'height':'90vh'}\">\n" +
    "                        <div class=\"b-b b-t b-light m-b\" ng-hide=\"::isSmartDevice\" style=\"padding: 5px 15px;\">\n" +
    "                            <a ui-sref=\"app.chatList({hotelScope: localState.chatPartner.hotelId == hotelState.profileData.hotelId})\" class=\"pull-right text-muted text-md\"><i class=\"fa fa-times\"></i></a>\n" +
    "                            Chat with:\n" +
    "\n" +
    "                            <!--<a href ui-sref=\"app.user({userId: localState.chatPartner.id})\" class=\"avatar thumb-sm m-r\">-->\n" +
    "                                <!--&lt;!&ndash;<img src=\"{{getProfileImageUrl(nextCustomer.id)}}\" class=\"r r-2x\">&ndash;&gt;-->\n" +
    "                                <!--<img ng-src=\"{{localState.chatPartner.avatarUrl}}\" class=\"r r-2x\" style=\"height: 20px; margin-right: 5px;\">-->\n" +
    "                                <!--<i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[localState.chatPartner.id]\"></i>-->\n" +
    "                                <!--<i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"localState.chatPartner.hotelStaff || hotelNotification.notificationObj.hotelOnlineGuestIds[localState.chatPartner.id]\"></i>-->\n" +
    "                            <!--</a>-->\n" +
    "                            \n" +
    "                            \n" +
    "                            <!--<a ui-sref=\"app.user({userId: chatPartner.id})\"  class=\"thumb-xs avatar\"><img src=\"{{getProfileImageUrl(chatPartner.id)}}\" alt=\"...\"></a>-->\n" +
    "                            <a ui-sref=\"app.user({userId: localState.chatPartner.id})\">                            \n" +
    "                                <img ng-src=\"{{localState.chatPartner.avatarUrl}}\" class=\"r r-2x\" alt=\"...\" style=\"height: 20px; margin-right: 2px;\">\n" +
    "                                <span  style=\"text-decoration: underline;\">{{::localState.chatPartner.firstName}} {{::localState.chatPartner.lastName}}</span>\n" +
    "                            </a>\n" +
    "                            <span class=\"label bg-orange inline hotelStaff\" ng-show=\"localState.chatPartner.hotelStaff\" style=\"margin-left: 5px; margin-top: 3px;\">{{::'system.hotelStaff' | translate}}</span>\n" +
    "                            <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[localState.chatPartner.id]\" style=\"background-color: red;position: absolute;height: 4px;width: 4px;border-radius: 5px;\"></i>\n" +
    "                            <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"localState.chatPartner.hotelStaff || hotelNotification.notificationObj.hotelOnlineGuestIds[localState.chatPartner.id]\" style=\"background-color: #27c24c;position: absolute;height: 4px;width: 4px;border-radius: 5px;\"></i>\n" +
    "                            <div style=\"display: none\"><a id=\"resendButton\" ng-href=\"resend()\"></a></div>\n" +
    "                        </div>\n" +
    "                        <div class=\"row-row\" >\n" +
    "                            <div id=\"chatContainer\" class=\"cell\" scroll-to-bottom='localState.messages.length+localState.waitingMessages.length*2' layout=\"column\" style=\"justify-content: space-between;\">\n" +
    "                                <div class=\"cell-inner padder\" style=\"margin:5px; padding: 0px;\">\n" +
    "                                    <!-- chat list -->\n" +
    "                                    <div  ng-repeat=\"(key, nextMessage) in hotelState.chatHistoryByPartnerId[localState.receiverId] | orderBy:'creationTime':false track by nextMessage.id\" ng-class=\"{'leftMessage' :hotelState.profileData.id!=nextMessage.senderId, 'rightMessage': hotelState.profileData.id==nextMessage.senderId}\">\n" +
    "\n" +
    "                                        <div style=\"margin-bottom: 5px;\">\n" +
    "                                            <!--<a href=\"\" class=\"pull-left thumb-xs avatar\"><img src=\"{{getProfileImageUrl(message.senderId)}}\" alt=\"...\"></a>-->\n" +
    "\n" +
    "                                            <div class=\"clear\">\n" +
    "                                                <div ng-class=\"{'pos-rlt wrapper-sm b b-light r m-l-sm senderChatMessage': hotelState.profileData.id!=nextMessage.senderId, 'pos-rlt wrapper-sm bg-light r m-r-sm receiverChatMessage': hotelState.profileData.id == nextMessage.senderId}\">\n" +
    "                                                    <span ng-class=\"{'arrow left pull-up' :hotelState.profileData.id!=nextMessage.senderId, 'arrow right pull-up arrow-light': hotelState.profileData.id==nextMessage.senderId}\"></span>\n" +
    "\n" +
    "                                                    <div style=\"vertical-align: top;word-wrap: break-word;\" ng-class=\"{'staffMessage': nextMessage.hotelStaff, 'm-b-none': true}\" >{{nextMessage.message}}</div>\n" +
    "                                                    \n" +
    "                                                    <small class=\"text-muted\" style=\"float: right;margin-top: -5px; display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;\">\n" +
    "                                                        <time style=\"color:#000;\">{{::nextMessage.sendTimeString}}</time>\n" +
    "                                                        <!--Eugen: seenByReceiver-->\n" +
    "                                                        <div ng-show=\"!nextMessage.seenByReceiver && !nextMessage.delieveredToReceiver && hotelState.profileData.id==nextMessage.senderId\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/delieveredToServer.png\"/></div>\n" +
    "                                                        <div ng-show=\"!nextMessage.seenByReceiver && nextMessage.delieveredToReceiver && hotelState.profileData.id==nextMessage.senderId\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/delieveredToReceiver.png\"/></div>\n" +
    "                                                        <div ng-show=\"nextMessage.seenByReceiver && hotelState.profileData.id==nextMessage.senderId\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/seenByReceiver.png\"/></div>\n" +
    "                                                    </small>\n" +
    "                                                </div>\n" +
    "                                                \n" +
    "                                            </div>\n" +
    "                                        </div>\n" +
    "\n" +
    "                                    </div>\n" +
    "\n" +
    "                                    <div ng-repeat=\"(key, nextMessage) in localState.waitingMessages | orderBy:'creationTime':false track by nextMessage.initId\" ng-class=\"{'leftMessage' :hotelState.profileData.id!=nextMessage.senderId, 'rightMessage': hotelState.profileData.id==nextMessage.senderId}\">\n" +
    "\n" +
    "                                        <div style=\"margin-bottom: 5px;\">\n" +
    "                                            <!--<a href=\"\" class=\"pull-left thumb-xs avatar\"><img src=\"{{getProfileImageUrl(message.senderId)}}\" alt=\"...\"></a>-->\n" +
    "\n" +
    "                                            <div class=\"clear\">\n" +
    "                                                <div ng-class=\"{'pos-rlt wrapper-sm b b-light r m-l-sm senderChatMessage': hotelState.profileData.id!=nextMessage.senderId, 'pos-rlt wrapper-sm bg-light r m-r-sm receiverChatMessage': hotelState.profileData.id==nextMessage.senderId}\">\n" +
    "                                                    <span ng-class=\"{'arrow left pull-up' : hotelState.profileData.id!=nextMessage.senderId, 'arrow right pull-up arrow-light': hotelState.profileData.id==nextMessage.senderId}\"></span>\n" +
    "\n" +
    "                                                    <div style=\"vertical-align: top;word-wrap: break-word;\" ng-class=\"{'staffMessage': nextMessage.hotelStaff, 'm-b-none': true}\" > \n" +
    "                                                        <img src=\"angulr/icons/loader.gif\" style=\"margin-right: 5px;\" ng-hide=\"localState.showResend\">\n" +
    "                                                        <i class=\"fa fa-exclamation text-muted\"  ng-show=\"localState.showResend\"/>\n" +
    "                                                        {{nextMessage.decodeMessage}}\n" +
    "                                                    </div>\n" +
    "\n" +
    "                                                     \n" +
    "                                                    \n" +
    "                                                    <small class=\"text-muted\" style=\"position: relative;float: right;margin-top: -15px; z-index: 9999;\" ng-show=\"localState.showResend\">\n" +
    "                                                        <div ng-click=\"resendChatRessource(nextMessage)\" class=\"btn btn-default btn-xs\">\n" +
    "                                                            <i class=\"fa fa-repeat text-muted\" ></i> Retry\n" +
    "                                                        </div>\n" +
    "                                                        <div  ng-click=\"removeWaitingMessage(nextMessage)\" class=\"btn btn-default btn-xs\">\n" +
    "                                                            <i class=\"fa fa-trash-o text-muted\" ></i> Remove\n" +
    "                                                        </div >\n" +
    "                                                        <!--<time>{{message.sendTimeString}}</time>-->\n" +
    "                                                    </small>\n" +
    "                                                </div>\n" +
    "\n" +
    "                                            </div>\n" +
    "                                        </div>\n" +
    "\n" +
    "                                    </div>\n" +
    "\n" +
    "                                    <!--TODO EUGEN: What is chat-box?????-->\n" +
    "                                    <!--<chat-box></chat-box>-->\n" +
    "\n" +
    "                                    <!-- / chat list -->\n" +
    "\n" +
    "                                    \n" +
    "                                    \n" +
    "                                </div>\n" +
    "\n" +
    "                                <!--<div>-->\n" +
    "                                    <!--<form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"addMessage()\" name=\"messageForm\">-->\n" +
    "                                        <!--<div class=\"input-group\">-->\n" +
    "                                            <!--<input type=\"text\" id=\"chatInput\" autocomplete=\"off\" ng-focus=\"focusChatInput()\" ng-blur=\"checkVisibleHeight()\" class=\"form-control\" placeholder=\"Say something\" ng-model=\"localState.newMessage\">-->\n" +
    "                                                <!--<span class=\"input-group-btn\">-->\n" +
    "                                                <!--<button class=\"btn btn-orange\" ng-disabled=\"localState.newMessage.length > max\"> <i class=\"fa fa-angle-right\" style=\"font-size: 18px;\"/></button>-->\n" +
    "                                                <!--</span>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!--<div style=\" /* margin-bottom: -20px;*/\">-->\n" +
    "                                            <!--<span class=\"count\" ng-bind=\"localState.max - localState.newMessage.length\" ng-class=\"{danger: localState.newMessage.length > localState.max}\">{{::localState.max}}</span>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                    <!--</form>-->\n" +
    "                                <!--</div>-->\n" +
    "                                \n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                        <div class=\"wrapper b-t b-light\" style=\"margin-top:0;padding-top: 0px;min-height:53px;\">\n" +
    "                            <form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"addMessage()\" name=\"messageForm\">\n" +
    "                                <div class=\"input-group\">\n" +
    "                                    <input type=\"text\" id=\"chatInput\" autocomplete=\"off\" ng-focus=\"focusChatInput()\" ng-blur=\"checkVisibleHeight()\" class=\"form-control\" placeholder=\"Say something\" ng-model=\"localState.newMessage\">\n" +
    "    <span class=\"input-group-btn\">\n" +
    "    <button class=\"btn btn-orange\" ng-disabled=\"localState.newMessage.length > max\"> <i class=\"fa fa-angle-right\" style=\"font-size: 18px;\"/></button>\n" +
    "    </span>\n" +
    "                                </div>\n" +
    "                                <!--<div style=\" /* margin-bottom: -20px;*/\">-->\n" +
    "                                    <!--<span class=\"count\" ng-bind=\"localState.max - localState.newMessage.length\" ng-class=\"{danger: localState.newMessage.length > localState.max}\">{{::localState.max}}</span>-->\n" +
    "                                <!--</div>-->\n" +
    "                            </form>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <!-- / right col -->\n" +
    "        </div>\n" +
    "    </div>\n" +
    "    </div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_chatList.html",
    "<div style=\"height: 100%;\" ng-controller=\"ChatListContr\" class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.header.tabIndex = 1;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/notLoggedInfoBlock.html' \" style=\"margin: 5px;\"/>\n" +
    "\n" +
    "\n" +
    "    <!-- Eugen: tabIndex set from js -->\n" +
    "    <div class=\"row\" ng-show=\"!localState.showChatDiv\">\n" +
    "       \n" +
    "        <div class=\"col-md-6\" ng-style=\"::!isSmartDevice && {'min-width': '590px', 'width': '100%'}\">\n" +
    "       \n" +
    "            <div class=\"panel no-border\">\n" +
    "\n" +
    "                <!-- CHAT FILTER SELECTOR --->\n" +
    "                \n" +
    "                <div class=\"wrapper b-t b-light\" ng-show=\"!localState.hotelScope\" style=\"border-top: 0px;\">\n" +
    "\n" +
    "                    <!--{{'page.chat.citySelect' | translate}}:                 -->\n" +
    "                    <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showCityLoading\">\n" +
    "                    <select ng-change=\"onChangeCustomerCitySelector();\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.selectedCustomerCity\" id=\"customerCity\" ng-options=\"customer as customer.city for customer in localState.customerCitiesArray | unique:'city'\" placeholder=\" {{'page.chat.customerCityManualSelect' | translate}}\">\n" +
    "                        <option value=\"\" disabled selected> {{'page.chat.citySelect' | translate}}</option>\n" +
    "                        <!--<option value=\"\" > {{::'page.checkin.allCityChoosed' | translate}}</option>-->\n" +
    "                    </select>\n" +
    "                    \n" +
    "                    <span ng-show=\"localState.selectedCustomerCity && localState.filteredHotelsNOTEmpty\">Hotels in {{localState.selectedCustomerCity.city}}:</span>\n" +
    "                    <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showCityHotelLoading && localState.filteredHotelsNOTEmpty\">\n" +
    "                    <select ng-change=\"onChangeFilterHotelSelector();\" ng-show=\"localState.selectedCustomerCity && localState.filteredHotelsNOTEmpty\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.selectedFilterHotel\" id=\"hotel\" ng-options=\"hotel as hotel.name for hotel in localState.hotelsFilteredByCityArray\" placeholder=\" {{::'page.checkin.hotelManualSelect' | translate}}\">\n" +
    "                        <!--<option value=\"\" disabled selected>Hotels in {{selectedCustomerCity.city}}</option>-->\n" +
    "                        <option value=\"\"> {{::'page.checkin.allHotelsChoosed' | translate}}</option>\n" +
    "                    </select>\n" +
    "\n" +
    "\n" +
    "                </div>\n" +
    "                \n" +
    "                <!-- CUSTOMERS IN HOTEL AND STAFF --->\n" +
    "                <div class=\"myCenter m-t\">\n" +
    "                    <span ng-show=\"localState.dataLoaded && (localState.hotelScope && hotelState.hotelCustomers.length==0 && localState.filteredHotelCustomers.length==0)\">{{::'page.chat.noHotelContactFound' | translate }}</span>\n" +
    "                    <span ng-show=\"!localState.dataLoaded\">Loading...</span>\n" +
    "                </div>\n" +
    "\n" +
    "                <div ng-show=\"localState.hotelScope && hotelState.hotelCustomers.length>0\"> \n" +
    "                    \n" +
    "                    <div class=\"panel-heading wrapper b-b b-light\">\n" +
    "                        <h4 class=\"font-thin m-t-none m-b-none text-muted\" ng-show=\"hotelState.profileData.checkedIn\"><!--{{::currentHotel.name}}--> {{::'page.chat.chatListHeader' | translate}}:</h4>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <ul class=\"list-group list-group-lg m-b-none\" ng-repeat=\"(key, staffCustomer) in localState.filterHotelStaffCustomers | orderBy: 'lastMessageTimeToMe':true  track by staffCustomer.id\" >\n" +
    "\n" +
    "                        <li class=\"list-group-item chatListCol\">\n" +
    "                            <a href ui-sref=\"app.chat({receiverId: staffCustomer.id})\" style=\"display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;\">\n" +
    "                                <div ng-click=\"clickLoading('app.chat', null, null, {receiverId: staffCustomer.id})\" style=\"cursor: pointer;\">\n" +
    "                                    <a href ui-sref=\"app.user({userId: staffCustomer.id})\" class=\"avatar thumb-sm m-r\">\n" +
    "                                       \n" +
    "                                        <img ng-src=\"angulr/img/build/avatar/staff.png\" class=\"r r-2x\">\n" +
    "                                        <i class=\"on md b-white right customerOnlineStatus\"></i>\n" +
    "                                        \n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: staffCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: staffCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">\n" +
    "                                        {{hotelNotification.notificationObj.lastMessageTimesToMe[staffCustomer.id]}}\n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: staffCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: staffCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">\n" +
    "                                        <i class=\"fa fa-angle-right\"></i>\n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: staffCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: staffCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">\n" +
    "                                         <span style=\"margin-left: 4px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]\">\n" +
    "                                          +{{hotelNotification.notificationObj.unreadChatProSenderCount[staffCustomer.id]}}\n" +
    "                                        </span>\n" +
    "                                    </a>\n" +
    "                                   \n" +
    "                                    <div class=\"chatUserInfo\">\n" +
    "    \n" +
    "                                        <small class=\"text-muted clear text-ellipsis lastMessageFrom\">\n" +
    "                                            <!-- eugen: ng-class=\"unreadChatProSenderCount[nextCustomer.id]? 'unreadChatMessage':''\" -->\n" +
    "                                            <a class=\"lastMessageFromText\" ui-sref=\"app.chat({receiverId: staffCustomer.id})\">\n" +
    "                                                <div ng-show=\"hotelNotification.notificationObj.lastMessageSeenByCustomer[staffCustomer.id]\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/seenByReceiver.png\"/></div>\n" +
    "                                                <div ng-show=\"!hotelNotification.notificationObj.lastMessageSeenByCustomer[staffCustomer.id] && hotelNotification.notificationObj.lastMessageDelieveredToCustomer[staffCustomer.id]\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/delieveredToReceiver.png\"/></div>\n" +
    "                                                \n" +
    "                                                <!--<div ng-show=\"!hotelNotification.notificationObj.lastMessageSeenByCustomer[staffCustomer.id] && !hotelNotification.notificationObj.lastMessageDelieveredToCustomer[staffCustomer.id]\" style=\"margin-left: 2px;margin-top: -3px;\"><img src=\"angulr/img/build/chat/delieveredToServer.png\"/></div>-->\n" +
    "                                                \n" +
    "                                                {{hotelNotification.notificationObj.lastMessagesToMe[staffCustomer.id]}}\n" +
    "                                            </a>\n" +
    "                                        </small>\n" +
    "        \n" +
    "                                        <!--<a href ui-sref=\"app.chat({receiverId: staffCustomer.id})\" class=\"chatListCustomerName\">-->\n" +
    "                                        <a href ng-click=\"clickLoading('app.chat', null, null, {receiverId: staffCustomer.id})\" class=\"chatListCustomerName\">\n" +
    "                                            {{hotelState.currentHotel.name}}\n" +
    "                                            <span class=\"label bg-orange hotelStaff inline\">{{::'system.hotelStaff' | translate}}</span>\n" +
    "                                        </a>\n" +
    "                                    </div>\n" +
    "                                </div>\n" +
    "                            </a>\n" +
    "                        </li>\n" +
    "\n" +
    "                    </ul>\n" +
    "\n" +
    "                    \n" +
    "                    <ul class=\"list-group list-group-lg m-b-none\" ng-repeat=\"(key, nextCustomer) in localState.filteredHotelCustomers | orderBy: 'lastMessageTimeToMe':true track by nextCustomer.id\" >\n" +
    "                    \n" +
    "                        <li class=\"list-group-item chatListCol\">\n" +
    "                            <a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\" style=\"display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox;\">\n" +
    "                                <div ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" style=\"cursor: pointer;\">\n" +
    "                                    <a href ui-sref=\"app.user({userId: nextCustomer.id})\" class=\"avatar thumb-sm m-r\">\n" +
    "                                        <!--<img src=\"{{::getProfileImageUrl(nextCustomer.id)}}\" class=\"r r-2x\">-->\n" +
    "                                        <img ng-src=\"{{nextCustomer.avatarUrl}}\" class=\"r r-2x\">\n" +
    "                                        <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "                                        <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">\n" +
    "                                        {{hotelNotification.notificationObj.lastMessageTimesToMe[nextCustomer.id]}}\n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">\n" +
    "                                        <i class=\"fa fa-angle-right\"></i>\n" +
    "                                    </a>\n" +
    "                                    <!--<a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">-->\n" +
    "                                    <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">\n" +
    "                                         <span style=\"margin-left: 4px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]\">\n" +
    "                                          +{{hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]}}\n" +
    "                                        </span>\n" +
    "                                    </a>\n" +
    "                                    \n" +
    "                                    <div class=\"chatUserInfo\">\n" +
    "\n" +
    "                                        <small class=\"text-muted clear text-ellipsis lastMessageFrom\">\n" +
    "                                            <!-- eugen: ng-class=\"unreadChatProSenderCount[nextCustomer.id]? 'unreadChatMessage':''\" -->\n" +
    "                                            <!--<a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"text-ellipsis lastChatMessagesToMe\">-->\n" +
    "                                            <a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" class=\"text-ellipsis lastChatMessagesToMe\">\n" +
    "                                                <div ng-show=\"hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/seenByReceiver.png\"/></div>\n" +
    "                                                <div ng-show=\"!hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id] && hotelNotification.notificationObj.lastMessageDelieveredToCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/delieveredToReceiver.png\"/></div>\n" +
    "                                                \n" +
    "                                                <!--<div ng-show=\"hotelNotification.notificationObj.lastMessagesToMe[nextCustomer.id] && !hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id] && !hotelNotification.notificationObj.lastMessageDelieveredToCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/delieveredToServer.png\"/></div>-->\n" +
    "                                                \n" +
    "                                                {{hotelNotification.notificationObj.lastMessagesToMe[nextCustomer.id]}}\n" +
    "                                            </a>  \n" +
    "                                        </small>\n" +
    "            \n" +
    "                                        <!--<a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"chatListCustomerName\">-->\n" +
    "                                        <a href ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" class=\"chatListCustomerName\">\n" +
    "                                            {{nextCustomer.firstName}} <span ng-class=\"hotelState.profileData.logged && hotelState.profileData.fullCheckin? '' : 'diffuseText' \">{{nextCustomer.lastName}}</span>\n" +
    "                                        </a>\n" +
    "                                   </div>\n" +
    "                                </div>\n" +
    "                            </a>\n" +
    "                        </li>\n" +
    "                        \n" +
    "                    </ul>\n" +
    "                    \n" +
    "                </div>\n" +
    "                \n" +
    "                <!-- (OUTER) CONTACTS THAT ARE NOT IN HOTEL -->\n" +
    "\n" +
    "                <div ng-show=\"localState.hotelScope && localState.filteredOutOfHotelContactCustomers.length>0\">\n" +
    "                    <div class=\"panel-heading wrapper b-b b-light\">\n" +
    "                        <h4 class=\"font-thin m-t-none m-b-none text-muted\" ng-show=\"hotelScope && localState.filteredOutOfHotelContactCustomers.length>0\">{{::'page.chat.notHotelPartners' | translate }}</h4>\n" +
    "                        <h4 class=\"font-thin m-t-none m-b-none text-muted\" ng-show=\"!hotelScope\">{{::'page.chat.allYourChatPartners' | translate }}</h4>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <span ng-show=\"localState.filteredOutOfHotelContactCustomers.length==0\">{{::'page.chat.noPartnerFound' | translate }}</span>\n" +
    "                    \n" +
    "                    <ul class=\"list-group list-group-lg m-b-none\" ng-repeat=\"(key, nextCustomer) in localState.filteredOutOfHotelContactCustomers | orderBy: 'lastMessageTimeToMe':true  track by nextCustomer.id\" >\n" +
    "    \n" +
    "                        <li class=\"list-group-item chatListCol\">\n" +
    "                            <a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\">\n" +
    "                                <div ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" style=\"cursor: pointer;\">\n" +
    "                                    <a href ui-sref=\"app.user({userId: nextCustomer.id})\" class=\"avatar thumb-sm m-r\">\n" +
    "                                    <!--<a href ng-click=\"clickLoading('app.user', null, null, {userId: nextCustomer.id})\" class=\"avatar thumb-sm m-r\">-->\n" +
    "                                        <!--<img src=\"{{getProfileImageUrl(nextCustomer.id)}}\" class=\"r r-2x\">-->\n" +
    "                                        <img ng-src=\"{{nextCustomer.avatarUrl}}\" class=\"r r-2x\">\n" +
    "                                        <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "                                        <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[nextCustomer.id]\"></i>\n" +
    "                                    </a>\n" +
    "                                    <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">\n" +
    "                                    <!--<a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageTime': 'text-muted chatMessageTime' \">-->\n" +
    "                                        {{hotelNotification.notificationObj.lastMessageTimesToMe[nextCustomer.id]}}\n" +
    "                                    </a>\n" +
    "                                    <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">\n" +
    "                                    <!--<a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" ng-class=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]? ' unreadMessageTime text-muted chatMessageArrow': 'text-muted chatMessageArrow' \" ui-toggle-class=\"show\">-->\n" +
    "                                        <i class=\"fa fa-angle-right\"></i>\n" +
    "                                    </a>\n" +
    "                                    <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">\n" +
    "                                    <!--<a ng-click=\"clickLoading('app.chat', null, null, {receiverId: nextCustomer.id})\" class=\"text-muted unreadMessagesCounter\" ui-toggle-class=\"show\">-->\n" +
    "                                             <span style=\"margin-left: 4px;\" class=\"badge bg-success\" ng-show=\"hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]\">\n" +
    "                                              +{{hotelNotification.notificationObj.unreadChatProSenderCount[nextCustomer.id]}}\n" +
    "                                            </span>\n" +
    "                                    </a>\n" +
    "                                    \n" +
    "                                    <div class=\"chatUserInfo\">\n" +
    "\n" +
    "                                        <small class=\"text-muted clear text-ellipsis lastMessageFrom\">\n" +
    "                                            <!-- eugen: ng-class=\"unreadChatProSenderCount[nextCustomer.id]? 'unreadChatMessage':''\" -->\n" +
    "                                            <a ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"lastChatMessagesToMe text-ellipsis\">\n" +
    "                                                <div ng-show=\"hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/seenByReceiver.png\"/></div>\n" +
    "                                                <div ng-show=\"!hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id] && hotelNotification.notificationObj.lastMessageDelieveredToCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/delieveredToReceiver.png\"/></div>\n" +
    "                                                \n" +
    "                                                <!--<div ng-show=\"hotelNotification.notificationObj.lastMessagesToMe[nextCustomer.id] && !hotelNotification.notificationObj.lastMessageSeenByCustomer[nextCustomer.id] && !hotelNotification.notificationObj.lastMessageDelieveredToCustomer[nextCustomer.id]\" style=\"margin-left: 2px;margin-top: -2px;\"><img src=\"angulr/img/build/chat/delieveredToServer.png\"/></div>-->\n" +
    "                                                {{hotelNotification.notificationObj.lastMessagesToMe[nextCustomer.id]}}\n" +
    "                                            </a>\n" +
    "                                        </small>\n" +
    "                \n" +
    "                                        <a href ui-sref=\"app.chat({receiverId: nextCustomer.id})\" class=\"chatListCustomerName\">\n" +
    "                                            {{nextCustomer.firstName}} {{nextCustomer.lastName}}\n" +
    "                                        </a>\n" +
    "                                 </div>\n" +
    "                                </div>\n" +
    "                            </a>\n" +
    "                        </li>\n" +
    "                            \n" +
    "                    </ul>\n" +
    "                </div>\n" +
    "\n" +
    "                <!-- (ALL) CONTACTS [IGNORE HOTEL] -->\n" +
    "                \n" +
    "                <div ng-show=\"!localState.hotelScope\">\n" +
    "\n" +
    "                    <div class=\"panel-heading wrapper b-b b-light\">\n" +
    "                        <!--<h4 class=\"font-thin m-t-none m-b-none text-muted\" ng-show=\"app.header.tabIndex==1 && localState.filteredAllContactCustomers.length>0\">{{::'page.chat.notHotelPartners' | translate }}</h4>-->\n" +
    "                        <h4 class=\"font-thin m-t-none m-b-none text-muted\" style=\"border-bottom: 0;\" ng-show=\"!hotelScope\">{{::'page.chat.allYourChatPartners' | translate }}</h4>\n" +
    "                    </div>\n" +
    "\n" +
    "                    <div class=\"nothingFoundMsg\" ng-show=\"localState.dataLoaded && (localState.noContactFound || localState.filteredAllContactCustomers.length==0)\">{{::'page.chat.noPartnerFound' | translate}}<span ng-hide=\"hotelState.profileData.checkedIn\"><br>{{::'page.chat.tryCheckin' | translate}}</span></div>\n" +
    "                    <div class=\"nothingFoundMsg\" ng-show=\"!localState.dataLoaded\">Loading...</div>\n" +
    "\n" +
    "                    <ul class=\"list-group list-group-lg m-b-none\" ng-repeat=\"(key, nextCustomer) in localState.filteredAllContactCustomers | orderBy: 'lastMessageTimeToMe':true track by nextCustomer.id\" >\n" +
    "\n" +
    "                        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/chatListCustomer.html' \" />\n" +
    "\n" +
    "                    </ul>\n" +
    "                    \n" +
    "                    \n" +
    "                </div>\n" +
    "                \n" +
    "                <!-- OTHER VIP Contacts in system-->\n" +
    "                \n" +
    "                <div class=\"vipCustomers\" ng-show=\"!localState.hotelScope\">\n" +
    "\n" +
    "                    <accordion close-others=\"oneAtATime\">\n" +
    "\n" +
    "                        <accordion-group is-open=\"accordionStatus.chatVipOpen\" is-disabled=\"accordionStatus.isChatVipDisabled\" ng-click=\"loadVipChat();\">\n" +
    "\n" +
    "                            <accordion-heading >\n" +
    "                                <div class=\"headerAccordion\" ng-click=\"switchVipCustomers();\">\n" +
    "                                    <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.chatVipOpen, 'fa-angle-right': !accordionStatus.chatVipOpen}\"></i>\n" +
    "                                    <!--<div class=\"panel-heading wrapper b-b b-light\">-->\n" +
    "                                    <h4 class=\"font-thin m-t-none m-b-none text-muted\" style=\"color: #f1592a;\">\n" +
    "                                        {{::'page.chat.allChatCustomers' | translate }}\n" +
    "                                        <span ng-show=\"accordionStatus.chatVipOpen && localState.filterCity!='-1' && (!localState.filterHotelId || localState.filterHotelId=='-1' || localState.filterHotelId==-1)\">in {{localState.filterCity}}</span>\n" +
    "                                        <span ng-show=\"accordionStatus.chatVipOpen && (localState.filterHotelId && localState.filterHotelId!='-1' && localState.filterHotelId!=-1 && localState.selectedFilterHotel)\">in {{localState.selectedFilterHotel.name}}</span>\n" +
    "                                        <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showAllCustomersLoading && accordionStatus.chatVipOpen\"></h4>\n" +
    "                                    <!--</div>-->\n" +
    "                                </div>\n" +
    "                            </accordion-heading>\n" +
    "\n" +
    "                            <ul class=\"list-group list-group-lg m-b-none\" ng-repeat=\"(key, nextCustomer) in localState.filteredOtherCustomers | orderBy: 'city':false  track by nextCustomer.id\" >\n" +
    "\n" +
    "                                <div data-ng-include=\" 'angulr/tpl/hotel/blocks/chatListCustomer.html' \" />\n" +
    "\n" +
    "                            </ul>\n" +
    "                            \n" +
    "                            <div ng-show=\"localState.filteredOtherCustomers.length>=25\" style=\"width: 100%;\" class=\"myCenter\">\n" +
    "                                <a href=\"#\"  ng-click=\"loadMoreVip();\"><b>more...</b></a>\n" +
    "\n" +
    "                            </div>\n" +
    "\n" +
    "                            <div class=\"nothingFoundMsg\" ng-show=\"localState.noOtherContactFound\">{{::'page.chat.noOuterContactsFound' | translate}}</div>\n" +
    "\n" +
    "\n" +
    "                        </accordion-group>\n" +
    "                    </accordion>\n" +
    "                    \n" +
    "                </div>\n" +
    "               \n" +
    "            </div>\n" +
    "            \n" +
    "        </div>\n" +
    "        \n" +
    "    </div>\n" +
    "    \n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_checkIn.html",
    "<div ng-controller=\"CheckInController\" ng-init=\"\n" +
    "app.hideAside = true; \n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\" style=\"border-color: transparent;min-height: 480px; background-color: #fff;\" class=\"myCenter\"> \n" +
    "    \n" +
    "    <div class=\"panel panel-default\" id=\"checkinPanel\" style=\"/*border-color: transparent;*/border-color: #fff;box-shadow:none;-webkit-box-shadow:none;/*background-color: inherit;*/margin-bottom: 0;\"> <!-- ng-show=\"localState.ready\" -->\n" +
    "      \n" +
    "        <!--<div class=\"panel-heading\">-->\n" +
    "        <!--<h3 class=\"panel-title\">{{'page.checkin.title' | translate}} ({{'page.checkin.askReception' | translate}})</h3>-->\n" +
    "      <!--</div>-->\n" +
    "        \n" +
    "        <div class=\"panel-body\" id=\"checkinBody\" style=\"border-color: transparent; padding: 5px;\">\n" +
    "\n" +
    "          <!--<div id=\"langDiv\" ng-style=\" ::isSmartDevice && {'width': '100%'}\" class=\"container w-xxl w-auto-xs hideOnFocus myFlex\" style=\"justify-content: space-between; padding: 0;\">-->\n" +
    "\n" +
    "              <!--<div class=\"text-center\" ui-sref=\"app.hotelLogin\" style=\"padding: 5px;\">-->\n" +
    "                  <!--<a id=\"hotelLoginId\" ui-sref=\"app.hotelLogin\" style=\"white-space: nowrap;\">{{'page.login.hotelLoginPreview' | translate }} <span style=\"text-decoration: underline;\">{{'page.login.hotelLoginLink' | translate}}</span></a>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<div style=\"margin-top: 5px;\" isteven-multi-select id=\"selectSystemLanguages\" input-model=\"localState.availableSystemLanguages\" output-model=\"localState.selectSystemLanguage\" button-label=\"icon langKeyLabel\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\" selection-mode=\"single\"></div>-->\n" +
    "\n" +
    "              <!--</div>-->\n" +
    "              \n" +
    "              <!--<div class=\"text-center\" ui-sref=\"app.hotelLogin\" style=\"padding: 5px;\">-->\n" +
    "                  <!--<a id=\"hotelLoginId\" ui-sref=\"app.hotelLogin\" style=\"white-space: nowrap;\">{{'page.login.hotelLoginPreview' | translate }} <span style=\"text-decoration: underline;\">{{'page.login.hotelLoginLink' | translate}}</span></a>-->\n" +
    "              <!--</div>-->\n" +
    "              \n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "    \n" +
    "              <!--<div class=\"b-b pull-in hideOnFocus\" style=\"margin-top: 5px;\"></div>-->\n" +
    "    \n" +
    "              <!--<button id=\"sendButton\" class=\"button-primary\" ng-click=\"sendPush();\">Send me a push notification</button>-->\n" +
    "\n" +
    "              <div class=\"myCenter text-orange hideOnFocus\" data-ng-include=\" 'angulr/tpl/hotel/blocks/showCarousel.html' \" />\n" +
    "    \n" +
    "              <form class=\"form-horizontal\" >\n" +
    "              \n" +
    "                    <div class=\"form-group\" style=\"margin-top: 0px; margin-bottom: 10px;\">\n" +
    "                        \n" +
    "                        <div class=\"col-lg-12\" ng-class=\"{'notDisplay': localState.hideCodeInput}\">\n" +
    "    \n" +
    "                            \n" +
    "                                <div id=\"checkinCode\" ng-class=\"{'unvisible': localState.selectedFilterHotel}\" class=\"myCenter\">\n" +
    "                                     \n" +
    "                                    <div style=\"margin-bottom: 25px;width: 90%;\">\n" +
    "                                        <input ng-change=\"onHotelCodeInput();\" autocomplete=\"off\" ng-focus=\"hideFocus('none');localState.hotelcodeFocus=true; \" ng-blur=\"hideFocus('flex');localState.hotelcodeFocus=false\" type=\"text\" class=\"form-control\" ng-model=\"localState.selectedHotelCode\" id=\"hotelCode\" placeholder=\"{{'page.checkin.hotelCode' | translate}}\" style=\"height: 80px;font-size: 2em;display: block;margin: 0 auto;text-align: center;border-radius: 10px;outline: 0;\">\n" +
    "                                        <div>\n" +
    "                                            {{'page.checkin.askReception' | translate}}\n" +
    "                                        </div>\n" +
    "                                        <!--<button style=\"margin-top: 15px;\" ng-click=\"onCheckinSubmit()\" ng-disabled=\"localState.disableCheckin\" class=\"btn btn-lg btn-block btn-orange\" >{{localState.selectedFilterHotel.name}} {{'page.checkin.doCheckIn' | translate }}</button>-->\n" +
    "    \n" +
    "                                    </div>\n" +
    "    \n" +
    "                                    <i style=\"margin-top: -4px;padding-left: 8px;padding-right: 5px;position: absolute;\" class=\"fa fa-info-circle navIconItem hotelCitySelectInfo\" ng-click=\"openHotelCodePopup()\"></i>\n" +
    "    \n" +
    "                                </div>\n" +
    "                                \n" +
    "                            \n" +
    "                            \n" +
    "                                <!--<div ng-show=\"localState.selectedFilterHotel\">-->\n" +
    "                                    <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />-->\n" +
    "                                <!--</div>-->\n" +
    "                                \n" +
    "                        </div>\n" +
    "                        \n" +
    "                    </div>\n" +
    "              \n" +
    "                    <!--<div class=\"line b-b line-dashed line-lg pull-in\"></div>-->\n" +
    "\n" +
    "                    <div class=\"hiddenContent\">\n" +
    "                    \n" +
    "                        <div ng-style=\"!localState.showManualCheckin && {'visibilty':'hidden'} \">\n" +
    "    \n" +
    "                            <div layout=\"row\" style=\"justify-content: center;margin-bottom: 5px;\">\n" +
    "                                \n" +
    "                                <div style=\"width: 100%;\">\n" +
    "                                    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />\n" +
    "                                </div>\n" +
    "    \n" +
    "                                <!--<div class=\"text-center navbar bg-light lt b-t\" ng-show=\"(localState.selectedFilterHotel)\" style=\"margin-top: 10px;\">-->\n" +
    "                                    <!--<button ng-click=\"onCheckinSubmit()\" ng-disabled=\"localState.disableCheckin\" class=\"btn btn-lg btn-block btn-orange hiddenContent\">{{localState.selectedFilterHotel.name}} {{'page.checkin.doCheckIn' | translate }}</button>-->\n" +
    "                                <!--</div>-->\n" +
    "                                \n" +
    "                                <!--<div ng-style=\"!localState.selectedFilterHotel && {'visibility' : 'hidden'} \" style=\"margin-top: 39px;margin-left: -39px;\">-->\n" +
    "                                    <!--<button ng-click=\"onCheckinSubmit()\" class=\"btn btn-sm  btn-orange\" tabindex=\"0\" aria-disabled=\"false\">-->\n" +
    "                                         <!-- -->\n" +
    "                                        <!--<i class=\"fa fa-sign-in\" style=\"font-size: 20px;\" ></i>-->\n" +
    "                                    <!--</button>-->\n" +
    "                                <!--</div>-->\n" +
    "    \n" +
    "                               \n" +
    "                                \n" +
    "                            </div>\n" +
    "    \n" +
    "                            <div  ng-repeat=\"(key, previewHotel) in localState.filterHotels | orderBy: 'id':true  track by previewHotel.id\" style=\"margin: 5px;\">\n" +
    "    \n" +
    "                                <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelPreviewBlock.html' \" />\n" +
    "    \n" +
    "                            </div>\n" +
    "    \n" +
    "                            <div ng-style=\" ::!isSmartDevice && {'height':'150px'}\"></div>\n" +
    "                            \n" +
    "                            \n" +
    "                            <!--<div class=\"text-center app-footer navbar navbar-fixed-bottom bg-light lt b-t\" ng-show=\"(localState.selectedFilterHotel || localState.selectedHotelCode || localState.hotelcodeFocus)\" ng-style=\" ::(isSmartDevice && {'position': 'fixed', 'margin-top': '8px', 'margin-bottom': '7px'}) || (!isSmartDevice && { 'position': 'relative', 'margin-top': '8px', 'margin-left': '0'})\">-->\n" +
    "                                <!--<button ng-click=\"onCheckinSubmit()\" ng-disabled=\"localState.disableCheckin\" class=\"btn btn-lg btn-block btn-orange hiddenContent\">{{localState.selectedFilterHotel.name}} {{'page.checkin.doCheckIn' | translate }}</button>-->\n" +
    "                            <!--</div>-->\n" +
    "                            <!--<div class=\" text-center app-footer navbar navbar-fixed-bottom bg-light lt b-t\" ng-style=\" ::(isSmartDevice && {'margin': '5px', 'position': 'fixed', 'margin-top': '8px', 'margin-bottom': '2px'}) || (!isSmartDevice && { 'position': 'initial', 'margin-top': '8px', 'margin-left': '0', 'background-color': 'transparent'})\" style=\"background-color: #fff;\">-->\n" +
    "                                <!--&lt;!&ndash;ng-hide=\"(localState.selectedFilterHotel || localState.selectedHotelCode || localState.hotelcodeFocus)\" >&ndash;&gt;-->\n" +
    "                                <!--<button ng-click=\"onCheckinSubmit('demo')\" ng-disabled=\"localState.disableCheckin || mainState.disableCheckin\" class=\"btn btn-sm  btn-blue hiddenContent\">-->\n" +
    "                                    <!--&lt;!&ndash;{{'page.checkin.demoCheckin' | translate }}&ndash;&gt;-->\n" +
    "                                    <!--Demo Hotel-->\n" +
    "                                <!--</button>-->\n" +
    "                            <!--</div> -->\n" +
    "                    \n" +
    "\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    " \n" +
    "              </form>\n" +
    "          \n" +
    "           \n" +
    "        </div>\n" +
    "\n" +
    "        <div ng-style=\"isSmartDevice && !localState.selectedFilterHotel && {'position': 'fixed'}\" style=\"bottom: 5px; width:100%; text-align: center;\" class=\"hiddenContent\" >\n" +
    "             \n" +
    "            <div class=\" b-b line-sm pull-in\"></div>\n" +
    "\n" +
    "            <div style=\"width: 100%; margin-top: 5px;\">\n" +
    "                <button ng-click=\"onCheckinSubmit('demo')\" ng-disabled=\"localState.disableCheckin\" class=\"btn btn-sm btn-blue\" style=\"font-weight: bold;\">{{'page.checkin.demoCheckin' | translate }}</button>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        \n" +
    "     \n" +
    "    \n" +
    "    \n" +
    "    </div> \n" +
    "</div> ");
  $templateCache.put("angulr/tpl/hotel/page_dealList.html",
    "<div data-ng-include=\" 'angulr/tpl/hotel/blocks/dealSubTabBlock.html' \" style=\"display: flex;\"/>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" ng-controller=\"DealListController\" class=\"bg-light lter wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "hotelState.checkHeaderTab();\n" +
    "\"  >\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "\n" +
    "\n" +
    "    <div class=\"wrapper-md\" style=\"padding-top: 0;\">\n" +
    "  <div class=\"row\">\n" +
    "    <!--<div class=\"col-sm-9\">-->\n" +
    "    <div>\n" +
    "      <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "        <div class=\"myFlex\" style=\"justify-content: space-between;\">\n" +
    "             <span class=\"hotelicoHeader\" ng-show=\"localState.staffView\">\n" +
    "                  {{::'system.deals' | translate}} in {{hotelState.profileData.hotelName}}\n" +
    "              </span> \n" +
    "                \n" +
    "              <span class=\"hotelicoHeader\" ng-show=\"!localState.staffView\">\n" +
    "                  My deals:\n" +
    "              </span>\n" +
    "                \n" +
    "              <div ng-click=\"getUpdateModuleData(true)\"><i class=\"fa fa-refresh\" style=\"font-size: 19px;padding: 5px;}\"></i></div>\n" +
    "    \n" +
    "        </div>\n" +
    "        \n" +
    "          <hr/>\n" +
    "            \n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "    \n" +
    "          <div class=\"wrapper text-center\" style=\"padding-top: 0px;\">\n" +
    "          \n" +
    "          <div   style=\"text-align: left; margin-top: 10px;\">\n" +
    "    \n" +
    "              <!--{{'page.chat.citySelect' | translate}}:                 -->\n" +
    "              <img src=\"angulr/icons/loader.gif\" ng-show=\"localState.showDealLoading\">\n" +
    "              <input type=\"number\" class=\"form-control ng-pristine ng-valid ng-touched\" ng-model=\"localState.filterDealCode\" ng-change=\"updateLocalFilter()\" id=\"dealCode\" placeholder=\" {{'page.activities.dealCode' | translate}}\">\n" +
    "              \n" +
    "            <!--&lt;!&ndash;<div style=\"white-space: pre-line;\">{{::'page.hotel.activityFilterLabel' | translate}}:</div>&ndash;&gt;-->\n" +
    "            <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />-->\n" +
    "            <!---->\n" +
    "          </div>\n" +
    "              \n" +
    "    </div>\n" +
    "\n" +
    "      <!-- ACTIVITES START -->\n" +
    "      \n" +
    "\n" +
    "      <!-- LIST ALL ACTIVITIES -->\n" +
    "      <div id=\"dealPart\" ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "        <div class=\"list-group list-group-lg list-group-sp\">\n" +
    "            <!--DEAl HAVE NO ID!!!!!!!-->\n" +
    "          <div ng-repeat=\"showDeal in localState.filterDeals | orderBy: 'validTo'  track by showDeal.initId\">\n" +
    "  \n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showDealBlock.html' \" />\n" +
    "  \n" +
    "          </div>\n" +
    "\n" +
    "          <div class=\"nothingFoundMsg\" ng-show=\"localState.dataLoaded && localState.noDealFound\">{{::'page.activities.noDealFound' | translate}}</div>\n" +
    "          <div class=\"nothingFoundMsg\" ng-show=\"!localState.dataLoaded && localState.dealsLoading\">{{::'system.loading' | translate}}...</div>\n" +
    "\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "\n" +
    "      <!-- END ACTIVITES START -->\n" +
    "      \n" +
    "      \n" +
    "    </div>\n" +
    "\n" +
    "    <div ng-if=\"!hotelState.profileData.checkedIn\" class=\" text-center app-footer navbar navbar-fixed-bottom bg-light lt b-t\" ng-style=\" ::(isSmartDevice && {'margin': '5px', 'position': 'fixed', 'margin-top': '8px', 'margin-bottom': '2px'}) || (!isSmartDevice && { 'position': 'initial', 'margin-top': '8px', 'margin-left': '0', 'background-color': 'transparent'})\" style=\"background-color: #fff;\">\n" +
    "      <!--ng-hide=\"(localState.selectedFilterHotel || localState.selectedHotelCode || localState.hotelcodeFocus)\" >-->\n" +
    "      <button ui-sref=\"app.checkin\" class=\"btn btn-lg btn-block  btn-blue\">\n" +
    "        <!--{{'page.checkin.demoCheckin' | translate }}-->\n" +
    "        Back to Hotel-Selection\n" +
    "      </button>\n" +
    "    </div>\n" +
    "\n" +
    "  </div>\n" +
    "</div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_dse.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = false;\n" +
    "  app.hideFooter = false;\n" +
    "  app.header.showTab = false;\n" +
    "  app.header.showBackArrow = true;\n" +
    "  app.rootSettings.darkBg = true;\n" +
    "  \">\n" +
    "\n" +
    "	<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "		<h1 class=\"m-n font-thin h3\">hotelico.de Datenschutzerkl&auml;rung</h1>\n" +
    "	</div>\n" +
    "	<div class=\"wrapper-md\">\n" +
    "		<div class=\"row\">\n" +
    "			<div style=\"padding: 5px;\">\n" +
    "				<!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "				<p class=\"c2\"><span class=\"c10 c17\">Hotelico ist sich der Bedeutung der von Ihnen &ndash; ihren Kunden &ndash; &uuml;bermittelten personenbezogenen Daten bewusst und behandelt s&auml;mtliche Informationen streng vertraulich. Mehr &uuml;ber uns erfahren Sie im Impressum, das Sie auf dieser Internet-Pr&auml;senz ebenfalls leicht erreichen. Die nachfolgende Erkl&auml;rung soll allgemein einen &Uuml;berblick dar&uuml;ber geben, wie wir den Schutz der pers&ouml;nlichen Daten unserer Kunden gew&auml;hrleisten.</span></p><p class=\"c8\"><span class=\"c1\">&sect; 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Grundsatz</span></p><p class=\"c12\"><span class=\"c10\">Unter keinen Umst&auml;nden werden von Hotelico Kundendaten zu Werbezwecken an Dritte vermietet, verkauft oder aus sonstigen Gr&uuml;nden ohne Benachrichtigung und ausdr&uuml;ckliche Einwilligung des Kunden weitergegeben.</span></p><p class=\"c5\"><span class=\"c1\">&sect; 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Datenerfassung</span></p><ol class=\"c3 lst-kix_list_1-0 start\" start=\"1\"><li class=\"c11 c15\"><span class=\"c10\">Es steht dem Nutzer frei, sich als Gast anzumelden und auf die Preisgabe seiner Identit&auml;t g&auml;nzlich zu verzichten; gegebenenfalls kann er dann nicht von allen Leistungen profitieren, die wir ihm auf der Plattform zur Verf&uuml;gung stellen. Im &Uuml;brigen kann er sich unter einem Pseudonym registrieren.</span></li><li class=\"c11 c15\"><span class=\"c10\">Von Hotelico werden nur die personenbezogenen Daten des Kunden erfasst, die zur Durchf&uuml;hrung der angebotenen Dienstleistungen notwendig sind. Es kann notwendig sein, diese Daten mit solchen zu kombinieren, &uuml;ber die wir bereits verf&uuml;gen.</span></li><li class=\"c11 c15\"><span class=\"c10\">Im &Uuml;brigen erfolgt die Erhebung der Daten ausschlie&szlig;lich mit ausdr&uuml;cklicher Einwilligung des betroffenen Kunden. Dieser wird vor der Abgabe der Einwilligung zur Datenerfassung &uuml;ber den Zweck der Speicherung und die weitere Verwendung der Daten informiert. Es werden in jedem Fall nur die Daten gespeichert, die vom Kunden auf der Website oder unter Verwendung seines mobilen Endger&auml;tes eingegeben werden.</span></li></ol><p class=\"c5\"><span class=\"c1\">&sect; 3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Daten&uuml;bertragung / -weiterleitung</span></p><p class=\"c12\"><span class=\"c10\">F&uuml;r die &Uuml;bertragung aller vom Kunden eingegebenen sicherheitsrelevanten Daten &uuml;ber das Internet, die aufgrund dieser Erkl&auml;rung erfasst werden, verwendet Hotelico jeweils aktuelle Technologien, die einen Schutz vor unbefugtem Zugriff durch Dritte gew&auml;hrleisten k&ouml;nnen.</span></p><p class=\"c5\"><span class=\"c1\">&sect; 4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Speicherung, Verwendung und notwendige Weitergabe der Daten</span></p><p class=\"c12\"><span class=\"c10\">Die Speicherung der personenbezogenen Kundendaten erfolgt auf Rechnersystemen, deren Sicherheitsstandards stets auf dem Stand der Technik gehalten werden und deren Zugriff durch geeignete Sicherheitsmechanismen gesch&uuml;tzt ist. Die Verwendung und Weitergabe der Daten erfolgt ausschlie&szlig;lich zur Erf&uuml;llung der vereinbarten Dienstleistungen und bezieht sich immer nur auf die Daten, die zur weiteren Bearbeitung notwendig sind.</span></p><p class=\"c5\"><span class=\"c1\">&sect; 5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Cookies</span></p><ol class=\"c3 lst-kix_list_2-0 start\" start=\"1\"><li class=\"c11\"><span class=\"c10\">Im Rahmen unserer Website k&ouml;nnen sogenannte Session-Cookies eingesetzt werden. Solche Cookies legen Daten zur technischen Steuerung im Speicher Ihres Browsers ab. Sie enthalten jedoch keine pers&ouml;nlichen Daten und werden nach dem Schlie&szlig;en Ihres Browsers wieder gel&ouml;scht. Wir legen gegebenenfalls auch Cookies auf Ihrem Computer ab, die nach Beendigung der Sitzung nicht gel&ouml;scht werden. Dieses Vorgehen erm&ouml;glicht es, dass Ihr Computer bei dem n&auml;chsten Besuch wieder erkannt wird. Solche Cookies erlauben es uns, die Website Ihren Bed&uuml;rfnissen anzupassen und Ihnen auf diese Weise einen besseren Dienst anzubieten.</span></li><li class=\"c11\"><span class=\"c10\">Sie k&ouml;nnen Ihren Browser so einstellen, dass er Cookies ablehnt oder Sie von deren Speicherung informiert werden.</span></li></ol><p class=\"c5\"><span class=\"c1\">&sect; 6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Belehrung &uuml;ber Auskunft, &Auml;nderung und L&ouml;schung der Daten</span></p><ol class=\"c3 lst-kix_list_3-0 start\" start=\"1\"><li class=\"c11\"><span class=\"c10\">Wir eben Ihnen gern unentgeltlich und in Schriftform Auskunft &uuml;ber Ihre bei uns gespeicherten personenbezogenen Daten. Jeder Kunde ist jederzeit berechtigt, kostenlos Auskunft &uuml;ber die bei Hotelico &uuml;ber ihn gespeicherten personenbezogenen Daten sowie die Art ihrer Verwendung zu verlangen. Ein entsprechendes Auskunftsverlangen ist an das Support Team von Hotelico zu stellen und wird unverz&uuml;glich schriftlich beantwortet. Die Ansprechpartner sind jeweils im Impressum benannt.</span></li><li class=\"c11\"><span class=\"c10\">Sie k&ouml;nnen jederzeit Ihr Einverst&auml;ndnis bez&uuml;glich der Speicherung Ihrer personenbezogenen Daten schriftlich oder elektronisch widerrufen. Auf Anforderung des Kunden kann &uuml;ber das Support Team jederzeit die &Auml;nderung und L&ouml;schung seiner bei Hotelico gespeicherten personenbezogenen Daten erfolgen.</span></li></ol><p class=\"c5\"><span class=\"c1\">&sect; 7&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Sonstige Ma&szlig;nahmen zum Datenschutz</span></p><p class=\"c12\"><span class=\"c10\">S&auml;mtliche Mitarbeiter von Hotelico, die Zugriff auf die personenbezogenen Daten der Kunden nehmen, sind &uuml;ber die gesetzlichen Bestimmungen des Datenschutzes informiert und zur Verschwiegenheit verpflichtet. Eine Weitergabe der vertraulichen Kundendaten an &ouml;ffentliche Stellen erfolgt ausschlie&szlig;lich auf schriftliche Vorlage einer vollstreckbaren beh&ouml;rdlichen oder gerichtlichen Anordnung und nur soweit Hotelico hierzu gesetzlich verpflichtet ist.</span></p><p class=\"c5\"><span class=\"c1\">&sect; 8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">Datenschutz Minderj&auml;hriger</span></p><p class=\"c12\"><span class=\"c10\">Das Angebot dieser Website richtet sich nicht an Minderj&auml;hrige. Wir gehen daher davon aus, dass uns keine personenbezogenen Daten dieser Altersgruppe &uuml;berlassen werden.</span></p><p class=\"c5\"><span class=\"c1\">&sect; 9&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class=\"c6\">&Auml;nderung der Datenschutzbestimmungen</span></p><p class=\"c12\"><span class=\"c10\">Hotelico beh&auml;lt sich vor, insbesondere aufgrund der Entwicklung neuer technischer Sicherheitsstandards, diese Datenschutzerkl&auml;rung zu &auml;ndern. Die &Auml;nderung wird an geeigneter Stelle durch einen deutlichen Hinweis bekannt gegeben.</span></p><div><p class=\"c13 c14\"><span class=\"c10\"></span></p><p class=\"c13\"><span class=\"c10\">-</span><span class=\"c0\">&nbsp;</span><span class=\"c0\">&nbsp;-</span></p><p class=\"c9\"><span class=\"c10\"></span></p></div>\n" +
    "			</div>\n" +
    "		</div>\n" +
    "\n" +
    "	</div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_editActivity.html",
    "<div class=\"hbox hbox-auto-xs\" ng-controller=\"EditActivityContr\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = false;\n" +
    "  app.hideFooter = false;\n" +
    "    app.header.showTab = false;\n" +
    "    app.header.showBackArrow = true;\n" +
    "    app.rootSettings.darkBg = true;\n" +
    "    \">\n" +
    "    \n" +
    "<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "  <h1 class=\"m-n font-thin h3\">{{'page.activities.editTitle' | translate }} <span ng-show=\"showActivity\">: {{showActivity.title}}</span></h1>\n" +
    "</div>\n" +
    "<div class=\"wrapper-md\">\n" +
    "  <div class=\"row\">\n" +
    "    <div style=\"padding: 5px;\">\n" +
    "        <!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "        \n" +
    "        <span  ng-if=\"!((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showActivity.hotelId) || hotelState.profileData.admin)\">You are not a staff for the Hotel {{showActivity.hotelName}}!</span>\n" +
    "        \n" +
    "        <div data-ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "            <div class=\"hotelEditElt\" ng-if=\"((hotelState.profileData.hotelStaff && hotelState.profileData.hotelId==showActivity.hotelId) || hotelState.profileData.admin)\" ng-show=\"showActivity.initId\">\n" +
    "                 \n" +
    "                <accordion close-others=\"oneAtATime\">\n" +
    "                    <accordion-group is-open=\"accordionStatus.openEditActivities[showActivity.initId]\" is-disabled=\"accordionStatus.isFirstDisabled\" class=\"b-info\">\n" +
    "                    <accordion-heading>\n" +
    "                    <i class=\"fa fa-edit\"></i>  &nbsp; {{'page.activities.editActivity' | translate }} <i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.openEditActivities[showActivity.initId], 'fa-angle-right': !accordionStatus.openEditActivities[showActivity.initId]}\"></i>\n" +
    "                    </accordion-heading>\n" +
    "                        <!--<accordion-heading>-->\n" +
    "                            <!--&lt;!&ndash;<i class=\"pull-right fa fa-angle-right\" ng-class=\"{'fa-angle-down': accordionStatus.infoHotelOpen, 'fa-angle-right': !accordionStatus.infoHotelOpen}\"></i>&ndash;&gt;-->\n" +
    "                            <!--<div class=\"text-center\">-->\n" +
    "                            <!--&lt;!&ndash;<i class=\"glyphicon glyphicon-align-justify\"></i> Offer Description&ndash;&gt;-->\n" +
    "                            <!--<button style=\"margin: 0;\" class=\"btn m-b-xs w-xs btn-orangeBorder btn-rounded\" ng-click=\"openEditActivity(showActivity.initId)\"><span ng-show=\"accordionStatus.openEditActivities[showActivity.initId]\">Cancel</span><span ng-hide=\"accordionStatus.openEditActivities[showActivity.initId]\">Edit</span></button>-->\n" +
    "            <!-- -->\n" +
    "                            <!--</div>-->\n" +
    "                            <!--&lt;!&ndash;<i class=\"fa fa-pencil-square-o\"></i> &nbsp; {{::'page.hotel.updateHotelInfo' | translate }} &ndash;&gt;-->\n" +
    "                        <!--</accordion-heading>-->\n" +
    "        \n" +
    "                        <form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"submitActivityChange(showActivity)\" name=\"activityForm\">\n" +
    "        \n" +
    "                        <div class=\"form-group\">\n" +
    "                        <label>{{'page.activities.activityTitle' | translate}}</label>\n" +
    "                        <input type=\"text\" class=\"form-control\" ng-model=\"showActivity.title\" placeholder=\"{{'page.activities.activityTitle' | translate}}\" required>\n" +
    "        \n" +
    "                        </div>\n" +
    "        \n" +
    "        \n" +
    "                        <div class=\"form-group\" id=\"img{{showActivity.initId}}\">\n" +
    "                        <label>{{::'page.activities.activityLogo' | translate}}</label>\n" +
    "        \n" +
    "                        <input ng-model=\"newActivityFile\" ngf-fix-orientation=\"true\"\n" +
    "                        onchange=\"angular.element(this).scope().newFile_changed(this, 'activity')\"\n" +
    "                        type=\"file\" accept=\"image/*\" />\n" +
    "                        <!--<input type=\"button\" ng-click=\"uploadFilesX()\">-->\n" +
    "                        </div>\n" +
    "        \n" +
    "        \n" +
    "                        <div class=\"form-group\">\n" +
    "                        <label>{{'page.activities.activityShortDescription' | translate}}</label>\n" +
    "                        <textarea class=\"form-control\" rows=\"5\" ng-model=\"showActivity.shortDescription\" placeholder=\"{{'page.activities.activityShortDescription' | translate}}\" required></textarea>\n" +
    "                        </div>\n" +
    "                        <div class=\"form-group\">\n" +
    "                        <label>{{'page.activities.activityDescription' | translate}}</label>\n" +
    "                        <div text-angular ng-model=\"showActivity.description\" class=\"btn-groups\"  ta-toolbar=\"[\n" +
    "                        ['bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'redo', 'undo', 'clear'],\n" +
    "                        ['justifyLeft', 'justifyCenter', 'justifyRight', 'indent', 'outdent'],\n" +
    "                        ['html', 'insertImage','insertLink', 'insertVideo', 'wordcount', 'charcount']\n" +
    "                        ]\" required></div>\n" +
    "                        <!--<textarea class=\"form-control\" rows=\"5\" ng-model=\"newactivity.description\" placeholder=\"{{'page.activities.activityDescription' | translate}}\"></textarea>-->\n" +
    "                        </div>\n" +
    "                        <div class=\"form-group\">\n" +
    "                        <label>{{'page.activities.validFrom' | translate}}</label>\n" +
    "        \n" +
    "                        <div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">\n" +
    "                        <input type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control\" datepicker-popup=\"{{format}}\" ng-model=\"showActivity.validFrom\" is-open=\"opened\" datepicker-options=\"dateOptions\" ng-required=\"true\" close-text=\"Close\" required/>\n" +
    "                        <span class=\"input-group-btn\">\n" +
    "                        <button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>\n" +
    "                        </span>\n" +
    "                        </div>\n" +
    "        \n" +
    "                        </div>\n" +
    "                        <div class=\"form-group\">\n" +
    "                        <label>{{'page.activities.validTo' | translate}}</label>\n" +
    "                        <div class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">\n" +
    "                        <input type=\"text\" placeholder=\"DD.MM.YYYY\"  class=\"form-control\" datepicker-popup=\"{{format}}\" ng-model=\"showActivity.validTo\" is-open=\"opened\" datepicker-options=\"dateOptions\" ng-required=\"true\" close-text=\"Close\" required/>\n" +
    "                        <span class=\"input-group-btn\">\n" +
    "                        <button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>\n" +
    "                        </span>\n" +
    "                        </div>\n" +
    "                        </div>\n" +
    "                        <div class=\"form-group\">\n" +
    "                        <button type=\"submit\" class=\"btn btn-success\" ng-disabled=\"(showActivity.shortDescription.length > localState.maxActivityShortDescription) || showActivity.shortDescription.length == 0\">{{'page.activities.editActivity' | translate }}</button>\n" +
    "                         \n" +
    "                        <button style=\"width: 110px;\" class=\"btn btn-danger\" ng-click=\"removeActivity(showActivity.initId)\"><i class=\"fa fa-trash-o\"></i>{{::'system.remove' | translate}}</button>\n" +
    "        \n" +
    "                        </div>\n" +
    "                        </form>\n" +
    "                    </accordion-group>\n" +
    "                </accordion>\n" +
    "            </div>\n" +
    "\n" +
    "            <div ng-show=\"localState.showActivityId && showActivity.initId\">\n" +
    "\n" +
    "                <b>{{::'system.editPreview' | translate}}:</b>\n" +
    "\n" +
    "                <div class=\"panel panel-default\">\n" +
    "                    <div class=\"b-a panel-default\" style=\"min-height: 150px;\">\n" +
    "\n" +
    "                        <div>\n" +
    "                            <div class=\"item m-l-n-xxs m-r-n-xxs\">\n" +
    "                                <a ui-sref=\"app.editActivity({activityId:nextActivity.initId})\">\n" +
    "                                    <img ng-src=\"{{showActivity.pictureUrl}}\" ng-show=\"showActivity.pictureUrl\" class=\"img-full\">\n" +
    "                                </a>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "\n" +
    "                        <div style=\"display: flex; display: -webkit-flex;display: -moz-flex;display: -ms-flexbox; justify-content: space-between; margin: 10px; font-size: 22px;\">\n" +
    "                            <a ui-sref=\"app.editActivity({activityId:nextActivity.initId})\" class=\"text-lt\" style=\"font-weight: bold;\">\n" +
    "                                {{showActivity.title}}\n" +
    "                            </a>\n" +
    "                        </div>\n" +
    "\n" +
    "                        <div style=\"margin-left: 10px;margin-right: 10px;word-wrap: break-word;\">\n" +
    "                            <small class=\"text-muted clear text-ellipsis emphasize\" >{{showActivity.shortDescription}}</small>\n" +
    "\n" +
    "                        </div>\n" +
    "\n" +
    "                        <div  style=\"margin: 10px;word-wrap: break-word;\">\n" +
    "\n" +
    "\n" +
    "                            <div ng-bind-html=\"showActivity.description\"></div>\n" +
    "\n" +
    "                            <hr>\n" +
    "                            <!--<span>{{'page.activities.customerCounter' | translate}}: {{showActivity.customerCounter}}</span>-->\n" +
    "\n" +
    "                            <div style=\"margin-top: 5px;\" ng-show=\"(hotelState.profileData.hotelStaff || hotelState.profileData.admin)\">\n" +
    "                                <button style=\"width: 110px;\" class=\"btn m-b-xs btn-sm btn-info btn-addon\" ng-click=\"accordionStatus.openEditActivities[showActivity.initId]=true;\"><i class=\"fa fa-edit\"></i>{{'system.edit' | translate}}</button>\n" +
    "                                <br/>\n" +
    "                                <button style=\"width: 110px;\" class=\"btn m-b-xs btn-sm btn-danger btn-addon\" ng-click=\"removeActivity(showActivity.initId)\"><i class=\"fa fa-trash-o\"></i>{{'system.remove' | translate}}</button>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "                \n" +
    "            </div>\n" +
    "\n" +
    "        </div>\n" +
    "            \n" +
    "    </div>\n" +
    "    \n" +
    "  </div>\n" +
    "</div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_editHotel.html",
    "<div id=\"minPaddingPage\" ng-controller=\"HotelController\" class=\"bg-light lter b-b wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = false;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "  <div class=\"wrapper-md\" style=\"padding-top: 0;\" >\n" +
    "    \n" +
    "    <div class=\"row\">\n" +
    "      \n" +
    "      <div>\n" +
    "        <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "        <!--<button id=\"sendButton\" class=\"button-primary\" ng-click=\"send()\">Send me a push notification</button>-->\n" +
    "\n" +
    "        <!-- START HOTEL -->\n" +
    "        \n" +
    "        <div id=\"hotelPart\" ng-show=\"hotelState.profileData.checkedIn\" ng-controller=\"hotelCtrl\">\n" +
    "\n" +
    "            <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/fullCheckinRequest.html' \" />-->\n" +
    "\n" +
    "            <!-- START HOTEL accordion -->\n" +
    "  \n" +
    "          <!--<div class=\"staffContent\" ng-if=\"(hotelState.profileData.hotelStaff  && hotelState.profileData.hotelId==showHotel.id|| hotelState.profileData.admin)\">-->\n" +
    "              <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/editHotelAccardion.html' \" />-->\n" +
    "          <!--</div>-->\n" +
    "          <button ng-click=\"openModal('editHotel', showHotel.id)\" style=\"margin-bottom: 15px;\"  class=\"btn btn-lg btn-block btn-blue\">'{{showHotel.name}}' {{'system.edit' | translate }}</button>\n" +
    "\n" +
    "          <!-- / END hotel accordion -->\n" +
    "          <b>{{::'system.editPreview' | translate}}:</b>\n" +
    "\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "        <!-- END HOTEL -->\n" +
    "        \n" +
    "      </div>\n" +
    "      \n" +
    "    </div>\n" +
    "    \n" +
    "  </div>\n" +
    "  \n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_feed.html",
    "<div class=\"bg-light lter b-b wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\" ng-if=\"hotelState.profileData.hotelStaff || hotelState.profileData.admin\">\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "    <div class=\"bg-light lter b-b wrapper-md\">\n" +
    "        <h1 class=\"m-n font-thin h3\">{{hotelState.profileData.hotelName}} Feed</h1>\n" +
    "    </div>\n" +
    "<div class=\"wrapper-md\" ng-controller=\"FeedController\">\n" +
    "  <div class=\"row\">\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "      <div ng-show=\"successMsg\" style=\"display: flex;justify-content: center; margin-bottom: 10px;\">\n" +
    "            <a ui-sref=\"app.hotel\" class=\"btn btn-sm btn-success\">{{'page.feedback.tohotel' | translate }}</a>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"panel panel-default\">\n" +
    "            <div class=\"panel-body\" id=\"feedBody\">\n" +
    "\n" +
    "                <!--<div style=\"display: flex;\">-->\n" +
    "                    <label>{{localState.selectedFeedCustomers.length}} {{'page.feed.receivers' | translate }}:</label>\n" +
    "\n" +
    "                    <div isteven-multi-select id=\"feedCustomers\" input-model=\"localState.availableFeedCustomers\" output-model=\"localState.selectedFeedCustomers\" button-label=\"icon name\" item-label=\"icon name\" tick-property=\"ticked\" max-height=\"320px\" search-property=\"name\" output-properties=\"name id\" orientation=\"horizontal\" helper-elements=\"all none filter\"></div>\n" +
    "\n" +
    "                <!--</div>-->\n" +
    "                \n" +
    "                  <label>{{'page.feed.textLabel' | translate }}:</label>\n" +
    "                    \n" +
    "                  <br>\n" +
    "                    \n" +
    "                  <div text-angular ng-model=\"localState.feedText\" ta-toolbar=\"[]\" class=\"btn-groups\"></div>\n" +
    "\n" +
    "                  <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\" ng-click=\"submitFeed();\"  ng-disabled=\"!localState.feedText && localState.selectedFeedCustomers.length>0\" >{{'page.feed.button' | translate }}</button>\n" +
    "\n" +
    "            \n" +
    "            </div>\n" +
    " \n" +
    "      </div>\n" +
    "    \n" +
    "  </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_feedback.html",
    "<div class=\"bg-light lter b-b wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "    <div class=\"bg-light lter b-b wrapper-md\">\n" +
    "        <h1 class=\"m-n font-thin h3\">Feedback</h1>\n" +
    "    </div>\n" +
    "<div class=\"wrapper-md\" ng-controller=\"FeedbackController\">\n" +
    "  <div class=\"row\">\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "      <div ng-show=\"successMsg\" class=\"myCenter\" style=\"margin-bottom: 10px;\">\n" +
    "            <a ui-sref=\"app.hotel\" class=\"btn btn-sm btn-success\">{{'page.feedback.tohotel' | translate }}</a>\n" +
    "        </div>\n" +
    "        \n" +
    "      <!--<div class=\"panel b-a\" >-->\n" +
    "\n" +
    "\n" +
    "        <div class=\"panel panel-default\">\n" +
    "            <div class=\"panel-body\">\n" +
    "\n" +
    "                  <label>{{'page.feedback.header' | translate }}:</label>\n" +
    "                    <br>\n" +
    "                     <div ng-hide=\"hotelState.profileData.id>0\">\n" +
    "                         <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.firstName' | translate}}\"  ng-model=\"localState.anonymName\">\n" +
    "                         <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.email' | translate}}\"  ng-model=\"localState.anonymMail\">\n" +
    "\n" +
    "                     </div>\n" +
    "                    <br>\n" +
    "                  <div text-angular ng-model=\"localState.feedbackText\" ta-toolbar=\"[]\" class=\"btn-groups\"></div>\n" +
    "\n" +
    "                    <!--<textarea style=\"width: 100%\"-->\n" +
    "                            <!--ng-model=\"profileData.feedback\">-->\n" +
    "                    <!--</textarea>-->\n" +
    "                  <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\" ng-click=\"submitFeedback();\"  ng-disabled=\"!localState.feedbackText\" >{{'page.feedback.button' | translate }}</button>\n" +
    "\n" +
    "              </div>\n" +
    " \n" +
    "      </div>\n" +
    "    \n" +
    "  </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_forgotpwd.html",
    "<div ng-class=\"isSmartDevice? 'wrapper-md' : 'container w-xxl w-auto-xs' \" data-ng-controller=\"ForgotpwdController\" ng-init=\"\n" +
    "app.settings.container = false;\n" +
    " app.hideFooter = true;\n" +
    " app.header.showTab = false;\n" +
    " app.header.showBackArrow = true;\n" +
    " app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "  <a href class=\"navbar-brand block m-t\">{{app.name}}</a>\n" +
    "  <div class=\"m-b-lg\">\n" +
    "    <div class=\"wrapper text-center\">\n" +
    "      <strong ng-hide=\"showNewPasswordField\">Input your email to reset your password</strong>\n" +
    "      <strong ng-show=\"showNewPasswordField\">Input your new password (minimum 8 characters)</strong>\n" +
    "    </div>\n" +
    "\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    \n" +
    "    <form name=\"requestReset\" ng-init=\"isCollapsed=true\" ng-su ng-hide=\"showNewPasswordField\" ng-submit=\"sendResetCode()\">\n" +
    "      <div class=\"list-group list-group-sm\" >\n" +
    "        <div class=\"list-group-item\">\n" +
    "          <input type=\"email\" placeholder=\"Email\" ng-model=\"resetInfo.email\" class=\"form-control no-border\" required>\n" +
    "        </div>\n" +
    "       \n" +
    "      </div>\n" +
    "      \n" +
    "      <button type=\"submit\" ng-disabled=\"requestReset.$invalid\" class=\"btn btn-lg btn-orange btn-block\">Reset password</button>\n" +
    "\n" +
    "    </form>\n" +
    "\n" +
    "    <form name=\"resetPassword\" ng-show=\"showNewPasswordField\">\n" +
    "      \n" +
    "      <div class=\"list-group list-group-sm\">\n" +
    "\n" +
    "        <div class=\"list-group-item\">\n" +
    "          <input type=\"password\" placeholder=\"Password\" ng-model=\"resetInfo.password\" class=\"form-control no-border\" required>\n" +
    "        </div>\n" +
    "        <div class=\"list-group-item\">\n" +
    "          <input type=\"password\" placeholder=\"Repeat Password\" ng-model=\"resetInfo.repeatPassword\" class=\"form-control no-border\" required>\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "      <button type=\"submit\" ng-click=\"submitNewPassword()\"  ng-disabled=\"resetPassword.$invalid || resetInfo.password!=resetInfo.repeatPassword || resetInfo.password.length<7\" class=\"btn btn-lg btn-primary btn-block\" >Set new password</button>\n" +
    "\n" +
    "\n" +
    "    </form>\n" +
    "    \n" +
    "    <div collapse=\"isCollapsed\" class=\"m-t\">\n" +
    "      <div class=\"alert alert-success\">\n" +
    "        <p>A reset link sent to {{resetInfo.email}}, please check it in 24 hours. \n" +
    "          <div class=\"myCenter\">\n" +
    "          <!--//TODO Eugen-->\n" +
    "            <a ui-sref=\"access.login\" class=\"btn btn-sm btn-success\">Login</a>\n" +
    "          </div>\n" +
    "        </p>\n" +
    "          \n" +
    "      </div>\n" +
    "    </div>\n" +
    "    \n" +
    "    \n" +
    "  \n" +
    "  </div>\n" +
    "  <!--<div class=\"text-center\" ng-include=\"'angulr/tpl/blocks/page_footer.html'\">-->\n" +
    "    <!--{% include 'blocks/page_footer.html' %}-->\n" +
    "  <!--</div>-->\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_hotel.html",
    "<div data-ng-include=\" 'angulr/tpl/hotel/blocks/dealSubTabBlock.html' \" style=\"display: flex;\"/>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" ng-controller=\"HotelController\" class=\"bg-light lter b-b wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = false;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/notLoggedInfoBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "    <div class=\"wrapper-md\" style=\"padding-top: 0;\" >\n" +
    "    \n" +
    "    <div class=\"row\">\n" +
    "      \n" +
    "      <div>\n" +
    "        <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/pushRequest.html' \" />\n" +
    "\n" +
    "\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "        <!--<button id=\"sendButton\" class=\"button-primary\" ng-click=\"send()\">Send me a push notification</button>-->\n" +
    "\n" +
    "        <!-- START HOTEL -->\n" +
    "        \n" +
    "        <div id=\"hotelPart\" ng-show=\"hotelState.profileData.checkedIn\" ng-controller=\"hotelCtrl\">\n" +
    "\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/fullCheckinRequest.html' \" />\n" +
    "\n" +
    "            <!-- START HOTEL accordion -->\n" +
    "  \n" +
    "          <!--<div class=\"staffContent\" ng-if=\"(hotelState.profileData.hotelStaff  && hotelState.profileData.hotelId==showHotel.id|| hotelState.profileData.admin)\">-->\n" +
    "              <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/editHotelAccardion.html' \" />-->\n" +
    "          <!--</div>-->\n" +
    "            \n" +
    "          <!-- / END hotel accordion -->\n" +
    "\n" +
    "          <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />-->\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "        <!-- END HOTEL -->\n" +
    "        \n" +
    "        <!-- ACTIVITES START -->\n" +
    "  \n" +
    "        <div id=\"activityPart\" ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "          <!-- LIST ALL ACTIVITIES -->\n" +
    "\n" +
    "          <div ng-show=\"localState.filterActivities.length>0\" style=\"text-align: center;\" class=\"hotelicoHeader orange-text myCenter\">\n" +
    "            {{::'page.hotel.actualActivities' | translate}} in {{::showHotel.name}}:\n" +
    "            <a id=\"activitiesAnchor\"/>\n" +
    "          </div>\n" +
    "\n" +
    "          <div class=\"staffContent\" ng-if=\"::(hotelState.profileData.hotelStaff || hotelState.profileData.admin)\" style=\"margin-top: 10px; margin-bottom: 15px;\">\n" +
    "            <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/addActivityAccardion.html' \" />-->\n" +
    "            <button class=\"btn btn-lg btn-block btn-blue btn-addon\" ng-click=\"openModal('editActivity', 'new')\"><i class=\"fa fa-plus\"></i>{{::'page.activities.addActivity' | translate }}</button>\n" +
    "          </div>\n" +
    "\n" +
    "          <div class=\"list-group list-group-lg list-group-sp\">\n" +
    "            \n" +
    "            <div ng-repeat=\"showActivity in localState.filterActivities | orderBy: 'validFrom':true  track by showActivity.initId\" >\n" +
    "\n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showActivityBlock.html' \" />\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "          </div>\n" +
    "          \n" +
    "        </div>\n" +
    "        \n" +
    "        \n" +
    "  \n" +
    "        <!-- END ACTIVITES START -->\n" +
    "        \n" +
    "        <!-- END Activities -->\n" +
    "        \n" +
    "      </div>\n" +
    "      \n" +
    "    </div>\n" +
    "    \n" +
    "  </div>\n" +
    "\n" +
    "  <div ng-show=\"localState.activitiesLoaded && localState.filterActivities.length==0\" style=\"text-align: center;\" class=\"myCenter\">\n" +
    "    {{::'page.hotel.noActualActivities' | translate}} in {{::showHotel.name}}\n" +
    "  </div>\n" +
    "  \n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_hotelList.html",
    "<div id=\"minPaddingPage\" class=\"bg-light lter wrapper-md\" ng-controller=\"HotelListController\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "  \n" +
    "  <div class=\"wrapper-md\">\n" +
    "    <div class=\"row\">\n" +
    "      <div>\n" +
    "        <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "        <div class=\"wrapper text-center\" style=\"padding-top: 0px;\">\n" +
    "\n" +
    "          <div style=\"text-align: left; margin-top: 10px;\">\n" +
    "\n" +
    "            <!--<div style=\"white-space: pre-line;\">{{::'page.hotel.hotelFilterLabel' | translate}}:</div>-->\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "          <hr/>\n" +
    "          <span class=\"hotelicoHeader\" ng-show=\"localState.filterHotels.length>0 && localState.selectedCityHotel\">\n" +
    "              <span ng-show=\"localState.selectedFilterHotel\">{{::'page.hotel.activityList' | translate}}  in {{localState.selectedFilterHotel.name}}</span>\n" +
    "              <span ng-show=\"!localState.selectedFilterHotel && localState.selectedCityHotel\">{{::'page.hotel.hotels' | translate}}  in {{localState.selectedCityHotel.city}}</span>\n" +
    "            :\n" +
    "          </span>\n" +
    "        </div>\n" +
    "\n" +
    "        <div id=\"hotelPart\" ng-controller=\"hotelCtrl\">\n" +
    "          \n" +
    "          <div ng-repeat=\"(key, showHotel) in localState.filterHotels | orderBy: 'id':true  track by showHotel.id\" >\n" +
    "  \n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelPreviewBlock.html' \" />\n" +
    "  \n" +
    "          </div>\n" +
    "\n" +
    "          <div class=\"nothingFoundMsg\" ng-show=\"localState.noHotelFound\">{{::'page.hotel.noHotelFound' | translate}}</div>\n" +
    "\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_hotelLogin.html",
    "<div ng-controller=\"LoginController\" ng-init=\"\n" +
    "app.settings.container = false; \n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\"  class=\"myCenter hotelLoginBody\" style=\"background-color: #fff;\">\n" +
    "\n" +
    "    <div class=\"panel panel-default\" id=\"checkinPanel\" style=\"/*border-color: transparent;*/border-color: #fff;box-shadow:none;-webkit-box-shadow:none;/*background-color: inherit;*/margin-bottom: 0;\"> <!-- ng-show=\"localState.ready\" -->\n" +
    "\n" +
    "        <!--<div class=\"panel-heading\">-->\n" +
    "        <!--<h3 class=\"panel-title\">{{'page.checkin.title' | translate}} ({{'page.checkin.askReception' | translate}})</h3>-->\n" +
    "        <!--</div>-->\n" +
    "\n" +
    "        <div class=\"panel-body\" id=\"checkinBody\" style=\"border-color: transparent; padding: 5px;\">\n" +
    "    \n" +
    "    <!--<div ng-style=\" isSmartDevice && {'width': '100%'}\"  class=\"container w-xxl w-auto-xs myFlex\" style=\"justify-content: space-between; padding: 0;\">-->\n" +
    "        <!--<div class=\"text-center\" ui-sref=\"app.checkin\"  style=\"padding: 5px;\">-->\n" +
    "            <!--<a id=\"userLoginId\" ui-sref=\"app.checkin\"  style=\"white-space: nowrap;\">{{'page.login.userLoginPreview' | translate }} <span style=\"text-decoration: underline;\">{{'page.login.userLoginLink' | translate}}</span></a>-->\n" +
    "        <!--</div>-->\n" +
    "\n" +
    "        <!--<div style=\"margin-top: 5px;\" isteven-multi-select id=\"selectSystemLanguages\" input-model=\"localState.availableSystemLanguages\" output-model=\"localState.selectSystemLanguage\" button-label=\"icon langKeyLabel\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\" selection-mode=\"single\"></div>-->\n" +
    "\n" +
    "    <!--</div>-->\n" +
    "\n" +
    "\n" +
    "    <div class=\"wrapper text-center\" ng-if=\"::!isSmartDevice\">\n" +
    "        <img src=\"angulr/img/build/logo/logoFull2.png\" style=\"height:35px;\"/>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"myCenter text-orange\" data-ng-include=\" 'angulr/tpl/hotel/blocks/showCarousel.html' \" />\n" +
    "\n" +
    "\n" +
    "    <div class=\"container\" style=\"width: 90%;\">\n" +
    "        <!--<a href class=\"navbar-brand block m-t\">{{app.name}}</a>-->\n" +
    "        <div class=\"m-b-sm m-t-sm\">\n" +
    "\n" +
    "            <!--<div class=\"wrapper loginWellcomeMsg text-center\" style=\"padding: 0px; color: #f1592a;\">-->\n" +
    "                <!--{{'page.login.wellcomeHotelMsg' | translate}}-->\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "            <!--<div class=\"wrapper text-center loginHeaderItems\" style=\"padding-left: 0; padding-right: 0;\">-->\n" +
    "                <!--&lt;!&ndash;<a href=\"\" ng-click=\"setLoginTabState('guest')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.guest}\">{{ 'page.login.guestTitle' | translate}}</a>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<span style=\"padding: 5px;\">|</span>&ndash;&gt;-->\n" +
    "                <!--<a href=\"\" ng-click=\"setLoginTabState('login')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.login}\">{{ 'page.login.loginTitle' | translate}}</a>-->\n" +
    "                <!--<span style=\"padding: 5px;\">|</span>-->\n" +
    "                <!--<a href=\"\" ng-click=\"setLoginTabState('signup')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.signup}\">{{ 'page.login.signupTitle' | translate}}</a>-->\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "            <!--<div class=\"panel panel-default\" >-->\n" +
    "                <!-- -->\n" +
    "                <!--<div class=\"panel-body\" ng-show=\"loginTabState.login\">-->\n" +
    "                    <!--&lt;!&ndash; TODO Eugen: create Form hier!!! autofill&ndash;&gt;-->\n" +
    "                    <!--<form name=\"hotelicoLoginForm\"  autocomplete=\"on\" class=\"form-validation\" ng-submit=\"loginPassword()\">-->\n" +
    "\n" +
    "                        <!--<div class=\"list-group list-group-sm\">-->\n" +
    "                            <!--<div class=\"list-group-item\">-->\n" +
    "                                <!--<input type=\"email\" id=\"hotelicoLoginMail\" name=\"hotelicoLoginMail\" placeholder=\"{{::'profile.email' | translate}}\" class=\"form-control no-border\" ng-model=\"localState.checkUser.email\" required autocomplete=\"on\">-->\n" +
    "                            <!--</div>-->\n" +
    "                            <!--<div class=\"list-group-item\">-->\n" +
    "                                <!--<input type=\"password\" id=\"hotelicoLoginPassword\" name=\"hotelicoLoginPassword\" placeholder=\"{{::'profile.password' | translate}}\" class=\"form-control no-border\" ng-model=\"localState.checkUser.password\" required autocomplete=\"on\">-->\n" +
    "                            <!--</div>-->\n" +
    "                            <!--<button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\">{{::'page.login.doLogin'-->\n" +
    "                                <!--| translate }}</button>-->\n" +
    "                        <!--</div>-->\n" +
    "                    <!--</form>-->\n" +
    "\n" +
    "                    <!--<div class=\"text-center m-t m-b\"><a ui-sref=\"app.forgotpwd\">Forgot password?</a></div>-->\n" +
    "\n" +
    "                <!--</div>-->\n" +
    "\n" +
    "                <!--<div class=\"panel-body\"  ng-show=\"loginTabState.signup\">-->\n" +
    "\n" +
    "                    <!--<a ng-click=\"clickLoading('app.hotelRegister')\" class=\"btn btn-lg btn-orange btn-block\">{{::'page.login.hotelRegister' | translate}}</a>-->\n" +
    "\n" +
    "                <!--</div>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/socialLogin.html' \" />\n" +
    "\n" +
    "            <div class=\"panel panel-default\" style=\"margin-top: 10px;\">\n" +
    "\n" +
    "                <md-content>\n" +
    "                    <md-tabs md-stretch-tabs=\"always\" md-selected=\"hotelLoginTabIndex\">\n" +
    "\n" +
    "                        <!--<md-tab label=\"{{ 'page.login.guestTitle' | translate}}\" id=\"hotelLoginTab0\" class=\"\" aria-controls=\"hotelLoginTab0-content\" >-->\n" +
    "                            <!--<md-content class=\"md-padding\">-->\n" +
    "\n" +
    "                                <!--&lt;!&ndash; START GUEST LOGIN (1) &ndash;&gt;-->\n" +
    "                                <!--&lt;!&ndash;ng-show=\"loginTabState.guest\"&ndash;&gt;-->\n" +
    "                                <!--<div  class=\"\" >-->\n" +
    "\n" +
    "                                    <!--<form role=\"form\" name=\"loginGuestForm\" id=\"loginGuestForm\" class=\"form-validation\" ng-submit=\"guestRegister();\">-->\n" +
    "\n" +
    "                                        <!--<div class=\"list-group list-group-sm\">-->\n" +
    "                                            <!--<div class=\"list-group-item text-center\" style=\"height: 52px;padding: 0;margin: 0\">-->\n" +
    "                                                <!--<div style=\"width: 250px;display: table;margin: 0 auto;padding-top: 7px;\">-->\n" +
    "                                                    <!--<div style=\"width: 100px; float: left;\">-->\n" +
    "                                                        <!--<input stop-event=\"touchend\" type=\"text\" placeholder=\"{{' profile.firstName' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.guestUser.firstName\" ng-focus=\"scrollModalDown();\" required>-->\n" +
    "                                                    <!--</div>-->\n" +
    "                                                    <!--<div style=\"float: left; margin-top: 7px; margin-left: 10px;\">-->\n" +
    "                                                        <!--<label class=\"i-checks\">-->\n" +
    "                                                            <!--&lt;!&ndash;ng-required=\"!localState.guestUser.sex\"&ndash;&gt;-->\n" +
    "                                                            <!--<input type=\"radio\" value=\"m\" ng-model=\"localState.guestUser.sex\" ><i></i> {{ 'profile.gender-m' | translate}} &nbsp;-->\n" +
    "                                                        <!--</label>-->\n" +
    "                                                        <!--<label class=\"i-checks\">-->\n" +
    "                                                            <!--<input type=\"radio\" value=\"w\" ng-model=\"localState.guestUser.sex\" ><i></i> {{ 'profile.gender-f' | translate}}-->\n" +
    "                                                        <!--</label>-->\n" +
    "                                                    <!--</div>-->\n" +
    "                                                <!--</div>-->\n" +
    "\n" +
    "                                            <!--</div>-->\n" +
    "\n" +
    "                                            <!--<button type=\"submit\" class=\"btn btn-sm btn-orange btn-social btn-block\" style=\"text-align: center;padding:6px; padding-left: 0;\">{{' page.login.guestRegister'-->\n" +
    "                                                <!--| translate }}</button>-->\n" +
    "                                        <!--</div>-->\n" +
    "\n" +
    "                                        <!--&lt;!&ndash;<hr>&ndash;&gt;-->\n" +
    "\n" +
    "                                        <!--&lt;!&ndash;<div style=\"text-align: justify;\" class=\"text-center\">&ndash;&gt;-->\n" +
    "                                        <!--&lt;!&ndash;{{' page.login.guestMsg' | translate }}&ndash;&gt;-->\n" +
    "                                        <!--&lt;!&ndash;<br/>&ndash;&gt;-->\n" +
    "                                        <!--&lt;!&ndash;{{' page.login.guestMsg2' | translate }}&ndash;&gt;-->\n" +
    "                                        <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "\n" +
    "                                    <!--</form>-->\n" +
    "\n" +
    "                                <!--</div>-->\n" +
    "\n" +
    "                                <!--&lt;!&ndash; END GUEST LOGIN &ndash;&gt;-->\n" +
    "\n" +
    "                            <!--</md-content>-->\n" +
    "                        <!--</md-tab>-->\n" +
    "\n" +
    "                        <md-tab label=\"{{ 'page.login.loginTitle' | translate}}\" id=\"hotelLoginTab1\" aria-controls=\"hotelLoginTab1-content\" >\n" +
    "                            <md-content class=\"md-padding\" style=\"padding-top: 0; padding-bottom: 0;\">\n" +
    "\n" +
    "                                <!-- START LOGIN (2) -->\n" +
    "                                <!--ng-show=\"loginTabState.login\"-->\n" +
    "                                <div class=\"\" >\n" +
    "                                    <!-- TODO Eugen: create Form hier!!! autofill-->\n" +
    "                                    <form role=\"form\" name=\"hotelicoLoginForm\"  autocomplete=\"on\" class=\"form-validation\" ng-submit=\"loginPassword()\">\n" +
    "\n" +
    "                                        <div class=\"list-group list-group-sm\" style=\"margin-top: 0; margin-bottom:0;\">\n" +
    "                                            <div class=\"list-group-item\">\n" +
    "                                                <input stop-event=\"touchend\" type=\"email\" id=\"hotelicoLoginMail\" name=\"hotelicoLoginMail\" placeholder=\"{{' profile.email' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.checkUser.email\" ng-focus=\"scrollModalDown();\" required autocomplete=\"on\">\n" +
    "                                            </div>\n" +
    "                                            <div class=\"list-group-item\">\n" +
    "                                                <input stop-event=\"touchend\" type=\"password\" id=\"hotelicoLoginPassword\" name=\"hotelicoLoginPassword\" placeholder=\"{{' profile.password' | translate}}\" class=\"focusModal form-control no-border\" ng-model=\"localState.checkUser.password\" ng-focus=\"scrollModalDown();\" required autocomplete=\"on\">\n" +
    "                                            </div>\n" +
    "                                            <button type=\"submit\" class=\"btn btn-sm btn-orange btn-block\" style=\"padding: 6px;\">{{' page.login.doLogin'\n" +
    "                                                | translate }}</button>\n" +
    "                                        </div>\n" +
    "                                    </form>\n" +
    "\n" +
    "\n" +
    "                                </div>\n" +
    "                                <!-- END LOGIN DIV -->\n" +
    "\n" +
    "                            </md-content>\n" +
    "                        </md-tab>\n" +
    "\n" +
    "                        <md-tab label=\"{{ 'page.login.signupTitle' | translate}}\" id=\"hotelLoginTab2\" aria-controls=\"hotelLoginTab2-content\" >\n" +
    "                            <md-content class=\"md-padding\">\n" +
    "\n" +
    "                                <!-- START REGISTER (3) -->\n" +
    "                                <!--ng-show=\"hotelLoginTabState.signup\"-->\n" +
    "                                <div class=\"panel-body\"  >\n" +
    "\n" +
    "                                    <!--<a ng-click=\"ok('app.register')\" class=\"btn btn-sm btn-orange btn-block\" style=\"padding: 6px;\">{{' page.login.goToSignUp' | translate}}</a>-->\n" +
    "                                    <a ng-click=\"clickLoading('app.hotelRegister')\" class=\"btn btn-sm btn-orange btn-block\">{{::'page.login.hotelRegister' | translate}}</a>\n" +
    "\n" +
    "                                </div>\n" +
    "\n" +
    "                                <!-- END REGISTER -->\n" +
    "\n" +
    "                            </md-content>\n" +
    "                        </md-tab>\n" +
    "                    </md-tabs>\n" +
    "                </md-content>\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"text-center m-t m-b\"><a ui-sref=\"app.forgotpwd\">Forgot password?</a></div>\n" +
    "\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/impressumBottomBlock.html' \" />\n" +
    "\n" +
    "        <!--<div class=\"text-center\" ng-include=\"'angulr/tpl/blocks/page_footer.html'\">-->\n" +
    "        <!--&lt;!&ndash;{% include 'blocks/page_footer.html' %}&ndash;&gt;-->\n" +
    "        <!--</div>-->\n" +
    "    </div>\n" +
    "    </div>\n" +
    "    </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_hotelPreview.html",
    "<div data-ng-include=\" 'angulr/tpl/hotel/blocks/dealSubTabBlock.html' \" style=\"display: flex;\"/>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" ng-controller=\"HotelPreviewController\" class=\"bg-light lter wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "localState.showActivityHotelLink = false;\n" +
    "\" style=\"\">\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/notLoggedInfoBlock.html' \" />\n" +
    "    \n" +
    "    <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/pushRequest.html' \" />-->\n" +
    "    \n" +
    "\n" +
    "    <div class=\"wrapper-md\" style=\"padding-top: 0;\">\n" +
    "    <div class=\"row\">\n" +
    "      <!--<div class=\"col-sm-9\">-->\n" +
    "      <div>\n" +
    "        <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "        \n" +
    "        <div class=\"wrapper text-center\" style=\"padding-top: 0px; display: none;\">\n" +
    "\n" +
    "          <div  style=\"text-align: left;\">\n" +
    "\n" +
    "            {{::'page.hotel.activityFilterLabel' | translate}}:\n" +
    "            <div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "          <hr/>\n" +
    "          \n" +
    "          <span class=\"hotelicoHeader\" ng-show=\"localState.filterActivities.length>0\">\n" +
    "              <span class=\"orange-text\">{{::'page.hotel.activities' | translate}}</span> \n" +
    "              <span ng-show=\"localState.selectedFilterHotel\"> in {{localState.selectedFilterHotel.name}}</span>\n" +
    "              <span ng-show=\"!localState.selectedFilterHotel && localState.selectedCityHotel\"> in {{localState.selectedCityHotel.city}}</span>\n" +
    "              :\n" +
    "          </span>\n" +
    "        </div>\n" +
    "        \n" +
    "        <!-- Show Hotel -->\n" +
    "        \n" +
    "        <div id=\"hotelPart\" ng-show=\"hotelState.profileData.checkedIn\" ng-controller=\"hotelCtrl\">\n" +
    "\n" +
    "          <!--&lt;!&ndash; START HOTEL accordion &ndash;&gt;-->\n" +
    "\n" +
    "          <!--<div class=\"staffContent\" ng-if=\"(hotelState.profileData.hotelStaff  && hotelState.profileData.hotelId==showHotel.id|| hotelState.profileData.admin)\">-->\n" +
    "            <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/editHotelAccardion.html' \" />-->\n" +
    "          <!--</div>-->\n" +
    "\n" +
    "          <!--&lt;!&ndash; / END hotel accordion &ndash;&gt;-->\n" +
    "\n" +
    "          <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />-->\n" +
    "\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/fullCheckinRequest.html' \" />\n" +
    "          \n" +
    "        </div>\n" +
    "\n" +
    "        <!-- ACTIVITES START -->\n" +
    "\n" +
    "\n" +
    "        <!-- LIST ALL ACTIVITIES -->\n" +
    "        <div id=\"activityPart\" ng-controller=\"activityCtrl\">\n" +
    "\n" +
    "          <div ng-show=\"localState.filterActivities.length>0\" style=\"text-align: center;\" class=\"hotelicoHeader orange-text myCenter\">\n" +
    "            {{::'page.hotel.actualActivities' | translate}} in {{::showHotel.name}}:\n" +
    "            <a name=\"activitiesAnchor\"/>\n" +
    "          </div>\n" +
    "          \n" +
    "          <div class=\"list-group list-group-lg list-group-sp\">\n" +
    "            <div ng-repeat=\"showActivity in localState.filterActivities | orderBy: 'validTo'  track by showActivity.initId\">\n" +
    "\n" +
    "              <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showActivityBlock.html' \" />\n" +
    "\n" +
    "            </div>\n" +
    "\n" +
    "            <!--<div class=\"nothingFoundMsg\" ng-show=\"localState.noActivityFound\">{{::'page.activities.noActivityFound' | translate}}</div>-->\n" +
    "\n" +
    "            <div ng-show=\"localState.activitiesLoaded && localState.filterActivities.length==0\" style=\"text-align: center;\" class=\"myCenter\">\n" +
    "              {{::'page.hotel.noActualActivities' | translate}} in {{::showHotel.name}}\n" +
    "            </div>\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "        </div>\n" +
    "\n" +
    "        <!-- END ACTIVITES START -->\n" +
    "        \n" +
    "      </div>\n" +
    "\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_hotelRegister.html",
    "<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "  <h1 class=\"m-n font-thin h3\">Hotel Anmelden</h1>\n" +
    "</div>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" class=\"wrapper-md\" ng-controller=\"HotelRegisterController\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "\">\n" +
    "\n" +
    "  <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "\n" +
    "  <div class=\"wrapper-md\">\n" +
    "    <tabset class=\"tab-container\" ng-init=\"steps={percent:20, step1:true, step2:false, step3:false}\">\n" +
    "      \n" +
    "      <tab heading=\"Hotel-Information\" active=\"steps.step1\" select=\"steps.percent=10\">\n" +
    "        \n" +
    "        <div class=\"m-b\" style=\"text-align: right;\">\n" +
    "          \n" +
    "          <button type=\"submit\" ng-class=\"{'btn-orange' : showHotel.name && showHotel.currentHotelAccessCode, 'btn-default' : !showHotel.name || !showHotel.currentHotelAccessCode ,'btn btn-rounded' : true}\" ng-disabled=\"!showHotel.name || !showHotel.currentHotelAccessCode\" ng-click=\"steps.step2=true\">Next</button>\n" +
    "          \n" +
    "        </div>\n" +
    "\n" +
    "        <div style=\"text-align: center; font-weight: bold;\">\n" +
    "          <p class=\"m-b text-orange\" ng-show=\"showHotel.id<=0\">{{'page.register.hotelRegisterIntro' | translate}}</p>\n" +
    "\n" +
    "        </div>\n" +
    "\n" +
    "\n" +
    "        <!--<progressbar value=\"steps.percent\" class=\"progress-xs\" type=\"success\"></progressbar>-->\n" +
    "        <!--<h4>Validate Form</h4>-->\n" +
    "        <!--<label class=\"control-label\">{{'page.activities.activityLogo' | translate}}</label>-->\n" +
    "\n" +
    "        <!--<div  ng-cloak>-->\n" +
    "          <!--<div>-->\n" +
    "            <!--<div class=\"well\">-->\n" +
    "              <!--&lt;!&ndash;<form action=\"\" class=\"dropzone\" dropzone=\"\" id=\"dropzone\">&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<div class=\"dz-default dz-message\">&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;</div>&ndash;&gt;-->\n" +
    "              <!--&lt;!&ndash;</form>&ndash;&gt;-->\n" +
    "            <!--</div>-->\n" +
    "            <!--<div class=\"pull-right\" style=\"z-index: 999;margin-top: -40px;\">-->\n" +
    "              <!--&lt;!&ndash;<button class=\"btn btn-success\" ng-click=\"uploadFile()\">Upload Avatar</button>&ndash;&gt;-->\n" +
    "              <!--<button class=\"btn btn-danger resetDropzone\" ng-click=\"reset()\" ng-show=\"fileAdded && !hideReset\">{{'profile.removeAvatar' | translate}}</button>-->\n" +
    "            <!--</div>-->\n" +
    "          <!--</div>-->\n" +
    "        <!--</div>-->\n" +
    "        \n" +
    "        <!--<form name=\"step1\" class=\"form-validation\">-->\n" +
    "          \n" +
    "          <!--<div class=\"m-t m-b\">-->\n" +
    "            <!--<button type=\"submit\" ng-class=\"{'btn-orange' : showHotel.name && showHotel.currentHotelAccessCode, 'btn-default' : !showHotel.name || !showHotel.currentHotelAccessCode ,'btn btn-rounded' : true}\" ng-disabled=\"!showHotel.name || !showHotel.currentHotelAccessCode\" ng-click=\"steps.step2=true\">Next</button>-->\n" +
    "          <!--</div>-->\n" +
    "          \n" +
    "          <!--<p>Hotel Name:</p>-->\n" +
    "          <!--<input type=\"email\" name=\"email\" class=\"form-control\" ng-model=\"localState.newHotel.name\" required ng-change=\"step1.email.$valid ? (steps.percent=30) : (steps.percent=20)\">-->\n" +
    "\n" +
    "          <!--<p>Hotel email:</p>-->\n" +
    "          <!--<input type=\"email\" name=\"email\" class=\"form-control\" ng-model=\"localState.newHotel.email\" required ng-change=\"step1.email.$valid ? (steps.percent=30) : (steps.percent=20)\">-->\n" +
    "          <!-- -->\n" +
    "          <!--<p class=\"m-t\">Hotel website:</p>-->\n" +
    "          <!--<input type=\"url\" name=\"url\" placeholder=\"http://\" class=\"form-control\" ng-model=\"localState.newHotel.website\" required>-->\n" +
    "    \n" +
    "\n" +
    "          <button ng-click=\"openModal('editHotel', showHotel.id)\" style=\"margin-bottom: 15px;\"  class=\"btn btn-lg btn-block btn-blue\">{{localState.editHotelAccordionTitle}}</button>\n" +
    "\n" +
    "          <!-- / END hotel accordion -->\n" +
    "          <b>{{::'system.editPreview' | translate}}:</b>\n" +
    "\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelBlock.html' \" />\n" +
    "          <!--<div class=\"m-t m-b\">-->\n" +
    "            <!--<button type=\"submit\" ng-class=\"{'btn-orange' : localState.newHotel.name && localState.newHotel.currentHotelAccessCode, 'btn-default' : !localState.newHotel.name || !localState.newHotel.currentHotelAccessCode ,'btn btn-rounded' : true}\" ng-disabled=\"step1.$invalid\" ng-click=\"steps.step2=true\">Next</button>-->\n" +
    "          <!--</div>-->\n" +
    "          \n" +
    "        <!--</form>-->\n" +
    "      </tab>\n" +
    "      \n" +
    "      \n" +
    "      \n" +
    "      <tab heading=\"Staff Information\" disabled=\"showHotel.id<=0\" active=\"steps.step2\" select=\"steps.percent=60\">\n" +
    "        \n" +
    "         <form name=\"step2\" class=\"form-validation\" ng-submit=\"submitNewStaffHotel();\">\n" +
    "          \n" +
    "            <p class=\"m-b\">{{:: 'page.register.staffRegisterIntro' | translate}}</p>\n" +
    "          <!--<progressbar value=\"steps.percent\" class=\"progress-xs\" type=\"success\"></progressbar>-->\n" +
    "\n" +
    "          <!--<div class=\"panel panel-default\">-->\n" +
    "\n" +
    "\n" +
    "\n" +
    "\n" +
    "\n" +
    "\n" +
    "            <!--<div class=\"panel-heading font-bold\">-->\n" +
    "              <!--Personal Information-->\n" +
    "            <!--</div>-->\n" +
    "\n" +
    "            <!--<div class=\"panel-body\">-->\n" +
    "\n" +
    "              <!--<div class=\"form-group\">-->\n" +
    "              <!--<label class=\"col-sm-2 control-label\">Status</label>-->\n" +
    "              <!--<div class=\"col-sm-10\">-->\n" +
    "              <!--<input type=\"text\" class=\"form-control rounded\" placeholder=\"{{'status' | translate}}\"  ng-model=\"localState.newStaff.status\">-->\n" +
    "              <!--<span class=\"help-block m-b-none\">A block of help text that breaks onto a new line and may extend beyond one line.</span>-->\n" +
    "\n" +
    "              <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<div class=\"form-group\">-->\n" +
    "                <!--<label class=\"col-sm-2 control-label\"></label>-->\n" +
    "                <div>\n" +
    "                  <label class=\"i-checks\">\n" +
    "                    <input type=\"radio\" value=\"m\" ng-model=\"localState.newStaff.sex\" ng-required=\"!localState.newStaff.sex\"><i></i> Herr &nbsp;\n" +
    "                  </label>\n" +
    "                  <label class=\"i-checks\">\n" +
    "                    <input type=\"radio\" value=\"w\" ng-model=\"localState.newStaff.sex\" ng-required=\"!localState.newStaff.sex\"><i></i> Frau\n" +
    "                  </label>\n" +
    "                </div>\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "              <!--<div class=\"form-group\">-->\n" +
    "                <label class=\"control-label\">{{'firstName' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <!--<div class=\"col-sm-10\">-->\n" +
    "                  <input type=\"text\" class=\"form-control\" placeholder=\"{{'firstName' | translate}}\"  ng-model=\"localState.newStaff.firstName\" required>\n" +
    "                <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<div class=\"form-group\">-->\n" +
    "                <label class=\"control-label\">{{'lastName' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <!--<div class=\"col-sm-10\">-->\n" +
    "                  <input type=\"text\" class=\"form-control\" placeholder=\"{{'lastName' | translate}}\" ng-model=\"localState.newStaff.lastName\" required>\n" +
    "                <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "              <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "          <label class=\"control-label\">City<span class=\"requiredField\">*</span></label>\n" +
    "          <!--<div class=\"col-sm-10\">-->\n" +
    "          <input type=\"text\" class=\"form-control\" placeholder=\"{{'city' | translate}}\"  ng-model=\"localState.newStaff.city\" required>\n" +
    "          <!--</div>-->\n" +
    "        \n" +
    "          <!--<label class=\"control-label\">Geburtstag</label>-->\n" +
    "          <!--<br/>-->\n" +
    "          <!--<div>-->\n" +
    "\n" +
    "            <!--<a editable-date=\"staffBirthday\">{{ getStaffBirthdayString() || 'system.selectDate' | translate }}</a>-->\n" +
    "\n" +
    "          <!--</div>-->\n" +
    "        <br/>\n" +
    "\n" +
    "        <!--<div class=\"form-group\">-->\n" +
    "          <label class=\"control-label\">{{'language' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "        <br/>\n" +
    "\n" +
    "        <div>\n" +
    "            <div isteven-multi-select id=\"selectLanguages\"   input-model=\"localState.languages\" output-model=\"localState.newStaff.languages\" button-label=\"icon\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\"></div>\n" +
    "          </div>\n" +
    "        <!--</div>-->\n" +
    "          \n" +
    "         \n" +
    "          <!--<p>Your age:</p>-->\n" +
    "          <!--<input type=\"number\" name=\"age\" class=\"form-control\" ng-model=\"age\" required>-->\n" +
    "          <br>\n" +
    "          <br>\n" +
    "          <p>Hotel Staff email:<span class=\"requiredField\">*</span></p>\n" +
    "          <input type=\"email\" name=\"email\" class=\"form-control\" ng-model=\"localState.newStaff.email\" required ng-change=\"step1.email.$valid ? (steps.percent=30) : (steps.percent=20)\" required>\n" +
    "\n" +
    "          <p class=\"m-t\">Staff {{'password' | translate}}:<span class=\"requiredField\">*</span></p>\n" +
    "          <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputPassword\" ng-model=\"localState.newStaff.password\" placeholder=\"{{'password' | translate}}\" required>\n" +
    "\n" +
    "          <p class=\"m-t\">Staff {{'confirmPassword' | translate}}:</p>\n" +
    "          <input type=\"password\" name=\"confirmPassword\" class=\"form-control\" id=\"inputConfirmPassword\" ng-model=\"localState.newStaff.confirmPassword\" placeholder=\"{{'confirmPassword' | translate}}\" required>\n" +
    "\n" +
    "\n" +
    "          <br/>\n" +
    "\n" +
    "          <div class=\"checkbox m-b-md m-t-none\">\n" +
    "            <label class=\"i-checks\">\n" +
    "              <input type=\"checkbox\" ng-model=\"localState.agree\" required><i></i> <span style=\"margin-left: 5px;\">{{'page.register.agree' | translate}}</span> <a href ui-sref=\"app.agb\" style=\"text-decoration: underline;\">{{'page.register.termsAndConditions' | translate}}</a>\n" +
    "            </label>\n" +
    "          </div>\n" +
    "          <br>\n" +
    "          <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "          <div class=\"m-t m-b\">\n" +
    "              \n" +
    "            <!--<button type=\"button\" class=\"btn btn-default btn-rounded\" ng-click=\"steps.step1=true\">Prev</button>-->\n" +
    "            <!--<button type=\"submit\" ng-disabled=\"step2.$invalid\" class=\"btn btn-default btn-rounded\" ng-click=\"steps.step3=true\">Next</button>-->\n" +
    "            <button type=\"button\" class=\"btn btn-default btn-rounded\" ng-click=\"steps.step1=true\">Prev</button>\n" +
    "            <button type=\"submit\" class=\"btn btn-orange btn-rounded\" ng-disabled=\"step2.$invalid\">{{'page.register.doRegister' | translate }}</button>\n" +
    "          </div>\n" +
    "          \n" +
    "        </form>\n" +
    "      </tab>\n" +
    "      \n" +
    "      \n" +
    "      \n" +
    "      \n" +
    "      <!--<tab heading=\"{{'profile.section.loginData' | translate}}\" disabled=\"step2.$invalid\" active=\"steps.step3\" select=\"steps.percent=60\">-->\n" +
    "        <!--<p class=\"m-b\">Congraduations! You got the last step.</p>-->\n" +
    "        <!--<progressbar value=\"steps.percent\" class=\"progress-xs\" type=\"success\"></progressbar>-->\n" +
    "        <!--<p>Just one click to finish it.</p>-->\n" +
    "        <!-- -->\n" +
    "        <!--<p>Hotel Staff email:</p>-->\n" +
    "        <!--<input type=\"email\" name=\"email\" class=\"form-control\" ng-model=\"email\" required ng-change=\"step1.email.$valid ? (steps.percent=30) : (steps.percent=20)\">-->\n" +
    "\n" +
    "        <!--<p class=\"m-t\">Staff {{'password' | translate}}:</p>-->\n" +
    "        <!--<input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputPassword\" ng-model=\"localState.newStaff.password\" placeholder=\"{{'password' | translate}}\">-->\n" +
    "\n" +
    "        <!--<p class=\"m-t\">Staff {{'confirmPassword' | translate}}:</p>-->\n" +
    "        <!--<input type=\"password\" name=\"confirmPassword\" class=\"form-control\" id=\"inputConfirmPassword\" ng-model=\"localState.newStaff.confirmPassword\" placeholder=\"{{'confirmPassword' | translate}}\">-->\n" +
    "\n" +
    "\n" +
    "        <!--<div class=\"m-t m-b\">-->\n" +
    "          <!--<button type=\"button\" class=\"btn btn-default btn-rounded\" ng-click=\"steps.step2=true\">Prev</button>-->\n" +
    "          <!--<button type=\"button\" class=\"btn btn-default btn-rounded\" ng-click=\"steps.percent=100\">Click me to Finish</button>-->\n" +
    "        <!--</div>-->\n" +
    "      <!--</tab>-->\n" +
    "    </tabset>\n" +
    "  </div>\n" +
    "\n" +
    "  <!--<div class=\"container w-xxl w-auto-xs ng-scope\">-->\n" +
    "\n" +
    "    <!-- -->\n" +
    "    <!--&lt;!&ndash;<button type=\"submit\" class=\"btn btn-lg btn-primary btn-block\" ng-click=\"signup()\" ng-disabled='form.$invalid'><span translate=\"hotel.login.CREATEACCOUNT\">Create an account</span></button>&ndash;&gt;-->\n" +
    "    <!--<button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\" ng-click=\"register()\"   >{{'page.register.doRegister' | translate }}</button>-->\n" +
    "    <!--<div class=\"line line-dashed\"></div>-->\n" +
    "    <!--<p class=\"text-center\"><small>{{'page.register.alreadyHaveAccount' | translate}}</small></p>-->\n" +
    "    <!--<a ui-sref=\"access.login\" class=\"btn btn-lg btn-default btn-block\">{{'page.login.doLogin' | translate }}</a>-->\n" +
    "  <!--</div>-->\n" +
    "  <!--</form>-->\n" +
    "</div>\n" +
    "\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_impressum.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideFooter = true;\n" +
    "  app.header.showTab = false;\n" +
    "  app.header.showBackArrow = true;\n" +
    "  app.hideAside = true;\n" +
    "  app.rootSettings.darkBg = true;\n" +
    "  rootScope.hideAside();\n" +
    "  \">\n" +
    "\n" +
    "	<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "		<h1 class=\"m-n font-thin h3\">hotelico.de Impressum</h1>\n" +
    "	</div>\n" +
    "	<div class=\"wrapper-md\">\n" +
    "		<div class=\"row\">\n" +
    "			<div style=\"padding: 5px;\">\n" +
    "				<!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "				Unternehmensinformationen\n" +
    "				<br/>				\n" +
    "				<br/>\n" +
    "\n" +
    "				Sie befinden sich auf einem Informationsangebot des Hotelico-Konsortiums. Dies ist eine\n" +
    "				Arbeitsgemeinschaft in b&uuml;rgerlich-rechtlicher Rechtsform, zusammengesetzt aus den Gr&uuml;ndern\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Sergej Neustadt und\n" +
    "				<br/>\n" +
    "				Eugen Fanshil\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				sowie der\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Favoso Consulting GmbH & Co Team KG,<br>\n" +
    "				als im Au&szlig;enverh&auml;ltnis verantwortlichen Federf&uuml;hrer.\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Kontakte\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Favoso Consulting GmbH & Co. Team KG\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Postanschrift:\n" +
    "				<br/>\n" +
    "				August-Schanz-Str. 28\n" +
    "				<br/>\n" +
    "				D-60433 Frankfurt am Main\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				vertreten durch die Komplement&auml;rin:\n" +
    "				<br/>\n" +
    "				Favoso Consulting GmbH\n" +
    "				<br/>\n" +
    "				ebenda\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				E-mail: <a mailto=\"info@favoso.de\">info@favoso.de</a>				\n" +
    "				<br/>\n" +
    "\n" +
    "				Phone: +49 69 15322 8900\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Vertretungsberechtigte Gesch&auml;ftsf&uuml;hrer:\n" +
    "				Michael Schneider\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Verantwortlich i.S.d. &sect;&sect; 5 TMG, 55 RStV:\n" +
    "				<br/>\n" +
    "				Sergej Neustadt\n" +
    "\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Registergericht:\n" +
    "				<br/>\n" +
    "				AG Frankfurt/Main, HRA 48698\n" +
    "				<br/>\n" +
    "				(Registergericht der Komplement&auml;rin: AG Frankfurt/Main, HRB 103435)\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Steuer-Nr. der Komplement&auml;rin:\n" +
    "				<br/>\n" +
    "				220/5801/0905\n" +
    "				\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Urheberrecht:\n" +
    "				<br>\n" +
    "				<br>\n" +
    "				Jegliche Vervielf&auml;ltigung der Inhalte dieser Website und anderer Telemedien des Hotelico\n" +
    "				Konsortiums oder der Favoso Consulting GmbH & Co. Team KG, auch in Ausz&uuml;gen, bedarf unserer\n" +
    "				vorherigen schriftlichen Zustimmung.\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Sicherheit/Datenschutz:\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				Bitte senden Sie Anfagen, die den Datenschutz und die Sicherheit betreffen oder Anfragen aufgrund\n" +
    "				der Datenschutzerkl&auml;rung an diese Adresse:\n" +
    "				<br/>\n" +
    "				<br/>\n" +
    "\n" +
    "				<a mailto=\"info@hotelico.de\">info@hotelico.de</a>\n" +
    "			\n" +
    "			</div>\n" +
    "		</div>\n" +
    "\n" +
    "	</div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_language.html",
    "<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "    <h1 class=\"m-n font-thin h3\">{{'system.language' | translate}}</h1>\n" +
    "</div>\n" +
    "<div class=\"wrapper-md\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "    <!--{{'page.language.chooseLanguage' | translate }}: -->\n" +
    "    <!--<a editable-select=\"app.rootSettings.prefferedLanguage\" e-ng-options=\"s.value as s.text for s in languages\">-->\n" +
    "        <!--{{ app.rootSettings.prefferedLanguage }}-->\n" +
    "    <!--</a>-->\n" +
    "\n" +
    "    <!--<ui-select ng-model=\"page.language.chooseLanguage\" theme=\"bootstrap\">-->\n" +
    "        <!--<ui-select-match placeholder=\"Select a language in the list...\">{{$select.selected.name}}</ui-select-match>-->\n" +
    "        <!--<ui-select-choices repeat=\"item in people | filter: $select.search\">-->\n" +
    "            <!--<div ng-bind-html=\"item.name | highlight: $select.search\"></div>-->\n" +
    "            <!--<small ng-bind-html=\"item.email | highlight: $select.search\"></small>-->\n" +
    "        <!--</ui-select-choices>-->\n" +
    "    <!--</ui-select>-->\n" +
    "\n" +
    "    <!--<select ng-model=\"localState.selectedLanguageKey\" name=\"selectLang\" ng-change=\"submitLanguageChange()\" class=\"form-control m-b\">-->\n" +
    "        <!--<option default value=\"\" disabled>{{'page.language.chooseLanguage' | translate }}</option>        -->\n" +
    "        <!--<option value=\"en\">English</option>-->\n" +
    "        <!--<option value=\"de\">Deutsch</option>-->\n" +
    "        <!--&lt;!&ndash;<option value=\"ru\">Russian</option>&ndash;&gt;-->\n" +
    "    <!--</select>-->\n" +
    "\n" +
    "    <div isteven-multi-select id=\"selectSystemLanguagesFull\" input-model=\"localState.availableSystemLanguages\" output-model=\"localState.selectSystemLanguage\" button-label=\"icon name\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\" selection-mode=\"single\"></div>\n" +
    "\n" +
    "\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_login.html",
    "<div ng-controller=\"LoginController\" ng-init=\"\n" +
    "app.settings.container = false; \n" +
    "app.hideAside = true; \n" +
    "app.hideFooter = true;\n" +
    "app.rootSettings.darkBg = false;\n" +
    "\" \n" +
    " class=\"container w-xxl w-auto-xs\">\n" +
    "\n" +
    "  <div  ng-style=\" ::isSmartDevice && {'width': '100%'}\" class=\"container w-xxl w-auto-xs\" style=\"display: flex;  display: -webkit-flex;display: -moz-flex;display: -ms-flexbox; justify-content: space-between; padding: 0;\">\n" +
    "      \n" +
    "    <div class=\"text-center\" ui-sref=\"app.hotelLogin\" style=\"padding: 5px;\">\n" +
    "      <a id=\"hotelLoginId\" ui-sref=\"app.hotelLogin\" style=\"white-space: nowrap;\">{{'page.login.hotelLoginPreview' | translate }} <span style=\"text-decoration: underline;\">{{'page.login.hotelLoginLink' | translate}}</span></a>\n" +
    "    </div>\n" +
    "      \n" +
    "    <!--<div style=\"margin-top: 5px;\" isteven-multi-select id=\"selectSystemLanguages\" input-model=\"localState.availableSystemLanguages\" output-model=\"localState.selectSystemLanguage\" button-label=\"icon langKeyLabel\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\" selection-mode=\"single\"></div>-->\n" +
    "\n" +
    "  </div>\n" +
    "\n" +
    "\n" +
    "  <div class=\"wrapper text-center\">\n" +
    "    <img src=\"angulr/img/build/logo/logoFull2.png\" />\n" +
    "  </div>\n" +
    "\n" +
    "  <div ng-class=\"::isSmartDevice? 'wrapper-md' : 'container w-xxl w-auto-xs' \">\n" +
    "  <!--<a href class=\"navbar-brand block m-t\">{{::app.name}}</a>-->\n" +
    "    <div class=\"m-b-lg\">\n" +
    "      \n" +
    "      <div class=\"wrapper loginWellcomeMsg text-center\" style=\"padding: 0px; color: #f1592a;\">\n" +
    "        {{' page.login.wellcomeMsg' | translate}}\n" +
    "      </div>\n" +
    "      \n" +
    "      <div class=\"wrapper text-center loginHeaderItems\" style=\"padding-left: 0; padding-right: 0;\">\n" +
    "        <a href=\"\" ng-click=\"setLoginTabState('guest')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.guest}\">{{ 'page.login.guestTitle' | translate}}</a> \n" +
    "        <span style=\"padding: 5px;\">|</span>\n" +
    "        <a href=\"\" ng-click=\"setLoginTabState('login')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.login}\">{{ 'page.login.loginTitle' | translate}}</a>\n" +
    "        <span style=\"padding: 5px;\">|</span>\n" +
    "        <a href=\"\" ng-click=\"setLoginTabState('signup')\" ng-class=\"{'label bg-orange inline loginSelectedItem': loginTabState.signup}\">{{ 'page.login.signupTitle' | translate}}</a>\n" +
    "      </div>\n" +
    "      \n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "  \n" +
    "      <div class=\"panel panel-default\" >\n" +
    "        \n" +
    "        <div class=\"panel-body\" ng-show=\"loginTabState.guest\">\n" +
    "          \n" +
    "          <form name=\"loginGuestForm\" id=\"loginGuestForm\" class=\"form-validation\" ng-submit=\"guestRegister();\">\n" +
    "\n" +
    "            <div class=\"list-group list-group-sm\">\n" +
    "              <div class=\"list-group-item text-center\" style=\"height: 60px;padding: 0;margin: 0\">\n" +
    "                <div style=\"width: 250px;display: table;margin: 0 auto;padding-top: 7px;\">\n" +
    "                  <div style=\"width: 100px; float: left;\">\n" +
    "                      <input type=\"text\" placeholder=\"{{' profile.firstName' | translate}}\" class=\"form-control no-border\" ng-model=\"localState.guestUser.firstName\" required>\n" +
    "                  </div>\n" +
    "                  <div style=\"float: left; margin-top: 7px; margin-left: 10px;\">\n" +
    "                      <label class=\"i-checks\">\n" +
    "                        <input type=\"radio\" value=\"m\" ng-model=\"localState.guestUser.sex\" ng-required=\"!localState.guestUser.sex\"><i></i> {{ 'profile.gender-m' | translate}} &nbsp;\n" +
    "                      </label>\n" +
    "                      <label class=\"i-checks\">\n" +
    "                        <input type=\"radio\" value=\"w\" ng-model=\"localState.guestUser.sex\" ng-required=\"!localState.guestUser.sex\"><i></i> {{ 'profile.gender-f' | translate}}\n" +
    "                      </label>\n" +
    "                  </div>\n" +
    "                </div>\n" +
    "                  \n" +
    "              </div>\n" +
    "  \n" +
    "              <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\">{{' page.login.guestRegister'\n" +
    "                  | translate }}</button>\n" +
    "            </div>\n" +
    "              \n" +
    "            <hr>\n" +
    "  \n" +
    "            <div style=\"text-align: justify;\" class=\"text-center\">\n" +
    "                {{' page.login.guestMsg' | translate }}\n" +
    "                <br/>\n" +
    "                {{' page.login.guestMsg2' | translate }}\n" +
    "            </div>\n" +
    "          \n" +
    "        </form>\n" +
    "    \n" +
    "        </div>\n" +
    "      \n" +
    "        <div class=\"panel-body\" ng-show=\"loginTabState.login\">\n" +
    "          <!-- TODO Eugen: create Form hier!!! autofill-->\n" +
    "          <form name=\"hotelicoLoginForm\"  autocomplete=\"on\" class=\"form-validation\" ng-submit=\"loginPassword()\">\n" +
    "\n" +
    "            <div class=\"list-group list-group-sm\">\n" +
    "              <div class=\"list-group-item\">\n" +
    "                <input type=\"email\" id=\"hotelicoLoginMail\" name=\"hotelicoLoginMail\" placeholder=\"{{' profile.email' | translate}}\" class=\"form-control no-border\" ng-model=\"localState.checkUser.email\" required autocomplete=\"on\">\n" +
    "              </div>\n" +
    "              <div class=\"list-group-item\">\n" +
    "                <input type=\"password\" id=\"hotelicoLoginPassword\" name=\"hotelicoLoginPassword\" placeholder=\"{{' profile.password' | translate}}\" class=\"form-control no-border\" ng-model=\"localState.checkUser.password\" required autocomplete=\"on\">\n" +
    "              </div>\n" +
    "              <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\">{{' page.login.doLogin'\n" +
    "                | translate }}</button>\n" +
    "            </div>\n" +
    "          </form>\n" +
    "          \n" +
    "          <div class=\"text-center m-t m-b\"><a ui-sref=\"app.forgotpwd\">Forgot password?</a></div>\n" +
    "       \n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"panel-body\"  ng-show=\"loginTabState.signup\">\n" +
    "\n" +
    "          <a ng-click=\"clickLoading('app.register')\" class=\"btn btn-lg btn-orange btn-block\">{{' page.login.goToSignUp' | translate}}</a>\n" +
    "\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/socialLogin.html' \" />\n" +
    "\n" +
    "\n" +
    "    </div>\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/impressumBottomBlock.html' \" />\n" +
    "\n" +
    "    <!--<div class=\"text-center\" ng-include=\"'angulr/tpl/blocks/page_footer.html'\">-->\n" +
    "      <!--&lt;!&ndash;{% include 'blocks/page_footer.html' %}&ndash;&gt;-->\n" +
    "    <!--</div>-->\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_menuList.html",
    "<div data-ng-include=\" 'angulr/tpl/hotel/blocks/dealSubTabBlock.html' \" style=\"display: flex;\"/>\n" +
    "\n" +
    "<div id=\"minPaddingPage\" class=\"bg-light lter wrapper-md\" ng-controller=\"MenuListController\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = true;\n" +
    "app.header.tabIndex = 0;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "    <div class=\"wrapper-md\">\n" +
    "        <div class=\"row\">\n" +
    "            <div>\n" +
    "                <!--ng-class=\"::{'col-sm-9': !isSmartDevice}\">-->\n" +
    "\n" +
    "                <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "                <div class=\"wrapper text-center\" style=\"padding-top: 0px;\">\n" +
    "\n" +
    "                    <div style=\"text-align: left; margin-top: 10px;\" ng-if=\"!hotelState.profileData.checkedIn\">\n" +
    "\n" +
    "                        <!--<div style=\"white-space: pre-line;\">{{::'page.hotel.hotelFilterLabel' | translate}}:</div>-->\n" +
    "                        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/selectHotelCityBlock.html' \" />\n" +
    "\n" +
    "                    </div>\n" +
    "\n" +
    "                    <hr style=\"margin: 0;\"/>\n" +
    "          \n" +
    "          <span class=\"hotelicoHeader\" ng-show=\"localState.filterMenuItems.length>0 && localState.selectedCityHotel\">\n" +
    "              <span ng-show=\"localState.selectedFilterHotel\">{{::'system.menu' | translate}}  in {{localState.selectedFilterHotel.name}}:</span>\n" +
    "              <!--<span ng-show=\"!localState.selectedFilterHotel && localState.selectedCityHotel\">{{::'page.hotel.hotels' | translate}}  in {{localState.selectedCityHotel.city}}</span>-->\n" +
    "          </span>\n" +
    "                </div>\n" +
    "\n" +
    "                <div id=\"hotelPart\" ng-controller=\"hotelCtrl\">\n" +
    "\n" +
    "                    <!--<div ng-repeat=\"(key, showHotel) in localState.filterMenuItems | orderBy: 'id':true  track by showMenuItem.id\" >-->\n" +
    "                    <!-- -->\n" +
    "                    <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/showHotelPreviewBlock.html' \" />-->\n" +
    "                    <!-- -->\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <div ng-show=\"selectedFilterHotel.id>0\">\n" +
    "\n" +
    "                    </div>\n" +
    "\n" +
    "                    <div ng-if=\"isHotelStaffOrAdmin()\" >\n" +
    "                        <ul id=\"menuItemsUl\" class = \"sortable sortable-1\" dnd-container dnd-sortable-list = \"localState.filterMenuItems\" style=\"list-style-type: none;padding-left: 0px;\">\n" +
    "\n" +
    "                            <!--Eugen: $index - is index in having array -> also in localState.filterMenuItems!!!-->\n" +
    "                            <li ng-repeat = \"showMenuItem in localState.filterMenuItems | orderBy: $index\" dnd-sortable\n" +
    "                                dnd-sortable-opts = \"{layer: 'test12'}\"\n" +
    "                                dnd-on-sortstart = \"onSortStart()\"\n" +
    "                                dnd-on-sort = \"onSort()\"\n" +
    "                                dnd-on-sortchange = \"onSortChange(showMenuItem, $index)\"\n" +
    "                                dnd-on-sortend = \"onSortEnd(showMenuItem, $index)\"\n" +
    "                                dnd-on-sortenter1 = \"onSortEnter(showMenuItem, $index)\"\n" +
    "                            >\n" +
    "\n" +
    "                                <div data-ng-include=\" 'angulr/tpl/hotel/blocks/menuItemBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "                            </li>\n" +
    "                        </ul>\n" +
    "                    </div>\n" +
    "                      \n" +
    "                    <div ng-if=\"!isHotelStaffOrAdmin()\">\n" +
    "                        <ul style=\"list-style-type: none;padding-left: 0px;\">\n" +
    "                            <li ng-repeat = \"showMenuItem in localState.filterMenuItems | orderBy: 'index'\">\n" +
    "\n" +
    "                                <div ng-class=\"{'selectedMenuItem' :showMenuItem.amount>0}\" data-ng-include=\" 'angulr/tpl/hotel/blocks/menuItemBlock.html' \" ng-click=\"addOrderItemAction(showMenuItem, 'addItemToOrder')\"/>\n" +
    "\n" +
    "                            </li>\n" +
    "                        </ul>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    \n" +
    "                    \n" +
    "\n" +
    "                    <div layout=\"column\" class=\"container w-xxl w-auto-xs\" ng-show=\"localState.selectedFilterHotel.id>0 && isHotelStaffOrAdmin()\">\n" +
    "\n" +
    "                        <!--<button class=\"btn btn-lg btn-orange btn-block btn-addon\" ng-show=\"localState.orderingChanged\" ng-click=\"saveOrdering()\"><i class=\"fa fa-list\"></i> Save new ordering</button>-->\n" +
    "                        \n" +
    "                        <button class=\"btn btn-lg btn-blue btn-block btn-addon\" ng-click=\"addMenuItem('item')\"><i class=\"fa fa-plus\"></i> Add menu item</button>\n" +
    "\n" +
    "                        <button class=\"btn btn-lg btn-blue btn-block btn-addon\" ng-click=\"addMenuItem('delimiter')\"><i class=\"fa fa-align-left\"></i> Add menu delimiter</button>\n" +
    "\n" +
    "                    </div>\n" +
    "\n" +
    "\n" +
    "\n" +
    "                    <!--<div class=\"nothingFoundMsg\" ng-show=\"localState.noHotelFound\">{{::'page.hotel.noHotelFound' | translate}}</div>-->\n" +
    "                  \n" +
    "\n" +
    "                    <div class=\"myCenter m-t\">\n" +
    "                        <div ng-show=\"localState.selectedFilterHotel.name && localState.menuItemsLoaded && localState.filterMenuItems.length==0\" style=\"text-align: center;\">\n" +
    "                            {{::'page.hotel.noActualMenus' | translate}} in {{localState.selectedFilterHotel.name}}\n" +
    "                        </div>                        \n" +
    "                        \n" +
    "                        <span ng-show=\"!localState.menuItemsLoaded\">Loading...</span>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <!--<div>-->\n" +
    "                    <!--<div class=\"menu-disclaimer yd-grid\">-->\n" +
    "\n" +
    "\n" +
    "                    <!--<div class=\"yd-grid-12\">-->\n" +
    "                    <!--<h2>Stoffe oder Erzeugnisse, die Allergien oder Intoleranzen auslösen</h2>-->\n" +
    "                    <!--<ul>-->\n" +
    "                    <!--<li><span>1</span> <span>Enthält glutenhaltige/s Getreide/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>2</span> <span>Enthält Krebstiere/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>3</span> <span>Enthält Ei/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>4</span> <span>Enthält Fisch/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>5</span> <span>Enthält Erdnüsse/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>6</span> <span>Enthält Sojabohnen/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>7</span> <span>Enthält Milch/-Erzeugnisse (laktosehaltig)</span></li>-->\n" +
    "                    <!--<li><span>8</span> <span>Enthält Schalenfrüchte/Nüsse bzw. Nusserzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>9</span> <span>Enthält Sellerie/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>10</span><span>Enthält Senf/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>11</span><span>Enthält Sesamsamen/-Erzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>12</span><span>Enthält Schwefeloxid/Sulfite</span></li>-->\n" +
    "                    <!--<li><span>13</span><span>Enthält Lupine/-nerzeugnisse</span></li>-->\n" +
    "                    <!--<li><span>14</span><span>Enthält Weichtiere/-Erzeugnisse</span></li>-->\n" +
    "                    <!--</ul>-->\n" +
    "\n" +
    "                    <!--<h2>LMIV - Allergene</h2>-->\n" +
    "                    <!--<ul>-->\n" +
    "                    <!--<li><span>A</span> <span>Mit Süßungsmittel(n)</span></li>-->\n" +
    "                    <!--<li><span>B</span> <span>Mit Zucker(n) und Süßungsmittel(n)</span></li>-->\n" +
    "                    <!--</ul>-->\n" +
    "\n" +
    "                    <!--</div>-->\n" +
    "                    <!--<div class=\"yd-grid-11 yd-off-01\">-->\n" +
    "\n" +
    "                    <!--<ul>-->\n" +
    "                    <!--<li><span>C</span> <span>Enthält Aspartam</span></li>-->\n" +
    "                    <!--<li><span>D</span> <span>Kann bei übermäßigem Verzehr abführend wirken</span></li>-->\n" +
    "                    <!--<li><span>E</span> <span>Enthält Süßholz</span></li>-->\n" +
    "                    <!--<li><span>F</span> <span>Erhöhter Koffeingehalt. Für Kinder und schwangere oder stillende Frauen nicht empfohlen</span></li>-->\n" +
    "                    <!--<li><span>G</span> <span>Enthält Koffein. Für Kinder und schwangere Frauen nicht empfohlen</span></li>-->\n" +
    "                    <!--</ul>-->\n" +
    "\n" +
    "                    <!--<h2>Zusatzstoffe</h2>-->\n" +
    "                    <!--<ul>-->\n" +
    "                    <!--<li><span>H</span> <span>Mit Farbstoff</span></li>-->\n" +
    "                    <!--<li><span>I</span> <span>Mit Konservierungsstoff</span></li>-->\n" +
    "                    <!--<li><span>J</span> <span>Mit Nitritpökelsalz</span></li>-->\n" +
    "                    <!--<li><span>K</span> <span>Mit Nitrat</span></li>-->\n" +
    "                    <!--<li><span>L</span> <span>Mit Nitritpökelsalz und Nitrat</span></li>-->\n" +
    "                    <!--<li><span>M</span> <span>Mit Antioxidationsmittel</span></li>-->\n" +
    "                    <!--<li><span>N</span> <span>Mit Geschmacksverstärker</span></li>-->\n" +
    "                    <!--<li><span>O</span> <span>Geschwärzt</span></li>-->\n" +
    "                    <!--<li><span>P</span> <span>Gewachst</span></li>-->\n" +
    "                    <!--<li><span>Q</span> <span>Mit Phosphat</span></li>-->\n" +
    "                    <!--<li><span>S</span> <span>Sulfite</span></li>-->\n" +
    "                    <!--</ul>-->\n" +
    "                    <!--</div>-->\n" +
    "                    <!-- -->\n" +
    "                    <!--</div>-->\n" +
    "                    <!--</div>-->\n" +
    "\n" +
    "                    <div class=\"bottom-menu-sum\" ng-hide=\"isHotelStaffOrAdmin()\">\n" +
    "                        <div class=\"bottom-menu-sum-transparent\">\n" +
    "                            <div class=\"bottom-menu-sum-content\" ng-click=\"openModal('editMenuOrder')\">\n" +
    "                                {{hotelState.menuOrder.itemAmount}} Items \n" +
    "                                <span>\n" +
    "                                    ({{ hotelState.menuOrder.totalPrice | currency:\"\":2}} <i class=\"fa fa-eur menu-euro\" style=\"font-size: 15px;\"></i>)\n" +
    "                                </span>\n" +
    "                                <a style=\"float: right;margin-right: 12px;padding-top: 3px;font-size: 33px;\">\n" +
    "                                    <i class=\"fa fa-cart-arrow-down\"></i>\n" +
    "                                </a>\n" +
    "                            </div>\n" +
    "\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                </div>\n" +
    "\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_privacy.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = false;\n" +
    "  app.hideFooter = false;\n" +
    "  app.header.showTab = false;\n" +
    "  app.header.showBackArrow = true;\n" +
    "  app.rootSettings.darkBg = true;\n" +
    "  \">\n" +
    "\n" +
    "	<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "		<h1 class=\"m-n font-thin h3\">hotelico.de Privacy Policy</h1>\n" +
    "	</div>\n" +
    "	<div class=\"wrapper-md\">\n" +
    "		<div class=\"row\">\n" +
    "			<div style=\"padding: 5px;\">\n" +
    "				<!--class=\"col-sm-9\">-->\n" +
    "				\n" +
    "	\n" +
    "	 \n" +
    "\n" +
    "	This privacy policy has been compiled to better serve those who are\n" +
    "	concerned with how their 'Personally identifiable information' (PII) is\n" +
    "	being used online. PII, as used in US privacy law and information security,\n" +
    "	is information that can be used on its own or with other information to\n" +
    "	identify, contact, or locate a single person, or to identify an individual\n" +
    "	in context. Please read our privacy policy carefully to get a clear\n" +
    "	understanding of how we collect, use, protect or otherwise handle your\n" +
    "	Personally Identifiable Information in accordance with our website.\n" +
    "\n" +
    "	<br/><br/>\n" +
    "\n" +
    "	<b>What personal information do we collect from the people that visit our\n" +
    "	blog, website or app?</b>\n" +
    "\n" +
    "	<br/><br/>\n" +
    "\n" +
    "\n" +
    "	When ordering or registering on our site, as appropriate, you may be asked\n" +
    "	to enter your name, email address or other details to help you with your\n" +
    "	experience.\n" +
    "\n" +
    "	<br/><br/>\n" +
    "\n" +
    "	<b>When do we collect information?</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	We collect information from you when you register on our site or enter\n" +
    "	information on our site.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>How do we use your information?</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	We may use the information we collect from you when you register, make a\n" +
    "	purchase, sign up for our newsletter, respond to a survey or marketing\n" +
    "	communication, surf the website, or use certain other site features in the\n" +
    "	following ways:\n" +
    "				<br/><br/>\n" +
    "				<ul>\n" +
    "					 <li>To personalize user's experience and to allow us to deliver the\n" +
    "	type of content and product offerings in which you are most interested.\n" +
    "</li></ul>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>How do we protect visitor information?</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	Our website is scanned on a regular basis for security holes and known\n" +
    "	vulnerabilities in order to make your visit to our site as safe as possible.\n" +
    "				<br/><br/>\n" +
    "	We use regular Malware Scanning.\n" +
    "				<br/><br/>\n" +
    "	Your personal information is contained behind secured networks and is only\n" +
    "	accessible by a limited number of persons who have special access rights to\n" +
    "	such systems, and are required to keep the information confidential.\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	We implement a variety of security measures when a user enters, submits, or\n" +
    "	accesses their information to maintain the safety of your personal\n" +
    "	information.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	All transactions are processed through a gateway provider and are not\n" +
    "	stored or processed on our servers.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>Do we use 'cookies'?</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	Yes. Cookies are small files that a site or its service provider transfers\n" +
    "	to your computer's hard drive through your Web browser (if you allow) that\n" +
    "	enables the site's or service provider's systems to recognize your browser\n" +
    "	and capture and remember certain information. For instance, we use cookies\n" +
    "	to help us remember and process the items in your shopping cart. They are\n" +
    "	also used to help us understand your preferences based on previous or\n" +
    "	current site activity, which enables us to provide you with improved\n" +
    "	services. We also use cookies to help us compile aggregate data about site\n" +
    "	traffic and site interaction so that we can offer better site experiences\n" +
    "	and tools in the future.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "	<b>We use cookies to:</b>\n" +
    "				<br/><br/>\n" +
    "	<ul>\n" +
    "	<li>Understand and save user's preferences for future visits.</li>\n" +
    "	</ul>\n" +
    "				<br/><br/>\n" +
    "	You can choose to have your computer warn you each time a cookie is being\n" +
    "	sent, or you can choose to turn off all cookies. You do this through your\n" +
    "	browser (like Internet Explorer) settings. Each browser is a little\n" +
    "	different, so look at your browser's Help menu to learn the correct way to\n" +
    "	modify your cookies.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "	<b>If users disable cookies in their browser:</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	If you disable cookies off, some features will be disabled. It will turn\n" +
    "	off some of the features that make your site experience more efficient and\n" +
    "	some of our services will not function properly.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>Third Party Disclosure</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	We do not sell, trade, or otherwise transfer to outside parties your\n" +
    "	personally identifiable information.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>Third party links</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	Occasionally, at our discretion, we may include or offer third party\n" +
    "	products or services on our website. These third party sites have separate\n" +
    "	and independent privacy policies. We therefore have no responsibility or\n" +
    "	liability for the content and activities of these linked sites.\n" +
    "	Nonetheless, we seek to protect the integrity of our site and welcome any\n" +
    "	feedback about these sites.\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	<b>Google</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	Google's advertising requirements can be summed up by Google's Advertising\n" +
    "	Principles. They are put in place to provide a positive experience for\n" +
    "	users. <a href=\"https://support.google.com/adwordspolicy/answer/1316548?hl=en\">https://support.google.com/adwordspolicy/answer/1316548?hl=en</a>\n" +
    "				<br/><br/>\n" +
    "	We have not enabled Google AdSense on our site but we may do so in the\n" +
    "	future.\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	<b>How does our site handle do not track signals?</b>\n" +
    "				<br/><br/>\n" +
    "	We honor do not track signals and do not track, plant cookies, or use\n" +
    "	advertising when a Do Not Track (DNT) browser mechanism is in place.\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<!--<b>Does our site allow third party behavioral tracking?</b>-->\n" +
    "				<!--<br/><br/>-->\n" +
    "	<!--It's also important to note that we do not allow third party behavioral-->\n" +
    "	<!--tracking-->\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>COPPA (Children Online Privacy Protection Act)</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	When it comes to the collection of personal information from children under\n" +
    "	13, the Children's Online Privacy Protection Act (COPPA) puts parents in\n" +
    "	control. The Federal Trade Commission, the nation's consumer protection\n" +
    "	agency, enforces the COPPA Rule, which spells out what operators of\n" +
    "	websites and online services must do to protect children's privacy and\n" +
    "	safety online.\n" +
    "				<br/><br/>\n" +
    "	We do not specifically market to children under 13.\n" +
    "				<br/><br/>\n" +
    "\n" +
    "\n" +
    "	<b>Fair Information Practices</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	The Fair Information Practices Principles form the backbone of privacy law\n" +
    "	in the United States and the concepts they include have played a\n" +
    "	significant role in the development of data protection laws around the\n" +
    "	globe. Understanding the Fair Information Practice Principles and how they\n" +
    "	should be implemented is critical to comply with the various privacy laws\n" +
    "	that protect personal information.\n" +
    "				<br/><br/>\n" +
    "	<b>In order to be in line with Fair Information Practices we will take the\n" +
    "	following responsive action, should a data breach occur:</b>\n" +
    "				<br/><br/>\n" +
    "	We will notify the users via email\n" +
    "				<br/><br/>\n" +
    "				<ul>\n" +
    "					 <li>Within 1 business day</li></ul>\n" +
    "				<br/><br/>\n" +
    "	We will notify the users via in site notification\n" +
    "				<br/><br/>\n" +
    "				<ul>\n" +
    "					 <li> Within 1 business day</li></ul>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	We also agree to the individual redress principle, which requires that\n" +
    "	individuals have a right to pursue legally enforceable rights against data\n" +
    "	collectors and processors who fail to adhere to the law. This principle\n" +
    "	requires not only that individuals have enforceable rights against data\n" +
    "	users, but also that individuals have recourse to courts or a government\n" +
    "	agency to investigate and/or prosecute non-compliance by data processors.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>CAN SPAM Act</b>\n" +
    "\n" +
    "\n" +
    "				<br/><br/>\n" +
    "	The CAN-SPAM Act is a law that sets the rules for commercial email,\n" +
    "	establishes requirements for commercial messages, gives recipients the\n" +
    "	right to have emails stopped from being sent to them, and spells out tough\n" +
    "	penalties for violations.\n" +
    "				<br/><br/>\n" +
    "	<b>We collect your email address in order to:</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "	<b>To be in accordance with CANSPAM we agree to the following:</b>\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b> If at any time you would like to unsubscribe from receiving future\n" +
    "	emails, you can email us at</b>\n" +
    "				<br/><br/>\n" +
    "	and we will promptly remove you from </b>ALL</b> correspondence.\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	<b>Contacting Us</b>\n" +
    "\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	If there are any questions regarding this privacy policy you may contact us\n" +
    "	using the information below.\n" +
    "				<br/><br/>\n" +
    "	hotelico.de\n" +
    "				<br/>\n" +
    "	August-Schanz-Str. 28\n" +
    "				<br/>\n" +
    "	Frankfurt am Main, 60433\n" +
    "				<br/>\n" +
    "	Germany\n" +
    "				<br/>\n" +
    "	info@hotelico.de\n" +
    "				<br/><br/>\n" +
    "\n" +
    "	Last Edited on 2015-08-20\n" +
    "\n" +
    "</div>\n" +
    "		</div>\n" +
    "\n" +
    "	</div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_profile.html",
    "<div ng-controller=\"ProfileController\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "  <div class=\"panel panel-default\">\n" +
    "    \n" +
    "    <div class=\"panel-heading\" ngCloak>\n" +
    "      <div class=\"clearfix\">\n" +
    "        <a href class=\"pull-left thumb-md avatar b-3x m-r\">\n" +
    "          <!--<img src=\"{{getProfileImageUrl()}}\" alt=\"...\">-->\n" +
    "          <img ng-src=\"{{hotelState.profileData.avatarUrl}}\" alt=\"...\">\n" +
    "        </a>\n" +
    "        <div class=\"clear\">\n" +
    "          <div class=\"h3 m-t-xs m-b-xs\">\n" +
    "            {{hotelState.profileData.firstName}} {{hotelState.profileData.lastName}}\n" +
    "            <i class=\"fa fa-circle text-success pull-right text-xs m-t-sm\"></i>\n" +
    "            <br/>\n" +
    "            <a ui-sref=\"app.avatar\" style=\" font-size: 12px; text-decoration: underline;\">(Change Avatar)</a>\n" +
    "          </div>\n" +
    "          <small class=\"text-muted\">{{hotelState.profileData.status}}</small>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"wrapper-md\">\n" +
    "\n" +
    "      <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "      <div class=\"panel panel-default\">\n" +
    "        \n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "          {{:: 'profile.section.personalInfo' | translate }}\n" +
    "        </div>\n" +
    "  \n" +
    "        <div class=\"panel-body\">\n" +
    "         \n" +
    "          <div class=\"form-group\">\n" +
    "             \n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.statusLabel' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control rounded\" placeholder=\"{{::'profile.status' | translate}}\"  ng-model=\"localState.updateProfileData.status\">\n" +
    "              <span class=\"help-block m-b-none\">{{::'profile.statusDescr' | translate}}</span>\n" +
    "   \n" +
    "            </div>\n" +
    "          </div>\n" +
    "          \n" +
    "          \n" +
    "          <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "          <!--<div class=\"form-group\">-->\n" +
    "            <!--<label class=\"col-sm-2 control-label\">{{::'profile.changeAvatar' | translate}}</label>-->\n" +
    "            <!--<div class=\"col-sm-10\">-->\n" +
    "\n" +
    "              <!--<div  ng-cloak>-->\n" +
    "                <!--<div>-->\n" +
    "                  <!--<div class=\"well\">-->\n" +
    "                    <!--<form action=\"\" class=\"dropzone\" dropzone=\"\" id=\"dropzone\">-->\n" +
    "                      <!--<div class=\"dz-default dz-message\">-->\n" +
    "                      <!--</div>-->\n" +
    "                    <!--</form>-->\n" +
    "                  <!--</div>-->\n" +
    "                  <!--<div class=\"pull-right\">-->\n" +
    "                    <!--&lt;!&ndash;<button class=\"btn btn-success\" ng-click=\"uploadFile()\">Upload Avatar</button>&ndash;&gt;-->\n" +
    "                    <!--<button class=\"btn btn-danger\" ng-click=\"reset()\" ng-show=\"fileAdded\">{{::'profile.removeAvatar' | translate}}</button>-->\n" +
    "                  <!--</div>-->\n" +
    "                <!--</div>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "            <!--</div>-->\n" +
    "          <!--</div>-->\n" +
    "          \n" +
    "          \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\"></label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <label class=\"i-checks\">\n" +
    "                <input type=\"radio\" value=\"m\" ng-model=\"localState.updateProfileData.sex\"><i></i> {{::'profile.gender-m' | translate}} &nbsp;\n" +
    "              </label>\n" +
    "              <label class=\"i-checks\">\n" +
    "                <input type=\"radio\" value=\"w\" ng-model=\"localState.updateProfileData.sex\"><i></i> {{::'profile.gender-f' | translate}}\n" +
    "              </label>\n" +
    "              <span class=\"requiredField\">*</span>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "\n" +
    "          <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.firstName' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.firstName' | translate}}\"  ng-model=\"localState.updateProfileData.firstName\" required>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "\n" +
    "          <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.lastName' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.lastName' | translate}}\" ng-model=\"localState.updateProfileData.lastName\">\n" +
    "            </div>\n" +
    "          </div>\n" +
    "          \n" +
    "          <!--<div class=\"form-group\">-->\n" +
    "            <!--<label class=\"col-sm-2 control-label\">{{::'profile.section.aboutYou' | translate}}<span class=\"requiredField\">*</span></label>-->\n" +
    "            <!--<div class=\"col-sm-10\">-->\n" +
    "              <!--<div class=\"m-b\">-->\n" +
    "                <!--<input type=\"text\" class=\"form-control\" placeholder=\"{{::'firstName' | translate}}\"  ng-model=\"localState.updateProfileData.firstName\" required>-->\n" +
    "              <!--</div>-->\n" +
    "              <!--<div class=\"m-b\">-->\n" +
    "                <!--<input type=\"text\" class=\"form-control\" placeholder=\"{{::'lastName' | translate}}\" ng-model=\"localState.updateProfileData.lastName\" required>-->\n" +
    "              <!--</div>-->\n" +
    "              <!--<div class=\"m-b\">-->\n" +
    "                <!--<label class=\"i-checks\">-->\n" +
    "                  <!--<input type=\"radio\" value=\"m\" ng-model=\"localState.updateProfileData.sex\"><i></i> Mann &nbsp;-->\n" +
    "                <!--</label>-->\n" +
    "                <!--<label class=\"i-checks\">-->\n" +
    "                  <!--<input type=\"radio\" value=\"w\" ng-model=\"localState.updateProfileData.sex\"><i></i> Frau-->\n" +
    "                <!--</label>-->\n" +
    "              <!--</div>-->\n" +
    "            <!--</div>-->\n" +
    "          <!--</div>-->\n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.birthday' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <!--< class=\"input-group\"  ng-controller=\"DatepickerCheckinCtrl\">-->\n" +
    "                <!--<input type=\"text\" placeholder=\"{{::'birthday' | translate}}\"  class=\"form-control\" datepicker-popup=\"{{::format}}\" ng-model=\"localState.updateProfileData.birthdayTimeString\" is-open=\"opened\" datepicker-options=\"dateOptions\" ng-required=\"true\" close-text=\"Close\" />-->\n" +
    "                              <!--<span class=\"input-group-btn\">-->\n" +
    "                                  <!--<button type=\"button\" class=\"btn btn-default\" ng-click=\"open($event)\"><i class=\"glyphicon glyphicon-calendar\"></i></button>-->\n" +
    "                              <!--</span>-->\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "                <a editable-date=\"localState.profileBirthday\">{{ localState.generatedProfileBirthdayString || 'system.selectDate' | translate }}</a>\n" +
    "                <!--<select class=\"form-control ng-pristine localState.birthdayDaySelector\" ng-model=\"localState.birthday.day\" id=\"day\" ng-options=\"day as day for day in days\" placeholder=\"Tag\"></select>-->\n" +
    "                <!--<select class=\"form-control ng-pristine localState.birthdayMonthSelector\" ng-model=\"localState.birthday.month\" id=\"month\" ng-options=\"month.id as month.name for month in months\" placeholder=\"Monat\"></select>-->\n" +
    "                <!--<select class=\"form-control ng-pristine localState.birthdayYearSelector\" ng-model=\"localState.birthday.year\" id=\"year\" ng-options=\"year as year for year in years track by year\" placeholder=\"Jahr\"></select>-->\n" +
    "\n" +
    "              </div>\n" +
    "          </div>\n" +
    "          \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.language' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              {{usedLanguage}}<div isteven-multi-select  style=\"margin-left: -15px;\" id=\"selectLanguages\" class=\"col-xs-10\" input-model=\"localState.languages\" output-model=\"localState.updateProfileData.languages\" button-label=\"icon\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"250px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\"></div>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "          \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.city' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.city' | translate}}\"  ng-model=\"localState.updateProfileData.city\">\n" +
    "            </div>\n" +
    "          </div>\n" +
    "          \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.originalCity' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.originalCity' | translate}}\"  ng-model=\"localState.updateProfileData.originalCity\">\n" +
    "            </div>\n" +
    "          </div>\n" +
    "  \n" +
    "        </div>\n" +
    "      </div>\n" +
    "  \n" +
    "      <!-- #### arbeit --->\n" +
    "  \n" +
    "      <div class=\"panel panel-default\">\n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "          {{::'profile.section.aboutYourJob' | translate}}\n" +
    "        </div>\n" +
    "  \n" +
    "        <div class=\"panel-body\">\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.jobTitle' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.jobTitle' | translate}}\" ng-model=\"localState.updateProfileData.jobTitle\">\n" +
    "              <!--<span class=\"help-block m-b-none\">{{::'profile.jobTitleDescr' | translate}}</span>-->\n" +
    "            </div>\n" +
    "          </div>\n" +
    "  \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.jobDescriptor' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.jobDescriptor' | translate}}\" ng-model=\"localState.updateProfileData.jobDescriptor\">\n" +
    "              <span class=\"help-block m-b-none\">{{::'profile.jobDescriptorDescr' | translate}}</span>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "  \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.company' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <input type=\"text\" class=\"form-control\" placeholder=\"{{::'profile.company' | translate}}\" ng-model=\"localState.updateProfileData.company\">\n" +
    "              <!--<span class=\"help-block m-b-none\">{{::'profile.companyDescr' | translate}}</span>-->\n" +
    "            </div>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "\n" +
    "      <!-- #### lopgin --->\n" +
    "\n" +
    "      <div class=\"panel panel-default\">\n" +
    "\n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "          {{::'profile.section.privateSetting' | translate}}\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"panel-body\">\n" +
    "\n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.allowHotelNotification' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <div class=\"btn-group\">\n" +
    "                <label class=\"i-switch m-t-xs m-r\">\n" +
    "                  <input type=\"checkbox\" checked=\"true\" ng-model=\"localState.updateProfileData.allowHotelNotification\">\n" +
    "                  <i></i>\n" +
    "                </label>\n" +
    "              </div>\n" +
    "            </div>\n" +
    "\n" +
    "          </div>\n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\" ng-show=\"localState.updateProfileData.avatarUrl.length>0\"></div>\n" +
    "\n" +
    "          <div class=\"form-group\" ng-show=\"hotelState.profileData.avatarUrl.length>0\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.showAvatar' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <div class=\"btn-group\">\n" +
    "                <label class=\"i-switch m-t-xs m-r\">\n" +
    "                  <input type=\"checkbox\" checked=\"true\" ng-model=\"localState.updateProfileData.showAvatar\">\n" +
    "                  <i></i>\n" +
    "                </label>\n" +
    "              </div>\n" +
    "            </div>\n" +
    "\n" +
    "          </div>\n" +
    "          \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.showInGuestList' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <div class=\"btn-group\">\n" +
    "                <label class=\"i-switch m-t-xs m-r\">\n" +
    "                  <input type=\"checkbox\" checked=\"true\" ng-model=\"localState.updateProfileData.showInGuestList\">\n" +
    "                  <i></i>\n" +
    "                </label>\n" +
    "              </div>\n" +
    "            </div>\n" +
    "\n" +
    "          </div>\n" +
    "\n" +
    "        </div>\n" +
    "\n" +
    "      </div>\n" +
    "      \n" +
    "      \n" +
    "      <!-- #### lopgin --->\n" +
    "  \n" +
    "      <div class=\"panel panel-default\">\n" +
    "        \n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "          {{::'profile.section.loginData' | translate}}\n" +
    "        </div>\n" +
    "  \n" +
    "        <div class=\"panel-body\">\n" +
    "  \n" +
    "          <!--<div class=\"form-group\">-->\n" +
    "            <!--<label class=\"col-sm-2 control-label\">Email</label>-->\n" +
    "            <!--<div class=\"col-sm-10\">-->\n" +
    "              <!--<input type=\"text\" class=\"form-control\" placeholder=\"{{::'email' | translate}}\"  ng-model=\"localState.updateProfileData.email\">-->\n" +
    "            <!--</div>-->\n" +
    "          <!--</div>-->\n" +
    "  \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-lg-2 control-label\">Email<span class=\"requiredField\">*</span></label>\n" +
    "            <div class=\"col-lg-10\">\n" +
    "              <p class=\"form-control-static\" ng-show=\"localState.goodEmail\">{{localState.updateProfileData.email}}</p>\n" +
    "              <input type=\"email\" class=\"form-control\" placeholder=\"email\"  ng-model=\"localState.updateProfileData.email\" ng-hide=\"localState.goodEmail\">\n" +
    "\n" +
    "            </div>\n" +
    "          </div>\n" +
    "  \n" +
    "          <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "         \n" +
    "          <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{::'profile.newPasswordLabel' | translate}}</label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "              <div class=\"m-b\">\n" +
    "                <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputPassword\" ng-model=\"localState.updateProfileData.password\" placeholder=\"{{::'profile.password' | translate}}\">\n" +
    "              </div>\n" +
    "              <div class=\"m-b\">\n" +
    "                <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputConfirmPassword\" ng-model=\"localState.updateProfileData.confirmPassword\" placeholder=\"{{::'profile.confirmPassword' | translate}}\">\n" +
    "              </div>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "  \n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "\n" +
    "      \n" +
    "      \n" +
    "      \n" +
    "    </div>\n" +
    "  </div>\n" +
    "    \n" +
    "  <div class=\"container w-xxl w-auto-xs\" style=\"margin-bottom: 50px;\">\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "    <button type=\"submit\" class=\"btn btn-lg btn-success btn-block btn-addon\" ng-click=\"submitProfile()\"  ng-disabled=\"!localState.updateProfileData.firstName || !localState.updateProfileData.sex\" ><i class=\"fa fa-pencil-square-o\"></i>{{'profile.changeData' | translate }}</button>\n" +
    "  </div>\n" +
    "    \n" +
    "</div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_register.html",
    "<div class=\"bg-light lter b-b wrapper-md\">\n" +
    "    <h1 class=\"m-n font-thin h3\">Anmelden</h1>\n" +
    "</div>\n" +
    "<div class=\"wrapper-md\" ng-controller=\"RegisterController\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "<!--<form editable-form name=\"form\" class=\"form-horizontal\" > &lt;!&ndash;method=\"get\">&ndash;&gt;-->\n" +
    "    <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "    \n" +
    "    <div class=\"panel panel-default\">\n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "            Personal Information\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"panel-body\">\n" +
    "            \n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\"></label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <label class=\"i-checks\">\n" +
    "                        <input type=\"radio\" value=\"m\" ng-model=\"localState.profile.sex\"><i></i> Herr &nbsp;\n" +
    "                    </label>\n" +
    "                    <label class=\"i-checks\">\n" +
    "                        <input type=\"radio\" value=\"w\" ng-model=\"localState.profile.sex\"><i></i> Frau\n" +
    "                    </label>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{'firstName' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "                <input type=\"text\" class=\"form-control\" placeholder=\"{{'firstName' | translate}}\"  ng-model=\"localState.profile.firstName\" required>\n" +
    "            </div>\n" +
    "            </div>\n" +
    "            \n" +
    "            <!--<div class=\"line line-dashed b-b line-lg pull-in\"></div>-->\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "            <label class=\"col-sm-2 control-label\">{{'lastName' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "            <div class=\"col-sm-10\">\n" +
    "                <input type=\"text\" class=\"form-control\" placeholder=\"{{'lastName' | translate}}\" ng-model=\"localState.profile.lastName\" required>\n" +
    "            </div>\n" +
    "            </div>\n" +
    "            \n" +
    "            \n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "            \n" +
    "            <div class=\"form-group\">\n" +
    "                <!--<form editable-form>-->\n" +
    "                <label class=\"col-sm-2 control-label\">Geburtstag</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                       \n" +
    "                    <a editable-date=\"localState.registerBirthday\">{{ getRegisterBirthdayString() || 'system.selectDate' | translate }}</a>\n" +
    "\n" +
    "                </div>\n" +
    "                <!--</form>-->\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'language' | translate}}<span class=\"requiredField\">*</span></label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <div isteven-multi-select  style=\"margin-left: -15px;\" id=\"selectLanguages\" class=\"col-xs-10\" input-model=\"localState.languages\" output-model=\"localState.profile.languages\" button-label=\"icon\" item-label=\"icon name\" tick-property=\"ticked\" max-labels=\"5\" max-height=\"320px\" search-property=\"name\" output-properties=\"name\" helper-elements=\"filter\"></div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'profile.city' | translate}}</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"text\" class=\"form-control\" placeholder=\"{{'profile.city' | translate}}\"  ng-model=\"localState.profile.city\">\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            \n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'profile.originalCity' | translate}}</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"text\" class=\"form-control\" placeholder=\"{{'profile.originalCity' | translate}}\"  ng-model=\"localState.profile.originalCity\">\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            \n" +
    "        </div>\n" +
    "    </div>\n" +
    "    \n" +
    "    <!-- #### arbeit --->\n" +
    "\n" +
    "    <div class=\"panel panel-default\">\n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "            {{'profile.section.aboutYourJob' | translate}}\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"panel-body\">\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'jobTitle' | translate}}</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"text\" class=\"form-control\" placeholder=\"{{'jobTitle' | translate}}\" ng-model=\"localState.profile.jobTitle\">\n" +
    "                    <span class=\"help-block m-b-none\">A block of help text that breaks onto a new line and may extend beyond one line.</span>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'jobDescriptor' | translate}}</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"text\" class=\"form-control\" placeholder=\"{{'jobDescriptor' | translate}}\" ng-model=\"localState.profile.jobDescriptor\">\n" +
    "                    <span class=\"help-block m-b-none\">A block of help text that breaks onto a new line and may extend beyond one line.</span>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">{{'company' | translate}}</label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"text\" class=\"form-control\" placeholder=\"{{'company' | translate}}\" ng-model=\"localState.profile.company\">\n" +
    "                    <span class=\"help-block m-b-none\">A block of help text that breaks onto a new line and may extend beyond one line.</span>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "    </div> \n" +
    "    \n" +
    "    <!-- #### lopgin --->\n" +
    "\n" +
    "    <div class=\"panel panel-default\">\n" +
    "        <div class=\"panel-heading font-bold\">\n" +
    "            {{'profile.section.loginData' | translate}}\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"panel-body\">\n" +
    "\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">Email<span class=\"requiredField\">*</span></label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <input type=\"email\" class=\"form-control\" placeholder=\"{{'email' | translate}}\"  ng-model=\"localState.profile.email\" required>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "            <div class=\"line line-dashed b-b line-lg pull-in\"></div>\n" +
    "            <div class=\"form-group\">\n" +
    "                <label class=\"col-sm-2 control-label\">Password<span class=\"requiredField\">*</span></label>\n" +
    "                <div class=\"col-sm-10\">\n" +
    "                    <div class=\"m-b\">\n" +
    "                        <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputPassword\" ng-model=\"localState.profile.password\" placeholder=\"{{'password' | translate}}\">\n" +
    "                    </div>\n" +
    "                    <div class=\"m-b\">\n" +
    "                        <input type=\"password\" name=\"password\" class=\"form-control\" id=\"inputConfirmPassword\" ng-model=\"localState.profile.confirmPassword\" placeholder=\"{{'confirmPassword' | translate}}\">\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            \n" +
    "        </div>\n" +
    "    </div>\n" +
    "    \n" +
    "    <div class=\"container w-xxl w-auto-xs ng-scope\">\n" +
    "    \n" +
    "        <div class=\"checkbox m-b-md m-t-none\">\n" +
    "            <label class=\"i-checks\">\n" +
    "                <input type=\"checkbox\" ng-model=\"localState.agree\" required><i></i> {{'page.register.agree' | translate}} <a href>{{'page.register.termsAndConditions' | translate}}</a>\n" +
    "            </label>\n" +
    "        </div>\n" +
    "        <br/>\n" +
    "\n" +
    "        <div data-ng-include=\" 'angulr/tpl/hotel/blocks/showAlertBlock.html' \" />\n" +
    "\n" +
    "\n" +
    "        <!--<button type=\"submit\" class=\"btn btn-lg btn-primary btn-block\" ng-click=\"signup()\" ng-disabled='form.$invalid'><span translate=\"hotel.login.CREATEACCOUNT\">Create an account</span></button>-->\n" +
    "        <button type=\"submit\" class=\"btn btn-lg btn-orange btn-block\" ng-click=\"submitRegister()\"  ng-disabled=\"!localState.profile.firstName || !localState.profile.lastName || !localState.profile.email || !localState.agree\" >{{'page.register.doRegister' | translate }}</button>\n" +
    "        <div class=\"line line-dashed\"></div>\n" +
    "        <p class=\"text-center\"><small>{{'page.register.alreadyHaveAccount' | translate}}</small></p>\n" +
    "        <a ui-sref=\"access.login\" class=\"btn btn-lg btn-default btn-block\">{{'page.login.doLogin' | translate }}</a>\n" +
    "    </div>\n" +
    "<!--</form>-->\n" +
    "</div>\n" +
    "\n" +
    "\n" +
    "<!--TODO EUGEN: NGMATERIAL-->\n" +
    "<!--<h4>Inputs</h4>-->\n" +
    "<!--<div class=\"panel card wrapper\" ng-controller=\"MDInputCtrl\">-->\n" +
    "    <!--<div class=\"row\">-->\n" +
    "        <!--<md-input-container class=\"md-primary col-sm-6\">-->\n" +
    "            <!--<label>Title</label>-->\n" +
    "            <!--<input ng-model=\"user.title\">-->\n" +
    "        <!--</md-input-container>-->\n" +
    "        <!--<md-input-container class=\"col-sm-6\">-->\n" +
    "            <!--<label>Email</label>-->\n" +
    "            <!--<input ng-model=\"user.email\" type=\"email\">-->\n" +
    "        <!--</md-input-container>-->\n" +
    "    <!--</div>-->\n" +
    "    <!--<form name=\"userForm\">-->\n" +
    "\n" +
    "        <!--<md-input-container flex>-->\n" +
    "            <!--<label>Company (Disabled)</label>-->\n" +
    "            <!--<input ng-model=\"user.company\" disabled>-->\n" +
    "        <!--</md-input-container>-->\n" +
    "\n" +
    "        <!--<div layout layout-sm=\"column\">-->\n" +
    "            <!--<md-input-container flex>-->\n" +
    "                <!--<label>First Name</label>-->\n" +
    "                <!--<input ng-model=\"user.firstName\">-->\n" +
    "            <!--</md-input-container>-->\n" +
    "            <!--<md-input-container flex>-->\n" +
    "                <!--<label>Last Name</label>-->\n" +
    "                <!--<input ng-model=\"theMax\">-->\n" +
    "            <!--</md-input-container>-->\n" +
    "        <!--</div>-->\n" +
    "\n" +
    "        <!--<md-input-container flex>-->\n" +
    "            <!--<label>Address</label>-->\n" +
    "            <!--<input ng-model=\"user.address\">-->\n" +
    "        <!--</md-input-container>-->\n" +
    "\n" +
    "        <!--<div layout layout-sm=\"column\">-->\n" +
    "            <!--<md-input-container flex>-->\n" +
    "                <!--<label>City</label>-->\n" +
    "                <!--<input ng-model=\"user.city\">-->\n" +
    "            <!--</md-input-container>-->\n" +
    "            <!--<md-input-container flex>-->\n" +
    "                <!--<label>State</label>-->\n" +
    "                <!--<input ng-model=\"user.state\">-->\n" +
    "            <!--</md-input-container>-->\n" +
    "            <!--<md-input-container flex>-->\n" +
    "                <!--<label>Postal Code</label>-->\n" +
    "                <!--<input ng-model=\"user.postalCode\">-->\n" +
    "            <!--</md-input-container>-->\n" +
    "        <!--</div>-->\n" +
    "\n" +
    "        <!--<md-input-container flex>-->\n" +
    "            <!--<label>Biography</label>-->\n" +
    "            <!--<textarea ng-model=\"user.biography\" columns=\"1\" md-maxlength=\"150\"></textarea>-->\n" +
    "        <!--</md-input-container>-->\n" +
    "    <!--</form>-->\n" +
    "    <!--<h5>Errors</h5>-->\n" +
    "    <!--<form name=\"projectForm\">-->\n" +
    "        <!--<md-input-container>-->\n" +
    "            <!--<label>Description</label>-->\n" +
    "            <!--<input md-maxlength=\"30\" required name=\"description\" ng-model=\"project.description\">-->\n" +
    "            <!--<div ng-messages=\"projectForm.description.$error\">-->\n" +
    "                <!--<div ng-message=\"required\">This is required.</div>-->\n" +
    "                <!--<div ng-message=\"md-maxlength\">The name has to be less than 30 characters long.</div>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</md-input-container>-->\n" +
    "        <!--<md-input-container>-->\n" +
    "            <!--<label>Client Name</label>-->\n" +
    "            <!--<input required name=\"clientName\" ng-model=\"project.clientName\">-->\n" +
    "            <!--<div ng-messages=\"projectForm.clientName.$error\">-->\n" +
    "                <!--<div ng-message=\"required\">This is required.</div>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</md-input-container>-->\n" +
    "        <!--<md-input-container>-->\n" +
    "            <!--<label>Hourly Rate (USD)</label>-->\n" +
    "            <!--<input required type=\"number\" step=\"any\" name=\"rate\" ng-model=\"project.rate\" min=\"800\" max=\"4999\" required>-->\n" +
    "            <!--<div ng-messages=\"projectForm.rate.$error\">-->\n" +
    "                <!--<div ng-message=\"required\">You've got to charge something! You can't just <b>give away</b> a Missile Defense System.</div>-->\n" +
    "                <!--<div ng-message=\"min\">You should charge at least $800 an hour. This job is a big deal... if you mess up, everyone dies!</div>-->\n" +
    "                <!--<div ng-message=\"max\">$5,000 an hour? That's a little ridiculous. I doubt event Bill Clinton could afford that.</div>-->\n" +
    "            <!--</div>-->\n" +
    "        <!--</md-input-container>-->\n" +
    "    <!--</form>-->\n" +
    "<!--</div>-->\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_userInfo.html",
    "<div ng-controller=\"ProfileController\" ng-init=\"\n" +
    "app.hideFooter = false;\n" +
    "app.header.showTab = false;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "\">\n" +
    "\n" +
    "    <div class=\"panel panel-default\">\n" +
    "        <div class=\"panel-heading\" ngCloak>\n" +
    "            <div class=\"clearfix\">\n" +
    "                <a href class=\"pull-left thumb-md avatar b-3x m-r\">\n" +
    "                    <img ng-src=\"{{localState.userData.avatarUrl}}\" alt=\"...\">\n" +
    "                    <i class=\"on md b-white right customerOnlineStatus\" ng-show=\"hotelNotification.notificationObj.hotelOnlineGuestIds[app.header.chatPartnerId]\"></i>\n" +
    "                    <i class=\"offline md b-white right customerOnlineStatus\" ng-hide=\"hotelNotification.notificationObj.hotelOnlineGuestIds[app.header.chatPartnerId]\"></i>\n" +
    "\n" +
    "                </a>\n" +
    "                <div class=\"clear\">\n" +
    "                    <div class=\"h3 m-t-xs m-b-xs\">\n" +
    "                        {{localState.userData.firstName}} {{localState.userData.lastName}}\n" +
    "                        <!--<i class=\"fa fa-circle text-success text-xs m-t-sm\" ng-show=\"hotelNotification.notificationObjhotelOnlineGuestIds[localState.userData.id]\"></i>-->\n" +
    "                        <!--<i class=\"fa fa-circle text-offline text-xs m-t-sm\" ng-hide=\"hotelNotification.notificationObjhotelOnlineGuestIds[localState.userData.id]\"></i>-->\n" +
    "                    </div>\n" +
    "                    <small class=\"text-muted\">{{localState.userData.status}}</small>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"wrapper-md\">\n" +
    "            <div class=\"panel panel-default\">\n" +
    "                 \n" +
    "\n" +
    "                <div class=\"panel-body\">\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.firstName\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.firstName' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.firstName}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.lastName\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.lastName\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.lastName' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.lastName}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.hotelName\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-show=\"localState.userData.checkedIn\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'system.hotel' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\" ng-show=\"localState.userData.hotelId != hotelState.profileData.hotelId\">\n" +
    "                            <a style=\"text-decoration: underline;\" ui-sref=\"app.hotelList({filterCity: localState.userData.hotelCity, filterHotelId: localState.userData.hotelId})\">\n" +
    "                                {{localState.userData.hotelName}}, {{localState.userData.hotelCity}}\n" +
    "                            </a>\n" +
    "                        </div>\n" +
    "                        <div class=\"col-xs-6\" ng-show=\"localState.userData.hotelId == hotelState.profileData.hotelId\">\n" +
    "                            <a style=\"text-decoration: underline;\" ui-sref=\"app.hotel\">\n" +
    "                                {{localState.userData.hotelName}}, {{localState.userData.hotelCity}}\n" +
    "                            </a>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.city\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.city\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.city' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.city}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    \n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.originalCity\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.originalCity\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.originalCity' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.originalCity}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.company\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.company\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.company' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.company}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.jobTitle\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.jobTitle\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.jobTitle' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.jobTitle}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-hide=\"!localState.userData.jobDescriptor\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-hide=\"!localState.userData.jobDescriptor\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.jobDescriptor' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            {{localState.userData.jobDescriptor}}\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"line line-dashed b-b line-lg pull-in\" ng-show=\"localState.userData.languages && localState.userData.languages.length>0\"></div>\n" +
    "\n" +
    "                    <div class=\"form-group\" ng-show=\"localState.userData.languages && localState.userData.languages.length>0\">\n" +
    "                        <label class=\"col-xs-6 control-label\">{{'profile.language' | translate}}:</label>\n" +
    "                        <div class=\"col-xs-6\">\n" +
    "                            <span ng-repeat=\"nextLanguage in localState.userData.languages\"><img\n" +
    "                                    class=\"language-flag\" src=\"angulr/icons/flags/{{nextLanguage}}.png\"\n" +
    "                                    alt=\"{{nextLanguage}}\" style=\"margin: 3px;\"/></span>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                     \n" +
    "                    \n" +
    "             \n" +
    "             \n" +
    "                     \n" +
    "            <!--<a class=\"list-group-item profileInputContainer\" href ng-hide=\"!localState.userData.languages\">-->\n" +
    "                <!--&lt;!&ndash;<span class=\"badge bg-success\">25</span>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<i class=\"fa fa-comment fa-fw text-muted\"></i>&ndash;&gt;-->\n" +
    "                <!--<div class=\"profileInputLabel\">{{'language' | translate}}:</div>-->\n" +
    "                 <!-- -->\n" +
    "                <!--<span ng-repeat=\"language in localState.userData.languages\"><img-->\n" +
    "                        <!--class=\"language-flag\" src=\"angulr/icons/flags/{{language}}.png\"-->\n" +
    "                        <!--alt=\"{{language}}\" style=\"margin: 3px;\"/></span>-->\n" +
    "            <!--</a>-->\n" +
    "            <!--<a class=\"list-group-item profileInputContainer\" href ng-hide=\"!localState.userData.interests\">-->\n" +
    "                <!--&lt;!&ndash;<span class=\"badge bg-success\">25</span>&ndash;&gt;-->\n" +
    "                <!--&lt;!&ndash;<i class=\"fa fa-comment fa-fw text-muted\"></i>&ndash;&gt;-->\n" +
    "                <!--<div class=\"profileInputLabel\">{{'interests' | translate}}:</div> -->\n" +
    "                <!-- -->\n" +
    "                <!--<span ng-repeat=\"interest in localState.userData.interests\">{{interest}}<span ng-show=\" ! $last \">,</span>-->\n" +
    "									<!--</span>-->\n" +
    "            <!--</a>            -->\n" +
    "           \n" +
    "            \n" +
    "        </div>\n" +
    "        </div>\n" +
    "        </div>\n" +
    "    </div>\n" +
    "    \n" +
    "</div>");
  $templateCache.put("angulr/tpl/hotel/page_wall.html",
    "<div ng-controller=\"WallContr\" class=\"hbox hbox-auto-xs bg-white-only\" ng-init=\"\n" +
    "app.hideFooter = true;\n" +
    "app.header.showTab = true;\n" +
    "app.header.showBackArrow = true;\n" +
    "app.rootSettings.darkBg = true;\n" +
    "app.header.tabIndex = 2;\n" +
    "\">\n" +
    "\n" +
    "    <!--<div data-ng-include=\" 'angulr/tpl/hotel/blocks/notLoggedInfoBlock.html' \" />-->\n" +
    "\n" +
    "    <div class=\"hbox hbox-auto-xs hbox-auto-sm\" style=\"height: 100%\">\n" +
    "        <!-- main -->\n" +
    "        <div ng-class=\"{'col' : isSmartDevice}\">\n" +
    "\n" +
    "            <!-- / main -->\n" +
    "            <!-- right col -->\n" +
    "            <div ng-class=\"{'col w-md bg-white-only b-l bg-auto no-border-xs': isSmartDevice, 'bg-white-only bg-auto no-border-xs' : !isSmartDevice}\">\n" +
    "                <div id=\"wallContent\" ng-class=\"{'app-aside-right pos-fix no-padder w-md w-auto-xs bg-white b-l fadeInRight chatWindow' : isSmartDevice, 'bg-white b-l chatWindow' : !isSmartDevice}\" ng-style=\" localState.chatVisibleHeight? {'height': localState.chatVisibleHeight}:{'height':'90%'}\"><!-- Eugen weg: animated -->\n" +
    "                    <!--style=\"top:auto;\">-->\n" +
    "                    <div class=\"vbox\" ng-style=\"::isSmartDevice? {}:{'height':'90vh'}\">\n" +
    "                       \n" +
    "                        <div class=\"b-b b-t b-light m-b\" ng-hide=\"::isSmartDevice\" style=\"padding: 5px 15px;\">\n" +
    "                            <a ui-sref=\"app.hotel\" class=\"pull-right text-muted text-md\"><i class=\"fa fa-times\"></i></a>\n" +
    "                            <a ui-sref=\"app.hotel\" style=\"text-decoration: underline;\">{{hotelState.profileData.hotelName}}</a> Wall\n" +
    "                        </div>\n" +
    "                        \n" +
    "                        <div class=\"row-row\" >\n" +
    "                            <div id=\"chatContainer\"  class=\"cell\" data-scroll-to-bottom='localState.wallMessages.length+localState.waitingMessages.length*2'>\n" +
    "                                <div class=\"cell-inner padder\" style=\"margin-top: 5px;    padding-right: 5px;\n" +
    "    padding-left: 5px; \">\n" +
    "\n" +
    "                                    <!-- wall list -->\n" +
    "                                    <div class=\"myCenter m-t\" ng-show=\"localState.wallMessages && localState.dataLoaded && localState.wallMessages.length==0\">\n" +
    "                                        <span >{{::'page.wall.noEvents' | translate }}</span>\n" +
    "                                    </div> \n" +
    "                                    <div class=\"myCenter m-t\" ng-hide=\"localState.dataLoaded\">\n" +
    "                                        <span >Loading...</span>\n" +
    "                                    </div>\n" +
    "                                    \n" +
    "                                    <div class=\"m-b b-l m-l-md streamline\" style=\"margin-bottom: 7px;\" ng-repeat=\"nextMessage in localState.wallMessages | orderBy:'creationTime':false track by nextMessage.id\">\n" +
    "                                        <div>\n" +
    "                                            <a class=\"pull-left thumb-sm avatar m-l-n-md\" ui-sref=\"app.user({userId: nextMessage.senderId})\" ng-show=\"hotelState.profileData.logged && hotelState.profileData.fullCheckin || nextMessage.senderId==hotelState.profileData.id\">\n" +
    "                                                <img src=\"{{getProfileImageUrl(nextMessage.senderId)}}\" class=\"img-circle\" style=\"background-color: #fff;\" alt=\"...\">\n" +
    "                                            </a>\n" +
    "                                            <a class=\"pull-left thumb-sm avatar m-l-n-md\" ng-hide=\"hotelState.profileData.logged && hotelState.profileData.fullCheckin || nextMessage.senderId==hotelState.profileData.id\">\n" +
    "                                                <img src=\"{{getProfileImageUrl(nextMessage.senderId, true)}}\" class=\"img-circle\" style=\"background-color: #fff;\" alt=\"...\">\n" +
    "                                            </a>\n" +
    "                                            <div class=\"m-l-lg panel b-a\" style=\"margin-bottom: 15px;border-radius: 3px;\">\n" +
    "                                                <div class=\"panel-heading pos-rlt b-b b-light wall\" style=\"padding: 1px 15px; background-color: beige;\">\n" +
    "                                                    <span class=\"wall arrow left\"></span>\n" +
    "\n" +
    "                                                    <a href ui-sref=\"app.user({userId: nextMessage.senderId})\" ng-class=\"(hotelState.profileData.logged && hotelState.profileData.fullCheckin || nextMessage.senderId==hotelState.profileData.id)? 'customerNameElement' : 'customerNameElement diffuseText' \">{{nextMessage.senderName}}</a>\n" +
    "                                                    <!--<a href ng-hide=\"hotelState.profileData.logged && hotelState.profileData.fullCheckin || nextMessage.senderId==hotelState.profileData.id\" class=\"customerNameElement\">Hidden Information</a>-->\n" +
    "                                                    <!--<span ng-show=\"!message.hotelStaff && hotelCustomersById[message.senderId] && hotelCustomersById[message.senderId].age\"> ({{hotelCustomersById[message.senderId].age}})</span><span ng-hide=\"!hotelCustomersById[message.senderId].jobTitle || hotelCustomersById[message.senderId].hotelStaff\">, {{hotelCustomersById[message.senderId].jobTitle}}</span>-->\n" +
    "                                                    \n" +
    "                                                    <!--<span ng-hide=\"!hotelCustomersById[message.senderId].company || hotelCustomersById[message.senderId].hotelStaff\"><span style=\"margin-left: -3px;\" ng-hide=\"!hotelCustomersById[message.senderId].jobTitle\">&nbsp; </span> {{hotelCustomersById[message.senderId].company}}</span>-->\n" +
    "\n" +
    "                                                    <label class=\"label bg-orange hotelStaff\" ng-show=\"nextMessage.hotelStaff\" style=\"margin-top: -2px;\">{{'system.hotelStaff' | translate}}</label> \n" +
    "                                                \n" +
    "                                                   \n" +
    "                                                    \n" +
    "                                                      <span class=\"text-muted m-l-sm pull-right messageTimeElement\">\n" +
    "                                                        <!--<i class=\"fa fa-clock-o\"></i>-->\n" +
    "                                                         <!--<time>{{nextMessage.sendTime | date:'dd.MM HH:mm'}}</time>-->\n" +
    "                                                          <time style=\"background-color: beige\">{{nextMessage.sendTimeString}}</time>\n" +
    "                                                      </span>\n" +
    "                                                </div>\n" +
    "                                                <div class=\"panel-body\" style=\" word-wrap: break-word;background-color:beige;padding: 0px;padding-left: 15px;\">\n" +
    "                                                    <div ng-class=\"{'staffMessage' : nextMessage.hotelStaff}\">{{nextMessage.message}}</div>\n" +
    "                                                    <!--<div class=\"m-t-sm\">-->\n" +
    "                                                        <!--&lt;!&ndash;<a href ui-toggle-class class=\"btn btn-default btn-xs active\">&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-star-o text-muted text\"></i>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-star text-danger text-active\"></i>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;Like&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<a href class=\"btn btn-default btn-xs\">&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-mail-reply text-muted\"></i> Reply&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "                                                    <!--</div>-->\n" +
    "                                                    <blockquote ng-if=\"nextMessage.specialContent.name\">\n" +
    "                                                        \n" +
    "                                                        <div ng-show=\"nextMessage.specialContent.customerId\" ui-sref=\"app.user({userId: nextMessage.specialContent.customerId})\" style=\"min-height: 37px;\">\n" +
    "                                                            <a class=\"pull-left thumb-sm avatar\">\n" +
    "                                                                <img src=\"{{nextMessage.specialContent.pictureUrl}}\" class=\"img-circle\" style=\"background-color: #fff;\" alt=\"...\">\n" +
    "                                                            </a>\n" +
    "                                                            <a style=\"margin-left: 5px;\">{{nextMessage.specialContent.name}}</a>\n" +
    "                                                        </div>\n" +
    "\n" +
    "                                                        <div ng-show=\"nextMessage.specialContent.activityId\" ui-sref=\"app.activityList({filterHotelId: nextMessage.specialContent.hotelId, filterActivityId: nextMessage.specialContent.activityId})\" style=\"min-height: 37px;\">\n" +
    "                                                            <a class=\"pull-left thumb-sm avatar\">\n" +
    "                                                                <img src=\"{{nextMessage.specialContent.pictureUrl}}\" class=\"img-circle\" style=\"background-color: #fff;\" alt=\"...\">\n" +
    "                                                            </a>\n" +
    "                                                            <a style=\"margin-left: 5px;\">{{nextMessage.specialContent.name}}</a>\n" +
    "                                                        </div>                                     \n" +
    "                                                    \n" +
    "                                                    </blockquote>\n" +
    "                                                    \n" +
    "                                                </div>\n" +
    "                                            </div>\n" +
    "                                        </div>\n" +
    "                                        <!-- .comment-reply -->\n" +
    "                                        <!--<div class=\"m-l-lg\">-->\n" +
    "                                        <!--<a class=\"pull-left thumb-sm avatar\">-->\n" +
    "                                        <!--<img src=\"angulr/img/build/a8.jpg\" alt=\"...\">-->\n" +
    "                                        <!--</a>-->\n" +
    "                                        <!--<div class=\"m-l-xxl panel b-a\">-->\n" +
    "                                        <!--<div class=\"panel-heading pos-rlt\">-->\n" +
    "                                        <!--<span class=\"arrow left pull-up\"></span>-->\n" +
    "                                        <!--<span class=\"text-muted m-l-sm pull-right\">-->\n" +
    "                                        <!--<i class=\"fa fa-clock-o\"></i>-->\n" +
    "                                        <!--10m ago-->\n" +
    "                                        <!--</span>-->\n" +
    "                                        <!--<a href>Mika Sam</a>-->\n" +
    "                                        <!--Report this comment is helpful-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!-- / .comment-reply -->\n" +
    "                                    </div>\n" +
    "\n" +
    "                                    <div class=\"m-b b-l m-l-md streamline\" ng-repeat=\"nextMessage in localState.waitingWallMessages | orderBy:'creationTime':false track by nextMessage.initId\">\n" +
    "                                        <div>\n" +
    "                                            <a class=\"pull-left thumb-sm avatar m-l-n-md\" ui-sref=\"app.user({userId: nextMessage.senderId})\">\n" +
    "                                                <img src=\"{{getProfileImageUrl(nextMessage.senderId)}}\" class=\"img-circle\" style=\"background-color: #fff;\" alt=\"...\">\n" +
    "                                            </a>\n" +
    "                                            <div class=\"m-l-lg panel b-a\" style=\"border-radius: 15px; margin-bottom: 7px;\">\n" +
    "                                                <div class=\"panel-heading pos-rlt b-b b-light wall\" style=\"background-color: beige;\">\n" +
    "                                                    <span class=\"wall arrow left\" ></span>\n" +
    "\n" +
    "                                                    <a href ui-sref=\"app.user({userId: nextMessage.senderId})\" class=\"customerNameElement\">{{nextMessage.senderName}}</a>\n" +
    "\n" +
    "                                                    <!--<span ng-show=\"!message.hotelStaff && hotelCustomersById[message.senderId] && hotelCustomersById[message.senderId].age\"> ({{hotelCustomersById[message.senderId].age}})</span><span ng-hide=\"!hotelCustomersById[message.senderId].jobTitle || hotelCustomersById[message.senderId].hotelStaff\">, {{hotelCustomersById[message.senderId].jobTitle}}</span>-->\n" +
    "\n" +
    "                                                    <!--<span ng-hide=\"!hotelCustomersById[message.senderId].company || hotelCustomersById[message.senderId].hotelStaff\"><span style=\"margin-left: -3px;\" ng-hide=\"!hotelCustomersById[message.senderId].jobTitle\">&nbsp; </span> {{hotelCustomersById[message.senderId].company}}</span>-->\n" +
    "\n" +
    "                                                    <!--<label class=\"label bg-orange hotelStaff\" ng-show=\"message.hotelStaff\" style=\"margin-top: 8px;\">{{'system.hotelStaff' | translate}}</label> -->\n" +
    "                                                \n" +
    "                                                      <span class=\"text-muted m-l-sm pull-right messageTimeElement\">\n" +
    "                                                        <!--<i class=\"fa fa-clock-o\"></i>-->\n" +
    "                                                         <time style=\"background-color: beige;\">{{nextMessage.sendTime | date:'HH:mm'}}</time>\n" +
    "                                                      </span>\n" +
    "                                                </div>\n" +
    "                                                <div class=\"panel-body\" style=\" word-wrap: break-word;background-color:beige;padding: 0px;padding-left: 15px;\">\n" +
    "                                                    <div style=\"vertical-align: top;word-wrap: break-word;\" ng-class=\"{'staffMessage' : nextMessage.hotelStaff}\">\n" +
    "                                                        <img src=\"angulr/icons/loader.gif\" style=\"margin-right: 5px;\" ng-hide=\"localState.showResend\">\n" +
    "                                                        <i class=\"fa fa-exclamation text-muted\"  ng-show=\"localState.showResend\"/>\n" +
    "                                                        {{nextMessage.decodeMessage}}\n" +
    "                                                    </div>\n" +
    "                                                    <!--<div class=\"m-t-sm\">-->\n" +
    "                                                        <!--&lt;!&ndash;<a href ui-toggle-class class=\"btn btn-default btn-xs active\">&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-star-o text-muted text\"></i>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-star text-danger text-active\"></i>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;Like&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<a href class=\"btn btn-default btn-xs\">&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;<i class=\"fa fa-mail-reply text-muted\"></i> Reply&ndash;&gt;-->\n" +
    "                                                        <!--&lt;!&ndash;</a>&ndash;&gt;-->\n" +
    "                                                    <!--</div>-->\n" +
    "\n" +
    "                                                    <small class=\"text-muted\" style=\"position: relative;float: right;margin-top: -15px;\" ng-show=\"localState.showResend\">\n" +
    "                                                        <div ng-click=\"resendWallRessource(nextMessage)\" class=\"btn btn-default btn-xs\">\n" +
    "                                                            <i class=\"fa fa-repeat text-muted\" ></i> Retry\n" +
    "                                                        </div>\n" +
    "                                                        <div  ng-click=\"removeWaitingMessage(nextMessage)\" class=\"btn btn-default btn-xs\">\n" +
    "                                                            <i class=\"fa fa-trash-o text-muted\" ></i> Remove\n" +
    "                                                        </div >\n" +
    "                                                        <!--<time>{{message.sendTimeString}}</time>-->\n" +
    "                                                    </small>\n" +
    "                                                    \n" +
    "                                                </div>\n" +
    "                                            </div>\n" +
    "                                        </div>\n" +
    "                                        <!-- .comment-reply -->\n" +
    "                                        <!--<div class=\"m-l-lg\">-->\n" +
    "                                        <!--<a class=\"pull-left thumb-sm avatar\">-->\n" +
    "                                        <!--<img src=\"angulr/img/build/a8.jpg\" alt=\"...\">-->\n" +
    "                                        <!--</a>-->\n" +
    "                                        <!--<div class=\"m-l-xxl panel b-a\">-->\n" +
    "                                        <!--<div class=\"panel-heading pos-rlt\">-->\n" +
    "                                        <!--<span class=\"arrow left pull-up\"></span>-->\n" +
    "                                        <!--<span class=\"text-muted m-l-sm pull-right\">-->\n" +
    "                                        <!--<i class=\"fa fa-clock-o\"></i>-->\n" +
    "                                        <!--10m ago-->\n" +
    "                                        <!--</span>-->\n" +
    "                                        <!--<a href>Mika Sam</a>-->\n" +
    "                                        <!--Report this comment is helpful-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!--</div>-->\n" +
    "                                        <!-- / .comment-reply -->\n" +
    "                                    </div>\n" +
    "\n" +
    "                                    <chat-box></chat-box>\n" +
    "\n" +
    "                                    <!-- / chat list -->\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                        <div class=\"wrapper m-t b-t b-light\" style=\"margin-bottom: 0px; margin-top:0; padding-top: 0px; min-height: 70px;\">\n" +
    "                            <!--<a class=\"pull-left thumb-sm avatar\" style=\"margin-top: 5px;\"><img src=\"{{getProfileImageUrl()}}\" alt=\"...\"></a>-->\n" +
    "                            <a class=\"pull-left thumb-sm avatar\" style=\"margin-top: 5px;\"><img ng-src=\"{{hotelState.profileData.avatarUrl}}\" alt=\"...\"></a>\n" +
    "                            <div class=\"m-l-xxl\" ng-show=\"app.settings.blockGuestInWall && hotelState.profileData.guestAccount\" >\n" +
    "                                <div class=\"input-group\">\n" +
    "                                    <span class=\"form-control input-lg\" style=\"margin-top: 4px;\">\n" +
    "                                        {{'page.wall.guestMessage' | translate}}:\n" +
    "                                    </span>\n" +
    "                                    <span class=\"input-group-btn\" ng-whow=\"hotelState.profileData.guestAccount\">\n" +
    "                                        <button class=\"btn btn-orange btn-lg\" ng-click=\"clickLoading('app.me')\">\n" +
    "                                            {{'page.wall.register' | translate}}\n" +
    "                                        </button>\n" +
    "                                    </span>\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                            <div class=\"m-l-xxl\" ng-hide=\"app.settings.blockGuestInWall && hotelState.profileData.guestAccount\">\n" +
    "                                <form class=\"m-b-none ng-pristine ng-valid\" ng-submit=\"addMessage()\" name=\"messageForm\">\n" +
    "                                    <div class=\"input-group\">\n" +
    "                                        <input type=\"text\" id=\"wallInput\" autocomplete=\"off\" ng-focus=\"focusChatInput()\" ng-blur=\"checkVisibleHeight();\" class=\"form-control input-lg\" placeholder=\"{{'page.wall.commentPlaceholder' | translate}}\" ng-model=\"localState.newWallMessage\">\n" +
    "                                        <span class=\"input-group-btn\">\n" +
    "                                            <button class=\"btn btn-orange btn-lg\" ng-disabled=\"localState.newWallMessage.length > localState.max\">\n" +
    "                                                <!--{{'page.wall.sendButton' | translate}}-->\n" +
    "                                                <i class=\"fa fa-angle-right\" style=\"font-size: 18px;\"/>\n" +
    "                                            </button>\n" +
    "                                        </span>\n" +
    "                                    </div>\n" +
    "                                    <!--<div style=\"/*  margin-bottom: -20px;*/\">-->\n" +
    "                                        <!--<span class=\"count\" ng-bind=\"localState.max - localState.newWallMessage.length\" ng-class=\"{danger: localState.newWallMessage.length > localState.max}\">{{localState.max}}</span>-->\n" +
    "                                    <!--</div>-->\n" +
    "                                </form>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <!-- / right col -->\n" +
    "        </div>\n" +
    "    </div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/hotel/page_werbung.html",
    "<div class=\"bg-light lter b-b wrapper-md\" ng-init=\"\n" +
    "  app.hideFooter = true;\n" +
    "  app.rootSettings.darkBg = true;\">\n" +
    "  <!--<h1 class=\"m-n font-thin h3\"></h1>-->\n" +
    "  \n" +
    "<div class=\"wrapper-md\" ng-controller=\"WerbungController\">\n" +
    "  <div class=\"row\">\n" +
    "    <div style=\"padding: 5px;\">\n" +
    "        <!--class=\"col-sm-9\">-->\n" +
    "\n" +
    "      <div class=\"panel b-a\">\n" +
    "          <div class=\"item m-l-n-xxs m-r-n-xxs\">\n" +
    "            \n" +
    "              <div class=\"center text-center w-full\" style=\"margin-top:-60px\">\n" +
    "                  <div ui-refresh=\"x\" ui-options=\"{\n" +
    "                percent: {{x*15}},\n" +
    "                lineWidth: 60,\n" +
    "                trackColor: 'rgba(255,255,255,0)',\n" +
    "                barColor: 'rgba(35,183,229,0.7)',\n" +
    "                scaleColor: false,\n" +
    "                size: 120,\n" +
    "                lineCap: 'butt',\n" +
    "                rotate: 0,\n" +
    "                animate: 1000\n" +
    "              }\" class=\"inline\">\n" +
    "                  </div>\n" +
    "              </div>\n" +
    "              <img ng-src=\"angulr/img/build/add/1.jpg\"  class=\"img-full\">\n" +
    "         \n" +
    "          </div>\n" +
    "          <div class=\"text-center\">\n" +
    "              <img ng-src=\"angulr/icons/loader.gif\">\n" +
    "          </div>\n" +
    "\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    \n" +
    "  </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/layout_footer_mobile.html",
    "<div class=\"row\" ng-show=\"isSmartDevice\">\n" +
    "  <div class=\"col-sm-2 hidden-xs\">\n" +
    "\n" +
    "  </div>\n" +
    "  <div class=\"col-sm-8\">\n" +
    "    <div class=\"w-xl w-auto-xs center-block\">\n" +
    "      <div class=\"btn-group btn-group-justified text-center text-sm\">\n" +
    "        \n" +
    "        \n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==0\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.hotelList', 'fh')\" id=\"fh\" class=\"bottomMenuLink leftMenuHotel ignoreUiSref\">\n" +
    "              <!--<i class=\"fa fa-weibo bottomIcon\"></i><br>-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "\n" +
    "              <!--<div class=\"menuBottomItem\">-->\n" +
    "              <!--<b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:2px; top:0;\" ng-show=\"hotelNotification.notificationObjhotelUnreadActivitiesNumber\">{{hotelNotification.notificationObjhotelUnreadActivitiesNumber}}</b>-->\n" +
    "\n" +
    "              <div class=\"menuIconLeft iconHotel menuIconBottom\"/>\n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.hotelList\">Hotels</span>\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.hotelList\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==0\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.activityList', 'f0')\" id=\"f0\" class=\"bottomMenuLink leftMenuHotel ignoreUiSref\">\n" +
    "              <!--<i class=\"fa fa-weibo bottomIcon\" style=\"font-size: 30px;margin-bottom: -5px;\"></i>-->\n" +
    "\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "\n" +
    "              <!--<div class=\"menuBottomItem\">-->\n" +
    "              <!--<b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:2px; top:0;\" ng-show=\"hotelNotification.notificationObjhotelUnreadActivitiesNumber\">{{hotelNotification.notificationObjhotelUnreadActivitiesNumber}}</b>-->\n" +
    "\n" +
    "              <!--<div class=\"menuIconLeft iconHotel menuIconBottom\"/>-->\n" +
    "              <div class=\"menuIconLeft iconActivity menuIconBottom\"/>\n" +
    "\n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.activities\">Activities</span>\n" +
    "              <!--</div>-->\n" +
    "\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.activityList\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==1 && hotelState.profileData.checkedIn && hotelState.profileData.logged\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "              <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.hotel', 'f1')\" id=\"f1\" ui-sref=\"app.hotel\"  class=\"bottomMenuLink leftMenuHotel ignoreUiSref\">\n" +
    "                  <!--<i class=\"fa fa-weibo bottomIcon\"></i><br>-->\n" +
    "                  <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "                \n" +
    "                  <!--<div class=\"menuBottomItem\">-->\n" +
    "                    <b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:2px; top:0;\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "\n" +
    "                    <div class=\"menuIconLeft iconActivity menuIconBottom\"/>\n" +
    "                    <span class=\"text-sm bottomMenuLabel\" translate=\"system.activities\">Hotel</span>\n" +
    "                  <!--</div>-->\n" +
    "      \n" +
    "              </a>\n" +
    "              <a ui-sref=\"app.hotel\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==1 && hotelState.profileData.checkedIn && !hotelState.profileData.logged\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "              <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.hotel', 'f21')\" id=\"f21\" ui-sref=\"app.hotel\"  class=\"bottomMenuLink leftMenuHotel ignoreUiSref\">\n" +
    "                  <!--<i class=\"fa fa-weibo bottomIcon\"></i><br>-->\n" +
    "                  <!--<img src=\"angulr/img/build/menu/bottom/hotel-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "                \n" +
    "                  <!--<div class=\"menuBottomItem\">-->\n" +
    "                    <b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:2px; top:0;\" ng-show=\"hotelNotification.notificationObj.hotelUnreadActivitiesNumber\">{{hotelNotification.notificationObj.hotelUnreadActivitiesNumber}}</b>\n" +
    "\n" +
    "                    <div class=\"menuIconLeft iconActivity menuIconBottom\"/>\n" +
    "                    <span class=\"text-sm bottomMenuLabel\" translate=\"system.activities\">HotelPreview</span>\n" +
    "                  <!--</div>-->\n" +
    "      \n" +
    "              </a>\n" +
    "              <a ui-sref=\"app.hotelPreview\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.chatList', 'f2', null, {hotelScope: hotelState.profileData.checkedIn && app.header.tabIndex==1})\"  id=\"f2\"  class=\"bottomMenuLink ignoreUiSref\">\n" +
    "              <b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:6px; top:0;\" ng-show=\"hotelNotification.notificationObj.unreadChatsCounter\">{{hotelNotification.notificationObj.unreadChatsCounter}}</b>\n" +
    "              <!--<i class=\"fa fa-comments-o bottomIcon\"></i><br>-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/chat-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "              <div class=\"menuIconLeft iconChat menuIconBottom\"/>\n" +
    "  \n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.contacts\" ng-show=\"app.header.tabIndex==0\">Contacts</span>\n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.guests\" ng-show=\"app.header.tabIndex==1\">Guests</span>\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.chatList\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==1 && hotelState.profileData.checkedIn\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.wall', 'f3')\" id=\"f3\" class=\"bottomMenuLink ignoreUiSref\">\n" +
    "              <!--<i class=\"fa fa-comment-o  bottomIcon\"></i><br>-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/wall-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "             \n" +
    "              <b class=\"badge bg-orange pull-right\" style=\"position: absolute; right:2px; top:0;\" ng-show=\"hotelNotification.notificationObj.hotelUnreadWallNumber\">{{hotelNotification.notificationObj.hotelUnreadWallNumber}}</b>\n" +
    "\n" +
    "\n" +
    "              <div class=\"menuIconLeft iconWall menuIconBottom\"/>\n" +
    "  \n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.wall\">Wallpost</span>\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.wall\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div> \n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading('app.me', 'f4')\"   id=\"f4\" class=\"bottomMenuLink ignoreUiSref\">\n" +
    "              <!--<i class=\"fa icon-user fa-fw  bottomIcon\"></i><br>-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/me-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "              <div class=\"menuIconLeft iconMe menuIconBottom\"/>\n" +
    "  \n" +
    "              <span class=\"text-sm bottomMenuLabel\">\n" +
    "                <span class=\"badge bg-dark\" style=\"margin-top: -2px;\" ng-show=\"hotelState.profileData.guestAccount\">Guest</span>\n" +
    "                <span ng-hide=\"hotelState.profileData.guestAccount\" translate=\"system.profile\">Me</span>\n" +
    "              </span>\n" +
    "            </a>\n" +
    "            <a ui-sref=\"app.me\" style=\"display: none;\"></a>\n" +
    "\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==1 && hotelState.profileData.checkedIn && !hotelState.profileData.hotelStaff\">  \n" +
    "          \n" +
    "          <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"checkinService.resetCheckin();\" id=\"f5\"  class=\"bottomMenuLink\">\n" +
    "            <!--<i class=\"fa fa-sign-out  bottomIcon\"></i><br>-->\n" +
    "            <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "            <div class=\"menuIconLeft iconCheckout menuIconBottom\"/>\n" +
    "\n" +
    "            <span class=\"text-sm bottomMenuLabel\" translate=\"system.checkout\">Checkout</span>\n" +
    "          </a>\n" +
    "        </div> \n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"!hotelState.profileData.logged && app.header.tabIndex==0\">  \n" +
    "          \n" +
    "          <a class=\"wrapper-xs block menuBottomItem\" ui-sref=\"access.login\" id=\"f7\"  class=\"bottomMenuLink ignoreUiSref\">\n" +
    "            <!--<i class=\"fa fa-sign-out  bottomIcon\"></i><br>-->\n" +
    "            <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "            <div class=\"menuIconLeft iconCheckout menuIconBottom\"/>\n" +
    "\n" +
    "            <span class=\"text-sm bottomMenuLabel\" translate=\"system.login\">Login</span>\n" +
    "          </a>\n" +
    "        </div>\n" +
    "          \n" +
    "          <div class=\"btn-group\" ng-show=\"hotelState.profileData.logged && (hotelState.profileData.hotelStaff || app.header.tabIndex==0)\">  \n" +
    "          \n" +
    "          <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"loginService.resetLogin();\" id=\"f77\"  class=\"bottomMenuLink\">\n" +
    "            <!--<i class=\"fa fa-sign-out  bottomIcon\"></i><br>-->\n" +
    "            <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "            <div class=\"menuIconLeft iconCheckout menuIconBottom\"/>\n" +
    "\n" +
    "            <span class=\"text-sm bottomMenuLabel\" translate=\"system.logout\">Logout</span>\n" +
    "          </a>\n" +
    "        </div>\n" +
    "        \n" +
    "        <div class=\"btn-group\" ng-show=\"app.header.tabIndex==1 && !hotelState.profileData.checkedIn && (!hotelState.profileData.hotelStaff)\">\n" +
    "          <div  ui-sref-active=\"active\" class=\"leftMenuHotel\">\n" +
    "            <a class=\"wrapper-xs block menuBottomItem\" ng-click=\"clickLoading(null, 'f6')\" id=\"f6\" ui-sref=\"app.checkin\"  class=\"bottomMenuLink\">\n" +
    "              <!--<i class=\"fa fa-sign-out  bottomIcon\"></i><br>-->\n" +
    "              <!--<img src=\"angulr/img/build/menu/bottom/checkout-inactive.png\" class=\"bottomMenuIcon\"><br>-->\n" +
    "              <div class=\"menuIconLeft iconCheckout menuIconBottom\"/>\n" +
    "  \n" +
    "              <span class=\"text-sm bottomMenuLabel\" translate=\"system.checkin\">Checkin</span>\n" +
    "            </a>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        \n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <div class=\"col-sm-2 hidden-xs\">\n" +
    "\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/layout_fullwidth.html",
    "<!-- hbox layout -->\n" +
    "<div class=\"hbox hbox-auto-xs bg-light \" ng-controller=\"JVectorMapDemoCtrl\" ng-init=\"\n" +
    "  app.settings.asideFolded = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = true\" \n" +
    "  >\n" +
    "  <!-- column -->\n" +
    "  <div class=\"col item\">\n" +
    "    <div ui-jq=\"vectorMap\" class=\"h-full\" style=\"min-height:240px;\" ui-options=\"{\n" +
    "      map: 'us_aea_en',\n" +
    "      markers: {{usa_markers}},\n" +
    "      normalizeFunction: 'polynomial',\n" +
    "      backgroundColor: 'transparent',\n" +
    "      zoomMin: 0.5,            \n" +
    "      regionsSelectable: true,\n" +
    "      markersSelectable: true,\n" +
    "      regionsSelectableOne: true,\n" +
    "      markersSelectableOne: true,\n" +
    "      focusOn: {\n" +
    "        x:0,\n" +
    "        y:0,\n" +
    "        scale: 0.6\n" +
    "      },\n" +
    "      series: {\n" +
    "        markers: [{\n" +
    "          attribute: 'r',\n" +
    "          scale: [5, 15],\n" +
    "          values: [\n" +
    "            100.70,\n" +
    "            255.16,\n" +
    "            310.69,\n" +
    "            605.17,\n" +
    "            248.31,\n" +
    "            107.35,\n" +
    "            217.22\n" +
    "          ]\n" +
    "        }]\n" +
    "      },\n" +
    "      regionStyle: {\n" +
    "        initial: {\n" +
    "          fill: '#dde6e9',\n" +
    "          stroke: '#edf1f2',\n" +
    "          'stroke-width': 2,\n" +
    "          'stroke-opacity': 1\n" +
    "        },\n" +
    "        hover: {\n" +
    "          fill: '#fff'\n" +
    "        },\n" +
    "        selected: {\n" +
    "          fill: '{{app.color.info}}'\n" +
    "        },\n" +
    "      },\n" +
    "      markerStyle: {\n" +
    "        initial: {\n" +
    "          fill: '{{app.color.info}}',\n" +
    "          stroke: '#fff',\n" +
    "          r: 10\n" +
    "        },\n" +
    "        hover: {\n" +
    "          stroke: '#fff'\n" +
    "        },\n" +
    "        selected: {\n" +
    "          fill: '{{app.color.warning}}'\n" +
    "        }\n" +
    "      }\n" +
    "    }\">\n" +
    "    </div>\n" +
    "    <div class=\"top\" style=\"top:20px;left:60px\">\n" +
    "      <div class=\"panel no-border w-md\">\n" +
    "        <div class=\"panel-heading \">\n" +
    "          <a href class=\"pull-right\" ng-click=\"isCollapsed = !isCollapsed\" ui-toggle-class>\n" +
    "            <i class=\"fa fa-angle-up text\"></i>\n" +
    "            <i class=\"fa fa-angle-down text-active\"></i>\n" +
    "          </a>\n" +
    "          <span class=\"font-bold\">Mark Detail</span>\n" +
    "        </div>\n" +
    "        <div collapse=\"isCollapsed\">\n" +
    "          <div class=\"panel-body bg-light lter\">\n" +
    "            <div class=\"scrollable\" style=\"max-height:110px\" ui-jq=\"slimScroll\" ui-options=\"{height:'110px', size:'6px'}\">\n" +
    "              <p>\n" +
    "              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id neque quam. Aliquam sollicitudin venenatis ipsum ac feugiat. \n" +
    "              </p>\n" +
    "              <p>\n" +
    "              Vestibulum ullamcorper sodales nisi nec condimentum. Mauris convallis mauris at pellentesque volutpat.\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </div>\n" +
    "          <div class=\"panel-footer\">\n" +
    "            <i class=\"fa fa-fw m-r-xs text-info fa-bookmark\"></i><span class=\"font-bold\">37</span>\n" +
    "            <i class=\"fa fa-fw m-r-xs text-info fa-star m-l\"></i><span class=\"font-bold\">120</span>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"bottom text-right w-full\">\n" +
    "      <small class=\"text-muted wrapper-sm block\">Data @ naturalearthdata.com.</small>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <!-- /column -->\n" +
    "\n" +
    "  <!-- column -->\n" +
    "  <div class=\"col w-md lter b-l\">\n" +
    "    <div class=\"vbox\">\n" +
    "      <div class=\"wrapper b-b b-light\">Map</div>\n" +
    "      <div class=\"row-row\">\n" +
    "        <div class=\"cell\">\n" +
    "          <div class=\"cell-inner\">\n" +
    "            <div class=\"wrapper-md\">\n" +
    "              This is the scrollable content\n" +
    "              <p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>\n" +
    "              You got the bottom\n" +
    "            </div>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"wrapper b-t b-light text-center\">\n" +
    "        <p>Footer with fluid height</p>\n" +
    "        <a href class=\"btn btn-info\">Create Marker</a>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <!-- /column -->\n" +
    "</div>\n" +
    "<!-- /hbox layout -->");
  $templateCache.put("angulr/tpl/layout_mobile.html",
    "<div class=\"hbox hbox-auto-xs\" ng-init=\"\n" +
    "  app.settings.asideFixed = true;\n" +
    "  app.settings.asideDock = false;\n" +
    "  app.settings.container = false;\n" +
    "  app.hideAside = false;\n" +
    "  app.hideFooter = false;\n" +
    "  \">\n" +
    "  <div class=\"col w-md w-auto-xs bg-light lter b-r\">\n" +
    "    <div class=\"wrapper\">\n" +
    "      Left\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <div class=\"col\">\n" +
    "    <div class=\"wrapper\">\n" +
    "      Main\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/material/button.html",
    "<div class=\"card\">\n" +
    "  <div class=\"card-heading\">\n" +
    "    <h2>Button Usage</h2>\n" +
    "  </div>\n" +
    "  <div class=\"card-body\">\n" +
    "      <div class=\"m-b\">\n" +
    "        <h5 class=\"m-t-xs\">Flat button</h5>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw\">Default</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-primary\">Primary</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-info\">Info</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-success\">Success</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-warning\">Warning</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-danger\">Danger</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-accent\">Accent</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-flat m-b btn-fw text-danger\" disabled>Disabled</button>\n" +
    "      </div>\n" +
    "      <div class=\"m-b\">\n" +
    "        <h5 class=\"m-t-xs\">Raised button</h5>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw bg-white\">White</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw indigo\">Indigo</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw pink\">Pink</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw blue\">Blue</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw green\">Green</button><br>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw orange\">Orange</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw red\">Red</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw purple\">Purple</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw red\" disabled>Disabled</button>\n" +
    "      </div>\n" +
    "      <div class=\"m-b\">\n" +
    "        <h5 class=\"m-t-xs\">Themed</h5>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw indigo-300\">Indigo</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw pink-300\">Pink</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw blue-200\">Blue</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw green-700\">Green</button><br>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw orange-200\">Orange</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw cyan-900\">Cyan</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw purple-400\">Purple</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-raised m-b btn-fw brown-200\">Brown</button>\n" +
    "      </div>\n" +
    "      <div class=\"m-b\">\n" +
    "        <h5 class=\"m-t-xs\">Fab button</h5>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b bg-white\">1</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b indigo\">2</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b blue\">3</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b green\">4</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b cyan\">5</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b red\">6</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b pink\">7</button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b grey\" disabled>8</button>\n" +
    "      </div>\n" +
    "      <div class=\"m-b\">\n" +
    "        <h5 class=\"m-t-xs\">Icon</h5>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b bg-white\"><i class=\"mdi-content-add i-24\"></i></button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b indigo\"><i class=\"mdi-editor-mode-edit i-24\"></i></button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b pink\"><i class=\"mdi-file-file-download i-24\"></i></button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b blue\"><i class=\"mdi-hardware-keyboard-control i-24\"></i></button>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab m-b grey\" disabled><i class=\"mdi-maps-flight i-24\"></i></button>\n" +
    "      </div>\n" +
    "  </div>\n" +
    "</div>\n" +
    "<a href class=\"md-btn md-fab md-fab-bottom-right pos-fix teal\"><i class=\"mdi-editor-mode-edit i-24\"></i></a>\n" +
    "");
  $templateCache.put("angulr/tpl/material/card.html",
    "<div class=\"row\">\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c1.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "          <div class=\"bottom text-white wrapper\">\n" +
    "            Title\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <a md-ink-ripple class=\"md-btn md-raised md-fab brown m-r md-fab-offset pull-right\"><span class=\"text-white\">23</span></a>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c2.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "          <div class=\"bottom text-white wrapper\">\n" +
    "            Title\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab md-raised bg-white m-r md-fab-offset pull-right\">100</button>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c3.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "          <div class=\"bottom text-white wrapper\">\n" +
    "            Single Title\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab green m-r md-fab-offset pull-right\"><i class=\"mdi-action-favorite i-24\"></i></button>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c4.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "        </div>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab md-raised indigo m-r md-fab-offset pull-right\">100</button>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c5.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "        </div>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab md-raised pink m-r md-fab-offset pull-right\"><i class=\"mdi-content-add i-24\"></i></button>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-4\">\n" +
    "      <div class=\"panel panel-card\">\n" +
    "        <div class=\"item\">\n" +
    "          <img src=\"img/c6.jpg\" class=\"w-full r-t\" alt=\"Washed Out\">\n" +
    "        </div>\n" +
    "        <button md-ink-ripple class=\"md-btn md-fab md-raised green-100 m-r md-fab-offset pull-right\"><i class=\"mdi-content-add i-24\"></i></button>\n" +
    "        <div class=\"wrapper\">\n" +
    "          <h3>Paracosm</h3>\n" +
    "          <p>\n" +
    "            The titles of Washed Out's breakthrough song and the first single from Paracosm share the\n" +
    "            two most important words in Ernest Greene's musical language: feel it. It's a simple request, as well...\n" +
    "          </p>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/color.html",
    "<div class=\"card\">\n" +
    "  <div class=\"card-heading\">\n" +
    "    <h2>Color palette</h2>\n" +
    "    <small>Material Design is a visual language with specifications for innovative user experiences (UX) and user interface (UI) elements. Themes convey meaning through color, tones, and contrasts, similar to how Layouts convey meaning through keylines and alignments.</small>\n" +
    "  </div>\n" +
    "  <div class=\"card-body\">\n" +
    "    <div class=\"row no-gutter m-b-lg\">\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm red-500\">\n" +
    "          <div class=\"m-b-lg\">Red</div>\n" +
    "          @red-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm red-50\">@red-50</div>\n" +
    "        <div class=\"wrapper-sm red-100\">@red-100</div>\n" +
    "        <div class=\"wrapper-sm red-200\">@red-200</div>\n" +
    "        <div class=\"wrapper-sm red-300\">@red-300</div>\n" +
    "        <div class=\"wrapper-sm red-400\">@red-400</div>\n" +
    "        <div class=\"wrapper-sm red-500\">@red-500</div>\n" +
    "        <div class=\"wrapper-sm red-600\">@red-600</div>\n" +
    "        <div class=\"wrapper-sm red-700\">@red-700</div>\n" +
    "        <div class=\"wrapper-sm red-800\">@red-800</div>\n" +
    "        <div class=\"wrapper-sm red-900\">@red-900</div>\n" +
    "        <div class=\"wrapper-sm red-A100\">@red-A100</div>\n" +
    "        <div class=\"wrapper-sm red-A200\">@red-A200</div>\n" +
    "        <div class=\"wrapper-sm red-A400\">@red-A400</div>\n" +
    "        <div class=\"wrapper-sm red-A700\">@red-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm pink-500\">\n" +
    "          <div class=\"m-b-lg\">pink</div>\n" +
    "          @pink-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm pink-50\">@pink-50</div>\n" +
    "        <div class=\"wrapper-sm pink-100\">@pink-100</div>\n" +
    "        <div class=\"wrapper-sm pink-200\">@pink-200</div>\n" +
    "        <div class=\"wrapper-sm pink-300\">@pink-300</div>\n" +
    "        <div class=\"wrapper-sm pink-400\">@pink-400</div>\n" +
    "        <div class=\"wrapper-sm pink-500\">@pink-500</div>\n" +
    "        <div class=\"wrapper-sm pink-600\">@pink-600</div>\n" +
    "        <div class=\"wrapper-sm pink-700\">@pink-700</div>\n" +
    "        <div class=\"wrapper-sm pink-800\">@pink-800</div>\n" +
    "        <div class=\"wrapper-sm pink-900\">@pink-900</div>\n" +
    "        <div class=\"wrapper-sm pink-A100\">@pink-A100</div>\n" +
    "        <div class=\"wrapper-sm pink-A200\">@pink-A200</div>\n" +
    "        <div class=\"wrapper-sm pink-A400\">@pink-A400</div>\n" +
    "        <div class=\"wrapper-sm pink-A700\">@pink-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm purple-500\">\n" +
    "          <div class=\"m-b-lg\">purple</div>\n" +
    "          @purple-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm purple-50\">@purple-50</div>\n" +
    "        <div class=\"wrapper-sm purple-100\">@purple-100</div>\n" +
    "        <div class=\"wrapper-sm purple-200\">@purple-200</div>\n" +
    "        <div class=\"wrapper-sm purple-300\">@purple-300</div>\n" +
    "        <div class=\"wrapper-sm purple-400\">@purple-400</div>\n" +
    "        <div class=\"wrapper-sm purple-500\">@purple-500</div>\n" +
    "        <div class=\"wrapper-sm purple-600\">@purple-600</div>\n" +
    "        <div class=\"wrapper-sm purple-700\">@purple-700</div>\n" +
    "        <div class=\"wrapper-sm purple-800\">@purple-800</div>\n" +
    "        <div class=\"wrapper-sm purple-900\">@purple-900</div>\n" +
    "        <div class=\"wrapper-sm purple-A100\">@purple-A100</div>\n" +
    "        <div class=\"wrapper-sm purple-A200\">@purple-A200</div>\n" +
    "        <div class=\"wrapper-sm purple-A400\">@purple-A400</div>\n" +
    "        <div class=\"wrapper-sm purple-A700\">@purple-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm deep-purple-500\">\n" +
    "          <div class=\"m-b-lg\">deep-purple</div>\n" +
    "          @deep-purple-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm deep-purple-50\">@deep-purple-50</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-100\">@deep-purple-100</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-200\">@deep-purple-200</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-300\">@deep-purple-300</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-400\">@deep-purple-400</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-500\">@deep-purple-500</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-600\">@deep-purple-600</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-700\">@deep-purple-700</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-800\">@deep-purple-800</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-900\">@deep-purple-900</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-A100\">@deep-purple-A100</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-A200\">@deep-purple-A200</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-A400\">@deep-purple-A400</div>\n" +
    "        <div class=\"wrapper-sm deep-purple-A700\">@deep-purple-A700</div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"row no-gutter m-b-lg\">\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm indigo-500\">\n" +
    "          <div class=\"m-b-lg\">indigo</div>\n" +
    "          @indigo-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm indigo-50\">@indigo-50</div>\n" +
    "        <div class=\"wrapper-sm indigo-100\">@indigo-100</div>\n" +
    "        <div class=\"wrapper-sm indigo-200\">@indigo-200</div>\n" +
    "        <div class=\"wrapper-sm indigo-300\">@indigo-300</div>\n" +
    "        <div class=\"wrapper-sm indigo-400\">@indigo-400</div>\n" +
    "        <div class=\"wrapper-sm indigo-500\">@indigo-500</div>\n" +
    "        <div class=\"wrapper-sm indigo-600\">@indigo-600</div>\n" +
    "        <div class=\"wrapper-sm indigo-700\">@indigo-700</div>\n" +
    "        <div class=\"wrapper-sm indigo-800\">@indigo-800</div>\n" +
    "        <div class=\"wrapper-sm indigo-900\">@indigo-900</div>\n" +
    "        <div class=\"wrapper-sm indigo-A100\">@indigo-A100</div>\n" +
    "        <div class=\"wrapper-sm indigo-A200\">@indigo-A200</div>\n" +
    "        <div class=\"wrapper-sm indigo-A400\">@indigo-A400</div>\n" +
    "        <div class=\"wrapper-sm indigo-A700\">@indigo-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm blue-500\">\n" +
    "          <div class=\"m-b-lg\">blue</div>\n" +
    "          @blue-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm blue-50\">@blue-50</div>\n" +
    "        <div class=\"wrapper-sm blue-100\">@blue-100</div>\n" +
    "        <div class=\"wrapper-sm blue-200\">@blue-200</div>\n" +
    "        <div class=\"wrapper-sm blue-300\">@blue-300</div>\n" +
    "        <div class=\"wrapper-sm blue-400\">@blue-400</div>\n" +
    "        <div class=\"wrapper-sm blue-500\">@blue-500</div>\n" +
    "        <div class=\"wrapper-sm blue-600\">@blue-600</div>\n" +
    "        <div class=\"wrapper-sm blue-700\">@blue-700</div>\n" +
    "        <div class=\"wrapper-sm blue-800\">@blue-800</div>\n" +
    "        <div class=\"wrapper-sm blue-900\">@blue-900</div>\n" +
    "        <div class=\"wrapper-sm blue-A100\">@blue-A100</div>\n" +
    "        <div class=\"wrapper-sm blue-A200\">@blue-A200</div>\n" +
    "        <div class=\"wrapper-sm blue-A400\">@blue-A400</div>\n" +
    "        <div class=\"wrapper-sm blue-A700\">@blue-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm light-blue-500\">\n" +
    "          <div class=\"m-b-lg\">light-blue</div>\n" +
    "          @light-blue-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm light-blue-50\">@light-blue-50</div>\n" +
    "        <div class=\"wrapper-sm light-blue-100\">@light-blue-100</div>\n" +
    "        <div class=\"wrapper-sm light-blue-200\">@light-blue-200</div>\n" +
    "        <div class=\"wrapper-sm light-blue-300\">@light-blue-300</div>\n" +
    "        <div class=\"wrapper-sm light-blue-400\">@light-blue-400</div>\n" +
    "        <div class=\"wrapper-sm light-blue-500\">@light-blue-500</div>\n" +
    "        <div class=\"wrapper-sm light-blue-600\">@light-blue-600</div>\n" +
    "        <div class=\"wrapper-sm light-blue-700\">@light-blue-700</div>\n" +
    "        <div class=\"wrapper-sm light-blue-800\">@light-blue-800</div>\n" +
    "        <div class=\"wrapper-sm light-blue-900\">@light-blue-900</div>\n" +
    "        <div class=\"wrapper-sm light-blue-A100\">@light-blue-A100</div>\n" +
    "        <div class=\"wrapper-sm light-blue-A200\">@light-blue-A200</div>\n" +
    "        <div class=\"wrapper-sm light-blue-A400\">@light-blue-A400</div>\n" +
    "        <div class=\"wrapper-sm light-blue-A700\">@light-blue-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm cyan-500\">\n" +
    "          <div class=\"m-b-lg\">cyan</div>\n" +
    "          @cyan-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm cyan-50\">@cyan-50</div>\n" +
    "        <div class=\"wrapper-sm cyan-100\">@cyan-100</div>\n" +
    "        <div class=\"wrapper-sm cyan-200\">@cyan-200</div>\n" +
    "        <div class=\"wrapper-sm cyan-300\">@cyan-300</div>\n" +
    "        <div class=\"wrapper-sm cyan-400\">@cyan-400</div>\n" +
    "        <div class=\"wrapper-sm cyan-500\">@cyan-500</div>\n" +
    "        <div class=\"wrapper-sm cyan-600\">@cyan-600</div>\n" +
    "        <div class=\"wrapper-sm cyan-700\">@cyan-700</div>\n" +
    "        <div class=\"wrapper-sm cyan-800\">@cyan-800</div>\n" +
    "        <div class=\"wrapper-sm cyan-900\">@cyan-900</div>\n" +
    "        <div class=\"wrapper-sm cyan-A100\">@cyan-A100</div>\n" +
    "        <div class=\"wrapper-sm cyan-A200\">@cyan-A200</div>\n" +
    "        <div class=\"wrapper-sm cyan-A400\">@cyan-A400</div>\n" +
    "        <div class=\"wrapper-sm cyan-A700\">@cyan-A700</div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"row no-gutter m-b-lg\">\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm teal-500\">\n" +
    "          <div class=\"m-b-lg\">teal</div>\n" +
    "          @teal-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm teal-50\">@teal-50</div>\n" +
    "        <div class=\"wrapper-sm teal-100\">@teal-100</div>\n" +
    "        <div class=\"wrapper-sm teal-200\">@teal-200</div>\n" +
    "        <div class=\"wrapper-sm teal-300\">@teal-300</div>\n" +
    "        <div class=\"wrapper-sm teal-400\">@teal-400</div>\n" +
    "        <div class=\"wrapper-sm teal-500\">@teal-500</div>\n" +
    "        <div class=\"wrapper-sm teal-600\">@teal-600</div>\n" +
    "        <div class=\"wrapper-sm teal-700\">@teal-700</div>\n" +
    "        <div class=\"wrapper-sm teal-800\">@teal-800</div>\n" +
    "        <div class=\"wrapper-sm teal-900\">@teal-900</div>\n" +
    "        <div class=\"wrapper-sm teal-A100\">@teal-A100</div>\n" +
    "        <div class=\"wrapper-sm teal-A200\">@teal-A200</div>\n" +
    "        <div class=\"wrapper-sm teal-A400\">@teal-A400</div>\n" +
    "        <div class=\"wrapper-sm teal-A700\">@teal-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm green-500\">\n" +
    "          <div class=\"m-b-lg\">green</div>\n" +
    "          @green-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm green-50\">@green-50</div>\n" +
    "        <div class=\"wrapper-sm green-100\">@green-100</div>\n" +
    "        <div class=\"wrapper-sm green-200\">@green-200</div>\n" +
    "        <div class=\"wrapper-sm green-300\">@green-300</div>\n" +
    "        <div class=\"wrapper-sm green-400\">@green-400</div>\n" +
    "        <div class=\"wrapper-sm green-500\">@green-500</div>\n" +
    "        <div class=\"wrapper-sm green-600\">@green-600</div>\n" +
    "        <div class=\"wrapper-sm green-700\">@green-700</div>\n" +
    "        <div class=\"wrapper-sm green-800\">@green-800</div>\n" +
    "        <div class=\"wrapper-sm green-900\">@green-900</div>\n" +
    "        <div class=\"wrapper-sm green-A100\">@green-A100</div>\n" +
    "        <div class=\"wrapper-sm green-A200\">@green-A200</div>\n" +
    "        <div class=\"wrapper-sm green-A400\">@green-A400</div>\n" +
    "        <div class=\"wrapper-sm green-A700\">@green-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm light-green-500\">\n" +
    "          <div class=\"m-b-lg\">light-green</div>\n" +
    "          @light-green-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm light-green-50\">@light-green-50</div>\n" +
    "        <div class=\"wrapper-sm light-green-100\">@light-green-100</div>\n" +
    "        <div class=\"wrapper-sm light-green-200\">@light-green-200</div>\n" +
    "        <div class=\"wrapper-sm light-green-300\">@light-green-300</div>\n" +
    "        <div class=\"wrapper-sm light-green-400\">@light-green-400</div>\n" +
    "        <div class=\"wrapper-sm light-green-500\">@light-green-500</div>\n" +
    "        <div class=\"wrapper-sm light-green-600\">@light-green-600</div>\n" +
    "        <div class=\"wrapper-sm light-green-700\">@light-green-700</div>\n" +
    "        <div class=\"wrapper-sm light-green-800\">@light-green-800</div>\n" +
    "        <div class=\"wrapper-sm light-green-900\">@light-green-900</div>\n" +
    "        <div class=\"wrapper-sm light-green-A100\">@light-green-A100</div>\n" +
    "        <div class=\"wrapper-sm light-green-A200\">@light-green-A200</div>\n" +
    "        <div class=\"wrapper-sm light-green-A400\">@light-green-A400</div>\n" +
    "        <div class=\"wrapper-sm light-green-A700\">@light-green-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm lime-500\">\n" +
    "          <div class=\"m-b-lg\">lime</div>\n" +
    "          @lime-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm lime-50\">@lime-50</div>\n" +
    "        <div class=\"wrapper-sm lime-100\">@lime-100</div>\n" +
    "        <div class=\"wrapper-sm lime-200\">@lime-200</div>\n" +
    "        <div class=\"wrapper-sm lime-300\">@lime-300</div>\n" +
    "        <div class=\"wrapper-sm lime-400\">@lime-400</div>\n" +
    "        <div class=\"wrapper-sm lime-500\">@lime-500</div>\n" +
    "        <div class=\"wrapper-sm lime-600\">@lime-600</div>\n" +
    "        <div class=\"wrapper-sm lime-700\">@lime-700</div>\n" +
    "        <div class=\"wrapper-sm lime-800\">@lime-800</div>\n" +
    "        <div class=\"wrapper-sm lime-900\">@lime-900</div>\n" +
    "        <div class=\"wrapper-sm lime-A100\">@lime-A100</div>\n" +
    "        <div class=\"wrapper-sm lime-A200\">@lime-A200</div>\n" +
    "        <div class=\"wrapper-sm lime-A400\">@lime-A400</div>\n" +
    "        <div class=\"wrapper-sm lime-A700\">@lime-A700</div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"row no-gutter m-b-lg\">\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm yellow-500\">\n" +
    "          <div class=\"m-b-lg\">yellow</div>\n" +
    "          @yellow-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm yellow-50\">@yellow-50</div>\n" +
    "        <div class=\"wrapper-sm yellow-100\">@yellow-100</div>\n" +
    "        <div class=\"wrapper-sm yellow-200\">@yellow-200</div>\n" +
    "        <div class=\"wrapper-sm yellow-300\">@yellow-300</div>\n" +
    "        <div class=\"wrapper-sm yellow-400\">@yellow-400</div>\n" +
    "        <div class=\"wrapper-sm yellow-500\">@yellow-500</div>\n" +
    "        <div class=\"wrapper-sm yellow-600\">@yellow-600</div>\n" +
    "        <div class=\"wrapper-sm yellow-700\">@yellow-700</div>\n" +
    "        <div class=\"wrapper-sm yellow-800\">@yellow-800</div>\n" +
    "        <div class=\"wrapper-sm yellow-900\">@yellow-900</div>\n" +
    "        <div class=\"wrapper-sm yellow-A100\">@yellow-A100</div>\n" +
    "        <div class=\"wrapper-sm yellow-A200\">@yellow-A200</div>\n" +
    "        <div class=\"wrapper-sm yellow-A400\">@yellow-A400</div>\n" +
    "        <div class=\"wrapper-sm yellow-A700\">@yellow-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm amber-500\">\n" +
    "          <div class=\"m-b-lg\">amber</div>\n" +
    "          @amber-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm amber-50\">@amber-50</div>\n" +
    "        <div class=\"wrapper-sm amber-100\">@amber-100</div>\n" +
    "        <div class=\"wrapper-sm amber-200\">@amber-200</div>\n" +
    "        <div class=\"wrapper-sm amber-300\">@amber-300</div>\n" +
    "        <div class=\"wrapper-sm amber-400\">@amber-400</div>\n" +
    "        <div class=\"wrapper-sm amber-500\">@amber-500</div>\n" +
    "        <div class=\"wrapper-sm amber-600\">@amber-600</div>\n" +
    "        <div class=\"wrapper-sm amber-700\">@amber-700</div>\n" +
    "        <div class=\"wrapper-sm amber-800\">@amber-800</div>\n" +
    "        <div class=\"wrapper-sm amber-900\">@amber-900</div>\n" +
    "        <div class=\"wrapper-sm amber-A100\">@amber-A100</div>\n" +
    "        <div class=\"wrapper-sm amber-A200\">@amber-A200</div>\n" +
    "        <div class=\"wrapper-sm amber-A400\">@amber-A400</div>\n" +
    "        <div class=\"wrapper-sm amber-A700\">@amber-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm orange-500\">\n" +
    "          <div class=\"m-b-lg\">orange</div>\n" +
    "          @orange-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm orange-50\">@orange-50</div>\n" +
    "        <div class=\"wrapper-sm orange-100\">@orange-100</div>\n" +
    "        <div class=\"wrapper-sm orange-200\">@orange-200</div>\n" +
    "        <div class=\"wrapper-sm orange-300\">@orange-300</div>\n" +
    "        <div class=\"wrapper-sm orange-400\">@orange-400</div>\n" +
    "        <div class=\"wrapper-sm orange-500\">@orange-500</div>\n" +
    "        <div class=\"wrapper-sm orange-600\">@orange-600</div>\n" +
    "        <div class=\"wrapper-sm orange-700\">@orange-700</div>\n" +
    "        <div class=\"wrapper-sm orange-800\">@orange-800</div>\n" +
    "        <div class=\"wrapper-sm orange-900\">@orange-900</div>\n" +
    "        <div class=\"wrapper-sm orange-A100\">@orange-A100</div>\n" +
    "        <div class=\"wrapper-sm orange-A200\">@orange-A200</div>\n" +
    "        <div class=\"wrapper-sm orange-A400\">@orange-A400</div>\n" +
    "        <div class=\"wrapper-sm orange-A700\">@orange-A700</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm deep-orange-500\">\n" +
    "          <div class=\"m-b-lg\">deep-orange</div>\n" +
    "          @deep-orange-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm deep-orange-50\">@deep-orange-50</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-100\">@deep-orange-100</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-200\">@deep-orange-200</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-300\">@deep-orange-300</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-400\">@deep-orange-400</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-500\">@deep-orange-500</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-600\">@deep-orange-600</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-700\">@deep-orange-700</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-800\">@deep-orange-800</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-900\">@deep-orange-900</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-A100\">@deep-orange-A100</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-A200\">@deep-orange-A200</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-A400\">@deep-orange-A400</div>\n" +
    "        <div class=\"wrapper-sm deep-orange-A700\">@deep-orange-A700</div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"row no-gutter\">\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm brown-500\">\n" +
    "          <div class=\"m-b-lg\">brown</div>\n" +
    "          @brown-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm brown-50\">@brown-50</div>\n" +
    "        <div class=\"wrapper-sm brown-100\">@brown-100</div>\n" +
    "        <div class=\"wrapper-sm brown-200\">@brown-200</div>\n" +
    "        <div class=\"wrapper-sm brown-300\">@brown-300</div>\n" +
    "        <div class=\"wrapper-sm brown-400\">@brown-400</div>\n" +
    "        <div class=\"wrapper-sm brown-500\">@brown-500</div>\n" +
    "        <div class=\"wrapper-sm brown-600\">@brown-600</div>\n" +
    "        <div class=\"wrapper-sm brown-700\">@brown-700</div>\n" +
    "        <div class=\"wrapper-sm brown-800\">@brown-800</div>\n" +
    "        <div class=\"wrapper-sm brown-900\">@brown-900</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm blue-grey-500\">\n" +
    "          <div class=\"m-b-lg\">blue-grey</div>\n" +
    "          @blue-grey-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm blue-grey-50\">@blue-grey-50</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-100\">@blue-grey-100</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-200\">@blue-grey-200</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-300\">@blue-grey-300</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-400\">@blue-grey-400</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-500\">@blue-grey-500</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-600\">@blue-grey-600</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-700\">@blue-grey-700</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-800\">@blue-grey-800</div>\n" +
    "        <div class=\"wrapper-sm blue-grey-900\">@blue-grey-900</div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-3\">\n" +
    "        <div class=\"wrapper-sm grey-500\">\n" +
    "          <div class=\"m-b-lg\">grey</div>\n" +
    "          @grey-500\n" +
    "        </div>\n" +
    "        <div class=\"wrapper-sm grey-50\">@grey-50</div>\n" +
    "        <div class=\"wrapper-sm grey-100\">@grey-100</div>\n" +
    "        <div class=\"wrapper-sm grey-200\">@grey-200</div>\n" +
    "        <div class=\"wrapper-sm grey-300\">@grey-300</div>\n" +
    "        <div class=\"wrapper-sm grey-400\">@grey-400</div>\n" +
    "        <div class=\"wrapper-sm grey-500\">@grey-500</div>\n" +
    "        <div class=\"wrapper-sm grey-600\">@grey-600</div>\n" +
    "        <div class=\"wrapper-sm grey-700\">@grey-700</div>\n" +
    "        <div class=\"wrapper-sm grey-800\">@grey-800</div>\n" +
    "        <div class=\"wrapper-sm grey-900\">@grey-900</div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/dialog.tmpl.html",
    "<md-dialog aria-label=\"Mango (Fruit)\">\n" +
    "  <md-subheader class=\"md-sticky-no-effect\">Mango (Fruit)</md-subheader>\n" +
    "  <md-content class=\"wrapper-md\">\n" +
    "    \n" +
    "    <p>\n" +
    "      The mango is a juicy stone fruit belonging to the genus Mangifera, consisting of numerous tropical fruiting trees, cultivated mostly for edible fruit. \n" +
    "    </p>\n" +
    "    <p>\n" +
    "      The majority of these species are found in nature as wild mangoes. They all belong to the flowering plant family Anacardiaceae. The mango is native to South and Southeast Asia, from where it has been distributed worldwide to become one of the most cultivated fruits in the tropics.\n" +
    "    </p>\n" +
    "    <p>\n" +
    "      The highest concentration of Mangifera genus is in the western part of Malesia (Sumatra, Java and Borneo) and in Burma and India. \n" +
    "    </p>\n" +
    "    <p>While other Mangifera species (e.g. horse mango, M. foetida) are also grown on a more localized basis, Mangifera indica—the \"common mango\" or \"Indian mango\"—is the only mango tree commonly cultivated in many tropical and subtropical regions. \n" +
    "    </p>\n" +
    "    <p>\n" +
    "      It originated in Indian subcontinent (present day India and Pakistan) and Burma. It is the national fruit of India, Pakistan, and the Philippines, and the national tree of Bangladesh. In several cultures, its fruit and leaves are ritually used as floral decorations at weddings, public celebrations, and religious ceremonies.\n" +
    "    </p>\n" +
    "  </md-content>\n" +
    "\n" +
    "  <div class=\"md-actions\" layout=\"row\">\n" +
    "    <md-button href=\"http://en.wikipedia.org/wiki/Mango\" target=\"_blank\" hide show-md>\n" +
    "      More on Wikipedia\n" +
    "    </md-button>\n" +
    "    <span flex></span>\n" +
    "    <md-button ng-click=\"answer('not useful')\">\n" +
    "     Not Useful\n" +
    "    </md-button>\n" +
    "    <md-button ng-click=\"answer('useful')\" class=\"md-primary\">\n" +
    "      Useful\n" +
    "    </md-button>\n" +
    "  </div>\n" +
    "\n" +
    "</md-dialog>\n" +
    "");
  $templateCache.put("angulr/tpl/material/form.html",
    "<div class=\"card\" ng-controller=\"MDInputCtrl\" >\n" +
    "  <div class=\"card-heading\">\n" +
    "    <h2>Basic Usage</h2>\n" +
    "  </div>\n" +
    "  <div class=\"card-body bg-dark\">\n" +
    "    <div class=\"row row-sm\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input md-input-white\" ng-model=\"user.title\">\n" +
    "          <label>Title</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input md-input-white\" ng-model=\"user.email\">\n" +
    "          <label>Email</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "  <div class=\"card-body\">\n" +
    "    <div class=\"row row-sm\">\n" +
    "      <div class=\"col-sm-12\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input\" disabled ng-model=\"user.company\">\n" +
    "          <label>Company(disabled)</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <div class=\"md-form-group float-label\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.firstName\" required>\n" +
    "          <label>First Name</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <div class=\"md-form-group float-label\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.lastName\" required>\n" +
    "          <label>Last Name</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-12\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.address\">\n" +
    "          <label>Address</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.city\">\n" +
    "          <label>City</label>\n" +
    "          <span class=\"md-input-msg right\">30/30</span>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.state\">\n" +
    "          <label>State</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <input class=\"md-input\" ng-model=\"user.postalCode\">\n" +
    "          <label>Postal Code</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-sm-12\">\n" +
    "        <div class=\"md-form-group\">\n" +
    "          <textarea class=\"md-input\" ng-model=\"user.biography\" rows=\"4\"></textarea>\n" +
    "          <label>Biography</label>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\">\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\" checked>\n" +
    "            <i class=\"blue\"></i>\n" +
    "            Checkbox: checked\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\" disabled>\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox: disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\" checked disabled>\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox: checked and disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\">\n" +
    "            <i class=\"pink\"></i>\n" +
    "            Checkbox: pink\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\" checked>\n" +
    "            <i class=\"green\"></i>\n" +
    "            Checkbox: green\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"checkbox\" checked>\n" +
    "            <i class=\"teal no-icon\"></i>\n" +
    "            Checkbox: no icon\n" +
    "          </label>\n" +
    "        </p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"radio\">\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"radio\" checked>\n" +
    "            <i class=\"blue\"></i>\n" +
    "            Checkbox: checked\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"a\" disabled>\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox: disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"a\" checked disabled>\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Checkbox: checked and disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"radio\">\n" +
    "            <i class=\"pink\"></i>\n" +
    "            Checkbox: pink\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" checked name=\"b\">\n" +
    "            <i class=\"green\"></i>\n" +
    "            Checkbox: green\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-check\">\n" +
    "            <input type=\"radio\" name=\"b\">\n" +
    "            <i class=\"teal no-icon\"></i>\n" +
    "            Checkbox: no icon\n" +
    "          </label>\n" +
    "        </p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-4\">\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"checkbox\">\n" +
    "            <i class=\"indigo\"></i>\n" +
    "            Switch\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"checkbox\" checked>\n" +
    "            <i class=\"blue\"></i>\n" +
    "            Switch: checked\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"checkbox\" disabled>\n" +
    "            <i class=\"blue\"></i>\n" +
    "            Switch: disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"checkbox\" checked disabled>\n" +
    "            <i class=\"blue\"></i>\n" +
    "            Switch: checked and disabled\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"checkbox\">\n" +
    "            <i class=\"pink\"></i>\n" +
    "            Switch: pink\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"radio\" name='switch' checked>\n" +
    "            <i class=\"green\"></i>\n" +
    "            Switch: radio 1\n" +
    "          </label>\n" +
    "        </p>\n" +
    "        <p>\n" +
    "          <label class=\"md-switch\">\n" +
    "            <input type=\"radio\" name='switch'>\n" +
    "            <i class=\"orange\"></i>\n" +
    "            Switch: radio 2\n" +
    "          </label>\n" +
    "        <p>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/icon.html",
    "<div class=\"card\">\n" +
    "    <div class=\"card-body\">\n" +
    "      <h4 class=\"page-header m-t\">Action</h4>\n" +
    "      <div class=\"row list-icon\">\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-3d-rotation i-24 m-t-sm\"></i><span>mdi-action-3d-rotation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-accessibility i-24 m-t-sm\"></i><span>mdi-action-accessibility</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-account-balance i-24 m-t-sm\"></i><span>mdi-action-account-balance</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-account-balance-wallet i-24 m-t-sm\"></i><span>mdi-action-account-balance-wallet</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-account-box i-24 m-t-sm\"></i><span>mdi-action-account-box</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-account-child i-24 m-t-sm\"></i><span>mdi-action-account-child</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-account-circle i-24 m-t-sm\"></i><span>mdi-action-account-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-add-shopping-cart i-24 m-t-sm\"></i><span>mdi-action-add-shopping-cart</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-alarm i-24 m-t-sm\"></i><span>mdi-action-alarm</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-alarm-add i-24 m-t-sm\"></i><span>mdi-action-alarm-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-alarm-off i-24 m-t-sm\"></i><span>mdi-action-alarm-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-alarm-on i-24 m-t-sm\"></i><span>mdi-action-alarm-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-android i-24 m-t-sm\"></i><span>mdi-action-android</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-announcement i-24 m-t-sm\"></i><span>mdi-action-announcement</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-aspect-ratio i-24 m-t-sm\"></i><span>mdi-action-aspect-ratio</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assessment i-24 m-t-sm\"></i><span>mdi-action-assessment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment i-24 m-t-sm\"></i><span>mdi-action-assignment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment-ind i-24 m-t-sm\"></i><span>mdi-action-assignment-ind</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment-late i-24 m-t-sm\"></i><span>mdi-action-assignment-late</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment-return i-24 m-t-sm\"></i><span>mdi-action-assignment-return</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment-returned i-24 m-t-sm\"></i><span>mdi-action-assignment-returned</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-assignment-turned-in i-24 m-t-sm\"></i><span>mdi-action-assignment-turned-in</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-autorenew i-24 m-t-sm\"></i><span>mdi-action-autorenew</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-backup i-24 m-t-sm\"></i><span>mdi-action-backup</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-book i-24 m-t-sm\"></i><span>mdi-action-book</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-bookmark i-24 m-t-sm\"></i><span>mdi-action-bookmark</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-bookmark-outline i-24 m-t-sm\"></i><span>mdi-action-bookmark-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-bug-report i-24 m-t-sm\"></i><span>mdi-action-bug-report</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-cached i-24 m-t-sm\"></i><span>mdi-action-cached</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-class i-24 m-t-sm\"></i><span>mdi-action-class</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-credit-card i-24 m-t-sm\"></i><span>mdi-action-credit-card</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-dashboard i-24 m-t-sm\"></i><span>mdi-action-dashboard</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-delete i-24 m-t-sm\"></i><span>mdi-action-delete</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-description i-24 m-t-sm\"></i><span>mdi-action-description</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-dns i-24 m-t-sm\"></i><span>mdi-action-dns</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-done i-24 m-t-sm\"></i><span>mdi-action-done</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-done-all i-24 m-t-sm\"></i><span>mdi-action-done-all</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-event i-24 m-t-sm\"></i><span>mdi-action-event</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-exit-to-app i-24 m-t-sm\"></i><span>mdi-action-exit-to-app</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-explore i-24 m-t-sm\"></i><span>mdi-action-explore</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-extension i-24 m-t-sm\"></i><span>mdi-action-extension</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-face-unlock i-24 m-t-sm\"></i><span>mdi-action-face-unlock</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-favorite i-24 m-t-sm\"></i><span>mdi-action-favorite</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-favorite-outline i-24 m-t-sm\"></i><span>mdi-action-favorite-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-find-in-page i-24 m-t-sm\"></i><span>mdi-action-find-in-page</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-find-replace i-24 m-t-sm\"></i><span>mdi-action-find-replace</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-flip-to-back i-24 m-t-sm\"></i><span>mdi-action-flip-to-back</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-flip-to-front i-24 m-t-sm\"></i><span>mdi-action-flip-to-front</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-get-app i-24 m-t-sm\"></i><span>mdi-action-get-app</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-grade i-24 m-t-sm\"></i><span>mdi-action-grade</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-group-work i-24 m-t-sm\"></i><span>mdi-action-group-work</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-help i-24 m-t-sm\"></i><span>mdi-action-help</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-highlight-remove i-24 m-t-sm\"></i><span>mdi-action-highlight-remove</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-history i-24 m-t-sm\"></i><span>mdi-action-history</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-home i-24 m-t-sm\"></i><span>mdi-action-home</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-https i-24 m-t-sm\"></i><span>mdi-action-https</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-info i-24 m-t-sm\"></i><span>mdi-action-info</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-info-outline i-24 m-t-sm\"></i><span>mdi-action-info-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-input i-24 m-t-sm\"></i><span>mdi-action-input</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-invert-colors i-24 m-t-sm\"></i><span>mdi-action-invert-colors</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-label i-24 m-t-sm\"></i><span>mdi-action-label</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-label-outline i-24 m-t-sm\"></i><span>mdi-action-label-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-language i-24 m-t-sm\"></i><span>mdi-action-language</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-launch i-24 m-t-sm\"></i><span>mdi-action-launch</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-list i-24 m-t-sm\"></i><span>mdi-action-list</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-lock i-24 m-t-sm\"></i><span>mdi-action-lock</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-lock-open i-24 m-t-sm\"></i><span>mdi-action-lock-open</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-lock-outline i-24 m-t-sm\"></i><span>mdi-action-lock-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-loyalty i-24 m-t-sm\"></i><span>mdi-action-loyalty</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-markunread-mailbox i-24 m-t-sm\"></i><span>mdi-action-markunread-mailbox</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-note-add i-24 m-t-sm\"></i><span>mdi-action-note-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-open-in-browser i-24 m-t-sm\"></i><span>mdi-action-open-in-browser</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-open-in-new i-24 m-t-sm\"></i><span>mdi-action-open-in-new</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-open-with i-24 m-t-sm\"></i><span>mdi-action-open-with</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-pageview i-24 m-t-sm\"></i><span>mdi-action-pageview</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-payment i-24 m-t-sm\"></i><span>mdi-action-payment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-camera-mic i-24 m-t-sm\"></i><span>mdi-action-perm-camera-mic</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-contact-cal i-24 m-t-sm\"></i><span>mdi-action-perm-contact-cal</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-data-setting i-24 m-t-sm\"></i><span>mdi-action-perm-data-setting</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-device-info i-24 m-t-sm\"></i><span>mdi-action-perm-device-info</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-identity i-24 m-t-sm\"></i><span>mdi-action-perm-identity</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-media i-24 m-t-sm\"></i><span>mdi-action-perm-media</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-phone-msg i-24 m-t-sm\"></i><span>mdi-action-perm-phone-msg</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-perm-scan-wifi i-24 m-t-sm\"></i><span>mdi-action-perm-scan-wifi</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-picture-in-picture i-24 m-t-sm\"></i><span>mdi-action-picture-in-picture</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-polymer i-24 m-t-sm\"></i><span>mdi-action-polymer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-print i-24 m-t-sm\"></i><span>mdi-action-print</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-query-builder i-24 m-t-sm\"></i><span>mdi-action-query-builder</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-question-answer i-24 m-t-sm\"></i><span>mdi-action-question-answer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-receipt i-24 m-t-sm\"></i><span>mdi-action-receipt</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-redeem i-24 m-t-sm\"></i><span>mdi-action-redeem</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-report-problem i-24 m-t-sm\"></i><span>mdi-action-report-problem</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-restore i-24 m-t-sm\"></i><span>mdi-action-restore</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-room i-24 m-t-sm\"></i><span>mdi-action-room</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-schedule i-24 m-t-sm\"></i><span>mdi-action-schedule</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-search i-24 m-t-sm\"></i><span>mdi-action-search</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings i-24 m-t-sm\"></i><span>mdi-action-settings</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-applications i-24 m-t-sm\"></i><span>mdi-action-settings-applications</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-backup-restore i-24 m-t-sm\"></i><span>mdi-action-settings-backup-restore</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-bluetooth i-24 m-t-sm\"></i><span>mdi-action-settings-bluetooth</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-cell i-24 m-t-sm\"></i><span>mdi-action-settings-cell</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-display i-24 m-t-sm\"></i><span>mdi-action-settings-display</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-ethernet i-24 m-t-sm\"></i><span>mdi-action-settings-ethernet</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-input-antenna i-24 m-t-sm\"></i><span>mdi-action-settings-input-antenna</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-input-component i-24 m-t-sm\"></i><span>mdi-action-settings-input-component</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-input-composite i-24 m-t-sm\"></i><span>mdi-action-settings-input-composite</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-input-hdmi i-24 m-t-sm\"></i><span>mdi-action-settings-input-hdmi</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-input-svideo i-24 m-t-sm\"></i><span>mdi-action-settings-input-svideo</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-overscan i-24 m-t-sm\"></i><span>mdi-action-settings-overscan</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-phone i-24 m-t-sm\"></i><span>mdi-action-settings-phone</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-power i-24 m-t-sm\"></i><span>mdi-action-settings-power</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-remote i-24 m-t-sm\"></i><span>mdi-action-settings-remote</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-settings-voice i-24 m-t-sm\"></i><span>mdi-action-settings-voice</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-shop i-24 m-t-sm\"></i><span>mdi-action-shop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-shop-two i-24 m-t-sm\"></i><span>mdi-action-shop-two</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-shopping-basket i-24 m-t-sm\"></i><span>mdi-action-shopping-basket</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-shopping-cart i-24 m-t-sm\"></i><span>mdi-action-shopping-cart</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-speaker-notes i-24 m-t-sm\"></i><span>mdi-action-speaker-notes</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-spellcheck i-24 m-t-sm\"></i><span>mdi-action-spellcheck</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-star-rate i-24 m-t-sm\"></i><span>mdi-action-star-rate</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-stars i-24 m-t-sm\"></i><span>mdi-action-stars</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-store i-24 m-t-sm\"></i><span>mdi-action-store</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-subject i-24 m-t-sm\"></i><span>mdi-action-subject</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-swap-horiz i-24 m-t-sm\"></i><span>mdi-action-swap-horiz</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-swap-vert i-24 m-t-sm\"></i><span>mdi-action-swap-vert</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-swap-vert-circle i-24 m-t-sm\"></i><span>mdi-action-swap-vert-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-system-update-tv i-24 m-t-sm\"></i><span>mdi-action-system-update-tv</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-tab i-24 m-t-sm\"></i><span>mdi-action-tab</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-tab-unselected i-24 m-t-sm\"></i><span>mdi-action-tab-unselected</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-theaters i-24 m-t-sm\"></i><span>mdi-action-theaters</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-thumb-down i-24 m-t-sm\"></i><span>mdi-action-thumb-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-thumb-up i-24 m-t-sm\"></i><span>mdi-action-thumb-up</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-thumbs-up-down i-24 m-t-sm\"></i><span>mdi-action-thumbs-up-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-toc i-24 m-t-sm\"></i><span>mdi-action-toc</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-today i-24 m-t-sm\"></i><span>mdi-action-today</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-track-changes i-24 m-t-sm\"></i><span>mdi-action-track-changes</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-translate i-24 m-t-sm\"></i><span>mdi-action-translate</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-trending-down i-24 m-t-sm\"></i><span>mdi-action-trending-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-trending-neutral i-24 m-t-sm\"></i><span>mdi-action-trending-neutral</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-trending-up i-24 m-t-sm\"></i><span>mdi-action-trending-up</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-turned-in i-24 m-t-sm\"></i><span>mdi-action-turned-in</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-turned-in-not i-24 m-t-sm\"></i><span>mdi-action-turned-in-not</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-verified-user i-24 m-t-sm\"></i><span>mdi-action-verified-user</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-agenda i-24 m-t-sm\"></i><span>mdi-action-view-agenda</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-array i-24 m-t-sm\"></i><span>mdi-action-view-array</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-carousel i-24 m-t-sm\"></i><span>mdi-action-view-carousel</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-column i-24 m-t-sm\"></i><span>mdi-action-view-column</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-day i-24 m-t-sm\"></i><span>mdi-action-view-day</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-headline i-24 m-t-sm\"></i><span>mdi-action-view-headline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-list i-24 m-t-sm\"></i><span>mdi-action-view-list</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-module i-24 m-t-sm\"></i><span>mdi-action-view-module</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-quilt i-24 m-t-sm\"></i><span>mdi-action-view-quilt</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-stream i-24 m-t-sm\"></i><span>mdi-action-view-stream</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-view-week i-24 m-t-sm\"></i><span>mdi-action-view-week</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-visibility i-24 m-t-sm\"></i><span>mdi-action-visibility</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-visibility-off i-24 m-t-sm\"></i><span>mdi-action-visibility-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-wallet-giftcard i-24 m-t-sm\"></i><span>mdi-action-wallet-giftcard</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-wallet-membership i-24 m-t-sm\"></i><span>mdi-action-wallet-membership</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-wallet-travel i-24 m-t-sm\"></i><span>mdi-action-wallet-travel</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-action-work i-24 m-t-sm\"></i><span>mdi-action-work</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">AV</h4>\n" +
    "      <div id=\"av\" class=\"row list-icon\">\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-album i-24 m-t-sm\"></i><span>mdi-av-album</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-timer i-24 m-t-sm\"></i><span>mdi-av-timer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-closed-caption i-24 m-t-sm\"></i><span>mdi-av-closed-caption</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-equalizer i-24 m-t-sm\"></i><span>mdi-av-equalizer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-explicit i-24 m-t-sm\"></i><span>mdi-av-explicit</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-fast-forward i-24 m-t-sm\"></i><span>mdi-av-fast-forward</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-fast-rewind i-24 m-t-sm\"></i><span>mdi-av-fast-rewind</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-games i-24 m-t-sm\"></i><span>mdi-av-games</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-hearing i-24 m-t-sm\"></i><span>mdi-av-hearing</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-high-quality i-24 m-t-sm\"></i><span>mdi-av-high-quality</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-loop i-24 m-t-sm\"></i><span>mdi-av-loop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-mic i-24 m-t-sm\"></i><span>mdi-av-mic</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-mic-none i-24 m-t-sm\"></i><span>mdi-av-mic-none</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-mic-off i-24 m-t-sm\"></i><span>mdi-av-mic-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-movie i-24 m-t-sm\"></i><span>mdi-av-movie</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-my-library-add i-24 m-t-sm\"></i><span>mdi-av-my-library-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-my-library-books i-24 m-t-sm\"></i><span>mdi-av-my-library-books</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-my-library-music i-24 m-t-sm\"></i><span>mdi-av-my-library-music</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-new-releases i-24 m-t-sm\"></i><span>mdi-av-new-releases</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-not-interested i-24 m-t-sm\"></i><span>mdi-av-not-interested</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-pause i-24 m-t-sm\"></i><span>mdi-av-pause</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-pause-circle-fill i-24 m-t-sm\"></i><span>mdi-av-pause-circle-fill</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-pause-circle-outline i-24 m-t-sm\"></i><span>mdi-av-pause-circle-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-play-arrow i-24 m-t-sm\"></i><span>mdi-av-play-arrow</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-play-circle-fill i-24 m-t-sm\"></i><span>mdi-av-play-circle-fill</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-play-circle-outline i-24 m-t-sm\"></i><span>mdi-av-play-circle-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-play-shopping-bag i-24 m-t-sm\"></i><span>mdi-av-play-shopping-bag</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-playlist-add i-24 m-t-sm\"></i><span>mdi-av-playlist-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-queue i-24 m-t-sm\"></i><span>mdi-av-queue</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-queue-music i-24 m-t-sm\"></i><span>mdi-av-queue-music</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-radio i-24 m-t-sm\"></i><span>mdi-av-radio</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-recent-actors i-24 m-t-sm\"></i><span>mdi-av-recent-actors</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-repeat i-24 m-t-sm\"></i><span>mdi-av-repeat</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-repeat-one i-24 m-t-sm\"></i><span>mdi-av-repeat-one</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-replay i-24 m-t-sm\"></i><span>mdi-av-replay</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-shuffle i-24 m-t-sm\"></i><span>mdi-av-shuffle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-skip-next i-24 m-t-sm\"></i><span>mdi-av-skip-next</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-skip-previous i-24 m-t-sm\"></i><span>mdi-av-skip-previous</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-snooze i-24 m-t-sm\"></i><span>mdi-av-snooze</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-stop i-24 m-t-sm\"></i><span>mdi-av-stop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-subtitles i-24 m-t-sm\"></i><span>mdi-av-subtitles</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-surround-sound i-24 m-t-sm\"></i><span>mdi-av-surround-sound</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-video-collection i-24 m-t-sm\"></i><span>mdi-av-video-collection</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-videocam i-24 m-t-sm\"></i><span>mdi-av-videocam</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-videocam-off i-24 m-t-sm\"></i><span>mdi-av-videocam-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-volume-down i-24 m-t-sm\"></i><span>mdi-av-volume-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-volume-mute i-24 m-t-sm\"></i><span>mdi-av-volume-mute</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-volume-off i-24 m-t-sm\"></i><span>mdi-av-volume-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-volume-up i-24 m-t-sm\"></i><span>mdi-av-volume-up</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-av-web i-24 m-t-sm\"></i><span>mdi-av-web</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Communication</h4>\n" +
    "      <div id=\"communication\" class=\"row list-icon\">\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-business i-24 m-t-sm\"></i><span>mdi-communication-business</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call i-24 m-t-sm\"></i><span>mdi-communication-call</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-end i-24 m-t-sm\"></i><span>mdi-communication-call-end</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-made i-24 m-t-sm\"></i><span>mdi-communication-call-made</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-merge i-24 m-t-sm\"></i><span>mdi-communication-call-merge</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-missed i-24 m-t-sm\"></i><span>mdi-communication-call-missed</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-received i-24 m-t-sm\"></i><span>mdi-communication-call-received</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-call-split i-24 m-t-sm\"></i><span>mdi-communication-call-split</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-chat i-24 m-t-sm\"></i><span>mdi-communication-chat</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-clear-all i-24 m-t-sm\"></i><span>mdi-communication-clear-all</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-comment i-24 m-t-sm\"></i><span>mdi-communication-comment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-contacts i-24 m-t-sm\"></i><span>mdi-communication-contacts</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-dialer-sip i-24 m-t-sm\"></i><span>mdi-communication-dialer-sip</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-dialpad i-24 m-t-sm\"></i><span>mdi-communication-dialpad</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-dnd-on i-24 m-t-sm\"></i><span>mdi-communication-dnd-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-email i-24 m-t-sm\"></i><span>mdi-communication-email</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-forum i-24 m-t-sm\"></i><span>mdi-communication-forum</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-import-export i-24 m-t-sm\"></i><span>mdi-communication-import-export</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-invert-colors-off i-24 m-t-sm\"></i><span>mdi-communication-invert-colors-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-invert-colors-on i-24 m-t-sm\"></i><span>mdi-communication-invert-colors-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-live-help i-24 m-t-sm\"></i><span>mdi-communication-live-help</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-location-off i-24 m-t-sm\"></i><span>mdi-communication-location-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-location-on i-24 m-t-sm\"></i><span>mdi-communication-location-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-message i-24 m-t-sm\"></i><span>mdi-communication-message</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-messenger i-24 m-t-sm\"></i><span>mdi-communication-messenger</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-no-sim i-24 m-t-sm\"></i><span>mdi-communication-no-sim</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-phone i-24 m-t-sm\"></i><span>mdi-communication-phone</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-portable-wifi-off i-24 m-t-sm\"></i><span>mdi-communication-portable-wifi-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-quick-contacts-dialer i-24 m-t-sm\"></i><span>mdi-communication-quick-contacts-dialer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-quick-contacts-mail i-24 m-t-sm\"></i><span>mdi-communication-quick-contacts-mail</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-ring-volume i-24 m-t-sm\"></i><span>mdi-communication-ring-volume</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-stay-current-landscape i-24 m-t-sm\"></i><span>mdi-communication-stay-current-landscape</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-stay-current-portrait i-24 m-t-sm\"></i><span>mdi-communication-stay-current-portrait</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-stay-primary-landscape i-24 m-t-sm\"></i><span>mdi-communication-stay-primary-landscape</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-stay-primary-portrait i-24 m-t-sm\"></i><span>mdi-communication-stay-primary-portrait</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-swap-calls i-24 m-t-sm\"></i><span>mdi-communication-swap-calls</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-textsms i-24 m-t-sm\"></i><span>mdi-communication-textsms</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-voicemail i-24 m-t-sm\"></i><span>mdi-communication-voicemail</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-communication-vpn-key i-24 m-t-sm\"></i><span>mdi-communication-vpn-key</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Content</h4>\n" +
    "      <div id=\"content\" class=\"row list-icon\">\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-add i-24 m-t-sm\"></i><span>mdi-content-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-add-box i-24 m-t-sm\"></i><span>mdi-content-add-box</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-add-circle i-24 m-t-sm\"></i><span>mdi-content-add-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-add-circle-outline i-24 m-t-sm\"></i><span>mdi-content-add-circle-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-archive i-24 m-t-sm\"></i><span>mdi-content-archive</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-backspace i-24 m-t-sm\"></i><span>mdi-content-backspace</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-block i-24 m-t-sm\"></i><span>mdi-content-block</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-clear i-24 m-t-sm\"></i><span>mdi-content-clear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-content-copy i-24 m-t-sm\"></i><span>mdi-content-content-copy</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-content-cut i-24 m-t-sm\"></i><span>mdi-content-content-cut</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-content-paste i-24 m-t-sm\"></i><span>mdi-content-content-paste</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-create i-24 m-t-sm\"></i><span>mdi-content-create</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-drafts i-24 m-t-sm\"></i><span>mdi-content-drafts</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-filter-list i-24 m-t-sm\"></i><span>mdi-content-filter-list</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-flag i-24 m-t-sm\"></i><span>mdi-content-flag</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-forward i-24 m-t-sm\"></i><span>mdi-content-forward</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-gesture i-24 m-t-sm\"></i><span>mdi-content-gesture</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-inbox i-24 m-t-sm\"></i><span>mdi-content-inbox</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-link i-24 m-t-sm\"></i><span>mdi-content-link</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-mail i-24 m-t-sm\"></i><span>mdi-content-mail</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-markunread i-24 m-t-sm\"></i><span>mdi-content-markunread</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-redo i-24 m-t-sm\"></i><span>mdi-content-redo</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-remove i-24 m-t-sm\"></i><span>mdi-content-remove</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-remove-circle i-24 m-t-sm\"></i><span>mdi-content-remove-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-remove-circle-outline i-24 m-t-sm\"></i><span>mdi-content-remove-circle-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-reply i-24 m-t-sm\"></i><span>mdi-content-reply</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-reply-all i-24 m-t-sm\"></i><span>mdi-content-reply-all</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-report i-24 m-t-sm\"></i><span>mdi-content-report</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-save i-24 m-t-sm\"></i><span>mdi-content-save</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-select-all i-24 m-t-sm\"></i><span>mdi-content-select-all</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-send i-24 m-t-sm\"></i><span>mdi-content-send</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-sort i-24 m-t-sm\"></i><span>mdi-content-sort</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-text-format i-24 m-t-sm\"></i><span>mdi-content-text-format</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-content-undo i-24 m-t-sm\"></i><span>mdi-content-undo</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Device</h4>\n" +
    "      <div id=\"device\" class=\"row list-icon\">\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-access-alarm i-24 m-t-sm\"></i><span>mdi-device-access-alarm</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-access-alarms i-24 m-t-sm\"></i><span>mdi-device-access-alarms</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-access-time i-24 m-t-sm\"></i><span>mdi-device-access-time</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-add-alarm i-24 m-t-sm\"></i><span>mdi-device-add-alarm</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-airplanemode-off i-24 m-t-sm\"></i><span>mdi-device-airplanemode-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-airplanemode-on i-24 m-t-sm\"></i><span>mdi-device-airplanemode-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-20 i-24 m-t-sm\"></i><span>mdi-device-battery-20</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-30 i-24 m-t-sm\"></i><span>mdi-device-battery-30</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-50 i-24 m-t-sm\"></i><span>mdi-device-battery-50</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-60 i-24 m-t-sm\"></i><span>mdi-device-battery-60</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-80 i-24 m-t-sm\"></i><span>mdi-device-battery-80</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-90 i-24 m-t-sm\"></i><span>mdi-device-battery-90</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-alert i-24 m-t-sm\"></i><span>mdi-device-battery-alert</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-20 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-20</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-30 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-30</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-50 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-50</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-60 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-60</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-80 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-80</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-90 i-24 m-t-sm\"></i><span>mdi-device-battery-charging-90</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-charging-full i-24 m-t-sm\"></i><span>mdi-device-battery-charging-full</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-full i-24 m-t-sm\"></i><span>mdi-device-battery-full</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-std i-24 m-t-sm\"></i><span>mdi-device-battery-std</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-battery-unknown i-24 m-t-sm\"></i><span>mdi-device-battery-unknown</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-bluetooth i-24 m-t-sm\"></i><span>mdi-device-bluetooth</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-bluetooth-connected i-24 m-t-sm\"></i><span>mdi-device-bluetooth-connected</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-bluetooth-disabled i-24 m-t-sm\"></i><span>mdi-device-bluetooth-disabled</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-bluetooth-searching i-24 m-t-sm\"></i><span>mdi-device-bluetooth-searching</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-brightness-auto i-24 m-t-sm\"></i><span>mdi-device-brightness-auto</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-brightness-high i-24 m-t-sm\"></i><span>mdi-device-brightness-high</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-brightness-low i-24 m-t-sm\"></i><span>mdi-device-brightness-low</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-brightness-medium i-24 m-t-sm\"></i><span>mdi-device-brightness-medium</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-data-usage i-24 m-t-sm\"></i><span>mdi-device-data-usage</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-developer-mode i-24 m-t-sm\"></i><span>mdi-device-developer-mode</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-devices i-24 m-t-sm\"></i><span>mdi-device-devices</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-dvr i-24 m-t-sm\"></i><span>mdi-device-dvr</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-gps-fixed i-24 m-t-sm\"></i><span>mdi-device-gps-fixed</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-gps-not-fixed i-24 m-t-sm\"></i><span>mdi-device-gps-not-fixed</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-gps-off i-24 m-t-sm\"></i><span>mdi-device-gps-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-location-disabled i-24 m-t-sm\"></i><span>mdi-device-location-disabled</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-location-searching i-24 m-t-sm\"></i><span>mdi-device-location-searching</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-multitrack-audio i-24 m-t-sm\"></i><span>mdi-device-multitrack-audio</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-network-cell i-24 m-t-sm\"></i><span>mdi-device-network-cell</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-network-wifi i-24 m-t-sm\"></i><span>mdi-device-network-wifi</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-nfc i-24 m-t-sm\"></i><span>mdi-device-nfc</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-now-wallpaper i-24 m-t-sm\"></i><span>mdi-device-now-wallpaper</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-now-widgets i-24 m-t-sm\"></i><span>mdi-device-now-widgets</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-screen-lock-landscape i-24 m-t-sm\"></i><span>mdi-device-screen-lock-landscape</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-screen-lock-portrait i-24 m-t-sm\"></i><span>mdi-device-screen-lock-portrait</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-screen-lock-rotation i-24 m-t-sm\"></i><span>mdi-device-screen-lock-rotation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-screen-rotation i-24 m-t-sm\"></i><span>mdi-device-screen-rotation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-sd-storage i-24 m-t-sm\"></i><span>mdi-device-sd-storage</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-settings-system-daydream i-24 m-t-sm\"></i><span>mdi-device-settings-system-daydream</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-0-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-0-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-1-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-1-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-2-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-2-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-3-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-3-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-4-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-4-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-connected-no-internet-0-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-connected-no-internet-0-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-connected-no-internet-1-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-connected-no-internet-1-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-connected-no-internet-2-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-connected-no-internet-2-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-connected-no-internet-3-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-connected-no-internet-3-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-connected-no-internet-4-bar i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-connected-no-internet-4-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-no-sim i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-no-sim</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-null i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-null</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-cellular-off i-24 m-t-sm\"></i><span>mdi-device-signal-cellular-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-0-bar i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-0-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-1-bar i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-1-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-2-bar i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-2-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-3-bar i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-3-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-4-bar i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-4-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-signal-wifi-off i-24 m-t-sm\"></i><span>mdi-device-signal-wifi-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-storage i-24 m-t-sm\"></i><span>mdi-device-storage</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-usb i-24 m-t-sm\"></i><span>mdi-device-usb</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-wifi-lock i-24 m-t-sm\"></i><span>mdi-device-wifi-lock</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-device-wifi-tethering i-24 m-t-sm\"></i><span>mdi-device-wifi-tethering</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Editor</h4>\n" +
    "      <div id=\"editor\" class=\"row list-icon\">\n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-attach-file i-24 m-t-sm\"></i><span>mdi-editor-attach-file</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-attach-money i-24 m-t-sm\"></i><span>mdi-editor-attach-money</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-all i-24 m-t-sm\"></i><span>mdi-editor-border-all</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-bottom i-24 m-t-sm\"></i><span>mdi-editor-border-bottom</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-clear i-24 m-t-sm\"></i><span>mdi-editor-border-clear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-color i-24 m-t-sm\"></i><span>mdi-editor-border-color</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-horizontal i-24 m-t-sm\"></i><span>mdi-editor-border-horizontal</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-inner i-24 m-t-sm\"></i><span>mdi-editor-border-inner</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-left i-24 m-t-sm\"></i><span>mdi-editor-border-left</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-outer i-24 m-t-sm\"></i><span>mdi-editor-border-outer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-right i-24 m-t-sm\"></i><span>mdi-editor-border-right</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-style i-24 m-t-sm\"></i><span>mdi-editor-border-style</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-top i-24 m-t-sm\"></i><span>mdi-editor-border-top</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-border-vertical i-24 m-t-sm\"></i><span>mdi-editor-border-vertical</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-align-center i-24 m-t-sm\"></i><span>mdi-editor-format-align-center</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-align-justify i-24 m-t-sm\"></i><span>mdi-editor-format-align-justify</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-align-left i-24 m-t-sm\"></i><span>mdi-editor-format-align-left</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-align-right i-24 m-t-sm\"></i><span>mdi-editor-format-align-right</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-bold i-24 m-t-sm\"></i><span>mdi-editor-format-bold</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-clear i-24 m-t-sm\"></i><span>mdi-editor-format-clear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-color-fill i-24 m-t-sm\"></i><span>mdi-editor-format-color-fill</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-color-reset i-24 m-t-sm\"></i><span>mdi-editor-format-color-reset</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-color-text i-24 m-t-sm\"></i><span>mdi-editor-format-color-text</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-indent-decrease i-24 m-t-sm\"></i><span>mdi-editor-format-indent-decrease</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-indent-increase i-24 m-t-sm\"></i><span>mdi-editor-format-indent-increase</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-italic i-24 m-t-sm\"></i><span>mdi-editor-format-italic</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-line-spacing i-24 m-t-sm\"></i><span>mdi-editor-format-line-spacing</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-list-bulleted i-24 m-t-sm\"></i><span>mdi-editor-format-list-bulleted</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-list-numbered i-24 m-t-sm\"></i><span>mdi-editor-format-list-numbered</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-paint i-24 m-t-sm\"></i><span>mdi-editor-format-paint</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-quote i-24 m-t-sm\"></i><span>mdi-editor-format-quote</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-size i-24 m-t-sm\"></i><span>mdi-editor-format-size</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-strikethrough i-24 m-t-sm\"></i><span>mdi-editor-format-strikethrough</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-textdirection-l-to-r i-24 m-t-sm\"></i><span>mdi-editor-format-textdirection-l-to-r</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-textdirection-r-to-l i-24 m-t-sm\"></i><span>mdi-editor-format-textdirection-r-to-l</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-format-underline i-24 m-t-sm\"></i><span>mdi-editor-format-underline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-functions i-24 m-t-sm\"></i><span>mdi-editor-functions</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-chart i-24 m-t-sm\"></i><span>mdi-editor-insert-chart</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-comment i-24 m-t-sm\"></i><span>mdi-editor-insert-comment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-drive-file i-24 m-t-sm\"></i><span>mdi-editor-insert-drive-file</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-emoticon i-24 m-t-sm\"></i><span>mdi-editor-insert-emoticon</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-invitation i-24 m-t-sm\"></i><span>mdi-editor-insert-invitation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-link i-24 m-t-sm\"></i><span>mdi-editor-insert-link</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-insert-photo i-24 m-t-sm\"></i><span>mdi-editor-insert-photo</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-merge-type i-24 m-t-sm\"></i><span>mdi-editor-merge-type</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-mode-comment i-24 m-t-sm\"></i><span>mdi-editor-mode-comment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-mode-edit i-24 m-t-sm\"></i><span>mdi-editor-mode-edit</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-publish i-24 m-t-sm\"></i><span>mdi-editor-publish</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-vertical-align-bottom i-24 m-t-sm\"></i><span>mdi-editor-vertical-align-bottom</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-vertical-align-center i-24 m-t-sm\"></i><span>mdi-editor-vertical-align-center</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-vertical-align-top i-24 m-t-sm\"></i><span>mdi-editor-vertical-align-top</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-editor-wrap-text i-24 m-t-sm\"></i><span>mdi-editor-wrap-text</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">File</h4>\n" +
    "      <div id=\"file\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-attachment i-24 m-t-sm\"></i><span>mdi-file-attachment</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud i-24 m-t-sm\"></i><span>mdi-file-cloud</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-circle i-24 m-t-sm\"></i><span>mdi-file-cloud-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-done i-24 m-t-sm\"></i><span>mdi-file-cloud-done</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-download i-24 m-t-sm\"></i><span>mdi-file-cloud-download</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-off i-24 m-t-sm\"></i><span>mdi-file-cloud-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-queue i-24 m-t-sm\"></i><span>mdi-file-cloud-queue</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-cloud-upload i-24 m-t-sm\"></i><span>mdi-file-cloud-upload</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-file-download i-24 m-t-sm\"></i><span>mdi-file-file-download</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-file-upload i-24 m-t-sm\"></i><span>mdi-file-file-upload</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-folder i-24 m-t-sm\"></i><span>mdi-file-folder</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-folder-open i-24 m-t-sm\"></i><span>mdi-file-folder-open</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-file-folder-shared i-24 m-t-sm\"></i><span>mdi-file-folder-shared</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Hardware</h4>\n" +
    "      <div id=\"hardware\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-cast i-24 m-t-sm\"></i><span>mdi-hardware-cast</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-cast-connected i-24 m-t-sm\"></i><span>mdi-hardware-cast-connected</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-computer i-24 m-t-sm\"></i><span>mdi-hardware-computer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-desktop-mac i-24 m-t-sm\"></i><span>mdi-hardware-desktop-mac</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-desktop-windows i-24 m-t-sm\"></i><span>mdi-hardware-desktop-windows</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-dock i-24 m-t-sm\"></i><span>mdi-hardware-dock</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-gamepad i-24 m-t-sm\"></i><span>mdi-hardware-gamepad</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-headset i-24 m-t-sm\"></i><span>mdi-hardware-headset</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-headset-mic i-24 m-t-sm\"></i><span>mdi-hardware-headset-mic</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard i-24 m-t-sm\"></i><span>mdi-hardware-keyboard</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-alt i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-alt</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-arrow-down i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-arrow-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-arrow-left i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-arrow-left</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-arrow-right i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-arrow-right</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-arrow-up i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-arrow-up</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-backspace i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-backspace</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-capslock i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-capslock</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-control i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-control</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-hide i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-hide</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-return i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-return</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-tab i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-tab</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-keyboard-voice i-24 m-t-sm\"></i><span>mdi-hardware-keyboard-voice</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-laptop i-24 m-t-sm\"></i><span>mdi-hardware-laptop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-laptop-chromebook i-24 m-t-sm\"></i><span>mdi-hardware-laptop-chromebook</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-laptop-mac i-24 m-t-sm\"></i><span>mdi-hardware-laptop-mac</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-laptop-windows i-24 m-t-sm\"></i><span>mdi-hardware-laptop-windows</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-memory i-24 m-t-sm\"></i><span>mdi-hardware-memory</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-mouse i-24 m-t-sm\"></i><span>mdi-hardware-mouse</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-phone-android i-24 m-t-sm\"></i><span>mdi-hardware-phone-android</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-phone-iphone i-24 m-t-sm\"></i><span>mdi-hardware-phone-iphone</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-phonelink i-24 m-t-sm\"></i><span>mdi-hardware-phonelink</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-phonelink-off i-24 m-t-sm\"></i><span>mdi-hardware-phonelink-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-security i-24 m-t-sm\"></i><span>mdi-hardware-security</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-sim-card i-24 m-t-sm\"></i><span>mdi-hardware-sim-card</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-smartphone i-24 m-t-sm\"></i><span>mdi-hardware-smartphone</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-speaker i-24 m-t-sm\"></i><span>mdi-hardware-speaker</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-tablet i-24 m-t-sm\"></i><span>mdi-hardware-tablet</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-tablet-android i-24 m-t-sm\"></i><span>mdi-hardware-tablet-android</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-tablet-mac i-24 m-t-sm\"></i><span>mdi-hardware-tablet-mac</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-tv i-24 m-t-sm\"></i><span>mdi-hardware-tv</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-hardware-watch i-24 m-t-sm\"></i><span>mdi-hardware-watch</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Image</h4>\n" +
    "      <div id=\"image\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-add-to-photos i-24 m-t-sm\"></i><span>mdi-image-add-to-photos</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-adjust i-24 m-t-sm\"></i><span>mdi-image-adjust</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-assistant-photo i-24 m-t-sm\"></i><span>mdi-image-assistant-photo</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-audiotrack i-24 m-t-sm\"></i><span>mdi-image-audiotrack</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-blur-circular i-24 m-t-sm\"></i><span>mdi-image-blur-circular</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-blur-linear i-24 m-t-sm\"></i><span>mdi-image-blur-linear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-blur-off i-24 m-t-sm\"></i><span>mdi-image-blur-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-blur-on i-24 m-t-sm\"></i><span>mdi-image-blur-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-1 i-24 m-t-sm\"></i><span>mdi-image-brightness-1</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-2 i-24 m-t-sm\"></i><span>mdi-image-brightness-2</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-3 i-24 m-t-sm\"></i><span>mdi-image-brightness-3</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-4 i-24 m-t-sm\"></i><span>mdi-image-brightness-4</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-5 i-24 m-t-sm\"></i><span>mdi-image-brightness-5</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-6 i-24 m-t-sm\"></i><span>mdi-image-brightness-6</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brightness-7 i-24 m-t-sm\"></i><span>mdi-image-brightness-7</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-brush i-24 m-t-sm\"></i><span>mdi-image-brush</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-camera i-24 m-t-sm\"></i><span>mdi-image-camera</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-camera-alt i-24 m-t-sm\"></i><span>mdi-image-camera-alt</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-camera-front i-24 m-t-sm\"></i><span>mdi-image-camera-front</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-camera-rear i-24 m-t-sm\"></i><span>mdi-image-camera-rear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-camera-roll i-24 m-t-sm\"></i><span>mdi-image-camera-roll</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-center-focus-strong i-24 m-t-sm\"></i><span>mdi-image-center-focus-strong</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-center-focus-weak i-24 m-t-sm\"></i><span>mdi-image-center-focus-weak</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-collections i-24 m-t-sm\"></i><span>mdi-image-collections</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-color-lens i-24 m-t-sm\"></i><span>mdi-image-color-lens</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-colorize i-24 m-t-sm\"></i><span>mdi-image-colorize</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-compare i-24 m-t-sm\"></i><span>mdi-image-compare</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-control-point i-24 m-t-sm\"></i><span>mdi-image-control-point</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-control-point-duplicate i-24 m-t-sm\"></i><span>mdi-image-control-point-duplicate</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-16-9 i-24 m-t-sm\"></i><span>mdi-image-crop-16-9</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop i-24 m-t-sm\"></i><span>mdi-image-crop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-3-2 i-24 m-t-sm\"></i><span>mdi-image-crop-3-2</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-5-4 i-24 m-t-sm\"></i><span>mdi-image-crop-5-4</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-7-5 i-24 m-t-sm\"></i><span>mdi-image-crop-7-5</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-din i-24 m-t-sm\"></i><span>mdi-image-crop-din</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-free i-24 m-t-sm\"></i><span>mdi-image-crop-free</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-landscape i-24 m-t-sm\"></i><span>mdi-image-crop-landscape</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-original i-24 m-t-sm\"></i><span>mdi-image-crop-original</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-portrait i-24 m-t-sm\"></i><span>mdi-image-crop-portrait</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-crop-square i-24 m-t-sm\"></i><span>mdi-image-crop-square</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-dehaze i-24 m-t-sm\"></i><span>mdi-image-dehaze</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-details i-24 m-t-sm\"></i><span>mdi-image-details</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-edit i-24 m-t-sm\"></i><span>mdi-image-edit</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure i-24 m-t-sm\"></i><span>mdi-image-exposure</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure-minus-1 i-24 m-t-sm\"></i><span>mdi-image-exposure-minus-1</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure-minus-2 i-24 m-t-sm\"></i><span>mdi-image-exposure-minus-2</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure-plus-1 i-24 m-t-sm\"></i><span>mdi-image-exposure-plus-1</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure-plus-2 i-24 m-t-sm\"></i><span>mdi-image-exposure-plus-2</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-exposure-zero i-24 m-t-sm\"></i><span>mdi-image-exposure-zero</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-1 i-24 m-t-sm\"></i><span>mdi-image-filter-1</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter i-24 m-t-sm\"></i><span>mdi-image-filter</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-2 i-24 m-t-sm\"></i><span>mdi-image-filter-2</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-3 i-24 m-t-sm\"></i><span>mdi-image-filter-3</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-4 i-24 m-t-sm\"></i><span>mdi-image-filter-4</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-5 i-24 m-t-sm\"></i><span>mdi-image-filter-5</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-6 i-24 m-t-sm\"></i><span>mdi-image-filter-6</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-7 i-24 m-t-sm\"></i><span>mdi-image-filter-7</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-8 i-24 m-t-sm\"></i><span>mdi-image-filter-8</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-9 i-24 m-t-sm\"></i><span>mdi-image-filter-9</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-9-plus i-24 m-t-sm\"></i><span>mdi-image-filter-9-plus</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-b-and-w i-24 m-t-sm\"></i><span>mdi-image-filter-b-and-w</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-center-focus i-24 m-t-sm\"></i><span>mdi-image-filter-center-focus</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-drama i-24 m-t-sm\"></i><span>mdi-image-filter-drama</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-frames i-24 m-t-sm\"></i><span>mdi-image-filter-frames</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-hdr i-24 m-t-sm\"></i><span>mdi-image-filter-hdr</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-none i-24 m-t-sm\"></i><span>mdi-image-filter-none</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-tilt-shift i-24 m-t-sm\"></i><span>mdi-image-filter-tilt-shift</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-filter-vintage i-24 m-t-sm\"></i><span>mdi-image-filter-vintage</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-flare i-24 m-t-sm\"></i><span>mdi-image-flare</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-flash-auto i-24 m-t-sm\"></i><span>mdi-image-flash-auto</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-flash-off i-24 m-t-sm\"></i><span>mdi-image-flash-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-flash-on i-24 m-t-sm\"></i><span>mdi-image-flash-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-flip i-24 m-t-sm\"></i><span>mdi-image-flip</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-gradient i-24 m-t-sm\"></i><span>mdi-image-gradient</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-grain i-24 m-t-sm\"></i><span>mdi-image-grain</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-grid-off i-24 m-t-sm\"></i><span>mdi-image-grid-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-grid-on i-24 m-t-sm\"></i><span>mdi-image-grid-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-hdr-off i-24 m-t-sm\"></i><span>mdi-image-hdr-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-hdr-on i-24 m-t-sm\"></i><span>mdi-image-hdr-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-hdr-strong i-24 m-t-sm\"></i><span>mdi-image-hdr-strong</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-hdr-weak i-24 m-t-sm\"></i><span>mdi-image-hdr-weak</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-healing i-24 m-t-sm\"></i><span>mdi-image-healing</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-image i-24 m-t-sm\"></i><span>mdi-image-image</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-image-aspect-ratio i-24 m-t-sm\"></i><span>mdi-image-image-aspect-ratio</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-iso i-24 m-t-sm\"></i><span>mdi-image-iso</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-landscape i-24 m-t-sm\"></i><span>mdi-image-landscape</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-leak-add i-24 m-t-sm\"></i><span>mdi-image-leak-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-leak-remove i-24 m-t-sm\"></i><span>mdi-image-leak-remove</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-lens i-24 m-t-sm\"></i><span>mdi-image-lens</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks i-24 m-t-sm\"></i><span>mdi-image-looks</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-3 i-24 m-t-sm\"></i><span>mdi-image-looks-3</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-4 i-24 m-t-sm\"></i><span>mdi-image-looks-4</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-5 i-24 m-t-sm\"></i><span>mdi-image-looks-5</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-6 i-24 m-t-sm\"></i><span>mdi-image-looks-6</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-one i-24 m-t-sm\"></i><span>mdi-image-looks-one</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-looks-two i-24 m-t-sm\"></i><span>mdi-image-looks-two</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-loupe i-24 m-t-sm\"></i><span>mdi-image-loupe</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-movie-creation i-24 m-t-sm\"></i><span>mdi-image-movie-creation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-nature i-24 m-t-sm\"></i><span>mdi-image-nature</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-nature-people i-24 m-t-sm\"></i><span>mdi-image-nature-people</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-navigate-before i-24 m-t-sm\"></i><span>mdi-image-navigate-before</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-navigate-next i-24 m-t-sm\"></i><span>mdi-image-navigate-next</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-palette i-24 m-t-sm\"></i><span>mdi-image-palette</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-panorama i-24 m-t-sm\"></i><span>mdi-image-panorama</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-panorama-fisheye i-24 m-t-sm\"></i><span>mdi-image-panorama-fisheye</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-panorama-horizontal i-24 m-t-sm\"></i><span>mdi-image-panorama-horizontal</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-panorama-vertical i-24 m-t-sm\"></i><span>mdi-image-panorama-vertical</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-panorama-wide-angle i-24 m-t-sm\"></i><span>mdi-image-panorama-wide-angle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-photo i-24 m-t-sm\"></i><span>mdi-image-photo</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-photo-album i-24 m-t-sm\"></i><span>mdi-image-photo-album</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-photo-camera i-24 m-t-sm\"></i><span>mdi-image-photo-camera</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-photo-library i-24 m-t-sm\"></i><span>mdi-image-photo-library</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-portrait i-24 m-t-sm\"></i><span>mdi-image-portrait</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-remove-red-eye i-24 m-t-sm\"></i><span>mdi-image-remove-red-eye</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-rotate-left i-24 m-t-sm\"></i><span>mdi-image-rotate-left</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-rotate-right i-24 m-t-sm\"></i><span>mdi-image-rotate-right</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-slideshow i-24 m-t-sm\"></i><span>mdi-image-slideshow</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-straighten i-24 m-t-sm\"></i><span>mdi-image-straighten</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-style i-24 m-t-sm\"></i><span>mdi-image-style</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-switch-camera i-24 m-t-sm\"></i><span>mdi-image-switch-camera</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-switch-video i-24 m-t-sm\"></i><span>mdi-image-switch-video</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-tag-faces i-24 m-t-sm\"></i><span>mdi-image-tag-faces</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-texture i-24 m-t-sm\"></i><span>mdi-image-texture</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timelapse i-24 m-t-sm\"></i><span>mdi-image-timelapse</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timer-10 i-24 m-t-sm\"></i><span>mdi-image-timer-10</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timer i-24 m-t-sm\"></i><span>mdi-image-timer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timer-3 i-24 m-t-sm\"></i><span>mdi-image-timer-3</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timer-auto i-24 m-t-sm\"></i><span>mdi-image-timer-auto</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-timer-off i-24 m-t-sm\"></i><span>mdi-image-timer-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-tonality i-24 m-t-sm\"></i><span>mdi-image-tonality</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-transform i-24 m-t-sm\"></i><span>mdi-image-transform</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-tune i-24 m-t-sm\"></i><span>mdi-image-tune</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-wb-auto i-24 m-t-sm\"></i><span>mdi-image-wb-auto</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-wb-cloudy i-24 m-t-sm\"></i><span>mdi-image-wb-cloudy</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-wb-incandescent i-24 m-t-sm\"></i><span>mdi-image-wb-incandescent</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-wb-irradescent i-24 m-t-sm\"></i><span>mdi-image-wb-irradescent</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-image-wb-sunny i-24 m-t-sm\"></i><span>mdi-image-wb-sunny</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Maps</h4>\n" +
    "      <div id=\"maps\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-beenhere i-24 m-t-sm\"></i><span>mdi-maps-beenhere</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions i-24 m-t-sm\"></i><span>mdi-maps-directions</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-bike i-24 m-t-sm\"></i><span>mdi-maps-directions-bike</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-bus i-24 m-t-sm\"></i><span>mdi-maps-directions-bus</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-car i-24 m-t-sm\"></i><span>mdi-maps-directions-car</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-ferry i-24 m-t-sm\"></i><span>mdi-maps-directions-ferry</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-subway i-24 m-t-sm\"></i><span>mdi-maps-directions-subway</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-train i-24 m-t-sm\"></i><span>mdi-maps-directions-train</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-transit i-24 m-t-sm\"></i><span>mdi-maps-directions-transit</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-directions-walk i-24 m-t-sm\"></i><span>mdi-maps-directions-walk</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-flight i-24 m-t-sm\"></i><span>mdi-maps-flight</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-hotel i-24 m-t-sm\"></i><span>mdi-maps-hotel</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-layers i-24 m-t-sm\"></i><span>mdi-maps-layers</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-layers-clear i-24 m-t-sm\"></i><span>mdi-maps-layers-clear</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-airport i-24 m-t-sm\"></i><span>mdi-maps-local-airport</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-atm i-24 m-t-sm\"></i><span>mdi-maps-local-atm</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-attraction i-24 m-t-sm\"></i><span>mdi-maps-local-attraction</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-bar i-24 m-t-sm\"></i><span>mdi-maps-local-bar</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-cafe i-24 m-t-sm\"></i><span>mdi-maps-local-cafe</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-car-wash i-24 m-t-sm\"></i><span>mdi-maps-local-car-wash</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-convenience-store i-24 m-t-sm\"></i><span>mdi-maps-local-convenience-store</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-drink i-24 m-t-sm\"></i><span>mdi-maps-local-drink</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-florist i-24 m-t-sm\"></i><span>mdi-maps-local-florist</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-gas-station i-24 m-t-sm\"></i><span>mdi-maps-local-gas-station</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-grocery-store i-24 m-t-sm\"></i><span>mdi-maps-local-grocery-store</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-hospital i-24 m-t-sm\"></i><span>mdi-maps-local-hospital</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-hotel i-24 m-t-sm\"></i><span>mdi-maps-local-hotel</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-laundry-service i-24 m-t-sm\"></i><span>mdi-maps-local-laundry-service</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-library i-24 m-t-sm\"></i><span>mdi-maps-local-library</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-mall i-24 m-t-sm\"></i><span>mdi-maps-local-mall</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-movies i-24 m-t-sm\"></i><span>mdi-maps-local-movies</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-offer i-24 m-t-sm\"></i><span>mdi-maps-local-offer</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-parking i-24 m-t-sm\"></i><span>mdi-maps-local-parking</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-pharmacy i-24 m-t-sm\"></i><span>mdi-maps-local-pharmacy</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-phone i-24 m-t-sm\"></i><span>mdi-maps-local-phone</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-pizza i-24 m-t-sm\"></i><span>mdi-maps-local-pizza</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-play i-24 m-t-sm\"></i><span>mdi-maps-local-play</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-post-office i-24 m-t-sm\"></i><span>mdi-maps-local-post-office</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-print-shop i-24 m-t-sm\"></i><span>mdi-maps-local-print-shop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-restaurant i-24 m-t-sm\"></i><span>mdi-maps-local-restaurant</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-see i-24 m-t-sm\"></i><span>mdi-maps-local-see</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-shipping i-24 m-t-sm\"></i><span>mdi-maps-local-shipping</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-local-taxi i-24 m-t-sm\"></i><span>mdi-maps-local-taxi</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-location-history i-24 m-t-sm\"></i><span>mdi-maps-location-history</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-map i-24 m-t-sm\"></i><span>mdi-maps-map</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-my-location i-24 m-t-sm\"></i><span>mdi-maps-my-location</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-navigation i-24 m-t-sm\"></i><span>mdi-maps-navigation</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-pin-drop i-24 m-t-sm\"></i><span>mdi-maps-pin-drop</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-place i-24 m-t-sm\"></i><span>mdi-maps-place</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-rate-review i-24 m-t-sm\"></i><span>mdi-maps-rate-review</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-restaurant-menu i-24 m-t-sm\"></i><span>mdi-maps-restaurant-menu</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-satellite i-24 m-t-sm\"></i><span>mdi-maps-satellite</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-store-mall-directory i-24 m-t-sm\"></i><span>mdi-maps-store-mall-directory</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-terrain i-24 m-t-sm\"></i><span>mdi-maps-terrain</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-maps-traffic i-24 m-t-sm\"></i><span>mdi-maps-traffic</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Navigation</h4>\n" +
    "      <div id=\"navigation\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-apps i-24 m-t-sm\"></i><span>mdi-navigation-apps</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-arrow-back i-24 m-t-sm\"></i><span>mdi-navigation-arrow-back</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-arrow-drop-down i-24 m-t-sm\"></i><span>mdi-navigation-arrow-drop-down</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-arrow-drop-down-circle i-24 m-t-sm\"></i><span>mdi-navigation-arrow-drop-down-circle</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-arrow-drop-up i-24 m-t-sm\"></i><span>mdi-navigation-arrow-drop-up</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-arrow-forward i-24 m-t-sm\"></i><span>mdi-navigation-arrow-forward</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-cancel i-24 m-t-sm\"></i><span>mdi-navigation-cancel</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-check i-24 m-t-sm\"></i><span>mdi-navigation-check</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-chevron-left i-24 m-t-sm\"></i><span>mdi-navigation-chevron-left</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-chevron-right i-24 m-t-sm\"></i><span>mdi-navigation-chevron-right</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-close i-24 m-t-sm\"></i><span>mdi-navigation-close</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-expand-less i-24 m-t-sm\"></i><span>mdi-navigation-expand-less</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-expand-more i-24 m-t-sm\"></i><span>mdi-navigation-expand-more</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-fullscreen i-24 m-t-sm\"></i><span>mdi-navigation-fullscreen</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-fullscreen-exit i-24 m-t-sm\"></i><span>mdi-navigation-fullscreen-exit</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-menu i-24 m-t-sm\"></i><span>mdi-navigation-menu</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-more-horiz i-24 m-t-sm\"></i><span>mdi-navigation-more-horiz</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-more-vert i-24 m-t-sm\"></i><span>mdi-navigation-more-vert</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-refresh i-24 m-t-sm\"></i><span>mdi-navigation-refresh</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-unfold-less i-24 m-t-sm\"></i><span>mdi-navigation-unfold-less</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-navigation-unfold-more i-24 m-t-sm\"></i><span>mdi-navigation-unfold-more</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Notifications</h4>\n" +
    "      <div id=\"notifications\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-adb i-24 m-t-sm\"></i><span>mdi-notification-adb</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-bluetooth-audio i-24 m-t-sm\"></i><span>mdi-notification-bluetooth-audio</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-disc-full i-24 m-t-sm\"></i><span>mdi-notification-disc-full</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-dnd-forwardslash i-24 m-t-sm\"></i><span>mdi-notification-dnd-forwardslash</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-do-not-disturb i-24 m-t-sm\"></i><span>mdi-notification-do-not-disturb</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-drive-eta i-24 m-t-sm\"></i><span>mdi-notification-drive-eta</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-event-available i-24 m-t-sm\"></i><span>mdi-notification-event-available</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-event-busy i-24 m-t-sm\"></i><span>mdi-notification-event-busy</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-event-note i-24 m-t-sm\"></i><span>mdi-notification-event-note</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-folder-special i-24 m-t-sm\"></i><span>mdi-notification-folder-special</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-mms i-24 m-t-sm\"></i><span>mdi-notification-mms</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-more i-24 m-t-sm\"></i><span>mdi-notification-more</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-network-locked i-24 m-t-sm\"></i><span>mdi-notification-network-locked</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-bluetooth-speaker i-24 m-t-sm\"></i><span>mdi-notification-phone-bluetooth-speaker</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-forwarded i-24 m-t-sm\"></i><span>mdi-notification-phone-forwarded</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-in-talk i-24 m-t-sm\"></i><span>mdi-notification-phone-in-talk</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-locked i-24 m-t-sm\"></i><span>mdi-notification-phone-locked</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-missed i-24 m-t-sm\"></i><span>mdi-notification-phone-missed</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-phone-paused i-24 m-t-sm\"></i><span>mdi-notification-phone-paused</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-play-download i-24 m-t-sm\"></i><span>mdi-notification-play-download</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-play-install i-24 m-t-sm\"></i><span>mdi-notification-play-install</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sd-card i-24 m-t-sm\"></i><span>mdi-notification-sd-card</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sim-card-alert i-24 m-t-sm\"></i><span>mdi-notification-sim-card-alert</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sms i-24 m-t-sm\"></i><span>mdi-notification-sms</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sms-failed i-24 m-t-sm\"></i><span>mdi-notification-sms-failed</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sync i-24 m-t-sm\"></i><span>mdi-notification-sync</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sync-disabled i-24 m-t-sm\"></i><span>mdi-notification-sync-disabled</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-sync-problem i-24 m-t-sm\"></i><span>mdi-notification-sync-problem</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-system-update i-24 m-t-sm\"></i><span>mdi-notification-system-update</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-tap-and-play i-24 m-t-sm\"></i><span>mdi-notification-tap-and-play</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-time-to-leave i-24 m-t-sm\"></i><span>mdi-notification-time-to-leave</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-vibration i-24 m-t-sm\"></i><span>mdi-notification-vibration</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-voice-chat i-24 m-t-sm\"></i><span>mdi-notification-voice-chat</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-notification-vpn-lock i-24 m-t-sm\"></i><span>mdi-notification-vpn-lock</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Social</h4>\n" +
    "      <div id=\"social\" class=\"row list-icon\">\n" +
    "        \n" +
    "\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-cake i-24 m-t-sm\"></i><span>mdi-social-cake</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-domain i-24 m-t-sm\"></i><span>mdi-social-domain</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-group i-24 m-t-sm\"></i><span>mdi-social-group</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-group-add i-24 m-t-sm\"></i><span>mdi-social-group-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-location-city i-24 m-t-sm\"></i><span>mdi-social-location-city</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-mood i-24 m-t-sm\"></i><span>mdi-social-mood</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-notifications i-24 m-t-sm\"></i><span>mdi-social-notifications</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-notifications-none i-24 m-t-sm\"></i><span>mdi-social-notifications-none</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-notifications-off i-24 m-t-sm\"></i><span>mdi-social-notifications-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-notifications-on i-24 m-t-sm\"></i><span>mdi-social-notifications-on</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-notifications-paused i-24 m-t-sm\"></i><span>mdi-social-notifications-paused</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-pages i-24 m-t-sm\"></i><span>mdi-social-pages</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-party-mode i-24 m-t-sm\"></i><span>mdi-social-party-mode</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-people i-24 m-t-sm\"></i><span>mdi-social-people</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-people-outline i-24 m-t-sm\"></i><span>mdi-social-people-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-person i-24 m-t-sm\"></i><span>mdi-social-person</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-person-add i-24 m-t-sm\"></i><span>mdi-social-person-add</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-person-outline i-24 m-t-sm\"></i><span>mdi-social-person-outline</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-plus-one i-24 m-t-sm\"></i><span>mdi-social-plus-one</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-poll i-24 m-t-sm\"></i><span>mdi-social-poll</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-public i-24 m-t-sm\"></i><span>mdi-social-public</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-school i-24 m-t-sm\"></i><span>mdi-social-school</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-share i-24 m-t-sm\"></i><span>mdi-social-share</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-social-whatshot i-24 m-t-sm\"></i><span>mdi-social-whatshot</span></div>\n" +
    "      </div>\n" +
    "\n" +
    "      <h4 class=\"page-header\">Toggle</h4>\n" +
    "      <div id=\"toggle\" class=\"row list-icon\">\n" +
    "        \n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-toggle-check-box i-24 m-t-sm\"></i><span>mdi-toggle-check-box</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-toggle-check-box-outline-blank i-24 m-t-sm\"></i><span>mdi-toggle-check-box-outline-blank</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-toggle-radio-button-off i-24 m-t-sm\"></i><span>mdi-toggle-radio-button-off</span></div>\n" +
    "        <div class=\"col-xs-6 col-md-4\"><i class=\"mdi-toggle-radio-button-on i-24 m-t-sm\"></i><span>mdi-toggle-radio-button-on</span>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "\n" +
    "    </div>\n" +
    "</div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/list.html",
    "<div class=\"row\">\n" +
    "    <div class=\"col-sm-6\">\n" +
    "      <h5>Basic</h5>\n" +
    "      <div class=\"md-list md-whiteframe-z0 bg-white m-b\">\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a0.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a1.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a2.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a3.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a4.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a5.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a6.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a7.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <h5>Divider inset</h5>\n" +
    "      <div class=\"md-list md-whiteframe-z0 bg-white m-b\">\n" +
    "        <div class=\"md-list-item inset\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a2.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item inset\">\n" +
    "          <div class=\"md-list-item-left img-circle bg-light\">\n" +
    "            <i class=\"mdi-action-grade i-24 text-muted\"></i>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"col-sm-6\">\n" +
    "      <h5>Text &amp; Icon</h5>\n" +
    "      <div class=\"md-list md-whiteframe-z0 bg-white m-b\">\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle green\">\n" +
    "            <i class=\"mdi-action-grade i-24\"></i>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle indigo\">\n" +
    "            <span class=\"font-thin text-lg\">C</span>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Ci Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle pink\">\n" +
    "            <span class=\"font-thin text-lg\">L</span>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Lin Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle teal\">\n" +
    "            <span class=\"font-thin text-lg\">A</span>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle teal\">\n" +
    "            <span class=\"font-thin text-lg\">M</span>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle teal\">\n" +
    "            <span class=\"font-thin text-lg\">B</span>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Bin Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <h5>Color</h5>\n" +
    "      <div class=\"md-list md-whiteframe-z0 bg-primary m-b\">\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a1.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle lt\">\n" +
    "            <i class=\"mdi-action-grade i-24\"></i>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"md-list md-whiteframe-z0 teal m-b\">\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left\">\n" +
    "            <img src=\"img/a1.jpg\" class=\"w-full img-circle\">\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"md-list-item\">\n" +
    "          <div class=\"md-list-item-left img-circle lt\">\n" +
    "            <i class=\"mdi-action-grade i-24\"></i>\n" +
    "          </div>\n" +
    "          <div class=\"md-list-item-content\">\n" +
    "            <h3 class=\"text-md\">Brunch this weekend?</h3>\n" +
    "            <small class=\"font-thin\">Min Li Chan</small>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      \n" +
    "    </div>\n" +
    "  </div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/ngmaterial.html",
    "<div class=\"alert alert-info none ie-show\">\n" +
    "    Some components not working on IE9.\n" +
    "  </div>\n" +
    "  <h4 class=\"no-margin m-b\">Autocomplete</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDAutocompleteCtrl as ctrl\">\n" +
    "    <form ng-submit=\"$event.preventDefault()\">\n" +
    "      <p>Use  <code>md-autocomplete</code> to search for matches from local or remote data sources.</p>\n" +
    "      <md-autocomplete\n" +
    "          ng-disabled=\"ctrl.isDisabled\"\n" +
    "          md-no-cache=\"ctrl.noCache\"\n" +
    "          md-selected-item=\"ctrl.selectedItem\"\n" +
    "          md-search-text-change=\"ctrl.searchTextChange(ctrl.searchText)\"\n" +
    "          md-search-text=\"ctrl.searchText\"\n" +
    "          md-selected-item-change=\"ctrl.selectedItemChange(item)\"\n" +
    "          md-items=\"item in ctrl.querySearch(ctrl.searchText)\"\n" +
    "          md-item-text=\"item.display\"\n" +
    "          md-min-length=\"0\"\n" +
    "          placeholder=\"What is your favorite US state?\">\n" +
    "        <md-item-template>\n" +
    "          <span md-highlight-text=\"ctrl.searchText\" md-highlight-flags=\"^i\">{{item.display}}</span>\n" +
    "        </md-item-template>\n" +
    "        <md-not-found>\n" +
    "          No matches found for \"{{ctrl.searchText}}\".\n" +
    "        </md-not-found>\n" +
    "      </md-autocomplete>\n" +
    "      <br/>\n" +
    "      <md-checkbox ng-model=\"ctrl.simulateQuery\">Simulate query for results?</md-checkbox>\n" +
    "      <md-checkbox ng-model=\"ctrl.noCache\">Disable caching of queries?</md-checkbox>\n" +
    "      <md-checkbox ng-model=\"ctrl.isDisabled\">Disable the input?</md-checkbox>\n" +
    "\n" +
    "      <p>By default, <code>md-autocomplete</code> will cache results when performing a query.  After the initial call is performed, it will use the cached results to eliminate unnecessary server requests or lookup logic. This can be disabled above.</p>\n" +
    "    </form>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Buttons</h4>\n" +
    "  <div class=\"panel card wrapper\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Normal</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"btn-fw m-b-sm\">Button</md-button>\n" +
    "        <md-button class=\"md-primary btn-fw m-b-sm\">Primary</md-button>\n" +
    "        <md-button ng-disabled=\"true\" class=\"md-primary btn-fw m-b-sm\">Disabled</md-button>\n" +
    "        <md-button class=\"md-warn btn-fw m-b-sm\">Warn</md-button>\n" +
    "        <md-button class=\"md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "        <md-button md-no-ink class=\"btn-fw\">No ink</md-button>\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Raised</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-raised btn-fw m-b-sm\">Button</md-button>\n" +
    "        <md-button class=\"md-raised md-primary btn-fw m-b-sm\">Primary</md-button>\n" +
    "        <md-button ng-disabled=\"true\" class=\"md-raised md-primary btn-fw m-b-sm\">Disabled</md-button>\n" +
    "        <md-button class=\"md-raised md-warn btn-fw m-b-sm\">Warn</md-button>\n" +
    "        <md-button class=\"md-raised md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Themed</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-primary md-hue-1 btn-fw m-b-sm\">Primary Hue 1</md-button>\n" +
    "        <md-button class=\"md-warn md-raised md-hue-2 btn-fw m-b-sm\">Warn Hue 2</md-button>\n" +
    "        <md-button class=\"md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "        <md-button class=\"md-accent md-raised md-hue-1 btn-fw m-b-sm\">Accent Hue 1</md-button>\n" +
    "        \n" +
    "        <p class=\"m-t\">\n" +
    "          <span class=\"label bg-light\">FAB</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-fab\" aria-label=\"Eat cake\">\n" +
    "            <i class=\"mdi-action-android i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab md-primary\" aria-label=\"Use Android\">\n" +
    "          <i class=\"mdi-action-speaker-notes i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab\" ng-disabled=\"true\" aria-label=\"Comment\">\n" +
    "          <i class=\"mdi-communication-dialpad i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab md-primary md-hue-2\" aria-label=\"Profile\">\n" +
    "          <i class=\"mdi-communication-phone i-24\"></i>\n" +
    "        </md-button>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Dialog</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDDialogCtrl\">\n" +
    "    <p>\n" +
    "      Open a dialog over the app's content. Press escape or click outside to close the dialog and\n" +
    "      send focus back to the triggering button.\n" +
    "    </p>\n" +
    "\n" +
    "    <div >\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showAlert($event)\">\n" +
    "        Alert Dialog\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showConfirm($event)\">\n" +
    "        Confirm Dialog\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showAdvanced($event)\">\n" +
    "        Custom Dialog\n" +
    "      </md-button>\n" +
    "    </div>\n" +
    "    \n" +
    "    <br/>\n" +
    "    <b layout=\"row\" layout-align=\"center center\" layout-margin>\n" +
    "      {{alert}}\n" +
    "    </b>\n" +
    "  </div>\n" +
    "\n" +
    "\n" +
    "  <h4>Inputs</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDInputCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <md-input-container class=\"md-primary col-sm-6\">\n" +
    "        <label>Title</label>\n" +
    "        <input ng-model=\"user.title\">\n" +
    "      </md-input-container>\n" +
    "      <md-input-container class=\"col-sm-6\">\n" +
    "        <label>Email</label>\n" +
    "        <input ng-model=\"user.email\" type=\"email\">\n" +
    "      </md-input-container>\n" +
    "    </div>\n" +
    "    <form name=\"userForm\">\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Company (Disabled)</label>\n" +
    "        <input ng-model=\"user.company\" disabled>\n" +
    "      </md-input-container>\n" +
    "    \n" +
    "      <div layout layout-sm=\"column\">\n" +
    "        <md-input-container flex>\n" +
    "          <label>First Name</label>\n" +
    "          <input ng-model=\"user.firstName\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>Last Name</label>\n" +
    "          <input ng-model=\"theMax\">\n" +
    "        </md-input-container>\n" +
    "      </div>\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Address</label>\n" +
    "        <input ng-model=\"user.address\">\n" +
    "      </md-input-container>\n" +
    "\n" +
    "      <div layout layout-sm=\"column\">\n" +
    "        <md-input-container flex>\n" +
    "          <label>City</label>\n" +
    "          <input ng-model=\"user.city\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>State</label>\n" +
    "          <input ng-model=\"user.state\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>Postal Code</label>\n" +
    "          <input ng-model=\"user.postalCode\">\n" +
    "        </md-input-container>\n" +
    "      </div>\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Biography</label>\n" +
    "        <textarea ng-model=\"user.biography\" columns=\"1\" md-maxlength=\"150\"></textarea>\n" +
    "      </md-input-container>\n" +
    "    </form>\n" +
    "    <h5>Errors</h5>\n" +
    "    <form name=\"projectForm\">\n" +
    "      <md-input-container>\n" +
    "        <label>Description</label>\n" +
    "        <input md-maxlength=\"30\" required name=\"description\" ng-model=\"project.description\">\n" +
    "        <div ng-messages=\"projectForm.description.$error\">\n" +
    "          <div ng-message=\"required\">This is required.</div>\n" +
    "          <div ng-message=\"md-maxlength\">The name has to be less than 30 characters long.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "      <md-input-container>\n" +
    "        <label>Client Name</label>\n" +
    "        <input required name=\"clientName\" ng-model=\"project.clientName\">\n" +
    "        <div ng-messages=\"projectForm.clientName.$error\">\n" +
    "          <div ng-message=\"required\">This is required.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "      <md-input-container>\n" +
    "        <label>Hourly Rate (USD)</label>\n" +
    "        <input required type=\"number\" step=\"any\" name=\"rate\" ng-model=\"project.rate\" min=\"800\" max=\"4999\" required>\n" +
    "        <div ng-messages=\"projectForm.rate.$error\">\n" +
    "          <div ng-message=\"required\">You've got to charge something! You can't just <b>give away</b> a Missile Defense System.</div>\n" +
    "          <div ng-message=\"min\">You should charge at least $800 an hour. This job is a big deal... if you mess up, everyone dies!</div>\n" +
    "          <div ng-message=\"max\">$5,000 an hour? That's a little ridiculous. I doubt event Bill Clinton could afford that.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "    </form>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Checkbox, Radio and Switch</h4>\n" +
    "  <div class=\"panel card wrapper\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDCheckboxCtrl\">\n" +
    "        <md-checkbox ng-model=\"data.cb1\" aria-label=\"Checkbox 1\">\n" +
    "          Checkbox 1: {{ data.cb1 }}\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-model=\"data.cb2\" aria-label=\"Checkbox 2\" ng-true-value=\"'yup'\" ng-false-value=\"'nope'\" class=\"md-warn\">\n" +
    "          Checkbox 2 (md-warn): {{ data.cb2 }}\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-disabled=\"true\" aria-label=\"Disabled checkbox\" ng-model=\"data.cb3\">\n" +
    "          Checkbox: Disabled\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-disabled=\"true\" aria-label=\"Disabled checked checkbox\" ng-model=\"data.cb4\" ng-init=\"data.cb4=true\">\n" +
    "          Checkbox: Disabled, Checked\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox md-no-ink aria-label=\"Checkbox No Ink\" ng-model=\"data.cb5\" class=\"md-primary\">\n" +
    "          Checkbox (md-primary): No Ink\n" +
    "        </md-checkbox>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDRadioCtrl\">\n" +
    "          <md-radio-group ng-model=\"data.group2\">\n" +
    "              <md-radio-button ng-repeat=\"d in radioData\"\n" +
    "                               ng-value=\"d.value\"\n" +
    "                               ng-disabled=\" d.isDisabled \">\n" +
    "                  {{ d.label }}\n" +
    "              </md-radio-button>\n" +
    "          </md-radio-group>\n" +
    "          <p>Selected Value: <span class=\"radioValue\">{{ data.group2 }}</span></p>\n" +
    "          <p>\n" +
    "            <md-button ng-click=\"addItem()\" type=\"button\">Add</md-button>\n" +
    "            <md-button ng-click=\"removeItem()\" type=\"button\">Remove</md-button>\n" +
    "          </p>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDSwitchCtrl\">\n" +
    "        <md-switch ng-model=\"data.cb1\" aria-label=\"Switch 1\">\n" +
    "          Switch 1: {{ data.cb1 }}\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-model=\"data.cb2\" aria-label=\"Switch 2\" ng-true-value=\"'yup'\" ng-false-value=\"'nope'\" class=\"md-warn\">\n" +
    "          Switch 2 (md-warn): {{ data.cb2 }}\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-disabled=\"true\" aria-label=\"Disabled switch\" ng-model=\"disabledModel\">\n" +
    "          Switch (Disabled)\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-disabled=\"true\" aria-label=\"Disabled active switch\" ng-model=\"data.cb4\">\n" +
    "          Switch (Disabled, Active)\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch class=\"md-primary\" md-no-ink aria-label=\"Switch No Ink\">\n" +
    "          Switch (md-primary): No Ink\n" +
    "        </md-switch>\n" +
    "        \n" +
    "        <md-switch ng-model=\"data.cb5\" aria-label=\"Switch 5\" ng-change=\"onChange(data.cb5)\">\n" +
    "          Switch 5 message: {{ message }}\n" +
    "        </md-switch>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Slider</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDSliderCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>\n" +
    "          RGB <span ng-attr-style=\"border: 1px solid #333; background: rgb({{color.red}},{{color.green}},{{color.blue}})\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
    "        </h5>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">R</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider min=\"0\" max=\"255\" ng-model=\"color.red\" aria-label=\"red\" id=\"red-slider\">\n" +
    "          </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.red\" aria-label=\"red\" aria-controls=\"red-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">G</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider ng-model=\"color.green\" min=\"0\" max=\"255\" aria-label=\"green\" id=\"green-slider\" class=\"md-accent\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.green\" aria-label=\"green\" aria-controls=\"green-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">B</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider ng-model=\"color.blue\" min=\"0\" max=\"255\" aria-label=\"blue\" id=\"blue-slider\" class=\"md-primary\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.blue\" aria-label=\"blue\" aria-controls=\"blue-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Rating: {{rating}}/5 - demo of theming classes</h5>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-default</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider md-discrete ng-model=\"rating1\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "          </div>\n" +
    "          </md-slider>\n" +
    "        </div>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-warn</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider class=\"md-warn\" md-discrete ng-model=\"rating2\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-primary</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider class=\"md-primary\" md-discrete ng-model=\"rating3\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Disabled</h5>\n" +
    "\n" +
    "        <md-slider ng-model=\"disabled1\" ng-disabled=\"true\" aria-label=\"Disabled 1\"></md-slider>\n" +
    "        <md-slider ng-model=\"disabled2\" ng-disabled=\"true\" aria-label=\"Disabled 2\"></md-slider>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Disabled, Discrete</h5>\n" +
    "\n" +
    "        <md-slider ng-model=\"disabled1\" ng-disabled=\"true\" step=\"3\" md-discrete min=\"0\" max=\"10\" aria-label=\"Disabled discrete 1\"></md-slider>\n" +
    "        <md-slider ng-model=\"disabled2\" ng-disabled=\"true\" step=\"10\" md-discrete aria-label=\"Disabled discrete 2\"></md-slider>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Grid</h4>\n" +
    "  <div>\n" +
    "    <md-grid-list\n" +
    "        md-row-height=\"1:1\" md-gutter=\"8px\"\n" +
    "        md-cols-gt-md=\"6\" md-cols-sm=\"3\" md-cols-md=\"4\"\n" +
    "        md-row-height-gt-md=\"1:1\" md-row-height=\"4:3\"\n" +
    "        md-gutter-gt-md=\"16px\" md-gutter-gt-sm=\"8px\" md-gutter=\"4px\">\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-rowspan=\"2\" md-colspan=\"2\">\n" +
    "          <h5>A</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>B</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>C</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-colspan=\"2\">\n" +
    "          <h5>D</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-rowspan=\"2\" md-colspan=\"2\">\n" +
    "          <h5>E</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>F</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>G</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>H</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>I</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>J</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>K</h5>\n" +
    "      </md-grid-tile>\n" +
    "    </md-grid-list>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Progress</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDProgressCtrl\">\n" +
    "    <h5>Determinate</h5>\n" +
    "    <p>For operations where the percentage of the operation completed can be determined, use a determinate indicator. They give users a quick sense of how long an operation will take.</p>\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular md-mode=\"determinate\" value=\"{{determinateValue}}\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Indeterminate</h5>\n" +
    "    <p>For operations where the user is asked to wait a moment while something finishes up, and it’s not necessary to expose what's happening behind the scenes and how long it will take, use an indeterminate indicator.</p>\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Theming</h5>\n" +
    "\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular class=\"md-hue-2\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-accent\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-accent md-hue-1\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-warn md-hue-3\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-warn\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Linear</h5>\n" +
    "\n" +
    "    <md-progress-linear md-mode=\"indeterminate\" class=\"m-b\"></md-progress-linear>\n" +
    "\n" +
    "    <md-progress-linear class=\"md-warn m-b\" md-mode=\"buffer\" value=\"{{determinateValue}}\" md-buffer-value=\"{{determinateValue2}}\">\n" +
    "    </md-progress-linear>\n" +
    "\n" +
    "    <md-progress-linear class=\"md-accent m-b\" md-mode=\"{{mode}}\" value=\"{{determinateValue}}\"></md-progress-linear>\n" +
    "\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Select</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDSelectCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"userState\" placeholder=\"State\">\n" +
    "          <md-option ng-repeat=\"state in states\" value=\"{{state.abbrev}}\">{{state.abbrev}}</md-option>\n" +
    "        </md-select>\n" +
    "        <p>{{ userState ? 'You selected ' + userState : 'You haven\\'t selected a state yet' }}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select multiple ng-model=\"multiNeighborhood\" placeholder=\"Neighborhood\">\n" +
    "          <md-select-label>{{ multiNeighborhood.length ? multiNeighborhood.join(', ') : 'Choose some' }}</md-select-label>\n" +
    "          <md-option ng-value=\"opt\" ng-repeat=\"opt in neighborhoods\">{{ opt }}</md-option>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">{{ multiNeighborhood ? 'You selected ' + multiNeighborhood : 'You haven\\'t selected anything yet' }}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"favoriteTopping\" placeholder=\"Favorite Topping\">\n" +
    "          <md-optgroup label=\"Meats\">\n" +
    "            <md-option ng-value=\"topping.name\" ng-repeat=\"topping in toppings | filter: {category: 'meat' }\">{{topping.name}}</md-option>\n" +
    "          </md-option-group>\n" +
    "          <md-optgroup label=\"Veggies\">\n" +
    "            <md-option ng-value=\"topping.name\" ng-repeat=\"topping in toppings | filter: {category: 'veg' }\">{{topping.name}}</md-option>\n" +
    "          </md-option-group>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">{{ favoriteTopping ? 'Your favorite topping is ' + favoriteTopping : 'Please select a topping'}}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"user\" md-on-open=\"loadUsers()\", style=\"min-width: 200px;\">\n" +
    "          <md-select-label>{{ user ? user.name : 'Assign to user' }}</md-select-label>\n" +
    "          <md-option ng-value=\"user\" ng-repeat=\"user in users\">{{user.name}}</md-option>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">You have assigned the task to: {{ user ? user.name : 'No one yet' }}</p>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Sidenav</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDSidenavCtrl\">\n" +
    "    <section layout=\"row\" flex>\n" +
    "      <md-sidenav class=\"md-sidenav-left md-whiteframe-z2\" md-component-id=\"left\" md-is-locked-open=\"$mdMedia('gt-md')\">\n" +
    "\n" +
    "        <md-toolbar class=\"md-theme-indigo\">\n" +
    "          <h1 class=\"md-toolbar-tools\">Sidenav Left</h1>\n" +
    "        </md-toolbar>\n" +
    "        <md-content class=\"md-padding\">\n" +
    "          <md-button ng-click=\"closeLeft()\" class=\"md-primary\" hide-gt-md>\n" +
    "            Close Sidenav Left\n" +
    "          </md-button>\n" +
    "          <p hide-md show-gt-md>\n" +
    "            This sidenav is locked open on your device. To go back to the default behavior,\n" +
    "            narrow your display.\n" +
    "          </p>\n" +
    "        </md-content>\n" +
    "\n" +
    "      </md-sidenav>\n" +
    "\n" +
    "      <md-content flex class=\"md-padding\">\n" +
    "\n" +
    "        <div layout=\"column\" layout-fill layout-align=\"center center\">\n" +
    "          <p>\n" +
    "          The left sidenav will 'lock open' on a medium (>=960px wide) device.\n" +
    "          </p>\n" +
    "\n" +
    "          <div>\n" +
    "            <md-button ng-click=\"toggleLeft()\"\n" +
    "              class=\"md-primary\" hide-gt-md>\n" +
    "              Toggle left\n" +
    "            </md-button>\n" +
    "          </div>\n" +
    "\n" +
    "          <div>\n" +
    "            <md-button ng-click=\"toggleRight()\"\n" +
    "              class=\"md-primary\">\n" +
    "              Toggle right\n" +
    "            </md-button>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "      </md-content>\n" +
    "\n" +
    "      <md-sidenav class=\"md-sidenav-right md-whiteframe-z2\" md-component-id=\"right\">\n" +
    "\n" +
    "        <md-toolbar class=\"md-theme-light\">\n" +
    "          <h1 class=\"md-toolbar-tools\">Sidenav Right</h1>\n" +
    "        </md-toolbar>\n" +
    "        <md-content class=\"md-padding\">\n" +
    "          <md-button ng-click=\"closeRight()\" class=\"md-primary\">\n" +
    "            Close Sidenav Right\n" +
    "          </md-button>\n" +
    "        </md-content>\n" +
    "\n" +
    "      </md-sidenav>\n" +
    "    </section>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Subheader</h4>\n" +
    "  <div class=\"panel card wrapperos-rlt\" ng-controller=\"MDSubheaderCtrl\">\n" +
    "    <md-content style=\"height: 300px;\">\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-primary\">Unread Messages</md-subheader>\n" +
    "        <md-list layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "              <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "              <div class=\"md-list-item-text\">\n" +
    "                <h3>{{message.what}}</h3>\n" +
    "                <h4>{{message.who}}</h4>\n" +
    "                <p>\n" +
    "                  {{message.notes}}\n" +
    "                </p>\n" +
    "              </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-warn\">Late Messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader>Read Messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-accent\">Archived messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "    </md-content>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Tabs</h4>\n" +
    "  <div ng-controller=\"MDTabCtrl\">\n" +
    "    <div class=\"panel panel-card\">\n" +
    "      <div class=\"wrapper\">Static Tabs</div>\n" +
    "      <md-tabs class=\"md-primary\" md-selected=\"data.selectedIndex\">\n" +
    "        <md-tab id=\"tab1\" aria-controls=\"tab1-content\">\n" +
    "          Item One\n" +
    "        </md-tab>\n" +
    "        <md-tab id=\"tab2\" aria-controls=\"tab2-content\"\n" +
    "                ng-disabled=\"data.secondLocked\">\n" +
    "          {{data.secondLabel}}\n" +
    "        </md-tab>\n" +
    "        <md-tab id=\"tab3\" aria-controls=\"tab3-content\">\n" +
    "          Item Three\n" +
    "        </md-tab>\n" +
    "      </md-tabs>\n" +
    "\n" +
    "      <ng-switch on=\"data.selectedIndex\">\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab1-content\"\n" +
    "               aria-labelledby=\"tab1\"\n" +
    "               ng-switch-when=\"0\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper blue\">\n" +
    "              View for Item #1<br/>\n" +
    "              data.selectedIndex = 0\n" +
    "          </div>\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab2-content\"\n" +
    "               aria-labelledby=\"tab2\"\n" +
    "               ng-switch-when=\"1\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper teal\">\n" +
    "              View for {{data.secondLabel}}<br/>\n" +
    "              data.selectedIndex = 1\n" +
    "          </div>\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab3-content\"\n" +
    "               aria-labelledby=\"tab3\"\n" +
    "               ng-switch-when=\"2\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper cyan\">\n" +
    "              View for Item #3<br/>\n" +
    "              data.selectedIndex = 2\n" +
    "          </div>\n" +
    "      </ng-switch>\n" +
    "\n" +
    "      <div class=\"b-t wrapper\" layout=\"row\" layout-sm=\"column\" layout-align=\"left center\">\n" +
    "          <md-input-container>\n" +
    "            <label for=\"selectedIndex\">Selected Index</label>\n" +
    "            <input type=\"number\" id=\"selectedIndex\" ng-model=\"data.selectedIndex\">\n" +
    "          </md-input-container>\n" +
    "          <div flex></div>\n" +
    "          <md-checkbox ng-model=\"data.secondLocked\" aria-label=\"Disabled\">\n" +
    "            Disabled Item Two\n" +
    "          </md-checkbox>\n" +
    "\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"panel panel-card\">\n" +
    "      <md-tabs md-selected=\"selectedIndex\" flex>\n" +
    "        <md-tab ng-repeat=\"tab in tabs\"\n" +
    "                ng-disabled=\"tab.disabled\"\n" +
    "                label=\"{{tab.title}}\">\n" +
    "        <div class=\"b-t\" flex>\n" +
    "        <div class=\"wrapper tab{{$index%4}}\" layout=\"column\" layout-align=\"space-around center\">\n" +
    "            <div ng-bind=\"tab.content\" class=\"m-b-lg\"></div>\n" +
    "              <md-button class=\"md-primary md-raised\" ng-click=\"removeTab( tab )\">\n" +
    "                Remove Tab\n" +
    "              </md-button>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        </md-tab>\n" +
    "      </md-tabs>\n" +
    "\n" +
    "      <form ng-submit=\"addTab(tTitle,tContent)\" layout=\"column\" class=\"b-t p\">\n" +
    "        <div layout=\"row\" layout-sm=\"column\" layout-margin >\n" +
    "          <md-input-container>\n" +
    "            <label for=\"activeIndex\">Active Index</label>\n" +
    "            <input type=\"text\" id=\"activeIndex\" ng-model=\"selectedIndex\" disabled>\n" +
    "          </md-input-container>\n" +
    "\n" +
    "          <md-input-container>\n" +
    "            <label for=\"activeTitle\">Active Title</label>\n" +
    "            <input type=\"text\" id=\"activeTitle\" ng-model=\"tabs[selectedIndex].title\">\n" +
    "          </md-input-container>\n" +
    "        </div>\n" +
    "\n" +
    "        <div layout=\"row\" layout-sm=\"column\" layout-margin >\n" +
    "          <span class=\"title m-t-lg m-r-sm\">Add a new Tab:</span>\n" +
    "          <md-input-container>\n" +
    "            <label for=\"label\">Label</label>\n" +
    "            <input type=\"text\" id=\"label\" ng-model=\"tTitle\">\n" +
    "          </md-input-container>\n" +
    "          <md-input-container>\n" +
    "            <label for=\"content\">Content</label>\n" +
    "            <input type=\"text\" id=\"content\" ng-model=\"tContent\">\n" +
    "          </md-input-container>\n" +
    "          <md-button class=\"add-tab md-primary\"  type=\"submit\" style=\"max-height: 32px; margin-top:24px\" >Add Tab</md-button>\n" +
    "        </div>\n" +
    "      </form>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Toast</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDToastCtrl\" id=\"toast\">\n" +
    "    <p>Toast can be dismissed with a swipe, a timer, or a button.</p>\n" +
    "\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <div style=\"width:50px\"></div>\n" +
    "      <md-button ng-click=\"showCustomToast()\">\n" +
    "        Show Custom\n" +
    "      </md-button>\n" +
    "      <md-button ng-click=\"showSimpleToast()\">\n" +
    "        Show Simple\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-raised\" ng-click=\"showActionToast()\">\n" +
    "        Show With Action\n" +
    "      </md-button>\n" +
    "      <div style=\"width:50px\"></div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div>\n" +
    "      <br/>\n" +
    "      <b>Toast Position: \"{{getToastPosition()}}\"</b>\n" +
    "      <br/>\n" +
    "      <md-checkbox ng-repeat=\"(name, isSelected) in toastPosition\"\n" +
    "        ng-model=\"toastPosition[name]\"> \n" +
    "        {{name}}\n" +
    "      </md-checkbox>\n" +
    "      <md-button class=\"md-primary md-fab md-fab-bottom-right\">\n" +
    "        FAB\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-accent md-fab md-fab-top-right\">\n" +
    "        FAB\n" +
    "      </md-button>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Tooltip</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDTooltipCtrl\">\n" +
    "    <p>\n" +
    "      The tooltip is visible when the button is hovered, focused, or touched.\n" +
    "    </p>\n" +
    "\n" +
    "    <md-button class=\"md-fab md-primary m-r\" aria-label=\"Insert Drive\">\n" +
    "      <i class=\"mdi-editor-insert-drive-file i-24\"></i>\n" +
    "      <md-tooltip md-visible=\"demo.showTooltip\">\n" +
    "        Insert Drive\n" +
    "      </md-tooltip>\n" +
    "    </md-button>\n" +
    "    <md-button class=\"md-fab\" aria-label=\"Photos\">\n" +
    "      <i class=\"mdi-editor-insert-photo i-24\"></i>\n" +
    "      <md-tooltip>\n" +
    "        Photos\n" +
    "      </md-tooltip>\n" +
    "    </md-button>\n" +
    "\n" +
    "    <br/><br/><br/>\n" +
    "\n" +
    "    <p>\n" +
    "      <div>\n" +
    "        Additionally, the Tooltip's `md-visible` attribute can use data-binding to programmatically\n" +
    "        show/hide itself. Toggle the checkbox below...\n" +
    "      </div>\n" +
    "    </p>\n" +
    "\n" +
    "    <md-checkbox ng-model=\"demo.showTooltip\">\n" +
    "      Insert Drive\n" +
    "    </md-checkbox>\n" +
    "\n" +
    "  </div>\n" +
    "");
  $templateCache.put("angulr/tpl/material/toast.tmpl.html",
    "<md-toast>\n" +
    "  <span flex>Custom toast!</span>\n" +
    "  <md-button ng-click=\"closeToast()\">\n" +
    "    Close\n" +
    "  </md-button>\n" +
    "</md-toast>\n" +
    "");
  $templateCache.put("angulr/tpl/modal.html",
    "<div class=\"modal-header\">\n" +
    "    <h3 class=\"modal-title\">Modal!</h3>\n" +
    "</div>\n" +
    "<div class=\"modal-body\">\n" +
    "    <ul>\n" +
    "        <li ng-repeat=\"item in items\">\n" +
    "            <a ng-click=\"selected.item = item\">{{ item }}</a>\n" +
    "        </li>\n" +
    "    </ul>\n" +
    "    Selected: <b>{{ selected.item }}</b>\n" +
    "</div>\n" +
    "<div class=\"modal-footer\">                  \n" +
    "    <button class=\"btn btn-default\" ng-click=\"cancel()\">Cancel</button>\n" +
    "    <button class=\"btn btn-primary\" ng-click=\"ok()\">OK</button>\n" +
    "</div>");
  $templateCache.put("angulr/tpl/ngmaterial.html",
    "<div class=\"alert alert-info none ie-show\">\n" +
    "    Some components not working on IE9.\n" +
    "  </div>\n" +
    "  <h4 class=\"no-margin m-b\">Autocomplete</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDAutocompleteCtrl as ctrl\">\n" +
    "    <form ng-submit=\"$event.preventDefault()\">\n" +
    "      <p>Use  <code>md-autocomplete</code> to search for matches from local or remote data sources.</p>\n" +
    "      <md-autocomplete\n" +
    "          ng-disabled=\"ctrl.isDisabled\"\n" +
    "          md-no-cache=\"ctrl.noCache\"\n" +
    "          md-selected-item=\"ctrl.selectedItem\"\n" +
    "          md-search-text-change=\"ctrl.searchTextChange(ctrl.searchText)\"\n" +
    "          md-search-text=\"ctrl.searchText\"\n" +
    "          md-selected-item-change=\"ctrl.selectedItemChange(item)\"\n" +
    "          md-items=\"item in ctrl.querySearch(ctrl.searchText)\"\n" +
    "          md-item-text=\"item.display\"\n" +
    "          md-min-length=\"0\"\n" +
    "          placeholder=\"What is your favorite US state?\">\n" +
    "        <md-item-template>\n" +
    "          <span md-highlight-text=\"ctrl.searchText\" md-highlight-flags=\"^i\">{{item.display}}</span>\n" +
    "        </md-item-template>\n" +
    "        <md-not-found>\n" +
    "          No matches found for \"{{ctrl.searchText}}\".\n" +
    "        </md-not-found>\n" +
    "      </md-autocomplete>\n" +
    "      <br/>\n" +
    "      <md-checkbox ng-model=\"ctrl.simulateQuery\">Simulate query for results?</md-checkbox>\n" +
    "      <md-checkbox ng-model=\"ctrl.noCache\">Disable caching of queries?</md-checkbox>\n" +
    "      <md-checkbox ng-model=\"ctrl.isDisabled\">Disable the input?</md-checkbox>\n" +
    "\n" +
    "      <p>By default, <code>md-autocomplete</code> will cache results when performing a query.  After the initial call is performed, it will use the cached results to eliminate unnecessary server requests or lookup logic. This can be disabled above.</p>\n" +
    "    </form>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Buttons</h4>\n" +
    "  <div class=\"panel card wrapper\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Normal</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"btn-fw m-b-sm\">Button</md-button>\n" +
    "        <md-button class=\"md-primary btn-fw m-b-sm\">Primary</md-button>\n" +
    "        <md-button ng-disabled=\"true\" class=\"md-primary btn-fw m-b-sm\">Disabled</md-button>\n" +
    "        <md-button class=\"md-warn btn-fw m-b-sm\">Warn</md-button>\n" +
    "        <md-button class=\"md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "        <md-button md-no-ink class=\"btn-fw\">No ink</md-button>\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Raised</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-raised btn-fw m-b-sm\">Button</md-button>\n" +
    "        <md-button class=\"md-raised md-primary btn-fw m-b-sm\">Primary</md-button>\n" +
    "        <md-button ng-disabled=\"true\" class=\"md-raised md-primary btn-fw m-b-sm\">Disabled</md-button>\n" +
    "        <md-button class=\"md-raised md-warn btn-fw m-b-sm\">Warn</md-button>\n" +
    "        <md-button class=\"md-raised md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <p>\n" +
    "          <span class=\"label bg-light\">Themed</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-primary md-hue-1 btn-fw m-b-sm\">Primary Hue 1</md-button>\n" +
    "        <md-button class=\"md-warn md-raised md-hue-2 btn-fw m-b-sm\">Warn Hue 2</md-button>\n" +
    "        <md-button class=\"md-accent btn-fw m-b-sm\">Accent</md-button>\n" +
    "        <md-button class=\"md-accent md-raised md-hue-1 btn-fw m-b-sm\">Accent Hue 1</md-button>\n" +
    "        \n" +
    "        <p class=\"m-t\">\n" +
    "          <span class=\"label bg-light\">FAB</span>\n" +
    "        </p>\n" +
    "        <md-button class=\"md-fab\" aria-label=\"Eat cake\">\n" +
    "            <i class=\"mdi-action-android i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab md-primary\" aria-label=\"Use Android\">\n" +
    "          <i class=\"mdi-action-speaker-notes i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab\" ng-disabled=\"true\" aria-label=\"Comment\">\n" +
    "          <i class=\"mdi-communication-dialpad i-24\"></i>\n" +
    "        </md-button>\n" +
    "\n" +
    "        <md-button class=\"md-fab md-primary md-hue-2\" aria-label=\"Profile\">\n" +
    "          <i class=\"mdi-communication-phone i-24\"></i>\n" +
    "        </md-button>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Dialog</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDDialogCtrl\">\n" +
    "    <p>\n" +
    "      Open a dialog over the app's content. Press escape or click outside to close the dialog and\n" +
    "      send focus back to the triggering button.\n" +
    "    </p>\n" +
    "\n" +
    "    <div >\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showAlert($event)\">\n" +
    "        Alert Dialog\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showConfirm($event)\">\n" +
    "        Confirm Dialog\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-primary\" ng-click=\"showAdvanced($event)\">\n" +
    "        Custom Dialog\n" +
    "      </md-button>\n" +
    "    </div>\n" +
    "    \n" +
    "    <br/>\n" +
    "    <b layout=\"row\" layout-align=\"center center\" layout-margin>\n" +
    "      {{alert}}\n" +
    "    </b>\n" +
    "  </div>\n" +
    "\n" +
    "\n" +
    "  <h4>Inputs</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDInputCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <md-input-container class=\"md-primary col-sm-6\">\n" +
    "        <label>Title</label>\n" +
    "        <input ng-model=\"user.title\">\n" +
    "      </md-input-container>\n" +
    "      <md-input-container class=\"col-sm-6\">\n" +
    "        <label>Email</label>\n" +
    "        <input ng-model=\"user.email\" type=\"email\">\n" +
    "      </md-input-container>\n" +
    "    </div>\n" +
    "    <form name=\"userForm\">\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Company (Disabled)</label>\n" +
    "        <input ng-model=\"user.company\" disabled>\n" +
    "      </md-input-container>\n" +
    "    \n" +
    "      <div layout layout-sm=\"column\">\n" +
    "        <md-input-container flex>\n" +
    "          <label>First Name</label>\n" +
    "          <input ng-model=\"user.firstName\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>Last Name</label>\n" +
    "          <input ng-model=\"theMax\">\n" +
    "        </md-input-container>\n" +
    "      </div>\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Address</label>\n" +
    "        <input ng-model=\"user.address\">\n" +
    "      </md-input-container>\n" +
    "\n" +
    "      <div layout layout-sm=\"column\">\n" +
    "        <md-input-container flex>\n" +
    "          <label>City</label>\n" +
    "          <input ng-model=\"user.city\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>State</label>\n" +
    "          <input ng-model=\"user.state\">\n" +
    "        </md-input-container>\n" +
    "        <md-input-container flex>\n" +
    "          <label>Postal Code</label>\n" +
    "          <input ng-model=\"user.postalCode\">\n" +
    "        </md-input-container>\n" +
    "      </div>\n" +
    "\n" +
    "      <md-input-container flex>\n" +
    "        <label>Biography</label>\n" +
    "        <textarea ng-model=\"user.biography\" columns=\"1\" md-maxlength=\"150\"></textarea>\n" +
    "      </md-input-container>\n" +
    "    </form>\n" +
    "    <h5>Errors</h5>\n" +
    "    <form name=\"projectForm\">\n" +
    "      <md-input-container>\n" +
    "        <label>Description</label>\n" +
    "        <input md-maxlength=\"30\" required name=\"description\" ng-model=\"project.description\">\n" +
    "        <div ng-messages=\"projectForm.description.$error\">\n" +
    "          <div ng-message=\"required\">This is required.</div>\n" +
    "          <div ng-message=\"md-maxlength\">The name has to be less than 30 characters long.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "      <md-input-container>\n" +
    "        <label>Client Name</label>\n" +
    "        <input required name=\"clientName\" ng-model=\"project.clientName\">\n" +
    "        <div ng-messages=\"projectForm.clientName.$error\">\n" +
    "          <div ng-message=\"required\">This is required.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "      <md-input-container>\n" +
    "        <label>Hourly Rate (USD)</label>\n" +
    "        <input required type=\"number\" step=\"any\" name=\"rate\" ng-model=\"project.rate\" min=\"800\" max=\"4999\" required>\n" +
    "        <div ng-messages=\"projectForm.rate.$error\">\n" +
    "          <div ng-message=\"required\">You've got to charge something! You can't just <b>give away</b> a Missile Defense System.</div>\n" +
    "          <div ng-message=\"min\">You should charge at least $800 an hour. This job is a big deal... if you mess up, everyone dies!</div>\n" +
    "          <div ng-message=\"max\">$5,000 an hour? That's a little ridiculous. I doubt event Bill Clinton could afford that.</div>\n" +
    "        </div>\n" +
    "      </md-input-container>\n" +
    "    </form>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Checkbox, Radio and Switch</h4>\n" +
    "  <div class=\"panel card wrapper\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDCheckboxCtrl\">\n" +
    "        <md-checkbox ng-model=\"data.cb1\" aria-label=\"Checkbox 1\">\n" +
    "          Checkbox 1: {{ data.cb1 }}\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-model=\"data.cb2\" aria-label=\"Checkbox 2\" ng-true-value=\"'yup'\" ng-false-value=\"'nope'\" class=\"md-warn\">\n" +
    "          Checkbox 2 (md-warn): {{ data.cb2 }}\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-disabled=\"true\" aria-label=\"Disabled checkbox\" ng-model=\"data.cb3\">\n" +
    "          Checkbox: Disabled\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox ng-disabled=\"true\" aria-label=\"Disabled checked checkbox\" ng-model=\"data.cb4\" ng-init=\"data.cb4=true\">\n" +
    "          Checkbox: Disabled, Checked\n" +
    "        </md-checkbox>\n" +
    "\n" +
    "        <md-checkbox md-no-ink aria-label=\"Checkbox No Ink\" ng-model=\"data.cb5\" class=\"md-primary\">\n" +
    "          Checkbox (md-primary): No Ink\n" +
    "        </md-checkbox>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDRadioCtrl\">\n" +
    "          <md-radio-group ng-model=\"data.group2\">\n" +
    "              <md-radio-button ng-repeat=\"d in radioData\"\n" +
    "                               ng-value=\"d.value\"\n" +
    "                               ng-disabled=\" d.isDisabled \">\n" +
    "                  {{ d.label }}\n" +
    "              </md-radio-button>\n" +
    "          </md-radio-group>\n" +
    "          <p>Selected Value: <span class=\"radioValue\">{{ data.group2 }}</span></p>\n" +
    "          <p>\n" +
    "            <md-button ng-click=\"addItem()\" type=\"button\">Add</md-button>\n" +
    "            <md-button ng-click=\"removeItem()\" type=\"button\">Remove</md-button>\n" +
    "          </p>\n" +
    "      </div>\n" +
    "\n" +
    "      <div class=\"col-md-4\" ng-controller=\"MDSwitchCtrl\">\n" +
    "        <md-switch ng-model=\"data.cb1\" aria-label=\"Switch 1\">\n" +
    "          Switch 1: {{ data.cb1 }}\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-model=\"data.cb2\" aria-label=\"Switch 2\" ng-true-value=\"'yup'\" ng-false-value=\"'nope'\" class=\"md-warn\">\n" +
    "          Switch 2 (md-warn): {{ data.cb2 }}\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-disabled=\"true\" aria-label=\"Disabled switch\" ng-model=\"disabledModel\">\n" +
    "          Switch (Disabled)\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch ng-disabled=\"true\" aria-label=\"Disabled active switch\" ng-model=\"data.cb4\">\n" +
    "          Switch (Disabled, Active)\n" +
    "        </md-switch>\n" +
    "\n" +
    "        <md-switch class=\"md-primary\" md-no-ink aria-label=\"Switch No Ink\">\n" +
    "          Switch (md-primary): No Ink\n" +
    "        </md-switch>\n" +
    "        \n" +
    "        <md-switch ng-model=\"data.cb5\" aria-label=\"Switch 5\" ng-change=\"onChange(data.cb5)\">\n" +
    "          Switch 5 message: {{ message }}\n" +
    "        </md-switch>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Slider</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDSliderCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>\n" +
    "          RGB <span ng-attr-style=\"border: 1px solid #333; background: rgb({{color.red}},{{color.green}},{{color.blue}})\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
    "        </h5>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">R</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider min=\"0\" max=\"255\" ng-model=\"color.red\" aria-label=\"red\" id=\"red-slider\">\n" +
    "          </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.red\" aria-label=\"red\" aria-controls=\"red-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">G</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider ng-model=\"color.green\" min=\"0\" max=\"255\" aria-label=\"green\" id=\"green-slider\" class=\"md-accent\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.green\" aria-label=\"green\" aria-controls=\"green-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-2 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">B</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider ng-model=\"color.blue\" min=\"0\" max=\"255\" aria-label=\"blue\" id=\"blue-slider\" class=\"md-primary\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-2\">\n" +
    "            <input type=\"number\" ng-model=\"color.blue\" aria-label=\"blue\" aria-controls=\"blue-slider\" class=\"m-t-sm form-control input-sm\">\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Rating: {{rating}}/5 - demo of theming classes</h5>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-default</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider md-discrete ng-model=\"rating1\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "          </div>\n" +
    "          </md-slider>\n" +
    "        </div>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-warn</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider class=\"md-warn\" md-discrete ng-model=\"rating2\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        <div class=\"row\">\n" +
    "          <div class=\"col-xs-4 text-center\">\n" +
    "            <span class=\"m-t block l-h-1x\">md-primary</span>\n" +
    "          </div>\n" +
    "          <div class=\"col-xs-8\">\n" +
    "            <md-slider class=\"md-primary\" md-discrete ng-model=\"rating3\" step=\"1\" min=\"1\" max=\"5\" aria-label=\"rating\">\n" +
    "            </md-slider>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Disabled</h5>\n" +
    "\n" +
    "        <md-slider ng-model=\"disabled1\" ng-disabled=\"true\" aria-label=\"Disabled 1\"></md-slider>\n" +
    "        <md-slider ng-model=\"disabled2\" ng-disabled=\"true\" aria-label=\"Disabled 2\"></md-slider>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <h5>Disabled, Discrete</h5>\n" +
    "\n" +
    "        <md-slider ng-model=\"disabled1\" ng-disabled=\"true\" step=\"3\" md-discrete min=\"0\" max=\"10\" aria-label=\"Disabled discrete 1\"></md-slider>\n" +
    "        <md-slider ng-model=\"disabled2\" ng-disabled=\"true\" step=\"10\" md-discrete aria-label=\"Disabled discrete 2\"></md-slider>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Grid</h4>\n" +
    "  <div>\n" +
    "    <md-grid-list\n" +
    "        md-row-height=\"1:1\" md-gutter=\"8px\"\n" +
    "        md-cols-gt-md=\"6\" md-cols-sm=\"3\" md-cols-md=\"4\"\n" +
    "        md-row-height-gt-md=\"1:1\" md-row-height=\"4:3\"\n" +
    "        md-gutter-gt-md=\"16px\" md-gutter-gt-sm=\"8px\" md-gutter=\"4px\">\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-rowspan=\"2\" md-colspan=\"2\">\n" +
    "          <h5>A</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>B</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>C</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-colspan=\"2\">\n" +
    "          <h5>D</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\"\n" +
    "          md-rowspan=\"2\" md-colspan=\"2\">\n" +
    "          <h5>E</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>F</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>G</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>H</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>I</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>J</h5>\n" +
    "      </md-grid-tile>\n" +
    "      <md-grid-tile class=\"bg-white md-whiteframe-z0\">\n" +
    "          <h5>K</h5>\n" +
    "      </md-grid-tile>\n" +
    "    </md-grid-list>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Progress</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDProgressCtrl\">\n" +
    "    <h5>Determinate</h5>\n" +
    "    <p>For operations where the percentage of the operation completed can be determined, use a determinate indicator. They give users a quick sense of how long an operation will take.</p>\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular md-mode=\"determinate\" value=\"{{determinateValue}}\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Indeterminate</h5>\n" +
    "    <p>For operations where the user is asked to wait a moment while something finishes up, and it’s not necessary to expose what's happening behind the scenes and how long it will take, use an indeterminate indicator.</p>\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Theming</h5>\n" +
    "\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <md-progress-circular class=\"md-hue-2\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-accent\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-accent md-hue-1\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-warn md-hue-3\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "      <md-progress-circular class=\"md-warn\" md-mode=\"indeterminate\"></md-progress-circular>\n" +
    "    </div>\n" +
    "\n" +
    "    <h5>Linear</h5>\n" +
    "\n" +
    "    <md-progress-linear md-mode=\"indeterminate\" class=\"m-b\"></md-progress-linear>\n" +
    "\n" +
    "    <md-progress-linear class=\"md-warn m-b\" md-mode=\"buffer\" value=\"{{determinateValue}}\" md-buffer-value=\"{{determinateValue2}}\">\n" +
    "    </md-progress-linear>\n" +
    "\n" +
    "    <md-progress-linear class=\"md-accent m-b\" md-mode=\"{{mode}}\" value=\"{{determinateValue}}\"></md-progress-linear>\n" +
    "\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Select</h4>\n" +
    "  <div class=\"panel card wrapper\" ng-controller=\"MDSelectCtrl\">\n" +
    "    <div class=\"row\">\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"userState\" placeholder=\"State\">\n" +
    "          <md-option ng-repeat=\"state in states\" value=\"{{state.abbrev}}\">{{state.abbrev}}</md-option>\n" +
    "        </md-select>\n" +
    "        <p>{{ userState ? 'You selected ' + userState : 'You haven\\'t selected a state yet' }}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select multiple ng-model=\"multiNeighborhood\" placeholder=\"Neighborhood\">\n" +
    "          <md-select-label>{{ multiNeighborhood.length ? multiNeighborhood.join(', ') : 'Choose some' }}</md-select-label>\n" +
    "          <md-option ng-value=\"opt\" ng-repeat=\"opt in neighborhoods\">{{ opt }}</md-option>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">{{ multiNeighborhood ? 'You selected ' + multiNeighborhood : 'You haven\\'t selected anything yet' }}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"favoriteTopping\" placeholder=\"Favorite Topping\">\n" +
    "          <md-optgroup label=\"Meats\">\n" +
    "            <md-option ng-value=\"topping.name\" ng-repeat=\"topping in toppings | filter: {category: 'meat' }\">{{topping.name}}</md-option>\n" +
    "          </md-option-group>\n" +
    "          <md-optgroup label=\"Veggies\">\n" +
    "            <md-option ng-value=\"topping.name\" ng-repeat=\"topping in toppings | filter: {category: 'veg' }\">{{topping.name}}</md-option>\n" +
    "          </md-option-group>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">{{ favoriteTopping ? 'Your favorite topping is ' + favoriteTopping : 'Please select a topping'}}</p>\n" +
    "      </div>\n" +
    "      <div class=\"col-sm-6\">\n" +
    "        <md-select ng-model=\"user\" md-on-open=\"loadUsers()\", style=\"min-width: 200px;\">\n" +
    "          <md-select-label>{{ user ? user.name : 'Assign to user' }}</md-select-label>\n" +
    "          <md-option ng-value=\"user\" ng-repeat=\"user in users\">{{user.name}}</md-option>\n" +
    "        </md-select>\n" +
    "        <p class=\"result\">You have assigned the task to: {{ user ? user.name : 'No one yet' }}</p>\n" +
    "      </div>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Sidenav</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDSidenavCtrl\">\n" +
    "    <section layout=\"row\" flex>\n" +
    "      <md-sidenav class=\"md-sidenav-left md-whiteframe-z2\" md-component-id=\"left\" md-is-locked-open=\"$mdMedia('gt-md')\">\n" +
    "\n" +
    "        <md-toolbar class=\"md-theme-indigo\">\n" +
    "          <h1 class=\"md-toolbar-tools\">Sidenav Left</h1>\n" +
    "        </md-toolbar>\n" +
    "        <md-content class=\"md-padding\">\n" +
    "          <md-button ng-click=\"closeLeft()\" class=\"md-primary\" hide-gt-md>\n" +
    "            Close Sidenav Left\n" +
    "          </md-button>\n" +
    "          <p hide-md show-gt-md>\n" +
    "            This sidenav is locked open on your device. To go back to the default behavior,\n" +
    "            narrow your display.\n" +
    "          </p>\n" +
    "        </md-content>\n" +
    "\n" +
    "      </md-sidenav>\n" +
    "\n" +
    "      <md-content flex class=\"md-padding\">\n" +
    "\n" +
    "        <div layout=\"column\" layout-fill layout-align=\"center center\">\n" +
    "          <p>\n" +
    "          The left sidenav will 'lock open' on a medium (>=960px wide) device.\n" +
    "          </p>\n" +
    "\n" +
    "          <div>\n" +
    "            <md-button ng-click=\"toggleLeft()\"\n" +
    "              class=\"md-primary\" hide-gt-md>\n" +
    "              Toggle left\n" +
    "            </md-button>\n" +
    "          </div>\n" +
    "\n" +
    "          <div>\n" +
    "            <md-button ng-click=\"toggleRight()\"\n" +
    "              class=\"md-primary\">\n" +
    "              Toggle right\n" +
    "            </md-button>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "\n" +
    "      </md-content>\n" +
    "\n" +
    "      <md-sidenav class=\"md-sidenav-right md-whiteframe-z2\" md-component-id=\"right\">\n" +
    "\n" +
    "        <md-toolbar class=\"md-theme-light\">\n" +
    "          <h1 class=\"md-toolbar-tools\">Sidenav Right</h1>\n" +
    "        </md-toolbar>\n" +
    "        <md-content class=\"md-padding\">\n" +
    "          <md-button ng-click=\"closeRight()\" class=\"md-primary\">\n" +
    "            Close Sidenav Right\n" +
    "          </md-button>\n" +
    "        </md-content>\n" +
    "\n" +
    "      </md-sidenav>\n" +
    "    </section>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Subheader</h4>\n" +
    "  <div class=\"panel card wrapperos-rlt\" ng-controller=\"MDSubheaderCtrl\">\n" +
    "    <md-content style=\"height: 300px;\">\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-primary\">Unread Messages</md-subheader>\n" +
    "        <md-list layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "              <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "              <div class=\"md-list-item-text\">\n" +
    "                <h3>{{message.what}}</h3>\n" +
    "                <h4>{{message.who}}</h4>\n" +
    "                <p>\n" +
    "                  {{message.notes}}\n" +
    "                </p>\n" +
    "              </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-warn\">Late Messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader>Read Messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "      <section>\n" +
    "        <md-subheader class=\"md-accent\">Archived messages</md-subheader>\n" +
    "        <md-list layout=\"column\" layout-padding>\n" +
    "          <md-list-item class=\"md-3-line\" ng-repeat=\"message in messages\">\n" +
    "            <img ng-src=\"{{message.face}}\" class=\"md-avatar\" alt=\"{{message.who}}\">\n" +
    "            <div class=\"md-list-item-text\">\n" +
    "              <h3>{{message.what}}</h3>\n" +
    "              <h4>{{message.who}}</h4>\n" +
    "              <p>\n" +
    "                {{message.notes}}\n" +
    "              </p>\n" +
    "            </div>\n" +
    "          </md-list-item>\n" +
    "        </md-list>\n" +
    "      </section>\n" +
    "    </md-content>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Tabs</h4>\n" +
    "  <div ng-controller=\"MDTabCtrl\">\n" +
    "    <div class=\"panel panel-card\">\n" +
    "      <div class=\"wrapper\">Static Tabs</div>\n" +
    "      <md-tabs class=\"md-primary\" md-selected=\"data.selectedIndex\">\n" +
    "        <md-tab id=\"tab1\" aria-controls=\"tab1-content\">\n" +
    "          Item One\n" +
    "        </md-tab>\n" +
    "        <md-tab id=\"tab2\" aria-controls=\"tab2-content\"\n" +
    "                ng-disabled=\"data.secondLocked\">\n" +
    "          {{data.secondLabel}}\n" +
    "        </md-tab>\n" +
    "        <md-tab id=\"tab3\" aria-controls=\"tab3-content\">\n" +
    "          Item Three\n" +
    "        </md-tab>\n" +
    "      </md-tabs>\n" +
    "\n" +
    "      <ng-switch on=\"data.selectedIndex\">\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab1-content\"\n" +
    "               aria-labelledby=\"tab1\"\n" +
    "               ng-switch-when=\"0\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper blue\">\n" +
    "              View for Item #1<br/>\n" +
    "              data.selectedIndex = 0\n" +
    "          </div>\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab2-content\"\n" +
    "               aria-labelledby=\"tab2\"\n" +
    "               ng-switch-when=\"1\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper teal\">\n" +
    "              View for {{data.secondLabel}}<br/>\n" +
    "              data.selectedIndex = 1\n" +
    "          </div>\n" +
    "          <div role=\"tabpanel\"\n" +
    "               id=\"tab3-content\"\n" +
    "               aria-labelledby=\"tab3\"\n" +
    "               ng-switch-when=\"2\"\n" +
    "               md-swipe-left=\"next()\"\n" +
    "               md-swipe-right=\"previous()\"  class=\"wrapper cyan\">\n" +
    "              View for Item #3<br/>\n" +
    "              data.selectedIndex = 2\n" +
    "          </div>\n" +
    "      </ng-switch>\n" +
    "\n" +
    "      <div class=\"b-t wrapper\" layout=\"row\" layout-sm=\"column\" layout-align=\"left center\">\n" +
    "          <md-input-container>\n" +
    "            <label for=\"selectedIndex\">Selected Index</label>\n" +
    "            <input type=\"number\" id=\"selectedIndex\" ng-model=\"data.selectedIndex\">\n" +
    "          </md-input-container>\n" +
    "          <div flex></div>\n" +
    "          <md-checkbox ng-model=\"data.secondLocked\" aria-label=\"Disabled\">\n" +
    "            Disabled Item Two\n" +
    "          </md-checkbox>\n" +
    "\n" +
    "      </div>\n" +
    "    </div>\n" +
    "    <div class=\"panel panel-card\">\n" +
    "      <md-tabs md-selected=\"selectedIndex\" flex>\n" +
    "        <md-tab ng-repeat=\"tab in tabs\"\n" +
    "                ng-disabled=\"tab.disabled\"\n" +
    "                label=\"{{tab.title}}\">\n" +
    "        <div class=\"b-t\" flex>\n" +
    "        <div class=\"wrapper tab{{$index%4}}\" layout=\"column\" layout-align=\"space-around center\">\n" +
    "            <div ng-bind=\"tab.content\" class=\"m-b-lg\"></div>\n" +
    "              <md-button class=\"md-primary md-raised\" ng-click=\"removeTab( tab )\">\n" +
    "                Remove Tab\n" +
    "              </md-button>\n" +
    "          </div>\n" +
    "        </div>\n" +
    "        </md-tab>\n" +
    "      </md-tabs>\n" +
    "\n" +
    "      <form ng-submit=\"addTab(tTitle,tContent)\" layout=\"column\" class=\"b-t p\">\n" +
    "        <div layout=\"row\" layout-sm=\"column\" layout-margin >\n" +
    "          <md-input-container>\n" +
    "            <label for=\"activeIndex\">Active Index</label>\n" +
    "            <input type=\"text\" id=\"activeIndex\" ng-model=\"selectedIndex\" disabled>\n" +
    "          </md-input-container>\n" +
    "\n" +
    "          <md-input-container>\n" +
    "            <label for=\"activeTitle\">Active Title</label>\n" +
    "            <input type=\"text\" id=\"activeTitle\" ng-model=\"tabs[selectedIndex].title\">\n" +
    "          </md-input-container>\n" +
    "        </div>\n" +
    "\n" +
    "        <div layout=\"row\" layout-sm=\"column\" layout-margin >\n" +
    "          <span class=\"title m-t-lg m-r-sm\">Add a new Tab:</span>\n" +
    "          <md-input-container>\n" +
    "            <label for=\"label\">Label</label>\n" +
    "            <input type=\"text\" id=\"label\" ng-model=\"tTitle\">\n" +
    "          </md-input-container>\n" +
    "          <md-input-container>\n" +
    "            <label for=\"content\">Content</label>\n" +
    "            <input type=\"text\" id=\"content\" ng-model=\"tContent\">\n" +
    "          </md-input-container>\n" +
    "          <md-button class=\"add-tab md-primary\"  type=\"submit\" style=\"max-height: 32px; margin-top:24px\" >Add Tab</md-button>\n" +
    "        </div>\n" +
    "      </form>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Toast</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDToastCtrl\" id=\"toast\">\n" +
    "    <p>Toast can be dismissed with a swipe, a timer, or a button.</p>\n" +
    "\n" +
    "    <div layout=\"row\" layout-sm=\"column\" layout-align=\"space-around\">\n" +
    "      <div style=\"width:50px\"></div>\n" +
    "      <md-button ng-click=\"showCustomToast()\">\n" +
    "        Show Custom\n" +
    "      </md-button>\n" +
    "      <md-button ng-click=\"showSimpleToast()\">\n" +
    "        Show Simple\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-raised\" ng-click=\"showActionToast()\">\n" +
    "        Show With Action\n" +
    "      </md-button>\n" +
    "      <div style=\"width:50px\"></div>\n" +
    "    </div>\n" +
    "\n" +
    "    <div>\n" +
    "      <br/>\n" +
    "      <b>Toast Position: \"{{getToastPosition()}}\"</b>\n" +
    "      <br/>\n" +
    "      <md-checkbox ng-repeat=\"(name, isSelected) in toastPosition\"\n" +
    "        ng-model=\"toastPosition[name]\"> \n" +
    "        {{name}}\n" +
    "      </md-checkbox>\n" +
    "      <md-button class=\"md-primary md-fab md-fab-bottom-right\">\n" +
    "        FAB\n" +
    "      </md-button>\n" +
    "      <md-button class=\"md-accent md-fab md-fab-top-right\">\n" +
    "        FAB\n" +
    "      </md-button>\n" +
    "    </div>\n" +
    "  </div>\n" +
    "\n" +
    "  <h4>Tooltip</h4>\n" +
    "  <div class=\"panel card wrapper pos-rlt\" ng-controller=\"MDTooltipCtrl\">\n" +
    "    <p>\n" +
    "      The tooltip is visible when the button is hovered, focused, or touched.\n" +
    "    </p>\n" +
    "\n" +
    "    <md-button class=\"md-fab md-primary m-r\" aria-label=\"Insert Drive\">\n" +
    "      <i class=\"mdi-editor-insert-drive-file i-24\"></i>\n" +
    "      <md-tooltip md-visible=\"demo.showTooltip\">\n" +
    "        Insert Drive\n" +
    "      </md-tooltip>\n" +
    "    </md-button>\n" +
    "    <md-button class=\"md-fab\" aria-label=\"Photos\">\n" +
    "      <i class=\"mdi-editor-insert-photo i-24\"></i>\n" +
    "      <md-tooltip>\n" +
    "        Photos\n" +
    "      </md-tooltip>\n" +
    "    </md-button>\n" +
    "\n" +
    "    <br/><br/><br/>\n" +
    "\n" +
    "    <p>\n" +
    "      <div>\n" +
    "        Additionally, the Tooltip's `md-visible` attribute can use data-binding to programmatically\n" +
    "        show/hide itself. Toggle the checkbox below...\n" +
    "      </div>\n" +
    "    </p>\n" +
    "\n" +
    "    <md-checkbox ng-model=\"demo.showTooltip\">\n" +
    "      Insert Drive\n" +
    "    </md-checkbox>\n" +
    "\n" +
    "  </div>\n" +
    "");
}]);
