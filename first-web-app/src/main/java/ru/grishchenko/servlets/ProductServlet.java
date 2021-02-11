package ru.grishchenko.servlets;

import ru.grishchenko.entity.Product;
import ru.grishchenko.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");

        if (productRepository == null) {
            throw new ServletException("Error get productRepository from context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("pageHeader", "Список продуктов");
//        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
//        getServletContext().getRequestDispatcher("/navigation").include(req, resp);

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            Product product = productRepository.findById(id);
            if (product == null) {
//                resp.setStatus(404);
                resp.sendError(404);
                return;
            }
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_edit_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            // TODO delete product
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        Product product = new Product(id, req.getParameter("name"), req.getParameter("description"), price);
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/products");
    }
}
