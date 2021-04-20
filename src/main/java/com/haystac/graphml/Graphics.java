package com.haystac.graphml;

import lombok.Getter;
import lombok.Setter;

/**
 * GraphML element
 *
 * @param <D> document type
 * @param <P> parent element type
 */
public abstract class Graphics<D, P, T extends Graphics<D, P, T>> implements Defaults {

    @Getter
    @Setter
    private Defaults defaults;

    @Setter
    private String fontFamily;

    @Setter
    private int fontSize = -1;

    @Setter
    private String fontStyle;

    @Getter
    @Setter
    private Color color;

    @Getter
    @Setter
    private String label;

    public abstract void append(D document, P parent);

    protected abstract T self();

    public T defaults(Defaults defaults) {
        this.defaults = defaults;
        return self();
    }

    public T fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return self();
    }

    public T fontSize(int fontSize) {
        this.fontSize = fontSize;
        return self();
    }

    public T fontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        return self();
    }

    public T color(Color color) {
        this.color = color;
        return self();
    }

    public T label(String label) {
        this.label = label;
        return self();
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
}
