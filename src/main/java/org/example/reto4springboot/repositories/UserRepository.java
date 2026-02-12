package org.example.reto4springboot.repositories;

import org.example.reto4springboot.entities.UserDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDB, String> {
    Optional<UserDB> findUserDBByEmail(String email);
}