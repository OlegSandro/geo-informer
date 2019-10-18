package com.example.geoinformer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {

    private long id;
    @JsonProperty("lat")
    private float latitude;
    @JsonProperty("lon")
    private float longitude;
    @JsonProperty("display_name")
    private String name;
    private String country; // 14 symbols
    @JsonProperty("type")
    private String type; // 22 symbols
    @JsonProperty("osm_id")
    private Long osmId;
    @JsonProperty("osm_type")
    private Character osmType;

    public Position() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public Character getOsmType() {
        return osmType;
    }

    public void setOsmType(Character osmType) {
        this.osmType = osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = (osmType.equals("node"))
                ? 'N'
                : (osmType.equals("relation"))
                ? 'R'
                : (osmType.equals("way"))
                ? 'W'
                : null;
    }

    @JsonProperty("address")
    private void unpackCountry(Map<String, Object> address) {
        this.country = (String) address.get("country");
    }

    @Override
    public String toString() {
        return "Position{\n" +
                "id=" + id + ",\n" +
                "lat=" + latitude + ",\n" +
                "lon=" + longitude + ",\n" +
                "name=\"" + name + "\",\n" +
                "country=\"" + country + "\",\n" +
                "type=\"" + type + "\",\n" +
                "osm_id=" + osmId + ",\n" +
                "osm_type=\'" + osmType + "\'\n" +
                "}";
    }
}
