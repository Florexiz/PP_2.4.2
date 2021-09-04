package crud.service;

import crud.dao.UserDAO;
import crud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }

    @Override
    public void addUser(User user, String[] roles) {
        user.setRoles(new HashSet<>());
        for (String role : roles) {
            user.addRole(roleService.getOrCreateRole(role));
        }
        addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        User oldUser = getUser(user.getId());
        if (user.getPassword().equals("")) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDAO.editUser(user);
    }

    @Override
    public void editUser(User user, String[] roles) {
        user.setRoles(new HashSet<>());
        for (String role : roles) {
            user.addRole(roleService.getOrCreateRole(role));
        }
        editUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUser(username);
    }
}
