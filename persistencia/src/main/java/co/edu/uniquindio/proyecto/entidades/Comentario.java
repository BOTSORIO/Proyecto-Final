package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "comentario", nullable = false,length = 200)
    @Size(max = 200, message = "El comentario no puede superar los 200 caracteres")
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @Column(name = "respuesta", nullable = true,length = 200)
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
    @Builder
    public Comentario(String comentario, int calificacion,Lugar lugar,Usuario usuario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.lugar = lugar;

    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {

        return  "Usuario: " + getUsuario().getNombre() + "\nComentario: " + getComentario()+
                "\nCalificación: " + getCalificacion() + "\nFecha: " + getFechaComentario();
    }
}
