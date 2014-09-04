/**
* RoutesListController
* This controller is responsible for all user interactions
*/
arctouchApp.controller('RoutesListController',['$scope','$rootScope','RoutesService','$state', function ($scope, $rootScope , RoutesService,$state) {

  //Term used to search a route
  $scope.searchTerm = "";

  //method used on Search button click
  $scope.searchRoute = function() {
    var query = RoutesService.findRoutes($scope.searchTerm);
    query.then(
      function(response){
        $rootScope.routes = response.data.rows;
      }, function(err){
        //TODO
      });
  };

  $scope.showDetails = function(routeObj) {
    $rootScope.selectedRoute = routeObj;
    $state.go('details.stops', {'route_id': $rootScope.selectedRoute.id});
  }

  $scope.showStops = function() {
    $state.go('details.stops', {'route_id': $rootScope.selectedRoute.id});
  }

  $scope.showDepartures = function() {
    $state.go('details.departures', {'route_id': $rootScope.selectedRoute.id});
  }

}]);
