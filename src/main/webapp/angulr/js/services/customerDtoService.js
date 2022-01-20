angular.module("hotelApp.customerDto", [ "ngResource" ]).factory("CustomerDto", function($resource) {
	return $resource("./customers/customers/:id/requesterId/:requesterId", {
		
		id: '@id',
		hotelId: '@hotelId',
		requesterId: '@requesterId',
		password: '@password'  //password will be sended automaticly in header , good because java dto-object has no password field to receive!
	}, {
		update: {
			method: "PUT",
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		},
		get: {
			method: 'get' ,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		},
		checkLogin: {
			method: 'POST' ,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		},
		remove: {
			method: "DELETE",
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		},
		save: {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		}
	});
});