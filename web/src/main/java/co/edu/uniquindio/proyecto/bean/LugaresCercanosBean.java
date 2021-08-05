package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class LugaresCercanosBean {

    @Autowired
    LugarServicio lugarServicio;

    @Setter @Getter
    private List<Lugar> lugares;

    @Getter @Setter
    private List<Lugar>lugaresCercanos;

    @Getter @Setter
    private float distancia;

    @Getter @Setter
    private int distanciaBusqueda;

    @PostConstruct
    public void inicializar(){

        this.lugares = lugarServicio.listarLugares();
        this.lugaresCercanos = new ArrayList<>();

    }

    public void obtenerLugaresCercanos(){

        PrimeFaces.current().executeScript("obtenerDistanciaLugar("+new Gson().toJson(this.lugares.stream().map(l -> new MarkerDTO(l.getId(),l.getNombre(),l.getDescripcion(),l.getLatitud(),l.getLongitud(),l.getEstado())).collect(Collectors.toList()))+","+distanciaBusqueda+");");
    }

}
