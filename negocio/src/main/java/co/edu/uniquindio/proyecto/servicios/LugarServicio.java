package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar l) throws Exception;

    Lugar actualizarLugar(Lugar l) throws Exception;

    void eliminarLugar(int id) throws Exception;

    Lugar obtenerLugar(int id) throws Exception;

    List<Lugar> listarLugares();

    List<Lugar> buscarLugares(String nombre);

    List<Comentario> listarComentarios(Integer idLugar);

    List<Horario> listarHorarios(Integer idLugar);

    void registrarComentario(Comentario c) throws Exception;


}
