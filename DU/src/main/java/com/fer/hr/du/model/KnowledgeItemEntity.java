package com.fer.hr.du.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "knowledge_items", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class KnowledgeItemEntity {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String name;
}
