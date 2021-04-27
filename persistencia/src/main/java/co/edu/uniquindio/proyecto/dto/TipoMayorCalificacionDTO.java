package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Tipo;

public class TipoMayorCalificacionDTO {

    private Tipo tipo;
    private Double cantidadLugaresAprobados;


    public TipoMayorCalificacionDTO(Tipo tipo, Double cantidadLugaresAprobados) {
        this.tipo = tipo;
        this.cantidadLugaresAprobados = cantidadLugaresAprobados;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Double getCantidadLugaresAprobados() {
        return cantidadLugaresAprobados;
    }

    public void setCantidadLugaresAprobados(Double cantidadLugaresAprobados) {
        this.cantidadLugaresAprobados = cantidadLugaresAprobados;
    }

    @Override
    public String toString() {
        return "TipoMayorCalificacionDTO{" +
                "tipo=" + tipo +
                ", cantidadLugaresAprobados=" + cantidadLugaresAprobados +
                '}';
    }
}
