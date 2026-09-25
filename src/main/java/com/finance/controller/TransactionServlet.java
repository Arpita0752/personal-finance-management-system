package com.finance.controller;

import java.io.IOException;
import java.util.List;

import com.finance.dao.TransactionDAO;
import com.finance.model.Transaction;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {

    private TransactionDAO transactionDAO = new TransactionDAO();

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

        int userId = user.getUserId();

        List<Transaction> transactions =
                transactionDAO.getTransactions(userId);

        request.setAttribute("transactions", transactions);

        request.getRequestDispatcher("transactions.jsp")
               .forward(request, response);
    }
}