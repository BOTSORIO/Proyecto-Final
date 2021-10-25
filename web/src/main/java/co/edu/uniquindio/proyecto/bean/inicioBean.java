package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.Mascota;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class inicioBean implements Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private List<Mascota> lugares;

    @PostConstruct
    public void inicializar(){

        this.lugares = lugarServicio.listarLugares();

        PrimeFaces.current().executeScript("crearMapa("+new Gson().toJson(this.lugares.stream().map(l -> new MarkerDTO(l.getId(),l.getNombre(),l.getDescripcion(),l.getLatitud(),l.getLongitud(),l.getEstado())).collect(Collectors.toList()))+");");
    }

    public String irADetalle(Integer id){
        return  "/detalleLugar?faces-redirect=true&amp;lugar="+id;
    }
}
