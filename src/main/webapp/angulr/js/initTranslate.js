//angular.module('hotelApp.initTranslate', [])
app
        //Let's now 'teach' the putHttpCache method to take translations from $templateCache:
        //http://fadeit.dk/blog/post/preload-angular-translate-partial-loader
        .run(function run ($cacheFactory, $templateCache) {

            var httpCache = $cacheFactory.get('$http');

            var putHttpCache = function putHttpCache(cacheKey){
                var cacheObj = [200, $templateCache.get(cacheKey), {}, "OK"];
                httpCache.put('/' + cacheKey, cacheObj); //prepend '/' to make url's match
            };
            putHttpCache('angulr/l10n/en.json');
            putHttpCache('angulr/l10n/de.json');
        });