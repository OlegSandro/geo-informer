package com.example.geoinformer.service;

import com.example.geoinformer.entity.Position;
import com.example.geoinformer.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public List<Position> getPositionsByCountry(String country) {
        return positionRepository.findByCountryOrderByCountryAsc(country);
    }
}
