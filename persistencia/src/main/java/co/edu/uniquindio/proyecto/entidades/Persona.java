package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;

//================================= RELACION DE HERENCIA =================================//
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "nombre",length = 100,nullable = false)
    private String nombre;

    @Column(name = "nickname",length = 100,nullable = false,unique = true)
    private String nickname;

    @Column(name = "password",length = 100,nullable = false)
    private String password;

    @Column(name = "email",length = 100,nullable = false,unique = true)
    private String email;

    //================================= CONSTRUCTOR  =================================//
    public Persona() {
        super();
    }

    public Persona(String id, String nombre, String nickname, String password, String email) {
        this.id = id;
        this.nombre = nombre;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    //================================= SETS Y GETS =================================//
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //================================= EQUALS Y HASHCODE =================================//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return id != null ? id.equals(persona.id) : persona.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
