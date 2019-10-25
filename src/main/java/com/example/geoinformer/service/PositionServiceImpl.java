package com.example.geoinformer.service;

import com.example.geoinformer.controller.PositionController;
import com.example.geoinformer.entity.Position;
import com.example.geoinformer.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private static final Logger logger = LoggerFactory.getLogger(PositionController.class.getName());

    private static final String SUCCESS = "success!";
    private static final String FAILED = "failed!";
    private static final String NULL = "null!";
    private static final String EMPTY = "empty!";
    private static final String BAD_INPUT = "bad input!";
    private static final String NOMINATIM = "Nominatim: ";
    private static final String DATABASE = "Database: ";

    // TODO Replace email to header changes
    private final String URL = "https://nominatim.openstreetmap.org/reverse?" +
            "email=okuziura@gmail.com&format=jsonv2&accept-language=ru&zoom=18&lat=%f&lon=%f";

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position receivePosition(float latitude, float longitude) {
        if (latitude >= -90F && latitude <= 90F && longitude >= -180F && longitude <= 180F) {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(URL, latitude, longitude);
            logger.info(url);
            Position position = restTemplate.getForObject(url, Position.class);
            if (position != null) {
                logger.info(NOMINATIM + "\n" + position.toString());
                logger.info(NOMINATIM + SUCCESS);
                return position;
            } else {
                logger.error(NOMINATIM + NULL);
                logger.info(NOMINATIM + FAILED);
                return null;
            }
        } else {
            logger.warn(NOMINATIM + BAD_INPUT);
            logger.info(FAILED);
            return null;
        }
    }

    @Override
    public Position savePosition(Position position) {
        if (position != null) {
            Position positionFromDB =
                    positionRepository.findByLatitudeAndLongitude(position.getLatitude(), position.getLongitude());
            if (positionFromDB == null) {
                positionFromDB = positionRepository.save(position);
                if (positionFromDB != null) {
                    logger.info(DATABASE + "\n" + positionFromDB.toString());
                    logger.info(DATABASE + SUCCESS);
                } else {
                    logger.error(DATABASE + NULL);
                    logger.info(DATABASE + FAILED);
                }
                return positionFromDB;
            } else {
                logger.info(DATABASE + "\n" + positionFromDB.toString());
                logger.info(DATABASE + SUCCESS);
                return positionFromDB;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Position> findPositionsByCountry(String country) {
        return positionRepository.findByCountryOrderByCountryAscNameAsc(country);
    }

    @Override
    public Position findPositionByName(String name) {
        return positionRepository.findFirstByNameOrderById(name);
    }

    @Override
    public Position findPositionByCoords(float latitude, float longitude) {
        return positionRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public void updateAllPositions() {
        List<Position> positions = positionRepository.findAll();
        Position positionNew = null;
        String url = null;
        float latitude;
        float longitude;
        RestTemplate restTemplate = new RestTemplate();
        for (Position position : positions) {
            latitude = position.getLatitude();
            longitude = position.getLongitude();
            url = String.format(URL, latitude, longitude);
            positionNew = restTemplate.getForObject(url, Position.class);
            position.setLatitude(positionNew.getLatitude());
            position.setLongitude(positionNew.getLongitude());
            position.setCountry(positionNew.getCountry());
            position.setType(positionNew.getType());
            position.setName(positionNew.getName());
            position.setOsmId(positionNew.getOsmId());
            position.setOsmType(positionNew.getOsmType());
            positionRepository.save(position);
        }
    }
}
