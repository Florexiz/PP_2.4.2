package crud.service;

import crud.dao.RoleDAO;
import crud.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    public RoleServiceImpl (RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getOrCreateRole(String name) {
        Role role = roleDAO.getRole(name);
        if (role == null) {
            role = new Role(name);
            roleDAO.addRole(role);
        }
        return role;
    }
}
