package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Ciudad implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "ciudad")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Ciudad( String nombre) {
        super();
        this.nombre = nombre;
        usuarios= new ArrayList<>();
        lugares= new ArrayList<>();
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nCodigo:"+getId()+"\nNombre :"+ getNombre()+"\n";
    }
}
