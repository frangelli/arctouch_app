arctouchApp.controller('RoutesListController',['$scope','$location','$state', function ($scope , $location,$state) {

  $scope.searchTerm = "";

  $scope.searchRoute = function() {

    $state.go('details.stops');

  };

}]);
