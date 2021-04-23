package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario,Integer> {

    //================================= REPOSITORIO DE COMENTARIO =================================//

    @Query("select c from Comentario c where c.calificacion > ?1")
    List<Comentario> obtenerListaPorCalificacion(int calificacion);


    @Query("select c.usuario from Comentario c where c.lugar.id = :idLugar")
    List<Usuario> usuariosComentarios(Integer id);

}
