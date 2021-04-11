package de.adrianwilke.javayed;

/**
 * yEd colors.
 *
 * @author Adrian Wilke
 */
public enum Color {

    ORANGE("#ff9900"),
    YELLOW("#ffcc00"),
    GREEN("#99cc00"),
    BLUE("#99ccff"),
    PURPLE("#cc99ff"),
    RED("#ff0000"),
    CYAN("#00FFFF"),
    MAGENTA("#FF00FF"),
    WHITE("#ffffff");

    private final String code;

    Color(java.lang.String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}