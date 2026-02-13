package org.example.reto4springboot.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entidad que representa un usuario en la base de datos MongoDB.
 * Se utiliza para la autenticación y autorización en la aplicación.
 */
@Data
@Document(collection = "users")
public class UserDB {

    /**
     * Identificador único del usuario.
     */
    @Id
    private String _id;

    /**
     * Correo electrónico del usuario, utilizado como nombre de usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Indica si el usuario tiene privilegios de administrador.
     */
    private boolean admin;

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico.
     */
    public String getEmail() {
        return this.email;
    }
}
