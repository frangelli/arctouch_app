/**
* RoutesDeparturesController
* This controller is responsible for all user interactions
*/
arctouchApp.controller('RoutesDeparturesController',['$scope','RoutesService','$state', '$stateParams', function ($scope , RoutesService,$state, $stateParams) {

  var queryDepartures = RoutesService.findDeparturesByRouteId($stateParams.route_id);
  queryDepartures.then(
    function(response){
      $scope.departures = response.data.rows;
      console.log($scope.departures);
      $state.go('details.departures');
    }, function(err){
      //TODO
    });

}]);
