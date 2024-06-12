package com.fer.hr.du.model;

public class MemoryGameScreen extends Screen {

    public MemoryGameScreen() {
        super("memory_game_screen");
    }

    public MemoryGameScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "MemoryGameScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
