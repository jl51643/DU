package com.fer.hr.du.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Choice {
    @Getter @Setter
    @Column(length = 1000)
    private String description;

    @Getter @Setter
    private String buttonTitle;

    @Getter @Setter
    @Column(length = 1000)
    private String choiceUrl;

    @Override
    public String toString() {
        return "Choice{" +
                "description='" + description + '\'' +
                ", buttonTitle='" + buttonTitle + '\'' +
                ", choiceUrl='" + choiceUrl + '\'' +
                '}';
    }
}

