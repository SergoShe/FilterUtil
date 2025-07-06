package com.github.SergoShe.filter;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class SortedData {
    private final ArrayList<String> stringList = new ArrayList<>();
    private final ArrayList<String> integerList = new ArrayList<>();
    private final ArrayList<String> floatList = new ArrayList<>();

    public void addValue(String value, Type type) {
        switch (type) {
            case INTEGER -> integerList.add(value);
            case FLOAT -> floatList.add(value);
            default -> {
                if (!value.isEmpty()) {
                    stringList.add(value);
                }
            }
        }
    }
}
