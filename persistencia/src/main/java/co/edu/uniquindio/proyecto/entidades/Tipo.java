package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tipo implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    @NotBlank
    @Size(max = 100, message = "El tipo no puede superar los 100 caracteres")
    private String nombre;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "tipo")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Tipo() {
        super();
        lugares= new ArrayList<>();
    }

    public Tipo(String nombre) {
        this.nombre = nombre;
        lugares= new ArrayList<>();
    }

    //================================= SETS Y GETS =================================//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    //================================= EQUALS Y HASHCODE =================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipo tipo = (Tipo) o;

        return id == tipo.id;
    }

    @Override
    public int hashCode() {
        return id;
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
