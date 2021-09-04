package crud.dao;

import crud.model.Role;

import java.util.List;

public interface RoleDAO {

    Role getRole(String name);

    List<Role> getRoles();

    void addRole(Role role);

}
