function crearMapa(lugares){

    //console.log(lugares);

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

function saludar(texto){

    console.log(texto);
}

function ubicarLugares(lugares, map){

    for(let l of lugares){

        new mapboxgl.Marker().setLngLat([l.lng,l.lat]).addTo(map);

    }
}
