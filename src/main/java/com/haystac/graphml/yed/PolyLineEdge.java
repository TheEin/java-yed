package com.haystac.graphml.yed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PolyLineEdge extends YedEdge<PolyLineEdge> {

    @Override
    protected PolyLineEdge self() {
        return this;
    }

    @Override
    public void append(Document document, Element parent) {
        Element edgeType = document.createElement("y:PolyLineEdge");

        super.append(document, edgeType);

        appendEdgeLabel(document, edgeType);
        appendLineStyle(document, edgeType);

        parent.appendChild(edgeType);
    }

    protected void appendEdgeLabel(Document document, Element parent) {
        String label = getLabel();
        if (label != null) {
            Element edgeLabel = document.createElement("y:EdgeLabel");
            edgeLabel.appendChild(document.createTextNode(label));
            YedDoc.setFont(edgeLabel, this);
            parent.appendChild(edgeLabel);
        }
    }

    protected void appendLineStyle(Document document, Element parent) {
        Element lineStyle = document.createElement("y:LineStyle");
        lineStyle.setAttribute("color", YedDoc.decodeColor(getColor()));
        lineStyle.setAttribute("type", "line");
        lineStyle.setAttribute("width", "1.0");
        parent.appendChild(lineStyle);
    }
}
