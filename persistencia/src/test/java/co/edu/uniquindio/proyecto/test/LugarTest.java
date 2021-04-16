package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LugarTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private ModeradorRepo moderadorRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private TipoRepo tipoRepo;

    @Autowired
    private HorarioRepo horarioRepo;

    @Autowired
    private ImagenRepo imagenRepo;

    @Autowired
    private TelefonoRepo telefonoRepo;

    @Autowired
    private FavoritoRepo favoritoRepo;

    //================================= Metodo para registrar o crear un lugar =================================//
    @Test
    @Sql("classpath:moderadores.sql")
    @Sql("classpath:tipos.sql")
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:horarios.sql")
    @Sql("classpath:telefonos.sql")
    @Sql("classpath:favoritos.sql")
    public void registrarLugarTest(){

        try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date fechaCreacion =sdf.parse("2021/01/04");
                Date fechaAprobacion=sdf.parse("2021/01/04");

                Moderador moderadorBuscado = moderadorRepo.findById("123").orElse(null);
                Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
                Tipo tipoBuscado = tipoRepo.findById(1).orElse(null);
                Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
                Horario horarioBuscado = horarioRepo.findById(1).orElse(null);
                Imagen imagenBuscada = imagenRepo.findById(1).orElse(null);
                Telefono telefonoBuscado = telefonoRepo.findById(1).orElse(null);
                Favorito favoritoBuscado = favoritoRepo.findById(1).orElse(null);

                Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
                lugarNuevo.setCiudad(ciudadBuscada);
                lugarNuevo.setUsuario(usuarioBuscado);
                lugarNuevo.setModerador(moderadorBuscado);
                lugarNuevo.setTipo(tipoBuscado);


                lugarNuevo.getHorarios().add(horarioBuscado);
                lugarNuevo.getImagenes().add(imagenBuscada);
                lugarNuevo.getTelefonos().add(telefonoBuscado);
                lugarNuevo.getFavoritos().add(favoritoBuscado);
                tipoBuscado.getLugares().add(lugarNuevo);
                horarioBuscado.getLugares().add(lugarNuevo);
                ciudadBuscada.getLugares().add(lugarNuevo);
                usuarioBuscado.getLugares().add(lugarNuevo);
                moderadorBuscado.getLugares().add(lugarNuevo);

                Lugar lugarGuardado = lugarRepo.save(lugarNuevo);

                System.out.println(lugarGuardado.toString());

                Assertions.assertNotNull(lugarGuardado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para eliminar un lugar =================================//
    @Test
    @Sql("classpath:moderadores.sql")
    @Sql("classpath:tipos.sql")
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:horarios.sql")
    @Sql("classpath:imagenes.sql")
    @Sql("classpath:telefonos.sql")
    @Sql("classpath:favoritos.sql")
    public void eliminarLugarTest() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion =sdf.parse("2021/01/04");
            Date fechaAprobacion=sdf.parse("2021/01/04");
            Moderador moderadorBuscado = moderadorRepo.findById("123").orElse(null);
            Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
            Tipo tipoBuscado = tipoRepo.findById(1).orElse(null);
            Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
            Horario horarioBuscado = horarioRepo.findById(1).orElse(null);
            Imagen imagenBuscada = imagenRepo.findById(1).orElse(null);
            Telefono telefonoBuscado = telefonoRepo.findById(1).orElse(null);
            Favorito favoritoBuscado = favoritoRepo.findById(1).orElse(null);

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
            lugarNuevo.setCiudad(ciudadBuscada);
            lugarNuevo.setUsuario(usuarioBuscado);
            lugarNuevo.setModerador(moderadorBuscado);
            lugarNuevo.setTipo(tipoBuscado);

            lugarNuevo.getHorarios().add(horarioBuscado);
            lugarNuevo.getImagenes().add(imagenBuscada);
            lugarNuevo.getTelefonos().add(telefonoBuscado);
            lugarNuevo.getFavoritos().add(favoritoBuscado);
            tipoBuscado.getLugares().add(lugarNuevo);
            horarioBuscado.getLugares().add(lugarNuevo);
            ciudadBuscada.getLugares().add(lugarNuevo);
            usuarioBuscado.getLugares().add(lugarNuevo);
            moderadorBuscado.getLugares().add(lugarNuevo);


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
    @Sql("classpath:moderadores.sql")
    @Sql("classpath:tipos.sql")
    @Sql("classpath:ciudades.sql")
    @Sql("classpath:usuarios.sql")
    @Sql("classpath:horarios.sql")
    @Sql("classpath:imagenes.sql")
    @Sql("classpath:telefonos.sql")
    @Sql("classpath:favoritos.sql")
    public void actualizarModeradorTest(){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion =sdf.parse("2021/01/04");
            Date fechaAprobacion=sdf.parse("2021/01/04");
            Moderador moderadorBuscado = moderadorRepo.findById("123").orElse(null);
            Ciudad ciudadBuscada = ciudadRepo.findById(1).orElse(null);
            Tipo tipoBuscado = tipoRepo.findById(1).orElse(null);
            Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
            Horario horarioBuscado = horarioRepo.findById(1).orElse(null);
            Imagen imagenBuscada = imagenRepo.findById(1).orElse(null);
            Telefono telefonoBuscado = telefonoRepo.findById(1).orElse(null);
            Favorito favoritoBuscado = favoritoRepo.findById(1).orElse(null);

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
            lugarNuevo.setCiudad(ciudadBuscada);
            lugarNuevo.setUsuario(usuarioBuscado);
            lugarNuevo.setModerador(moderadorBuscado);
            lugarNuevo.setTipo(tipoBuscado);

            lugarNuevo.getImagenes().add(imagenBuscada);
            lugarNuevo.getHorarios().add(horarioBuscado);
            lugarNuevo.getTelefonos().add(telefonoBuscado);
            lugarNuevo.getFavoritos().add(favoritoBuscado);
            tipoBuscado.getLugares().add(lugarNuevo);
            horarioBuscado.getLugares().add(lugarNuevo);
            ciudadBuscada.getLugares().add(lugarNuevo);
            usuarioBuscado.getLugares().add(lugarNuevo);
            moderadorBuscado.getLugares().add(lugarNuevo);

            Lugar lugarGuardado = lugarRepo.save(lugarNuevo);

            lugarGuardado.setDescripcion("Bienvenidos");
            lugarRepo.save(lugarGuardado);

            Lugar lugarBuscado= lugarRepo.findById(1).orElse(null);

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

}
