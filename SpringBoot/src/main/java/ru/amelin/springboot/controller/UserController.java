package ru.amelin.springboot.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;
import ru.amelin.springboot.service.UserService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final HttpServletResponse response;

    public UserController(UserService userService, HttpServletResponse response) {
        this.userService = userService;
        this.response = response;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "users";
    }

    @GetMapping("admin/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("my_role") String my_role) throws IOException {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(userService.getRoleByName(my_role));
            roles.add(userService.getRoleByName("USER"));
            user.setRoles(roles);
            userService.addUser(user);
        } catch (DataAccessException e) {
            response.sendError(400, "Role does not exist");
            return "users";
        }
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/update")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "update";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
