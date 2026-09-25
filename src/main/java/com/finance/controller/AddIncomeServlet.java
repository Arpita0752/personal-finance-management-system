package com.finance.controller;

import java.io.IOException;
import java.sql.Date;

import com.finance.dao.IncomeDAO;
import com.finance.model.Income;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addIncome")
public class AddIncomeServlet extends HttpServlet {

    private IncomeDAO incomeDAO = new IncomeDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        int userId = user.getUserId();
        int sourceId = Integer.parseInt(request.getParameter("sourceId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date incomeDate = Date.valueOf(request.getParameter("incomeDate"));
        String incomeType = request.getParameter("incomeType");
        String description = request.getParameter("description");

        Income income = new Income();

        income.setUserId(userId);
        income.setSourceId(sourceId);
        income.setAmount(amount);
        income.setIncomeDate(incomeDate);
        income.setIncomeType(incomeType);
        income.setDescription(description);

        boolean result = incomeDAO.addIncome(income);

        response.setContentType("text/html");
        response.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Income Added</title>
                <style>
                    body {
                        margin: 0;
                        font-family: Arial, sans-serif;
                        background: #f4f6f8;
                    }
                    .navbar {
                        background: #1f2937;
                        color: white;
                        padding: 18px 40px;
                        font-size: 22px;
                        font-weight: bold;
                    }
                    .card {
                        width: 420px;
                        margin: 100px auto;
                        background: white;
                        padding: 40px;
                        text-align: center;
                        border-radius: 12px;
                        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
                    }
                    h2 { color: #16a34a; }
                    .btn {
                        display: inline-block;
                        margin: 10px 5px;
                        padding: 12px 20px;
                        text-decoration: none;
                        border-radius: 6px;
                        color: white;
                        background: #2563eb;
                    }
                    .btn.secondary {
                        background: #6b7280;
                    }
                </style>
            </head>
            <body>
                <div class="navbar">Personal Finance Management</div>
                <div class="card">
                    <h2>✓ Income Added Successfully!</h2>
                    <p>Your income has been recorded successfully.</p>
                    <a class="btn" href="dashboard">Go to Dashboard</a>
                    <a class="btn secondary" href="add-income.html">Add Another</a>
                </div>
            </body>
            </html>
            """);
    }
}