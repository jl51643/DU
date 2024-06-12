package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MemoryCardState {
    @Column(name = "`value`")
    private String value;
    /*private boolean isFacedUp;
    private boolean isPairedUp;*/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
// getters and setters
}

