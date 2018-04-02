'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminValorPoliza', [
  'ngRoute',
  'adminValorPoliza.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/ValorPoliza.html', controller: 'ValorPolizaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
