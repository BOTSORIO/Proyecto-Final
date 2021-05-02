package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private TipoRepo tipoRepo;


    //================================= Metodo para registrar o crear un lugar =================================//
    @Test
    public void registrarLugarTest(){

        try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date fechaCreacion =sdf.parse("2021/01/04");
                Date fechaAprobacion=sdf.parse("2021/01/04");

                Moderador moderador = new Moderador("88167","Nelson","Nequi","Isi123","@outlok");
                Ciudad ciudad= new Ciudad("Medellin");
                Tipo tipo= new Tipo("Deporte");
                Usuario usuario= new Usuario("24584","Sandra","sandrita","21san","san@hotmail.com");
                Horario horario= new Horario("lunes a viernes","10am","10pm");
                Imagen imagen = new Imagen("addafada.dadada");
                Telefono telefono = new Telefono("3116310037");
                Favorito favorito = new Favorito("xd");

                Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,true);
                lugarNuevo.setCiudad(ciudad);
                lugarNuevo.setUsuario(usuario);
                lugarNuevo.setModerador(moderador);
                lugarNuevo.setTipo(tipo);


                lugarNuevo.getHorarios().add(horario);
                lugarNuevo.getImagenes().add(imagen);
                lugarNuevo.getTelefonos().add(telefono);
                lugarNuevo.getFavoritos().add(favorito);
                tipo.getLugares().add(lugarNuevo);
                horario.getLugares().add(lugarNuevo);
                ciudad.getLugares().add(lugarNuevo);
                usuario.getLugares().add(lugarNuevo);
                moderador.getLugares().add(lugarNuevo);

                Lugar lugarGuardado = lugarRepo.save(lugarNuevo);

                Assertions.assertNotNull(lugarGuardado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para eliminar un lugar =================================//
    @Test
    public void eliminarLugarTest() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion =sdf.parse("2021/01/04");
            Date fechaAprobacion=sdf.parse("2021/01/04");

            Moderador moderador = new Moderador("88167","Nelson","Nequi","Isi123","@outlok");
            Ciudad ciudad= new Ciudad("Medellin");
            Tipo tipo= new Tipo("Deporte");
            Usuario usuario= new Usuario("24584","Sandra","sandrita","21san","san@hotmail.com");
            Horario horario= new Horario("lunes a viernes","10am","10pm");
            Imagen imagen = new Imagen("addafada.dadada");
            Telefono telefono = new Telefono("3116310037");
            Favorito favorito = new Favorito("xd");

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,true);
            lugarNuevo.setCiudad(ciudad);
            lugarNuevo.setUsuario(usuario);
            lugarNuevo.setModerador(moderador);
            lugarNuevo.setTipo(tipo);

            lugarNuevo.getHorarios().add(horario);
            lugarNuevo.getImagenes().add(imagen);
            lugarNuevo.getTelefonos().add(telefono);
            lugarNuevo.getFavoritos().add(favorito);
            tipo.getLugares().add(lugarNuevo);
            horario.getLugares().add(lugarNuevo);
            ciudad.getLugares().add(lugarNuevo);
            usuario.getLugares().add(lugarNuevo);
            moderador.getLugares().add(lugarNuevo);


            lugarRepo.save(lugarNuevo);

            lugarRepo.delete(lugarNuevo);

            Lugar lugarBorrado = lugarRepo.findById(1).orElse(null);

            Assertions.assertNull(lugarBorrado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para actualizar o modificar un lugar =================================//
    @Test
    public void actualizarModeradorTest(){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion =sdf.parse("2021/01/04");
            Date fechaAprobacion=sdf.parse("2021/01/04");

            Moderador moderador = new Moderador("88167","Nelson","Nequi","Isi123","@outlok");
            Ciudad ciudad= new Ciudad("Medellin");
            Tipo tipo= new Tipo("Deporte");
            Usuario usuario= new Usuario("24584","Sandra","sandrita","21san","san@hotmail.com");
            Horario horario= new Horario("lunes a viernes","10am","10pm");
            Imagen imagen = new Imagen("addafada.dadada");
            Telefono telefono = new Telefono("3116310037");
            Favorito favorito = new Favorito("xd");
            Comentario comentario = new Comentario("hola",10,"uwu",fechaCreacion);

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,true);
            lugarNuevo.setCiudad(ciudad);
            lugarNuevo.setUsuario(usuario);
            lugarNuevo.setModerador(moderador);
            lugarNuevo.setTipo(tipo);
            lugarNuevo.getComentarios().add(comentario);

            lugarNuevo.getImagenes().add(imagen);
            lugarNuevo.getHorarios().add(horario);
            lugarNuevo.getTelefonos().add(telefono);
            lugarNuevo.getFavoritos().add(favorito);
            tipo.getLugares().add(lugarNuevo);
            horario.getLugares().add(lugarNuevo);
            usuario.getLugares().add(lugarNuevo);
            moderador.getLugares().add(lugarNuevo);

            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);

            lugarGuardado.setDescripcion("Bienvenidos");
            lugarRepo.save(lugarGuardado);

            Lugar lugarBuscado= lugarRepo.findById(1).orElse(null);

            System.out.println(lugarBuscado.toString());

            Assertions.assertEquals("Bienvenidos",lugarBuscado.getDescripcion());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para obtener los lugares =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugares(){

        List<Lugar> lista = lugarRepo.findAll();
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerTipoLugar(){

        String nombreTipo = lugarRepo.obtenerTiposLugares(2);
        System.out.println(nombreTipo);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLatitudLugar(){

        Float latitudLugar = lugarRepo.latitudLugares(1);
        System.out.println(latitudLugar);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerinfoLugar(){

        List<Object[]> infoLugar = lugarRepo.infoLugares(1);

        for(int i=0;i<infoLugar.get(0).length;i++){

            System.out.println(infoLugar.get(0)[i]);
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerinfoLugar2(){

        List<Object[]> infoLugar = lugarRepo.infoLugares2();

        for(Object[] arr: infoLugar){

            System.out.println(arr[0]+"\n"+arr[1]+"\n"+arr[2]);
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresFavoritos() {

        List<Lugar> lugares = usuarioRepo.obtenerLugaresFav("2");

        for (Lugar l : lugares) {

            System.out.println(l.toString());
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresFavoritos2() {

        List<Lugar> lugares = usuarioRepo.obtenerLugaresFav2("2");

        for (Lugar l : lugares) {

            System.out.println(l);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerUsuariosCiudadTest() {

        List<Usuario> usuarios = ciudadRepo.obtenerCiudadUsuario("Calarca");

        for (Usuario u : usuarios) {

            System.out.println(u);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerUsuariosCiudad2Test() {

        List<Object[]> usuarios = ciudadRepo.obtenerCiudadUsuarios();

        for (Object[] u : usuarios) {

            System.out.println(u[0]+"\n"+u[1] );
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerComentariosLugar(){
        List<ComentariosLugarDTO> comentarios = lugarRepo.obtenerComentariosLugares();

        for(ComentariosLugarDTO l: comentarios){
            //System.out.println(l.getLugar().getNombre()+","+l.getComentario().getComentario());
            System.out.println(comentarios);
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerhorariosTest() {

        List<Object[]> horarios = lugarRepo.obtenerLugaresHorario("Lunes","10pm","10am");

        for (Object[] u : horarios) {

            System.out.println(u[0]+"\n"+u[1] );
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerCantidadComentariosTest() {

        int cantidad = lugarRepo.obtenerCantidadComentarios(1);

        System.out.println("La cantidad de comentarios es: "+cantidad);
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerModeradoresLugarTest() {

        List<Object[]> moderadores = lugarRepo.obtenerLugaresModeradores("@hotmail.com");

        for (Object[] u : moderadores) {

            System.out.println(u[0]+"\n");
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresCategoria(){
        List<NumeroLugaresPorCategoriaDTO> cantidad=lugarRepo.obtenerCantidadLugaresPorCategoria();

        for(NumeroLugaresPorCategoriaDTO l: cantidad){
            System.out.println(l);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresSinHorario() {

        List<Lugar> lugares = lugarRepo.obtenerLugaresSinHorarios();

        for (Lugar l : lugares) {

            System.out.println(l);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresPorCiudadTest(){
        List<NumeroLugaresPorCiudadDTO> cantidad=lugarRepo.obtenerCantidadLugaresPorCiudad();

        for(NumeroLugaresPorCiudadDTO l: cantidad){
            System.out.println(l);
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresAbiertos() {

        DateTimeFormatter sdf= DateTimeFormatter.ofPattern("HH:mm");
        String horaActual = sdf.format(LocalTime.now());

        System.out.println(horaActual);

        long cantidad = tipoRepo.obtenerLugaresAbiertos("Lunes", horaActual);

        System.out.println("La cantidad de lugares abiertos es: "+cantidad);
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugarCalificaion() {

        List<TipoMayorCalificacionDTO> cantidad= tipoRepo.obtenerTipoMayorCalificacion(1);

        for(TipoMayorCalificacionDTO t: cantidad){
            System.out.println(t);
        }

    }



    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerTipoLugarPopularTest(){
        List<NumeroTipoLugaresPopularDTO> cantidad=lugarRepo.obtenerTipoLugarPopular();

        for(NumeroTipoLugaresPopularDTO l: cantidad){
            System.out.println(l);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerModeradorMasLugaresAprobadosTest(){

        List<ModeradorLugaresAprobadosDTO>moderadores=lugarRepo.obtenerModeradorLugaresAprobados();

        for(ModeradorLugaresAprobadosDTO m: moderadores){

            System.out.println(m);
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerCalificacionPromedioTest() {

        float calificacion = lugarRepo.obtenerCalificacionPromedio(3);

        System.out.println("La calificacion promedio es: "+calificacion);
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugarMayorCalificacionTest(){

        List<LugarMayorCalificacionDTO>lugares=lugarRepo.obtenerLugarMayorCalificacion(1);

        for(LugarMayorCalificacionDTO l:lugares){

            System.out.println(l);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugaresPublicadosTest(){

        List<Object[]> lista = usuarioRepo.obtenerLugaresPublicadosUsuarios();

        for(Object[] arr: lista){

            System.out.println(arr[0]+"\n"+arr[1]);
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarComentarariosLugarTest(){

        List<Comentario> lista = lugarRepo.obtenerComentariosLugarEspecifico2(1);

        System.out.println(lista);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarComentarariosNoRespondidosTest(){

        List<Comentario> lista = lugarRepo.obtenerComentariosNoRespondidosPorUnaPersona("2");

        System.out.println(lista);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugarTelefono(){

        Lugar lugar = lugarRepo.obtenerLugarPorTelefono("3045994932");
        System.out.println(lugar);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void calcularCantiComentarios(){

        long cantidad = lugarRepo.calcularCantComentarios(2);
        System.out.println("La cantidad de comentarios con calificacion entre 1 y 5 es:" + cantidad);
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void calcularCantLugaresNoAprovados(){

        long cantidad = ciudadRepo.obtenerLugaresNoAprobadosPorCiudad();
        System.out.println("La cantidad de lugares no aprobados es:" + cantidad);
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresCreadosPorUsuario() {

        List<Lugar> lugares = lugarRepo.obtenerLugaresPorUsuario("2");

        for (Lugar l : lugares) {

            System.out.println(l.getNombre());
        }
    }


    @Test
    @Sql("classpath:lugares.sql")
    public void obtenerLugaresCreadosTest() {

        List<LugarUsuarioDTO> lugares = lugarRepo.obtenerLosLugaresConSuUsuario();

        for (LugarUsuarioDTO l : lugares) {

            System.out.println(l);
        }
    }

}
