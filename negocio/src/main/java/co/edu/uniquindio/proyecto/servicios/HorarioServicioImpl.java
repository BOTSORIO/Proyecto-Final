package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HorarioServicioImpl implements  HorarioServicio{

   private final HorarioRepo horarioRepo;

    public HorarioServicioImpl(HorarioRepo horarioRepo) {
        this.horarioRepo = horarioRepo;
    }

    @Override
    public Horario registrarHorario(Horario h) throws Exception {

       if(h.getDiaSemana().length() > 100){
           throw new Exception("No se puede exceder los 100 caracteres");
       }

        if(h.getHoraInicio().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if(h.getHoraFin().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        return horarioRepo.save(h);
    }

    @Override
    public Horario actualizarHorario(Horario h) throws Exception {

        if(h.getDiaSemana().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if(h.getHoraInicio().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        if(h.getHoraFin().length() > 100){
            throw new Exception("No se puede exceder los 100 caracteres");
        }

        return horarioRepo.save(h);
    }

    @Override
    public void eliminarHorario(int id) throws Exception {

        Horario horarioEncontrado  = obtenerHorario(id);

        if (horarioEncontrado != null) {

            horarioRepo.delete(horarioEncontrado);
        }else {
            throw  new Exception("No se encontro el horario");
        }
    }

    @Override
    public Horario obtenerHorario(int id) throws Exception {

       Optional<Horario> horario = horarioRepo.findById(id);

       if(horario.isEmpty()){
           throw new Exception("No se encontro el horario");
       }
        return horario.get();
    }

    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }
}
