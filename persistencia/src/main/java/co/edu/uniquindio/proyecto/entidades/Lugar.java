package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lugar implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length =100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(name = "descripcion",length =200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_aprobacion")
    private Date fechaAprobacion;

    @Column(name = "latitud", nullable = false)
    private float latitud;

    @Column(name = "longitud", nullable = false)
    private float longitud;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    //================================= RELACION CON LA ENTIDAD MODERADOR =================================//
    @ManyToOne
    private Moderador moderador;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;

    //================================= RELACION CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD TIPO =================================//
    @ManyToOne
    private Tipo tipo;

    //================================= RELACION CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "lugar",fetch=FetchType.EAGER)
    private List<Imagen> imagenes;

    //================================= RELACION CON LA ENTIDAD TELEFONO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Telefono> telefonos;

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Favorito> favoritos;

    //================================= RELACION CON LA ENTIDAD HORARIO =================================//
    @ManyToMany
    private List<Horario> horarios;

    //================================= CONSTRUCTOR  =================================//
    @Builder
    public Lugar(String nombre, String descripcion, Date fechaCreacion, Date fechaAprobacion, float latitud, float longitud, Boolean estado) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaAprobacion = fechaAprobacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.horarios=new ArrayList<>();
        this.telefonos= new ArrayList<>();
        this.imagenes = new ArrayList<>();
        this.favoritos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    @Builder
    public Lugar(String nombre1, String descripcion, Date fechaCreacion, Date fechaAprobacion, float latitud, float longitud,Ciudad ciudad,Usuario usuarioCreador,List<Imagen>imagenes,List<Horario>horarios,Tipo tipo) {

        this.nombre = nombre1;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaAprobacion = fechaAprobacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
        this.horarios = horarios;
        this.imagenes = imagenes;
        this.usuario  = usuarioCreador;
        this.tipo = tipo;

    }

    public String getImagenPrincipal(){

        if(imagenes!=null && !imagenes.isEmpty()){

            return imagenes.get(0).getUrl();
        }

        return "default.png";
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {

        String cadena="";

        for(int i=0;i<getTelefonos().size();i++){

            cadena += "Telefonos: \n"+getTelefonos().get(i).getTelefonoLugar()+"\n";
        }

        for (int i=0;i<getHorarios().size();i++){

            cadena += "\nHorarios: \n"+ getHorarios().get(i).toString()+"\n";
        }

        for (int i=0;i<getComentarios().size();i++){

            cadena += "\nComentarios: \n" +getComentarios().get(i).getComentario()+"\n";
        }

        return "\nCodigo: "+getId()+"\nNombre: "+getNombre()+"\nDescripcion: "+getDescripcion()
                +"\nFechaC: "+getFechaCreacion()+"\nFechaA: "+getFechaAprobacion()+"\nLogintud: "+getLongitud()
                +"\nLatitud: "+getLatitud()+"\nEstado: "+getEstado()+"\n\nCiudad: "+getCiudad()
                +"\nModerador: "+getModerador()+"\nUsuario: "+getUsuario()+"\n"+"Tipo: "+getTipo()+"\n"+cadena+"\n";
    }

}
