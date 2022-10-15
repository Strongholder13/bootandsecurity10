package com.springsecurity.bootsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name= "Roles")
public class Role {
    @OneToOne(mappedBy = "roles", cascade = CascadeType.ALL)
    private User user;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String role;


}
