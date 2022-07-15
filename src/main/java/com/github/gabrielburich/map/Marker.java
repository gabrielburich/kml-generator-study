package com.github.gabrielburich.map;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Marker {

    private String name;
    private String description;
    private LocalDateTime date;
    private Double latitude;
    private Double longitude;
    private Double altitude = 0D;

}
