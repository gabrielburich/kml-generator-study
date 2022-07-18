# kml-generator-study

Java KML generator. Simple structure to create KML files with markers, polygons and Path's with style.

# What is KML

> KML is a file format used to display geographic data in an Earth browser such as Google Earth.
> KML uses a tag-based structure with nested elements and attributes and is based on the XML standard
>
> -- <cite>KML Tutorial</cite>

- [KML Tutorial](https://developers.google.com/kml/documentation/kml_tut)
- [KML Reference](https://developers.google.com/kml/documentation/kmlreference)
- [KML Example](https://developers.google.com/static/kml/documentation/KML_Samples.kml)

# Where open a KML file

You can open a KML file on tools like Google Earth.    
I strongly recommend the Google Earth pro.

# Why not use libraries to do this

There are some libraries to do this in Java, but they are no longer maintained.
As the implementation creating the file is very simple, I followed it here.
So it is not necessary to add a library with vulnerabilities to the project.

Some examples:

- [Java API for KML](https://github.com/micromata/javaapiforkml)
- [Libkml](https://github.com/google/libkml)

# TODO
- [X] Markers
- [x] Info window (Description property)
- [X] Polygon
- [X] Polyline
- [X] Add Styles support
- [x] Add style to an element support
- [x] Improve documentation
- [ ] Improve tests
