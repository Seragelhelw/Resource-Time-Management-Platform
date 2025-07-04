package com.example.asweprj.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String name;
    String email;
    String role;
    String password;



    public User(Long userId, String name, String email, String role, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User role(String role) {
        setRole(role);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

 
    


}