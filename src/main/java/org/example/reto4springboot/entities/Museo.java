package org.example.reto4springboot.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Entidad que representa un museo en la base de datos MongoDB.
 * Mapea los campos del documento JSON a propiedades de Java.
 */
@Data
@Document(collection = "museos")
public class Museo {

    /**
     * Identificador único interno de MongoDB.
     */
    @Id
    private String _id;

    /**
     * Identificador de referencia externo (por ejemplo, de un dataset importado).
     */
    @Field("id")
    private Integer referenciaId;

    /**
     * Nombre del museo.
     */
    @Field("name")
    private String nombre;

    /**
     * Ubicación geográfica o coordenadas.
     */
    private String location;

    /**
     * Código postal.
     */
    private Integer postcode;

    /**
     * Municipio donde se encuentra el museo.
     */
    private String municipality;

    /**
     * Observaciones adicionales sobre el museo.
     */
    private String observations;

    /**
     * Dirección física del museo.
     */
    private String address;

    /**
     * Horario de apertura.
     */
    private String opening_hours;

    /**
     * Sitio web del museo.
     */
    private String web;

    /**
     * Provincia donde se ubica el museo.
     */
    private String province;

    /**
     * Número de teléfono de contacto.
     */
    private String phone;

    /**
     * Correo electrónico de contacto.
     */
    private String email;

    /**
     * Tipo de unidad o categoría del museo.
     */
    private String unit_type;
}
