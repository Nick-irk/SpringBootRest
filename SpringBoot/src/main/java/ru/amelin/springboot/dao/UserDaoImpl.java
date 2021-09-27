package ru.amelin.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.amelin.springboot.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        return (User) entityManager.createQuery("FROM User WHERE email =:e")
                .setParameter("e", email)
                .getSingleResult();
    }

    @Override
    public boolean userExist(String email) {
        return getAllUsers()
                .stream()
                .anyMatch((e) -> e.getEmail().hashCode() == email.hashCode());
    }
}
