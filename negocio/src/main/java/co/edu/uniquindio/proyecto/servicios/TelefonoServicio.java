package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Telefono;

import java.util.List;

public interface TelefonoServicio {

    Telefono registrarTelefono (Telefono t) throws Exception;

    Telefono actualizarTelefono (Telefono t) throws Exception;

    void eliminarTelefono(int id) throws Exception;

    Telefono obtenerTelefono (int id) throws Exception;

    List<Telefono> listarTelefonos ();
}
