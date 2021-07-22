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
public class FavoritoServicioTest {

    @Autowired
    private FavoritoServicio favoritoServicio;

    @Test
    public void registrarFavoritoTest(){

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion = sdf.parse("2021/01/04");

            Usuario usuario= new Usuario("0655","Jose","kuro","kuro123","k@gmail.com");
            Lugar lugar = new Lugar("no je","xd",fechaCreacion,fechaCreacion,12,12,true);

            Favorito favoritoNuevo= new Favorito("pam pam");
            favoritoNuevo.setLugar(lugar);
            favoritoNuevo.setUsuario(usuario);

            Favorito favoritoRegistrado = favoritoServicio.registrarFavorito(favoritoNuevo);

            Assertions.assertNotNull(favoritoRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:favoritos.sql")
    public void actualiazarFavoritoTest(){

        try {

            Favorito favoritoEncontrado= favoritoServicio.obtenerFavorito(1);

            favoritoEncontrado.setAporte("no je");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:favoritos.sql")
    public void eliminarFavoritoTest(){

        try {

            Favorito favoritoEncontrado= favoritoServicio.obtenerFavorito(1);

            favoritoServicio.eliminarFavorito(favoritoEncontrado.getId());
            Favorito favoritoBorrado= favoritoServicio.obtenerFavorito(1);

            Assertions.assertNull(favoritoBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:favoritos.sql")
    public void listarFavoritosTest(){

        List<Favorito> favoritos= favoritoServicio.listarFavoritos();

        System.out.println(favoritos);

    }

    }
