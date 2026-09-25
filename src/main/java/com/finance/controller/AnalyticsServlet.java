package com.finance.controller;

import java.io.IOException;

import com.finance.dao.AnalyticsDAO;
import com.finance.model.Analytics;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/analytics")
public class AnalyticsServlet extends HttpServlet {

    private AnalyticsDAO analyticsDAO = new AnalyticsDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        Analytics analytics =
                analyticsDAO.getAnalytics(user.getUserId());

        request.setAttribute("analytics", analytics);

        request.getRequestDispatcher("analytics.jsp")
               .forward(request, response);
    }
}