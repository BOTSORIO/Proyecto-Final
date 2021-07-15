package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Imagen implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "url",length = 100,nullable = false)
    @NotBlank
    private String url;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= CONSTRUCTOR  =================================//
    public Imagen(String url) {
        this.url = url;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nCodigo: " + getId() + "\nURL: " + getUrl();
    }
}
