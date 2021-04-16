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
public class ModeradorTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private ModeradorRepo moderadorRepo;

    @Autowired
    private AdministradorRepo administradorRepo;

    //================================= Metodo para registrar o crear un moderador =================================//
    @Test
    @Sql("classpath:administradores.sql")
    public void registrarModeradorTest(){

        Administrador administradorBuscado = administradorRepo.findById("7").orElse(null);

        Moderador moderadorNuevo = new Moderador();
        moderadorNuevo.setId("1237");
        moderadorNuevo.setNombre("Daniel");
        moderadorNuevo.setNickname("Dan");
        moderadorNuevo.setPassword("12da03ni");
        moderadorNuevo.setEmail("d@gmail.com");
        moderadorNuevo.setAdministrador(administradorBuscado);

        administradorBuscado.getModeradores().add(moderadorNuevo);

        Moderador moderadorGuardado = moderadorRepo.save(moderadorNuevo);

        System.out.println(moderadorGuardado.toString());

        Assertions.assertNotNull(moderadorGuardado);
    }

    //================================= Metodo para eliminar un moderador =================================//
    @Test
    @Sql("classpath:administradores.sql")
    public void eliminarModeradorTest() {

        Administrador administradorBuscado = administradorRepo.findById("7").orElse(null);

        Moderador moderadorNuevo= new Moderador("1237","Daniel","Dan","12da03ni","d@gmail.com");
        moderadorNuevo.setAdministrador(administradorBuscado);

        moderadorRepo.save(moderadorNuevo);

        moderadorRepo.delete(moderadorNuevo);

        Moderador moderadorBorrado = moderadorRepo.findById("1237").orElse(null);

        Assertions.assertNull(moderadorBorrado);
    }

    //================================= Metodo para actualizar o modificar un moderador =================================//
    @Test
    @Sql("classpath:administradores.sql")
    public void actualizarModeradorTest(){

        Administrador administradorBuscado = administradorRepo.findById("7").orElse(null);

        Moderador moderadorNuevo= new Moderador("1237","Daniel","Dan","12da03ni","d@gmail.com");
        moderadorNuevo.setAdministrador(administradorBuscado);

        Moderador moderadorGuardado = moderadorRepo.save(moderadorNuevo);

        moderadorGuardado.setEmail("da@gmail.com");
        moderadorRepo.save(moderadorGuardado);

        Moderador moderadorBuscado= moderadorRepo.findById("1237").orElse(null);

        Assertions.assertEquals("da@gmail.com",moderadorBuscado.getEmail());
    }

    //================================= Metodo para obtener los moderadores =================================//
    @Test
    @Sql("classpath:moderadores.sql")
    public void listarModeradores(){

        List<Moderador> lista = moderadorRepo.findAll();
        System.out.println(lista);
    }
}
