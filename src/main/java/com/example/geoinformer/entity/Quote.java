package com.example.geoinformer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private int id;
    private String quote;

    public Quote() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @JsonProperty("value")
    private void unpackNested(Map<String, Object> value) {
        this.id = (int) value.get("id");
        this.quote = (String) value.get("quote");
    }

    @Override
    public String toString() {
        return "Quote{\n" +
                "type='" + "', \n" +
                "quote=\"" + quote + "\"" +
                "}";
    }
}