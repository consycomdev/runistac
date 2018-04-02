'use strict';


// Declare app level module which depends on filters, and services
var app=angular.module('adminRunistac', [
  'ngRoute',
  'angularUtils.directives.dirPagination',
  'adminRunistac.controllers'
]);
app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {templateUrl: 'partials/menu.html'});
	$routeProvider.when('/EntidadFinanciera', {templateUrl: 'partials/EntidadFinanciera.html', controller: 'EntidadFinancieraCtrl'});
        $routeProvider.when('/TipoGarantiaBancaria', {templateUrl: 'partials/TipoGarantiaBancaria.html', controller: 'TipoGarantiaBancariaCtrl'});
	
  	$routeProvider.otherwise({redirectTo: '/'}); 
}]);

var module=angular.module("adminRunistac.controllers",[]);
