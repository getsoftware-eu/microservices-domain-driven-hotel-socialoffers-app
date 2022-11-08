angular.module('app')
    .directive('scrollToBottom', function(){
      return {
        restrict: 'A',
        scope: {
          trigger: '=scrollToBottom'
        },
        link: function postLink(scope, elem) {
          scope.$watch('trigger', function() {
            //alert(elem[0].innerHtml);
            elem[0].scrollTop = elem[0].scrollHeight;
          });
        }
      };
    });
