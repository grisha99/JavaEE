package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.Category;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    private final static Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (getCategoryCount() == 0) {
            try {
                userTransaction.begin();

                saveOrUpdate(new Category(null, "Продукты", "Продукты питания"));
                saveOrUpdate(new Category(null, "Бытовая техника", "Техника для дома"));
                saveOrUpdate(new Category(null, "Мебель", "Спальни, прихожые и тд..."));

                userTransaction.commit();
            } catch (Exception e) {
                logger.error("", e);
                userTransaction.rollback();
            }

        }
    }

    public List<Category> findAll() {
        return entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    public Long getCategoryCount() {
        return entityManager.createNamedQuery("getCategoryCount", Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            entityManager.persist(category);
        }
        entityManager.merge(category);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("Category.deleteById", Category.class)
                .setParameter("id", id)
                .executeUpdate();
    }
}
