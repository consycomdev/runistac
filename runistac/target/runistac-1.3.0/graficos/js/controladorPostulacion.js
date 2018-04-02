var filtro = [];
filtro['POSTULACION_ESTADO'] = [];
filtro['AAAA'] = [];
filtro['MM'] = [];
filtro['MODELO'] = [];
filtro['ESTADO_VEHICULO'] = [];
filtro['MARCA_NOMBRE'] = [];
filtro['CARROCERIA'] = [];
var tabla='V_POSTULACION';
function cargarInformacion() {
    $.ajax({
        type: "POST",
        url: "../webresources/consulta/"+tabla,
        headers: {
            "Content-Type": "application/json"
        },
        dataType: "json",
        success: function (data) {
            pieDatos('POSTULACION_ESTADO', data.POSTULACION_ESTADO);
            barrasDatos('AAAA', data.AAAA);
            barrasDatos('MM', data.MM);
            barrasDatos('MODELO', data.MODELO);
            pieDatos('ESTADO_VEHICULO', data.ESTADO_VEHICULO);
            barrasDatos('MARCA_NOMBRE', data.MARCA_NOMBRE);
            barrasDatos('CARROCERIA', data.CARROCERIA);

            var total = 0;
            Object.keys(data.POSTULACION_ESTADO).forEach(function (v) {
                total += data.POSTULACION_ESTADO[v].cantidad;
            });
            $('#cantidad').html(total);
        },
        data: JSON.stringify({
            "POSTULACION_ESTADO": filtro['POSTULACION_ESTADO'],
            "AAAA": filtro['AAAA'],
            "MM": filtro['MM'],
            "MODELO": filtro['MODELO'],
            "ESTADO_VEHICULO": filtro['ESTADO_VEHICULO'],
            "MARCA_NOMBRE": filtro['MARCA_NOMBRE'],
            "CARROCERIA": filtro['CARROCERIA']
        })
    });
}

cargarInformacion();

procesarOpciones('POSTULACION_ESTADO', tabla);
procesarOpciones('AAAA', tabla);
procesarOpciones('MM', tabla);
procesarOpciones('MODELO', tabla);
procesarOpciones('ESTADO_VEHICULO', tabla);
procesarOpciones('MARCA_NOMBRE', tabla);
procesarOpciones('CARROCERIA', tabla);