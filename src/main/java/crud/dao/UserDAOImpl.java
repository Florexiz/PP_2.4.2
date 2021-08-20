package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        entityManager.remove(getUser(id));
    }

    @Override
    @Transactional
    public void editUser(User user) {
        User u = getUser(user.getId());

        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
    }
}
