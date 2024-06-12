package com.fer.hr.du.model;

import jakarta.persistence.*;
import kotlin.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("MatchPairsState")
public class MatchPairsStateEntity extends AbstractStateEntity {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    @ElementCollection
    private List<PairsCard> cards;

    @Getter @Setter
    @ElementCollection
    private List<Pair<Integer, Integer>> pairs;
}

