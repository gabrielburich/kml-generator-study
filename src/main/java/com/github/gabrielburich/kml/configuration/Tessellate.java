package com.github.gabrielburich.kml.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tessellate {

    TESSELLATE_TRUE("1"),
    TESSELLATE_FALSE("0");

    private final String value;

}
