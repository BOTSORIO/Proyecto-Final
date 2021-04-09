package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "nombre",length =100, nullable = false)
    private String nombre;

    @Column(name = "descripcion",length =200, nullable = false)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_aprobacion", nullable = false)
    private Date fechaAprobacion;

    @Column(name = "latitud", nullable = false)
    private float latitud;

    @Column(name = "longitud", nullable = false)
    private float longitud;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    private Moderador idModerador;

    @ManyToOne
    private Usuario idUsuario;

    @ManyToOne
    private Ciudad idCiudad;

    @OneToMany(mappedBy = "idLugar")
    private List<Comentario> comentarios;

    @ManyToOne
    private Tipo idTipo;

    @OneToMany(mappedBy = "idLugar")
    private List<Imagen> imagenes;

    @OneToMany(mappedBy = "idLugar")
    private List<Telefono> telefonos;

    @ManyToMany
    private List<Horario> horarios;



    public Lugar() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lugar lugar = (Lugar) o;

        return id == lugar.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
