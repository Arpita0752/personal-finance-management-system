package com.finance.controller;

import com.finance.model.SavingsGoal;
import com.finance.service.SavingsGoalService;

public class SavingsGoalController {

    private SavingsGoalService savingsGoalService = new SavingsGoalService();

    public boolean addGoal(SavingsGoal goal) {
        return savingsGoalService.addGoal(goal);
    }
}