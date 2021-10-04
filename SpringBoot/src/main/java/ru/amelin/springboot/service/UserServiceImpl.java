package ru.amelin.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.amelin.springboot.dao.UserDao;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        if (userExist(user.getEmail())) {
            return false;
        } else if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 ||
                user.getEmail().trim().length() == 0 || user.getLastname().trim().length() == 0 || user.getRoles().equals("null")) {
            return false;
        } else {
            userDao.addUser(user);
            return true;
        }
    }

    @Transactional
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Transactional
    @Override
    public boolean updateUser(User user) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 ||
                user.getEmail().trim().length() == 0 || user.getLastname().trim().length() == 0 || user.getRoles().equals("null")) {
            return false;
        } else {
            userDao.updateUser(user);
            return true;
        }
    }



    @Override
    public Set<Role> getRoleForUser(String roles) {
        Set<Role> rolesSet = new HashSet<>();
        try {
            String[] partsRole = roles.split(",");
            for (String role : partsRole) {
                rolesSet.add(new Role(role));
            }
            return rolesSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        rolesSet.add(new Role(roles));
        return rolesSet;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> currentUser = Optional.ofNullable(userDao.getUserByEmail(email));
        return currentUser.orElseThrow(IllegalAccessError::new);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public boolean userExist(String email) {
        return userDao.userExist(email);
    }

}
