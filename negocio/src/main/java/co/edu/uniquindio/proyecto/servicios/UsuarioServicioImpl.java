package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    public boolean estaDisponible(String email){
        Optional<Usuario> usuarioEmail = usuarioRepo.findByEmail(email);

        return  usuarioEmail.isPresent();
    }


    @Override
    public Usuario registroUsuario(Usuario u) throws Exception {

        if (u.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres compa");
        }

        if (u.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres compa");
        }

        if (u.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres compa");
        }

        if (u.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        if (u.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres compa");
        }

        Optional<Usuario> usuarioNick=usuarioRepo.findByNickname(u.getNickname());
        if(usuarioNick.isPresent()){
            throw new Exception("El usuario ya existe");
        }

        if(estaDisponible(u.getEmail())){
            throw new Exception("El usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {


        if (u.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres compa");
        }

        if (u.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres compa");
        }

        if (u.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres compa");
        }

        if (u.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        if (u.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres compa");
        }

        return usuarioRepo.save(u);
    }


    @Override
    public void eliminarUsuario(String email) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmail(email);

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado :c");
        }

    }

    @Override
    public Usuario obtenerUsuario(String id) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
    }


    @Override
    public Usuario obtenerUsuarioEmail(String email) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findByEmail(email);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el correo dado");
        }

        return usuario.get();
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    /*
    @Override
    public Usuario iniciarSecion(String email, String password) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findByEmailAndPasswordS(email,password);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con estos datos por lo tanto no puede ingresar");
        }

        return usuario.get();
    }
     */
}
