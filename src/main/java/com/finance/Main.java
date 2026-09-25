package com.finance;

import com.finance.controller.DashboardController;

public class Main {

    public static void main(String[] args) {

        int userId = 1;

        DashboardController dashboardController =
                new DashboardController();

        System.out.println("===== PERSONAL FINANCE DASHBOARD =====");

        System.out.println("Total Income  : ₹" +
                dashboardController.getTotalIncome(userId));

        System.out.println("Total Expense : ₹" +
                dashboardController.getTotalExpense(userId));

        System.out.println("Balance       : ₹" +
                dashboardController.getBalance(userId));

        System.out.println("\n===== CATEGORY WISE EXPENSE =====");
        dashboardController.getCategoryWiseExpense(userId);

        System.out.println("\n===== BUDGET UTILIZATION =====");
        dashboardController.getBudgetUtilization(userId, 9, 2026);

        System.out.println("\n===== SAVINGS GOAL PROGRESS =====");
        dashboardController.getSavingsGoalProgress(userId);

        System.out.println("\n===== MONTHLY EXPENSE COMPARISON =====");
        dashboardController.getMonthlyExpenseComparison(
                userId, 9, 2026);

        System.out.println("\n======================================");
    }
}