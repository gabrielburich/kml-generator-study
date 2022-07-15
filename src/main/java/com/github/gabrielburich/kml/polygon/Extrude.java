package com.github.gabrielburich.kml.polygon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Extrude {

    EXTUDE_TRUE("1"),
    EXTUDE_FALSE("0");

    private final String value;

}
