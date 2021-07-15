package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefono implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "telefono_lugar",length = 12,nullable = false)
    @NotBlank
    @Size(max = 12, message = "El numero de telefono no puede superar los 12 digitos")
    private String telefonoLugar;

    //================================= RELACION CON LA ENTIDAD LUGAR =================================//
    @ManyToOne
    private Lugar lugar;

    //================================= CONSTRUCTOR  =================================//
    public Telefono(String telefonoLugar) {
        this.telefonoLugar = telefonoLugar;
    }


    //================================= TO STRING DE LA ENTIDAD =================================//
    @Override
    public String toString() {
        return  "\nCodigo :" + getId() + "\nTelefono del lugar :" + getTelefonoLugar();
    }
}
