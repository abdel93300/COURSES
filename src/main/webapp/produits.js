angular
.module("listeproduits",[])
.controller('produitsCtrl', ['$scope','$http', function ($scope,$http) {
	function successCallback(response) {
		$scope.listeproduits = response.data;
	}
	function errorCallback(){
		console.error(arguments);
	}
	$http({
		method:'GET',
		url:'http://localhost:8080/listeCourses/rest/produits'
	}).then (successCallback,errorCallback);
}]
);