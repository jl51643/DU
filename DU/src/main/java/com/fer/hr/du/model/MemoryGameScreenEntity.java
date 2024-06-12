package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MemoryGameScreen")
public class MemoryGameScreenEntity extends ScreenEntity {

    public MemoryGameScreenEntity() {
        this.setRoute("memory_game_screen");
    }
}
