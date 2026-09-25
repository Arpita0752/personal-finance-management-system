package com.finance.service;

import com.finance.dao.IncomeDAO;
import com.finance.model.Income;

public class IncomeService {

    private IncomeDAO incomeDAO = new IncomeDAO();

    public boolean addIncome(Income income) {
        return incomeDAO.addIncome(income);
    }

    public void viewIncome(int userId) {
        incomeDAO.viewIncomeByUser(userId);
    }
}