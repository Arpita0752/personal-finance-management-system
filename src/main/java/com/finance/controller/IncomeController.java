package com.finance.controller;

import com.finance.model.Income;
import com.finance.service.IncomeService;

public class IncomeController {

    private IncomeService incomeService = new IncomeService();

    public boolean addIncome(Income income) {
        return incomeService.addIncome(income);
    }

    public void viewIncome(int userId) {
        incomeService.viewIncome(userId);
    }
}