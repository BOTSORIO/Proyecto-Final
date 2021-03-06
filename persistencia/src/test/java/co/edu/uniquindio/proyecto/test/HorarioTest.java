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
public class HorarioTest {

    //================================= Instancias del repositorio =================================//
    @Autowired
    private HorarioRepo horarioRepo;


    //================================= Metodo para registrar o crear un horario =================================//
    @Test
    public void registrarHorarioTest(){

        Horario horarioNuevo = new Horario("lunes a viernes","10:00","22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);

        System.out.println(horarioGuardado.toString());

        Assertions.assertNotNull(horarioGuardado);
    }

    //================================= Metodo para eliminar un horario =================================//
    @Test
    public void eliminarHorarioTest(){

        Horario horarioNuevo = new Horario("lunes a viernes","10:00","22:00");

        horarioRepo.save(horarioNuevo);
        horarioRepo.delete(horarioNuevo);

        Horario horarioBorrado= horarioRepo.findById(1).orElse(null);

        Assertions.assertNull(horarioBorrado);
    }

    //================================= Metodo para actualizar o modificar un horario =================================//
    @Test
    public void actualizarHorarioTest(){

        Horario horarioNuevo =new Horario("lunes a viernes","10:00","22:00");

        Horario horarioGuardado = horarioRepo.save(horarioNuevo);

        horarioGuardado.setHoraFin("23:00");
        horarioRepo.save(horarioGuardado);

        Horario horarioBuscado= horarioRepo.findById(1).orElse(null);

        System.out.println(horarioBuscado.toString());

        Assertions.assertEquals("23:00",horarioBuscado.getHoraFin());
    }

    //================================= Metodo para obtener los horarios =================================//
    @Test
    @Sql("classpath:horarios.sql")
    public void listarHorariosTest(){

        List<Horario> lista = horarioRepo.findAll();
        System.out.println(lista);
    }
}
