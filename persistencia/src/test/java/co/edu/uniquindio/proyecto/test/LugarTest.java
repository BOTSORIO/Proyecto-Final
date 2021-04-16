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
                Horario horario= new Horario("4pm - 8pm");
                Imagen imagen = new Imagen("addafada.dadada");
                Telefono telefono = new Telefono("3116310037");
                Favorito favorito = new Favorito("xd");

                Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
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
            Horario horario= new Horario("4pm - 8pm");
            Imagen imagen = new Imagen("addafada.dadada");
            Telefono telefono = new Telefono("3116310037");
            Favorito favorito = new Favorito("xd");

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
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
            Horario horario= new Horario("4pm - 8pm");
            Imagen imagen = new Imagen("addafada.dadada");
            Telefono telefono = new Telefono("3116310037");
            Favorito favorito = new Favorito("xd");

            Lugar lugarNuevo = new Lugar("Pepitos","Lugar de baile",fechaCreacion,fechaAprobacion,13,12,"xyz");
            lugarNuevo.setCiudad(ciudad);
            lugarNuevo.setUsuario(usuario);
            lugarNuevo.setModerador(moderador);
            lugarNuevo.setTipo(tipo);

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

}
