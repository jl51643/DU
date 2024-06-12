package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CollectObjectsState")
public class CollectObjectsStateEntity extends AbstractStateEntity {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    @ElementCollection
    private List<CollectableObject> objects = new ArrayList<>();

    @Getter @Setter
    @ElementCollection
    private List<CollectableContainer> containers = new ArrayList<>();
}

