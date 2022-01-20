angular.module("hotelApp.hotelDto", [ "ngResource" ])
	.factory("HotelDto", function($resource) {
	return $resource("./hotels/customer/:customerId/hotel/:hotelId", {
		hotelId: '@hotelId',
		customerId: '@customerId'
	}, {
		update: {
			method: "PUT",
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		},
		get: { 
			//cache: true, 
			method: 'get' },
		getList: { 
			//cache: true, 
			method: 'get',
			isArray: true,
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
		}
		
	});
});

