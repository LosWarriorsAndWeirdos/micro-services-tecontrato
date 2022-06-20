package com.example.propietarioservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "propietarios")
@Data @NoArgsConstructor @AllArgsConstructor
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no debe estar vacío.")
    private String name;
    private String lastname;
    private String address;
    private String password;
    private String email;
    private Long dni;

    @NotNull(message = "La ciudad no puede estar vacía")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;
}
