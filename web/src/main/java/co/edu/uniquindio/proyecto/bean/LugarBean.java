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
import java.util.Date;
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
    private final TelefonoServicio telefonoServicio;

    @Getter @Setter
    private Lugar lugar;

    //Ya tenían las variables acá, estas si se pueden usar porque son Date
    @Getter @Setter
    private Date horaInicio;

    @Getter @Setter
    private Date horaFin;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<Imagen>imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Telefono> telefonos;

    @Getter @Setter
    private List<Tipo> tipos;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private Telefono telefono;

    @Getter @Setter
    private Horario horario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private ArrayList<String>dias;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio, TipoServicio tipoServicio, ImagenServicio imagenServicio, HorarioServicio horarioServicio, TelefonoServicio telefonoServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
        this.tipoServicio = tipoServicio;
        this.imagenServicio = imagenServicio;
        this.horarioServicio = horarioServicio;
        this.telefonoServicio = telefonoServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.lugar= new Lugar();
        this.horario = new Horario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipos = tipoServicio.listarTipos();
        this.imagenes = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.telefonos = new ArrayList<>();
        this.dias = new ArrayList<>();
        this.telefono= new Telefono();
        llenarDias();

    }

    public String registrarLugar(){
        try {
            if (personaLogin!=null) {
                if (lugar.getLatitud() != 0 && lugar.getLongitud() != 0 && !imagenes.isEmpty()) {

                    for(Horario h:horarios){
                        horarioServicio.registrarHorario(h);
                    }

                    lugar.setUsuario((Usuario) personaLogin);
                    lugar.setHorarios(horarios);

                    Lugar lugarCreado = lugarServicio.registrarLugar(lugar);

                    for (Imagen i : imagenes) {
                        i.setLugar(lugarCreado);
                        imagenServicio.registrarImagen(i);
                    }

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "¡Super! el lugar se creo correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);

                } else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario ubicar el lugar dentro del mapa");
                    FacesContext.getCurrentInstance().addMessage("mensajePersonalizado", facesMsg);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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
        this.horaInicio = new Date();
        this.horaFin = new Date();
    }
    public void nuevoHorario() {
        this.horario = new Horario();
    }

    public void crearHorario() {
        try {
            //acá está el casting, pero, value="#{lugarBean.horario.horaInicio}" acá lo asignan directamente, una solución e primero guardarlo en una variable acá en el bean
            if(horaInicio!=null && horaFin!=null) {
                String inicio = this.horaInicio.toString().split(" ")[3].substring(0, 5);
                String fin = this.horaFin.toString().split(" ")[3].substring(0, 5);
                horario.setHoraInicio(inicio);
                horario.setHoraFin(fin);
                System.out.println(horario);
                this.horarios.add(horario);
                nuevoHorario();
            }else{
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","Asigne un horario");
                FacesContext.getCurrentInstance().addMessage(null,msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarHorario() {
        this.horarios.remove(this.horario);
        nuevoHorario();
    }

    public void nuevoTelefono(){

        this.telefono = new Telefono();
    }

    public void crearTelefono(){

        if (personaLogin!=null){

            System.out.println(telefono.getTelefonoLugar());
            this.telefonos.add(telefono);
            nuevoTelefono();

            for(Telefono t:telefonos){
                System.out.println(t);
            }
        }

    }

    public void eliminarTelefono() {
        this.telefonos.remove(this.telefono);
        nuevoTelefono();
    }

}
