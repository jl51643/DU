package com.fer.hr.du.model;

public class MatchPairScreen extends Screen {

    public MatchPairScreen() {
        super("match_pair_screen");
    }

    public MatchPairScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "MatchPairScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
