package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PairsCard {

    @Column(name = "`value`")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

