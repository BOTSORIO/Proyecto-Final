package co.edu.uniquindio.proyecto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args){
        SpringApplication.run(WebApplication.class,args); //lo que pasa es que se sejucutó un test y borró los datos de l abd
    }
}
