package com.fer.hr.du.controller;

import com.fer.hr.du.model.KnowledgeItemEntity;
import com.fer.hr.du.repository.KnowledgeItemRepository;
import com.fer.hr.du.service.KnowledgeItemService;
import kong.unirest.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ki")
public class KnowledgeItemController {

    @Autowired
    private KnowledgeItemService knowledgeItemService;

    @GetMapping
    public ResponseEntity<List<KnowledgeItemEntity>> getAllKnowledgeItems() {
        return ResponseEntity.status(HttpStatus.OK).body(knowledgeItemService.getAllKnowledgeItems());
    }

    @PostMapping
    public ResponseEntity<KnowledgeItemEntity> addNewKnowledgeItem(@RequestBody KnowledgeItemEntity knowledgeItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(knowledgeItemService.addNewKnowledgeItem(knowledgeItem));
    }
}
