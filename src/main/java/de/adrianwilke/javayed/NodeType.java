package de.adrianwilke.javayed;

/**
 * Provide node attributes
 *
 * @author Adrian Wilke
 */
public class NodeType {

    public static final NodeType DEFAULT = new NodeType();

    private double width = 30;

    private double height = 30;

    private Color color = Color.WHITE;

    private Shape shape = Shape.RECTANGLE;

    private AutoSizePolicy autoSizePolicy = AutoSizePolicy.CONTENT;

    private Configuration configuration = Configuration.STANDARD;

    private int leftInset;

    private int rightInset;

    private int topInset;

    private int bottomInset;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public NodeType width(double width) {
        this.width = width;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public NodeType height(double height) {
        this.height = height;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public NodeType color(Color color) {
        this.color = color;
        return this;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public NodeType shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public AutoSizePolicy getAutoSizePolicy() {
        return autoSizePolicy;
    }

    public void setAutoSizePolicy(AutoSizePolicy autoSizePolicy) {
        this.autoSizePolicy = autoSizePolicy;
    }

    public NodeType autoSizePolicy(AutoSizePolicy autoSizePolicy) {
        this.autoSizePolicy = autoSizePolicy;
        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public NodeType configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public int getLeftInset() {
        return leftInset;
    }

    public void setLeftInset(int leftInset) {
        this.leftInset = leftInset;
    }

    public int getRightInset() {
        return rightInset;
    }

    public void setRightInset(int rightInset) {
        this.rightInset = rightInset;
    }

    public int getTopInset() {
        return topInset;
    }

    public void setTopInset(int topInset) {
        this.topInset = topInset;
    }

    public int getBottomInset() {
        return bottomInset;
    }

    public void setBottomInset(int bottomInset) {
        this.bottomInset = bottomInset;
    }

    public NodeType insets(int all) {
        leftInset = all;
        rightInset = all;
        topInset = all;
        bottomInset = all;
        return this;
    }
}