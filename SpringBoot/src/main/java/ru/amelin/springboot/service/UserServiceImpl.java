package ru.amelin.springboot.service;

import ru.amelin.springboot.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void addUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public Role getRoleById(int id) {
        return userDao.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }
}
