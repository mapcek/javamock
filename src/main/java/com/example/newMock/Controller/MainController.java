package com.example.newMock.Controller;

import java.math.BigDecimal;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

//декоратор для работы с рест апи классами
@RestController
public class MainController {
    // переменная для логирования, применяем либу slf4j
    private Logger log = LoggerFactory.getLogger(MainController.class);
    // преставление xml с помощью либы fasterxml
    ObjectMapper mapper = new ObjectMapper();

    // декоратор пост запроса для описания логики ответа сервера
    // внутри value - маршрут запроса, настройка контента продюсера и консюмера
    // в данном случае стандартная библиотека спринга
    @PostMapping(value = "/info/balances", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        // разработка логики заглушки, конструкция трай кэтч
        // для возможности обработки ошибок запросов
        try {
            // переменные класса
            String clientId = requestDTO.getClientId();
            // тут обрабатываем первое число в айди клиента для дальнейшей логики
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String currency;
            BigDecimal balance;
            Random rand;
            // переменная для рандомизации чисел в ответе сервера
            rand = new Random();
            // if else можно заменить switch при наличии большого объема условий
            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000);
                currency = new String("US");
                balance = new BigDecimal(rand.nextInt(2001));
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000);
                currency = new String("EU");
                balance = new BigDecimal(rand.nextInt(1001));
            } else {
                maxLimit = new BigDecimal(10000);
                currency = new String("RUB");
                balance = new BigDecimal(rand.nextInt(10001));
            }
            // представление ответа сервера путем создания объекта класса ответа
            // ранее определенного в моделях, с помощью сеттеров созданных в @Data
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);
            responseDTO.setMaxLimit(maxLimit);

            // логирование, можно брать шаблоном везде, просто красивое отображение
            // можно реализовать любую другую логику логирования

            log.error("********* RequestDTO *********"
                    + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("********* ResponseDTO *********"
                    + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            // возвращаем ответ сервера
            return responseDTO;
            // обработка ошибок - кидаем код 404 при не валидном запросе
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}