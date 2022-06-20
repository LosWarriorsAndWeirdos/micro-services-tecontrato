package com.example.budget.controller;

import com.example.budget.entity.Budget;
import com.example.budget.service.BudgetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> listAllBudgets()
    {
        List<Budget> budgets = new ArrayList<>();
        budgets = budgetService.ListAllBudgets();
        if (budgets.isEmpty())
        {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(budgets);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable("id") Long id){
        log.info("Obteniendo el presupuesto con id {}", id);
        Budget budget = budgetService.getBudget(id);
        if (budget==null)
        {
            log.error("El presupuesto con id {} no existe", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(budget);
    }

    @PostMapping
    public ResponseEntity<Budget> createPropietario(@Valid @RequestBody Budget budget, BindingResult result)
    {
        log.info("Creando propietario {}", budget);
        if (result.hasErrors())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        Budget budgetDB = budgetService.createBudget(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable("id") long id, @RequestBody Budget budget)
    {
        log.info("Actualizando budget con id {}", id);
        Budget currentBudget = budgetService.getBudget(id);

        if (currentBudget == null)
        {
            log.error("No se puede actualizar. Budget con Id {} no fue encontrado", id);
            return ResponseEntity.notFound().build();
        }
        currentBudget = budgetService.updateBudget(budget);
        return ResponseEntity.ok(currentBudget);
    }

    private String formatMessage(BindingResult result)
    {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
