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
public class TelefonoTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private TelefonoRepo telefonoRepo;

    //================================= Metodo para registrar o crear un telefono =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void registrarTelefonoTest(){

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423");
        telefonoNuevo.setLugar(lugarBuscado);

        Telefono telefonoGuardado = telefonoRepo.save(telefonoNuevo);

        System.out.println(telefonoGuardado.toString());

        Assertions.assertNotNull(telefonoGuardado);
    }

    //================================= Metodo para eliminar un telefono =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void eliminarTelefonoTest() {

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423");
        telefonoNuevo.setLugar(lugarBuscado);

        telefonoRepo.save(telefonoNuevo);

        telefonoRepo.delete(telefonoNuevo);

        Telefono telefonoBorrado = telefonoRepo.findById(1).orElse(null);

        Assertions.assertNull(telefonoBorrado);
    }

    //================================= Metodo para actualizar o modificar un telefono =================================//
    @Test
    @Sql("classpath:lugares.sql")
    public void actualizarTelefonoTest(){

        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Telefono  telefonoNuevo = new Telefono("3222842423");
        telefonoNuevo.setLugar(lugarBuscado);

        Telefono telefonoGuardado = telefonoRepo.save(telefonoNuevo);

        telefonoGuardado.setTelefonoLugar("322123");
        telefonoRepo.save(telefonoGuardado);

        Telefono telefonoBuscado = telefonoRepo.findById(1).orElse(null);

        Assertions.assertEquals("322123",telefonoBuscado.getTelefonoLugar());
    }

    //================================= Metodo para obtener los telefonos =================================//
    @Test
    @Sql("classpath:telefonos.sql")
    public void listarTelefonos(){

        List<Telefono> lista = telefonoRepo.findAll();
        System.out.println(lista);
    }
}
