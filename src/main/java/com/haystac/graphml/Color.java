package com.haystac.graphml;

/**
 * yEd colors.
 */
public enum Color {

    ORANGE("ff9900"),
    YELLOW("ffcc00"),
    GREEN("99cc00"),
    BLUE("99ccff"),
    PURPLE("cc99ff"),
    RED("ff0000"),
    CYAN("00FFFF"),
    MAGENTA("FF00FF"),
    WHITE("ffffff"),
    BLACK("000000");

    private final String rgbCode;

    Color(String rgbCode) {
        this.rgbCode = rgbCode;
    }

    public String getRgbCode() {
        return rgbCode;
    }
}