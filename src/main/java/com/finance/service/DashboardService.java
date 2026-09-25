package com.finance.service;

import com.finance.dao.DashboardDAO;

public class DashboardService {

    private DashboardDAO dashboardDAO = new DashboardDAO();

    public double getTotalIncome(int userId) {
        return dashboardDAO.getTotalIncome(userId);
    }

    public double getTotalExpense(int userId) {
        return dashboardDAO.getTotalExpense(userId);
    }

    public double getBalance(int userId) {
        return getTotalIncome(userId) - getTotalExpense(userId);
    }
    
    public double getBudgetUsed(int userId) {
        return dashboardDAO.getBudgetUsed(userId);
    }
    
    public double getSavingsProgress(int userId) {
        return dashboardDAO.getSavingsProgress(userId);
    }
}