package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SpinningWheelScreen")
public class SpinningWheelScreenEntity extends ScreenEntity {
    public SpinningWheelScreenEntity() {
        this.setRoute("spinning_wheel_screen");
    }
}

