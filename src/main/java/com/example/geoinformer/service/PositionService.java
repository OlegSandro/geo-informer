package com.example.geoinformer.service;

import com.example.geoinformer.entity.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositionsByCountry(String country);
}
