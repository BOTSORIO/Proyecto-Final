package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    public void registrarUsuarioTest(){

        try {
            Ciudad ciudad = new Ciudad("Calarca");
            ciudadServicio.registrarCiudad(ciudad);

            Usuario usuarioNuevo= new Usuario("567","Nelson","nequi","nequi123","nequi@gmail.com");
            usuarioNuevo.setCiudad(ciudad);
            Usuario usuarioRegistrado= usuarioServicio.registrarUsuario(usuarioNuevo);

            System.out.println(usuarioRegistrado);

            Assertions.assertNotNull(usuarioRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarUsuarioTest(){

        try {

            Usuario usuario = usuarioServicio.obtenerUsuarioEliminar("m@gmail.com","manu123");

            usuarioServicio.eliminarUsuario(usuario.getEmail(),usuario.getPassword());

            Usuario usuarioEliminado = usuarioServicio.obtenerUsuarioEliminar("m@gmail.com","manu123");;

            Assertions.assertNull(usuarioEliminado);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void inisiarSesionUsuarioTest(){

        try {

            Usuario usuario = usuarioServicio.iniciarSesion("f@gmail.com","fer123");


            Assertions.assertNotNull(usuario);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarUsuarioTest(){

        try {

            Usuario usuarioGuardado= usuarioServicio.obtenerUsuario("123");

            usuarioGuardado.setNickname("neq");
            //usuarioServicio.actualizarUsuario(usuarioGuardado);

            Usuario usuarioBuscado = usuarioServicio.obtenerUsuario("123");

            System.out.println(usuarioBuscado);

            Assertions.assertEquals("neq",usuarioBuscado.getNickname());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosTest(){

        List<Usuario> listaUsuarios = usuarioServicio.listarUsuarios();

        System.out.println(listaUsuarios);
    }



    @Test
    @Sql("classpath:usuarios.sql")
    public void iniciarSecionTest(){

        try {

            Usuario usuarioBuscado = usuarioServicio.iniciarSesion("m@gmail.com","manu123");

            System.out.println(usuarioBuscado);

            Assertions.assertNotNull(usuarioBuscado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
