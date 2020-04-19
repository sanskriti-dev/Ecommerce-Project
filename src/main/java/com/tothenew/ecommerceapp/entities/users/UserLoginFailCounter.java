package com.tothenew.ecommerceapp.entities.users;

import javax.persistence.*;

@Entity
public class UserLoginFailCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private Integer attempts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "UserLoginFailCounter{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", attempts=" + attempts +
                '}';
    }
}
