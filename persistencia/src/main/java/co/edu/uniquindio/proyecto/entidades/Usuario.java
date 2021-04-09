package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario extends Persona implements Serializable {

    @ManyToOne
    private Ciudad idCiudad;

    @OneToMany(mappedBy = "idUsuario")
    private List<Lugar> lugares;

    @OneToMany(mappedBy = "idUsuario")
    private List<Comentario> comentarios;

    public Usuario() {
        super();
    }
}
