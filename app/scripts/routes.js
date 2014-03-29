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

    'main': {
          abstract: true,
          templateUrl: 'views/main.html',
          controller: 'MainController'
    },

    'main.routes': {
      url:'/routes',
      views: {
        'content@main': {
          templateUrl: 'views/routes_list.html',
          controller: 'RoutesListController'
        }
      }
    },

    'main.routes.details': {
      url:'/routes/details',
      views: {
        'content@main': {
          templateUrl: 'views/routes_details.html',
          controller: 'RoutesDetailsController'
        }
      }
    }

  };

  angular.forEach(states, function(stateConfig, stateName) {
      $stateProvider.state(stateName, stateConfig);
  });


}]);
