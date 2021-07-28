package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LugarServicioImpl implements LugarServicio {

    private final LugarRepo lugarRepo;
    private final ComentarioRepo comentarioRepo;
    private final TelefonoRepo telefonoRepo;


    public LugarServicioImpl(LugarRepo lugarRepo, ComentarioRepo comentarioRepo, TelefonoRepo telefonoRepo) {
        this.lugarRepo = lugarRepo;
        this.comentarioRepo = comentarioRepo;
        this.telefonoRepo = telefonoRepo;
    }

    @Override
    public Lugar registrarLugar(Lugar l) throws Exception {


        l.setEstado(false);
        l.setFechaAprobacion(new Date());
        l.setFechaCreacion(new Date());

        return lugarRepo.save(l);
    }

    @Override
    public void actualizarLugar(Lugar l,int codigoLugar) throws Exception {

        Lugar lugar = obtenerLugar(codigoLugar);

        if(lugar != null){
            lugar.setNombre(l.getNombre());
            lugar.setDescripcion(l.getDescripcion());
            lugar.setCiudad(l.getCiudad());
            lugar.setHorarios(l.getHorarios());
            lugar.setLatitud(l.getLatitud());
            lugar.setLongitud(l.getLongitud());
            lugar.setTipo(l.getTipo());

            lugarRepo.save(lugar);
        }
    }

    @Override
    public void eliminarLugar(int id) throws Exception {

        Lugar lugarEncontrado = obtenerLugar2(id);

        if(lugarEncontrado != null){

            lugarRepo.delete(lugarEncontrado);

        } else {
            throw  new Exception("No se encontro el lugar");
        }
    }

    @Override
    public void actualizarLugar(Lugar l) throws Exception{

        lugarRepo.save(l);

    }

    @Override
    public Lugar obtenerLugar(int id) throws Exception {

      Optional<Lugar> lugar = lugarRepo.findById(id);

      if(lugar.isEmpty()){
          throw  new Exception("No se encontro el lugar");
      }

        return lugar.get();
    }

    @Override
    public Lugar obtenerLugar2(int id) throws Exception {

        Lugar lugar = lugarRepo.obtenerLugar(id);

        if(lugar==null){
            throw  new Exception("No se encontro el lugar");
        }

        return lugar;
    }

    @Override
    public List<Lugar> listarLugares() {
        return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugares(String nombre) {
        return lugarRepo.buscarLugares(nombre);
    }

    @Override
    public List<Comentario> listarComentarios(Integer idLugar) {
        return lugarRepo.obtenerComentariosLugarEspecifico(idLugar);
    }

    @Override
    public List<Horario> listarHorarios(Integer idLugar) {
        return lugarRepo.obtenerHorariosLugarEspecifico(idLugar);
    }


    @Override
    public void registrarComentario(Comentario c) throws Exception {

        try{
            c.setFechaComentario(new Date());
            comentarioRepo.save(c);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
