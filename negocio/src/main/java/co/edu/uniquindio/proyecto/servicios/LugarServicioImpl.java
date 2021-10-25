package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LugarServicioImpl implements LugarServicio {

    private final MascotaRepo lugarRepo;
    private final ComentarioRepo comentarioRepo;
    private final TelefonoRepo telefonoRepo;
    private final FavoritoRepo favoritoRepo;


    public LugarServicioImpl(MascotaRepo lugarRepo, ComentarioRepo comentarioRepo, TelefonoRepo telefonoRepo, FavoritoRepo favoritoRepo) {
        this.lugarRepo = lugarRepo;
        this.comentarioRepo = comentarioRepo;
        this.telefonoRepo = telefonoRepo;
        this.favoritoRepo = favoritoRepo;
    }

    @Override
    public Mascota registrarLugar(Mascota l) throws Exception {


        l.setEstado(false);
        l.setFechaCreacion(new Date());

        return lugarRepo.save(l);
    }

    @Override
    public void actualizarLugar(Mascota l, int codigoLugar) throws Exception {

        Mascota lugar = obtenerLugar(codigoLugar);

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

        Mascota lugarEncontrado = obtenerLugar2(id);

        if(lugarEncontrado != null){

            lugarRepo.delete(lugarEncontrado);

        } else {
            throw  new Exception("No se encontro el lugar");
        }
    }

    @Override
    public void actualizarLugar(Mascota l) throws Exception{

        lugarRepo.save(l);

    }

    @Override
    public Mascota obtenerLugar(int id) throws Exception {

      Optional<Mascota> lugar = lugarRepo.findById(id);

      if(lugar.isEmpty()){
          throw  new Exception("No se encontro el lugar");
      }

        return lugar.get();
    }

    @Override
    public Mascota obtenerLugar2(int id) throws Exception {

        Mascota lugar = lugarRepo.obtenerLugar(id);

        if(lugar==null){
            throw  new Exception("No se encontro el lugar");
        }

        return lugar;
    }


    @Override
    public List<Mascota> listarLugares() {
        return lugarRepo.findAll();
    }

    @Override
    public List<Mascota> buscarLugares(String cadena) {
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
    public void ingresarComentario(Comentario c, Mascota lugar, Persona persona) throws Exception {

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
    public void marcarFavorito(Mascota lugar, Persona persona) throws Exception {

        Favorito favorito = new Favorito();


        if(lugar != null && persona != null){
                favorito.setLugar(lugar);
                favorito.setUsuario((Usuario)persona);
                favoritoRepo.save(favorito);
        }
    }

    @Override
    public void eliminarFavorito(Mascota lugar, Persona persona) throws Exception{

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
        Mascota lugarEncontrado= lugarRepo.obtenerLugar(idLugar);

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
    public List<Mascota> obtenerLugaresPorTipo(String tipo){

        List<Mascota>lugares;

        lugares = lugarRepo.busquedaLugaresTipoNombre(tipo);

        return lugares;
    }

    @Override
    public List<Mascota> obtenerLugaresPorCiudad(String nombreCiudad){

        List<Mascota>lugares;

        lugares = lugarRepo.obtenerLugaresPorCiudad(nombreCiudad);

        return lugares;
    }

    @Override
    public Mascota obtenerLugarMejorCalificacion(){

        List<Mascota>lugares = listarLugares();
        Mascota lugarAux = new Mascota();
        int flag=0;

        for(Mascota l:lugares){

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
    public List<Mascota> obtenerLugaresFavoritosUsuario(String idUsuario) {

        List<Mascota> lugares = lugarRepo.findAll();
        List<Mascota>lugaresFavoritosUsuario=new ArrayList<>();

        for (Mascota l:lugares){

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
    public List<Mascota> obtenerLugaresAbiertos(){

       String diaSemana="";
       List<Mascota> lugaresAbiertos = new ArrayList<>();

       String dia= new Date().toString().split(" ")[0];
       String horaActual = new Date().toString().split(" ")[3].substring(0, 5);

       switch (dia){

           case "Mon" :
               diaSemana = "Lunes";
               break;

           case "Tue":
               diaSemana = "Martes";
               break;

           case "Wed":
               diaSemana = "Miercoles";
               break;

           case "Thu":
               diaSemana = "Jueves";
               break;

           case "Fri":
               diaSemana = "Viernes";
               break;

           case "Sat":
               diaSemana = "Sabado";
               break;

           case "Sun":
               diaSemana = "Domingo";
               break;
       }

       if (!dia.equalsIgnoreCase("") && !horaActual.equalsIgnoreCase("")){

           lugaresAbiertos = lugarRepo.obtenerLugaresAbiertos(diaSemana,horaActual);
       }

       return  lugaresAbiertos;
    }

}
