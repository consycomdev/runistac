'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminTipoDocumento', [
  'ngRoute',
  'adminTipoDocumento.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/TipoDocumento.html', controller: 'TipoDocumentoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
