package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trabajador extends Persona implements Serializable {

    //================================= RELACION CON LA ENTIDAD ADMINISTRADOR =================================//
    @ManyToOne
    private Administrador administrador;


    //================================= CONSTRUCTOR  =================================//
    public Trabajador(String id, String nombre, String nickname, String password, String email) {
        super(id, nombre, nickname, password, email);

    }

}
