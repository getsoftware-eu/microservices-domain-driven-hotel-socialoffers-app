'use strict';
app
//angular.module('hotelApp.hotelList', ['ngRoute','ngCookies', 'ngResource', 'hotelApp.sessionCustomerService', /*'hotelApp.fileUploader',*/ 'hotelApp.activityService', 'hotelApp.activityDto'])

    .controller('HotelRegisterController',
                ['$scope', '$rootScope', 'HotelDto', '$state', 'editableOptions', 'editableThemes', '$filter',
        function($scope, $rootScope, HotelDto, $state, editableOptions, editableThemes, $filter ) {

        editableThemes.bs3.inputClass = 'input-sm';
        editableThemes.bs3.buttonsClass = 'btn-sm';
        editableOptions.theme = 'bs3';

        $scope.showHotel = $scope.hotelState.tempCreationHotel? $scope.hotelState.tempCreationHotel : $scope.hotelService.getInitHotel();
        $scope.hotelState.tempCreationHotel = $scope.showHotel;
        $scope.hotelState.profileData.systemMessages["staffHotelCreationTime"]  = $scope.hotelState.tempCreationHotel.creationTime;
            
        $scope.localState = {

            languages : $scope.hotelService.getLanguageArray(),

            availableSystemLanguages : $scope.hotelService.getSystemLanguagesArray(),

            selectSystemLanguage : $scope.hotelState.getPrefferedLanguage(),
            
            ready: 0,
            
            //newHotel: $scope.hotelService.getInitHotel(),

            maxHotelDescription: $scope.hotelService.validationUtils.maxHotelDescription,

            editHotelAccordionTitle: $scope.showHotel.name + " " + $filter('translate')('system.edit') ,
            hotelBlockInternActivities : true,
            showHotelBlockFullInfo : true

        };

        $scope.accordionStatus = {

            editHotelOpen: false,
            isEditHotelDisabled: false,

            infoHotelOpen: [],
            isInfoHotelDisabled: false,

            descriptionHotelOpen: [],
            isDescriptionHotelDisabled: false,
            
            newActivityOpen: false,
            isNewActivityDisabled: false
        };

        //################ filter Cities and Hotels #########################
        
        function createInitStaff(){
            
            var initStaff = $scope.hotelService.getInitCustomer();
            initStaff.firstName = "Staff";
            
            return initStaff;
        };

        function initSystemLanguages()
        {
            for (var i = 0; i < $scope.localState.availableSystemLanguages.length; i++) {

                if ($scope.localState.availableSystemLanguages[i].name == $scope.hotelService.getDefaultSystemLanguageLabel()) {
                    $scope.localState.availableSystemLanguages[i].ticked = true;
                }
            }
        };  
            
        function initHotelRegister() {

            $scope.localState.newStaff = createInitStaff();
            $scope.localState.gree = false;
            $scope.localState.newStaff.languages=["en"];
            
            if($scope.localState.newStaff.languages!=undefined)
            {
                for (var l = 0; l < $scope.localState.newStaff.languages.length; l++) {

                    for (var i = 0; i < $scope.localState.languages.length; i++) {
                        if ($scope.localState.languages[i].name === $scope.localState.newStaff.languages[l]) {
                            $scope.localState.languages[i].ticked = true;
                        }
                    }
                }
            }
            
            initSystemLanguages();

            $scope.openModal('editHotel', $scope.showHotel.id);
        };

        $rootScope.$on('hotelUpdated', function(event, obj) {
            if(obj && obj.creationTime == $scope.showHotel.creationTime)
            {
                $scope.showHotel = obj;
                $scope.hotelState.tempCreationHotel = $scope.showHotel;
            }
        });    
            
        $scope.submitNewStaffHotel = function () {
            
            $scope.mainState.successMsg = false;
            $scope.mainState.errorMsg = false;

            ///########## NEW STAFF CHECK !!!!!!!

            var customerError = $scope.hotelService.validateCustomerInfo($scope.localState.newStaff, true);

            if(customerError.length>0)
            {
                $scope.mainState.errorMsg = customerError;
                $rootScope.topPage();
                return;
            }
            
            //################## NEW HOTEL CHECK!!!!!!

            var hotelError = $scope.hotelService.validateHotelInfo($scope.showHotel);
            
            if(hotelError.length>0)
            {
                $scope.mainState.errorMsg = hotelError;
                $rootScope.topPage();
                return;
            }
            
            //var encodedNewHotel = $scope.hotelService.encodeHotel($scope.localState.newHotel);

            var creatorId = ($scope.hotelState && $scope.hotelState.profileData && $scope.hotelState.profileData.id > 0) ? $scope.hotelState.profileData.id : -1;

            //$scope.showHotel.id = -1; //NEW HOTEL!!!
            
            //$scope.hotelState.submitHotelData($scope.showHotel, creatorId).then(function(updatedHotel)
            {

                //$scope.mainState.successMsg = $filter('translate')('alert.success.hotelCreatedSuccess');
                
                //$scope.myWait(500);
                
                //$scope.accordionStatus.editHotelOpen = false;
                //$scope.accordionStatus.newActivityOpen = false;
                
                //$scope.showHotel = updatedHotel;

                //if($scope.hotelState)
                //{
                //    $scope.hotelState.currentHotel = $scope.localState.newHotel;
                //}

                ////##### REGISTER STAFF

                //Eugen: STAFF INFO!!!
                $scope.localState.newStaff.hotelStaff = true;
                
                $scope.localState.newStaff.hotelId = $scope.showHotel.id;
                
                if($scope.localState.newStaff.firstName == undefined){
                    $scope.localState.newStaff.firstName = "staff";
                }

                $scope.localState.newStaff.prefferedLanguage = $scope.hotelService.getDefaultAllowedLanguageViewKey();

                $scope.hotelState.registerCustomer($scope.localState.newStaff).then(function(createdProfil)
                {
                    createdProfil.hotelId = $scope.showHotel.id;

                    //$scope.uploadNewImage("hotel", updatedHotel.creationTime, createdProfil.id);

                    //$scope.myWait(500);
                    
                    $scope.localState.newStaff = createdProfil;
                    
                    $scope.hotelState.initState(createdProfil);

                    $scope.mainState.successMsg = $filter('translate')("alert.success.createdHotelStaffAndSendMail") + createdProfil.email;

                    $scope.hotelState.getDeferredState().then(function() 
                    {

                        $scope.localState.newStaff = createInitStaff();
    
                        $scope.hotelState.profileData.hotelId = $scope.showHotel.id;
                        
                        $scope.hotelState.submitProfileData(true, $scope.hotelState.profileData).then(function(updatedProfile)
                        {
                            
                            $scope.showHotel = HotelDto.get({customerId: $scope.hotelState.profileData.id, hotelId: $scope.showHotel.id}, function (hotelObj) 
                            {
    
                                if(!$scope.mainState.hotelArray)
                                {
                                    $scope.mainState.hotelArray = [];
                                }
                                $scope.mainState.hotelArray.push($scope.hotelService.decodeHotel(hotelObj));
                                $scope.accordionStatus.infoHotelOpen[hotelObj.id] = false;
                                $scope.accordionStatus.descriptionHotelOpen[hotelObj.id] = false;
    
                                //$rootScope.topPage();
    
                                $rootScope.showLoading(true);
                                
                                $state.go("app.hotel", {reloadHotel:true});
    
                            });
                        });
                    });

                });
                    
            }
            //);
            
        };
            
        ///############### INIT BLOCK #######################################
            
        $scope.getUpdateModuleData = function() {

            initHotelRegister();

        }

        $scope.hotelState.getDeferredState().then(function() {
            $scope.getUpdateModuleData();
            $rootScope.showLoading(false)
        });
        
        if ($state.current.name == "app.hotelRegister") {
            $scope.getUpdateModuleData();
            $rootScope.showLoading(false)
        };
        
    }]);