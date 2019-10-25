package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Position;
import com.example.geoinformer.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    @Autowired
    private PositionService positionService;

    /**
     * Метод, возвращающий главную страницу веб-сервиса. Добавлен для проверки
     * работы веб-сервиса.
     *
     * @return представление с текстом, являющим собой краткую информацию
     *         о текущем веб-сервисе
     */
    @GetMapping("/")
    public String index() {
        String content = "The Spring Boot app, which makes interaction with the OpenStreetMap Nominatim API " +
                "to work with geographic data and store it to the MySQL database.";
        return content;
    }

    /**
     * Метод, который получает по координатам в формате WGS 84 объект
     * найденного места в формате JSON, сохраняет его в БД и возвращает из БД
     * сущность в формате JSON, соответствующую этому месту.
     *
     * @param lat широта, указанная в градусах (в пределах от -90 до 90)
     * @param lon долгота, указанная в градусах (в пределах от -180 до 180)
     * @return ответ от сервера, включающий сущность места в формате JSON,
     *         взятую из БД и соответствующую указанным в параметрах
     *         широте <tt>lat</tt> и долготе <tt>lon</tt>
     */
    @GetMapping("/pos-by-coords")
    public ResponseEntity<Position> getPositionByCoords(@RequestParam float lat, @RequestParam float lon) {
        ResponseEntity<Position> responseEntity = positionService.receivePosition(lat, lon);
        if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return positionService.savePosition(responseEntity.getBody());
        } else {
            return responseEntity;
        }
    }

/*    @GetMapping("/pos-by-country")
    public ResponseEntity<List<Position>> getPositionsByCountry(@RequestParam String country) {
        List<Position> positions = positionService.findPositionsByCountry(country);
        if (positions != null) {
            return new ResponseEntity<>(positions, HttpStatus.OK); // 200
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

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
