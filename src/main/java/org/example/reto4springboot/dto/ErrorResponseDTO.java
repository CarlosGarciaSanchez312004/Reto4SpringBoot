package org.example.reto4springboot.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponseDTO {

    private String error;
    private String message;
    Integer errorCode;

    public ErrorResponseDTO(String message, String error, int status) {
        this.message = message;
        this.error = error;
    }
}
