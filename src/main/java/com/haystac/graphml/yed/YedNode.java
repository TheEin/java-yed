package com.haystac.graphml.yed;

import com.haystac.graphml.AutoSize;
import com.haystac.graphml.Configuration;
import com.haystac.graphml.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class YedNode<N extends YedNode<N>> extends Node<Document, Element, N> {

    @Override
    protected void append(Document document, Element parent) {
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
        fill.setAttribute("color", "#" + getColor().getRgbCode());
        fill.setAttribute("transparent", "false");
        parent.appendChild(fill);
    }

    protected void appendNodeLabel(Document document, Element parent) {
        if (getLabel() != null) {
            Element nodeLabel = document.createElement("y:NodeLabel");
            nodeLabel.appendChild(document.createTextNode(getLabel()));
            nodeLabel.setAttribute("width", String.valueOf(getWidth()));
            nodeLabel.setAttribute("height", String.valueOf(getHeight()));

            setFont(nodeLabel);
            setAutoSize(nodeLabel);
            setInsets(nodeLabel);

            parent.appendChild(nodeLabel);
        }
    }

    protected void setFont(Element nodeLabel) {
        if (getFontFamily() != null) {
            nodeLabel.setAttribute("fontFamily", getFontFamily());
        }
        if (getFontSize() != -1) {
            nodeLabel.setAttribute("fontSize", String.valueOf(getFontSize()));
        }
        if (getFontStyle() != null) {
            nodeLabel.setAttribute("fontStyle", getFontStyle());
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
        if (getLeftInset() != 0) {
            nodeLabel.setAttribute("leftInset", String.valueOf(getLeftInset()));
        }
        if (getRightInset() != 0) {
            nodeLabel.setAttribute("rightInset", String.valueOf(getRightInset()));
        }
        if (getTopInset() != 0) {
            nodeLabel.setAttribute("topInset", String.valueOf(getTopInset()));
        }
        if (getBottomInset() != 0) {
            nodeLabel.setAttribute("bottomInset", String.valueOf(getBottomInset()));
        }
    }

    protected void appendShape(Document document, Element parent) {
        Element shape = document.createElement("y:Shape");
        shape.setAttribute("type", getShape().toString().toLowerCase());
        parent.appendChild(shape);
    }
}
