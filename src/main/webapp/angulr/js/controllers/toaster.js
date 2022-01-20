app.controller('ToasterDemoCtrl', ['$scope', '$rootScope', 'toaster', function($scope, $rootScope, toaster) {
    $scope.toaster = {
        type: 'success',
        title: 'Title',
        text: 'Message'
    };
    
    $rootScope.addToasterMessage = function(type, header, message)
    {
        toaster.pop(type,header, message);
    };
    
    $scope.pop = function(){
        toaster.pop($scope.toaster.type, $scope.toaster.title, $scope.toaster.text);
    };
}]);