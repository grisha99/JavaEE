package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class ProductRepository {

    private final static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

//    @Inject
//    private CategoryRepository categoryRepository;

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

//    @Resource
//    private UserTransaction userTransaction;
//
//    @PostConstruct
//    public void init() throws SystemException {
//
//        if (getProductsCount() == 0) {
//            try {
//                userTransaction.begin();
//
//                saveOrUpdate(new Product(null, "Молоко", "Молоко 3.2%", new BigDecimal(82.50), categoryRepository.findById(1L)));
//                saveOrUpdate(new Product(null, "Хлеб", "Батон нарезной", new BigDecimal(32.00), categoryRepository.findById(1L)));
//                saveOrUpdate(new Product(null, "Соль", "Соль пищевая", new BigDecimal(22.30), categoryRepository.findById(1L)));
//
//                userTransaction.commit();
//            } catch (Exception e) {
//                logger.error("", e);
//                userTransaction.rollback();
//            }
//        }
//    }

    public List<Product> findAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Long getProductsCount() {
        return entityManager.createNamedQuery("getProductsCount", Long.class).getSingleResult();
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

//    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

//    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("Product.deleteById").setParameter("id", id).executeUpdate();
    }
}
