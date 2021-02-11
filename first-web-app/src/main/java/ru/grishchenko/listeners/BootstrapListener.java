package ru.grishchenko.listeners;

import ru.grishchenko.entity.Product;
import ru.grishchenko.repositories.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Молоко", "Молоко 3.2%", new BigDecimal(82.50)));
        productRepository.saveOrUpdate(new Product(null, "Хлеб", "Батон нарезной", new BigDecimal(32.00)));
        productRepository.saveOrUpdate(new Product(null, "Соль", "Соль пищевая", new BigDecimal(22.30)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
