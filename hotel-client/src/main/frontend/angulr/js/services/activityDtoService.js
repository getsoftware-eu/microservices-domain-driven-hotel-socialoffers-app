angular
	.module("hotelApp.activityDto", [ "ngResource" ])
	//app
	.factory("HotelActivityDto", function($resource) {
	return $resource("./activity/customer/:customerId/activity/:activityId", {
		customerId: '@customerId',
		activityId: '@activityId'
		
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
		//getByHotelId: {
		//	method: 'get' ,
		//	headers: {
		//		'Content-Type': 'application/json;charset=UTF-8',
		//		'Accept': 'application/json, text/plain, */*'
		//	}
		//},
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
		}
		,save: {
			method: 'POST'
			,
			headers: {
				'Content-Type': 'application/json;charset=UTF-8',
				'Accept': 'application/json, text/plain, */*'
			}
		}
	});
});
	