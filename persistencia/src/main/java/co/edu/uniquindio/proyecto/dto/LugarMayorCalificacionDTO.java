package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Lugar;

public class LugarMayorCalificacionDTO {

    private Lugar lugar;
    private Double calificacionPromedio;

    public LugarMayorCalificacionDTO(Lugar lugar, Double calificacionPromedio) {
        this.lugar = lugar;
        this.calificacionPromedio = calificacionPromedio;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    @Override
    public String toString() {
        return "LugarMayorCalificacionDTO{" +
                "lugar=" + lugar +
                ", calificacionPromedio=" + calificacionPromedio +
                '}';
    }
}
