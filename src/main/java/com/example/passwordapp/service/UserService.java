package com.example.passwordapp.service;

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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService implements UserPasswordServiceInterface {

    @Override
    public String generatePasswordByName(String name) {
        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[12]; //Means 2048 bit
        random.nextBytes(r);
        String s = Base64.encodeBase64String(r);
        return name.concat(s);
    }

    public String generatePasswordWithCharecters(int charecters, int specCharecters) {
        String specChars = "!@#$%&";
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(charecters);
        double rand;
        //Adding special charecters
        for (int i = 0; i < specCharecters; i++) {
            sb.append(specChars.charAt(rnd.nextInt(specChars.length())));
        }
        //Adding charecters
        for (int i = 0; i < charecters; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return shuffle(sb.toString());
    }

    public String shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    @Autowired
    UserDataBaseRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    //getting all users acaunts
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void saveOrUpdate(User user) throws WebApplicationException {
        if (!isValidURL(user.getUrl())) {
            Response response = new Response(Response.SC_BAD_REQUEST);
            throw new WebApplicationException(response.getMessage());
        }
        user.setPassword(generatePasswordWithCharecters(7, 2));
        userRepository.save(user);
    }

    public void saveUserWithPasswordCharInput(User user, int charecters, int specCharecters) throws WebApplicationException {
        if (!isValidURL(user.getUrl())) {
            Response response = new Response(Response.SC_BAD_REQUEST);
            throw new WebApplicationException(response.getMessage());
        }
        user.setPassword(generatePasswordWithCharecters(charecters, specCharecters));
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
