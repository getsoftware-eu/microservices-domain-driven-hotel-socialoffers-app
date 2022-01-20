angular.module("hotelApp.sessionCustomerService", [ "ngResource" ]).factory("SessionCustomer", function($resource) {
	return $resource("./customers/validSessionCustomer", {
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
		,save: { 
			method: 'POST' ,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		} 
	});
});
	