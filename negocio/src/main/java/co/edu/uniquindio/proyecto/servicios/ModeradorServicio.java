package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ModeradorServicio {

    Trabajador registrarModerador(Trabajador m) throws Exception;

    void actualizarModerador(Trabajador m, String email, String password) throws Exception;

    void eliminarModerador(String email,String password) throws Exception;

    Trabajador obtenerModerador(String id) throws Exception;

    List<Mascota> obtenerLugaresAprobados(String email);

    List<Mascota> obtenerTodosLugaresAprobados();

    List<Mascota> obtenerLugaresSinAprobacion();

    List<Trabajador> listarModeradores();

    Trabajador obtenerEmailPassword(String email, String password) throws Exception;
}
