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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String query = request.getQueryString();
            Long id = Long.valueOf(query.substring(3));
            //Use Dao to get current ad

            Ad currentAd = DaoFactory.getAdsDao().singleAd(id);

            //Set this sessions attribute to current ad
            request.getSession().setAttribute("ad", currentAd);

            request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/redirect");
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
            response.sendRedirect("/ads/edit");
            return;
        }

        try {
            Ad ad = new Ad(id, title, description, image);
            //Create update method in DAO
            DaoFactory.getAdsDao().updateAd(ad);
            request.getSession().setAttribute("ad", ad);
            response.sendRedirect("/profile");
        }catch (Exception e){
            response.sendRedirect("/profile");
        }
    }
}

