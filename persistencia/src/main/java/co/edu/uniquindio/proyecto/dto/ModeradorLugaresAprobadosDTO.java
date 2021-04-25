package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Moderador;

public class ModeradorLugaresAprobadosDTO {

    private Moderador moderador;
    private Long cantidadLugaresAprobados;

    public ModeradorLugaresAprobadosDTO(Moderador moderador, Long cantidadLugaresAprobados) {
        this.moderador = moderador;
        this.cantidadLugaresAprobados = cantidadLugaresAprobados;
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    public Long getCantidadLugaresAprobados() {
        return cantidadLugaresAprobados;
    }

    public void setCantidadLugaresAprobados(Long cantidadLugaresAprobados) {
        this.cantidadLugaresAprobados = cantidadLugaresAprobados;
    }

    @Override
    public String toString() {
        return "moderadorLugaresAprovadosDTO{" +
                "moderador=" + moderador +
                ", cantidadLugaresAprovados=" + cantidadLugaresAprobados +
                '}';
    }
}
