package de.adrianwilke.javayed;

public enum Configuration {

    STANDARD(null),
    CROPPING("CroppingLabel");

    private final String configuration;

    Configuration(String configuration) {
        this.configuration = configuration;
    }

    public String getConfiguration() {
        return configuration;
    }
}
