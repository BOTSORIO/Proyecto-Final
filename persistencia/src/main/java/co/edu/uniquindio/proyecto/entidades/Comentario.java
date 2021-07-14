package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
public class Comentario implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comentario", nullable = false,length = 200)
    @NotBlank
    @Size(max = 200, message = "El comentario no puede superar los 200 caracteres")
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    @NotBlank
    @Positive
    private int calificacion;

    @Column(name = "respuesta", nullable = true,length = 200)
    @NotBlank
    @Size(max = 200, message = "La respuesta no puede superar los 200 caracteres")
    private String respuesta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comentario", nullable = false)
    private Date fechaComentario;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= CONSTRUCTOR  =================================//
    public Comentario() {
       super();
    }

    public Comentario(String comentario, int calificacion, String respuesta, Date fechaComentario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
    }

    //================================= SETS Y GETS =================================//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //================================= EQUALS Y HASHCODE =================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comentario that = (Comentario) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nComentario: " + getComentario() + "\nCalificacion: " + getCalificacion() +
                "\nRespuesta: " + getRespuesta() + "\nFecha del comentario: " + getFechaComentario() +
                "\nLugar: "+ getLugar().getNombre()+"\nUsuario: "+getUsuario().getNombre()+"\n";
    }
}
