package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Position;
import com.example.geoinformer.entity.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PositionController {

    @GetMapping("/")
    public String printSomething() {
        return "Hello world";
    }

    @GetMapping("/quot")
    public ResponseEntity<Quote> qoute() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://gturnquist-quoters.cfapps.io/api/random";
        Quote quote = restTemplate.getForObject(url, Quote.class);
        if(quote == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        }
        return new ResponseEntity<>(quote, HttpStatus.OK); //200
    }

    @GetMapping("/pos")
    public ResponseEntity<Position> pos() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://nominatim.openstreetmap.org/reverse?email=okuziura@gmail.com&format=json&lat=52.42107819733966&lon=31.015610763888095";
        System.out.println(url);
        Position position = restTemplate.getForObject(url, Position.class);
        if(position == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //204
        }
        return new ResponseEntity<>(position, HttpStatus.OK); //200
    }
}
