package com.github.gabrielburich.map;

import com.github.gabrielburich.kml.configuration.AltitudeMode;
import com.github.gabrielburich.kml.configuration.Extrude;
import com.github.gabrielburich.kml.configuration.Tessellate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineString {

    private String name;
    private List<Coordinate> coordinates;
    private Extrude extrude;
    private AltitudeMode altitudeMode;
    private Tessellate tessellate = Tessellate.TESSELLATE_TRUE;
    private String styleUrl;

}
