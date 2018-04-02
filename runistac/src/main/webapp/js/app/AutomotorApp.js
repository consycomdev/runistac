'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAutomotor', [
  'ngRoute',
  'adminAutomotor.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Automotor.html', controller: 'AutomotorCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
