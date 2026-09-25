package com.finance.controller;

import java.io.IOException;

import com.finance.model.User;
import com.finance.service.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        int userId = user.getUserId();

        double totalIncome = dashboardService.getTotalIncome(userId);
        double totalExpense = dashboardService.getTotalExpense(userId);
        double balance = dashboardService.getBalance(userId);
        double budgetUsed = dashboardService.getBudgetUsed(userId);
        double savingsProgress = dashboardService.getSavingsProgress(userId);
        
        System.out.println("USER ID = " + userId);
        System.out.println("TOTAL INCOME = " + totalIncome);
        System.out.println("TOTAL EXPENSE = " + totalExpense);
        System.out.println("BALANCE = " + balance);

        request.setAttribute("userName", user.getFullName());
        request.setAttribute("totalIncome", totalIncome);
        request.setAttribute("totalExpense", totalExpense);
        request.setAttribute("balance", balance);
        request.setAttribute("budgetUsed", budgetUsed);
        request.setAttribute("savingsProgress", savingsProgress);

        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }
}