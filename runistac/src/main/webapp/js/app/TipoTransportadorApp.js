'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminTipoTransportador', [
  'ngRoute',
  'adminTipoTransportador.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/TipoTransportador.html', controller: 'TipoTransportadorCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
