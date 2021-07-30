package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
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


    @Query("select new co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO(l,c) from Lugar l left join l.comentarios c")
    List<ComentariosLugarDTO> obtenerComentariosLugares();

    @Query("select l.nombre,l.descripcion,l.ciudad.nombre from Lugar l where l.moderador.email = :emailMod and l.estado= true")
    List<Object[]> obtenerLugaresModeradores(String emailMod);


    @Query("select l.nombre,l.descripcion,l.longitud,l.latitud from Lugar l join l.horarios h where h.diaSemana = :diaSemana and h.horaInicio= :horaInicio and h.horaFin= :horaFin")
    List<Object[]> obtenerLugaresHorario(String diaSemana,String horaInicio,String horaFin);

    @Query("select count(c) from Lugar l join l.comentarios c where l.id=:idLugar")
    int obtenerCantidadComentarios(Integer idLugar);


    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO(l.tipo.nombre, count(l)) from Lugar l group by l.tipo")
    List<NumeroLugaresPorCategoriaDTO> obtenerCantidadLugaresPorCategoria();

    @Query("select l from Lugar l where l.horarios is empty ")
    List<Lugar> obtenerLugaresSinHorarios();

    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCiudadDTO(l.ciudad.nombre,count(l)) from Lugar l group by l.ciudad")
    List<NumeroLugaresPorCiudadDTO> obtenerCantidadLugaresPorCiudad();


    @Query("select l from Lugar l join l.horarios h where h.diaSemana= :diaSemana and :horaActual between h.horaInicio and h.horaFin")
    List<Lugar> obtenerLugaresAbiertos2(String diaSemana,Date horaActual);


    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroTipoLugaresPopularDTO(l.tipo.nombre, count(l)) from Lugar l where l.estado=true group by l.tipo order by count(l) desc ")
    List<NumeroTipoLugaresPopularDTO> obtenerTipoLugarPopular();


    @Query("select new co.edu.uniquindio.proyecto.dto.ModeradorLugaresAprobadosDTO(l.moderador, count(l)) from Lugar l where l.estado=true group by l.moderador order by count(l) desc")
    List<ModeradorLugaresAprobadosDTO> obtenerModeradorLugaresAprobados();


    @Query("select avg(c.calificacion) from Lugar l join l.comentarios c where l.id= :idLugar")
    float obtenerCalificacionPromedio(Integer idLugar);

    @Query("select new co.edu.uniquindio.proyecto.dto.LugarMayorCalificacionDTO(l, avg(c.calificacion)) from Lugar l join l.comentarios c where l.estado=true and l.ciudad.id =:idCiudad group by l.id order by avg(c.calificacion) desc ")
    List<LugarMayorCalificacionDTO> obtenerLugarMayorCalificacion(Integer idCiudad);

    @Query("select l from Lugar l join l.telefonos t where t.telefonoLugar = :telLugar")
    Lugar obtenerLugarPorTelefono(String telLugar);


    @Query("select count (l) from Lugar l  join l.comentarios c where c.calificacion  < 6 and l.id= :id")
    long calcularCantComentarios(Integer id);


    @Query("select c from Lugar l join l.comentarios c where l.id= :idLugar ")
    List<Comentario> obtenerComentariosLugarEspecifico(int idLugar);

    @Query("select h from Lugar l join l.horarios h where l.id= :idLugar")
    List<Horario> obtenerHorariosLugarEspecifico(int idLugar);

    @Query("select c from Lugar l join l.comentarios c where l.usuario.id= :idUsuario and c.respuesta is null")
    List<Comentario> obtenerComentariosNoRespondidosPorUnaPersona(String idUsuario);

    @Query("select new co.edu.uniquindio.proyecto.dto.LugarUsuarioDTO(l.nombre, u) from Usuario u left join u.lugares l")
    List<LugarUsuarioDTO> obtenerLosLugaresConSuUsuario();

    //Lugares que estan aprobados
    @Query("select l from Lugar l where l.nombre like concat('%',:nombre, '%') ")
    List<Lugar> buscarLugares(String nombre);

    @Query("select l from Lugar l where l.nombre like concat('%', :cadena,'%') or l.tipo.nombre like concat('%', :cadena,'%')")
    List<Lugar> busquedaLugaresTipoNombre(String cadena);

    @Query("select l from Lugar  l where l.id = :idLugar")
    Lugar obtenerLugar(int idLugar);


    //Eliminar
    @Query("select t from Lugar l join l.telefonos t where l.id = :idLugar")
    List<Telefono> obtenerTelefonos(int idLugar);

    @Query("select i from Lugar l join l.imagenes i where l.id = :idLugar")
    List<Imagen> obtenerImagenes(int idLugar);

    @Query("select h from Lugar l join l.horarios h where l.id = :idLugar")
    List<Horario> obtenerHorarios(int idLugar);

    @Query("select c from Lugar l join l.comentarios c where l.id = :idLugar")
    List<Comentario>obtenerComentariosLugar(int idLugar);

    @Query("select avg(c.calificacion) from Comentario c where c.lugar.id = :idLugar")
    Integer obtenerCalificacion(Integer idLugar);

    @Query("select l from Lugar l where l.usuario.id= :idUsuario")
    List<Lugar> obtenerLugaresPorUsuario(String idUsuario);


    //Cargar
    @Query("select l from Lugar l where l.estado = false")
    List<Lugar> obtenerLugaresSinAprobacion();

    @Query("select l from Lugar l where l.moderador.email = :emailMod and l.estado=true")
    List<Lugar> obtenerLugaresAprobados(String emailMod);

    @Query("select l from Lugar l where l.estado=true")
    List<Lugar> obtenerTodosLugaresAprobados();
}
