package com.haystac.graphml.yed;

import com.haystac.graphml.AutoSize;
import com.haystac.graphml.Configuration;
import com.haystac.graphml.Node;
import com.haystac.graphml.Shape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class YedNode<N extends YedNode<N>> extends Node<Document, Element, N> {

    static String decodeShape(Shape shape) {
        return shape.toString().toLowerCase();
    }

    @Override
    public void append(Document document, Element parent) {
        appendGeometry(document, parent);
        appendFill(document, parent);
        appendNodeLabel(document, parent);
        appendShape(document, parent);
    }

    protected void appendGeometry(Document document, Element parent) {
        Element geometry = document.createElement("y:Geometry");
        geometry.setAttribute("width", String.valueOf(getWidth()));
        geometry.setAttribute("height", String.valueOf(getHeight()));
        parent.appendChild(geometry);
    }

    protected void appendFill(Document document, Element parent) {
        Element fill = document.createElement("y:Fill");
        fill.setAttribute("color", YedDoc.decodeColor(getColor()));
        fill.setAttribute("transparent", "false");
        parent.appendChild(fill);
    }

    protected void appendNodeLabel(Document document, Element parent) {
        String label = getLabel();
        if (label != null) {
            Element nodeLabel = document.createElement("y:NodeLabel");
            nodeLabel.appendChild(document.createTextNode(label));
            nodeLabel.setAttribute("width", String.valueOf(getWidth()));
            nodeLabel.setAttribute("height", String.valueOf(getHeight()));

            YedDoc.setFont(nodeLabel, this);
            setAutoSize(nodeLabel);
            setInsets(nodeLabel);

            parent.appendChild(nodeLabel);
        }
    }

    protected void setAutoSize(Element nodeLabel) {
        AutoSize autoSize = getAutoSize();
        if (autoSize != null) {
            nodeLabel.setAttribute("autoSizePolicy", decodeAutoSize(autoSize));
        }
        Configuration configuration = getConfiguration();
        if (configuration != null) {
            nodeLabel.setAttribute("configuration", decodeConfiguration(configuration));
        }
    }

    protected String decodeAutoSize(AutoSize autoSize) {
        switch (autoSize) {
            case CONTENT:
                return "content";
            case SIZE:
                return "node_size";
            case HEIGHT:
                return "node_height";
            case WIDTH:
                return "node_width";
            default:
                throw new IllegalArgumentException("Unknown AutoSize: " + autoSize);
        }
    }

    protected String decodeConfiguration(Configuration configuration) {
        switch (configuration) {
            case CROPPING:
                return "CroppingLabel";
            default:
                throw new IllegalArgumentException("Unknown Configuration: " + configuration);
        }
    }

    protected void setInsets(Element nodeLabel) {
        int leftInset = getLeftInset();
        if (leftInset != 0) {
            nodeLabel.setAttribute("leftInset", String.valueOf(leftInset));
        }
        int rightInset = getRightInset();
        if (rightInset != 0) {
            nodeLabel.setAttribute("rightInset", String.valueOf(rightInset));
        }
        int topInset = getTopInset();
        if (topInset != 0) {
            nodeLabel.setAttribute("topInset", String.valueOf(topInset));
        }
        int bottomInset = getBottomInset();
        if (bottomInset != 0) {
            nodeLabel.setAttribute("bottomInset", String.valueOf(bottomInset));
        }
    }

    protected void appendShape(Document document, Element parent) {
        Element shape = document.createElement("y:Shape");
        shape.setAttribute("type", decodeShape(getShape()));
        parent.appendChild(shape);
    }
}
