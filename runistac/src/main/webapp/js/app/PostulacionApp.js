'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminPostulacion', [
  'ngRoute', 'angularUtils.directives.dirPagination',
  'adminPostulacion.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Postulacion.html', controller: 'PostulacionCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
