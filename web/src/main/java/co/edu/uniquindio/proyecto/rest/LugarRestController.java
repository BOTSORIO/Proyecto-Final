package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Mascota;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lugares")
public class LugarRestController {

    @Autowired
    private LugarServicio lugarServicio;


    @GetMapping
    public List<Mascota> listar(){

        return lugarServicio.listarLugares();
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Mascota lugar){

        try {
            lugarServicio.registrarLugar(lugar);
            return ResponseEntity.status(201).body(new Mensaje("El lugar se ha creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> actualizar(@RequestBody Mascota lugar, @PathVariable(name="id") Integer id){

        try {
            lugarServicio.actualizarLugar(lugar,id);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se actualizo correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable(name="id") Integer id){

        try {
            lugarServicio.eliminarLugar(id);
            return ResponseEntity.status(200).body(new Mensaje("El lugar se elimino correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable(name="id") Integer id){

        try {
            return ResponseEntity.status(200).body(lugarServicio.obtenerLugar2(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable(name="nombre") String nombre){

        try {
            return ResponseEntity.status(200).body(lugarServicio.buscarLugares(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/calificacion")
    public ResponseEntity<?> obtenerLugarMejorCalificacion(){

        try {
            return ResponseEntity.status(200).body(lugarServicio.obtenerLugarMejorCalificacion());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @GetMapping("/favoritosUsuario/{idUsuario}")
    public List<Mascota> listarFavoritosUsuario(@PathVariable(name="idUsuario") String id){

        return lugarServicio.obtenerLugaresFavoritosUsuario(id);
    }


}