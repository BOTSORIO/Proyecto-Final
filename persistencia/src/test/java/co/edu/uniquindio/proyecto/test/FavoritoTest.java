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


    }

    //================================= Metodo para eliminar un favorito =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void eliminarFavoritoTest(){

    }

    //================================= Metodo para actualizar o modificar un favorito =================================//
    @Test
    @Sql("classpath:lugares.sql")
    @Sql("classpath:usuarios.sql")
    public void actualizarFavoritoTest(){

    }

    //================================= Metodo para obtener los favoritos =================================//
    @Test
    @Sql("classpath:favoritos.sql")
    public void listarFavoritosTest(){

        List<Favorito> lista = favoritoRepo.findAll();
        System.out.println(lista);
    }
}
