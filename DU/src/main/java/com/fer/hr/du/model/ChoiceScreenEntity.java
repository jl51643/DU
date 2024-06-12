package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ChoiceScreen")
public class ChoiceScreenEntity extends ScreenEntity {

    public ChoiceScreenEntity(String route) {
        setRoute(route);
    }

    public ChoiceScreenEntity() {

    }
}

