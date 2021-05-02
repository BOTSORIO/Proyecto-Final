package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.*;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class TipoServicioTest {

    @Autowired
    private  TipoServicio tipoServicio;

    @Test
    public void registrarTipoTest(){

        try{
            Tipo tipoNuevo = new Tipo("motel");

            Tipo tipoRegistrado = tipoServicio.registrarTipo(tipoNuevo);

            System.out.println(tipoRegistrado);

            Assertions.assertNotNull(tipoRegistrado);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:tipos.sql")
    public void actualizarTipoTest(){

        try{
            Tipo tipoBuscado = tipoServicio.obtenerTipo(1);

            tipoBuscado.setNombre("Shango");
            Tipo tipoActualizado = tipoServicio.actualizarTipo(tipoBuscado);

            System.out.println(tipoActualizado);

            Assertions.assertEquals("Shango",tipoActualizado.getNombre());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:tipos.sql")
    public void eliminarTipoTest(){

        try{
            Tipo tipoBuscado = tipoServicio.obtenerTipo(1);
            tipoServicio.eliminarTipo(tipoBuscado.getId());
            Tipo tipoBorrado = tipoServicio.obtenerTipo(1);

            Assertions.assertNull(tipoBorrado);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:tipos.sql")
    public void ListarTipoTest() {
        List<Tipo> tipos = tipoServicio.listarTipos();

        System.out.println(tipos);
    }
}
