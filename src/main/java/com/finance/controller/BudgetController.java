package com.finance.controller;

import com.finance.model.Budget;
import com.finance.service.BudgetService;

public class BudgetController {

    private BudgetService budgetService = new BudgetService();

    public boolean addBudget(Budget budget) {
        return budgetService.addBudget(budget);
    }
}