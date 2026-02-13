package org.example.reto4springboot.repositories;

import org.example.reto4springboot.entities.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad UserDB.
 * Permite realizar operaciones CRUD y consultas personalizadas sobre la colección de usuarios.
 */
@Repository
public interface UserRepository extends MongoRepository<UserDB, String> {

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email Correo electrónico del usuario.
     * @return Un Optional con el usuario si se encuentra.
     */
    Optional<UserDB> findUserDBByEmail(String email);
}
