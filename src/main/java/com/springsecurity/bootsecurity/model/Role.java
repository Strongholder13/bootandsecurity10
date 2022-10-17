package com.springsecurity.bootsecurity.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name= "Roles")
public class Role implements GrantedAuthority, Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column
    private String role;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;


    public Role() {
    }

    public Role(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
