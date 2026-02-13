package org.example.reto4springboot.controllers;


import org.example.reto4springboot.entities.Museo;
import org.example.reto4springboot.services.MuseoService;
import org.example.reto4springboot.exceptions.MuseoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los museos.
 * Proporciona endpoints para crear, leer, actualizar y eliminar museos,
 * así como consultas específicas por provincia y estadísticas.
 */
@RestController
@RequestMapping("/api/museos")
public class MuseoController {

    private final MuseoService museoService;

    /**
     * Constructor del controlador.
     *
     * @param museoService Servicio de lógica de negocio para museos.
     */
    public MuseoController(MuseoService museoService) {
        this.museoService = museoService;
    }

    /**
     * Obtiene la lista de todos los museos registrados.
     *
     * @return Lista de objetos Museo.
     */
    @GetMapping
    public List<Museo> getAll() {
        return museoService.findAll();
    }

    /**
     * Busca un museo por su identificador único.
     *
     * @param id Identificador del museo.
     * @return ResponseEntity con el museo encontrado o lanza una excepción si no existe.
     * @throws MuseoNotFoundException si no se encuentra el museo con el ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Museo> getById(@PathVariable String id) {
        return museoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MuseoNotFoundException("No existe el museo con ID: " + id));
    }

    /**
     * Crea un nuevo museo en el sistema.
     *
     * @param museo Objeto Museo con la información a guardar.
     * @return ResponseEntity con el museo creado y el estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Museo> create(@RequestBody Museo museo) {
        return new ResponseEntity<>(museoService.save(museo), HttpStatus.CREATED);
    }

    /**
     * Elimina un museo existente por su identificador.
     *
     * @param id Identificador del museo a eliminar.
     * @return ResponseEntity con contenido vacío (No Content) si la eliminación es exitosa.
     * @throws MuseoNotFoundException si no se encuentra el museo con el ID proporcionado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(museoService.findById(id).isEmpty()){
            throw new MuseoNotFoundException("No se puede borrar. ID no encontrado: " + id);
        }
        museoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca museos ubicados en una provincia específica.
     *
     * @param provincia Nombre de la provincia.
     * @return Lista de museos en la provincia indicada.
     */
    @GetMapping("/provincia/{provincia}")
    public List<Museo> getByProvince(@PathVariable String provincia) {
        return museoService.findByProvince(provincia);
    }

    /**
     * Busca un museo por su nombre.
     *
     * @param nombre Nombre del museo.
     * @return ResponseEntity con el museo encontrado.
     * @throws MuseoNotFoundException si no se encuentra un museo con ese nombre.
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Museo> getByName(@PathVariable String nombre) {
        return museoService.findMuseoByName(nombre)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MuseoNotFoundException("No se encontró el museo: " + nombre));
    }

    /**
     * Obtiene una lista de todas las provincias que tienen museos registrados.
     *
     * @return Lista de nombres de provincias.
     */
    @GetMapping("/provincias/lista")
    public List<String> getProvincesList() {
        return museoService.findAllProvinces();
    }

    /**
     * Obtiene estadísticas sobre la cantidad de museos por provincia.
     *
     * @return Mapa donde la clave es la provincia y el valor es el número de museos.
     */
    @GetMapping("/stats/provincias")
    public Map<String, Long> getStatsByProvince() {
        return museoService.countMuseosByProvince();
    }

    /**
     * Actualiza la información de un museo existente.
     *
     * @param id Identificador del museo a actualizar.
     * @param museo Objeto Museo con los nuevos datos.
     * @return ResponseEntity con el museo actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Museo> update(@PathVariable String id, @RequestBody Museo museo) {
        Museo museoActualizado = museoService.update(id, museo);
        return ResponseEntity.ok(museoActualizado);
    }
}
