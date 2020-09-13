package com.example.demo.websocket.domain;

import lombok.Data;

@Data
public class SubscribeDto {

    String value;

    public SubscribeDto(String value, String id) {
        this.value = value +id;
    }

}
