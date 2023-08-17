package com.github.guninigor75.news_line.entity;

import java.util.Arrays;

public enum Type {
    SPORT,
    CULTURE,
    INTERNET,
    POLITIC,
    ECONOMIC;

    public static Type getCategories(String name) {
        return Arrays.stream(Type.values()).filter(el -> el.name().equals(name.toUpperCase())).findFirst().orElseThrow();
    }

}
