'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAprobacionPostulacion', [
  'ngRoute',
  'adminAprobacionPostulacion.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/AprobacionPostulacion.html', controller: 'AprobacionPostulacionCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
