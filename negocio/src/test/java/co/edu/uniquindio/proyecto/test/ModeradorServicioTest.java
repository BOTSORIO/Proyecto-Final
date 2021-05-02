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

            Moderador moderadorNuevo = new Moderador("1","Braian","ghostbit","braian123","bra@gmail.com");
            moderadorNuevo.setAdministrador(administradorEncontrado);

            Moderador moderadorRegistrado = moderadorServicio.registrarModerador(moderadorNuevo);

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

            Moderador moderadorEncontrado = moderadorServicio.obtenerModerador("123");

            moderadorEncontrado.setNickname("TUBBCITA");
            Moderador moderadorActualizado = moderadorServicio.actualizarModerador(moderadorEncontrado);

            System.out.println(moderadorActualizado );

            Assertions.assertEquals("TUBBCITA",moderadorActualizado.getNickname());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //============================ Metodo para eliminar moderadores ============================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void eliminarModeradorTest(){

        try{

            Moderador moderadorEncontrado = moderadorServicio.obtenerModerador("123");

            moderadorServicio.eliminarModerador(moderadorEncontrado.getEmail());
            Moderador moderadorBorrado= moderadorServicio.obtenerModerador("123");

            Assertions.assertNull(moderadorBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //============================ Metodo para listar moderadores ============================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void listarModeradoresTest(){

        List<Moderador> moderadores = moderadorServicio.listarModeradores();

        System.out.println(moderadores);
    }
}
