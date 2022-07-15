package com.github.gabrielburich.map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Marker extends Coordinate {

    private String name;
    private String description;

    public Marker(Double latitude, Double longitude, Double altitude, String name, String description) {
        super(latitude, longitude, altitude);
        this.name = name;
        this.description = description;
    }
}
