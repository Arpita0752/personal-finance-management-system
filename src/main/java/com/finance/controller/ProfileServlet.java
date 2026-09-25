package com.finance.controller;

import java.io.IOException;

import com.finance.dao.ProfileDAO;
import com.finance.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ProfileDAO profileDAO = new ProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User sessionUser = (User) session.getAttribute("user");

        User user = profileDAO.getUserById(sessionUser.getUserId());

        request.setAttribute("user", user);

        request.getRequestDispatcher("profile.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User sessionUser = (User) session.getAttribute("user");

        String fullName = request.getParameter("fullName");
        String mobile = request.getParameter("mobile");
        String dob = request.getParameter("dateOfBirth");

        User user = new User();

        user.setUserId(sessionUser.getUserId());
        user.setFullName(fullName);
        user.setMobile(mobile);

        if (dob != null && !dob.isEmpty()) {
            user.setDateOfBirth(java.sql.Date.valueOf(dob));
        }

        boolean result = profileDAO.updateProfile(user);

        if (result) {

            // Updated user data session मध्ये ठेवणे
            User updatedUser = profileDAO.getUserById(sessionUser.getUserId());
            session.setAttribute("user", updatedUser);

            response.sendRedirect("profile");

        } else {

            response.setContentType("text/html");
            response.getWriter().println("<h2>Profile Update Failed!</h2>");
            response.getWriter().println("<a href='profile'>Back to Profile</a>");
        }
    }
}