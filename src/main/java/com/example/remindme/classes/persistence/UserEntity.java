package com.example.remindme.classes.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity { //UserEntitylass I change the nmae cause of mysql (UserEntity is a reserved keyword)
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;
    private String tweeter;
    private String email;

    public UserEntity() {
        
    }

    public UserEntity(String name, String password, String tweeter, String email) {
        this.name = name;
        this.password = password;
        this.tweeter = tweeter;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTweeter() {
        return tweeter;
    }

    public void setTweeter(String tweeter) {
        this.tweeter = tweeter;
    }
}
