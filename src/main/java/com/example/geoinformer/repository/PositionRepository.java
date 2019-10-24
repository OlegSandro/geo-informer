package com.example.geoinformer.repository;

import com.example.geoinformer.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    List<Position> findByCountryOrderByCountryAsc(String country);

    /*@Query("FROM position WHERE name = ?1")
    Position findPositionByName(String name);*/
}
