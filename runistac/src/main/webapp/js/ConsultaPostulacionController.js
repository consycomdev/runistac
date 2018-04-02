'use strict';

/* Controllers */
var module = angular.module('adminConsultaPostulacion.controllers', []);

module.controller('ConsultaPostulacionCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
        //listar
        $scope.lista = {};
        $scope.datosFormulario = {};
        $scope.panelEditar = false;
        $scope.listar = function () {
            $http.get('./webresources/Postulacion', {})
                    .success(function (data, status, headers, config) {
                        $scope.lista = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n, por favor intente m\xe1s tarde');
            });
        };


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
            $http.post('./webresources/Postulacion', JSON.stringify($scope.datosFormulario), {}
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

        $scope.aprobar = function () {
            if (confirm('\xbfEsta seguro que desea aprobar esta solicitud?')) {
                $http.get('./webresources/Postulacion/aprobar', {})
                        .success(function (data, status, headers, config) {
                            $scope.listaAsignacionCCM = data;
                        }).error(function (data, status, headers, config) {
                    alert('Error al consultar la informaci\xf3n de asignacionCCM, por favor intente m\xe1s tarde');
                });
            }
        };

        $scope.rechazar = function () {
            $http.get('./webresources/Postulacion/Rechazar', {})
                    .success(function (data, status, headers, config) {
                        $scope.listaAsignacionCCM = data;
                    }).error(function (data, status, headers, config) {
                alert('Error al consultar la informaci\xf3n de asignacionCCM, por favor intente m\xe1s tarde');
            });
        };

        //editar
        $scope.editar = function (data) {
            $scope.panelEditar = true;
            $scope.datosFormulario = data;
        };
    }]);
