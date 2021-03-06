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
public class Moderador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @OneToMany(mappedBy = "moderador")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Moderador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        lugares = new ArrayList<>();
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nId: "+getId()+"\nNombre: "+ getNombre()+"\nNickname: "+getNickname()+"\nPassword: "
        +getPassword()+"\nEmail: "+getEmail()+"\n\nAdministrador: " + getAdministrador().getNombre() +"\n";
    }
}
