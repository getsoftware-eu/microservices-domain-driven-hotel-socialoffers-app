//app
angular.module("hotelApp.logoutCustomerService", [ "ngResource" ])
	
	.factory("LogoutCustomer", function($resource) {
	return $resource("./customers/logout", {
		//eMail: '@eMail',
		//password: '@password'
	}, {
		get: { 
			method: 'GET' ,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		} 
		,logout: { 
			method: 'POST' ,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		} 
	});
});