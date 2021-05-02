package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;
    Usuario actualizarUsuario(Usuario u) throws Exception;
    Usuario obtenerUsuario(String id) throws Exception;
    void eliminarUsuario(Usuario u) throws Exception;
    List<Usuario> listarUsuarios();

}
