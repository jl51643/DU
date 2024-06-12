package com.fer.hr.du.model;

import com.fer.hr.du.model.game.states.QuizAnswerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("QuizState")
public class QuizStateEntity extends AbstractStateEntity {

    @Getter @Setter
    @Column(length = 1000)
    private String question;

    @Getter @Setter
    @ElementCollection
    private List<String> answers;

    @Getter @Setter
    @ElementCollection
    private List<Integer> imageAnswers;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private QuizAnswerType answersType;

    @Getter @Setter
    private int indexOfCorrectAnswer;

}

