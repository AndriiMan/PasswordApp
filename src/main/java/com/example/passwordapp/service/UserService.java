package com.example.passwordapp.service;

import com.example.passwordapp.Repository.UserDataBaseRepository;
import com.example.passwordapp.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserPasswordServiceInterface {

    @Override
    public String generatePasswordByNameAndUrl(String name, String url) {
        return name.concat(url);
    }
    @Autowired
    UserDataBaseRepository userRepository;

    //getting all users acaunts
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void saveOrUpdate(User user)
    {
        userRepository.save(user);
    }

}
