package com.example.passwordapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PasswordAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordAppApplication.class, args);
    }
}