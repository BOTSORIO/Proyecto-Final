package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequestScope
public class ModeradorBean implements Serializable {

    private final ModeradorServicio moderadorServicio;
    private final LugarServicio lugarServicio;

    @Getter
    @Setter
    private List<Lugar> lugaresAprobados;

    @Getter
    @Setter
    private List<Lugar> lugaresTAprobados;

    @Getter
    @Setter
    private List<Lugar> lugaresDesaprobados;

    @Getter
    @Setter
    private Moderador moderador;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public ModeradorBean(ModeradorServicio moderadorServicio, LugarServicio lugarServicio) {
        this.moderadorServicio = moderadorServicio;
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {

        try {
            this.lugaresAprobados = obtenerLugaresAprobados();
            this.lugaresTAprobados = obtenerTodosLugaresAprobados();
            this.lugaresDesaprobados = obtenerLugaresDesaprobados();
            this.moderador = obtenerModerador();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void aprobarLugar(int id) {

        Lugar lugarEncontrado ;

        if (personaLogin != null) {

            try {

                lugarEncontrado = lugarServicio.obtenerLugar(id);

                if (lugarEncontrado != null && !lugarEncontrado.getEstado()) {

                    lugarEncontrado.setEstado(true);
                    lugarEncontrado.setModerador((Moderador) personaLogin);
                    lugarEncontrado.setFechaAprobacion(new Date());

                    lugarServicio.actualizarLugar(lugarEncontrado);
                    this.lugaresAprobados=obtenerLugaresAprobados();
                    this.lugaresDesaprobados=obtenerLugaresDesaprobados();
                    this.lugaresTAprobados=obtenerTodosLugaresAprobados();
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el lugar se autorizo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void desaprobarLugar(int id){

        Lugar lugarEncontrado ;

        if (personaLogin != null) {

            try {

                lugarEncontrado = lugarServicio.obtenerLugar(id);

                if(lugarEncontrado!=null){

                    lugarEncontrado.setEstado(false);
                    lugarEncontrado.setModerador(null);
                    lugarEncontrado.setFechaAprobacion(null);

                    lugarServicio.actualizarLugar(lugarEncontrado);
                    this.lugaresAprobados=obtenerLugaresAprobados();
                    this.lugaresDesaprobados=obtenerLugaresDesaprobados();

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El lugar se desaprobo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                }else{
                    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","El lugar no existe");
                    FacesContext.getCurrentInstance().addMessage(null,msg);
                }

            }catch (Exception e){

                e.printStackTrace();
            }
        }

    }

    public List<Lugar> obtenerTodosLugaresAprobados(){

        List<Lugar> aprobados = null;

        if (personaLogin!=null){

            aprobados =moderadorServicio.obtenerTodosLugaresAprobados();
        }
        return aprobados;
    }

    public List<Lugar> obtenerLugaresAprobados(){

        List<Lugar> aprobados = null;

        if (personaLogin!=null){

            aprobados =moderadorServicio.obtenerLugaresAprobados(personaLogin.getEmail());
        }
        return aprobados;
    }

    public List<Lugar> obtenerLugaresDesaprobados() throws Exception {

        List<Lugar> desaprobados=null;

        if (personaLogin!=null){

            try{
                desaprobados= moderadorServicio.obtenerLugaresSinAprobacion();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return desaprobados;
    }

    public Moderador obtenerModerador() {

        Moderador moderadorEncontrado = new Moderador();

        if (personaLogin != null) {

            try {
                moderadorEncontrado=moderadorServicio.obtenerModerador(personaLogin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return moderadorEncontrado;
    }

}
