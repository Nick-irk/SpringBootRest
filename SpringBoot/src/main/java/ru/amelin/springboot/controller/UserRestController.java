package ru.amelin.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;
import ru.amelin.springboot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/admin/addUser")
    public void addUser(String email, String password, String firstname, String lastname, int age, String roles) {
        Set<Role> roleSet = userService.getRoleForUser(roles);
        userService.addUser(new User(email, password, firstname, lastname, age, roleSet));
    }

    @DeleteMapping("/admin/remove")
    public void removeUser(int id) {
        userService.removeUser(id);
    }

    @PutMapping("/admin/update")
    public void updateUser(int id, String email, String password, String firstname, String lastname, int age, String roles) {
        Set<Role> roleSet = userService.getRoleForUser(roles);
        userService.updateUser(new User(id, email, password, firstname, lastname, age, roleSet));
    }

    @GetMapping(value = "/user/getUser")
    public ResponseEntity<List<User>> getUser(HttpSession session) {
        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}