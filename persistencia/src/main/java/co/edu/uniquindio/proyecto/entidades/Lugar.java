package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.*;
import java.util.*;

@Entity
public class Lugar implements Serializable {

    //================================= ATRIBUTOS CON SU RESPECTIVA PARAMETRIZACION =================================//
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

    //================================= RELACION CON LA ENTIDAD MODERADOR =================================//
    @ManyToOne
    private Moderador moderador;

    //================================= RELACION CON LA ENTIDAD USUARIO =================================//
    @ManyToOne
    private Usuario usuario;

    //================================= RELACION CON LA ENTIDAD CIUDAD =================================//
    @ManyToOne
    private Ciudad ciudad;

    //================================= RELACION CON LA ENTIDAD COMENTARIO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Comentario> comentarios;

    //================================= RELACION CON LA ENTIDAD TIPO =================================//
    @ManyToOne
    private Tipo tipo;

    //================================= RELACION CON LA ENTIDAD IMAGEN =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Imagen> imagenes;

    //================================= RELACION CON LA ENTIDAD TELEFONO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Telefono> telefonos;

    //================================= RELACION CON LA ENTIDAD FAVORITO =================================//
    @OneToMany(mappedBy = "lugar")
    private List<Favorito> favoritos;

    //================================= RELACION CON LA ENTIDAD HORARIO =================================//
    @ManyToMany
    private List<Horario> horarios;

    //================================= CONSTRUCTOR  =================================//
    public Lugar() {
        super();
        horarios=new ArrayList<>();
        telefonos= new ArrayList<>();
        imagenes = new ArrayList<>();
        favoritos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    public Lugar(String nombre, String descripcion, Date fechaCreacion, Date fechaAprobacion, float latitud, float longitud, String estado) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaAprobacion = fechaAprobacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        horarios=new ArrayList<>();
        telefonos= new ArrayList<>();
        imagenes = new ArrayList<>();
        favoritos = new ArrayList<>();
        comentarios = new ArrayList<>();
    }


    //================================= SETS Y GETS =================================//
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

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }


    //================================= EQUALS Y HASHCODE =================================//
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

    //================================= TO STRING =================================//
    @Override
    public String toString() {

        String cadena="";

        for(int i=0;i<getTelefonos().size();i++){

            cadena += "Telefonos: \n"+getTelefonos().get(i).getTelefonoLugar()+"\n";
        }

        for (int i=0;i<getHorarios().size();i++){

            cadena += "\nHorarios: \n"+ getHorarios().get(i).getHorario()+"\n";
        }

        for (int i=0;i<getComentarios().size();i++){

            cadena += "\nComentarios: \n" +getComentarios().get(i).getComentario()+"\n";
        }

        for (int i=0;i<getFavoritos().size();i++){

            cadena += "\nFavoritos: \n"+getFavoritos().get(i).getAporte()+"\n";
        }

        return "\nCodigo: "+getId()+"\nNombre: "+getNombre()+"\nDescripcion: "+getDescripcion()
                +"\nFechaC: "+getFechaCreacion()+"\nFechaA: "+getFechaAprobacion()+"\nLogintud: "+getLongitud()
                +"\nLatitud: "+getLatitud()+"\nEstado: "+getEstado()+"\n\nCiudad: "+getCiudad()
                +"\nModerador: "+getModerador()+"\nUsuario: "+getUsuario()+"\n"+cadena+"\n";
    }

}
