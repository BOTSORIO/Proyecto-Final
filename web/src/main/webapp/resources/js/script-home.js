function crearMapa(lugares){

    mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-75.66, 4.52],
        zoom: 12
    });

    map.on("load",function (e){

        ubicarLugares(lugares,map);
    });
}

function ubicarLugares(lugares, map){

    let bounds = new mapboxgl.LngLatBounds();

    for(let l of lugares){

        if(l.aprobado){

            new mapboxgl.Marker().setLngLat([l.lng,l.lat]).setPopup(new mapboxgl.Popup().setHTML("<strong>"+l.nombre+"</strong><br>"+l.descripcion+"<br><a href='http://localhost:8080/detalleLugar.xhtml?lugar="+l.id+"'>Ir a detalle</a>")).addTo(map).togglePopup();

            bounds.extend([l.lng,l.lat]);
        }

    }

    map.fitBounds(bounds,{padding: 100});
    document.getElementById("map").style.visibility="visible";

}
