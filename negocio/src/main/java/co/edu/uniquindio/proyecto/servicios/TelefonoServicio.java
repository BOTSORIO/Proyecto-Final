package co.edu.uniquindio.proyecto.servicios;

import java.util.List;

public interface TelefonoServicio {

    Telefono registrarTelefono (Telefono t) throws Exception;

    void actualizarTelefono (Telefono t, int codigoTelefono) throws Exception;

    void eliminarTelefono(int id) throws Exception;

    List<Telefono> obtenerTelefonosLugar(int idLugar) throws Exception;

    Telefono obtenerTelefono (int id) throws Exception;

    List<Telefono> listarTelefonos ();
}
