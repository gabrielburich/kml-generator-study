package com.github.gabrielburich.kml;

import com.github.gabrielburich.map.Coordinate;
import com.github.gabrielburich.map.LineString;
import com.github.gabrielburich.map.Marker;
import com.github.gabrielburich.map.Polygon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

public class KMLCreator {

    private final Document document;
    private final Element root;

    /**
     * Creates a new KML file
     * @throws ParserConfigurationException can throw
     */
    public KMLCreator() throws ParserConfigurationException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();
        document = builder.newDocument();
        Element kml = document.createElementNS(KMLConstants.NAMESPACE_URL, KMLConstants.KML);
        document.appendChild(kml);
        root = document.createElement(KMLConstants.DOCUMENT);
        kml.appendChild(root);
    }

    /**
     * Add a Marker to the map
     * @param marker marker locations
     */
    public void addPlaceMark(Marker marker) {
        Element placeMark = createElement(KMLConstants.PLACE_MARK, root);
        createElementWithValue(KMLConstants.NAME, marker.getName(), placeMark);
        createElementWithValue(KMLConstants.DESCRIPTION, marker.getDescription(), placeMark);

        Element point = createElement(KMLConstants.POINT, placeMark);
        if (marker.getAltitude() > 0) {
            createElementWithValue(KMLConstants.ALTITUDE_MODE, "absolute", point);
        }

        String coordinates = marker.getLongitude() + "," + marker.getLatitude() + "," + marker.getAltitude();
        createElementWithValue(KMLConstants.COORDINATES, coordinates, point);

        if (marker.getStyleUrl() != null) {
            createElementWithValue(KMLConstants.STYLE_URL, marker.getStyleUrl(), placeMark);
        }
    }

    /**
     * Adds a polygon to the KML file
     * @param polygon polygon definition
     */
    public void addPolygon(Polygon polygon) {
        Element placeMark = createElement(KMLConstants.PLACE_MARK, root);

        if (polygon.getName() != null) {
            createElementWithValue(KMLConstants.NAME, polygon.getName(), placeMark);
        }

        Element polygonElement = createElement(KMLConstants.POLYGON, placeMark);


        if (polygon.getExtrude() != null) {
            createElementWithValue(KMLConstants.EXTRUDE, polygon.getExtrude().getValue(), polygonElement);
        }

        if (polygon.getAltitudeMode() != null) {
            createElementWithValue(KMLConstants.ALTITUDE_MODE, polygon.getAltitudeMode().getValue(), polygonElement);
        }

        Element outerBoundaryIs = createElement(KMLConstants.OUTER_BOUNDARY_IS, polygonElement);
        Element linearRing = createElement(KMLConstants.LINEAR_RING, outerBoundaryIs);
        Element coordinatesElement = createElement(KMLConstants.COORDINATES, linearRing);

        for (Coordinate coordinate : polygon.getCoordinates()) {
            String coordinates = coordinate.getLongitude() + "," + coordinate.getLatitude() + "," + coordinate.getAltitude() + "\n";
            coordinatesElement.appendChild(document.createTextNode(coordinates));
        }

        if (polygon.getStyleUrl() != null) {
            createElementWithValue(KMLConstants.STYLE_URL, polygon.getStyleUrl(), placeMark);
        }
    }

    /**
     * Add's a polyline
     * @param lineString Polyline definition
     */
    public void addLineString(LineString lineString) {
        Element placeMark = createElement(KMLConstants.PLACE_MARK, root);
        Element lineStringElement = createElement(KMLConstants.LINE_STRING, placeMark);

        if (lineString.getName() != null) {
            createElementWithValue(KMLConstants.NAME, lineString.getName(), placeMark);
        }

        if (lineString.getTessellate() != null) {
            createElementWithValue(KMLConstants.TESSELLATE, lineString.getTessellate().getValue(), lineStringElement);
        }

        if (lineString.getExtrude() != null) {
            createElementWithValue(KMLConstants.EXTRUDE, lineString.getExtrude().getValue(), lineStringElement);
        }

        if (lineString.getAltitudeMode() != null) {
            createElementWithValue(KMLConstants.ALTITUDE_MODE, lineString.getAltitudeMode().getValue(), lineStringElement);
        }

        Element coordinatesElement = createElement(KMLConstants.COORDINATES, lineStringElement);

        for (Coordinate coordinate : lineString.getCoordinates()) {
            String coordinates = coordinate.getLongitude() + "," + coordinate.getLatitude() + "," + coordinate.getAltitude() + "\n";
            var textNode = document.createTextNode(coordinates);
            coordinatesElement.appendChild(textNode);
        }

        if (lineString.getStyleUrl() != null) {
            createElementWithValue(KMLConstants.STYLE_URL, lineString.getStyleUrl(), placeMark);
        }
    }

    public void addStyle(String id, Map<String, Map<String, String>> styles) {
        Element rootStyleElement = createElement(KMLConstants.STYLE, root);
        rootStyleElement.setAttribute("id", id);

        for(Map.Entry<String, Map<String, String>> stylePerElement: styles.entrySet()) {
            Element stylePropertyElement = createElement(stylePerElement.getKey(), rootStyleElement);

            for (Map.Entry<String, String> elementStyleProperty : stylePerElement.getValue().entrySet()) {
                if (elementStyleProperty.getKey().equals(KMLConstants.HREF)) {
                    Element iconElement = createElement(KMLConstants.ICON, stylePropertyElement);
                    createElementWithValue(KMLConstants.HREF, elementStyleProperty.getValue(), iconElement);
                    continue;
                }

                Element styleValue = document.createElement(elementStyleProperty.getKey());
                stylePropertyElement.appendChild(styleValue);

                styleValue.appendChild(document.createTextNode(elementStyleProperty.getValue()));
            }
        }
    }

    private Element createElement(String elementName, Element parent) {
        Element element = document.createElement(elementName);
        parent.appendChild(element);
        return element;
    }

    private Element createElementWithValue(String elementName, String elementValue, Element parent) {
        Element element = createElement(elementName, parent);
        element.appendChild(document.createTextNode(elementValue));
        return element;
    }


    /**
     * Write the kml in one file
     * @param file the output file
     * @return true when creates with success and false when was error
     */
    public boolean writeFile(File file) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource src = new DOMSource(document);
            StreamResult out = new StreamResult(file);
            transformer.transform(src, out);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
