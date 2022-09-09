package com.client.ws.rasmooplus.enums;

import lombok.Getter;

@Getter
public enum UserTypeEnum {

    PROFESSOR(1),
    ADMINISTRADOR(2),
    ALUNO(3);

    private Long id;

    UserTypeEnum(int i) {
    }
}
