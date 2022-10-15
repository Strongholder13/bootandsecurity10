package com.springsecurity.bootsecurity.model;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

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

//    @Column(name = "role")
//    @NonNull
//    private String role;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")

    private Role role;

    public void setRole(Role role){ this.role = role;}

    public Role getRole() {
         return role;
    }

    public User() {

    }
}
