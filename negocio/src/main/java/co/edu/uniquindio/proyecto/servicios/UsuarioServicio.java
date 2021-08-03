package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    void actualizarUsuario(String email, String password, Usuario u) throws Exception;

    void actualizar(String id,Usuario u) throws Exception;

    void eliminarUsuario(String email, String password) throws Exception;

    void eliminarUsuarioId(String id) throws Exception;

    Usuario obtenerUsuario(String id) throws Exception;

    Usuario obtenerUsuarioEmailPassword(String email, String password) throws Exception;

    List<Lugar> obtenerLugaresPorUsuario(String idUsuario);

    List<Comentario> obtenerComentariosSinRespuesta(String idUsuario);

    List<Comentario> obtenerComentariosConRespuesta(String idUsuario);

    List<Lugar> listarFavoritosUsuario(String id);

    List<Usuario> listarUsuarios();

}
