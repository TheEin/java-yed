package com.haystac.graphml;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Node attributes
 *
 * @param <D> document type
 * @param <P> parent element type
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Node<D, P, N extends Node<D, P, N>> extends Graphics<D, P> {

    private Defaults defaults;

    private String label;

    private String fontFamily;

    private int fontSize = -1;

    private String fontStyle;

    private double width = 30;

    private double height = 30;

    private Color color = Color.WHITE;

    private Shape shape = Shape.RECTANGLE;

    private AutoSize autoSize = AutoSize.CONTENT;

    private Configuration configuration;

    private int leftInset;

    private int rightInset;

    private int topInset;

    private int bottomInset;

    public N defaults(Defaults defaults) {
        this.defaults = defaults;
        return (N) this;
    }

    public String getFontFamily() {
        if (fontFamily != null) {
            return fontFamily;
        }
        if (defaults != null) {
            return defaults.getFontFamily();
        }
        return null;
    }

    public int getFontSize() {
        if (fontSize != -1) {
            return fontSize;
        }
        if (defaults != null) {
            return defaults.getFontSize();
        }
        return -1;
    }

    public String getFontStyle() {
        if (fontStyle != null) {
            return fontStyle;
        }
        if (defaults != null) {
            return defaults.getFontStyle();
        }
        return null;
    }

    public N label(String label) {
        this.label = label;
        return (N) this;
    }

    public Node<D, P, N> fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Node<D, P, N> fontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Node<D, P, N> fontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return this;
    }

    public Node<D, P, N> width(double width) {
        this.width = width;
        return this;
    }

    public Node<D, P, N> height(double height) {
        this.height = height;
        return this;
    }

    public Node<D, P, N> color(Color color) {
        this.color = color;
        return this;
    }

    public Node<D, P, N> shape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public Node<D, P, N> autoSize(AutoSize autoSize) {
        this.autoSize = autoSize;
        return this;
    }

    public Node<D, P, N> configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public Node<D, P, N> insets(int all) {
        leftInset = all;
        rightInset = all;
        topInset = all;
        bottomInset = all;
        return this;
    }
}