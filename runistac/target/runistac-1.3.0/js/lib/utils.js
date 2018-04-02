$(document).ready(function () {
    calcularIframe();
});
$(window).resize(function () {
    calcularIframe();
});

function calcularIframe() {
    var width = $(window).width() - 30;
    var height = $(window).height() - $('.content-header').height() - $('.content-footer').height() - 75;
    $('#iframeContenido').width(width);
    $('#iframeContenido').height(height);
}

function accionMenu(url) {
    //$('#iframeContenido').attr('src', url);
    $.get(url,{},function(data){
        $('#contenido').html(data);
    });
}


function procesarError(data, mensaje){
    if(data && data.mensaje){
        alert(data.mensaje);
    }else{
        try{
            var texto=JSON.parse(data.replace(/\\"/g, '"').replace(/"{/g,'{').replace(/}"/g,'}'));
            alert(texto.mensaje);
        }catch(ex){
            alert(mensaje);
        }
    }
}

function alert(mensaje){
    $('#msgConsulta').html(mensaje);
    $('#dlgConsulta').modal();
}