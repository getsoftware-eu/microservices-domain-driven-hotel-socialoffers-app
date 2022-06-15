angular.module('app').directive('stopEvent', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            element.on(attr.stopEvent, function (e) {
                e.stopPropagation();
            });
        }
    };
});

//angular.module('app')
//    .directive('input', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//
//angular.module('app')
//    .directive('textarea', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//
//angular.module('app')
//    .directive('label', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//
//angular.module('app')
//    .directive('form', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//angular.module('app')
//    .directive('div', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//angular.module('app')
//    .directive('button', [
//        function() {
//            return {
//                restrict: 'E',
//                link: link
//            };
//        }
//    ]);
//
//function link(scope, elem) {
//    // bind the events iff this is an input/textarea within a modal
//    if (elem.parents('.modal').length) {
//        elem.on('touchstart', function(e) {
//            elem.focus();
//            e.preventDefault();
//            e.stopPropagation();
//        });
//    }
//}
//;