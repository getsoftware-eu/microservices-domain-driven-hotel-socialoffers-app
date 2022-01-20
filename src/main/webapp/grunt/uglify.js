module.exports = {
  options: {
    mangle: false
  },
  angular:{
    src:[
      'angulr/js/build/app.src.js'
    ],
    dest:'angulr/js/build/app.min.js'
  },
  html:{
    src:[
      'html/js/app.src.js'
    ],
    dest:'html/js/app.min.js'
  }
}
