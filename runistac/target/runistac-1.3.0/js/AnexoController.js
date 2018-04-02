'use strict';

/* Controllers */
var module = angular.module('adminAnexo.controllers', []);

module.controller('AnexoCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
    //listar
    $scope.lista = {};
    $scope.datosFormulario = {};
    $scope.panelEditar = false;
    $scope.listar=function(){
        $http.get('./webresources/Anexo', {})
            .success(function (data, status, headers, config) {
                $scope.lista = data;
            }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
        });    
    };

        $scope.listarTipoAnexo=function(){
            $http.get('./webresources/TipoAnexo', {})
                .success(function (data, status, headers, config) {
                    $scope.listaTipoAnexo = data;
                }).error(function (data, status, headers, config) {
                    procesarError(data,'Error al consultar la informaci\xf3n de tipoAnexo, por favor intente m\xe1s tarde');
            });    
        };
        $scope.listarTipoAnexo();
        

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
        $http.post('./webresources/Anexo', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con \u00E9xito");
                $scope.panelEditar = false;
                $scope.listar();
            }).error(function (data, status, headers, config) {
                procesarError(data,'Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
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
        if (confirm('¿Desea elminar este registro?')) {    
            $http.delete('./webresources/Anexo/'+data.id, {})
                .success(function (data, status, headers, config) {
                    $scope.listar();
                }).error(function (data, status, headers, config) {    
                    procesarError(data,'Error al eliminar la informaci\xf3n de Anexo, por favor intente m\xe1s tarde');
            });   
        }
    };
}]);
