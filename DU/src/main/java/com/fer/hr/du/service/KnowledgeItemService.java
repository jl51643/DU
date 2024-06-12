package com.fer.hr.du.service;

import com.fer.hr.du.model.KnowledgeItemEntity;
import com.fer.hr.du.repository.KnowledgeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class KnowledgeItemService {

    @Autowired
    private KnowledgeItemRepository knowledgeItemRepository;

    public List<KnowledgeItemEntity> getAllKnowledgeItems() {
        return knowledgeItemRepository.findAll();
    }

    public KnowledgeItemEntity addNewKnowledgeItem(@RequestBody KnowledgeItemEntity knowledgeItem) {
        KnowledgeItemEntity existingItem = knowledgeItemRepository.findByName(knowledgeItem.getName());
        if (existingItem != null) {
            return existingItem;
        }

        return knowledgeItemRepository.save(knowledgeItem);
    }
}
