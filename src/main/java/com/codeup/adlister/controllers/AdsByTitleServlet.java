package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsByTitleServlet", urlPatterns = "/ads/title")
public class AdsByTitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adTitle = request.getParameter("title");
        request.setAttribute("title", DaoFactory.getAdsDao().getAdsByTitle(adTitle));
        request.setAttribute("categories",DaoFactory.getCategoriesDao().all());
        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());

        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);

    }

}
