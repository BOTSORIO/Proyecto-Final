package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Comentario registrarComentario(Comentario c) throws Exception {

        if(c.getComentario().length() > 200){
            throw  new Exception("No se pueden exceder los 200 caracteres");
        }

        if(c.getRespuesta().length() > 200){
            throw  new Exception("No se pueden exceder los 200 caracteres");
        }

        return comentarioRepo.save(c);
    }

    @Override
    public Comentario actualizarComentario(Comentario c) throws Exception {

        if(c.getComentario().length() > 200){
            throw  new Exception("No se pueden exceder los 200 caracteres");
        }

        if(c.getRespuesta().length() > 200){
            throw  new Exception("No se pueden exceder los 200 caracteres");
        }

        return comentarioRepo.save(c);
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
