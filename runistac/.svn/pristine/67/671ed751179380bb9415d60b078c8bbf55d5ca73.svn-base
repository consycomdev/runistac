'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminParametroSistema', [
  'ngRoute',
  'adminParametroSistema.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/ParametroSistema.html', controller: 'ParametroSistemaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
