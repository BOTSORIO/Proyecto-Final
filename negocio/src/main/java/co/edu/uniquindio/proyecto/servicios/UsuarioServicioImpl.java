package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final LugarRepo lugarRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, LugarRepo lugarRepo) {
        this.usuarioRepo = usuarioRepo;
        this.lugarRepo = lugarRepo;
    }


    public boolean estaDisponible(String email){
        Usuario usuarioEmail = usuarioRepo.findByEmail(email);

        if (usuarioEmail!=null){
            return  true;
        }

        return false;
    }


    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

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
    public void actualizarUsuario(String email, String password,Usuario u) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password);

        if(usuarioEncontrado!=null){

            usuarioEncontrado.setNombre(u.getNombre());
            usuarioEncontrado.setNickname(u.getNickname());
            usuarioEncontrado.setPassword(u.getPassword());
            usuarioEncontrado.setCiudad(u.getCiudad());
            usuarioEncontrado.setEmail(u.getEmail());

            usuarioRepo.save(usuarioEncontrado);
        }

    }


    @Override
    public void eliminarUsuario(String email,String password) throws Exception {

        Usuario usuarioEncontrado = obtenerUsuarioEmailPassword(email,password) ;

        if (usuarioEncontrado != null){
            usuarioRepo.delete(usuarioEncontrado);
        }else{
            throw new Exception("Usuario no encontrado ");
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
    public Usuario obtenerUsuarioEmailPassword(String email,String password) throws Exception {

        Usuario usuario = usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario==null){
            throw new Exception("No existe un usuario con el correo y contraseña ingresado");
        }

        return usuario;
    }


    @Override
    public List<Lugar> obtenerLugaresPorUsuario(String idUsuario){

        List<Lugar> lugaresU = lugarRepo.obtenerLugaresPorUsuario(idUsuario);

        return lugaresU;
    }

    @Override
    public List<Comentario> obtenerComentariosSinRespuesta(String idUsuario){

        List<Lugar> lugaresU = obtenerLugaresPorUsuario(idUsuario);
        List<Comentario> comentariosSR=new ArrayList<>();

        for (Lugar l:lugaresU){
            List<Comentario>comentariosLugar=l.getComentarios();

            if(comentariosLugar.size()>0){

                for(Comentario c:comentariosLugar){

                    if(c.getRespuesta()==null){

                        comentariosSR.add(c);
                    }

                }
            }

        }

        return comentariosSR;
    }

    @Override
    public List<Comentario> obtenerComentariosConRespuesta(String idUsuario){

        List<Lugar> lugaresU = obtenerLugaresPorUsuario(idUsuario);
        List<Comentario> comentariosCR=new ArrayList<>();

        for (Lugar l:lugaresU){
            List<Comentario>comentariosLugar=l.getComentarios();

            if(comentariosLugar.size()>0){

                for(Comentario c:comentariosLugar){

                    if(c.getRespuesta()!=null){

                        comentariosCR.add(c);
                    }

                }
            }

        }

        return comentariosCR;
    }


    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }


}
