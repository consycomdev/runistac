'use strict';

/* Controllers */
var module = angular.module('adminPagosMT.controllers', []);

module.controller('PagosMTCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = {};
        $scope.datosFormulario = {};
        $scope.infoPersona = {};
        $scope.panelEditar = false;
        $scope.listar = function () {
            $http.get('./webresources/Postulacion/consulta/pagados', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarCausalesRechazo = function () {
            $http.get('./webresources/CausalesRechazo/rechazo', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCausalesRechazo = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data, 'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCausalesRechazo();

        $scope.listarCausalesDevolucion = function () {
            $http.get('./webresources/CausalesRechazo/devolucion', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaCausalesDevolucion = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data, 'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarCausalesDevolucion();

        $scope.obtenerInfoPersona = function () {
            $scope.infoPersona = {};
            var id = $scope.postulacion.persona.id;
            $http.get('./webresources/PersonaNatural/' + id, {})
                    .success(function (data, status, headers, config) {
                        $scope.infoPersona = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarPoliza = function (idPostulacion) {
            $scope.poliza = [];
            $http.get('./webresources/PolizaCaucion/postulacion/' + idPostulacion, {})
                    .success(function (data, status, headers, config) {
                        $scope.poliza = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarAseguradora = function () {
            $http.get('./webresources/Aseguradora', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaBanco = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de la aseguradora, por favor intente m\xe1s tarde');
            });
        };

        $scope.listarAseguradora();

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
            $http.post('./webresources/PagosMT', JSON.stringify($scope.datosFormulario), {}
            ).success(function (data, status, headers, config) {
                alert("Los datos han sido guardados con \u00E9xito");
                $scope.listarPagos($scope.datosFormulario.postulacion.id);
            }).error(function (data, status, headers, config) {
                alert('Error al guardar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.aprobarSolicitud = function () {
            modalFirma('\xbfEsta seguro de aprobar el t\u00EDtulo valor de esta postulaci\xf3n?', JSON.stringify($scope.datosFormulario.postulacion), function (datosFirmados) {
                $http.post('./webresources/Postulacion/confirmarPago/', datosFirmados, {})
                        .success(function (data, status, headers, config) {
                            alert('La aprobaci\xf3n del título valor fue realizado con \u00E9xito');
                            $scope.listar();
                            $scope.panelEditar = false;

                        }).error(function (data, status, headers, config) {
                    if (data) {
                        alert('Error al generar la aprobaci\xf3n del t\u00EDtulo valor: ' + data);
                    } else {
                        alert('Error al generar la aprobaci\xf3n del t\u00EDtulo valor, por favor intente m\xe1s tarde');
                    }
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
            $http.post('./webresources/Postulacion/devolver/', JSON.stringify($scope.postulacion), {})
                    .success(function (data, status, headers, config) {
                        alert('La devoluci\xf3n al ciudadano del t\u00EDtulo valor fue realizado con \u00E9xito');
                        $scope.listar();
                        $scope.panelEditar = false;
                        $("#modalRechazo").modal('hide');
                        $("#modalDevolucion").modal('hide');
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n del t\u00EDtulo valor, por favor intente m\xe1s tarde');
            });
        };

        $scope.rechazarSolicitud = function () {
            $scope.postulacion.observacionesRechazo=$scope.observacionesRechazo;
            $http.post('./webresources/Postulacion/rechazarCiudadano/', JSON.stringify($scope.postulacion), {})
                    .success(function (data, status, headers, config) {
                        alert('El rechazo del t\u00EDtulo valor fue realizado con \u00E9xito');
                        $scope.listar();
                        $scope.panelEditar = false;
                        $("#modalRechazo").modal('hide');
                        $("#modalDevolucion").modal('hide');
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n del t\u00EDtulo valor, por favor intente m\xe1s tarde');
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
    }]);
