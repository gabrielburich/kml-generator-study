package com.github.gabrielburich.kml.polygon;

public enum AltitudeMode {

    CLAMP_TO_GROUND("clampToGround"),
    RELATIVE_TO_GROUND("relativeToGround"),
    ABSOLUTE("absolute");

    private final String value;

    AltitudeMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
