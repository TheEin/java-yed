package de.adrianwilke.javayed;

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
		new YedDoc().createNode("x");
	}

	@Test(expected = RuntimeException.class)
	public void testAlreadeInitialized() {
		new YedDoc().initialize().initialize();
	}

	@Test
	public void testCreateGraph() {
		YedDoc yedDoc = new YedDoc().initialize();

		String nodeA = yedDoc.createNode("a simple node");
		String nodeB = yedDoc.createNode("b type 0", new NodeType(Color.ORANGE));
		String nodeC = yedDoc.createNode("c type 1", new NodeType(Color.YELLOW));
		String nodeD = yedDoc.createNode("d type 2", new NodeType(Color.GREEN));
		String nodeE = yedDoc.createNode("e type 3", new NodeType(Color.BLUE));
		String nodeF = yedDoc.createNode("f type 4", new NodeType(Color.PURPLE));
		String nodeG = yedDoc.createNode("g type 5", new NodeType(Color.RED));
		String nodeH = yedDoc.createNode("h type 6 computed color", new NodeType(Color.CYAN));
		yedDoc.createNode("type 100 computed color", new NodeType(Color.MAGENTA));
		yedDoc.createNode("type 1000 computed default color", new NodeType(Color.MAGENTA));

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