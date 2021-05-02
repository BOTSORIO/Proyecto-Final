package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    //revisar
    @Test
    public void registrarUsuarioTest(){

        try{

            Ciudad ciudadNueva = new Ciudad("Tebaida");
            ciudadServicio.registrarCiudad(ciudadNueva);

            Usuario usuario = new Usuario("1193485769", "Sergio", "Serg", "sergio123", "sergio@gmail.com");
            usuario.setCiudad(ciudadNueva);
            Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(usuario);

            Assertions.assertNotNull(usuarioRegistrado);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    //revisar
    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarUsuarioTest(){

        try{

            Usuario usuario=usuarioServicio.obtenerUsuario("1");

            usuarioServicio.eliminarUsuario(usuario);

            Usuario usuarioEliminado = usuarioServicio.obtenerUsuario("1");

            Assertions.assertNull(usuarioEliminado);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

}
