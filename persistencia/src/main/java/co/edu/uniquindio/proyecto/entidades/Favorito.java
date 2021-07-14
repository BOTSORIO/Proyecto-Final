package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.*;

@Entity
public class Favorito implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @NotBlank
    private int id;

    @Column(name = "aporte",nullable = false)
    @NotBlank
    private String aporte;
    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= CONSTRUCTOR  =================================//
    public Favorito() {
        super();
    }

    public Favorito(String aporte) {
        this.aporte = aporte;
    }

    //================================= SETS Y GETS =================================//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAporte() {
        return aporte;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    //================================= EQUALS Y HASHCODE =================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorito favorito = (Favorito) o;

        return id == favorito.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nCodigo: " +getId()+ "\nAporte: " + getAporte() ;
    }
}
