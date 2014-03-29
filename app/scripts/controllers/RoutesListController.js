arctouchApp.controller('RoutesListController',['$scope','$location', function ($scope , $location) {

  $scope.searchTerm = "";

  $scope.searchRoute = function() {

    $location.path('/routes/details');

  };

}]);
