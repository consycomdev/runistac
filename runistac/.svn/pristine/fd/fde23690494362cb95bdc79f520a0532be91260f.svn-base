'use strict';

/* Controllers */
var module = angular.module('adminAprobacionPoliza.controllers', []);

module.controller('AprobacionPolizaCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = {};
        $scope.datosFormulario = {};
        $scope.infoPersona = {};
        $scope.panelEditar = false;
        $scope.listar = function () {
            $http.get('./webresources/Postulacion/consulta/solicitudSiniestro', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.obtenerInfoPersona = function () {
            $scope.infoPersona = {};
            var id = $scope.postulacion.persona.id;
            $http.get('./webresources/PersonaNatural/' + id, {})
                    .success(function (data, status, headers, config) {
                        $scope.infoPersona = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarPoliza = function (idPostulacion) {
            $scope.poliza = [];
            $http.get('./webresources/PolizaCaucion/postulacion/' + idPostulacion, {})
                    .success(function (data, status, headers, config) {
                        $scope.poliza = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        
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
            $http.get('./webresources/CausalesRechazo/devolucion', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCausalesDevolucion = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCausalesDevolucion();

        $scope.listarAseguradora = function () {
            $http.get('./webresources/Aseguradora', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaBanco = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n de la aseguradora, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.consultarVehiculo=function(vin){
            $scope.consultaVehiculo={};
            $http.get('./webresources/Automotor/vin/' + vin,
                    {}).success(function (data, status, headers, config) {
                $scope.consultaVehiculo.vehiculo = data;
                $('#dlgEspera').modal('hide');
            }).error(function (data, status, headers, config) {
                procesarError(data,'Error al consultar la informaci\xf3n del vehiculo');
            });
        };

        $scope.listarAseguradora();

        $scope.listar();
        //guardar
        $scope.nuevo = function () {
            $scope.panelEditar = true;
            $scope.datosFormulario = {};
        };

        $scope.aprobarSiniestroPoliza = function () {
            if(!$scope.consultaVehiculo.vehiculo.fthCarroceria){
                if($scope.fth){
                    $scope.datosFormulario.postulacion.fthCarroceria=$scope.fth;
                }else{
                    alert('Debe digitar la FTH de carrocer\u00EDa');
                    return;
                }
            }
            modalFirma('\xbfEsta seguro de aprobar la solicitud de siniestro del t\u00EDtulo valor?', JSON.stringify($scope.datosFormulario.postulacion), function (datosFirmados) {
                $('#esperaModal').modal();
                $http.post('./webresources/Postulacion/autorizarSolicitudSiniestro/', datosFirmados, {})
                        .success(function (data, status, headers, config) {
                            $('#esperaModal').modal('hide');
                            alert('La aprobaci\xf3n del siniestro del t\u00EDtulo valor fue realizada con \xe9xito');
                            $scope.listar();
                            $scope.panelEditar = false;
                        }).error(function (data, status, headers, config) {
                            $('#esperaModal').modal('hide');
                            procesarError(data,'Error al generar la aprobaci\xf3n del siniestro del t\u00EDtulo valor, por favor intente m\xe1s tarde');
                });
            });
        };

        $scope.mostrarDevolver = function () {
            $("#modalDevolucion").modal();
            $scope.observacionesRechazo="";
        };

        $scope.mostrarRechazar = function () {
            $("#modalRechazo").modal();
            $scope.observacionesRechazo="";
        };

        $scope.devolverSolicitud = function () {
            $scope.postulacion.observacionesRechazo=$scope.observacionesRechazo;
            $http.post('./webresources/Postulacion/devolverSolicitudSiniestro/', JSON.stringify($scope.postulacion), {})
                    .success(function (data, status, headers, config) {
                        alert('La devoluci\xf3n al ciudadano fue realizada con \xe9xito');
                        $scope.listar();
                        $scope.panelEditar = false;
                        $("#modalRechazo").modal('hide');
                        $("#modalDevolucion").modal('hide');
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al procesar la solicitud, por favor intente m\xe1s tarde');
            });
        };
        
        $scope.rechazarSolicitud = function () {
            $scope.postulacion.observacionesRechazo=$scope.observacionesRechazo;
            $http.post('./webresources/Postulacion/rechazarSolicitudSiniestro/' ,JSON.stringify($scope.postulacion), {})
                    .success(function (data, status, headers, config) {
                        alert('El rechazo del siniestro del t\u00EDtulo valor fue realizado con \xe9xito');
                        $scope.listar();
                        $scope.panelEditar = false;
                        $("#modalRechazo").modal('hide');
                        $("#modalDevolucion").modal('hide');
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al procesar la solicitud, por favor intente m\xe1s tarde');
            });
        };

        $scope.cancelar = function () {
            $scope.panelEditar = false;
            $scope.datosFormulario = {};
        };

        $scope.registrarPagos = function (data) {
            $scope.postulacion = data;
            $scope.obtenerInfoPersona();
            $scope.datosFormulario = {};
            $scope.listarPoliza(data.id);
            $scope.consultarVehiculo(data.vin);
            $scope.datosFormulario.postulacion = {id: data.id};
            $scope.panelEditar = true;

        };

        //editar
        $scope.editar = function (data) {
            $scope.panelEditar = true;
            $scope.datosFormulario = data;
        };

        $scope.descargar = function (idAnexo) {
            window.open("./webresources/Anexo/descargar/" + idAnexo + '.pdf', '_blank');
        };
        
        $scope.validarFTH=function(){
            //consummir servicio de validacion
            $http.get('./webresources/Automotor/validarFTHCarroceria/'+$scope.postulacion.id+'/'+$scope.fth, {})
                    .success(function (data, status, headers, config) {
                        alert('La carrocer\u00EDa asociada a la FTH digitada es '+data+'. Puede continuar con el proceso');
                        $scope.consultaVehiculo.vehiculo.carroceria=data;
                    }).error(function (data, status, headers, config) {
                procesarError(data,'Error al procesar la solicitud, por favor intente m\xe1s tarde');
            });
        };
    }]);
