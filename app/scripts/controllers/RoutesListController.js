arctouchApp.controller('RoutesListController',['$scope','$location', function ($scope , $location) {

  $scope.searchTerm = "";

  $scope.searchRoute = function() {

    $location.path('/details/stops');

  };

}]);
