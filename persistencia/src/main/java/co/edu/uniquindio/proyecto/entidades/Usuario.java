package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "usuario")
    private List<Lugar> lugares;

    //================================= RELACION CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "usuario")
    private List<Favorito> favoritos;

    //================================= CONSTRUCTOR  =================================//
    public Usuario() {
        super();
        lugares= new ArrayList<>();
    }

    public Usuario(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        lugares= new ArrayList<>();
    }

    //================================= SETS Y GETS =================================//
    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }


    //================================= TO STRING =================================//
    @Override
    public String toString() {
        return "\nId :"+getId()+"\nNombre :"+ getNombre()+"\nNickname :"+getNickname()+"\nPassword"
                +getPassword()+"\nEmail :"+getEmail()+"\n"+"Ciudad: "+getCiudad()+"\n";
    }
}