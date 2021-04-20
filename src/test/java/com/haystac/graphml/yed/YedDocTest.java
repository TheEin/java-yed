package com.haystac.graphml.yed;

import com.haystac.graphml.Color;
import com.haystac.graphml.Node;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link YedDoc}.
 *
 * @author Adrian Wilke
 */
public class YedDocTest {

    @Test(expected = RuntimeException.class)
    public void testNotInitializedEdge() {
        new YedDoc().createEdge("x", "y");
    }

    @Test(expected = RuntimeException.class)
    public void testNotInitializedNode() {
        new YedDoc().addNode("x");
    }

    @Test(expected = RuntimeException.class)
    public void testAlreadeInitialized() {
        new YedDoc().initialize().initialize();
    }

    @Test
    public void testCreateGraph() {
        YedDoc yedDoc = new YedDoc().initialize();

        String nodeA = yedDoc.addNode("a simple node");
        ShapeNode node = new ShapeNode();
        String nodeB = yedDoc.add(node.label("b type 0").color(Color.ORANGE));
        String nodeC = yedDoc.add(node.label("c type 1").color(Color.YELLOW));
        String nodeD = yedDoc.add(node.label("d type 2").color(Color.GREEN));
        String nodeE = yedDoc.add(node.label("e type 3").color(Color.BLUE));
        String nodeF = yedDoc.add(node.label("f type 4").color(Color.PURPLE));
        String nodeG = yedDoc.add(node.label("g type 5").color(Color.RED));
        String nodeH = yedDoc.add(node.label("h type 6 computed color").color(Color.CYAN));
        yedDoc.add(node.label("type 100 computed color").color(Color.MAGENTA));
        yedDoc.add(node.label("type 1000 computed default color").color(Color.MAGENTA));

        yedDoc.createEdge(nodeA, nodeB);
        yedDoc.createEdge(nodeA, nodeB, "a to b no type", null);
        yedDoc.createEdge(nodeC, nodeD, null, null);
        yedDoc.createEdge(nodeE, nodeF, null, 0);
        yedDoc.createEdge(nodeG, nodeH, "g to h type 1", 1);

        Assert.assertEquals("graphml element exists", "graphml", yedDoc.getRoot().getNodeName());

        Assert.assertEquals("graph element exists", "graph", yedDoc.getDefaultGraph().getNodeName());

        Assert.assertEquals("2 keys", 2, yedDoc.getRoot().getElementsByTagName("key").getLength());

        Assert.assertEquals("10 nodes", 10, yedDoc.getDefaultGraph().getElementsByTagName("node").getLength());

        Assert.assertEquals("5 edges", 5, yedDoc.getDefaultGraph().getElementsByTagName("edge").getLength());
    }

}