package com.example.budget.service;

import com.example.budget.entity.Budget;

import java.util.List;

public interface BudgetService {
    public List<Budget> ListAllBudgets();
    public Budget getBudget(long id);
    public Budget createBudget(Budget budget);
    public Budget updateBudget(Budget budget);
    public Budget deleteBudget(long id);
}
