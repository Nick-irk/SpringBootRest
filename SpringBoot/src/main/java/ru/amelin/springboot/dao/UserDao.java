package ru.amelin.springboot.dao;

import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUser();
    User getUser(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    User findByLogin(String login);
    User getUser(String login);
    Role getRoleById(int id);
    Role getRoleByName(String name);
}
