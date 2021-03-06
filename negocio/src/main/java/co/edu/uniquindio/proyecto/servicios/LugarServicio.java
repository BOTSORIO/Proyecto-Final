package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar l) throws Exception;

    void  actualizarLugar(Lugar l, int codigoLugar) throws Exception;

    void actualizarLugar(Lugar l) throws Exception;

    void eliminarLugar(int id) throws Exception;

    Lugar obtenerLugar(int id) throws Exception;

    Lugar obtenerLugar2(int id) throws Exception;

    List<Lugar> listarLugares();

    List<Lugar> buscarLugares(String nombre);

    List<Comentario> listarComentarios(Integer idLugar);

    List<Horario> listarHorarios(Integer idLugar);

    void registrarComentario(Comentario c) throws Exception;

    void ingresarComentario(Comentario c,Lugar lugar,Persona persona) throws Exception;

    void marcarFavorito(Lugar lugar, Persona p) throws Exception;

    void eliminarFavorito(Lugar lugar, Persona persona) throws Exception;

    int obtenerCalificacionPromedio(int idLugar) throws Exception;

    List<Lugar> obtenerLugaresPorTipo(String tipo);

    List<Lugar> obtenerLugaresPorCiudad(String nombreCiudad);

    Lugar obtenerLugarMejorCalificacion();

    List<Lugar> obtenerLugaresFavoritosUsuario(String idUsuario);

    List<Lugar> obtenerLugaresAbiertos();
}
