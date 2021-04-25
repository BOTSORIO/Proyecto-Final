package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    @Query("select l.nombre,l.descripcion,l.longitud,l.latitud from Lugar l join l.horarios h where h.diaSemana = :diaSemana and h.horaInicio= :horaInicio and h.horaFin= :horaFin")
    List<Object[]> obtenerLugaresHorario(String diaSemana,String horaInicio,String horaFin);

    @Query("select count(c) from Lugar l join l.comentarios c where l.id=:idLugar")
    int obtenerCantidadComentarios(Integer idLugar);


    //Probar en el test

    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO(l.tipo.nombre, count(l)) from Lugar l group by l.tipo")
    List<NumeroLugaresPorCategoriaDTO> obtenerCantidadLugaresPorCategoria();

    @Query("select l from Lugar l where l.horarios is empty ")
    List<Lugar> obtenerLugaresSinHorarios();

    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCiudadDTO(l.ciudad.nombre,count(l)) from Lugar l group by l.ciudad")
    List<NumeroLugaresPorCiudadDTO> obtenerCantidadLugaresPorCiudad();

    @Query("select l from Lugar l join l.horarios h where h.diaSemana= :diaSemana and(h.horaInicio<:horaActual and h.horaFin > :horaActual)")
    List<Lugar> obtenerLugaresAbiertos(String diaSemana,String horaActual);

    @Query("select l from Lugar l join l.horarios h where h.diaSemana= :diaSemana and :horaActual between h.horaInicio and h.horaFin")
    List<Lugar> obtenerLugaresAbiertos2(String diaSemana,Date horaActual);


    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroTipoLugaresPopularDTO(l.tipo.nombre, count(l)) from Lugar l where l.estado=true group by l.tipo order by count(l) desc ")
    List<NumeroTipoLugaresPopularDTO> obtenerTipoLugarPopular();


    @Query("select new co.edu.uniquindio.proyecto.dto.ModeradorLugaresAprobadosDTO(l.moderador, count(l)) from Lugar l where l.estado=true group by l.moderador order by count(l) desc")
    List<ModeradorLugaresAprobadosDTO> obtenerModeradorLugaresAprobados();


    @Query("select avg(c.calificacion) from Lugar l join l.comentarios c where l.id= :idLugar")
    float obtenerCalificacionPromedio(Integer idLugar);

    @Query("select new co.edu.uniquindio.proyecto.dto.LugarMayorCalificacionDTO(l, avg(c.calificacion)) from Lugar l join l.comentarios c where l.estado=true and l.ciudad.id =:idCiudad group by c.calificacion order by avg(c.calificacion) desc ")
    List<LugarMayorCalificacionDTO> obtenerLugarMayorCalificacion(Integer idCiudad);

}
