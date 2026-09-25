package com.finance.controller;

import com.finance.service.FinanceAnalyticsService;

public class DashboardController {

    private FinanceAnalyticsService analyticsService =
            new FinanceAnalyticsService();

    public double getTotalIncome(int userId) {
        return analyticsService.getTotalIncome(userId);
    }

    public double getTotalExpense(int userId) {
        return analyticsService.getTotalExpense(userId);
    }

    public double getBalance(int userId) {
        return analyticsService.getBalance(userId);
    }

    public void getCategoryWiseExpense(int userId) {
        analyticsService.getCategoryWiseExpense(userId);
    }

    public void getBudgetUtilization(int userId, int month, int year) {
        analyticsService.getBudgetUtilization(userId, month, year);
    }

    public void getSavingsGoalProgress(int userId) {
        analyticsService.getSavingsGoalProgress(userId);
    }

    public void getMonthlyExpenseComparison(
            int userId, int currentMonth, int currentYear) {

        analyticsService.getMonthlyExpenseComparison(
                userId, currentMonth, currentYear);
    }
}