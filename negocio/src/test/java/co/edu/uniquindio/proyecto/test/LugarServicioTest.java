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
public class LugarServicioTest {

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private TipoServicio tipoServicio;

    @Test
    public void registrarLugarTest(){

        try{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fechaCreacion =sdf.parse("2021/01/04");
            Date fechaAprobacion=sdf.parse("2021/01/04");

            Ciudad ciudad= new Ciudad("Medellin");
            Moderador moderador= new Moderador("9","Carlos","rojo","rojo123","ro@gmail.com");
            Usuario usuario= new Usuario("0","Omar","omitar","omar123","omar@gmail.com");
            Tipo tipo = new Tipo("Motel");
            Horario horario=new Horario("Lunes a viernes","10:00","20:00");
            Telefono telefono = new Telefono("3116310037");
            Imagen imagen = new Imagen("adsdasda");

            Lugar lugarNuevo= new Lugar("El castillo","Lugar de paso",fechaCreacion,fechaAprobacion,12,23,true);
            lugarNuevo.setTipo(tipo);
            lugarNuevo.setUsuario(usuario);
            lugarNuevo.setModerador(moderador);
            lugarNuevo.setCiudad(ciudad);
            lugarNuevo.getHorarios().add(horario);
            lugarNuevo.getTelefonos().add(telefono);
            lugarNuevo.getImagenes().add(imagen);

            Lugar lugarRegistrado = lugarServicio.registrarLugar(lugarNuevo);

            Assertions.assertNotNull(lugarRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void actualizatLugarTest(){

        try {

            Lugar lugarEncontrado = lugarServicio.obtenerLugar(1);

            lugarEncontrado.setEstado(false);
            Lugar lugarActualizado = lugarServicio.actualizarLugar(lugarEncontrado);

            Assertions.assertEquals(false,lugarActualizado.getEstado());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void eliminarLugarTest(){

        try {

            Lugar lugarEncontrado = lugarServicio.obtenerLugar(1);

            lugarServicio.eliminarLugar(lugarEncontrado.getId());
            Lugar lugarBorrado= lugarServicio.obtenerLugar(1);

            Assertions.assertNull(lugarBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:lugares.sql")
    public void listarLugaresTest(){

        List<Lugar> lugares= lugarServicio.listarLugares();

        System.out.println(lugares);
    }

}
