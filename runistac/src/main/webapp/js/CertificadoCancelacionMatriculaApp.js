'use strict';


// Declare app level module which depends on filters, and services
var module=angular.module('adminCertificadoCancelacionMatricula', [
  'ngRoute',
  'adminCertificadoCancelacionMatricula.controllers'
]);
module.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/', {templateUrl: 'partials/CertificadoCancelacionMatricula.html', controller: 'CertificadoCancelacionMatriculaCtrl'});
  $routeProvider.otherwise({redirectTo: '/'}); 
}]);
