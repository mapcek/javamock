package com.example.newMock.Model;

import lombok.Data;

// декоратор добавления методов класса для автогенерации геттеров и сеттеров
// все из коробки
@Data
// модель представления запроса клиента (меняем под нужды)
public class RequestDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;

}
