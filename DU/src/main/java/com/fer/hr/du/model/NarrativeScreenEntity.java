package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NarrativeScreen")
public class NarrativeScreenEntity extends ScreenEntity {
    public NarrativeScreenEntity() {
        this.setRoute("narrative_screen");
    }
}
