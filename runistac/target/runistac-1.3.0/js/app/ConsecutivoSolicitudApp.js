'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminConsecutivoSolicitud', [
  'ngRoute',
  'adminConsecutivoSolicitud.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/ConsecutivoSolicitud.html', controller: 'ConsecutivoSolicitudCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
