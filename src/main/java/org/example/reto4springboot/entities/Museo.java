package org.example.reto4springboot.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "museos")
public class Museo {

    @Id
    private String _id; // ID interno de MongoDB (hash)

    @Field("id")
    private Integer referenciaId; // El ID "2714" que viene en tu JSON

    @Field("name")
    private String nombre; // Mapeamos "name" del JSON a "nombre"

    private String location;
    private Integer postcode;
    private String municipality;
    private String observations;
    private String address;
    private String opening_hours;
    private String web;
    private String province;
    private String phone;
    private String email;
    private String unit_type;
}
