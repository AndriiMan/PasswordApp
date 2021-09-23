/*
package com.example.passwordapp.model;

import javax.persistence.*;

@Entity
@Table(name = "userPassword")
public class UserPassword {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    private String url;

    private String password;

    @ManyToOne
    @JoinColumn(name = "userm_id")
    private UserM userm;

    public UserPassword(String url, String password, UserM userm) {
        this.url = url;
        this.password = password;
        this.userm = userm;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

}
*/
