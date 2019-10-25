package com.example.geoinformer.service;

import com.example.geoinformer.entity.Position;

import java.util.List;

public interface PositionService {

    public Position receivePosition(float latitude, float longitude);

    public Position savePosition(Position position);

    public List<Position> findPositionsByCountry(String country);

    public Position findPositionByName(String name);

    public Position findPositionByCoords(float latitude, float longitude);

    public void updateAllPositions();
}
