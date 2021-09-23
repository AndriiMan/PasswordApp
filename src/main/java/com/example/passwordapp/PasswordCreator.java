package com.example.passwordapp;

import com.example.passwordapp.service.UserPasswordService;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PasswordCreator  implements UserPasswordService {

    private final String SPECIAL_CHARS = "!@#$%&";
    private final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String generatePasswordByName(String name) {
        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[12];
        random.nextBytes(r);
        String s = Base64.encodeBase64String(r);
        return name.concat(s);
    }

    public String generatePasswordWithCharecters(int charecters, int specCharecters) {

        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(charecters);
        double rand;
        //Adding special charecters
        for (int i = 0; i < specCharecters; i++) {
            sb.append(SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length())));
        }
        //Adding charecters
        for (int i = 0; i < charecters; i++) {
            sb.append(CHARS.charAt(rnd.nextInt(CHARS.length())));
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

}