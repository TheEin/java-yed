package com.haystac.graphml;

/**
 * GraphML element
 *
 * @param <D> document type
 * @param <P> parent element type
 */
public abstract class Graphics<D, P> {

    protected abstract void append(D document, P parent);
}
