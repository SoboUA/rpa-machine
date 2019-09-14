package com.rpa.controltower.model;

public enum Category {

    FESTIVAL("Фестивалі"),
    CONFERENCEC("Конференції"),
    CONCERTS("Концерти"),
    EXHIBITIONS("Виставки"),
    OTHER("Інше");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
