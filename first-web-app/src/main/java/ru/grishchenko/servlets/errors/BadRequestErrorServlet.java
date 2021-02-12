package ru.grishchenko.servlets.errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/400")
public class BadRequestErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "Неверный запрос");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("Перейдите на <a href=\"/" + req.getContextPath() + "/main\">главную</a> страницу.");
    }
}
