package com.springsecurity.bootsecurity.service;




import com.springsecurity.bootsecurity.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    List<User> listUsers();

    User findById(int id);

    void delete(int id);

}