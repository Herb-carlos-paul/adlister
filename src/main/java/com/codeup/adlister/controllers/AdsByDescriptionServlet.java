//package com.codeup.adlister.controllers;
//
//import com.codeup.adlister.dao.DaoFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "controllers.AdsByDescriptionServlet", urlPatterns = "/ads/description")
//public class AdsByDescriptionServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String adDescription = request.getParameter("description");
//        request.setAttribute("description", DaoFactory.getAdsDao().getAdsByDescription(adDescription));
//        request.setAttribute("categories",DaoFactory.getCategoriesDao().all());
//        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());
//        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//    }
//}

package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsByDescriptionServlet", urlPatterns = "/ads/description")
public class AdsByDescriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adDescription = request.getParameter("description");
        request.setAttribute("descriptionSearchTerm", adDescription); // to preserve the search term
        request.setAttribute("ads", DaoFactory.getAdsDao().getAdsByDescription(adDescription));
        request.setAttribute("categories",DaoFactory.getCategoriesDao().all());
        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}

