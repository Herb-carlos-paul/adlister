package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_JSP_PATH = "/WEB-INF/login.jsp";
    private static final String PROFILE_PATH = "/profile";
    private static final String LOGIN_PATH = "/login";


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect(PROFILE_PATH);
            return;
        }
        request.getRequestDispatcher(LOGIN_JSP_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        if (user == null) {
            response.sendRedirect(LOGIN_PATH);
            return;
        }

        boolean validAttempt = Password.check(password, user.getPassword());
        String lastPage = (String) request.getSession().getAttribute("last-page");
        if (validAttempt) {
            request.getSession().setAttribute("user", user);
            if (lastPage != null) {
                response.sendRedirect(lastPage);
            } else {
                response.sendRedirect(PROFILE_PATH);
            }
        } else {
            // If the password is incorrect, redirect back to the login page
            response.sendRedirect(LOGIN_PATH);
        }
    }
}
