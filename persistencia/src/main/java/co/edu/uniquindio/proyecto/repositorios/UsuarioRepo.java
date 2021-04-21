package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {

    Usuario findByEmailAndPassword(String email, String password);

    //================================= REPOSITORIO DE USUARIO =================================//

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pageable);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort sort);

    @Query("select u from Usuario u where u.email = :email and u.nombre= :nombre")
    Usuario obtenerUsuario( @Param("email")String email ,@Param("nombre")String nombre);

    //and o or
    Usuario findByEmailOrNombre(String email,String nombre);

    List<Usuario> findByNombre(String nombre);









}
