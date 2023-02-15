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

@WebServlet(name="AdEditServlet", urlPatterns = "/ads/edit")
public class AdEditServlet extends HttpServlet {
    //immutable constants
    private static final String URL_PATTERN = "/ads/edit";
    private static final String EDIT_JSP_PATH = "/WEB-INF/ads/edit.jsp";
    private static final String REDIRECT_PATH = "/redirect";
    private static final String PROFILE_PATH = "/profile";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String query = request.getQueryString();
            Long id = Long.valueOf(query.substring(3));
            //Use Dao to get current ad

            Ad currentAd = DaoFactory.getAdsDao().singleAd(id);

            //Set this sessions attribute to current ad
            request.getSession().setAttribute("ad", currentAd);

            request.getRequestDispatcher(EDIT_JSP_PATH).forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(REDIRECT_PATH);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        Long id = Long.parseLong(request.getParameter("id"));
        // validate input
        boolean inputHasErrors = title.isEmpty()
                || description.isEmpty();

        if (inputHasErrors){
            response.sendRedirect(URL_PATTERN);
            return;
        }

        try {
            //We chained our constructors
            Ad ad = new Ad(id, title, description, image);

            //Create update method in DAO
            DaoFactory.getAdsDao().updateAd(ad);
            request.getSession().setAttribute("ad", ad);
            response.sendRedirect(PROFILE_PATH);
        }catch (Exception e){
            response.sendRedirect(PROFILE_PATH);
        }
    }
}

