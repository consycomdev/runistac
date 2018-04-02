'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminTipoAnexo', [
  'ngRoute',
  'adminTipoAnexo.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/TipoAnexo.html', controller: 'TipoAnexoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
