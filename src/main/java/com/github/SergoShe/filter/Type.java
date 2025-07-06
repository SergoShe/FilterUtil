package com.github.SergoShe.filter;

import lombok.Getter;

@Getter
public enum Type {
    STRING("string"),
    INTEGER("integer"),
    FLOAT("float");

    private final String type;

    Type(String type) {
        this.type = type;
    }

}
