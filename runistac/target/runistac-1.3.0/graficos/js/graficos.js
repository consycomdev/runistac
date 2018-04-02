

function procesarOpciones(id, tabla) {
    $.ajax({
        type: "GET",
        url: "../webresources/consulta/"+tabla+"/" + id,
        headers: {
            "Content-Type": "application/json"
        },
        dataType: "json",
        success: function (data) {
            $('#select'+id).html('<select class="selectpicker" multiple id="seleccion'+id+'"> </select>');
            data.map(function (d) {
                $('#seleccion' + id).append('<option value="' + d + '">' + d + '</option>');
            });
            $('#seleccion' + id).val(data);
            $('#seleccion' + id).selectpicker('refresh');
            $('#seleccion' + id).on('changed.bs.select', function (e, x, y) {
                if (x == 0) {
                    if (y) {
                        $('#seleccion' + id).val(data);
                    } else {
                        $('#seleccion' + id).val([]);
                    }
                    $('#seleccion' + id).selectpicker('refresh');
                }
            });
            $('#seleccion' + id).on('hidden.bs.select', function (e, x, y) {
                filtro[id] = $('#seleccion' + id).val();
                cargarInformacion();
            });
        }
    });

}