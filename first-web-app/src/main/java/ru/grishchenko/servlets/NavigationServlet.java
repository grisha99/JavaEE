package ru.grishchenko.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/navigation")
public class NavigationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<div class=\"header\">");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/main\">Главная</a>&nbsp&nbsp");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/catalog\">Каталог товаров</a>&nbsp&nbsp");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/products\">Продукт</a>&nbsp&nbsp");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/cart\">Корзина</a>&nbsp&nbsp");
        resp.getWriter().println("<a href=\"" + req.getContextPath() + "/order\">Заказы</a>&nbsp&nbsp");
        resp.getWriter().println("</div>");
    }
}
