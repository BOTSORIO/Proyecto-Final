package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private TipoRepo tipoRepo;


    //================================= Metodo para registrar o crear un tipo =================================//
    @Test
    public void registrarTipoTest(){

        Tipo tipoNuevo = new Tipo("Comidas");

        Tipo tipoGuardado = tipoRepo.save(tipoNuevo);

        System.out.println(tipoGuardado.toString());

        Assertions.assertNotNull(tipoGuardado);
    }

    //================================= Metodo para eliminar un tipo =================================//
    @Test
    public void eliminarTipoTest(){

        Tipo tipoNuevo = new Tipo("Comidas");

        tipoRepo.save(tipoNuevo);
        tipoRepo.delete(tipoNuevo);

        Tipo tipoBorrado= tipoRepo.findById(1).orElse(null);

        Assertions.assertNull(tipoBorrado);
    }

    //================================= Metodo para actualizar o modificar un tipo =================================//
    @Test
    public void actualizarTipoTest(){

        Tipo tipoNuevo = new Tipo("Comidas");

        Tipo tipoGuardado = tipoRepo.save(tipoNuevo);

        tipoGuardado.setNombre("Bebidas");
        tipoRepo.save(tipoGuardado);

        Tipo tipoBuscado= tipoRepo.findById(1).orElse(null);

        Assertions.assertEquals("Bebidas",tipoBuscado.getNombre());
    }

    //================================= Metodo para obtener los tipos =================================//
    @Test
    @Sql("classpath:tipos.sql")
    public void listarTiposTest(){

        List<Tipo> lista = tipoRepo.findAll();
        System.out.println(lista);
    }


}
