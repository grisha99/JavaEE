package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.User;

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
public class UserRepository {

    private final static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @PostConstruct
    public void init() throws SystemException {
        if (getUsersCount() == 0) {
         try {
             userTransaction.begin();

             saveOrUpdate(new User(null, "John Dow", "john", "123", "john@mail.com"));
             saveOrUpdate(new User(null, "Bob Johnson", "bob", "123", "bob@mail.com"));
             saveOrUpdate(new User(null, "Dmitri Ivanov", "dima", "123", "dima@mail.com"));

             userTransaction.commit();
         } catch (Exception e) {
             logger.error("", e);
             userTransaction.rollback();
         }
        }
    }

    public List<User> findAll() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public Long getUsersCount() {
        return entityManager.createNamedQuery("getUsersCount", Long.class).getSingleResult();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }
        entityManager.merge(user);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createNamedQuery("User.deleteById", User.class).setParameter("id", id).executeUpdate();
    }

}
