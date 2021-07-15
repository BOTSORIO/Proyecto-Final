package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tipo implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    @NotBlank
    @Size(max = 100, message = "El tipo no puede superar los 100 caracteres")
    private String nombre;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "tipo")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Tipo(String nombre) {
        this.nombre = nombre;
        lugares= new ArrayList<>();
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {

        String cadena="";

        for (int i=0;i<getLugares().size();i++){

            cadena +="Codigo: "+getLugares().get(i).getId()+"\n";
            cadena +="Nombre: "+getLugares().get(i).getNombre()+"\n";
            cadena += "================\n";
        }
        return "\nCodigo :" + getId() + "\nNombre :" + getNombre()  + "\nLugares :\n" + cadena;
    }
}
