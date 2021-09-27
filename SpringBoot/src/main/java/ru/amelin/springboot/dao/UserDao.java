package ru.amelin.springboot.dao;

import ru.amelin.springboot.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public User getUserByEmail(String email);

    boolean userExist(String email);
}
