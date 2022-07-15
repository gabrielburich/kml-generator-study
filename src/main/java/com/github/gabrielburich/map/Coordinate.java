package com.github.gabrielburich.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {

    private Double latitude;
    private Double longitude;
    private Double altitude = 0D;

}
