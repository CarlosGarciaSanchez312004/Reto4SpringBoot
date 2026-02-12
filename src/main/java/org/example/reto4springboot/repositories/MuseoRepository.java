package org.example.reto4springboot.repositories;
import org.example.reto4springboot.entities.Museo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface MuseoRepository extends MongoRepository<Museo, String> {
    // Búsqueda personalizada estilo "findTeamByName"
    Optional<Museo> findByNombre(String nombre);
    List<Museo> findByMunicipality(String municipality);
}
