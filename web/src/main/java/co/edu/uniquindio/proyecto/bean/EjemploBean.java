package co.edu.uniquindio.proyecto.bean;

import org.springframework.stereotype.Component;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class EjemploBean implements Serializable {

    private String atributo1;
    private String atributo2;

    public void cambiarValores(){
        String temp = atributo1;
        atributo1 = atributo2;
        atributo2 = temp;
    }

    public String getAtributo1() {
        return atributo1;
    }

    public void setAtributo1(String atributo1) {
        this.atributo1 = atributo1;
    }

    public String getAtributo2() {
        return atributo2;
    }

    public void setAtributo2(String atributo2) {
        this.atributo2 = atributo2;
    }
}