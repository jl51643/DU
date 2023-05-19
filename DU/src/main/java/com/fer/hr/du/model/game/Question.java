package com.fer.hr.du.model.game;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    public Question() {
    }

    public Question(String text, String correctAnswer, Level level, List<Answer> answers) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.level = level;
        this.answers = answers;
    }

    public Question(Long id, String text, String correctAnswer, Level level, List<Answer> answers) {
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.level = level;
        this.answers = answers;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @ManyToOne
    private Level level;

    @OneToMany
    private List<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", level=" + level +
                ", answers=" + answers +
                '}';
    }
}
