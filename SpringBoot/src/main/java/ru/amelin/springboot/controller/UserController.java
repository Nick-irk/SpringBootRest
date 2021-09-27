package ru.amelin.springboot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;
import ru.amelin.springboot.service.UserService;
import java.util.Set;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

        @GetMapping("/admin/table")
        public String getTable(Model model, HttpSession session) {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("users", userService.getAllUsers());
            return "table";
        }

        @PostMapping("/admin/table")
        public String addUser(String email, String password, String firstname, String lastname, int age, String roles) {
            Set<Role> roleSet = userService.getRoleForUser(roles);
            userService.addUser(new User(email, password, firstname, lastname, age, roleSet));
            return "redirect:/admin/table";
        }

        @PostMapping("/admin/remove")
        public String removeUser(int id) {
            userService.removeUser(id);
            return "redirect:/admin/table";
        }

        @PostMapping("/admin/update")
        public String updateUser(int id, String email, String password, String firstname, String lastname, int age, String roles) {
            Set<Role> roleSet = userService.getRoleForUser(roles);
            userService.updateUser(new User(id, email, password, firstname, lastname, age, roleSet));
            return "redirect:/admin/table";
        }

        @GetMapping(value = {"/", "/login"})
        public String loginPage() {
            return "login";
        }

        @GetMapping(value = "/user")
        public String userPage(Model model, HttpSession session) {
            model.addAttribute("user", session.getAttribute("user"));
            return "user";
        }

        @GetMapping(value = "/admin/newUser")
        public String newUser(Model model, HttpSession session) {
            model.addAttribute("user", session.getAttribute("user"));
            return "/newUser";
        }
}
