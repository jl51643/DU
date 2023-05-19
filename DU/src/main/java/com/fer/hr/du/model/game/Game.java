package com.fer.hr.du.model.game;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    public Game() {
    }

    public Game(String name, boolean isPrivate, List<Level> levels) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.levels = levels;
    }

    public Game(Long id, String name, boolean isPrivate, List<Level> levels) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
        this.levels = levels;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_private")
    private boolean isPrivate;

    @OneToMany
    private List<Level> levels;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPrivate=" + isPrivate +
                ", levels=" + levels +
                '}';
    }
}
