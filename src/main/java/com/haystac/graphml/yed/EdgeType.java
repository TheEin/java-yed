package com.haystac.graphml.yed;

import com.haystac.graphml.Color;

/**
 * Sets color based on ID.
 * <p>
 * Uses colors in {@link Color} and {@link Color#getColor(int)}.
 *
 * @author Adrian Wilke
 */
public class EdgeType {

    public static final String DEFAULT_COLOR = "#000000";

    protected String color;

    public EdgeType(Integer id) {
        if (id == null) {
            color = DEFAULT_COLOR;
        } else {
            color = computeColor(id);
        }
    }

    protected static String computeColor(int number) {

        // Computed gray
        // Range: [1, 200]
        int code = number + 1;
        if (code >= 1 && code <= 200) {
            String hex = Integer.toHexString(code);
            if (code < 10) {
                hex = "0" + hex;
            }
            return "#" + hex + hex + hex;
        }

        // Default color

        return DEFAULT_COLOR;
    }

    public String getColor() {
        return color;
    }
}