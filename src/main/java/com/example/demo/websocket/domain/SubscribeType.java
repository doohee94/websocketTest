package com.example.demo.websocket.domain;

import lombok.Getter;

@Getter
public enum SubscribeType {

    ADMIN("/admin/"),
    EDC("/edc/"),
    CDM("/cdm/"),
    VCF("/vcf/"),
    BROAD("/broad/")
    ;

    String value;

    SubscribeType(String value) {
        this.value = value;
    }
}


