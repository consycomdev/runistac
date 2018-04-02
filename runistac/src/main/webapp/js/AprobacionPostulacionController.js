'use strict';

/* Controllers */
var module = angular.module('adminAprobacionPostulacion.controllers', []);

module.controller('AprobacionPostulacionCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = {};
        $scope.datosFormulario = {};
        $scope.infoPersona={};
        $scope.panelEditar = false;
        $scope.listaTipoTransportador=[];
        $scope.listaVehiculos=[];
        $scope.listar = function () {
            $http.get('./webresources/Postulacion/consulta/registrados', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.listarTipoTransportador=function(){
            $http.get('./webresources/TipoTransportador', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaTipoTransportador = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarTipoTransportador();
        
        $scope.listarParametroMaxCCM=function(){
            $http.get('./webresources/TipoTransportador', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaNumeroCCM = {};
                        for(var i=0;i<data.valor;i++){
                            
                        }
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarParametroMaxCCM();
        
        
        $scope.listarCausalesRechazo=function(){
            $http.get('./webresources/CausalesRechazo/rechazo', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCausalesRechazo = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCausalesRechazo();
        
        $scope.listarCausalesDevolucion=function(){
            $http.get('./webresources/CausalesDevolucion', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCausalesDevolucion = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCausalesDevolucion();
        
        
        $scope.obtenerInfoPersona=function(){
            $scope.infoPersona={};
            var id=$scope.datosFormulario.persona.id;
            $http.get('./webresources/PersonaNatural/'+id, {})
                    .success(function (data, status, headers, config) {
                        $scope.infoPersona = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.obtenerVehiculos=function(){
            $scope.listaVehiculos=[];
            var id=$scope.datosFormulario.persona.id;
            $http.get('./webresources/Automotor/propietario/'+id, {})
                    .success(function (data, status, headers, config) {
                        $scope.listaVehiculos = data;
                        if(!$scope.listaVehiculos){
                            alert("No se encontraron vehículos asociados para el solicitante");
                        }
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.numeroVehiculos=0;
        $scope.calcularNumeroVehiculos=function(){
            if($scope.datosFormulario.numeroCCM!=null && $scope.datosFormulario.numeroCCM!=undefined && $scope.datosFormulario.tipoTransportador.numeroCCM){
                $scope.numeroVehiculos=parseInt(parseInt($scope.datosFormulario.numeroCCM)/parseInt($scope.datosFormulario.tipoTransportador.numeroCCM));
            }else{
                $scope.numeroVehiculos=0;
            }
        };

        $scope.listar();
        //guardar
        $scope.nuevo = function () {
            $scope.panelEditar = true;
            $scope.datosFormulario = {};
        };


        $scope.cancelar = function () {
            $scope.panelEditar = false;
            $scope.datosFormulario = {};
            $scope.listar();
        };

        $scope.aprobar = function () {
            //validar campos
            $scope.errores = {};
            var error = false;
            //validar tipo postulacion
            if(!$scope.datosFormulario.tipoTransportador){
                alert('Debe seleccionar el tipo de transportador');
                return ;
            }
            //validar numero de ccm 
            if(!$scope.datosFormulario.numeroCCMAsignados){
                alert('Debe digitar el numero de CCM asignados');
                return ;
            }
            modalFirma('\xbfEsta seguro que desea aprobar esta solicitud?',JSON.stringify($scope.datosFormulario), function(datosFirmados){
                $http.post('./webresources/Postulacion/aprobar/', datosFirmados, {})
                        .success(function (data, status, headers, config) {
                            alert('La postulaci\xf3n fue aprobada correctamente');
                            $('#modalAprobacion').modal('hide');
                            $scope.cancelar();
                        }).error(function (data, status, headers, config) {
                    alert('Error al consultar la informaci\xf3n de asignacionCCM, por favor intente m\xe1s tarde');
                });
            });
        };

        $scope.rechazar = function () {
            modalFirma('\xbfEsta seguro que desea rechazar esta solicitud?',JSON.stringify($scope.datosFormulario), function(datosFirmados){
                $http.post('./webresources/Postulacion/rechazar', datosFirmados, {})
                        .success(function (data, status, headers, config) {
                            alert('La postulaci\xf3n fue rechazada correctamente');
                            $scope.cancelar();
                            $('#modalRechazo').modal('hide');
                        }).error(function (data, status, headers, config) {
                    alert('Error al consultar la informaci\xf3n de asignacionCCM, por favor intente m\xe1s tarde');
                });
            });
        };
        
        $scope.abrirRechazar= function(){
            $('#modalRechazo').modal();
        };
        
        $scope.abrirAprobar= function(){
            $scope.datosFormulario.numeroCCMAsignados=$scope.datosFormulario.numeroCCM;
            $('#modalAprobacion').modal();
        };

        //editar
        $scope.editar = function (data) {
            $scope.panelEditar = true;
            $scope.datosFormulario = data;
            $scope.obtenerInfoPersona();
            $scope.obtenerVehiculos();
        };

        $scope.descargar = function (idAnexo) {
            window.open("./webresources/Anexo/descargar/" + idAnexo+'.pdf', '_blank');
        };
    }]);
