package com.github.gabrielburich.map;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Marker extends Coordinate {

    private String name;
    private String description;
    private LocalDateTime date;

    public Marker(Double latitude, Double longitude, Double altitude, String name, String description, LocalDateTime date) {
        super(latitude, longitude, altitude);
        this.name = name;
        this.description = description;
        this.date = date;
    }
}
