package ru.grishchenko.repositories;

import ru.grishchenko.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<Long, Product>();

    private final AtomicLong identity = new AtomicLong(0);

    public ProductRepository() {
        saveOrUpdate(new Product(null, "Молоко", "Молоко 3.2%", new BigDecimal(82.50)));
        saveOrUpdate(new Product(null, "Хлеб", "Батон нарезной", new BigDecimal(32.00)));
        saveOrUpdate(new Product(null, "Соль", "Соль пищевая", new BigDecimal(22.30)));
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(productMap.values());
    }

    public Product findById(Long id) {
        return productMap.get(id);
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
