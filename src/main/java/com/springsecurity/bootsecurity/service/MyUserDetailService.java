package com.springsecurity.bootsecurity.service;


import com.springsecurity.bootsecurity.model.User;
import com.springsecurity.bootsecurity.repository.UsersRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public MyUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
    }
}
