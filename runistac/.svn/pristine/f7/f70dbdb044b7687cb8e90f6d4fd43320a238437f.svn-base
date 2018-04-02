'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAseguradora', [
  'ngRoute',
  'adminAseguradora.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Aseguradora.html', controller: 'AseguradoraCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
