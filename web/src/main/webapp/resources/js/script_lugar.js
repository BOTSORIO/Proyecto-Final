window.onload = function (){

    let enable=true;
    mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

    var map = new mapboxgl.Map({
        container: 'mapa',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-72.309, 4.473],
        zoom: 4.5
    });

    //Añade un control de geolocalizacion al mapa en la ventana de la creacion del lugar
    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy:true
        },
        trackUserLocation: true
    }));

    //Añade varios controles al mapa
    map.addControl(new mapboxgl.NavigationControl());

    //Nos ubica en la localizacion actual en el mapa
    map.on("load", function (e){
        if ("geolocation" in navigator){
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude,position.coords.latitude],
                    zoom: 14
                })
            })
        }
    })

    //Obtiene la latitud y longitud para mandarlo al xhtml
    map.on("click", function (e) {
        if (enable) {
            setLtnLng(e.lngLat.lat,e.lngLat.lng)
            enable=false;


            let marker = new mapboxgl.Marker({
                draggable: true
            }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);

            marker.on("dragend", function () {
                var lngLat = marker.getLngLat();
                setLtnLng(lngLat.lat,lngLat.lng)
            })
        }
    });

}

//Asigna la longitud y la latitud en el xhtml para ser usados en el bean
function setLtnLng(lat, lng){
    document.getElementById("crear-lugar:lat-lugar").value  = lat
    document.getElementById("crear-lugar:long-lugar").value = lng
}
