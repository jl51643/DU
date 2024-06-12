package com.fer.hr.du.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class CollectableContainer {

    @Getter @Setter
    private String title;

    @Override
    public String toString() {
        return "CollectableContainer{" +
                "title='" + title + '\'' +
                '}';
    }
}
