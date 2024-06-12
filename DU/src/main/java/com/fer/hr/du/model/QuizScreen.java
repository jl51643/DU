package com.fer.hr.du.model;

public class QuizScreen extends Screen {

    public QuizScreen() {
        super("quiz_screen");
    }

    public QuizScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "QuizScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
