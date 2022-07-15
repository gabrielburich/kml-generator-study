package com.github.gabrielburich.kml;

import com.github.gabrielburich.map.Marker;
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

public class KMLCreator {

    private final Document doc;
    private final Element root;

    /**
     * Creates a new KML file
     * @throws ParserConfigurationException can throw
     */
    public KMLCreator() throws ParserConfigurationException {
        var factory = DocumentBuilderFactory.newInstance();
        var builder = factory.newDocumentBuilder();
        doc = builder.newDocument();
        Element kml = doc.createElementNS(KMLConstants.NAMESPACE_URL, KMLConstants.KML);
        doc.appendChild(kml);
        root = doc.createElement(KMLConstants.DOCUMENT);
        kml.appendChild(root);
    }

    public void addPlaceMark(Marker marker) {
        // creates the place mark
        Element placeMark = doc.createElement(KMLConstants.PLACEMARK);
        root.appendChild(placeMark);

        // add the name to the place mark
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(marker.getName()));
        placeMark.appendChild(name);

        // add the description to the place mark
        Element desc = doc.createElement("description");
        // aqui vão todos os detalhes que iriam em um infowindow
        desc.appendChild(doc.createTextNode(marker.getDescription()));
        placeMark.appendChild(desc);

        // creates a point and add to placeMark
        Element point = doc.createElement("Point");
        placeMark.appendChild(point);

        // if there is altitude, inserts
        if (marker.getAltitude() > 0) {
            Element altitudeMode = doc.createElement("altitudeMode");
            altitudeMode.appendChild(doc.createTextNode("absolute"));
            point.appendChild(altitudeMode);
        }

        // creates a coordinates
        Element coords = doc.createElement("coordinates");
        coords.appendChild(doc.createTextNode(marker.getLongitude() + ", " + marker.getLatitude() + ", " + marker.getAltitude()));
        point.appendChild(coords);

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
            DOMSource src = new DOMSource(doc);
            StreamResult out = new StreamResult(file);
            transformer.transform(src, out);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}