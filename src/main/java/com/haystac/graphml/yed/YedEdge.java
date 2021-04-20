package com.haystac.graphml.yed;

import com.haystac.graphml.Edge;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class YedEdge<T extends YedEdge<T>> extends Edge<Document, Element, T> {

    @Override
    public void append(Document document, Element parent) {
        Element arrows = document.createElement("y:Arrows");
        arrows.setAttribute("source", "none");
        arrows.setAttribute("target", "delta");
        parent.appendChild(arrows);
    }
}