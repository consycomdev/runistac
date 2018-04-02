'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAprobacionDesistimiento', [
  'ngRoute',
  'adminAprobacionDesistimiento.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/AprobacionDesistimiento.html', controller: 'AprobacionDesistimientoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
