// The service worker running in background to receive the incoming
// push notifications and user clicks

self.customerId = 0;
self.currentState = null;
self.hostSuffix = "";
self.relocateUrl = '/' + self.hostSuffix + '#/app/chatList/false//';

self.addEventListener('message', function handler (event) {
  console.log(event.data);

  if(!event.data)
  {
    return;
  }
  
  switch(event.data.command) {
    
    case "updateHostSuffix":
      self.hostSuffix = event.data.key;
      self.relocateUrl = '/' + self.hostSuffix + '#/app/chatList/false//';
      break; 
    
    case "updateCustomerId":
      self.customerId = event.data.key;
      break; 
    
    case "updateState":
      self.currentState = event.data.key;
      break;
    
    case "unloadState":
      self.currentState = null;
      break;
    
    default:
      break;
  }
  
});

// A push has arrived ...
self.addEventListener('push', function(event) {
  // Since there is no payload data with the first version  
  // of push messages, we'll use some static content. 
  // However you could grab some data from  
  // an API and use it to populate a notification

  console.log('Received a push message', event);
  
  if(self.customerId<=0)
  {
    return;
  }

  {
    
    var HOTELICO_LAST_NOTIFICATION_ENDPOINT = '/' + self.hostSuffix + "customers/customerLastNotification/"+self.customerId + "/push/"+true;
    
    event.waitUntil(
        fetch(HOTELICO_LAST_NOTIFICATION_ENDPOINT).then(function(response) {
          if (response.status !== 200) {
            // Either show a message to the user explaining the error  
            // or enter a generic message and handle the
            // onnotificationclick event to direct the user to a web page  
            console.log('Looks like there was a problem. Status Code: ' + response.status);
            throw new Error();
          }

          // Examine the text in the response  
          return response.json().then(function(data) {
            if (data.error || !data.customerEvent) {
              console.error('The API returned an error.', data.error);
              throw new Error();
            }

            var title = data.customerEvent.pushTitle;
            var message = data.customerEvent.pushMessage;
            var icon = data.customerEvent.pushIcon;
            var chatPartnerId = data.customerEvent.chatPartnerId;
            var notificationTag = data.customerEvent.pushTagId;
            self.relocateUrl = data.customerEvent.pushUrl;
            
            if(self.hostSuffix && (self.relocateUrl+"").indexOf("/#")==0 )
            {
              self.relocateUrl = '/' + self.hostSuffix + self.relocateUrl.substr(1);
            }
            
            if(self.currentState && (self.currentState+"").indexOf("app.chat#"+chatPartnerId)>=0)
            {
              return;
            }
            
            return self.registration.showNotification(title, {
              body: message,
              icon: icon
              , tag: notificationTag
            });
          });
        }).catch(function(err) {
          console.error('Unable to retrieve data', err);

          var title = 'An error occurred';
          var message = 'We were unable to get the information for this push message';
          var icon = URL_TO_DEFAULT_ICON;
          var notificationTag = 'notification-error';
          return self.registration.showNotification(title, {
            body: message,
            icon: icon,
            tag: notificationTag
          });
        })
    );
    
  }
   
});


/**
 * Eugen: Google:
 * This example opens the browser to the root of the site’s origin, 
 * by focusing an existing same-origin tab if one exists, and otherwise opening a new one.
 */

// The user has clicked on the notification ...
self.addEventListener('notificationclick', function(event) {

  console.log('On notification click: ', event.notification.tag);

  // Android doesn’t close the notification when you click on it
  // See: http://crbug.com/463146
  event.notification.close();

  // This looks to see if the current is already open and
  // focuses if it is
  event.waitUntil(
      clients.matchAll({
        type: "window"
      })
    .then(function(clientList) {
		
    for (var a = 0; a < clientList.length; a++) {
      var client = clientList[a];
      //if (client.url == '/' && 'focus' in client)
      if ((client.url+"").indexOf(self.relocateUrl)>=0 && 'focus' in client)
	  {
		return client.focus();
	  }
    }
	
	for (var b = 0; b < clientList.length; b++) {
	  var client = clientList[b];
	  //if (client.url == '/' && 'focus' in client)
	  if ((client.url+"").indexOf("/hotelico.de/")>=0 && 'focus' in client && 'navigate' in client)
	  {
		client.navigate(self.relocateUrl);
		return client.focus();
	  }
    }
	
    if (clients.openWindow) 
      //Eugen: why not '/' ??? in google tutorial
      //var url = '/#/app/chatList/false//';    
      return clients.openWindow(self.relocateUrl);
  }));
});
