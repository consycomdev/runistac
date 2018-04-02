function initMap() {
    

    $('.map').each(function (i) {
        var map = new google.maps.Map($('.map')[i], {
            center: {lat: 5.53333330, lng: -73.36666670},
            zoom: 5
        });

        marker(5.53333330, -73.36666670, map, 'Boyacá','800', '40');
        marker(4.6482837,-74.2478957, map, 'Bogotá','500','30');
    })
    // Create a map object and specify the DOM element for display.




}

function marker(lat, lng, map, label, cantidad, porcentaje) {
    var contentString = '<div id="content">' +
            '<div id="siteNotice">' +
            '</div>' +
            '<h3 id="firstHeading" class="firstHeading">'+label+'</h3>' +
            '<div id="bodyContent">' +
            '<p align="center">'+cantidad+'<br>'+porcentaje+'%</p>' +
            '</div>' +
            '</div>';
    
    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });
    var marker = new google.maps.Marker({
        position: {lat: lat, lng: lng},
        map: map,
        title: 'Tunja'});

    marker.addListener('click', function () {
        infowindow.open(map, marker);
    });
}

