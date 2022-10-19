package com.springsecurity.bootsecurity.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name= "Roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "username")
    private String username;

    @Column
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role1)) return false;
        return username.equals(role1.username) && role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
