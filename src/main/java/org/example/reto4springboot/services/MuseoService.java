package org.example.reto4springboot.services;
import org.example.reto4springboot.entities.Museo;
import org.example.reto4springboot.repositories.MuseoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MuseoService {

    private final MuseoRepository museoRepository;

    public MuseoService(MuseoRepository museoRepository) {
        this.museoRepository = museoRepository;
    }

    public List<Museo> findAll() {
        return museoRepository.findAll();
    }

    public Optional<Museo> findById(String id) {
        return museoRepository.findById(id);
    }

    public Museo save(Museo museo) {
        return museoRepository.save(museo);
    }

    public void deleteById(String id) {
        museoRepository.deleteById(id);
    }

    public Optional<Museo> findMuseoByName(String nombre) {
        return museoRepository.findByNombre(nombre);
    }
}
