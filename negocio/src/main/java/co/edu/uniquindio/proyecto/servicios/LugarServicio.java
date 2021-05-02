package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;

import java.util.List;

public interface LugarServicio {

    Lugar registrarLugar(Lugar l) throws Exception;

    Lugar actualizarLugar(Lugar l) throws Exception;

    void eliminarLugar(int id) throws Exception;

    Lugar obtenerLugar(int id) throws Exception;

    List<Lugar> listarLugares();

}
