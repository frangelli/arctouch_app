/**
* RoutesStopsController
* This controller is responsible for all user interactions
*/
arctouchApp.controller('RoutesStopsController',['$scope','RoutesService','$state', '$stateParams', function ($scope , RoutesService,$state,$stateParams) {

  var queryStops = RoutesService.findStopsByRouteId($stateParams.route_id);
  queryStops.then(
    function(response){
      $scope.stops = response.data.rows;
      console.log($scope.stops);
      $state.go('details.stops');
    }, function(err){
      //TODO
    });


}]);
