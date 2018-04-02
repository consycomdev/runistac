'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminCausalesRechazo', [
  'ngRoute',
  'adminCausalesRechazo.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/CausalesRechazo.html', controller: 'CausalesRechazoCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
