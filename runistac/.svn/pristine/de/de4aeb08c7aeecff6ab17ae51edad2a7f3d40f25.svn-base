'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAprobacionPoliza', [
  'ngRoute',
  'adminAprobacionPoliza.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/AprobacionPoliza.html', controller: 'AprobacionPolizaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
