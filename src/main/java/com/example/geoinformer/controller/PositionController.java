package com.example.geoinformer.controller;

import com.example.geoinformer.entity.Position;
import com.example.geoinformer.service.PositionService;
import com.example.geoinformer.utility.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * Метод, возвращающий все найденные в БД места из указанной страны.
     * При этом место включает лишь общую информацию о себе.
     *
     * @param country название страны
     * @return список всех мест из указанной в параметре <tt>country</tt> страны,
     *         найденных в БД
     */
    @JsonView(View.Position.class)
    @GetMapping("/pos-by-country")
    public ResponseEntity<List<Position>> getPositionsByCountry(@RequestParam String country) {
        return positionService.findPositionsByCountry(country);
    }

    /**
     * Метод, возвращающий найденное в БД место по указанному названию.
     *
     * @param name текст, который содержится в названии места
     * @return найденное в БД место, которое имеет в своем названии указанный
     *         в параметре <tt>name</tt> текст
     */
    @GetMapping("/pos-by-name")
    public ResponseEntity<Position> getPositionByName(@RequestParam String name) {
        return positionService.findPositionByName(name);
    }

    /**
     * Метод, обновляющий информацию по всем местам в БД
     *
     * @return состояние HTTP
     */
    @PostMapping("/pos-update")
    public ResponseEntity<?> refreshPositions() {
        return positionService.refreshAllPositions();
    }
}
