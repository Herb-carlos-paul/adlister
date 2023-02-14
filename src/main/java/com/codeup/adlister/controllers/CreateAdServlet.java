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

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("last-page", "/ads/create");
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        // Set default image if an image is not submitted
        if (image == null || image.isEmpty()) {
            image = "/img/default.jpeg";
        }

        boolean inputHasErrors = title.isEmpty() || description.isEmpty();
        if (inputHasErrors) {
            response.sendRedirect("/ads/create");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        String[] categories = request.getParameterValues("category");

        Ad ad = new Ad(user.getId(), title, description, image);
        Long adId = DaoFactory.getAdsDao().insert(ad);
        try {
            for (String categoryId : categories) {
                DaoFactory.getAdsDao().linkAdToCategory(adId, Long.parseLong(categoryId));
            }
            response.sendRedirect("/ads");
        } catch (RuntimeException e) {
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
        }
    }
}
