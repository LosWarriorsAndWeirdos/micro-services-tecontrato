package com.example.project.client;

import com.example.project.model.Budget;
import com.example.project.model.Propietario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BudgetHystrixFallbackFactory implements BudgetClient{
    @Override
    public ResponseEntity<Budget> getBudget(long id) {
        Budget budget = Budget.builder()
                .description("none")
                .monto(0f)
                .date("none")
                .build();
        return ResponseEntity.ok(budget);
    }
}
