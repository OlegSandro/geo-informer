package com.example.geoinformer.entity;

import com.example.geoinformer.utility.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
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
    @JsonView(View.Position.class)
    private String country;

    @JsonProperty("type")
    @Column(length = 22)
    private String type;

    @JsonProperty("display_name")
    @Column(length = 128)
    @JsonView(View.Position.class)
    private String name;

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
        this.country = (String) address.get("country_code");
    }

    public String overview() {
        return "{\n" +
                "country=\"" + country + "\",\n" +
                "name=\"" + name + "\"\n" +
                "}";
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
