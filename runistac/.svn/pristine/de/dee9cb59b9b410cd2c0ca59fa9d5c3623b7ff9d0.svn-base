'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminConsultaPostulacion', [
  'ngRoute',
  'adminConsultaPostulacion.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/ConsultaPostulacion.html', controller: 'ConsultaPostulacionCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
