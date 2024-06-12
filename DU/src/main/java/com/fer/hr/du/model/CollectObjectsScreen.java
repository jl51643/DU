package com.fer.hr.du.model;

public class CollectObjectsScreen extends Screen {

    public CollectObjectsScreen() {
        super("collect_objects_screen");
    }

    public CollectObjectsScreen(String route) {
        super(route);
    }

    @Override
    public String toString() {
        return "CollectObjectsScreen{" +
                "route='" + route + '\'' +
                '}';
    }
}
