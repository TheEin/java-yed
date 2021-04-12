package com.haystac.javayed;

public enum AutoSizePolicy {

    CONTENT("content"),
    SIZE("node_size"),
    HEIGHT("node_height"),
    WIDTH("node_width");

    private final String autoSizePolicy;

    AutoSizePolicy(String autoSizePolicy) {
        this.autoSizePolicy = autoSizePolicy;
    }

    public String getAutoSizePolicy() {
        return autoSizePolicy;
    }
}
