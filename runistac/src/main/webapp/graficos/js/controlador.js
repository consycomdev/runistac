var filtro = [];
filtro['CCM_ESTADO'] = [];
filtro['AAAA'] = [];
filtro['MM'] = [];
var tabla='V_CCM';
function cargarInformacion() {
    $.ajax({
        type: "POST",
        url: "../webresources/consulta/"+tabla,
        headers: {
            "Content-Type": "application/json"
        },
        dataType: "json",
        success: function (data) {
            pieDatos('CCM_ESTADO', data.CCM_ESTADO);
            barrasDatos('AAAA', data.AAAA);
            barrasDatos('MM', data.MM);

            var total = 0;
            Object.keys(data.CCM_ESTADO).forEach(function (v) {
                total += data.CCM_ESTADO[v].cantidad;
            });
            $('#cantidad').html(total);
        },
        data: JSON.stringify({
            "CCM_ESTADO": filtro['CCM_ESTADO'],
            "AAAA": filtro['AAAA'],
            "MM": filtro['MM']
        })
    });
}

cargarInformacion();

procesarOpciones('CCM_ESTADO', tabla);
procesarOpciones('AAAA', tabla);
procesarOpciones('MM', tabla);