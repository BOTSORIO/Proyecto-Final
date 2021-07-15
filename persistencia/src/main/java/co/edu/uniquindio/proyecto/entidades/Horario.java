package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
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
    public Horario( String diaSemana, String horaInicio, String horaFin) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        lugares = new ArrayList<>();
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
