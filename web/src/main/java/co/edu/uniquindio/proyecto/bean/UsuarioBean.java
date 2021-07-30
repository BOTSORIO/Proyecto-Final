package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
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
@ViewScoped
public class UsuarioBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

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

    @Getter @Setter
    private List<Lugar>lugaresUsuario;

    @Getter @Setter
    private List<Comentario>comentariosSR;

    @Getter @Setter
    private List<Comentario>comentariosCR;

    @Getter @Setter
    private Comentario comentario;

    @Getter @Setter
    private Integer idComentarioResponder;

    @PostConstruct
    public void inicializar() {
        this.usuario  = new Usuario();
        this.usuarioAux = obtenerUsuario();
        this.comentario = new Comentario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.lugaresUsuario = obtenerLugaresUsuario();
        this.comentariosSR = obtenerComentariosSR();
        this.comentariosCR = obtenerComentariosCR();
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


    public List<Lugar> obtenerLugaresUsuario(){

        List<Lugar> lugaresU = null;

        if (personaLogin!=null){
            try{
                lugaresU= usuarioServicio.obtenerLugaresPorUsuario(personaLogin.getId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return lugaresU;

    }

    public List<Comentario>obtenerComentariosSR(){

        List<Comentario>comentariosSR=null;

        if(personaLogin!=null){
            try{

                comentariosSR=usuarioServicio.obtenerComentariosSinRespuesta(personaLogin.getId());

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return comentariosSR;

    }

    public List<Comentario>obtenerComentariosCR(){

        List<Comentario>comentariosCR=null;

        if(personaLogin!=null){
            try{

                comentariosCR=usuarioServicio.obtenerComentariosConRespuesta(personaLogin.getId());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return comentariosCR;
    }


    public void responderComentario(){

        Comentario comentarioEncontrado;

        if(personaLogin!=null){

            try{

                comentarioEncontrado = comentarioServicio.obtenerComentario(idComentarioResponder);

                if (comentarioEncontrado!=null){
                    comentarioServicio.responderComentario(comentario.getRespuesta(),idComentarioResponder);
                    this.comentariosSR = obtenerComentariosSR();

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! la respuesta se registro exitosamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }catch (Exception e){
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No fue posible registrar la respuesta");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }


    public void eliminarComentario(int idComentario) {

        Comentario comentarioEncontrado;

        if (personaLogin != null) {

            try {

                comentarioEncontrado = comentarioServicio.obtenerComentario(idComentario);
                comentarioServicio.eliminarComentario(comentarioEncontrado.getId());
                this.comentariosSR = obtenerComentariosSR();

                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El comentario se elimino correctamente");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "No se encontro el comentario");
                FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
            }
        }
    }

    public Usuario obtenerUsuario(){

        Usuario usuarioEncontrado = new Usuario();

        if (personaLogin!=null){

            try {
                usuarioEncontrado = usuarioServicio.obtenerUsuario(personaLogin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuarioEncontrado;
    }

    public void responder(Integer id) {
        this.idComentarioResponder = id;
    }

}
