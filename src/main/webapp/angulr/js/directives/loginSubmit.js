////app
//angular.module('app')
//    .directive("ngLoginSubmit", function(){
//    return {
//        restrict: "A",
//        scope: {
//            onSubmit: "=ngLoginSubmit"
//        },
//        link: function(scope, element, attrs) {
//            $(element)[0].onsubmit = function() {
//                $("#login-login").val($("#hotelicoLoginMail", element).val());
//                $("#login-password").val($("#hotelicoLoginPassword", element).val());
//
//                scope.onSubmit(function() {
//                    $("#hotelicoLoginForm")[0].submit();
//                });
//                return false;
//            };
//        }
//    };
//});