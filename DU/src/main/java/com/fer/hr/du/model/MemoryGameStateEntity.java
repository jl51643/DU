package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import kotlin.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("MemoryGameState")
public class MemoryGameStateEntity extends AbstractStateEntity {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    private float progress;

    @Getter @Setter
    @ElementCollection
    private List<MemoryCardState> cards;

    @Getter @Setter
    @ElementCollection
    private List<Pair<Integer, Integer>> pairs;
}
