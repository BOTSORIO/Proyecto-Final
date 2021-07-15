package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Administrador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD MODERADOR =================================//
    @OneToMany(mappedBy = "administrador")
    private List<Moderador> moderadores;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        moderadores = new ArrayList<Moderador>();
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nId :"+getId()+"\nNombre: "+ getNombre()+"\nNickname: "+getNickname()+"\nPassword: "
                +getPassword()+"\nEmail: "+getEmail()+ "\n\n";
    }

}
