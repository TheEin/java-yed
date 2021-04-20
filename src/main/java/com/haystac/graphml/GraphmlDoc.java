package com.haystac.graphml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

/**
 * GraphML document.
 *
 * @see <a href="http://graphml.graphdrawing.org/specification/xsd.html">GraphML Schema Documentation</a>
 */
public class GraphmlDoc extends XmlDoc {

    @Getter
    private final List<Element> graphs = new LinkedList<>();

    private Element root;

    static String decodeEdgeType(EdgeType edgeType) {
        return edgeType.toString().toLowerCase();
    }

    /**
     * Creates default root element, named "graphml".
     */
    protected Element createRoot() {
        Document document = getDocument();

        Element element = document.createElement("graphml");
        document.appendChild(element);

        return element;
    }

    /**
     * Gets the default root element, named "graphml".
     */
    public Element getRoot() {
        if (root == null) {
            root = createRoot();
        }
        return root;
    }

    /**
     * Appends graph element to root.
     */
    public GraphmlDoc addGraph(EdgeType edgeType) {
        Document document = getDocument();
        Element element = getRoot();
        Element graph = document.createElement("graph");
        graph.setAttribute("edgedefault", decodeEdgeType(edgeType));
        element.appendChild(graph);
        graphs.add(graph);
        return this;
    }
}