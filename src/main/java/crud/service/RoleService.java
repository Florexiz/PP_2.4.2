package crud.service;

import crud.model.Role;

import java.util.List;

public interface RoleService {

    Role getOrCreateRole(String name);

    List<Role> getRoles();
}
