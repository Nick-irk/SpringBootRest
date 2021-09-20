package ru.amelin.springboot.service;

import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUser();
    User getUser(int id);
    User getUser(String login);
    void updateUser(int id, User user);
    void deleteUser(int id);
    Role getRoleById(int id);
    Role getRoleByName(String name);
}
