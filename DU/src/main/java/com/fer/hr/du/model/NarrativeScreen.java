package com.fer.hr.du.model;

public class NarrativeScreen extends Screen {

    public NarrativeScreen() {
        super("narrative_screen");
    }

    public NarrativeScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "NarrativeScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
