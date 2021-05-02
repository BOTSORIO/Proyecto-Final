package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.*;
import java.util.*;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CiudadServicioTest {

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    public void registrarCiudadTest(){

        try {
            Ciudad ciudadNueva= new Ciudad("Calarcá");

            Ciudad ciudadRegistrada= ciudadServicio.registrarCiudad(ciudadNueva);

            System.out.println(ciudadRegistrada);

            Assertions.assertNotNull(ciudadRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:ciudades.sql")
    public void eliminarCiudadTest(){

        try {

            Ciudad ciudadEncontrada = ciudadServicio.obtenerCiudad(1);

            ciudadServicio.eliminarCiudad(ciudadEncontrada.getId());

            Ciudad ciudadBorrada = ciudadServicio.obtenerCiudad(1);

            Assertions.assertNull(ciudadBorrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:ciudades.sql")
    public void actualizarCiudadTest(){

        try {

            Ciudad ciudadEncontrada = ciudadServicio.obtenerCiudad(1);

            ciudadEncontrada.setNombre("Cali");
            ciudadServicio.actualizarCiudad(ciudadEncontrada);

            Ciudad ciudadBuscada = ciudadServicio.obtenerCiudad(1);

            System.out.println(ciudadBuscada);

            Assertions.assertEquals("Cali",ciudadBuscada.getNombre());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:ciudades.sql")
    public void listarCiudadesTest(){

        List<Ciudad> listaCiudades = ciudadServicio.listarCiudades();

        System.out.println(listaCiudades);
    }
}
