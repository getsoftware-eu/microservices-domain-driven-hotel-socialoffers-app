angular.module('app')
    .directive('dropzone', ['$rootScope', '$location', function($rootScope, $location) {
        return {
            restrict: 'C',
            link: function(scope, element, attrs) {

                //http://www.dropzonejs.com/#configuration
                var config = {
                    url: '/'+$rootScope.HOST_SUFFIX+'files/upload',
                    maxFilesize: 100,
                    //    thumbnailWidth: 80,
                    //    thumbnailHeight: 80,
                    //    parallelUploads: 20,
                    //    previewTemplate: previewTemplate,
                    //    previewsContainer: "#previewsContainer", // Define the container to display the previews
                    //    autoQueue: false, // Make sure the files aren't queued until manually added

                    paramName: "uploadfile",
                    maxThumbnailFilesize: 10,
                    parallelUploads: 1,
                    autoProcessQueue: false
                };

                //{ // Make the whole body a dropzone
                //    url: "/target-url", // Set the url
                //    thumbnailWidth: 80,
                //    thumbnailHeight: 80,
                //    parallelUploads: 20,
                //    previewTemplate: previewTemplate,
                //    autoQueue: false, // Make sure the files aren't queued until manually added
                //    previewsContainer: "#previews", // Define the container to display the previews
                //    clickable: ".fileinput-button" // Define the element that should be used as click trigger to select files.
                //}

                var eventHandlers = {
                    'addedfile': function(file) {
                        scope.file = file;
                        var url = $location.url();

                       var urlSplit =  url.replace("/app/", "").split("/");
                        var page = urlSplit[0];
                        
                        var pageId = urlSplit.length>1? urlSplit[1] : 0;

                        //dropzone.options.url = '/' + $rootScope.HOST_SUFFIX + 'files/upload'+'/customer/'+$scope.hotelState.profileData.id+"/model/"+page+"/"+pageId;

                        var typeSplit = scope.file.name.split(".");
                        var type = typeSplit[typeSplit.length-1];

                        dropzone.options.paramName = $scope.hotelState.profileData.hotelId + "#" + $scope.hotelState.profileData.id + "#" + page + "#"+ pageId;

                        scope.dropzone = dropzone;

                        if (this.files[1]!=null) {
                            this.removeFile(this.files[0]);
                        }
                        scope.$apply(function() {
                            scope.dropzone = dropzone;
                            scope.fileAdded = true;
                        });
                    },

                    'success': function (file, response) {
                    }

                };

                dropzone = new Dropzone(element[0], config);

                angular.forEach(eventHandlers, function(handler, event) {
                    dropzone.on(event, handler);
                });

                scope.processDropzone = function() {
                    dropzone.processQueue();
                };

                scope.resetDropzone = function() {
                    dropzone.removeAllFiles();
                }

                scope.dropzone = dropzone;
                $rootScope.dropzone = dropzone;
            }
        }
    }]);