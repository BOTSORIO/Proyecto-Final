package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class AdministradorBean implements Serializable {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    @Getter
    @Setter
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Moderador moderador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void inicializar() {
        this.moderador  = new Moderador();
        this.ciudades = ciudadServicio.listarCiudades();
    }

    public void registrarModerador() {
        try {
            if (personaLogin != null) {
                moderador.setAdministrador((Administrador) personaLogin);
                moderadorServicio.registrarModerador(moderador);

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se ha creado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public void eliminarModerador(){

        try {
            if (personaLogin!=null ) {
                moderador.setAdministrador(null);
                moderadorServicio.eliminarModerador(moderador.getEmail(),moderador.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarModerador(){

        try{
            if(personaLogin!=null){

                Moderador moderadorAux = moderadorServicio.obtenerModerador(moderador.getId());

                if (moderadorAux!=null){

                    moderador.setAdministrador((Administrador) personaLogin);
                    moderadorServicio.actualizarModerador(moderador,moderadorAux.getEmail(),moderadorAux.getPassword());
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el moderador se actualizo con exito");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No hemos podido encontrar el moderador");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }
}
