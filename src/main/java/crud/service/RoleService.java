package crud.service;

import crud.model.Role;

public interface RoleService {
    Role getOrCreateRole(String name);
}
