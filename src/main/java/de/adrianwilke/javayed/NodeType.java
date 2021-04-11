package de.adrianwilke.javayed;

/**
 * Sets color based on ID.
 * <p>
 * Uses colors in {@link Color} and {@link Color#getColor(int)}.
 *
 * @author Adrian Wilke
 */
public class NodeType {

    public static final NodeType DEFAULT = new NodeType();

    private Color color = Color.WHITE;

    private Shape shape = Shape.RECTANGLE;

    public NodeType() {
    }

    public NodeType(Color color) {
        this.color = color;
    }

    public NodeType(Shape shape) {
        this.shape = shape;
    }

    public NodeType(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}