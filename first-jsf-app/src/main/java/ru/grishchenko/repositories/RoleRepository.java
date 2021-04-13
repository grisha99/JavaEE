package ru.grishchenko.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.entity.Role;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

    private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager entityManager;

    @TransactionAttribute
    public Role merge(Role role) {
        return entityManager.merge(role);
    }

    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public Long getCount() {
        return entityManager.createQuery("select count(*) from Role", Long.class).getSingleResult();
    }
}
