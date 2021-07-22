package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TelefonoServicioImpl implements TelefonoServicio{

   private final TelefonoRepo telefonoRepo;

    public TelefonoServicioImpl(TelefonoRepo telefonoRepo) {
        this.telefonoRepo = telefonoRepo;
    }


    @Override
    public Telefono registrarTelefono(Telefono t) throws Exception {

        if(t.getTelefonoLugar().length() > 12){
            throw new Exception("No puede superar los 12 caracteres");
        }

        return telefonoRepo.save(t);
    }

    @Override
    public void actualizarTelefono(Telefono t, int codigoTelefono) throws Exception {

        Telefono telefono = obtenerTelefono(codigoTelefono);

        if (telefono != null){

            telefono.setTelefonoLugar(t.getTelefonoLugar());

            telefonoRepo.save(telefono);
        }
    }

    @Override
    public void eliminarTelefono(int id) throws Exception {

        Telefono telefonoEcncontrado = obtenerTelefono(id);

        if(telefonoEcncontrado != null){
            telefonoRepo.delete(telefonoEcncontrado);
        } else {
            throw new Exception("No se encontro el telefono");
        }
    }

    @Override
    public Telefono obtenerTelefono(int id) throws Exception {

        Optional<Telefono> telefono = telefonoRepo.findById(id);

        if(telefono.isEmpty()){
            throw new Exception("No se encontro el telefono");
        }

        return telefono.get();
    }

    @Override
    public List<Telefono> listarTelefonos() {
        return telefonoRepo.findAll();
    }
}
