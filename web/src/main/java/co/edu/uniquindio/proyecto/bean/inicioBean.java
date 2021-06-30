package co.edu.uniquindio.proyecto.bean;

import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class inicioBean implements Serializable {

    private String mensaje= "Hola mundo desde Iniciobean!";

    public String getMensaje(){
        return mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
}
