package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Administrador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD MODERADOR =================================//
    @OneToMany(mappedBy = "administrador")
    private List<Moderador> moderadores;

    //================================= CONSTRUCTOR  =================================//
    public Administrador() {
        super();
        moderadores = new ArrayList<Moderador>();
    }

    public Administrador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
    }

    //================================= SETS Y GETS =================================//
    public List<Moderador> getModeradores() {
        return moderadores;
    }

    public void setModeradores(List<Moderador> moderadores) {
        this.moderadores = moderadores;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nId :"+getId()+"\nNombre: "+ getNombre()+"\nNickname: "+getNickname()+"\nPassword: "
                +getPassword()+"\nEmail: "+getEmail()+ "\n\n";
    }

}
