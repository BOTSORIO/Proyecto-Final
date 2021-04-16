package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    Usuario findByEmailAndPassword(String email, String password);

    //================================= REPOSITORIO DE USUARIO =================================//
}
