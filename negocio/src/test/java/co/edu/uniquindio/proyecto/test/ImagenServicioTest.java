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
public class ImagenServicioTest {

    @Autowired
    private ImagenServicio imagenServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Test
    @Sql("classpath:lugares.sql")
    public void registrarImagenTest(){

        try{

            Lugar lugarEncontrado = lugarServicio.obtenerLugar(1);

            Imagen imagenNueva = new Imagen("adsafsasdsa");
            imagenNueva.setLugar(lugarEncontrado);

            Imagen imagenRegistrada= imagenServicio.registrarImagen(imagenNueva);

            System.out.println(imagenRegistrada);

            Assertions.assertNotNull(imagenRegistrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:imagenes.sql")
    public void actualizarImagenTest(){

        try{

            Imagen imagenBuscada= imagenServicio.obtenerImagen(1);
            imagenBuscada.setUrl("dsffgdsfd");

            Imagen imagenActualizada = imagenServicio.actualizarImagen(imagenBuscada);

            System.out.println(imagenActualizada);

            Assertions.assertEquals("dsffgdsfd",imagenActualizada.getUrl());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:imagenes.sql")
    public void eliminarImagenTest(){

        try{

            Imagen imagenBuscada= imagenServicio.obtenerImagen(1);

            imagenServicio.eliminarImagen(imagenBuscada.getId());

            Imagen imagenBorrada = imagenServicio.obtenerImagen(1);

            Assertions.assertNull(imagenBorrada);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:imagenes.sql")
    public void listarImagenTest(){

        List<Imagen> imagenes= imagenServicio.listarImagenes();

        System.out.println(imagenes);
    }


}
