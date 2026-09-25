package com.finance.controller;

import java.io.IOException;
import java.sql.Date;

import com.finance.dao.GoalContributionDAO;
import com.finance.model.GoalContribution;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addContribution")
public class AddContributionServlet extends HttpServlet {

    private GoalContributionDAO contributionDAO = new GoalContributionDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        int goalId = Integer.parseInt(request.getParameter("goalId"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date contributionDate = Date.valueOf(request.getParameter("contributionDate"));
        String description = request.getParameter("description");

        GoalContribution contribution = new GoalContribution();

        contribution.setGoalId(goalId);
        contribution.setAmount(amount);
        contribution.setContributionDate(contributionDate);
        contribution.setDescription(description);

        boolean result = contributionDAO.addContribution(contribution);

        response.setContentType("text/html");
        response.getWriter().println("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Contribution Added</title>
                <style>
                    body { margin:0; font-family:Arial,sans-serif; background:#f4f6f8; }
                    .navbar { background:#1f2937; color:white; padding:18px 40px; font-size:22px; font-weight:bold; }
                    .card {
                        width:420px; margin:100px auto; background:white; padding:40px;
                        text-align:center; border-radius:12px;
                        box-shadow:0 4px 15px rgba(0,0,0,0.1);
                    }
                    h2 { color:#16a34a; }
                    .btn {
                        display:inline-block; margin:10px 5px; padding:12px 20px;
                        text-decoration:none; border-radius:6px; color:white; background:#2563eb;
                    }
                    .btn.secondary { background:#6b7280; }
                </style>
            </head>
            <body>
                <div class="navbar">Personal Finance Management</div>
                <div class="card">
                    <h2>✓ Contribution Added Successfully!</h2>
                    <p>Your savings contribution has been recorded successfully.</p>
                    <a class="btn" href="dashboard">Go to Dashboard</a>
                    <a class="btn secondary" href="add-contribution.html">Add Another</a>
                </div>
            </body>
            </html>
            """);
    }
}