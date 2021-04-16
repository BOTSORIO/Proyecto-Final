package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono,Integer> {

    //================================= REPOSITORIO DE TELEFONO =================================//
}
