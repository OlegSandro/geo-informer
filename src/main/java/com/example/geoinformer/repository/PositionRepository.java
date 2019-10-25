package com.example.geoinformer.repository;

import com.example.geoinformer.entity.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Position findByLatitudeAndLongitude(float latitude, float longitude);

    List<Position> findByCountryOrderByCountryAscNameAsc(String country);

    Position findFirstByNameOrderById(String name);

    List<Position> findAll();
}
