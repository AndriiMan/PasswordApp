package com.example.passwordapp.controllers;

import com.example.passwordapp.component.UserConverter;
import com.example.passwordapp.dto.UserDTO;
import com.example.passwordapp.model.User;
import com.example.passwordapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    final
    UserService userService;

    @Autowired
    UserConverter userConverter;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getuser/{id}")
    private User getStudent(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getusername/{name}")
    private List<User> getStudentByName(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/get")
    public String createPasswordForUser() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createPasswordForUser(@RequestBody User user, int charecters, int specCharecters) {
        try {
            userService.saveUserWithPasswordCharInput(user, charecters, specCharecters);
            return new ResponseEntity<>((user.getPassword()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createUserDTO")
    public ResponseEntity<String> createPasswordForUser(@RequestBody UserDTO userDTO, int charecters, int specCharecters) {
        try {
            User user= userConverter.dtoToEntity(userDTO);
            userService.saveUserWithPasswordCharInput(user, charecters, specCharecters);
            return new ResponseEntity<>((user.getPassword()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}