package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileEditServlet", urlPatterns = "/profile/edit")
public class ProfileEditServlet extends HttpServlet {
    public static String error = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user")==null){
            //////////////////////
            //intended redirect//
            /////////////////////
            request.getSession().setAttribute("last-page", "/profile/edit");
            response.sendRedirect("/login");
            return;
        }
        request.getSession().getAttribute("user");
        request.getRequestDispatcher("/WEB-INF/profile/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Long id = Long.valueOf(request.getParameter("id"));
        //Split declaration to mitigate duplicate
        User uniqueUser;
        uniqueUser = DaoFactory.getUsersDao().findByUsername(username);


        boolean inputHasErrors = username.isEmpty()
                || uniqueUser != null
                || email.isEmpty()
                || password.isEmpty();

        if (inputHasErrors) {
            response.sendRedirect("/profile/edit");
            return;
        }

        try {
            User user = new User(id, username, email, password);
            DaoFactory.getUsersDao().update(user);
            request.getSession().setAttribute("user", user);
        }catch (Exception e) {

        }
        User updatedUser = DaoFactory.getUsersDao().findByUsername(username);
        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("user", updatedUser);
        response.sendRedirect("/profile");

    }
}