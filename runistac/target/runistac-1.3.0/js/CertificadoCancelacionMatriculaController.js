'use strict';

/* Controllers */
var module = angular.module('adminCertificadoCancelacionMatricula.controllers', []);

module.controller('CertificadoCancelacionMatriculaCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
    //listar
    $scope.lista = {};
    $scope.datosFormulario = {};
    $scope.panelEditar = false;
    $scope.listar=function(){
        $http.get('./webresources/CertificadoCancelacionMatricula', {})
            .success(function (data, status, headers, config) {
                $scope.lista = data;
            }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
        });    
    };

        $scope.listarAutomotor=function(){
            $http.get('./webresources/Automotor', {})
                .success(function (data, status, headers, config) {
                    $scope.listaAutomotor = data;
                }).error(function (data, status, headers, config) {
                    alert('Error al consultar la informaci\xf3n de automotor, por favor intente m\xe1s tarde');
            });    
        };
        $scope.listarAutomotor();
        

    $scope.listar();
    //guardar
    $scope.nuevo = function () {
        $scope.panelEditar = true;
        $scope.datosFormulario = {};
    };
    
    $scope.guardar = function () {
        $scope.errores = {};
        var error = false;
        
        if (error)
            return;
        $http.post('./webresources/CertificadoCancelacionMatricula', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con \u00E9xito");
                $scope.panelEditar = false;
                $scope.listar();
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
    };
    $scope.cancelar = function () {
        $scope.panelEditar = false;
        $scope.datosFormulario = {};
    };

    //editar
    $scope.editar = function (data) {
        $scope.panelEditar = true;
        $scope.datosFormulario = data;
    };
    //eliminar
    $scope.eliminar = function (data){
        if (confirm('�Desea elminar este registro?')) {    
            $http.delete('./webresources/CertificadoCancelacionMatricula/'+data.id, {})
                .success(function (data, status, headers, config) {
                    $scope.listar();
                }).error(function (data, status, headers, config) {    
                    alert('Error al eliminar la informaci\xf3n de CertificadoCancelacionMatricula, por favor intente m\xe1s tarde');
            });   
        }
    };
}]);
