package org.example.reto4springboot.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserDB {
    @Id
    private String _id;
    private String email;
    private String password;
    private boolean admin;

    // Escribimos los métodos manualmente para que el error desaparezca
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}