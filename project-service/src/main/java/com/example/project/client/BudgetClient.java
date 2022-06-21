package com.example.project.client;

import com.example.project.model.Budget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name= "budget-service", fallback = BudgetHystrixFallbackFactory.class)
public interface BudgetClient {
    @RequestMapping("/budgets/{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable("id") long id);
}
