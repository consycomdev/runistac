'use strict';

/* Controllers */
var module = angular.module('adminPostulacion.controllers', []);

module.controller('PostulacionCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = {};
        $scope.datosFormulario = {};
        $scope.panelEditar = false;
        $scope.listar = function () {
            $('#esperaModal').modal();
            $http.post('./webresources/Postulacion/filtro', JSON.stringify($scope.datosFormulario), {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                        $('#esperaModal').modal('hide');
                        if (!data || data.length==0) {
                            alert('No se encontró ninguna solicitud con los datos seleccionados.');
                        }
                    }).error(function (data, status, headers, config) {
                $('#esperaModal').modal('hide');
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.listar();

        $scope.listarTipoDocumento = function () {
            $http.get('./webresources/TipoDocumento', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaTipoDocumento = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de tipoDocumento, por favor intente m\xe1s tarde');
            });
        };
        $scope.listarTipoDocumento();

        $scope.cancelar = function () {
            $scope.datosFormulario = {};
            $scope.listar();
        };


        $scope.detalle = function (data) {
            $scope.postulacion = data;
            $http.get('./webresources/CertificadoCancelacionMatricula/asignacion/' + data.asignacionCCM.id, {})
                    .success(function (data, status, headers, config) {
                        $("#detalleModal").modal();
                        $scope.listaCCM = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
            $http.get('./webresources/RegistroEstados/postulacion/' + data.id, {})
                    .success(function (data, status, headers, config) {
                        $("#detalleEstadoModal").modal();
                        $scope.listaEstados = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
            $scope.consultarVehiculo(data.vin);
            $scope.listarPoliza(data.id);
        };

        $scope.consultarVehiculo = function (vin) {
            $scope.consultaVehiculo = {};
            $http.get('./webresources/Automotor/vin/' + vin,
                    {}).success(function (data, status, headers, config) {
                $scope.consultaVehiculo.vehiculo = data;
                $scope.consultaVehiculo.vin = vin;
                $('#dlgEspera').modal('hide');
            }).error(function (data, status, headers, config) {
                procesarError(data, 'Error al consultar la informaci\xf3n del vehiculo');
            });
        };

        $scope.listarPoliza = function (idPostulacion) {
            $scope.poliza = [];
            $http.get('./webresources/PolizaCaucion/postulacion/' + idPostulacion, {})
                    .success(function (data, status, headers, config) {
                        $scope.poliza = data;
                    }).error(function (data, status, headers, config) {
                procesarError(data, 'Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

        $scope.descargar = function (idAnexo) {
            window.open("./webresources/Anexo/descargar/" + idAnexo + '.pdf');
        };

        $scope.descargarExcel = function () {
            $http.post('./webresources/Postulacion/filtroExcel', JSON.stringify($scope.datosFormulario), {})
                    .success(function (data, status, headers, config) {
                        window.open("./webresources/Postulacion/descargar/postulacion.xlsx", '_blank');
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };

    }]);
