package com.example.project.client;

import com.example.project.model.Propietario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PropietarioHystrixFallbackFactory implements PropietarioClient {
    @Override
    public ResponseEntity<Propietario> getPropietario(long id) {
        Propietario propietario = Propietario.builder()
                .name("none")
                .lastname("none")
                .address("none")
                .password("none")
                .email("none")
                .dni(123L).build();
        return ResponseEntity.ok(propietario);
    }
}