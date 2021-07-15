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
    public Usuario(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        lugares= new ArrayList<>();
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return "\nId :"+getId()+"\nNombre :"+ getNombre()+"\nNickname :"+getNickname()+"\nPassword"
                +getPassword()+"\nEmail :"+getEmail()+"\n"+"Ciudad: "+getCiudad()+"\n";
    }
}
