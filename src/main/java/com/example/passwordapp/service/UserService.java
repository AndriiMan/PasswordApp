package com.example.passwordapp.service;

import com.example.passwordapp.PasswordCreator;
import com.example.passwordapp.WebApplicationException;
import com.example.passwordapp.repository.UserDataBaseRepository;
import com.example.passwordapp.model.User;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    protected PasswordCreator passwordCreator= new PasswordCreator();

    final
    UserDataBaseRepository userRepository;

    public UserService(UserDataBaseRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    //getting all users acaunts
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void saveOrUpdate(User user) throws WebApplicationException {
        if (!isValidURL(user.getUrl())) {
            Response response = new Response(Response.SC_BAD_REQUEST);
            throw new WebApplicationException(response.getMessage());
        }
        user.setPassword(passwordCreator.generatePasswordWithCharecters(7, 2));
        userRepository.save(user);
    }

    public void saveUserWithPasswordCharInput(User user, int charecters, int specCharecters) throws WebApplicationException {
        if (!isValidURL(user.getUrl())) {
            Response response = new Response(Response.SC_BAD_REQUEST);
            throw new WebApplicationException(response.getMessage());
        }
        user.setPassword(passwordCreator.generatePasswordWithCharecters(charecters, specCharecters));
        userRepository.save(user);
    }


    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return true;
    }
}
