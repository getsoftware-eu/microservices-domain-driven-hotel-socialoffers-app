module.exports = {
	less: {
        files: {
          'angulr/css/app.css': [
            'angulr/css/less/app.less'
          ],
          'angulr/css/md.css': [
            'angulr/css/less/md.less'
          ]
        },
        options: {
          compile: true
        }
    },
    angular: {
        files: {
            'angulr/css/app.min.css': [
                
                'libs/bootstrap/dist/css/bootstrap.css',
                'libs/animate.css/animate.css',
                //'libs/components-font-awesome/css/font-awesome.min.css',
                //'libs/components-font-awesome/css/font-awesome.css',
                'libs/simple-line-icons/css/simple-line-icons.css',
                'libs/angular-xeditable/dist/css/xeditable.css',
                'libs/angular-material/angular-material.css',
                'libs/isteven-angular-multiselect/isteven-multi-select.css',
                'libs/angular-socialshare/angular-socialshare.min.css',
                'libs/angular-carousel/dist/angular-carousel.min.css',

                'libs/ng-tags-input/ng-tags-input.min.css',
                
                'angulr/css/material-design-icons.css',
                'angulr/css/md.css',
               
                // 'angulr/css/ng-img-crop/ng-img-crop.css',
    
                'angulr/css/font.css',
                 'angulr/css/chat.css',
                 'angulr/css/angular-dnd.css',
                'angulr/css/whiteApp.css',
            
                'angulr/css/app.css',
                'angulr/css/eugen.css',
                //'angulr/css/socialShare.css',
                'angulr/css/bootstrap-social.css'

                //'angulr/css/*.css',
                //'angulr/css/src/*.css',
                //'angulr/css/src/eugen/*.css'
            ]
        },
        options: {
            compress: true
        }
    },
    html: {
        files: {
            'html/css/app.min.css': [
                'libs/jquery/bootstrap/dist/css/bootstrap.css',
                'src/css/*.css'
            ]
        },
        options: {
            compress: true
        }
    }
}
