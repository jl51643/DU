package com.fer.hr.du.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class GameEntity {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LevelEntity> levels = new ArrayList<>();
}

