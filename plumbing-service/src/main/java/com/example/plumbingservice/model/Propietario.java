package com.example.plumbingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Propietario {
    private Long id;
    private String name;
    private String lastname;
    private String address;
    private String password;
    private String email;
    private Long dni;
    private City city;
}
