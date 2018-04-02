'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminBanco', [
  'ngRoute',
  'adminBanco.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Banco.html', controller: 'BancoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
