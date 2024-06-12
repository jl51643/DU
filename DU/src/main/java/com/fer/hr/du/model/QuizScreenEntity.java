package com.fer.hr.du.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("QuizScreen")
public class QuizScreenEntity extends ScreenEntity {
    public QuizScreenEntity() {
        this.setRoute("quiz_screen");
    }
}

