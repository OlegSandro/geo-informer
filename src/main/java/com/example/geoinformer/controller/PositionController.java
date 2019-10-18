package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PositionController {

    private final String SUCCESS = "Success!";
    private final String FAILED = "Failed!";
    private final String NULL = "Null!";

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
    public ResponseEntity<Position> getPositionByCoords(@RequestParam Float lat, @RequestParam Float lon) {
        if (
            lat.compareTo(-90F) >= 0 &&
            lat.compareTo(90F) <= 0 &&
            lon.compareTo(-180F) >= 0 &&
            lon.compareTo(180F) <= 0
        ) {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format("https://nominatim.openstreetmap.org/reverse?" +
                    "email=okuziura@gmail.com&format=jsonv2&accept-language=ru&zoom=18&lat=%f&lon=%f", lat, lon);
            logger.info("Forward to " + url);
            Position position = restTemplate.getForObject(url, Position.class);
            return returnResponse(position);
        } else {
            return returnResponse(null);
        }
    }

    /**
     * Метод, позволяющий в зависимости от обнаружения места возвратить объект сущности Position и статус HTTP-запроса
     * @param position объект – место на карте
     * @return объект – ответ от контроллера, включающий объект сущности Position и статус HTTP-запроса
     */
    private ResponseEntity<Position> returnResponse(Position position) {
        if (position == null) {
            logger.warn(NULL);
            logger.info(FAILED);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        logger.info(position.toString());
        logger.info(SUCCESS);
        return new ResponseEntity<>(position, HttpStatus.OK); // 200
    }
}
