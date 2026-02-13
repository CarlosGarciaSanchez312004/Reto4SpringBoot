package org.example.reto4springboot.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un museo.
 */
public class MuseoNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción.
     *
     * @param message Mensaje descriptivo del error.
     */
    public MuseoNotFoundException(String message) {
        super(message);
    }
}
