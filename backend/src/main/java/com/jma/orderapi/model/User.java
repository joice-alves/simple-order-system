package com.jma.orderapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "Username is a required field")
    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "Password must be at least 8 characters long") // Uma boa prática de validação básica
    private String password;

    @Column(nullable = false)
    private Boolean active = true;

    public User() {
    }

    public User(Long userId, String name, String username, String password, Boolean active) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}