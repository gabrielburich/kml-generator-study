package com.github.gabrielburich.kml;

import com.github.gabrielburich.map.Coordinate;
import com.github.gabrielburich.map.Marker;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// TODO do really do tests
public class KMLCreatorTest {

    @Test
    public void writeFileTest() throws ParserConfigurationException {
        KMLCreator kmlCreator = new KMLCreator();

        Marker marker = new Marker();
        marker.setName("Hercilio Luz Bridge");
        marker.setDescription("Bridge in Florianopolis");
        marker.setLatitude(-27.184972);
        marker.setLongitude(-48.502638);

        kmlCreator.addPlaceMark(marker);

        File outputFile = new File("test.kml");
        kmlCreator.writeFile(outputFile);
    }

    @Test
    public void writeFileWithCoordinatesTest() throws ParserConfigurationException {
        KMLCreator kmlCreator = new KMLCreator();
        List<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(new Coordinate(37.42257124044786,-122.0848938459612,17D));
        coordinates.add(new Coordinate(37.42211922626856,-122.0849580979198,17D));
        coordinates.add(new Coordinate(37.42207183952619,-122.0847469573047,17D));
        coordinates.add(new Coordinate(37.42209006729676,-122.0845725380962,17D));
        coordinates.add(new Coordinate(37.42215932700895,-122.0845954886723,17D));
        coordinates.add(new Coordinate(37.42227278564371,-122.0838521118269,17D));
        coordinates.add(new Coordinate(37.42203539112084,-122.083792243335,17D));
        coordinates.add(new Coordinate(37.42209006957106,-122.0835076656616,17D));
        coordinates.add(new Coordinate(37.42200987395161,-122.0834709464152,17D));
        coordinates.add(new Coordinate(37.4221046494946,-122.0831221085748,17D));
        coordinates.add(new Coordinate(37.42226503990386,-122.0829247374572,17D));
        coordinates.add(new Coordinate(37.42231242843094,-122.0829339169385,17D));
        coordinates.add(new Coordinate(37.42225046087618,-122.0833837359737,17D));
        coordinates.add(new Coordinate(37.42234159228745,-122.0833607854248,17D));
        coordinates.add(new Coordinate(37.42237075460644,-122.0834204551642,17D));
        coordinates.add(new Coordinate(37.42251292011001,-122.083659133885,17D));
        coordinates.add(new Coordinate(37.42265873093781,-122.0839758438952,17D));
        coordinates.add(new Coordinate(37.42265143972521,-122.0842374743331,17D));
        coordinates.add(new Coordinate(37.4226514386435,-122.0845036949503,17D));
        coordinates.add(new Coordinate(37.42261133916315,-122.0848020460801,17D));
        coordinates.add(new Coordinate(37.42256395055121,-122.0847882750515,17D));
        coordinates.add(new Coordinate(37.42257124044786,-122.0848938459612,17D));


        kmlCreator.addPolygon(coordinates);

        File outputFile = new File("test_coordinate__b.kml");
        kmlCreator.writeFile(outputFile);
    }
}
