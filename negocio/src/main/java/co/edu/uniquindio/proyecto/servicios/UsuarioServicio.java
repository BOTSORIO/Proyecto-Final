package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registroUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(String email) throws Exception;

    Usuario obtenerUsuario(String id) throws Exception;

    Usuario obtenerUsuarioEmail(String email) throws Exception;

    List<Usuario> listarUsuarios();

    //Usuario iniciarSecion(String email,String password) throws Exception;

}
