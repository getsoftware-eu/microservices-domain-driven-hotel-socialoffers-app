//angular.module("hotelApp.userDto", [ "ngResource" ]).factory("UserDto",  ['$resource', function($resource) {
//	return $resource("./users/users/:id", {
//		id: '@id',
//		hotelId: '@hotelId'
//		
//	}, {
//		update: {
//			method: "PUT"
//		},
//		get: {
//			method: 'get' ,
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		},
//		getByHotelId: {
//			method: 'get' ,
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		},
//		//checkLogin: {
//		//	method: 'POST' ,
//		//	headers: {
//		//		'Content-Type': 'application/json;charset=UTF-8',
//		//		'Accept': 'application/json, text/plain, */*'
//		//	}
//		//},
//		remove: {
//			method: "DELETE",
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		},
//		save: {
//			method: 'POST',
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		},
//		add: {
//			method: 'POST',
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		},
//		register: {
//			method: 'PUT',
//			headers: {
//				'Content-Type': 'application/json;charset=UTF-8',
//				'Accept': 'application/json, text/plain, */*'
//			}
//		}
//	});
//}]);
//
//
