package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private final static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

//    @Inject
//    private CategoryRepository categoryRepository;

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public List<Product> findByName(String name) {
        return entityManager.createNamedQuery("getProductsByName", Product.class).setParameter("name", "%" + name + "%").getResultList();
    }

    public List<Product> findProductByCategoryId(Long id) {
        return entityManager.createNamedQuery("getProductsByCategoryId", Product.class).setParameter("id", id).getResultList();
    }

    public Long getProductsCount() {
        return entityManager.createNamedQuery("getProductsCount", Long.class).getSingleResult();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("Product.deleteById").setParameter("id", id).executeUpdate();
    }
}
