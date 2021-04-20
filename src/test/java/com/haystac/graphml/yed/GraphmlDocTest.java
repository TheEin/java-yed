package com.haystac.graphml.yed;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * Tests {@link GraphmlDoc}.
 */
public class GraphmlDocTest {

    @Test
    public void testRoot() {
        GraphmlDoc graphmlDoc = new GraphmlDoc();
        Element root = graphmlDoc.getRoot();

        Assert.assertEquals("Root element name is graphml", "graphml", root.getNodeName());

        Assert.assertEquals("XML document has one element", 1, graphmlDoc.getDocument().getChildNodes().getLength());
    }

    @Test
    public void testGraphCreation() {
        GraphmlDoc graphmlDoc = new GraphmlDoc().addGraph(GraphmlDoc.GraphType.DIRECTED);

        Assert.assertEquals("Root has one graph", 1, graphmlDoc.getRoot().getChildNodes().getLength());

        Assert.assertEquals("List of graphs has one element", 1, graphmlDoc.getGraphs().size());

        Assert.assertEquals("Required graph type is set", "directed",
            graphmlDoc.getGraphs().get(0).getAttribute("edgedefault"));
    }

}