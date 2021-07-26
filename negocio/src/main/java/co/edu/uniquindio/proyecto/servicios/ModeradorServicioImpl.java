package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModeradorServicioImpl implements ModeradorServicio{

    private final ModeradorRepo moderadorRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo) {
        this.moderadorRepo = moderadorRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Moderador> modEmail = moderadorRepo.findByEmail(email);

        return  modEmail.isPresent();
    }

    @Override
    public Moderador registrarModerador(Moderador m) throws Exception {

        if (m.getId().length()>10){
            throw new Exception("La cedula solo puede tener 10 caracteres");
        }

        if (m.getNickname().length()>100){
            throw new Exception("El nickname solo puede tener 100 caracteres compa");
        }

        if (m.getEmail().length()>100){
            throw new Exception("El correo solo puede tener 100 caracteres compa");
        }

        if (m.getNombre().length()>100){
            throw new Exception("El nombre solo puede tener 100 caracteres compa");
        }

        if (m.getPassword().length()>100){
            throw new Exception("La contraseña solo puede tener 100 caracteres compa");
        }

        Optional<Moderador> modNick= moderadorRepo.findByNickname(m.getNickname());
        if(modNick.isPresent()){
            throw new Exception("El moderador ya existe");
        }

        if(estaDisponible(m.getEmail())){
            throw new Exception("El moderador ya existe");
        }

        return moderadorRepo.save(m);
    }

    @Override
    public void actualizarModerador(Moderador m,String email,String password) throws Exception {

      Moderador moderadorObtenido = obtenerEmailPassword(email,password);

      if(moderadorObtenido != null){
          moderadorObtenido.setId(m.getId());
          moderadorObtenido.setNombre(m.getNombre());
          moderadorObtenido.setNickname(m.getNickname());
          moderadorObtenido.setEmail(m.getEmail());
          moderadorObtenido.setPassword(m.getPassword());
          moderadorObtenido.setAdministrador(m.getAdministrador());
          moderadorObtenido.setLugares(m.getLugares());

          moderadorRepo.save(moderadorObtenido);
      }
    }

    @Override
    public void eliminarModerador(String email,String password) throws Exception {

        Moderador moderadorEncontrado = obtenerEmailPassword(email,password);

        if (moderadorEncontrado != null){
            moderadorRepo.delete(moderadorEncontrado);
        } else {
            throw new Exception("El moderador no ha sido encontrado");
        }
    }

    @Override
    public Moderador obtenerModerador(String id) throws Exception {

        Optional<Moderador> moderador = moderadorRepo.findById(id);

        if(moderador.isEmpty()){
            throw new Exception("No existe un moderador con el id dado");
        }

        return moderador.get();
    }


    @Override
    public Moderador obtenerEmailPassword(String email, String password) throws Exception {

        Moderador moderador = moderadorRepo.findByEmailAndPassword(email,password);

        if(moderador == null){

            throw new Exception("¡Ups! No te hemos podido encontrar");
        }
        return moderador;
    }

    @Override
    public List<Moderador> listarModeradores() {

        return moderadorRepo.findAll();
    }
}
