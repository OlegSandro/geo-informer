package com.example.geoinformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*(scanBasePackages = {
        "com.example.geoinformer.repository",
        "com.example.geoinformer.controller"*//*,
        "com.example.geoinformer.entity"*//*
        })*/
@SpringBootApplication(scanBasePackages = "com.example.geoinformer.controller")
@EnableJpaRepositories(basePackages = "com.example.geoinformer.repository")
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.geoinformer.entity")
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}