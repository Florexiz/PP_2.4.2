package crud.controller;

import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/admin/new")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roles") String[] roles) {
        userService.addUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }
    @PutMapping("/admin/edit")
    public String patchUser(@ModelAttribute User user,
                            @RequestParam(value = "roles") String[] roles) {
        userService.editUser(user, roles);
        return "redirect:/admin";
    }
}