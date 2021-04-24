package co.edu.uniquindio.proyecto.dto;

public class NumeroLugaresPorCategoriaDTO {

    private String nombreTipoLugar;
    private Long cantidadLugares;

    public NumeroLugaresPorCategoriaDTO(String nombreTipoLugar, Long cantidadLugares) {
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
        return "NumeroLugaresPorCategoriaDTO{" +
                "nombreTipoLugar='" + nombreTipoLugar + '\'' +
                ", cantidadLugares=" + cantidadLugares +
                '}';
    }
}
