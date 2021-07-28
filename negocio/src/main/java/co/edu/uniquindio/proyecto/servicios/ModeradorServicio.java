package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ModeradorServicio {

    Moderador registrarModerador(Moderador m) throws Exception;

    void actualizarModerador(Moderador m, String email,String password) throws Exception;

    void eliminarModerador(String email,String password) throws Exception;

    Moderador obtenerModerador(String id) throws Exception;

    List<Lugar> obtenerLugaresAprobados(String email);

    List<Lugar> obtenerLugaresSinAprobacion();

    List<Moderador> listarModeradores();

    Moderador obtenerEmailPassword(String email, String password) throws Exception;
}
