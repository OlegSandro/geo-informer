package com.example.geoinformer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @GetMapping("/")
    public String printSomething() {
        return "Hello world";
    }

    @GetMapping("/index")
    public String index() {
        return "Index page";
    }
}
