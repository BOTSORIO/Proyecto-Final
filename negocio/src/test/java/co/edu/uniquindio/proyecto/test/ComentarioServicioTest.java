package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ComentarioServicioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    public void registrarComentarioTest(){

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion = sdf.parse("2021/01/04");

            Usuario usuario= new Usuario("0655","Jose","kuro","kuro123","k@gmail.com");
            Lugar lugar = new Lugar("no je","xd",fechaCreacion,fechaCreacion, 77, 65, 12,12,true);

            Comentario comentarioNuevo= new Comentario("hola",1,lugar,usuario);
            comentarioNuevo.setLugar(lugar);
            comentarioNuevo.setUsuario(usuario);

            Comentario comentarioRegistrado = comentarioServicio.registrarComentario(comentarioNuevo);

            Assertions.assertNotNull(comentarioRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void actualizarComentarioTest(){

        try {

            Comentario aux= new Comentario();

            aux.setComentario("XD");

            comentarioServicio.actualizarComentario(aux,1);

            Comentario comentarioEncontrado = comentarioServicio.obtenerComentario(1);

            Assertions.assertEquals("XD",comentarioEncontrado.getComentario());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void eliminarComentarioTest(){

        try {

            Comentario comentarioEncontrado = comentarioServicio.obtenerComentario(1);

            comentarioServicio.eliminarComentario(comentarioEncontrado.getId());
            Comentario comentarioBorrado = comentarioServicio.obtenerComentario(1);

            Assertions.assertNull(comentarioBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void listarComentariosTest(){

        List<Comentario> comentarios = comentarioServicio.listarComentarios();

        System.out.println(comentarios);

    }
}
