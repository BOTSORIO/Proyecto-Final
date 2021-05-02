package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface ModeradorServicio {

    Moderador registrarModerador(Moderador m) throws Exception;

    Moderador actualizarModerador(Moderador m) throws Exception;

    void eliminarModerador(String email) throws Exception;

    Moderador obtenerModerador(String id) throws Exception;

    Moderador obtenerModeradorEmail(String email) throws Exception;

    List<Moderador> listarModeradores();
}
