package org.example.reto4springboot.controllers;


import org.example.reto4springboot.entities.Museo;
import org.example.reto4springboot.services.MuseoService;
import org.example.reto4springboot.exceptions.MuseoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/museos")
public class MuseoController {

    private final MuseoService museoService;

    public MuseoController(MuseoService museoService) {
        this.museoService = museoService;
    }

    @GetMapping
    public List<Museo> getAll() {
        return museoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Museo> getById(@PathVariable String id) {
        return museoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MuseoNotFoundException("No existe el museo con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Museo> create(@RequestBody Museo museo) {
        return new ResponseEntity<>(museoService.save(museo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if(museoService.findById(id).isEmpty()){
            throw new MuseoNotFoundException("No se puede borrar. ID no encontrado: " + id);
        }
        museoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
