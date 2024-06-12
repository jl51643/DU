package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MatchPairScreen")
public class MatchPairScreenEntity extends ScreenEntity {
    public MatchPairScreenEntity() {
        this.setRoute("match_pair_screen");
    }
}
