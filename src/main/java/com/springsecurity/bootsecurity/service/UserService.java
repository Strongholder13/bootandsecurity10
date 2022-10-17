package com.springsecurity.bootsecurity.service;




import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    List<User> listUsers();

    User findById(int id);

    void delete(int id);

    @Transactional
    User findByUsername(String username);
    List<Role> listRoles(String username);
}