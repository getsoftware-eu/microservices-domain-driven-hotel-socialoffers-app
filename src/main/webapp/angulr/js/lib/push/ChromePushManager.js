
var ChromePushManager = function(serviceWorkerPath, callback, onlyCheckExisting){
	if ('serviceWorker' in navigator) {
		navigator.serviceWorker.register(serviceWorkerPath)
		.then(ChromePushManager.initialiseState(callback, onlyCheckExisting));
	} else {
		callback('Service workers aren\'t supported in this browser.', null);
	}
}

ChromePushManager.initialiseState = function (callback, onlyCheckExisting) {  
  // Are Notifications supported in the service worker?  
  if (!('showNotification' in ServiceWorkerRegistration.prototype)) {  
    callback('Notifications aren\'t supported.', null);  
  } else if (Notification.permission === 'denied') {  
    callback('The user has blocked notifications.', null);  
  } else if (!('PushManager' in window)) {  
    callback('Push messaging isn\'t supported.', null);  
  } else {
      
    if(onlyCheckExisting)  
    {
        ChromePushManager.checkExistingSubscription(callback);
    }
    else 
    {
        ChromePushManager.subscribeBrowserId(callback);
    }
  }
}

ChromePushManager.isPushEnabled = false;
ChromePushManager.subscription = null;

ChromePushManager.checkExistingSubscription = function(callback) {
    navigator.serviceWorker.ready.then(function (serviceWorkerRegistration) {

        // Do we already have a push message subscription?  
        serviceWorkerRegistration.pushManager.getSubscription()
            .then(function (subscription) {
                // Enable any UI which subscribes / unsubscribes from  
                // push messages.  
                var pushButton = document.querySelector('.js-push-button');
                
                if(pushButton) {
                    pushButton.disabled = false;
                }
                
                if(subscription)
                {
                    ChromePushManager.subscribeBrowserId(callback);

                    //ChromePushManager.subscription = subscription;
                    //// Keep your server in sync with the latest subscriptionId
                    ////TODO Eugen: sendSubscriptionToServer(subscription);
                    //
                    //// Set your UI to show they have subscribed for  
                    //// push messages  
                    //if(pushButton)
                    //{
                    //    pushButton.textContent = 'Disable Push Messages';
                    //}
                    //
                    //ChromePushManager.isPushEnabled = true;
                    //
                    //var register = ChromePushManager.getRegistrationId(subscription);
                    //
                    //callback(null, register);
                }
                else // (!subscription)
                {
                    callback('notSubscribed', null);
                    //    ChromePushManager.subscribeBrowserId(callback);
                    //    // We aren't subscribed to push, so set UI  
                    //    // to allow the user to enable push  
                    //    return;
                }
               
            })
            .catch(function (err) {
                console.warn('Error during getSubscription()', err);
            });
    });
}


ChromePushManager.subscribeBrowserId = function(callback) {  
  navigator.serviceWorker.ready.then(function(serviceWorkerRegistration) {  
    serviceWorkerRegistration.pushManager.subscribe({userVisibleOnly: true})  
        
      //If the promise returned by the subscribe() method resolves, you’ll be given a PushSubscription object which will contain an endpoint.
      //"subscription" - The endpoint should be saved on your server for each user, since you’ll need them to send push messages at a later date.
        
      .then(function(subscription) {  
        var register = ChromePushManager.getRegistrationId(subscription);
          ChromePushManager.isPushEnabled = true;

          callback(null, register);
      })  
      .catch(function(e) {  
        if (Notification.permission === 'denied') {  
          callback('Permission for Notifications was denied', null);  
        } else {  
          callback('Unable to subscribe to push.', null);  
        }  
      });  
  }); 
}

ChromePushManager.getRegistrationId = function(pushSubscription) {
  if (pushSubscription.subscriptionId) {
    return pushSubscription.subscriptionId;
  }

  var endpoint = 'https://android.googleapis.com/gcm/send/';
  parts = pushSubscription.endpoint.split(endpoint);

  if(parts.length > 1)
  {
    return parts[1];
  }

} 

