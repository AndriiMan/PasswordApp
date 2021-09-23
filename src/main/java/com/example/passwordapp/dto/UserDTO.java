package com.example.passwordapp.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String url;
    private String password;
}
