package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private HorarioServicio horarioServicio;

    @Override
    public void run(String... args) throws Exception{

        if (administradorServicio.listarAdministradores().isEmpty()){

            Administrador admin1= new Administrador("1010017812","Melissa","DiosaNeko","Meli123","mortiz@gmail,com");
            administradorServicio.registrarAdministrador(admin1);

            Usuario u = new Usuario("1193409775", "usuario", "usuario", "usuario", "u@mail.com");
            usuarioServicio.registrarUsuario(u);

            Ciudad ciudad1 = new Ciudad("Calarcá");
            Ciudad ciudad3 = new Ciudad("Medellin");
            Ciudad ciudad4 = new Ciudad("Pereira");
            Ciudad ciudad5 = new Ciudad("Armenia");
            Ciudad ciudad6 = new Ciudad("Bogota");
            Ciudad ciudad7 = new Ciudad("Cucuta");
            Ciudad ciudad8 = new Ciudad("Villavicencio");
            Ciudad ciudad9 = new Ciudad("Cali");
            Ciudad ciudad10 = new Ciudad("Tulua");
            Ciudad ciudad11 = new Ciudad("Ibague");

            ciudadServicio.registrarCiudad(ciudad1);
            ciudadServicio.registrarCiudad(ciudad3);
            ciudadServicio.registrarCiudad(ciudad4);
            ciudadServicio.registrarCiudad(ciudad5);
            ciudadServicio.registrarCiudad(ciudad6);
            ciudadServicio.registrarCiudad(ciudad7);
            ciudadServicio.registrarCiudad(ciudad8);
            ciudadServicio.registrarCiudad(ciudad9);
            ciudadServicio.registrarCiudad(ciudad10);
            ciudadServicio.registrarCiudad(ciudad11);

            Tipo tipo1 = new Tipo("Restaurante");
            Tipo tipo2 = new Tipo("Motel");
            Tipo tipo3 = new Tipo("Cafe");
            Tipo tipo4 = new Tipo("Hotel");

            tipoServicio.registrarTipo(tipo1);
            tipoServicio.registrarTipo(tipo2);
            tipoServicio.registrarTipo(tipo3);
            tipoServicio.registrarTipo(tipo4);


            Horario h1 = new Horario("Lunes", "10:00", "16:00");
            Horario h2 = new Horario("Martes", "10:00", "16:00");
            horarioServicio.registrarHorario(h1);
            horarioServicio.registrarHorario(h2);

            List<Horario> horarios = new ArrayList<>();
            horarios.add(h1);
            horarios.add(h2);

            Lugar lugar1 = new Lugar("El castillo","Lugar bonito",new Date(),new Date(),4.52009964502443F,
                    -75.7124921956696F,ciudad1, u,new ArrayList<>(), horarios,tipo1);

            Lugar lugar2 = new Lugar("Selva negra","Cafe central",new Date(),new Date(),4.543038953770576F,
                    -75.68641861978931F,ciudad3, u,new ArrayList<>(), horarios,tipo2);

            lugarServicio.registrarLugar(lugar1);
            lugarServicio.registrarLugar(lugar2);

            Comentario comentario1= Comentario.builder().comentario("Que buen lugar")
                    .calificacion(4).lugar(lugar1)
                    .usuario(u).build();

            Comentario comentario2= Comentario.builder().comentario("Que buen lugar")
                    .calificacion(4).lugar(lugar2)
                    .usuario(u).build();

            lugarServicio.registrarComentario(comentario1);
            lugarServicio.registrarComentario(comentario2);

        }

    }
}
