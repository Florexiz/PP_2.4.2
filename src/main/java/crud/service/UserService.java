package crud.service;

import crud.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUser(Long id);

    List<User> getUsers();

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);
}
