'use strict';
//app
angular.module('hotelApp.language', ['ngRoute'])

    .controller('LanguageController', ['$scope', '$rootScope', '$state', 'HotelService', 'HotelState',
        function($scope, $rootScope, $state, HotelService, HotelState) {


            $scope.localState = {

                selectedLanguageKey : "" ,

                availableSystemLanguages : HotelService.getSystemLanguagesArray(),

                selectSystemLanguage : HotelState.getPrefferedLanguage()
            }
            
            $rootScope.showLoading(false);

            function initSystemLanguages()
            {
                for (var i = 0; i < $scope.localState.availableSystemLanguages.length; i++) {

                    if ($scope.localState.availableSystemLanguages[i].langKey == HotelState.getPrefferedLanguage()) {
                        $scope.localState.availableSystemLanguages[i].ticked = true;
                    }
                }
            };

            $scope.$watch('localState.selectSystemLanguage', function(){

                if($scope.localState.selectSystemLanguage && $scope.localState.selectSystemLanguage.length>0)
                {
                    var langValue = $scope.localState.selectSystemLanguage[0].name;
                    var langKey = HotelService.convertLangLabelToAvailableKey(langValue);
                    
                    if(langKey)
                    {
                        var langChanged = (langKey != HotelState.getPrefferedLanguage());
                        
                        $scope.setNewLanguage(langKey);
                        
                        if($state.current.name == 'app.language' && langChanged)
                        {
                            $scope.hotelState.goStateBack();
                        }
                    }
                }

            }, true);
            
            //$scope.submitLanguageChange = function() {
            //    
            //    //$scope.hotelService.checkNextRouteState();
            //    
            //    $rootScope.showLoading(true);
            //
            //    //$scope.hotelState.setPrefferedLanguage($scope.localState.selectedLanguageKey);
            //   
            //    $scope.setNewLanguage($scope.localState.selectedLanguageKey);
            //    
            //    $scope.hotelState.submitProfileData(true).then(function(responseProfile){
            //        $scope.hotelState.goStateBack();
            //    });
            //    
            //    //$location.path("app/hotel");
            //};

            $scope.hotelState.getDeferredState().then(function() {
                //$scope.localState.authOk = true;
                $scope.hotelState.updateState();
            });


            initSystemLanguages();

        }])
; 