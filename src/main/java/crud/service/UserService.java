package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);
}
