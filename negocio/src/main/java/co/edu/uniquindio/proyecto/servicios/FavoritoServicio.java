package co.edu.uniquindio.proyecto.servicios;

import java.util.List;

public interface FavoritoServicio {

    Favorito registrarFavorito(Favorito f) throws Exception;

    void actualizarFavorito(Favorito f, int codigoFavorito) throws Exception;

    void eliminarFavorito(int id) throws Exception;

    Favorito obtenerFavorito(int id) throws  Exception;

    List<Favorito> obtenerListaFavoritosLugar(int idLugar);

    List<Favorito> listarFavoritos();

}
