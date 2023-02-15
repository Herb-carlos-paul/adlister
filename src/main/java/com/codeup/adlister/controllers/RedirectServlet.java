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

@WebServlet(name = "controllers.RedirectServlet", urlPatterns = "/redirect")
public class RedirectServlet extends HttpServlet {
    //final and private allows for immutability
    private static final String REDIRECT_JSP_PATH = "/WEB-INF/redirect.jsp";
    private static final String PROFILE_PATH = "/profile";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REDIRECT_JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adTitle = req.getParameter("title");

        if (adTitle == null) {
            // Handle empty search term
            resp.sendRedirect(PROFILE_PATH);
            return;
        }

        List<Ad> ads = DaoFactory.getAdsDao().getAdsByTitle(adTitle);

        if (ads.isEmpty()) {
            // If the search result not found
            req.setAttribute("message", "No ads found for the given search term.");
            req.getRequestDispatcher(REDIRECT_JSP_PATH).forward(req, resp);
        } else {
            // Redirect to the ads index page with search results
            req.setAttribute("title", ads);
            req.setAttribute("categories", DaoFactory.getCategoriesDao().all());
            req.setAttribute("categoriesDao", DaoFactory.getCategoriesDao());
            resp.sendRedirect("/ads");
        }
    }
}
