package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Imagen implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url",length = 100,nullable = false)
    @NotBlank
    private String url;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= CONSTRUCTOR  =================================//
    public Imagen() {
        super();
    }

    public Imagen(String url) {
        this.url = url;
    }

    //================================= SETS Y GETS =================================//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    //================================= EQUALS Y HASHCODE =================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagen imagen = (Imagen) o;

        return id == imagen.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nCodigo: " + getId() + "\nURL: " + getUrl();
    }
}
