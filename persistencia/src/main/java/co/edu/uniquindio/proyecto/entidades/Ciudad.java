package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ciudad implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @NotBlank
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    @NotBlank
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
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

    public Ciudad() {
        super();
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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

        Ciudad ciudad = (Ciudad) o;

        return id == ciudad.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nCodigo:"+getId()+"\nNombre :"+ getNombre()+"\n";
    }
}
