package com.fer.hr.du.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("SpinningWheelScreenState")
public class SpinningWheelScreenStateEntity extends AbstractStateEntity {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    private int indexOfPrise;

    @Getter @Setter
    @ElementCollection
    private List<String> listOfPrises;
}

