package com.finance.service;

import com.finance.dao.BudgetDAO;
import com.finance.model.Budget;

public class BudgetService {

    private BudgetDAO budgetDAO = new BudgetDAO();

    public boolean addBudget(Budget budget) {
        return budgetDAO.addBudget(budget);
    }
}