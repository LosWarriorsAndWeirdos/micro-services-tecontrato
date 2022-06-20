package com.example.budget.service.impl;

import com.example.budget.entity.Budget;
import com.example.budget.repository.BudgetRepository;
import com.example.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    @Override
    public List<Budget> ListAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget getBudget(long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    @Override
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public Budget updateBudget(Budget budget) {
        Budget budgetDB = getBudget(budget.getId());
        if(budgetDB==null)
        {
            return null;
        }

        budgetDB.setDate(budget.getDate());
        budgetDB.setDescription(budget.getDescription());
        budgetDB.setMonto(budget.getMonto());

        return budgetRepository.save(budgetDB);
    }

    @Override
    public Budget deleteBudget(long id) {
        Budget budgetDB = getBudget(id);
        if (budgetDB==null)
        {
            return null;
        }
        return budgetRepository.save(budgetDB);

    }
}
