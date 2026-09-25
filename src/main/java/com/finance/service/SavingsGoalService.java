package com.finance.service;

import com.finance.dao.SavingsGoalDAO;
import com.finance.model.SavingsGoal;

public class SavingsGoalService {

    private SavingsGoalDAO savingsGoalDAO = new SavingsGoalDAO();

    public boolean addGoal(SavingsGoal goal) {
        return savingsGoalDAO.addGoal(goal);
    }
}