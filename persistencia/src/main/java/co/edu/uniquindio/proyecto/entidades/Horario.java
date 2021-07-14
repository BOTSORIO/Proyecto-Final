package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Horario implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "diaSemana",length =100,nullable = false)
    @NotBlank
    @Positive
    private String diaSemana;

    @Column(name = "horaInicio",length =100,nullable = false)
    @NotBlank
    private String horaInicio;

    @Column(name = "horaFin",length =100,nullable = false)
    @NotBlank
    private String horaFin;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToMany(mappedBy = "horarios")
    private List<Lugar> lugares;

    //================================= CONSTRUCTOR  =================================//
    public Horario() {
        super();
        lugares = new ArrayList<>();
    }

    public Horario( String diaSemana, String horaInicio, String horaFin) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        lugares = new ArrayList<>();
    }

    //================================= SETS Y GETS =================================//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<Lugar> getLugares() {
        return lugares;
    }

    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    //================================= EQUALS Y HASHCODE =================================//
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


    //================================= TO STRING DE LA ENTIDAD =================================//

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", diaSemana='" + diaSemana + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
