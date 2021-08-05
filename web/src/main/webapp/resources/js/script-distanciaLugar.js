function obtenerDistanciaLugar(latitud,longitud){

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {

            let longitudOrigen = position.coords.longitude;
            let latitudOrigen = position.coords.latitude;

            let markerInicial =  new mapboxgl.Marker().setLngLat([longitudOrigen,latitudOrigen]);
            let markerFinal = new mapboxgl.Marker().setLngLat([longitud,latitud]);
            let posInicial = markerInicial.getLngLat();
            let posFinal = markerFinal.getLngLat();
            let distancia = (posInicial.distanceTo(posFinal)).toFixed(0);

            console.log(distancia)

            setDistancia(distancia);

        })

    }

}

//Asigna la distancia en el xhtml para ser usados en el bean
function setDistancia(distancia){
    document.getElementById("busqueda:distancia").value  = distancia
}