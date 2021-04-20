package com.haystac.graphml.yed;

import com.haystac.graphml.Color;
import com.haystac.graphml.Defaults;
import com.haystac.graphml.Graphics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Creates yEd graph files using extended GraphML.
 * <p>
 * Execute {@link #initialize()} first. Afterwards, add nodes and edges.
 *
 * @see <a href="https://www.yworks.com/products/yed">yEd Graph Editor</a>
 */
public class YedDoc extends GraphmlDoc implements Defaults {

    public static final String ID_EDGE_GRAPHICS = "d0";

    public static final String ID_NODE_GRAPHICS = "d1";

    private static final ShapeNode DEFAULT_NODE = new ShapeNode();

    private static final PolyLineEdge DEFAULT_EDGE = new PolyLineEdge();

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

    static String decodeColor(Color color) {
        return "#" + color.getRgbCode();
    }

    static void setFont(Element nodeLabel, Graphics<Document, Element, ?> graphics) {
        String fontFamily = graphics.getFontFamily();
        if (fontFamily != null) {
            nodeLabel.setAttribute("fontFamily", fontFamily);
        }
        int fontSize = graphics.getFontSize();
        if (fontSize != -1) {
            nodeLabel.setAttribute("fontSize", String.valueOf(fontSize));
        }
        String fontStyle = graphics.getFontStyle();
        if (fontStyle != null) {
            nodeLabel.setAttribute("fontStyle", fontStyle);
        }
    }

    /**
     * Initializes document.
     *
     * @throws RuntimeException if already initialized.
     */
    public YedDoc initialize() throws RuntimeException {
        if (initialized) {
            throw new RuntimeException("Already initialized. " + YedDoc.class.getName());
        } else {
            createStructure();
            addGraph(GraphType.DIRECTED);
            initialized = true;
        }

        return this;
    }

    private <T extends Graphics<Document, Element, ?>> Element createData(T graphics, T def, String keyId) {
        if (!initialized) {
            throw new RuntimeException("Not initialized. " + YedDoc.class.getName());
        }
        if (graphics == null) {
            graphics = def;
        }
        graphics.setDefaults(this);

        Document document = getDocument();

        Element data = document.createElement("data");
        data.setAttribute("key", keyId);
        graphics.append(document, data);

        return data;
    }

    /**
     * Creates edge and returns the related edge ID.
     * <p>
     * Uses default edge-type. Does not set label.
     *
     * @throws RuntimeException if not initialized.
     */
    public String addEdge(String sourceId, String targetId) throws RuntimeException {
        return add(sourceId, targetId, DEFAULT_EDGE);
    }

    /**
     * Creates edge and returns the related edge ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String add(String sourceId, String targetId, YedEdge<?> edge) throws RuntimeException {
        Element data = createData(edge, DEFAULT_EDGE, ID_EDGE_GRAPHICS);

        Document document = getDocument();

        String edgeId = "e" + edgeCounter++;
        Element element = document.createElement("edge");
        element.setAttribute("id", edgeId);
        element.setAttribute("source", sourceId);
        element.setAttribute("target", targetId);
        element.appendChild(data);

        getDefaultGraph().appendChild(element);

        return edgeId;
    }

    /**
     * Adds the default node and returns the related node ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String addNode(String label) throws RuntimeException {
        return add(DEFAULT_NODE.label(label));
    }

    /**
     * Appends a new node and returns the related node ID.
     *
     * @throws RuntimeException if not initialized.
     */
    public String add(YedNode<?> node) throws RuntimeException {
        Element data = createData(node, DEFAULT_NODE, ID_NODE_GRAPHICS);

        Document document = getDocument();

        String nodeId = "n" + nodeCounter++;
        Element element = document.createElement("node");
        element.setAttribute("id", nodeId);
        element.appendChild(data);

        getDefaultGraph().appendChild(element);

        return nodeId;
    }

    /**
     * Gets the yEd graph element.
     */
    public Element getDefaultGraph() {
        return getGraphs().get(0);
    }

    /**
     * Creates default root element, named "graphml".
     * <p>
     * Adds yEd namespace information.
     * <p>
     * Will automatically be called, as overrides {@link GraphmlDoc#createRoot()}.
     */
    @Override
    protected Element createRoot() {
        Document document = getDocument();

        Element element = document.createElementNS(
            "http://graphml.graphdrawing.org/xmlns",
            "graphml");
        element.setAttributeNS(
            XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
            XMLConstants.XMLNS_ATTRIBUTE + ":" + "y",
            "http://www.yworks.com/xml/graphml");
        document.appendChild(element);

        return element;
    }

    /**
     * Creates default data structures for yEd files.
     * <p>
     * Integrated in {@link #initialize()}.
     */
    protected void createStructure() {
        Document document = getDocument();
        Element root = getRoot();

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
    }
}