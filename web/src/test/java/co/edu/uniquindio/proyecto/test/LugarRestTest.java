package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.WebApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
public class LugarRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Transactional
    public void registrarLugar() throws Exception{

        Lugar lugar = Lugar.builder().nombre("Pepitos").descripcion("Lugar de comida").fechaCreacion(new Date()).latitud(4.52009964502443F)
                .longitud(-75.7124921956696F).build();

        mockMvc.perform(post("/api/lugares")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(lugar)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    public void eliminarLugar() throws Exception{

        mockMvc.perform(delete("/api/lugares/{id}", 2)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void actualizarLugar() throws Exception{


        Lugar lugar1= new Lugar();
        lugar1.setId(2);
        lugar1.setFechaCreacion(new Date());
        lugar1.setNombre("Rancho");
        lugar1.setDescripcion("Arepa");
        lugar1.setEstado(true);
        lugar1.setLatitud(4.52009964502443F);
        lugar1.setLongitud(-75.7124921956696F);

        mockMvc.perform(put("/api/lugares/{id}",2)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(lugar1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void obtenerLugarTest() throws Exception{

        mockMvc.perform(get("/api/lugares/{id}",2)
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    public void obtenerLugarNombreTest() throws Exception{

        mockMvc.perform(get("/api/lugares/nombre/{nombre}","Selva negra")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    public void listarLugares() throws Exception{

        mockMvc.perform(get("/api/lugares/")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obtenerLugaresMejorCalificacion() throws Exception{

        mockMvc.perform(get("/api/lugares/calificacion/")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obtenerLugaresFavoritosUsuario() throws Exception{

        mockMvc.perform(get("/api/lugares/favoritosUsuario/{idUsuario}","1193409775")
                        .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}
