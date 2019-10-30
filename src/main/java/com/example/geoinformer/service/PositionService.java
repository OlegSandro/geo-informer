package com.example.geoinformer.service;

import com.example.geoinformer.entity.Position;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PositionService {

    ResponseEntity<Position> receivePosition(float latitude, float longitude);

    ResponseEntity<Position> savePosition(Position position);

    ResponseEntity<List<Position>> findPositionsByCountry(String country);

    ResponseEntity<Position> findPositionByName(String name);

    ResponseEntity refreshAllPositions();
}