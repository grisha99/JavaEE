package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

//@Named
//@ApplicationScoped
@Stateless
public class UserRepository {

    private final static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

//    @Resource
//    private UserTransaction userTransaction;

//    @PostConstruct
//    public void init() throws SystemException {
//        if (getUsersCount() == 0) {
//         try {
//             userTransaction.begin();
//
//             saveOrUpdate(new User(null, "John Dow", "john", "123", "john@mail.com"));
//             saveOrUpdate(new User(null, "Bob Johnson", "bob", "123", "bob@mail.com"));
//             saveOrUpdate(new User(null, "Dmitri Ivanov", "dima", "123", "dima@mail.com"));
//
//             userTransaction.commit();
//         } catch (Exception e) {
//             logger.error("", e);
//             userTransaction.rollback();
//         }
//        }
//    }

    public List<User> findAll() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);
        from.fetch("roles", JoinType.LEFT);
        query.select(from).distinct(true);
        List<User> tmp = entityManager.createQuery(query).getResultList();
        return tmp;

//        return em.createQuery("select distinct u from User u left join fetch u.roles", User.class)
//                .getResultList();
//        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public Long getUsersCount() {
        return entityManager.createNamedQuery("getUsersCount", Long.class).getSingleResult();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        }
        return entityManager.merge(user);
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("User.deleteById").setParameter("id", id).executeUpdate();
    }

    public User getUserByUsername(String username) {
        return entityManager.createNamedQuery("User.getByName", User.class).setParameter("username", username).getSingleResult();
    }

}
