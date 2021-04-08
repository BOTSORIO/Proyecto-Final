package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Horario implements Serializable {

    @Id
    private int id;

    private String horario;

    public Horario(int id, String horario) {
        this.id = id;
        this.horario = horario;
    }

    public Horario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        return id == horario.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
