package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface TipoServicio {

    Tipo registrarTipo(Tipo t) throws Exception;

    void  actualizarTipo(Tipo t, int codigoTipo) throws Exception;

    void eliminarTipo(int id) throws  Exception;

    Tipo obtenerTipo(int id) throws Exception;

    List<Tipo> listarTipos();

}
