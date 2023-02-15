////package com.codeup.adlister.controllers;
////
////import com.codeup.adlister.dao.DaoFactory;
////
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////
////@WebServlet(name = "controllers.AdsByDescriptionServlet", urlPatterns = "/ads/description")
////public class AdsByDescriptionServlet extends HttpServlet {
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        String adDescription = request.getParameter("description");
////        request.setAttribute("description", DaoFactory.getAdsDao().getAdsByDescription(adDescription));
////        request.setAttribute("categories",DaoFactory.getCategoriesDao().all());
////        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());
////        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
////    }
////}
//
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
//        request.setAttribute("descriptionSearchTerm", adDescription);
//        request.setAttribute("ads", DaoFactory.getAdsDao().getAdsByDescription(adDescription));
//        request.setAttribute("categories",DaoFactory.getCategoriesDao().all());
//        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());
//        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//    }
//}
//
package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.AdsByDescriptionServlet", urlPatterns = "/ads/description")
public class AdsByDescriptionServlet extends HttpServlet {
    //immutable constant
    private static final String REDIRECT_JSP_PATH = "/WEB-INF/redirect.jsp";
    private static String INDEX_JSP = "/WEB-INF/ads/index.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adDescription = request.getParameter("description");
        request.setAttribute("descriptionSearchTerm", adDescription);
        request.setAttribute("ads", DaoFactory.getAdsDao().getAdsByDescription(adDescription));
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());

        //if ads are misspelled or null, it dispatches the urlDestination
        if (request.getAttribute("ads") == null || ((List) request.getAttribute("ads")).isEmpty()) {
            INDEX_JSP = REDIRECT_JSP_PATH;
        }
        request.getRequestDispatcher(INDEX_JSP).forward(request, response);
    }
}
