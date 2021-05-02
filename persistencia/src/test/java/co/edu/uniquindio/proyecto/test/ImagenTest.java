package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImagenTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private ImagenRepo imagenRepo;

    //================================= Metodo para registrar o crear una imagen =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void registrarImagenTest(){

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Imagen imagenNueva = new Imagen("qeqaddaac");
        imagenNueva.setLugar(lugarBuscado);

        Imagen imagenGuardada =imagenRepo.save(imagenNueva);

        System.out.println(imagenGuardada.toString());

        Assertions.assertNotNull(imagenGuardada);
    }

    //================================= Metodo para eliminar una ciudad =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void eliminarImagenTest() {

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Imagen imagenNueva = new Imagen("qeqaddaac");
        imagenNueva.setLugar(lugarBuscado);
        imagenRepo.save(imagenNueva);

        imagenRepo.delete(imagenNueva);

        Imagen imagenBorrada = imagenRepo.findById(1).orElse(null);

        Assertions.assertNull(imagenBorrada);
    }

    //================================= Metodo para actualizar o modificar una imagen =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void actualizarImagenTest(){

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Imagen imagenNueva = new Imagen("qeqaddaac");
        imagenNueva.setLugar(lugarBuscado);

        Imagen imagenGuardada =imagenRepo.save(imagenNueva);

        imagenGuardada.setUrl("aeqwdasd");
        imagenRepo.save(imagenGuardada);

        Imagen imagenBuscada= imagenRepo.findById(1).orElse(null);

        System.out.println(imagenBuscada.toString());

        Assertions.assertEquals("aeqwdasd",imagenBuscada.getUrl());
    }

    //================================= Metodo para obtener las imagenes =================================//
    @Test
    @Sql("classpath:imagenes.sql")
    public void listarImagenesTest(){
        List<Imagen> lista = imagenRepo.findAll();
        System.out.println(lista);
    }
}
