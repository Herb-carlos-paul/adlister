
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

    private static final String CREATE_JSP_PATH = "/WEB-INF/ads/create.jsp";
    private static final String LOGIN_PATH = "/login";
    private static final String ADS_PATH = "/ads";
    private static final String CREATE_ADS_PATH = "/ads/create";
    private static final String DEFAULT_IMAGE_PATH = "/img/default.jpeg";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("last-page", "CREATE_ADS_PATH");
            response.sendRedirect(LOGIN_PATH);
            return;
        }
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher(CREATE_JSP_PATH).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        // Set default image if an image is not submitted
        if (image == null || image.isEmpty()) {
            image = DEFAULT_IMAGE_PATH;
        }

        boolean inputHasErrors = title.isEmpty() || description.isEmpty();
        if (inputHasErrors) {
            response.sendRedirect(CREATE_ADS_PATH);
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
            response.sendRedirect(ADS_PATH);
        } catch (RuntimeException e) {
            request.getRequestDispatcher(CREATE_JSP_PATH).forward(request, response);
        }
    }
}
