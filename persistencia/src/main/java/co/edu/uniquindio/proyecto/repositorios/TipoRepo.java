package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.TipoMayorCalificacionDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TipoRepo extends JpaRepository<Tipo,Integer> {

    //================================= REPOSITORIO DE TIPO =================================//
    @Query("select count (l) from Tipo t join t.lugares l join l.horarios h where h.diaSemana= :diaSemana and(h.horaInicio<:horaActual and h.horaFin > :horaActual)")
    long obtenerLugaresAbiertos(String diaSemana, String horaActual);

    @Query("select new co.edu.uniquindio.proyecto.dto.TipoMayorCalificacionDTO(t,avg(c.calificacion)) from Tipo t join t.lugares l join l.comentarios c where l.estado=true and l.ciudad.id =:idCiudad group by l.tipo.id order by avg(c.calificacion) desc")
    List<TipoMayorCalificacionDTO> obtenerTipoMayorCalificacion(Integer idCiudad);

}
