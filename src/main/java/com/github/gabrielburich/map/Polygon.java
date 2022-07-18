package com.github.gabrielburich.map;

import com.github.gabrielburich.kml.configuration.AltitudeMode;
import com.github.gabrielburich.kml.configuration.Extrude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Polygon {

    private String name;
    private List<Coordinate> coordinates;
    private Extrude extrude = Extrude.EXTUDE_TRUE;
    private AltitudeMode altitudeMode = AltitudeMode.RELATIVE_TO_GROUND;
    private String styleUrl;

}
