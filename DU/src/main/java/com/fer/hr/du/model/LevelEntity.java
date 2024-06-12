package com.fer.hr.du.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "levels")
public class LevelEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @Getter @Setter
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenStateEntity> screenStates = new ArrayList<>();

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "knowledge_item_id")
    private KnowledgeItemEntity knowledgeItem;

}

