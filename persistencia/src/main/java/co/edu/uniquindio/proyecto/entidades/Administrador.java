package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Administrador extends Persona implements Serializable {

    @OneToMany(mappedBy = "idAdministrador")
    private List<Moderador>moderadores;

    public Administrador() {
        super();
    }
}
