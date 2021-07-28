package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
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
    private List<Comentario> comentarios;

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
                this.comentarios = lugarServicio.listarComentarios(id);
                this.horarios = lugarServicio.listarHorarios(id);
               // this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void crearComentario(){
        try {
            if (personaLogin!=null) {
                comentarioNuevo.setLugar(this.lugar);
                comentarioNuevo.setUsuario((Usuario) personaLogin);
                lugarServicio.registrarComentario(comentarioNuevo);
                this.comentarioNuevo = new Comentario();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void marcarFavorito() throws Exception{

        Lugar lugarEncontrado;

        if(personaLogin!= null){

            int id = Integer.parseInt(idLugar);
            lugarEncontrado = lugarServicio.obtenerLugar(id);
            lugarServicio.marcarFavorito(lugarEncontrado,personaLogin);
        }
    }

    public List<Comentario> obtenerComentarios() throws Exception {

        List<Comentario> comentarios;
        int id = Integer.parseInt(idLugar);

        try {
            comentarios = comentarioServicio.obtenerComentariosLugar(id);

            return comentarios;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
