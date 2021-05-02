package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Favorito;

import java.util.List;

public interface FavoritoServicio {

    Favorito registrarFavorito(Favorito f) throws Exception;

    Favorito actualizarFavorito(Favorito f) throws Exception;

    void eliminarFavorito(int id) throws Exception;

    Favorito obtenerFavorito(int id) throws  Exception;

    List<Favorito> listarFavoritos();

}
