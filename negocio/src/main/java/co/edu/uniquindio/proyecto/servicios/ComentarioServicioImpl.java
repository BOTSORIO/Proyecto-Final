package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;
    private final LugarRepo lugarRepo;
    private final UsuarioRepo usuarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo, LugarRepo lugarRepo, UsuarioRepo usuarioRepo) {
        this.comentarioRepo = comentarioRepo;
        this.lugarRepo = lugarRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {
       try {
           c.setFechaComentario(new Date());
           return comentarioRepo.save(c);
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }

    }

    @Override
    public void actualizarComentario(Comentario c,int codigoComentario) throws Exception {

        Comentario comentarioObtenido = obtenerComentario(codigoComentario);

        if(comentarioObtenido != null){
            comentarioObtenido.setComentario(c.getComentario());
            comentarioObtenido.setFechaComentario(c.getFechaComentario());
            comentarioObtenido.setCalificacion(c.getCalificacion());

            comentarioRepo.save(comentarioObtenido);
        }

    }

    @Override
    public List<Comentario> obtenerComentariosLugar(int idLugar) throws Exception {

        List<Comentario>comentariosLugar = lugarRepo.obtenerComentariosLugar(idLugar);

        if(comentariosLugar==null){

            throw new Exception("No se encontraron los horarios");
        }

        return comentariosLugar;
    }

    @Override
    public void eliminarComentario(int id) throws Exception {

        Comentario comentarioEncontrado = obtenerComentario(id);

        if(comentarioEncontrado != null){
            comentarioRepo.delete(comentarioEncontrado);
        } else {
            throw new Exception("No se ha encontrado el comentario");
        }
    }

    @Override
    public Comentario obtenerComentario(int id) throws Exception {

        Optional<Comentario> comentario = comentarioRepo.findById(id);

        if(comentario.isEmpty()){
            throw new Exception("No se ha encontrado el comentario");
        }

       return comentario.get();
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepo.findAll();
    }
}
