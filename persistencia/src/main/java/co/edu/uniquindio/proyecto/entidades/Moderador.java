package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Moderador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "moderador")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Moderador() {
        super();
        lugares = new ArrayList<>();
    }

    public Moderador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        lugares = new ArrayList<>();
    }

    //================================= SETS Y GETS =================================//
    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nId :"+getId()+"\nNombre :"+ getNombre()+"\nNickname :"+getNickname()+"\nPassword"
        +getPassword()+"\nEmail :"+getEmail()+"\n";
    }
}
