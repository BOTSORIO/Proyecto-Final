package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestScope
public class ModeradorBean implements Serializable {

    private final ModeradorServicio moderadorServicio;
    private final LugarServicio lugarServicio;

    @Getter
    @Setter
    private List<Lugar> lugaresAprobados;

    @Getter
    @Setter
    private List<Lugar> lugaresDesaprobados;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public ModeradorBean(ModeradorServicio moderadorServicio, LugarServicio lugarServicio) {
        this.moderadorServicio = moderadorServicio;
        this.lugarServicio = lugarServicio;
    }

    @PostConstruct
    public void inicializar() {
        //  this.lugar= new Lugar();
        this.lugaresAprobados = obtenerLugaresAprobados();

        try {
            this.lugaresDesaprobados = obtenerLugaresDesaprobados();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void aprobarLugar(int id) {

        Lugar lugarEncontrado = null;

        if (personaLogin != null) {

            try {

                lugarEncontrado = lugarServicio.obtenerLugar(id);

                if (lugarEncontrado != null && !lugarEncontrado.getEstado()) {

                    lugarEncontrado.setEstado(true);
                    lugarEncontrado.setModerador((Moderador) personaLogin);

                    lugarServicio.registrarLugar(lugarEncontrado);

                } else if (lugarEncontrado != null && lugarEncontrado.getEstado()) {

                    lugarEncontrado.setEstado(false);
                    lugarEncontrado.setModerador(null);

                    lugarServicio.registrarLugar(lugarEncontrado);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public List<Lugar> obtenerLugaresAprobados(){

        List<Lugar> aprobados = null;

        if (personaLogin!=null){

            aprobados =moderadorServicio.obtenerLugaresAprobados(personaLogin.getEmail());
        }
        return aprobados;
    }

    public List<Lugar> obtenerLugaresDesaprobados() throws Exception {

        List<Lugar> desaprobados=null;

        if (personaLogin!=null){

            try{
                desaprobados= moderadorServicio.obtenerLugaresSinAprobacion();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return desaprobados;
    }

}
