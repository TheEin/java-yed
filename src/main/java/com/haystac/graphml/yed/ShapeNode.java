package com.haystac.graphml.yed;

import static com.haystac.graphml.yed.YedDoc.ID_NODE_GRAPHICS;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ShapeNode extends YedNode<ShapeNode> {

    @Override
    protected ShapeNode self() {
        return this;
    }

    @Override
    protected void append(Document document, Element parent) {
        Element shapeNode = document.createElement("y:ShapeNode");

        super.append(document, shapeNode);

        Element data = document.createElement("data");
        data.setAttribute("key", ID_NODE_GRAPHICS);
        data.appendChild(shapeNode);
    }
}
