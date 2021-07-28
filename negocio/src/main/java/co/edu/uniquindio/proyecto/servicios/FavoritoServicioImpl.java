package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FavoritoServicioImpl implements FavoritoServicio {

    private  final FavoritoRepo favoritoRepo;

    public FavoritoServicioImpl(FavoritoRepo favoritoRepo) {
        this.favoritoRepo = favoritoRepo;
    }

    @Override
    public Favorito registrarFavorito(Favorito f) throws Exception {

        return favoritoRepo.save(f);
    }

    @Override
    public void actualizarFavorito(Favorito f,int codigoFavorito) throws Exception {

        Favorito favorito = obtenerFavorito(codigoFavorito);

        if (favorito != null) {


            favoritoRepo.save(favorito);
        }
    }

    @Override
    public void eliminarFavorito(int id) throws Exception {

        Favorito favoritoEncontrado = obtenerFavorito(id);

        if(favoritoEncontrado != null){
            favoritoRepo.delete(favoritoEncontrado);
        } else {
            throw  new Exception("No se ha encontrado el favorito");
        }
    }

    @Override
    public Favorito obtenerFavorito(int id) throws Exception {

        Optional<Favorito> favorito = favoritoRepo.findById(id);

        if(favorito.isEmpty()){
            throw new Exception("No se ha encontrado el favorito");
        }

        return favorito.get();
    }

    @Override
    public List<Favorito> listarFavoritos() {
        return favoritoRepo.findAll();
    }
}
