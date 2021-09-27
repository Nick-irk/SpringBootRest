package ru.amelin.springboot.service;

import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUsers();

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public User getUserByEmail(String email);

    public void addFirstAdminAndUser();

    public Set<Role> getRoleForUser(String roles);

    public boolean userExist(String email);
}
