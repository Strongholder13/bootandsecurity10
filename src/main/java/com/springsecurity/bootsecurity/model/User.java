package com.springsecurity.bootsecurity.model;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username")
    @NonNull
    private String username;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Age")
    private int age;

    @Column(name = "Password")
    @NonNull
    private String password;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;



    public List<Role> getRoles() {
         return roles;
    }

    public User( @NonNull String username, String name, String surname, int age, @NonNull String password, List<Role> roles) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.password = password;
        this.roles = roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User() {

    }

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
//        List<GrantedAuthority> authorities
//                = new ArrayList<>();
//        //List<Role> roles2 = roles.getRoles();
//        for (Role role: roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        return authorities;
        // roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +

                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", password='" + password +
                '}';
    }
}
