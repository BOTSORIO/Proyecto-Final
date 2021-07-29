package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Value("#{param['lugar']}")
    private  String idLugar;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Getter @Setter
    private Lugar lugar;

    @Getter @Setter
    private List<Comentario> comentariosDetal;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private int calificacionPromedio;

    @Getter @Setter
    private Comentario comentarioNuevo;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private String icono;

    @PostConstruct
    public void inicializar(){

        this.comentarioNuevo = new Comentario();

        if (idLugar!=null && !"".equals(idLugar)){
            try {
                int id = Integer.parseInt(idLugar);

                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentariosDetal = obtenerComentarios();
                this.horarios = lugarServicio.listarHorarios(id);
               // this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void ingresarComentario(){

        Lugar lugarEncontrado;
        try {
            int id = Integer.parseInt(idLugar);
            lugarEncontrado = lugarServicio.obtenerLugar(id);

            if (personaLogin!=null && lugarEncontrado!=null){
                lugarServicio.ingresarComentario(comentarioNuevo,lugarEncontrado,personaLogin);
                this.comentarioNuevo = new Comentario();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void marcarFavorito() {

        Lugar lugarEncontrado;

        if(personaLogin!= null){

            int id = Integer.parseInt(idLugar);
            try {
                lugarEncontrado = lugarServicio.obtenerLugar(id);
                lugarServicio.marcarFavorito(lugarEncontrado,personaLogin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void eliminarFavorito(){

        Lugar lugarEncontrado;

        if (personaLogin != null){

            int id= Integer.parseInt(idLugar);
            try {

                lugarEncontrado= lugarServicio.obtenerLugar(id);
                lugarServicio.eliminarFavorito(lugarEncontrado,personaLogin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public List<Comentario> obtenerComentarios(){

        List<Comentario> comentarios;

        if (idLugar!=null){

            int id = Integer.parseInt(idLugar);

            try {
                comentarios = comentarioServicio.obtenerComentariosLugar(id);

                return comentarios;

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

}
