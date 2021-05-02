package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Tipo;

import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TipoServicioImpl implements TipoServicio{

    private final TipoRepo tipoRepo;

    public TipoServicioImpl(TipoRepo tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    @Override
    public Tipo registrarTipo(Tipo t) throws Exception {


      if (t.getNombre().length()>100){
          throw new Exception("El nombre no puede exceder los 100 caracteres");
      }

        return tipoRepo.save(t);
    }

    @Override
    public Tipo actualizarTipo(Tipo t) throws Exception {

        if (t.getNombre().length()>100){
            throw new Exception("El nombre no puede exceder los 100 caracteres");
        }

        return tipoRepo.save(t);

    }

    @Override
    public void eliminarTipo(int id) throws Exception {

        Tipo tipoEncontrado = obtenerTipo(id);

        if(tipoEncontrado != null){
            tipoRepo.delete(tipoEncontrado);
        } else {
            throw  new Exception("No se ha encontrado el tipo");
        }

    }

    @Override
    public Tipo obtenerTipo(int id) throws Exception {

        Optional<Tipo> tipo = tipoRepo.findById(id);

        if(tipo.isEmpty()){
            throw  new Exception("No se ha encontrado el tipo");
        }

        return tipo.get();
    }

    @Override
    public List<Tipo> listarTipos() {
        return tipoRepo.findAll();
    }
}
