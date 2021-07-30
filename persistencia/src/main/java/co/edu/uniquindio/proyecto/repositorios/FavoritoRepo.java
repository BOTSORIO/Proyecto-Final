package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito,Integer> {

    //================================= REPOSITORIO DE FAVORITO =================================//

    @Query("select f from Favorito f where f.lugar.id= :idLugar and f.usuario.id= :cedula")
    Favorito obtenerFavorito(int idLugar,String cedula);

    @Query("select f from Favorito f where f.lugar.id= :idLugar")
    List<Favorito> obtenerListaFavoritos(int idLugar);
}
