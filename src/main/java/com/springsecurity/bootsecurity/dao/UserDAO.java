package com.springsecurity.bootsecurity.dao;





import com.springsecurity.bootsecurity.model.Role;
import com.springsecurity.bootsecurity.model.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    void save(User user);

     List<User> listUsers();

    User getUserId(int id);

    User getUser(String username);

    void deleteUser(int id);

    List<Role> listRoles(String username);
}
