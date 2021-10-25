package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

    //============================ Instancias de servicio ============================//
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    //============================ Metodo para registrar moderadores ============================//
    @Test
    @Sql("classpath:administradores.sql")
    public void registrarModeradorTest(){

        try{

            Administrador administradorEncontrado= administradorServicio.obtenerAdministrador("5");

            Trabajador moderadorNuevo = new Trabajador("1","Braian","ghostbit","braian123","bra@gmail.com");
            moderadorNuevo.setAdministrador(administradorEncontrado);

            Trabajador moderadorRegistrado = moderadorServicio.registrarModerador(moderadorNuevo);

            System.out.println(moderadorRegistrado);

            Assertions.assertNotNull(moderadorRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //============================ Metodo para actualizar moderadores ============================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void actualizarModeradorTest(){

        try{

            Trabajador aux= new Trabajador();

            aux.setNickname("TUBBCITA");

            moderadorServicio.actualizarModerador(aux,"f@gmail.com","fer123");

            Trabajador moderadorEncontrado = moderadorServicio.obtenerModerador("123");

            Assertions.assertEquals("TUBBCITA",moderadorEncontrado.getNickname());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //============================ Metodo para eliminar moderadores ============================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void eliminarModeradorTest(){

        try{

            Trabajador moderadorEncontrado = moderadorServicio.obtenerModerador("123");

            moderadorServicio.eliminarModerador(moderadorEncontrado.getEmail(),moderadorEncontrado.getPassword());
            Trabajador moderadorBorrado= moderadorServicio.obtenerModerador("123");

            Assertions.assertNull(moderadorBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //============================ Metodo para listar moderadores ============================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void listarModeradoresTest(){

        List<Trabajador> moderadores = moderadorServicio.listarModeradores();

        System.out.println(moderadores);
    }
}
