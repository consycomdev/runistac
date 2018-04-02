'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminEmpresa', [
  'ngRoute',
  'adminEmpresa.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Empresa.html', controller: 'EmpresaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
