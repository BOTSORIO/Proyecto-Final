package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Moderador extends Persona implements Serializable {

    public Moderador() {
        super();
    }
}
