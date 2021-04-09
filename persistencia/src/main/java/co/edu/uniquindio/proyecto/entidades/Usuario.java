package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario extends Persona implements Serializable {

    public Usuario() {
        super();
    }
}
