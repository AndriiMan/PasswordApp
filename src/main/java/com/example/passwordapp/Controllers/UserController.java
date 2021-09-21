package com.example.passwordapp.Controllers;

import com.example.passwordapp.User;
import com.example.passwordapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/getuser/{id}")
    private User getStudent(@PathVariable("id") long id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/get")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/createUser")
    public String index(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getPassword();
    }

}