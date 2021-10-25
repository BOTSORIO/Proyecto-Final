package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.Mascota;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Value("#{param['busqueda']}")
    private String busquedaParam;
    private String busqueda;

    private List<Mascota> lugares;

    @Autowired
    private LugarServicio lugarServicio;

    @PostConstruct
    public void inicializar(){
        if (busquedaParam!=null && !busquedaParam.isEmpty()){

          lugares =   lugarServicio.buscarLugares(busquedaParam);
            PrimeFaces.current().executeScript("crearMapa("+new Gson().toJson(this.lugares.stream().map(l -> new MarkerDTO(l.getId(),l.getNombre(),l.getDescripcion(),l.getLatitud(),l.getLongitud(),l.getEstado())).collect(Collectors.toList()))+");");
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getBusquedaParam() {
        return busquedaParam;
    }

    public void setBusquedaParam(String busquedaParam) {
        this.busquedaParam = busquedaParam;
    }

    public List<Mascota> getLugares() {
        return lugares;
    }

    public void setLugares(List<Mascota> lugares) {
        this.lugares = lugares;
    }
}
