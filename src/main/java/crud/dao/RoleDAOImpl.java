package crud.dao;

import crud.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRole(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name=?1", Role.class)
                .setParameter(1, name)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

}
