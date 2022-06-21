package com.example.project.client;

import com.example.project.model.Contractor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ContractorHystrixFallbackFactory implements ContractorClient{
    @Override
    public ResponseEntity<Contractor> getContractor(long id) {
        Contractor contractor = Contractor.builder()
                .dni("none")
                .description("none")
                .build();
        return ResponseEntity.ok(contractor);
    }
}
