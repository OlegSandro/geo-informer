package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {

    @GetMapping("/")
    public String printSomething() {
        return "Hello world";
    }

    @GetMapping("/quot")
    public ResponseEntity<Quote> index() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gturnquist-quoters.cfapps.io/api/random";
        Quote quote = restTemplate.getForObject(url, Quote.class);
        if(quote == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        }
        return new ResponseEntity<>(quote, HttpStatus.OK); //200
    }
}
