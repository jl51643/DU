package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("ChoiceState")
public class ChoiceStateEntity extends AbstractStateEntity {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    private int activeChoice;

    @Getter @Setter
    @ElementCollection
    private List<Choice> choices;
}

