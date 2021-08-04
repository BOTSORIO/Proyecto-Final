package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.MailService;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class DetalleLugarBean implements Serializable {

    @Value("#{param['lugar']}")
    private  String idLugar;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private MailService mailService;

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
    private List<String> listaTelefonos;

    @Getter @Setter
    private Comentario comentarioNuevo;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private String icono;

    @Getter @Setter
    private boolean abierto;

    @Getter @Setter
    private List<String>urlImagenes;

    @PostConstruct
    public void inicializar(){

        this.comentarioNuevo = new Comentario();

        if (idLugar!=null && !"".equals(idLugar)){
            try {
                int id = Integer.parseInt(idLugar);

                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentariosDetal = obtenerComentarios();
                this.horarios = lugarServicio.listarHorarios(id);
                this.abierto = verificarHorario();
                this.urlImagenes = new ArrayList<>();
                this.listaTelefonos=new ArrayList<>();
                this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id);

                obtenerTelefonosLugar();

                List<Imagen>imagenes = lugar.getImagenes();

                if(imagenes.size()>0){

                    for(Imagen i:imagenes){

                        urlImagenes.add(i.getUrl());
                    }
                }else{

                    urlImagenes.add("default.png");
                }


                List<Lugar>lugares=new ArrayList<>();
                lugares.add(lugar);
                PrimeFaces.current().executeScript("crearMapa("+new Gson().toJson(lugares.stream().map(l -> new MarkerDTO(l.getId(),l.getNombre(),l.getDescripcion(),l.getLatitud(),l.getLongitud(),l.getEstado())).collect(Collectors.toList()))+");");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void ingresarComentario(){

        Lugar lugarEncontrado;
        Usuario usuarioCreador;
        try {
            int id = Integer.parseInt(idLugar);
            lugarEncontrado = lugarServicio.obtenerLugar(id);
            usuarioCreador=lugarEncontrado.getUsuario();

            if (personaLogin!=null && lugarEncontrado!=null && usuarioCreador!=null){
                lugarServicio.ingresarComentario(comentarioNuevo,lugarEncontrado,personaLogin);
                sendMailComentario();
                sendMailComentarioCreador(comentarioNuevo.getComentario(),usuarioCreador.getEmail());
                this.comentariosDetal.add(comentarioNuevo);
                this.comentarioNuevo = new Comentario();
                this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id);
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

    public void obtenerTelefonosLugar(){

        List<Telefono>telefonos=lugar.getTelefonos();

        if(telefonos.size()>0){

            for(Telefono t:telefonos){

                listaTelefonos.add(t.getTelefonoLugar());
            }
        }else{
            listaTelefonos.add("No hay telefonos");
        }
    }

    public void sendMailComentario(){

        String subject = "¡Tu comentario se registro con exito!";
        String message = "Cordial saludo, este correo es para informarte que acabas de publicar un comentario, ¡gracias por tu aporte!";

        mailService.sendMail("unilocal0804@gmail.com", personaLogin.getEmail(),subject,message);

    }

    public void sendMailComentarioCreador(String comentario,String email){

        String subject = "¡Alguien comento tu publicacion!";
        String message = comentario ;

        mailService.sendMail("unilocal0804@gmail.com", email,subject,message);

    }

    public String generarRuta(){

        return "/usuario/ruta?faces-redirect=true&amp;latitud="+lugar.getLatitud()+"&"+"longitud="+lugar.getLongitud();
    }

    public boolean verificarHorario(){

        int id= Integer.parseInt(idLugar);
        boolean flag= false;

        try {

            List<Lugar> lugaresAbiertos= lugarServicio.obtenerLugaresAbiertos();
            Lugar lugarEncontrado = lugarServicio.obtenerLugar2(id);

            for (Lugar l:lugaresAbiertos){

                if (l.getId()==lugarEncontrado.getId()){

                    flag=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
