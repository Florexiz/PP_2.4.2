package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        for (int i = 1; i <= 20; i++) {
            userService.addUser(new User(
                    "Name" + i,
                    "Surname" + i,
                    "City" + i,
                    "email" + i + "@mail.com"
            ));
        }
    }

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PatchMapping("/edit")
    public String patchUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/";
    }
}
