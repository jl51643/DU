package com.fer.hr.du.repository;

import com.fer.hr.du.model.KnowledgeItemEntity;
import com.fer.hr.du.model.LevelEntity;
import com.fer.hr.du.model.game.KnowledgeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    List<LevelEntity> findByKnowledgeItem(KnowledgeItemEntity knowledgeItem);
}
