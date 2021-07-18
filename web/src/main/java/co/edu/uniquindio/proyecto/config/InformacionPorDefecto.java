package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private TipoServicio tipoServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            Administrador admin1= new Administrador("1010017812","Melissa","DiosaNeko","Meli123","mortiz@gmail,com");
            administradorServicio.registrarAdministrador(admin1);
        }

        if (ciudadServicio.listarCiudades().isEmpty()){

            Ciudad ciudad1 = new Ciudad("Calarcá");
            Ciudad ciudad3 = new Ciudad("Medellin");
            Ciudad ciudad4 = new Ciudad("Pereira");

            ciudadServicio.registrarCiudad(ciudad1);
            ciudadServicio.registrarCiudad(ciudad3);
            ciudadServicio.registrarCiudad(ciudad4);

        }

        if (tipoServicio.listarTipos().isEmpty()){

            Tipo tipo1 = new Tipo("Restaurante");
            Tipo tipo2 = new Tipo("Motel");
            Tipo tipo3 = new Tipo("Cafe");
            Tipo tipo4 = new Tipo("Hotel");

            tipoServicio.registrarTipo(tipo1);
            tipoServicio.registrarTipo(tipo2);
            tipoServicio.registrarTipo(tipo3);
            tipoServicio.registrarTipo(tipo4);

        }

        if (lugarServicio.listarLugares().isEmpty()){

            //Los arrayList vacios que se mandan en el contructor son las imagenes y los horarios

            Lugar lugar1 = new Lugar("El castillo","Lugar bonito",new Date(),new Date(),4.52009964502443F,
                    -75.7124921956696F,ciudadServicio.obtenerCiudad(2),
                            usuarioServicio.obtenerUsuario("1193409775"),new ArrayList<>(),
                            new ArrayList<>(),tipoServicio.obtenerTipo(2));

            Lugar lugar2 = new Lugar("Selva negra","Cafe central",new Date(),new Date(),4.543038953770576F,
                    -75.68641861978931F,ciudadServicio.obtenerCiudad(2),
                    usuarioServicio.obtenerUsuario("1193409775"),new ArrayList<>(),
                    new ArrayList<>(),tipoServicio.obtenerTipo(3));

            lugarServicio.registrarLugar(lugar1);
            lugarServicio.registrarLugar(lugar2);
        }

        /*
        if(comentarioServicio.listarComentarios().isEmpty()){

            Comentario comentario1= Comentario.builder().comentario("Que buen lugar")
                    .calificacion(4).lugar(lugarServicio.obtenerLugar(1))
                    .usuario(usuarioServicio.obtenerUsuario("1193409775")).build();

            Comentario comentario2= Comentario.builder().comentario("Que buen lugar")
                    .calificacion(4).lugar(lugarServicio.obtenerLugar(2))
                    .usuario(usuarioServicio.obtenerUsuario("1193409775")).build();

            lugarServicio.registrarComentario(comentario1);
            lugarServicio.registrarComentario(comentario2);

        }

         */

    }
}
