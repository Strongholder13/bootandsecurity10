package com.springsecurity.bootsecurity.dao;



import com.springsecurity.bootsecurity.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    private final PasswordEncoder passwordEncoder;


    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImp(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRole("ROLE_USER");
        entityManager.persist(user);
    }

    @Override
    public void save(User user) {
          entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        List<User> list = entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
        return list;
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public void deleteUser(int id) {
        User user = this.entityManager.find(User.class, id);
        this.entityManager.remove(user);
    }
}

