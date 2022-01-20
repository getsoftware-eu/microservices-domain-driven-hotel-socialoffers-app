module.exports = {
  angular:{
    src:[
      
      // <!--  jQuery -->
      'libs/jquery/dist/jquery.min.js',
  
      // <!--  Angular -->
      //'libs/angular/angular.js',
      'libs/angular/angular.min.js',


      'libs/lodash/lodash.min.js',
        
      'libs/angular-route/angular-route.js',
      
      'libs/angular-animate/angular-animate.js',
      'libs/angular-cookies/angular-cookies.js',
      'libs/angular-resource/angular-resource.js',
      // <!-- 'libs/angular-sanitize/angular-sanitize.js',-->
      'libs/angular-touch/angular-touch.js',
      
      'libs/angular-ui-router/release/angular-ui-router.js',
      'libs/ngstorage/ngStorage.js',
      'libs/angular-ui-utils/ui-utils.js',
      
        // <!--  bootstrap -->
      'libs/angular-bootstrap/ui-bootstrap-tpls.js',
        // <!--  lazyload -->
      'libs/oclazyload/dist/ocLazyLoad.js',
        // <!--  translate -->
      'libs/angular-translate/angular-translate.js',
      'libs/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
      'libs/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      'libs/angular-translate-storage-local/angular-translate-storage-local.js',
      
      'libs/angular-material/angular-material.js',
      'libs/angular-aria/angular-aria.js',
        
      'libs/textAngular/dist/textAngular-sanitize.min.js',
      'libs/textAngular/dist/textAngular.min.js',
      'libs/angular-ui-sref-fastclick/angular-ui-sref-fastclick.js',
    
      'libs/angular-xeditable/dist/js/xeditable.min.js',
    
      'libs/isteven-angular-multiselect/isteven-multi-select.js',
        
      //'libs/angular-carousel/dist/angular-carousel.js',

      'libs/angular-socialshare/angular-socialshare.js',
        
      'libs/angular-money-directive/dist/angular-money-directive.min.js',
      //'libs/ng-tags-input/ng-tags-input.min.js',
        
      //'angulr/js/lib/fastclick.js',
    
      'libs/ng-file-upload/ng-file-upload.min.js',


      'libs/sockjs/sockjs.min.js',

        
      //'angulr/js/*.js',
      'angulr/js/app.js',
      'angulr/js/config.js',
      'angulr/js/config.lazyload.js',
        
      'angulr/js/build/template_cache.js',
     
      //TODO Eugen
      //'angulr/js/build/template_translations.js',
      //'angulr/js/initTranslate.js',
        
      'angulr/js/config.router.js',
      'angulr/js/main.js',

      'libs/stomp-websocket/lib/stomp.min.js', 
      'angulr/js/lib/*.js',
      'angulr/js/directives/*.js',
      'angulr/js/services/*.js',
      'angulr/js/filters/*.js',
      'angulr/js/controllers/bootstrap.js',
      'angulr/js/controllers/hotel/*.js',
      'angulr/js/controllers/hotel/blocks/*.js'
    ],
    dest:'angulr/js/build/app.src.js'
  },
  html:{
    src:[
      'libs/jquery/jquery/dist/jquery.js',
      'libs/jquery/bootstrap/dist/js/bootstrap.js',
      'html/js/*.js'
    ],
    dest:'html/js/app.src.js'
  }
}
