package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Tipo;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.TipoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoServicio tipoServicio;

    @Getter @Setter
    private Lugar lugar;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Tipo> tipos;


    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.lugar= new Lugar();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoServicio.listarTipos();
    }

    public String registrarLugar(){

        try {
            if(lugar.getLatitud()!=0 && lugar.getLongitud()!=0){

                lugar.setUsuario(usuarioServicio.obtenerUsuario("1193409775"));

                lugarServicio.registrarLugar(lugar);

               // return "lugarCreado?faces-redirect=true";
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el lugar se creo correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario ubicar el lugar dentro del mapa");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }catch (Exception e){

            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }

        return null;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
}
