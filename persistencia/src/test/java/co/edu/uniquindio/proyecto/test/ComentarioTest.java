package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    //================================= Metodo para registrar o crear un comentario =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void registrarComentarioTest() {

        try {
            Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
            Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion = sdf.parse("2021/01/04");

            Comentario comentarioNuevo = new Comentario("Cool xd", "10", "Ya sabemos :v", fechaCreacion);
            comentarioNuevo.setUsuario(usuarioBuscado);
            comentarioNuevo.setLugar(lugarBuscado);

            Comentario comentarioGuardado = comentarioRepo.save(comentarioNuevo);

            System.out.println(comentarioGuardado.toString());

            Assertions.assertNotNull(comentarioGuardado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para eliminar un comentario =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void eliminarComentarioTest() {

        try {
            Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
            Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion = sdf.parse("2021/01/04");

            Comentario comentarioNuevo = new Comentario("Cool xd", "10", "Ya sabemos :v", fechaCreacion);
            comentarioNuevo.setUsuario(usuarioBuscado);
            comentarioNuevo.setLugar(lugarBuscado);

            comentarioRepo.save(comentarioNuevo);
            comentarioRepo.delete(comentarioNuevo);

            Comentario comentarioBorrado= comentarioRepo.findById(1).orElse(null);

            Assertions.assertNull(comentarioBorrado);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para actualizar o modificar un comentario =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void actualizarComentarioTest() {

        try {
            Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
            Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion = sdf.parse("2021/01/04");

            Comentario comentarioNuevo = new Comentario("Cool xd", "10", "Ya sabemos :v", fechaCreacion);
            comentarioNuevo.setUsuario(usuarioBuscado);
            comentarioNuevo.setLugar(lugarBuscado);

            Comentario comentarioGuardado = comentarioRepo.save(comentarioNuevo);

            comentarioGuardado.setComentario("Ya no es cool");
            comentarioRepo.save(comentarioGuardado);

            Comentario comentarioBuscado= comentarioRepo.findById(1).orElse(null);

            System.out.println(comentarioBuscado.toString());

            Assertions.assertEquals("Ya no es cool",comentarioBuscado.getComentario());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //================================= Metodo para obtener los comentarios =================================//
    @Test
    @Sql("classpath:comentarios.sql")
    public void listarComentarios(){
        List<Comentario> lista = comentarioRepo.findAll();
        System.out.println(lista);
    }
}
