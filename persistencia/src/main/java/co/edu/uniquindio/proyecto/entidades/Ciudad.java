package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    public Ciudad() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciudad ciudad = (Ciudad) o;

        return id == ciudad.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
