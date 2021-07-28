package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    @Getter @Setter
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Usuario usuario;

    @Getter @Setter
    private Usuario usuarioAux;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioAux = new Usuario();
        this.ciudades = ciudadServicio.listarCiudades();
    }

    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! te registramos correctamente");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }


    public String eliminarUsuario(){

        try {
            if (personaLogin!=null) {

                usuarioServicio.eliminarUsuario(usuario.getEmail(),usuario.getPassword());
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario ha sido eliminado con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }

        return null;
    }


    public String actualizarUsuario(){

        try{

            if(personaLogin!=null){

                usuarioServicio.actualizarUsuario(personaLogin.getEmail(),personaLogin.getPassword(), usuario);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "/index?faces-redirect=true";
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
        return null;
    }

}
