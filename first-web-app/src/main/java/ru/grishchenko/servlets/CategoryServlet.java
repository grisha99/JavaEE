package ru.grishchenko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.Category;
import ru.grishchenko.repositories.CategoryRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/categories/*")
public class CategoryServlet extends HttpServlet {

    private CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryServlet.class);

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");

        if (categoryRepository == null) {
            throw new ServletException("Error get categoryRepository from context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("pageHeader", "Список продуктов");
//        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
//        getServletContext().getRequestDispatcher("/navigation").include(req, resp);

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("categories", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(req, resp);
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
            Category category = categoryRepository.findById(id);
            if (category == null) {
//                resp.setStatus(404);
                resp.sendError(404);
                return;
            }
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_edit_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/add")) {
            Category category = new Category();
            req.setAttribute("category", category);
            getServletContext().getRequestDispatcher("/WEB-INF/category_edit_form.jsp").forward(req, resp);
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
            categoryRepository.deleteById(id);
            resp.sendRedirect(getServletContext().getContextPath() + "/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        if (req.getParameter("id").equals("")) {    // новый продукт, id еще нет
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
        Category category = new Category(id, req.getParameter("title"), req.getParameter("description"));
        categoryRepository.saveOrUpdate(category);
        resp.sendRedirect(getServletContext().getContextPath() + "/categories");
    }
}
