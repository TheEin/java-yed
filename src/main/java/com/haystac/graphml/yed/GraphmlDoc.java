package com.haystac.graphml.yed;

import org.w3c.dom.Element;

import java.util.LinkedList;
import java.util.List;

/**
 * GraphML document.
 *
 * @author Adrian Wilke
 * @see http://graphml.graphdrawing.org/specification/xsd.html
 */
public class GraphmlDoc extends XmlDoc {

    protected Element root;

    protected List<Element> graphs = new LinkedList<>();

    /**
     * Creates default root element, named "graphml".
     */
    public GraphmlDoc createRoot() {
        if (document == null) {
            createDocument();
        }

        Element element = document.createElement("graphml");
        document.appendChild(element);

        root = element;

        return this;
    }

    /**
     * Gets the default root element, named "graphml".
     */
    public Element getRoot() {
        if (root == null) {
            createRoot();
        }

        return root;
    }

    /**
     * Appends graph element to root.
     */
    public GraphmlDoc addGraph(GraphType edgedefault) {
        if (root == null) {
            createRoot();
        }

        Element graph = document.createElement("graph");
        graph.setAttribute("edgedefault", edgedefault.toString().toLowerCase());
        root.appendChild(graph);
        graphs.add(graph);
        return this;
    }

    public enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

}