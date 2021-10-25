package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface LugarServicio {

    Mascota registrarLugar(Mascota l) throws Exception;

    void  actualizarLugar(Mascota l, int codigoLugar) throws Exception;

    void actualizarLugar(Mascota l) throws Exception;

    void eliminarLugar(int id) throws Exception;

    Mascota obtenerLugar(int id) throws Exception;

    Mascota obtenerLugar2(int id) throws Exception;

    List<Mascota> listarLugares();

    List<Mascota> buscarLugares(String nombre);

    List<Comentario> listarComentarios(Integer idLugar);

    List<Horario> listarHorarios(Integer idLugar);

    void registrarComentario(Comentario c) throws Exception;

    void ingresarComentario(Comentario c, Mascota lugar, Persona persona) throws Exception;

    void marcarFavorito(Mascota lugar, Persona p) throws Exception;

    void eliminarFavorito(Mascota lugar, Persona persona) throws Exception;

    int obtenerCalificacionPromedio(int idLugar) throws Exception;

    List<Mascota> obtenerLugaresPorTipo(String tipo);

    List<Mascota> obtenerLugaresPorCiudad(String nombreCiudad);

    Mascota obtenerLugarMejorCalificacion();

    List<Mascota> obtenerLugaresFavoritosUsuario(String idUsuario);

    List<Mascota> obtenerLugaresAbiertos();
}
