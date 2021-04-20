package com.haystac.graphml.yed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ShapeNode extends YedNode<ShapeNode> {

    @Override
    protected ShapeNode self() {
        return this;
    }

    @Override
    public void append(Document document, Element parent) {
        Element shapeNode = document.createElement("y:ShapeNode");

        super.append(document, shapeNode);

        parent.appendChild(shapeNode);
    }
}
