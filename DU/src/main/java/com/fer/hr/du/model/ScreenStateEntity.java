package com.fer.hr.du.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "screen_states")
public class ScreenStateEntity {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private LevelEntity level;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screen;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private AbstractStateEntity state;

    @Getter @Setter
    @OneToMany(mappedBy = "previousScreenState", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScreenStateEntity> nextScreens = new ArrayList<>();

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_screen_state_id")
    private ScreenStateEntity previousScreenState;
}

