package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet(name = "AdsByTitleServlet", urlPatterns = AdsByTitleServlet.URL_PATTERN)
public class AdsByTitleServlet extends HttpServlet {
    //immutable constant fields
    public static final String URL_PATTERN = "/ads/title";
    private static final String REDIRECT_JSP_PATH = "/WEB-INF/redirect.jsp";
    private static final String INDEX_JSP_PATH = "/WEB-INF/ads/index.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adTitle = request.getParameter("title");

        if (adTitle == null || adTitle.trim().isEmpty()) {
            // do nothing if the search input is empty
            return;
        }

        List<Ad> ads = DaoFactory.getAdsDao().getAdsByTitle(adTitle);

        if (ads.isEmpty()) {
            request.getRequestDispatcher(REDIRECT_JSP_PATH).forward(request, response);
            return;
        }

        request.setAttribute("title", ads);
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());

        request.getRequestDispatcher(INDEX_JSP_PATH).forward(request, response);
    }
}
