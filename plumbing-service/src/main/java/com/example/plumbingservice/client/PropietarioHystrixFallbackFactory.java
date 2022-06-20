package com.example.plumbingservice.client;

import com.example.plumbingservice.model.Budget;
import com.example.plumbingservice.model.Propietario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PropietarioHystrixFallbackFactory implements PropietarioClient{
    @Override
    public ResponseEntity<Propietario> getPropietarios(long id) {
        Propietario propietario = Propietario.builder()
                .id(null)
                .name("none")
                .lastname("none").build();
        return ResponseEntity.ok(propietario);
    }
}
