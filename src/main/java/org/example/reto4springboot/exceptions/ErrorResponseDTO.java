package org.example.reto4springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // Esto crea el constructor con (String, String, int)
@NoArgsConstructor  // Esto crea el constructor vacío
public class ErrorResponseDTO {
    private String message;
    private String error;
    private int status;
}

