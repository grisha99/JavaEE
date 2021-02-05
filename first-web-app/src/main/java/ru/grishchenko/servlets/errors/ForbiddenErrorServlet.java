package ru.grishchenko.servlets.errors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/403")
public class ForbiddenErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "Доступ запрещен");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

        resp.getWriter().println("Пожалуйста <a href=\"/" + req.getContextPath() + "/auth\">авторизуйтесь</a>");
    }
}
