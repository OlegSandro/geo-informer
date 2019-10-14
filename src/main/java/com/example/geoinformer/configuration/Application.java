package com.example.geoinformer.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.geoinformer.controller")
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}