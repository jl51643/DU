package com.fer.hr.du.model;

public class SpinningWheelScreen extends Screen {
    public SpinningWheelScreen() {
        super("spinning_wheel_screen");
    }

    public SpinningWheelScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "SpinningWheelScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
