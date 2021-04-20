package com.haystac.graphml;

import static com.haystac.graphml.Color.WHITE;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Node attributes
 *
 * @param <D> document type
 * @param <P> parent element type
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Node<D, P, T extends Node<D, P, T>> extends Graphics<D, P, T> {

    private double width = 30;

    private double height = 30;

    private Shape shape = Shape.RECTANGLE;

    private AutoSize autoSize = AutoSize.CONTENT;

    private Configuration configuration;

    private int leftInset;

    private int rightInset;

    private int topInset;

    private int bottomInset;

    protected Node() {
        setColor(WHITE);
    }

    public T width(double width) {
        this.width = width;
        return self();
    }

    public T height(double height) {
        this.height = height;
        return self();
    }

    public T shape(Shape shape) {
        this.shape = shape;
        return self();
    }

    public T autoSize(AutoSize autoSize) {
        this.autoSize = autoSize;
        return self();
    }

    public T configuration(Configuration configuration) {
        this.configuration = configuration;
        return self();
    }

    public T insets(int all) {
        leftInset = all;
        rightInset = all;
        topInset = all;
        bottomInset = all;
        return self();
    }
}