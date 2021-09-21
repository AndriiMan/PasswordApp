package com.example.passwordapp;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;


    private String name;
    private String url;
    private String password;

    public User(String name, String url, String password) {
        this.name = name;
        this.url = url;
        this.password = password;
    }

    public User(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
