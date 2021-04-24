package co.edu.uniquindio.proyecto.dto;

public class NumeroTipoLugaresPopularDTO {

    private String nombreTipoLugar;
    private Long cantidadLugares;


    public NumeroTipoLugaresPopularDTO(String nombreTipoLugar, Long cantidadLugares) {
        this.nombreTipoLugar = nombreTipoLugar;
        this.cantidadLugares = cantidadLugares;
    }

    public String getNombreTipoLugar() {
        return nombreTipoLugar;
    }

    public void setNombreTipoLugar(String nombreTipoLugar) {
        this.nombreTipoLugar = nombreTipoLugar;
    }

    public Long getCantidadLugares() {
        return cantidadLugares;
    }

    public void setCantidadLugares(Long cantidadLugares) {
        this.cantidadLugares = cantidadLugares;
    }

    @Override
    public String toString() {
        return "NumeroTipoLugaresPopularDTO{" +
                "nombreTipoLugar='" + nombreTipoLugar + '\'' +
                ", cantidadLugares=" + cantidadLugares +
                '}';
    }
}
