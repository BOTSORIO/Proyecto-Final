package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;

import java.util.List;

public interface ComentarioServicio {

    Comentario registrarComentario(Comentario c) throws  Exception;

    Comentario actualizarComentario(Comentario c) throws  Exception;

    void eliminarComentario(int id) throws  Exception;

    Comentario obtenerComentario(int id) throws  Exception;

    List<Comentario> listarComentarios();

}
