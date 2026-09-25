package com.finance.controller;

import java.io.IOException;
import java.sql.Date;

import com.finance.dao.UserDAO;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String dob = request.getParameter("dateOfBirth");

        User user = new User();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setPassword(password);

        if (dob != null && !dob.isEmpty()) {
            user.setDateOfBirth(Date.valueOf(dob));
        }

        boolean result = userDAO.registerUser(user);

        response.setContentType("text/html");

        if (result) {
            response.getWriter().println("<h2>Registration Successful!</h2>");
        } else {
            response.getWriter().println("<h2>Registration Failed!</h2>");
        }
    }
}