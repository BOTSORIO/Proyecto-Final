package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.TipoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@RequestScope
public class LugarBean implements Serializable {

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoServicio tipoServicio;
    Lugar lugar;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
    }

    @PostConstruct
    public void inicializar(){
        lugar= new Lugar();
    }

    public String registrarLugar(){

        try {

            lugar.setCiudad(ciudadServicio.obtenerCiudad(1));
            lugar.setUsuario(usuarioServicio.obtenerUsuario("123"));
            lugar.setTipo(tipoServicio.obtenerTipo(1));

            lugarServicio.registrarLugar(lugar);
            //FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","El lugar se ha creado correctamente");
            //FacesContext.getCurrentInstance().addMessage(null,msg);
            return "lugarCreado?faces-redirect=true";

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
