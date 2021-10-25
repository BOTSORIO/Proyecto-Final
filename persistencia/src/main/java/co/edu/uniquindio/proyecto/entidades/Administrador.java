package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Administrador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD TRABAJADOR =================================//
    @OneToMany(mappedBy = "administrador")
    private List<Trabajador> trabajadores;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @OneToMany(mappedBy = "administrador")
    private List<Usuario> usuarios;

    //================================= RELACION CON LA ENTIDAD SERVICIO =================================//
    @OneToMany(mappedBy = "administrador")
    private List<Servicio> servicios;

    //================================= CONSTRUCTOR  =================================//
    public Administrador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);
        trabajadores = new ArrayList<>();
        usuarios= new ArrayList<>();
        servicios= new ArrayList<>();
    }


}
