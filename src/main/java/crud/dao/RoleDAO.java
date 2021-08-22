package crud.dao;

import crud.model.Role;

public interface RoleDAO {

    Role getRole(String name);

    void addRole(Role role);

}
