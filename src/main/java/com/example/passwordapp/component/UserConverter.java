package com.example.passwordapp.component;

import com.example.passwordapp.dto.UserDTO;
import com.example.passwordapp.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public User dtoToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUrl(userDTO.getUrl());
        user.setPassword(user.getPassword());
        return user;
    }

    public UserDTO entityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setUrl(user.getUrl());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public List<User> dtoToEntity(List<UserDTO> userDTO) {
        return userDTO.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<UserDTO> entityToDTO(List<User> user) {
        return user.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
