//package com.springsecurity.bootsecurity.security;
//
//
//import com.springsecurity.bootsecurity.model.Role;
//import com.springsecurity.bootsecurity.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//public class MyUserDetailes implements UserDetails {
//
//    private final User user;
//   // private final Role role;
//
//    @Autowired
//    public MyUserDetailes(User user, Role role) {
//        this.user = user;
//        //this.role = role;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> list = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            list.add(new SimpleGrantedAuthority(role.getAuthority()));
//        }
//        return list;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public User getUser(){
//        return this.user;
//    }
//
//
//
//}