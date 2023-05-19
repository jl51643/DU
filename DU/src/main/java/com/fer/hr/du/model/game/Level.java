package com.fer.hr.du.model.game;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    public Level(Long id, String name, Game game) {
        this.id = id;
        this.name = name;
        this.game = game;
    }

    public Level() {
    }

    public Level(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Game.class)
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", game=" + game +
                '}';
    }
}
