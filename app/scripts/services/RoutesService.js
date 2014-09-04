/**
* RoutesService
* @author Leonardo Frangelli
*/
arctouchApp.service('RoutesService', ['$http', '$q' , function($http, $q){

  var apiHeaders = {
          'X-AppGlu-Environment': 'staging',
          'Authorization': 'Basic  V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==',
          'Content-Type': 'application/json'
        };

  return {
    findRoutes: function(searchTem) {

      return $http({
        method: 'POST',
        url: ' https://api.appglu.com/v1/queries/findRoutesByStopName/run',
        data: {"params":{"stopName":"%" + searchTem + "%"}},
        headers: apiHeaders
      });

    },
    findStopsByRouteId: function(routeId) {
      return $http({
        method: 'POST',
        url: ' https://api.appglu.com/v1/queries/findStopsByRouteId/run',
        data: {"params":{"routeId":routeId}},
        headers: apiHeaders
      });
    },
    findDeparturesByRouteId: function(routeId) {
      return $http({
        method: 'POST',
        url: ' https://api.appglu.com/v1/queries/findDeparturesByRouteId/run',
        data: {"params":{"routeId":routeId}},
        headers: apiHeaders
      });
    }
  };
}]);
