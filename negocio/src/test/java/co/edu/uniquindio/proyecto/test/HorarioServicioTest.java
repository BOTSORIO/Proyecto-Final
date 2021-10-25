package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.util.*;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class HorarioServicioTest {

    @Autowired
    private HorarioServicio horarioServicio;

    @Test
    public void registrarHorarioTest(){

        try {

            Horario horario = new Horario("Lunes","10:00","22:00");

            Horario horarioRegistrado = horarioServicio.registrarHorario(horario);

            Assertions.assertNotNull(horarioRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:horarios.sql")
    public void actualizarHorarioTest(){

        try {

            Horario horarioEncontrado= horarioServicio.obtenerHorario(1);

            horarioEncontrado.setDiaSemana("Martes");
            Horario horarioActualizado= horarioServicio.actualizarHorario(horarioEncontrado);

            Assertions.assertEquals("Martes",horarioActualizado.getDiaSemana());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:horarios.sql")
    public void eliminarHorarioTest(){

        try {

            Horario horarioEncontrado= horarioServicio.obtenerHorario(1);

            horarioServicio.eliminarHorario(horarioEncontrado.getId());
            Horario horarioBorrado= horarioServicio.obtenerHorario(1);

            Assertions.assertNull(horarioBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:horarios.sql")
    public void listarHorariosTest(){

        List<Horario> horarios= horarioServicio.listarHorarios();

        System.out.println(horarios);
    }


}
