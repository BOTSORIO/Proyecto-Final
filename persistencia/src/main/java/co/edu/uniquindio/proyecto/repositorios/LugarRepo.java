package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepo extends JpaRepository<Lugar,Integer>{

    //================================= REPOSITORIO DE LUGAR =================================//

    @Query("select l.tipo.nombre from Lugar l where l.id = :idLugar ")
    String obtenerTiposLugares(Integer idLugar);

    @Query("select l.latitud from Lugar l where l.id = :idLugar ")
    Float latitudLugares(Integer idLugar);

    @Query("select l.nombre, l.latitud, l.longitud from Lugar l where l.id = :idLugar ")
    List<Object[]> infoLugares(Integer idLugar);

    @Query("select l.nombre, l.latitud, l.longitud from Lugar l ")
    List<Object[]> infoLugares2();

    @Query("select l, c from Lugar l left join l.comentarios c")
    List<Object[]> obtenerComentarios();

    @Query("select new co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO(l,c) from Lugar l left join l.comentarios c")
    List<ComentariosLugarDTO> obtenerComentariosLugares();

    @Query("select l.nombre,l.descripcion,l.ciudad.nombre from Lugar l where l.moderador.email = :emailMod and l.estado= true")
    List<Object[]> obtenerLugaresModeradores(String emailMod);

    /*
    @Query("select l.nombre,l.descripcion,l.longitud,l.latitud from Lugar l join l.horarios h where h.diaSemana = diaSemana and h.horaInicio=horaInicio and h.horaFin=horaFin")
    List<Object[]> obtenerLugaresHorario(String diaSemana,String horaInicio,String horaFin);
    Modificar Horario del proyecto
    */



}
