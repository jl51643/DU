package com.fer.hr.du.repository;

import com.fer.hr.du.model.KnowledgeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeItemRepository extends JpaRepository<KnowledgeItemEntity, Long> {
    KnowledgeItemEntity findByName(String knowledgeItemName);

}
