package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Favorito implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Usuario usuario;

}
