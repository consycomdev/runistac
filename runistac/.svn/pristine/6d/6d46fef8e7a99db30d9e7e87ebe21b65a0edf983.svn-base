'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminPersona', [
  'ngRoute',
  'adminPersona.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/Persona.html', controller: 'PersonaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
