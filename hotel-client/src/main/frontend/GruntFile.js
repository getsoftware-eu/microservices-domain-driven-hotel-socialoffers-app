module.exports = function(grunt) {
	var gtx = require('gruntfile-gtx').wrap(grunt);

    gtx.loadAuto();

    var gruntConfig = require('./grunt');
    gruntConfig.package = require('./package.json');
	
	gtx.config(gruntConfig);

	gtx.config({
		  imagemin: {                          // Task
			// static: {                          // Target
			  // options: {                       // Target options
				// optimizationLevel: 3,
				// svgoPlugins: [{ removeViewBox: false }],
				// use: [mozjpeg()]
			  // },
			  // files: {                         // Dictionary of files
				// 'angular/img/a0.jpg': 'src/img/a0.jpg', // 'destination': 'source'
				// 'angular/img/a1.jpg': 'src/img/a1.jpg',
				// 'angular/img/a2.jpg': 'src/img/a2.jpg'
			  // }
			// },
			dynamic: {                         // Another target
			  files: [{
				expand: true,                  // Enable dynamic expansion
				cwd: 'angulr/img/src/',                   // Src matches are relative to this path
				src: ['**/*.{png,jpg,gif}'],   // Actual patterns to match
				dest: 'angulr/img/build' // Destination path prefix
			  }]
			}
		  }
	});
	
	gtx.config({
		 html2js: 
		 {
			 templates:{
				  options: {
					base: '',
					module: 'hotelApp.templates',
					singleModule: true,
					useStrict: true
					//  ,
					//htmlmin: {
					//  //collapseBooleanAttributes: false,
					//  //collapseWhitespace: false,
					//  //removeAttributeQuotes: false,
					//  //removeComments: false,
					//  //removeEmptyAttributes: false,
					//  //removeRedundantAttributes: false,
					//  //removeScriptTypeAttributes: false,
					//  //removeStyleLinkTypeAttributes: false
					//}
				  },
				  //main: {
					src: ['angulr/tpl/**/*.html'],
					dest: 'angulr/js/build/template_cache.js'
				  //}
			 },

			 translations: {
				 options: {
					 base: '',
					 module: 'hotelApp.initTranslations',
					 singleModule: true,
					 useStrict: true,
					 htmlmin: {} //override minification settings
				 },
				 src: [ 'angulr/l10n/**/*.json' ],
				 dest: 'angulr/js/build/template_translations.js'
			 }
		}
	});
	
	 
	
	
    // We need our bower components in order to develop
	
	//recess: compress & compile less -> app.min.css
	//clean: empty folder
	//copy:libs : copy libs to bowerComponents. 
	//copy:angular - copy angulr files to angular. min.html as main html
	//recess:angular - angular/css/app.min.css COMPRESS CSS
	//concat:angular - concat all js, but without compression. Just 1 big JS!
	//uglify -> COMPRESS JS
	
    //gtx.alias('build:angular', ['recess:less', 'clean:angular', /*'copy:libs', 'copy:angular',*/ 'recess:angular', 'concat:angular', 'uglify:angular', 'clean:img', 'imagemin', 'html2js:main']);
    gtx.alias('build:angulrLite', ['recess:less', 'clean:angular', 'recess:angular',  'html2js:templates', /*'html2js:translations',*/ 'concat:angular'/*, 'uglify:angular' /*'clean:img', 'imagemin',*/]);
    gtx.alias('build:angulr', ['recess:less', 'clean:angular', 'recess:angular',  'html2js:templates', /*'html2js:translations',*/ 'concat:angular', 'uglify:angular' /*'clean:img', 'imagemin',*/]);
    gtx.alias('build:angulrImg', ['recess:less', 'clean:angular', 'recess:angular', 'html2js:templates',  /*'html2js:translations',*/ 'concat:angular', 'uglify:angular', 'clean:img', 'imagemin']);

	/*
    gtx.alias('build:html', ['clean:html', 'copy:html', 'recess:html', 'swig:html', 'concat:html', 'uglify:html']);
    gtx.alias('build:landing', ['copy:landing', 'swig:landing']);

	//COMMIT IN GIT; INSTALL COMMIT AND angulrVersion++ in files setzen. (not Spring Case!)
    gtx.alias('release', ['bower-install-simple', 'bump-commit']);
    gtx.alias('release-patch', ['bump-only:patch', 'release']);
    gtx.alias('release-minor', ['bump-only:minor', 'release']);
    gtx.alias('release-major', ['bump-only:major', 'release']);
    gtx.alias('prerelease', ['bump-only:prerelease', 'release']);
    */
    
	gtx.finalise();
}
