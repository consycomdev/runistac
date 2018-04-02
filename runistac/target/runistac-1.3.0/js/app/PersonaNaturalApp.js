'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminPersonaNatural', [
  'ngRoute',
  'adminPersonaNatural.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/PersonaNatural.html', controller: 'PersonaNaturalCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
