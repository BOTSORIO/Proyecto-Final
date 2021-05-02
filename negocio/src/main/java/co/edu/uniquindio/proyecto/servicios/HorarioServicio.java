package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Horario;

import java.util.List;

public interface HorarioServicio {

    Horario registrarHorario(Horario h) throws Exception;

    Horario actualizarHorario(Horario h) throws Exception;

    void eliminarHorario(int id) throws Exception;

    Horario obtenerHorario(int id) throws Exception;

    List<Horario> listarHorarios();
}
