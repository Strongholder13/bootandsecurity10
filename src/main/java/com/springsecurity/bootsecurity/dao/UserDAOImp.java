package com.springsecurity.bootsecurity.dao;



import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
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

        List<Role> role = new ArrayList();
        Role role1 = new Role(user.getUsername(), "ROLE_USER") ;
        role.add(role1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(role);
        entityManager.persist(user);
        //entityManager.persist(role1);
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
    public User getUserId(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
    @Override
    public User getUser(String username) {
        User user = entityManager.find(User.class, username);
        return user;
    }

    public Role getListRoles(String username) {
        Role role = entityManager.find(Role.class, username);
        return role;
    }

    public void deleteUser(int id) {
        User user = this.entityManager.find(User.class, id);
        this.entityManager.remove(user);
    }

    @Override
    public List<Role> listRoles(String username) {
        User user = entityManager.find(User.class, username);
        return user.getRoles();
    }
}

