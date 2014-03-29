/**
* Routes
* All app routes will be found here and i'm making use of angular-ui-router
* @author Leonardo Frangelli
*/
arctouchApp.config(['$stateProvider','$urlRouterProvider', function($stateProvider, $urlRouterProvider){

  var states = {

    'index': {
      url: '',
      controller: ['$location',
          function($location){
            $location.path('/routes');
          }
      ]
    },

    '404': {
      url: '/404',
      templateUrl: 'views/404.html'
    },

    'routes': {
      url:'/routes',
      templateUrl: 'views/routes_list.html',
      controller: 'RoutesListController'
    },

    'details': {
      abstract: true,
      templateUrl: 'views/routes_details.html',
      controller: 'RoutesDetailsController'
    },

    'details.stops': {
      url:'/details/stops',
      views: {
        'content@details': {
          templateUrl: 'views/route_stops.html',
          controller: 'RoutesDetailsController'
        }
      }
    },

    'details.departures': {
      url:'/details/departures',
      views: {
        'content@routes': {
          templateUrl: 'views/route_departures.html',
          controller: 'RoutesDetailsController'
        }
      }
    }

  };

  angular.forEach(states, function(stateConfig, stateName) {
      $stateProvider.state(stateName, stateConfig);
  });

  $urlRouterProvider.otherwise('/404');


}]);
