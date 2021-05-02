package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,String> {

    //================================= REPOSITORIO DE MODERADOR =================================//

    Optional<Moderador> findByEmail(String email);

    Optional<Moderador> findByNickname(String nickname);
}
