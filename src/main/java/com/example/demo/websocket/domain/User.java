package com.example.demo.websocket.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    String id;
    String name;
    List<SubscribeType> type;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.type = new ArrayList<>();

        type.add(SubscribeType.BROAD);

        switch (id) {
            case "1":
                type.add(SubscribeType.ADMIN);
                break;
            case "2":
                type.add(SubscribeType.ADMIN);
                type.add(SubscribeType.VCF);
                break;
            case "3":
                type.add(SubscribeType.CDM);
                break;
            case "4":
                type.add(SubscribeType.EDC);
                type.add(SubscribeType.CDM);
                break;
            case "5":
                type.add(SubscribeType.VCF);
                type.add(SubscribeType.CDM);

                break;
            case "6":
                type.add(SubscribeType.ADMIN);
                type.add(SubscribeType.EDC);
                break;
            case "7":
                type.add(SubscribeType.VCF);
                type.add(SubscribeType.EDC);
                break;
            case "8":
                type.add(SubscribeType.EDC);
                break;
            default: break;

        }

    }


}
