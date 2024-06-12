package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CollectObjectsScreen")
public class CollectObjectsScreenEntity extends ScreenEntity {
    public CollectObjectsScreenEntity() {
        this.setRoute("collect_objects_screen");
    }
}

