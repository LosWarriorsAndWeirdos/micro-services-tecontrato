package com.example.plumbingservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Budget {
    private Long id;

    private String description;
    private Float monto;
    private String date;
}
