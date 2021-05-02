package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements  UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean estaDisponible(String email){
        Optional usuarioEmail= usuarioRepo.findByEmail(email);

        return usuarioEmail.isEmpty();
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

        Optional usuarioNick= usuarioRepo.findByNickname(u.getNickname());

        if(usuarioNick.isPresent()){
            throw new Exception("El nickname ya existe");
        }

        if(u.getNickname().length()>100){

            throw new Exception("El nombre solo puede tener hasta 100 caracteres");
        }

        if(!estaDisponible(u.getEmail())){

            throw new Exception("El correo ya existe");
        }


        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        //Agregar validaciones correspondientes
        usuarioRepo.save(u);

        return null;
    }

    @Override
    public Usuario obtenerUsuario(String id) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){

            throw new Exception("No existe el usuario con el id dado");
        }

        return usuario.get();
    }

    @Override
    public void eliminarUsuario(Usuario u) throws Exception {

        //validar que el usuario si exista en la base de datos (con usuarioRepo.findById se puede)
        usuarioRepo.delete(u);
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }
}
