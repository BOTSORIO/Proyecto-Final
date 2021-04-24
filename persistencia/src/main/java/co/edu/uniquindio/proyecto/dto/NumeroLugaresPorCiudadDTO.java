package co.edu.uniquindio.proyecto.dto;

public class NumeroLugaresPorCiudadDTO {

    private String nombreCiudadLugar;
    private Long cantidadLugares;

    public NumeroLugaresPorCiudadDTO(String nombreCiudadLugar, Long cantidadLugares) {
        this.nombreCiudadLugar = nombreCiudadLugar;
        this.cantidadLugares = cantidadLugares;
    }

    public String getNombreCiudadLugar() {
        return nombreCiudadLugar;
    }

    public void setNombreCiudadLugar(String nombreCiudadLugar) {
        this.nombreCiudadLugar = nombreCiudadLugar;
    }

    public Long getCantidadLugares() {
        return cantidadLugares;
    }

    public void setCantidadLugares(Long cantidadLugares) {
        this.cantidadLugares = cantidadLugares;
    }

    @Override
    public String toString() {
        return "NumeroLugaresPorCiudadDTO{" +
                "nombreCiudadLugar='" + nombreCiudadLugar + '\'' +
                ", cantidadLugares=" + cantidadLugares +
                '}';
    }
}
