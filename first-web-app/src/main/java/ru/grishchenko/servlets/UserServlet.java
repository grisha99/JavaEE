package ru.grishchenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.User;
import ru.grishchenko.repositories.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/users/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");

        if (userRepository == null) {
            throw new ServletException("Error get userRepository from context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("pageHeader", "Список продуктов");
//        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
//        getServletContext().getRequestDispatcher("/navigation").include(req, resp);

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
//                resp.setStatus(400);
                resp.sendError(400);
                logger.info(ex.getMessage());
                return;
            }
            User user = userRepository.findById(id);
            if (user == null) {
//                resp.setStatus(404);
                resp.sendError(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_edit_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/add")) {
            User user = new User();
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_edit_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
//                resp.setStatus(400);
                resp.sendError(400);
                logger.info(ex.getMessage());
                return;
            }
            userRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        if (req.getParameter("id").equals("")) {    // новый пользователь, id еще нет
            id = null;
        } else {
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
//                resp.setStatus(400);
                resp.sendError(400);
                logger.info(ex.getMessage());
                return;
            }
        }
        User user = new User(id, req.getParameter("alias"), req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
        userRepository.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/users");
    }
}
