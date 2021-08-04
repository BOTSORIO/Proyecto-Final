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
    private final FavoritoRepo favoritoRepo;


    public LugarServicioImpl(LugarRepo lugarRepo, ComentarioRepo comentarioRepo, TelefonoRepo telefonoRepo, FavoritoRepo favoritoRepo) {
        this.lugarRepo = lugarRepo;
        this.comentarioRepo = comentarioRepo;
        this.telefonoRepo = telefonoRepo;
        this.favoritoRepo = favoritoRepo;
    }

    @Override
    public Lugar registrarLugar(Lugar l) throws Exception {


        l.setEstado(false);
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
    public List<Lugar> buscarLugares(String cadena) {
        return lugarRepo.busquedaLugaresTipoNombre(cadena);
    }

    @Override
    public List<Comentario> listarComentarios(Integer idLugar) {

        List<Comentario> comentarios = lugarRepo.obtenerComentariosLugarEspecifico(idLugar);

        return comentarios;
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

    @Override
    public void ingresarComentario(Comentario c, Lugar lugar,Persona persona) throws Exception {

        try {
            if (lugar != null && persona != null) {

                c.setFechaComentario(new Date());
                c.setLugar(lugar);
                c.setUsuario((Usuario) persona);
                comentarioRepo.save(c);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void marcarFavorito(Lugar lugar, Persona persona) throws Exception {

        Favorito favorito = new Favorito();


        if(lugar != null && persona != null){
                favorito.setLugar(lugar);
                favorito.setUsuario((Usuario)persona);
                favoritoRepo.save(favorito);
        }
    }

    @Override
    public void eliminarFavorito(Lugar lugar, Persona persona) throws Exception{

        Favorito favorito;

        if(lugar !=null && persona !=null){

            favorito= favoritoRepo.obtenerFavorito(lugar.getId(),persona.getId());

            favorito.setLugar(null);
            favorito.setUsuario(null);
            favoritoRepo.save(favorito);
            favoritoRepo.delete(favorito);
        }
    }

    @Override
    public int obtenerCalificacionPromedio(int idLugar) throws Exception {

        Integer calificacion=0;
        Lugar lugarEncontrado= lugarRepo.obtenerLugar(idLugar);

        if (lugarEncontrado!=null){

            calificacion = lugarRepo.obtenerCalificacion(lugarEncontrado.getId());


        }else{
            throw new Exception("El lugar no fue encontrado");
        }

        if(calificacion==null){

            return 0;
        }
        else{
            return calificacion;
        }

    }

    @Override
    public List<Lugar> obtenerLugaresPorTipo(String tipo){

        List<Lugar>lugares;

        lugares = lugarRepo.busquedaLugaresTipoNombre(tipo);

        return lugares;
    }

    @Override
    public List<Lugar> obtenerLugaresPorCiudad(String nombreCiudad){

        List<Lugar>lugares;

        lugares = lugarRepo.obtenerLugaresPorCiudad(nombreCiudad);

        return lugares;
    }

    @Override
    public Lugar obtenerLugarMejorCalificacion(){

        List<Lugar>lugares = listarLugares();
        Lugar lugarAux = new Lugar();
        int flag=0;

        for(Lugar l:lugares){

            try {
                int calificacionPromedio=obtenerCalificacionPromedio(l.getId());

                if(calificacionPromedio>flag){

                    flag=calificacionPromedio;
                    lugarAux = l;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return lugarAux;

    }

    @Override
    public List<Lugar> obtenerLugaresFavoritosUsuario(String idUsuario) {

        List<Lugar> lugares = lugarRepo.findAll();
        List<Lugar>lugaresFavoritosUsuario=new ArrayList<>();

        for (Lugar l:lugares){

            List<Favorito>favoritos = l.getFavoritos();

            for(Favorito f:favoritos){
                if(f.getUsuario().getId().equalsIgnoreCase(idUsuario)){

                    lugaresFavoritosUsuario.add(l);
                }

            }

        }
        return lugaresFavoritosUsuario;
    }

    @Override
    public List<Lugar> obtenerLugaresAbiertos(){

       List<Lugar> lugares = lugarRepo.findAll();
       List<Lugar> lugaresAbiertos= new ArrayList<>();

        String horaActual = new Date().toString().split(" ")[3].substring(0, 5);

       for (Lugar l:lugares){

           List<Horario> horarios = l.getHorarios();

           for (Horario h:horarios) {

               lugaresAbiertos = lugarRepo.obtenerLugaresAbiertos(h.getDiaSemana(),horaActual);
           }
       }
       return  lugaresAbiertos;
    }

}
