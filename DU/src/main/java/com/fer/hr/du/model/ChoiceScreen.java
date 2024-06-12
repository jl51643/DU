package com.fer.hr.du.model;

public class ChoiceScreen extends Screen {
    public ChoiceScreen() {
        super("choice_screen");
    }

    public ChoiceScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "ChoiceScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}

