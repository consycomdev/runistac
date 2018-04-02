'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminAsignacionCCM', [
  'ngRoute',
  'adminAsignacionCCM.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/AsignacionCCM.html', controller: 'AsignacionCCMCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
