'use strict';

/* Controllers */
var module = angular.module('adminConsultaCCM.controllers', []);

module.controller('ConsultaCCMCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
    //listar
    $scope.lista = {};
    $scope.datosFormulario = {};
    $scope.consulta={};
    $scope.panelEditar = false;
    $scope.listar=function(){
        //obtener fechas
        $http.post('./webresources/CertificadoCancelacionMatricula/consulta',JSON.stringify($scope.consulta), {})
            .success(function (data, status, headers, config) {
                $scope.lista = data;
                if(!data || data.length==0){
                    alert('No se encontró ningún CCM asociado a los filtros seleccionados.')
                }
            }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
        });    
    };
    
    $scope.detalle=function(data){
        $http.get('./webresources/Postulacion/ccm/'+data.id, {})
            .success(function (data, status, headers, config) {
                $scope.detallePostulacion=data;
                $("#detalleModal").modal();
            }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
        });
    };
    
    $scope.limpiar=function(){
        $scope.lista=[];
        $scope.consulta={};
    };
}]);
