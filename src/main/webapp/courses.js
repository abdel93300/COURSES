angular
.module("listecourses",[])
.controller('coursesCtrl', ['$scope','$http', function ($scope,$http) {
	function successCallback(response) {
		$scope.listeCourses = response.data;
	}
	function errorCallback(){
		console.error(arguments);
	}
	$http({
		method:'GET',
		url:'http://localhost:8080/listeCourses/rest/listecourses'
	}).then (successCallback,errorCallback);
}]
);