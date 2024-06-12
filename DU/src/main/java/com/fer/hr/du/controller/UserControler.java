package com.fer.hr.du.controller;

import com.fer.hr.du.model.KnowledgeItemEntity;
import com.fer.hr.du.model.UserDTO;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.repository.KnowledgeItemRepository;
import com.fer.hr.du.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControler {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUserDTO = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }

    @GetMapping("/{userId}/score")
    public ResponseEntity<Integer> getUserScore(@PathVariable Long userId, @RequestParam String knowledgeItemName) {
        try {
            Integer score = userService.getScore(userId, knowledgeItemName);
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/score")
    public ResponseEntity<Void> updateUserScore(@PathVariable Long userId, @RequestParam String knowledgeItemName, @RequestParam Integer newScore) {
        try {
            userService.updateScore(userId, knowledgeItemName, newScore);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/overallScore")
    public ResponseEntity<Void> updateUserOverallScore(@PathVariable Long userId, @RequestParam Float additionalScore) {
        try {
            userService.updateOverallScore(userId, additionalScore);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/overallPoints")
    public ResponseEntity<Float> getUserOverallScore(@PathVariable Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserOverallScore(userId));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/lowestScoreItems")
    public ResponseEntity<List<KnowledgeItemEntity>> getLowestScoreKnowledgeItems(@PathVariable Long userId, @RequestParam(defaultValue = "3") int limit) {
        try {
            List<KnowledgeItemEntity> items = userService.getLowestScoreKnowledgeItems(userId, limit);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/randomLevel")
    public ResponseEntity<GameDTO> getRandomLevelWithKnowledgeItem(@RequestParam String knowledgeItemName) {
        try {
            GameDTO gameDTO = userService.getRandomLevelWithKnowledgeItem(knowledgeItemName);
            return new ResponseEntity<>(gameDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/topUsers")
    public ResponseEntity<List<UserDTO>> getTopUsersByOverallPoints(@RequestParam(defaultValue = "3") int limit) {
        try {
            List<UserDTO> users = userService.getTopUsersByOverallPoints(limit);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
