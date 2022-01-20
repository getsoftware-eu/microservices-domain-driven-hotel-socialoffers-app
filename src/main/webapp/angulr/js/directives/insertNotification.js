angular.module('app')
    .directive('insertNotification', function(){
      return {
        restrict: 'A',
        scope: {
          trigger: '=insertNotification'
        },
        link: function postLink(scope, elem) {
          scope.$watch('trigger', function(newValue, oldValue) {
            //alert(elem[0].innerHtml);
            elem[0].innerHtml = newValue;
          });
        }
      };
    });
