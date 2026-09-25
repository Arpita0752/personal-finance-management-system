package com.finance.service;

import com.finance.dao.ExpenseDAO;
import com.finance.model.Expense;

public class ExpenseService {

    private ExpenseDAO expenseDAO = new ExpenseDAO();

    public boolean addExpense(Expense expense) {
        return expenseDAO.addExpense(expense);
    }

    public void viewExpense(int userId) {
        expenseDAO.viewExpenseByUser(userId);
    }
}