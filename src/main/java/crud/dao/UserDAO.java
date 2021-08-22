package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDAO {

    User getUser(Long id);

    User getUser(String email);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);
}
