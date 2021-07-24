package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;
    private final TipoServicio tipoServicio;
    private final ImagenServicio imagenServicio;
    private final HorarioServicio horarioServicio;

    @Getter @Setter
    private Lugar lugar;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen>imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Tipo> tipos;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private Horario horario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private ArrayList<String>dias;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio, ImagenServicio imagenServicio, HorarioServicio horarioServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
        this.imagenServicio = imagenServicio;
        this.horarioServicio = horarioServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.lugar= new Lugar();
        this.horario = new Horario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoServicio.listarTipos();
        this.imagenes = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.dias = new ArrayList<>();
        llenarDias();
    }

    public String registrarLugar(){

        try {

            if (personaLogin!=null) {
                if (lugar.getLatitud() != 0 && lugar.getLongitud() != 0 && !imagenes.isEmpty()) {

                    lugar.setUsuario((Usuario) personaLogin);

                    for (Imagen i : imagenes) {

                        i.setLugar(lugar);
                    }

                    for (Horario h : horarios) {
                        h.getLugares().add(lugar);
                    }

                    lugar.setHorarios(horarios);
                    lugar.setImagenes(imagenes);
                    lugarServicio.registrarLugar(lugar);

                    for (Imagen i : imagenes) {

                        imagenServicio.registrarImagen(i);
                    }

                    for(Horario h:horarios){

                        horarioServicio.registrarHorario(h);
                    }

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el lugar se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                } else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario ubicar el lugar dentro del mapa");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }
        }catch (Exception e){

            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }

        return null;
    }

    public void subirImagenes(FileUploadEvent event){

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);

        if(nombreImagen!=null){

           Imagen foto = new Imagen(nombreImagen);

            imagenes.add(foto);
        }
    }


    public String subirImagen(UploadedFile file){

        try{
            InputStream input = file.getInputStream();
            String fileName = FilenameUtils.getName(file.getFileName());
            String baseName = FilenameUtils.getBaseName(fileName)+"_";
            String extension = "."+FilenameUtils.getExtension(fileName);
            File fileDest = File.createTempFile(baseName,extension,new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input,output);

            return fileDest.getName();
        }catch (Exception e){

            e.printStackTrace();
        }

        return null;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public void llenarDias() {
        this.dias = new ArrayList<>();
        this.dias.add("Lunes");
        this.dias.add("Martes");
        this.dias.add("Miercoles");
        this.dias.add("Jueves");
        this.dias.add("Viernes");
        this.dias.add("Sabado");
        this.dias.add("Domingo");
    }
    public void nuevoHorario() {
        System.out.println("Nuevo Horario");
        this.horario = new Horario();

    }

    public void crearHorario() {
        System.out.println("Creando horario");
        System.out.println(this.horario.getDiaSemana());
        System.out.println("Hora apertura" + this.horario.getHoraInicio());
        System.out.println("Hora cierre" + this.horario.getHoraFin());
        try {
            this.horarios.add(horario);
            nuevoHorario();
            for (Horario h : this.horarios) {
                System.out.println(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarHorario() {
        this.horarios.remove(this.horario);
        nuevoHorario();
    }

}
