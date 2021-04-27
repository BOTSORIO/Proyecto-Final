package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;

public class LugarUsuarioDTO {

    private String lugar;
    private Usuario usuario;

    public LugarUsuarioDTO(String lugar, Usuario usuario) {
        this.lugar = lugar;
        this.usuario = usuario;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "LugarUsuarioDTO{" +
                "lugar=" + lugar +
                ", usuario=" + usuario +
                '}';
    }
}
