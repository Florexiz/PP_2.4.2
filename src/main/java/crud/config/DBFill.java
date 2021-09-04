package crud.config;

import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DBFill {

    private final UserService userService;
    private final RoleService roleService;

    public DBFill(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void generateUsers() {
        for (int i = 1; i <= 20; i++) {
            userService.addUser(new User(
                    "Name" + i,
                    "Surname" + i,
                    "City" + i,
                    "email" + i + "@mail.com",
                    "PASSWORD" + i,
                    new HashSet<>(Collections.singletonList(
                            roleService.getOrCreateRole("ROLE_USER")
                    ))
            ));
        }

        userService.addUser(new User(
                "AName",
                "ASurname",
                "ACity",
                "admin@mail.com",
                "ADMIN",
                new HashSet<>(Arrays.asList(
                        roleService.getOrCreateRole("ROLE_USER"),
                        roleService.getOrCreateRole("ROLE_ADMIN")
                ))
        ));
    }
}
