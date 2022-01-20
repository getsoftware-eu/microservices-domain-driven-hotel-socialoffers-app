//app
angular.module("hotelApp.hotelCheckinService", [ "ngResource", "hotelApp.sessionCustomerService", "hotelApp.hotelDto", "hotelApp.sessionCustomerService", "hotelApp.hotelState" ])
    .factory("HotelCheckin", function($q, $rootScope, $resource, $state, $location, $cookies, $timeout, HotelDto, SessionCustomer, HotelState, HotelService, CustomerDto, $filter, $log, NotificationService ) {
        
        var _self = this;
        
        var deferredCheckin = $q.defer();

        var service = {

            hotelId: 0,

            checkinRestService : $resource("./customers/updateCheckin", {
                password: '@password'
            }, {
                get: {
                    method: 'GET' ,
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
                ,update: {
                    method: 'POST' ,
                    headers: {
                        //Problems here
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
            }),
            
            hotelByCodeService : $resource("./hotels/customer/:customerId/hotelCode/:hotelCode", {
                hotelCode: '@hotelCode',
                customerId: '@customerId'
            }, {
                get: {
                    method: 'GET' ,
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        'Accept': 'application/json, text/plain, */*'
                    }
                }
            }),
            
            hotelCode: null,
            
            checkinSuccess : false,
            checkinResponse : false,
            
            //checkinDates : {
                checkinFrom : null,
                checkinTo : null,
            //},
                
            config: function(){

                _self = this;

                _self.hotelState = HotelState;
                _self.hotelService = HotelService;
                
                HotelState.getDeferredState().then(function(hotelState){
                    _self.hotelState = hotelState;
                    deferredCheckin.resolve(_self);
                });
                
            },
            
            getCheckinRestService: function(){
                
                return _self.checkinRestService;
            },

            resetWholeInfo: function(){
                _self.resetHotelSelectInfo();
                _self.hotelCode = null;
            },
            
            resetHotelSelectInfo: function(){
                _self.checkinSuccess = false;
                _self.checkinResponse = false;
                _self.hotelId= 0;
                //_self.hotelCode= undefined;
            },
            
            getDeferredState: function () {
                return deferredCheckin.promise;
            }
            ,

            ///**
            // * Load hotels based on the available hotel identification indicators
            // */
            //loadHotels : function() {
            //
            //    return HotelDto.query({customerId: $scope.hotelState.profileData.id})
            //},
            //
            //loadHotelCities = function() {
            //
            //    HotelCitiesService.getCities({customerId:$scope.hotelState.profileData.id}, function(cityArray) {
            //        $scope.hotelCitiesArray = cityArray;
            //    });
            //},
            //
            //getHotelByCity = function(city) {
            //
            //    var HotelByCityService = $resource('./hotels/customer/:customerId/hotel/city/:city', {customerId:'@customerId', city:'@city' }, {'getHotelsByCity': {method: 'GET', isArray: true}});
            //
            //    HotelByCityService.getHotelsByCity({customerId:$scope.hotelState.profileData.id, city: city}, function(hotelsArray) {
            //        $scope.hotelsFilteredByCityArray = hotelsArray;
            //    });
            //},

            doCheckin: function() {

                var checkinDeffered = $q.defer();
                
                if(_self.checkinSuccess || _self.checkinResponse)
                {
                    checkinDeffered.resolve();
                    return checkinDeffered.promise;
                }

                _self.checkinSuccess = false;
                _self.checkinResponse = false;

                if(_self.hotelId<=0 && !_self.hotelCode)
                {
                    checkinDeffered.resolve();

                    return checkinDeffered.promise;
                }
    
                //var fullCheckin = _self.hotelCode? true : false;
    
                var checkinDateFrom = _self.checkinFrom!=null? new Date(_self.checkinFrom):null;
                var checkinDateTo = _self.checkinFrom!=null? new Date(_self.checkinFrom):null;
    
                if(!_self.hotelId && _self.hotelId>0 && !_self.hotelCode)
                {
                    $rootScope.rootErrorMsg = $filter('translate')('alert.checkin.chooseHotel');
                    $rootScope.topPage();
                    $rootScope.showLoading(false);
                    checkinDeffered.resolve();

                    return checkinDeffered.promise;
                }
    
                if(checkinDateFrom==null)
                {
                    //$scope.mainState.errorMsg = "Please choose a valid start date";
                    //return;
                    checkinDateFrom = new Date();
    
                }
    
                if(checkinDateTo==null)
                {
                    //$scope.mainState.errorMsg = "Please choose a valid checkout date";
                    //return;
                    checkinDateTo = HotelService.getNextSundayMiddayDate();
                }
    
                if(_self.hotelId)
                    _self.hotelState.profileData.hotelId = _self.hotelId;
    
                if(_self.hotelCode)
                    _self.hotelState.profileData.hotelCode = _self.hotelCode;
    
                //if(fullCheckin)
                //    _self.hotelState.profileData.fullCheckin = fullCheckin;

                _self.hotelState.profileData.checkinFrom = checkinDateFrom;

                _self.hotelState.profileData.checkinTo = checkinDateTo;
    
                if(_self.hotelState.profileData.checkedIn && (_self.hotelState.profileData.fullCheckin || !_self.hotelCode))
                {
                    checkinDeffered.resolve();

                    //TODO Eugen: whot to do if already checkin???
                    return checkinDeffered.promise;
                }
                
                //Eugen: NO LOADING IN SERVICE: ONLY BEFORE AND AFTER!!!
                //$rootScope.showLoading(true);

                if(_self.hotelState.profileData.id ==0 || !_self.hotelState.profileData.logged)
                {
                    _self.hotelState.profileData.checkedIn = true;
                    
                    
                    if(_self.hotelState.profileData.hotelId && _self.hotelState.profileData.hotelId>0)
                    {
                        _self.hotelState.submitHotelGuestPush();
                        
                        NotificationService.resubscribeWithHotelData();

                        $state.go("app.hotelPreview", {hotelId: _self.hotelState.profileData.hotelId});
                    }
                    
                    else if(!_self.hotelState.profileData.hotelCode)
                    {
                        _self.hotelState.profileData.hotelCode = _self.hotelService.getCookie('hotelicoNoLoginHotelCode');

                    }
                    else if(_self.hotelState.profileData.hotelCode)
                    {
                        _self.hotelService.setCookie('hotelicoNoLoginHotelCode', _self.hotelState.profileData.hotelCode);

                        if(_self.hotelState.profileData.hotelCode=="demo")
                        {
                            var demoHotelIdElt = document.getElementById("demoHotelId");

                            if(demoHotelIdElt && (demoHotelIdElt.innerText || demoHotelIdElt.innerHTML))
                            {
                                var demoHotelId = demoHotelIdElt.innerText? demoHotelIdElt.innerText : demoHotelIdElt.innerHTML;
                                //$scope.getUpdateMainHotelObject(demoHotelId).then(function(hotelObj){
                                _self.hotelState.profileData.hotelId = demoHotelId;
                                _self.hotelService.setCookie('hotelicoNoLoginHotelId', _self.hotelState.profileData.hotelId);

                                NotificationService.resubscribeWithHotelData();

                                _self.hotelState.submitHotelGuestPush();
                                
                                $state.go("app.hotelPreview", {hotelId: _self.hotelState.profileData.hotelId});
                                //});
                            }
                        }
                        else{
                            _self.hotelByCodeService.get({customerId : _self.hotelState.profileData.id , hotelCode: _self.hotelState.profileData.hotelCode}, function(hotelObj){
                                
                                if(!hotelObj.id)
                                {
                                    $rootScope.rootErrorMsg = $filter('translate')('alert.error.noHotelFound');
                                    $rootScope.showLoading(false);
                                    _self.hotelState.profileData.checkedIn = false;
                                    _self.hotelState.profileData.hotelId = 0;
                                    _self.hotelState.profileData.hotelCode = "";
                                    _self.hotelService.setCookie('hotelicoNoLoginHotelCode', "");
                                    _self.hotelService.setCookie('hotelicoNoLoginHotelId', "");

                                    checkinDeffered.resolve();
                                }
                               else{
                                    _self.hotelState.profileData.hotelId = hotelObj.id;
                                    _self.hotelService.setCookie('hotelicoNoLoginHotelId', _self.hotelState.profileData.hotelId);

                                    NotificationService.resubscribeWithHotelData();

                                    _self.hotelState.submitHotelGuestPush();
                                    
                                    $state.go("app.hotelPreview", {hotelId: _self.hotelState.profileData.hotelId});
                                }
                         
                            });
                        } 
                    }
                    
                    //TODO Eugen: if no hotelId and hotelCode???
                    
                    checkinDeffered.reject("noLogin")
                    return checkinDeffered.promise;
                }
                
                //TODO Eugen: angular error receive!
                var checkinResponse = _self.getCheckinRestService().update({ id : _self.hotelState.profileData.id }, _self.hotelState.profileData, function()
                {
                    $rootScope.rootErrorMsg = false;
                    _self.checkinResponse = true;

                    _self.hotelState.setProfileData(checkinResponse);
    
                    if(checkinResponse.id && checkinResponse.id>0)
                    {
    
                        if(checkinResponse.errorResponse && checkinResponse.errorResponse!='undefined')
                        {
                            $rootScope.rootErrorMsg = checkinResponse.errorResponse;
                            $rootScope.topPage();
                            $rootScope.showLoading(false);
                            //_self.checkinResponse = false;
                            checkinDeffered.resolve();

                            return
                        }
                        else if(checkinResponse.hotelId > 0)
                        {
                            _self.checkinSuccess = true;

                            NotificationService.resubscribeWithHotelData();

                            //reset info for next checkin process
                            _self.hotelCode = null;
                            _self.hotelId = 0;
                            
                            //Eugen: double call
                            //_self.hotelState.emptyCheckinData();
    
                            $rootScope.showLoading(true);
                            $state.go("app.hotel");
                            checkinDeffered.resolve();
                        }
                        else if(checkinResponse.hotelId == 0)
                        {
                            $rootScope.rootErrorMsg = $filter('translate')('alert.error.noHotelFound');
                            $rootScope.showLoading(false);
                            //_self.checkinResponse = false;
                            checkinDeffered.resolve();

                        }
                    }

                    checkinDeffered.resolve();


                }, function(error){
                    
                    //$scope.mainState.errorMsg = "connection problem";
                    $rootScope.showLoading(false);
                    checkinDeffered.resolve();

                });
    
                $timeout(function(){
                    if(_self.doCheckin) {
                        _self.doCheckin();
                    }
                }, 8000);
                
                return checkinDeffered.promise;
            },

            //TODO Eugen: if not logged
            resetCheckin : function()
            {
                _self.hotelService.setCookie('hotelicoNoLoginHotelCode', "");
                _self.hotelService.setCookie('hotelicoNoLoginHotelId', "");
                
                if(_self.hotelState)
                {
                    if(_self.hotelState.profileData.id>0)
                    {
                        var user = CustomerDto.get({id:_self.hotelState.profileData.id, requesterId: _self.hotelState.profileData.id}, function() {
    
                            //Eugen: REST HOTEL_ID
                            user.hotelId = "0";
    
                            //if($rootScope.virtualHotel.id>0)
                            //{
                            //    user.hotelId = $rootScope.virtualHotel.id;
                            //}
    
                            if(user.checkedIn)
                            {
                                $rootScope.showLoading(true);
    
                                _self.getCheckinRestService().update({id: user.id}, user, function(response){

                                    _self.hotelState.setProfileData(response);

                                    _self.hotelState.emptyCheckinData();

                                    NotificationService.resubscribeWithHotelData();

                                    $state.go("app.checkin");
    
                                }, function(error){
                                    $rootScope.rootErrorMsg = error;
                                });
                            }else{
                                _self.hotelState.updateState(user);
                            }
    
                        });
                    }
                    else //IF CHECKIN, BUT NOT LOGGED
                    {
                        if(_self.hotelService.chromePushRegistrationId)
                        {
                            var requesterId = _self.hotelState.getRequesterId();
                            var hotelId = _self.hotelState.profileData.hotelId;

                            //_self.hotelState.profileData.systemMessages["chromePushRegistrationId"] = _self.hotelService.chromePushRegistrationId
                            var encodeCustomer = _self.hotelService.encodeCustomer(_self.hotelState.profileData);

                            //TODO Eugen: reset guest pushId in hotel actin
                            _self.hotelService.getHotelGuestActionService().addAction({action:"removeGuestPushId", hotelId: hotelId, guestCustomerId: requesterId}, encodeCustomer, function(profObj){
                                
                            });
                        }
                        
                        _self.hotelState.profileData.hotelId = 0;
                        _self.hotelState.profileData.checkedIn = false;
                        _self.hotelState.profileData.hotelName = undefined;
                        _self.hotelState.profileData.hotelCity = undefined;
                        _self.hotelState.profileData.hotelCode = undefined;
                        $state.go("app.checkin");
                    }
                }
    
            },
            
            checkHotelCode: function(hotelId, inputHotelCode){

                var checkHotelCodeDeffered = $q.defer();
                
                var requesterId = _self.hotelState.profileData.id? _self.hotelState.profileData.id : -1;
                
                _self.hotelByCodeService.get({customerId : requesterId , hotelCode: inputHotelCode}, function(hotelObj){

                    if(hotelObj.id && hotelObj.id==hotelId)
                    {
                        checkHotelCodeDeffered.resolve(hotelObj);
                    }
                    else{
                        $rootScope.showLoading(false);

                        checkHotelCodeDeffered.reject($filter('translate')('alert.error.wrongHotelCode'));
                    }

                }, function(error){
                    checkHotelCodeDeffered.reject(error);
                });
                
                return checkHotelCodeDeffered.promise;
            }
        
     };

      service.config();  
      //service.getState().setStaticService(service);  
        
     return service;
        
});
 
