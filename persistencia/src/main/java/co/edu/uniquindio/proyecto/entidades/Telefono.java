package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Telefono implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "telefono_lugar",length = 12,nullable = false)
    private String telefonoLugar;

    @ManyToOne
    private Lugar idLugar;

    public Telefono() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefonoLugar() {
        return telefonoLugar;
    }

    public void setTelefonoLugar(String telefonoLugar) {
        this.telefonoLugar = telefonoLugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telefono telefono = (Telefono) o;

        return id == telefono.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
