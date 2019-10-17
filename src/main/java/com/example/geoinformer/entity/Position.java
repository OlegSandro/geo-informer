package com.example.geoinformer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {

    private int place_id;
    private String osm_type;

    public Position() {
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    @Override
    public String toString() {
        return "Position{\n" +
                "place_id=" + ",\n" +
                "osm_type=\"" + "\"\n" +
                "}";
    }
}
