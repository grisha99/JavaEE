package ru.grishchenko.listeners;

import ru.grishchenko.repositories.CategoryRepository;
import ru.grishchenko.repositories.ProductRepository;
import ru.grishchenko.repositories.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("productRepository", new ProductRepository());
        sce.getServletContext().setAttribute("categoryRepository", new CategoryRepository());
        sce.getServletContext().setAttribute("userRepository", new UserRepository());
    }
}
