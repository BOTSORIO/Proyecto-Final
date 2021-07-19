package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Usuario usuarioLogeado;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioLogeado = new Usuario();
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


    public void eliminarUsuario(){

        try {
            usuarioServicio.eliminarUsuario(usuario.getEmail());
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario ha sido eliminado con exito");
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

    public void actualizarUsuario(){

        try{

            if(usuarioLogeado!=null){

                usuarioServicio.actualizarUsuario(usuarioLogeado.getEmail(),usuarioLogeado.getPassword(), usuario);
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el usuario se actualizo con exito");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch(Exception e){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

        }
    }

    public void iniciarSesion(){

        try {

            usuarioLogeado = usuarioServicio.iniciarSesion(usuario.getEmail(),usuario.getPassword());

            System.out.println(usuarioLogeado);

            if (usuarioLogeado != null){

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! ingreso correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            }else{

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "¡Lo sentimos!no se encuentra registrado en la pagina");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }

        }catch (Exception e) {

            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
        }
    }

}
