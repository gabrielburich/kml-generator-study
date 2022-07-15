package com.github.gabrielburich.kml.polygon;

public enum Extrude {

    EXTUDE_TRUE("1"),
    EXTUDE_FALSE("0");

    private final String value;

    Extrude(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
