window.onload = function (){
    mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-75.66, 4.52],
        zoom: 12
    });
}

function obtenerDistanciaLugar(lugares,distanciaBusqueda){

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            let lugaresCercanos = []

            for(let l of lugares){

                let longitudOrigen = position.coords.longitude;
                let latitudOrigen = position.coords.latitude;

                let markerInicial =  new mapboxgl.Marker().setLngLat([longitudOrigen,latitudOrigen]);
                let markerFinal = new mapboxgl.Marker().setLngLat([l.lng,l.lat]);
                let posInicial = markerInicial.getLngLat();
                let posFinal = markerFinal.getLngLat();
                let distancia = (posInicial.distanceTo(posFinal)).toFixed(0);

                let distanciaKM = distancia/1000;

                if(distanciaKM<=distanciaBusqueda && distanciaBusqueda<=10){

                    lugaresCercanos.push(l)
                }

            }

            crearMapa(lugaresCercanos);

        })

    }

}


function crearMapa(lugares){

    mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-75.66, 4.52],
        zoom: 12
    });

    //Añade varios controles al mapa
    map.addControl(new mapboxgl.NavigationControl());

    let lugaresAprobados = [];

    for(let l of lugares){
        if(l.aprobado){

            lugaresAprobados.push(l);
        }
    }

    if(lugaresAprobados.length>0){

        map.on("load",function (e){

            ubicarLugares(lugaresAprobados,map);
        });
    }
    else{
        document.getElementById("map").style.visibility="visible";
    }

}

function ubicarLugares(lugares, map){

    let bounds = new mapboxgl.LngLatBounds();

    for(let l of lugares){

        new mapboxgl.Marker().setLngLat([l.lng,l.lat]).setPopup(new mapboxgl.Popup().setHTML("<strong>"+l.nombre+"</strong><br>"+l.descripcion+"<br><a href='http://localhost:8080/detalleLugar.xhtml?lugar="+l.id+"'>Ir a detalle</a>")).addTo(map).togglePopup();

        bounds.extend([l.lng,l.lat]);
    }

    map.fitBounds(bounds,{padding: 100});
    document.getElementById("map").style.visibility="visible";

}