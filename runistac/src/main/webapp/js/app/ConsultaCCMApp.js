'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminConsultaCCM', [
  'ngRoute', 'angularUtils.directives.dirPagination',
  'adminConsultaCCM.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/ConsultaCCM.html', controller: 'ConsultaCCMCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
