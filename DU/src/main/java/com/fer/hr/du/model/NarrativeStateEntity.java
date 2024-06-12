package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("NarrativeState")
public class NarrativeStateEntity extends AbstractStateEntity {

    @Getter @Setter
    @ElementCollection
    @Column(length = 1000)
    private List<String> list;

    @Getter @Setter
    @Column(length = 1000)
    private String backgroundImageId;

    @Getter @Setter
    @Column(length = 1000)
    private String narratorImageId;
}

