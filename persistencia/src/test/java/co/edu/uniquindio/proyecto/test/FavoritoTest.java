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
public class FavoritoTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private FavoritoRepo favoritoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    //================================= Metodo para registrar o crear un favorito =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void registrarFavoritoTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Favorito favoritoNuevo = new Favorito("algo");
        favoritoNuevo.setUsuario(usuarioBuscado);
        favoritoNuevo.setLugar(lugarBuscado);

        Favorito favoritoGuardado = favoritoRepo.save(favoritoNuevo);

        System.out.println(favoritoGuardado.toString());

        Assertions.assertNotNull(favoritoGuardado);
    }

    //================================= Metodo para eliminar un favorito =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void eliminarFavoritoTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Favorito favoritoNuevo = new Favorito("algo");
        favoritoNuevo.setUsuario(usuarioBuscado);
        favoritoNuevo.setLugar(lugarBuscado);

        favoritoRepo.save(favoritoNuevo);
        favoritoRepo.delete(favoritoNuevo);

        Favorito favoritoBorrado= favoritoRepo.findById(1).orElse(null);

        Assertions.assertNull(favoritoBorrado);
    }

    //================================= Metodo para actualizar o modificar un favorito =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void actualizarFavoritoTest(){

        Usuario usuarioBuscado = usuarioRepo.findById("1245").orElse(null);
        Lugar lugarBuscado = lugarRepo.findById(1).orElse(null);

        Favorito favoritoNuevo = new Favorito("algo");
        favoritoNuevo.setUsuario(usuarioBuscado);
        favoritoNuevo.setLugar(lugarBuscado);

        Favorito favoritoGuardado = favoritoRepo.save(favoritoNuevo);

        favoritoGuardado.setAporte("nada");
        favoritoRepo.save(favoritoGuardado);
;

        Favorito favoritoBuscado= favoritoRepo.findById(1).orElse(null);

        System.out.println(favoritoBuscado.toString());

        Assertions.assertEquals("nada",favoritoBuscado.getAporte());
    }

    //================================= Metodo para obtener los favoritos =================================//
    @Test
    @Sql("classpath:favoritos.sql")
    public void listarFavoritos(){

        List<Favorito> lista = favoritoRepo.findAll();
        System.out.println(lista);
    }
}
