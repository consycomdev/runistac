'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAnexo', [
  'ngRoute',
  'adminAnexo.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Anexo.html', controller: 'AnexoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
