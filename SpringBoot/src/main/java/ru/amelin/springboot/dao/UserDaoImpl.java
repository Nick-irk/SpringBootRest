package ru.amelin.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.amelin.springboot.entity.Role;
import ru.amelin.springboot.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User findByLogin(String login) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", login)
                .getSingleResult();
    }

    @Override
    public User getUser(String login) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class)
                .setParameter("name", login)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
