package com.fer.hr.du.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "`user`")
public class User {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    @ElementCollection
    @CollectionTable(name = "user_knowledge_items", joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyJoinColumn(name = "knowledge_item_id")
    @Column(name = "score")
    private Map<KnowledgeItemEntity, Integer> knowledgeItems = new HashMap<>();


    @Getter @Setter
    private Double overallScore;
}
