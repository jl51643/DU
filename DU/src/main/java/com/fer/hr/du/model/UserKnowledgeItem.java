package com.fer.hr.du.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class UserKnowledgeItem {

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "knowledge_item_id")
    private KnowledgeItemEntity knowledgeItem;

    @Getter @Setter
    private Integer score;
}
