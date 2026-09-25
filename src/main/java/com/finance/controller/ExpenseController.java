package com.finance.controller;

import com.finance.model.Expense;
import com.finance.service.ExpenseService;

public class ExpenseController {

    private ExpenseService expenseService = new ExpenseService();

    public boolean addExpense(Expense expense) {
        return expenseService.addExpense(expense);
    }

    public void viewExpense(int userId) {
        expenseService.viewExpense(userId);
    }
}