package com.example.geoinformer.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.example.geoinformer.service", "com.example.geoinformer.controller"})
@EnableJpaRepositories(basePackages = "com.example.geoinformer.repository")
@EntityScan(basePackages = "com.example.geoinformer.entity")
@EnableScheduling
public class EntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(EntryPoint.class, args);
    }
}