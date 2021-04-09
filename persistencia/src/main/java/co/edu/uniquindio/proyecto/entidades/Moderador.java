package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Moderador extends Persona implements Serializable {

    @ManyToOne
    private Administrador idAdministrador;

    @OneToMany(mappedBy = "idModerador")
    private List<Lugar> lugares;



    public Moderador() {
        super();
    }
}
