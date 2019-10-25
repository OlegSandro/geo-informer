package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Position;
import com.example.geoinformer.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    private static final String SUCCESS = "Success!";
    private static final String FAILED = "Failed!";
    private static final String NULL = "Null!";
    private static final String EMPTY = "Empty!";
    private static final String BAD_INPUT = "Bad input!";

    @Autowired
    private PositionService positionService;

    private static final Logger logger = LoggerFactory.getLogger(PositionController.class.getName());

    /**
     * Метод, служащий для проверки работы веб-сервиса
     * @return объект – представление с текстом "Hello world"
     */
    @GetMapping("/")
    public String index() {
        String content = "Hello world";
        logger.info(content);
        return content;
    }

    /**
     * Метод, возвращающий по координатам в формате WGS 84 все данные о найденном месте
     * @param lat число – значение широты в градусах (в пределах от -90 до 90)
     * @param lon число – значение долготы в градусах (в пределах от -180 до 180)
     * @return объект – ответ от контроллера, включающий объект сущности Position и статус HTTP-запроса
     */
    @GetMapping("/pos-by-coords")
    public ResponseEntity<Position> getPositionByCoords(@RequestParam float lat, @RequestParam float lon) {
        Position position = positionService.receivePosition(lat, lon);
        position = positionService.savePosition(position);
        if (position != null) {
            return new ResponseEntity<>(position, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
/*
    /**
     * Метод, позволяющий в зависимости от обнаружения места возвратить объект сущности Position и статус HTTP-запроса
     * @param position объект – место на карте
     * @return объект – ответ от контроллера, включающий объект сущности Position и статус HTTP-запроса
     */
  /*  private ResponseEntity<Position> returnResponse(Position position) {
        if (position == null) {
            logger.warn(NULL);
            logger.info(FAILED);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        logger.info("Remote RESTful webservice response:");
        logger.info(position.toString());
        logger.info(SUCCESS);
        logger.info("Local DB response:");
        try {
            Position positionNew = positionRepository.save(position);
            position = positionNew;
        } catch (Exception e) {
            // TODO Add check is the exception causes because position already exists or unforeseen situation
            logger.warn(e.getClass().getName());
            e.printStackTrace();
        } finally {
            logger.info(position.toString());
            logger.info(SUCCESS);
        }
        return new ResponseEntity<>(position, HttpStatus.OK); // 200
    }*/

/*
    @GetMapping("/pos-by-country")
    public ResponseEntity<List<Position>> getPositionsByCountry(@RequestParam String country) {
        List<Position> positions = positionRepository.findByCountryOrderByCountryAsc(country);
        if (positions == null) {
            logger.warn(NULL);
            logger.info(FAILED);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

        }
        for (Position position : positions) {
            logger.info(position.toString());
        }
        logger.info(SUCCESS);
        return new ResponseEntity<>(positions, HttpStatus.OK); // 200
    }
*/
    @GetMapping("/pos-by-country")
    public ResponseEntity<List<Position>> getPositionsByCountry(@RequestParam String country) {
        List<Position> positions = positionService.findPositionsByCountry(country);
        if(positions == null){
            logger.warn(NULL);
            logger.info(FAILED);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        } else if (positions.isEmpty()) {
            logger.warn(EMPTY);
            logger.info(SUCCESS);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        logger.info(SUCCESS);
        return new ResponseEntity<>(positions, HttpStatus.OK); // 200
    }

    /*@GetMapping("/pos-by-name")
    public ResponseEntity<Position> getPositionByName(@RequestParam String name) {
        Position position = positionRepository.findPositionByName(name);
        if (position == null) {
            logger.warn(NULL);
            logger.info(FAILED);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

        }
        logger.info(position.toString());
        logger.info(SUCCESS);
        return new ResponseEntity<>(position, HttpStatus.OK); // 200
    }*/
}
