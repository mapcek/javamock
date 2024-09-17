package com.example.newMock.Model;

import java.math.BigDecimal;

import lombok.Data;

// декоратор добавления методов класса для автогенерации геттеров и сеттеров
// все из коробки
@Data
// модель представления ответа сервера (меняем под нужды)
public class ResponseDTO {
    
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private BigDecimal maxLimit;
    
}
