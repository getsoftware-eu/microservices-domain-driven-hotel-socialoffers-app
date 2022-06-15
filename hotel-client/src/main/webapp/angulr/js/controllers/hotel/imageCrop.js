'use strict';
app
//angular.module('hotelApp.imageCrop', ['ngRoute','ImageCropper', 'hotelApp.sessionCustomerService'])
    
    .controller('ImageCropCtrl', ['$scope', '$rootScope', '$state', 'Upload', '$http', '$timeout', 'SessionCustomer', 
        function($scope, $rootScope, $location, Upload, $http, $timeout, SessionCustomer) {


            $scope.localState = {
                //canvasWidth : 325
                loading: false,
                loadComplete: false,
                redirectOnComplete: false
            };
            
            //var firstTime = true;
            //
            //$scope.$watch('cropper.sourceImage', function(newVal) {
            //    
            //    if(firstTime)
            //    {
            //        var width = $('#hiddenImage').width();
            //        var height = $('#hiddenImage').height();
            //        $scope.bounds.left = 0;
            //        $scope.bounds.right = width-20;
            //        $scope.bounds.top = 0;
            //        $scope.bounds.bottom = height-20;
            //    }
            //}
            
          
            
    //    $scope.fileChanged = function(e) {
    //
    //        var files = e.target.files;
    //
    //        var fileReader = new FileReader();
    //
    //        $scope.imgTarget = files[0];
    //        fileReader.readAsDataURL($scope.imgTarget);
    //
    //        fileReader.onload = function(e) {
    //            $scope.imgSrc = this.result;
    //            
    //            $scope.$apply();
    //
    //        };
    //
    //    }
    //
        $scope.clear = function() {
            //$scope.imageCropStep = 1;
            //delete $scope.imgSrc;
            //delete $scope.result;
            //delete $scope.resultBlob;
            $scope.cropper.sourceImage = null;
            $scope.localState.loading = false;

            //firstTime = true;
        };
    //    
    //function initAvatar(){
    //    $scope.hideAside();
    //    $scope.imageCropResult = null;
    //    $scope.showImageCropper = true;
    //    $scope.imageCropStep = 1;
    //}
    //
    //
    //initAvatar();    
    //
    //$scope.$watch('imageCropResult', function(newVal) {
    //    if (newVal) {
    //        console.log('imageCropResult', newVal);
    //
    //        
    //    }
    //
    //});    
    //    
    $scope.uploadAvatar = function()
    {
    
    
        ////Add to uploadable files
        //$scope.mainState.tempUploadFiles["customer"+"_"+customerId] = {
        //    file: files[0],
        //    entityName: "customer"
        //};
        //
        //$scope.uploadNewImage("customer", customerId);
        $scope.uploadFiles();
    }
        
    //initCrop();
   
            function readImg()
            {
                var fileReader = new FileReader();
                //
                //        $scope.imgTarget = files[0];
                        fileReader.readAsDataURL($scope.cropper.croppedImage);
                
                        fileReader.onload = function(e) {
                            $scope.imgSrc = this.result;
                            
                            $scope.$apply();
                
                        };
                
            }
            
            
    function dataURItoBlob(dataURI) {
        // convert base64/URLEncoded data component to raw binary data held in a string
        var byteString;
        if (dataURI.split(',')[0].indexOf('base64') >= 0)
            byteString = atob(dataURI.split(',')[1]);
        else
            byteString = unescape(dataURI.split(',')[1]);
    
        // separate out the mime component
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    
        // write the bytes of the string to a typed array
        var ia = new Uint8Array(byteString.length);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
    
        return new Blob([ia], {type:mimeString});
    }

            function b64toBlob(b64Data, contentType, sliceSize) {
                contentType = contentType || '';
                sliceSize = sliceSize || 512;

                var byteCharacters = atob(b64Data);
                var byteArrays = [];

                for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
                    var slice = byteCharacters.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                var blob = new Blob(byteArrays, {type: contentType});
                return blob;
            }        
            
    $scope.uploadFiles = function() {
        
        $scope.localState.loading = true;
        
        //readImg();
        //var split = $scope.cropper.croppedImage.split(";")
        //
        //var contentType = split[0].replace("data:", "");
        //var base64 = split[1].replace("base64,", "");
        //
        //var blob = b64toBlob(base64, contentType);
        var blob = dataURItoBlob($scope.cropper.croppedImage);
        //var blob = $scope.resultBlob;

        var customerId = $scope.hotelState.profileData? $scope.hotelState.profileData.id : -1;


        blob.name = "avatar" + customerId;
        var files = [blob]
        $scope.files = files;
        //$scope.files=[blob];
        angular.forEach(files, function(file) {
            if (file && !file.$error) {
                file.upload = Upload.upload({
                    url: '/'+$rootScope.HOST_SUFFIX+'files/upload/avatar',
                    file: file
                });
    
                file.upload.then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                        if(response.status == 200)
                        {
                            SessionCustomer.get(function(response) {
                                
                                $scope.localState.loadComplete = true;
                                
                                if($scope.localState.redirectOnComplete)
                                {
                                    $scope.hotelState.goStateBack();
                                }

                                setTimeout(function(){

                                    var requesterId = $scope.hotelState.profileData.id;

                                    $scope.hotelService.getEntityLogo(requesterId, "avatar", requesterId).then(function(imageJson){

                                        if(imageJson)
                                        {
                                            //var split = imageUrl.split("|");

                                            //if(split.length>2)
                                            {
                                                var entity = imageJson.entityType;
                                                var entityId = imageJson.entityId;
                                                var entityPictureUrl = imageJson.pictureUrl;
                                                
                                                if($scope.hotelState.profileData && $scope.hotelState.profileData.id == entityId && entityPictureUrl)
                                                    $scope.hotelState.profileData.avatarUrl = entityPictureUrl;

                                            }
                                        }
                                    })
                                }, 2000);
                                
                            });
                        }
                    });
                }, function (response) {
                    if (response.status > 0)
                        $scope.mainState.errorMsg = response.status + ': ' + response.data;
                });
    
                file.upload.progress(function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                    evt.loaded / evt.total));
                });
            }
        });
    }

            $scope.goBack = function(){
                $scope.hotelState.goStateBack();

            }

            function initAvatar(){
                
                $rootScope.hideAside();
                
                $scope.bounds = {};
                $scope.cropper = {};
                $scope.cropper.sourceImage = null;
                $scope.cropper.croppedImage   = null;
                $scope.bounds = {};
                $scope.bounds.left = 0;
                $scope.bounds.right = 0;
                $scope.bounds.top = 0;
                $scope.bounds.bottom = 0;
            }
           

            
            initAvatar();
 

            
        //};

// Kick everything off with the target image
        //resizeableImage($('.resize-image'));
            
            
            
    }
])
    //.directive('progressBar', [
    //    function () {
    //        return {
    //            link: function ($scope, el, attrs) {
    //                $scope.$watch(attrs.progressBar, function (newValue) {
    //                    el.css('width', 
    //                        newValue.toString() + '%');
    //                });
    //            }
    //        };
    //    }
    //])
;

