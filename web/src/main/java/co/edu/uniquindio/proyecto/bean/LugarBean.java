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
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
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

            lugar.setCiudad(ciudadServicio.obtenerCiudad(2));
            lugar.setUsuario(usuarioServicio.obtenerUsuario("1193409775"));
            lugar.setTipo(tipoServicio.obtenerTipo(1));

            lugarServicio.registrarLugar(lugar);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el lugar se creo correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            //return "lugarCreado?faces-redirect=true";

        }catch (Exception e){

            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta",e.getMessage());
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
