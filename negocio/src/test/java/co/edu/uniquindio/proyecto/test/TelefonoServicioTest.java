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
public class TelefonoServicioTest {

    @Autowired
    private TelefonoServicio telefonoServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Test
    @Sql("classpath:lugares.sql")
    public void registrarTelefonoTest(){

        try {

            Lugar lugarEncontrado= lugarServicio.obtenerLugar(1);

            Telefono telefonoNuevo = new Telefono("322532131");
            telefonoNuevo.setLugar(lugarEncontrado);

            Telefono telefonoRegistrado = telefonoServicio.registrarTelefono(telefonoNuevo);

            Assertions.assertNotNull(telefonoRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:telefonos.sql")
    public void actualizarTelefonoTest(){

        try {

            Telefono telefonoEncontrado = telefonoServicio.obtenerTelefono(1);
            telefonoEncontrado.setTelefonoLugar("31843242");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:telefonos.sql")
    public void eliminarTelefonoTest(){

        try {

            Telefono telefonoEncontrado = telefonoServicio.obtenerTelefono(1);

            telefonoServicio.eliminarTelefono(telefonoEncontrado.getId());
            Telefono telefonoBorrado = telefonoServicio.obtenerTelefono(1);

            Assertions.assertNull(telefonoBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:telefonos.sql")
    public void listarTelefonosTest(){

        List<Telefono> telefonos= telefonoServicio.listarTelefonos();

        System.out.println(telefonos);
    }
}
