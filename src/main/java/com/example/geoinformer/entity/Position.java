package com.example.geoinformer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("osm_type")
    @Column(name = "osm_type", length = 1)
    private Character osmType;

    @JsonProperty("osm_id")
    @Column(name = "osm_id")
    private Long osmId;

    @JsonProperty("lat")
    @Column(nullable = false)
    private float latitude;

    @JsonProperty("lon")
    @Column(nullable = false)
    private float longitude;

    @Column(length = 2)
    private String country;

    @JsonProperty("type")
    @Column(length = 22)
    private String type;

    @JsonProperty("display_name")
    @Column(length = 128)
    private String name;

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getOsmType() {
        return osmType;
    }

    public void setOsmType(Character osmType) {
        this.osmType = osmType;
    }

    public Long getOsmId() {
        return osmId;
    }

    public void setOsmId(Long osmId) {
        this.osmId = osmId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.country = (String) address.get("country_code");
    }

    @Override
    public String toString() {
        return "{\n" +
                "id=" + id + ",\n" +
                "osm_type=\'" + osmType + "\',\n" +
                "osm_id=" + osmId + ",\n" +
                "lat=" + latitude + ",\n" +
                "lon=" + longitude + ",\n" +
                "country=\"" + country + "\",\n" +
                "type=\"" + type + "\",\n" +
                "name=\"" + name + "\"\n" +
                "}";
    }
}
