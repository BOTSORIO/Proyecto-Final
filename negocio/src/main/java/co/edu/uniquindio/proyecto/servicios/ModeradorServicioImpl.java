package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModeradorServicioImpl implements ModeradorServicio{

    private final TrabajorRepo moderadorRepo;
    private final MascotaRepo lugarRepo;

    public ModeradorServicioImpl(TrabajorRepo moderadorRepo, MascotaRepo lugarRepo) {
        this.moderadorRepo = moderadorRepo;
        this.lugarRepo = lugarRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Trabajador> modEmail = moderadorRepo.findByEmail(email);

        return  modEmail.isPresent();
    }

    @Override
    public Trabajador registrarModerador(Trabajador m) throws Exception {

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

        Optional<Trabajador> modNick= moderadorRepo.findByNickname(m.getNickname());
        if(modNick.isPresent()){
            throw new Exception("El moderador ya existe");
        }

        if(estaDisponible(m.getEmail())){
            throw new Exception("El moderador ya existe");
        }

        return moderadorRepo.save(m);
    }

    @Override
    public void actualizarModerador(Trabajador m, String email, String password) throws Exception {

      Trabajador moderadorObtenido = obtenerEmailPassword(email,password);

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

        Trabajador moderadorEncontrado = obtenerEmailPassword(email,password);

        if (moderadorEncontrado != null){
            moderadorRepo.delete(moderadorEncontrado);
        } else {
            throw new Exception("El moderador no ha sido encontrado");
        }
    }

    @Override
    public Trabajador obtenerModerador(String id) throws Exception {

        Optional<Trabajador> moderador = moderadorRepo.findById(id);

        if(moderador.isEmpty()){
            throw new Exception("No existe un moderador con el id dado");
        }

        return moderador.get();
    }


    @Override
    public Trabajador obtenerEmailPassword(String email, String password) throws Exception {

        Trabajador moderador = moderadorRepo.findByEmailAndPassword(email,password);

        if(moderador == null){

            throw new Exception("¡Ups! No te hemos podido encontrar");
        }
        return moderador;
    }

    @Override
    public List<Mascota> obtenerLugaresAprobados(String email){

        List<Mascota> lugares = lugarRepo.obtenerLugaresAprobados(email);

        return lugares;
    }

    @Override
    public List<Mascota> obtenerTodosLugaresAprobados(){

        List<Mascota> lugares = lugarRepo.obtenerTodosLugaresAprobados();

        return lugares;
    }

    @Override
    public List<Mascota> obtenerLugaresSinAprobacion(){

        List<Mascota> lugares = lugarRepo.obtenerLugaresSinAprobacion();

        return lugares;
    }


    @Override
    public List<Trabajador> listarModeradores() {

        return moderadorRepo.findAll();
    }
}
