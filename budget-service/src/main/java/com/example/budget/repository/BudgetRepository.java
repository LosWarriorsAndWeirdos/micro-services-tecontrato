package com.example.budget.repository;

import com.example.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    public List<Budget> findByDate(String date);
}