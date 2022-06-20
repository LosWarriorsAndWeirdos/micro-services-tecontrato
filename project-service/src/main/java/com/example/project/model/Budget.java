package com.example.project.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
public class Budget {
    private Long id;
    private String description;
    private Float monto;
    private String date;
}
