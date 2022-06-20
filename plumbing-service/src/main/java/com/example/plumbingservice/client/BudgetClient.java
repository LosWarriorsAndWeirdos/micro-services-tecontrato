package com.example.plumbingservice.client;

import com.example.plumbingservice.model.Budget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="budget-service", fallback = PropietarioHystrixFallbackFactory.class)
public interface BudgetClient {
    @GetMapping(value = "/{id}")
    public ResponseEntity<Budget> getProduct(@PathVariable("id") Long id);

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Budget> updatePayment(@PathVariable  Long id , @RequestParam(name = "money", required = true) Double quantity);
}
