package com.example.geoinformer.service;

import com.example.geoinformer.entity.Position;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PositionService {

    public ResponseEntity<Position> receivePosition(float latitude, float longitude);

    public ResponseEntity<Position> savePosition(Position position);

    public ResponseEntity<List<Position>> findPositionsByCountry(String country);

//    public Position findPositionByName(String name);
//
//    public Position findPositionByCoords(float latitude, float longitude);
//
//    public void updateAllPositions();
}
