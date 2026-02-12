package org.example.reto4springboot.exceptions;

public class MuseoNotFoundException extends RuntimeException {
    public MuseoNotFoundException(String message) {
        super(message);
    }
}
