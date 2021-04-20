package com.haystac.graphml.yed;

import com.haystac.graphml.Defaults;

import org.w3c.dom.Element;

import javax.xml.XMLConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Creates yEd graph files using extended GraphML.
 * <p>
 * Execute {@link #initialize()} first. Afterwards, add nodes and edges.
 *
 * @author Adrian Wilke
 * @see https://www.yworks.com/products/yed
 */
public class YedDoc extends GraphmlDoc implements Defaults {

    public static final ShapeNode DEFAULT_NODE = new ShapeNode();

    public static final String ID_EDGE_GRAPHICS = "d0";

    public static final String ID_NODE_GRAPHICS = "d1";

    protected boolean initialized = false;

    protected long nodeCounter = 0;

    protected long edgeCounter = 0;

    @Getter
    @Setter
    private String fontFamily;

    @Getter
    @Setter
    private int fontSize = -1;

    @Getter
    @Setter
    private String fontStyle;

    /**
     * Initializes document.
     *
     * @throws RuntimeException if already initialized.
     */
    public YedDoc initialize() throws RuntimeException {
        if (initialized) {
            throw new RuntimeException("Already initialized. " + YedDoc.class.getName());
        } else {
            createRoot();
            createStructure();
            addGraph(GraphType.DIRECTED);
            initialized = true;
        }

        return this;
    }

    /**
     * Creates edge and returns the related edge ID.
     * <p>
     * Uses default edge-type. Does not set label.
     *
     * @throws RuntimeException if not initialized.
     */
    public String createEdge(String sourceId, String targetId) throws RuntimeException {
        return createEdge(sourceId, targetId, null, null);
    }

    /**
     * Creates edge and returns the related edge ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String createEdge(String sourceId, String targetId, String label, Integer type) throws RuntimeException {

        if (!initialized) {
            throw new RuntimeException("Not initialized. " + YedDoc.class.getName());
        }

        String edgeId = "e" + edgeCounter++;

        Element lineStyle = document.createElement("y:LineStyle");
        lineStyle.setAttribute("color", new EdgeType(type).getColor());
        lineStyle.setAttribute("type", "line");
        lineStyle.setAttribute("width", "1.0");

        Element arrows = document.createElement("y:Arrows");
        arrows.setAttribute("source", "none");
        arrows.setAttribute("target", "delta");

        Element edgeType = document.createElement("y:PolyLineEdge");
        edgeType.appendChild(lineStyle);
        edgeType.appendChild(arrows);

        if (label != null) {
            Element edgeLabel = document.createElement("y:EdgeLabel");
            edgeLabel.appendChild(document.createTextNode(label));
            edgeType.appendChild(edgeLabel);
            if (fontFamily != null) {
                edgeLabel.setAttribute("fontFamily", fontFamily);
            }
            if (fontSize != -1) {
                edgeLabel.setAttribute("fontSize", String.valueOf(fontSize));
            }
            if (fontStyle != null) {
                edgeLabel.setAttribute("fontStyle", fontStyle);
            }
        }

        Element data = document.createElement("data");
        data.setAttribute("key", ID_EDGE_GRAPHICS);
        data.appendChild(edgeType);

        Element edge = document.createElement("edge");
        edge.setAttribute("id", edgeId);
        edge.setAttribute("source", sourceId);
        edge.setAttribute("target", targetId);
        edge.appendChild(data);

        getDefaultGraph().appendChild(edge);

        return edgeId;
    }

    /**
     * Adds the default node and returns the related node ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String addNode(String label) throws RuntimeException {
        return add(DEFAULT_NODE.defaults(this).label(label));
    }

    /**
     * Appends a new node and returns the related node ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String add(YedNode<?> node) throws RuntimeException {
        if (!initialized) {
            throw new RuntimeException("Not initialized. " + YedDoc.class.getName());
        }
        if (node == null) {
            node = DEFAULT_NODE;
        }

        Element data = document.createElement("data");
        data.setAttribute("key", ID_NODE_GRAPHICS);
        node.append(document, data);

        String nodeId = "n" + nodeCounter++;
        Element nodeElement = document.createElement("node");
        nodeElement.setAttribute("id", nodeId);
        nodeElement.appendChild(data);

        getDefaultGraph().appendChild(nodeElement);

        return nodeId;
    }

    /**
     * Gets the yEd graph element.
     */
    public Element getDefaultGraph() {
        return graphs.get(0);
    }

    /**
     * Creates default root element, named "graphml".
     * <p>
     * Adds yEd namespace information.
     * <p>
     * Will automatically be called, as overrides {@link GraphmlDoc#createRoot()}.
     */
    @Override
    public GraphmlDoc createRoot() {
        if (document == null) {
            createDocument();
        }

        Element element = document.createElementNS(
            "http://graphml.graphdrawing.org/xmlns",
            "graphml");
        element.setAttributeNS(
            XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
            XMLConstants.XMLNS_ATTRIBUTE + ":" + "y",
            "http://www.yworks.com/xml/graphml");
        document.appendChild(element);

        root = element;

        return this;
    }

    /**
     * Creates default data structures for yEd files.
     * <p>
     * Integrated in {@link #initialize()}.
     */
    public YedDoc createStructure() {
        Element edgeGraphics = document.createElement("key");
        edgeGraphics.setAttribute("id", ID_EDGE_GRAPHICS);
        edgeGraphics.setAttribute("for", "edge");
        edgeGraphics.setAttribute("yfiles.type", "edgegraphics");
        root.appendChild(edgeGraphics);

        Element nodeGraphics = document.createElement("key");
        nodeGraphics.setAttribute("id", ID_NODE_GRAPHICS);
        nodeGraphics.setAttribute("for", "node");
        nodeGraphics.setAttribute("yfiles.type", "nodegraphics");
        root.appendChild(nodeGraphics);

        return this;
    }

}