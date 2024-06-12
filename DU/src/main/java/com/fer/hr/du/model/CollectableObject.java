package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class CollectableObject {

    @Getter @Setter
    @Column(name = "`value`")
    private String value;

    @Getter @Setter
    private int indexOfRightContainer;

    @Override
    public String toString() {
        return "CollectableObject{" +
                "value='" + value + '\'' +
                ", indexOfRightContainer=" + indexOfRightContainer +
                '}';
    }
}

