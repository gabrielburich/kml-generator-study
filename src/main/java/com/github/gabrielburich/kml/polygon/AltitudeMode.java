package com.github.gabrielburich.kml.polygon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AltitudeMode {

    CLAMP_TO_GROUND("clampToGround"),
    RELATIVE_TO_GROUND("relativeToGround"),
    ABSOLUTE("absolute");

    private final String value;

}
