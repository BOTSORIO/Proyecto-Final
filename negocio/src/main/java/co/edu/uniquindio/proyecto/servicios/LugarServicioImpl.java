package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LugarServicioImpl implements LugarServicio {

    private final LugarRepo lugarRepo;

    public LugarServicioImpl(LugarRepo lugarRepo) {
        this.lugarRepo = lugarRepo;
    }

    @Override
    public Lugar registrarLugar(Lugar l) throws Exception {

        if(l.getDescripcion().length() > 200){
            throw new Exception("La descripcion excede el maximo de caracteres");
        }

        if(l.getNombre().length() > 100){
            throw new Exception("El nombre excede el maximo de caracteres");
        }

        return lugarRepo.save(l);
    }

    @Override
    public Lugar actualizarLugar(Lugar l) throws Exception {

        if(l.getDescripcion().length() > 200){
            throw new Exception("La descripcion excede el maximo de caracteres");
        }

        if(l.getNombre().length() > 100){
            throw new Exception("El nombre excede el maximo de caracteres");
        }

        return lugarRepo.save(l);
    }

    @Override
    public void eliminarLugar(int id) throws Exception {

        Lugar lugarEncontrado = obtenerLugar(id);

        if(lugarEncontrado != null){
            lugarRepo.delete(lugarEncontrado);
        } else {
            throw  new Exception("No se encontro el lugar");
        }
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
    public List<Lugar> listarLugares() {
        return lugarRepo.findAll();
    }
}
