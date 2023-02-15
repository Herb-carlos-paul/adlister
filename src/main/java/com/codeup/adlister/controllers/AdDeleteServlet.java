package com.codeup.adlister.controllers;


import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete")
public class AdDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the query string from the URL, which should contain the ID
        String query = request.getQueryString();
        // Parse the ID from the query
        Long id = Long.valueOf(query.substring(3));
        // Delete the ad from the db.  Delete method found in MySQLAdsDao (see ads db for proof)
        DaoFactory.getAdsDao().delete(id);
        // Redirect the user to profile
        response.sendRedirect("/profile");
    }

}

