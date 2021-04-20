package com.haystac.graphml.yed;

import static com.haystac.graphml.Color.BLACK;

import com.haystac.graphml.Graphics;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Edge attributes
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Edge<D, P, T extends Edge<D, P, T>> extends Graphics<D, P, T> {

    protected Edge() {
        setColor(BLACK);
    }
}