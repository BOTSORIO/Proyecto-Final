function obtenerDistanciaLugar(latitud,longitud){

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            let longitudOrigen = position.coords.longitude;
            let latitudOrigen = position.coords.latitude;
            mapboxgl.accessToken = 'pk.eyJ1IjoiZmptdXJjaWFoIiwiYSI6ImNrcDhvNzVoNjAwY2MydnBjaWZ5am0xeWkifQ.CbBV5gBoxdhFKRt6lU3xCA';

            var map = new mapboxgl.Map({
                container: 'mapa',
                style: 'mapbox://styles/mapbox/streets-v11',
                center: [position.coords.longitude,position.coords.latitude],
                zoom: 12
            });

            //Añade varios controles al mapa
            map.addControl(new mapboxgl.NavigationControl());

            //Se añade la ubicación origen (ubicación actual) y destino (el lugar)
            map.on('load', function() {
                var directions = new MapboxDirections({
                    accessToken: mapboxgl.accessToken,language: 'es',unit: 'metric'
                });

                directions.setOrigin([longitudOrigen,latitudOrigen]);
                directions.setDestination([longitud,latitud]);

                map.addControl(directions, 'top-left');

                directions.interactive(false);

                directions.on("route", function (e){
                    setDistancia(e.route[0].distance);
                })

            });

        })

    }

}

//Asigna la distancia en el xhtml para ser usados en el bean
function setDistancia(distancia){
    document.getElementById("crear-lugar:lat-lugar").value  = distancia
}