package com.fer.hr.du.service;

import com.fer.hr.du.model.KnowledgeItemEntity;
import com.fer.hr.du.model.LevelEntity;
import com.fer.hr.du.model.User;
import com.fer.hr.du.model.UserDTO;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.model.game.DTO.LevelDTO;
import com.fer.hr.du.model.game.KnowledgeItem;
import com.fer.hr.du.repository.KnowledgeItemRepository;
import com.fer.hr.du.repository.LevelRepository;
import com.fer.hr.du.repository.UserRepository;
import com.fer.hr.du.util.GameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private KnowledgeItemRepository knowledgeItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LevelRepository levelRepository;

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user = userRepository.save(user);
        UserDTO newUSerDTO = new UserDTO();
        newUSerDTO.setId(user.getId());
        newUSerDTO.setUsername(user.getUserName());
        return newUSerDTO;
    }

    public UserDTO getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        if (user.isPresent()) {
            userDTO.setUsername(user.get().getUserName());
            userDTO.setId(user.get().getId());
        }
        return userDTO;
    }

    public Integer getScore(Long userId, String knowledgeItemName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        KnowledgeItemEntity knowledgeItem = knowledgeItemRepository.findByName(knowledgeItemName);

        if (knowledgeItem == null) {
            throw new RuntimeException("Knowledge item not found");
        }

        Integer score = user.getKnowledgeItems().get(knowledgeItem);
        if (score == null) {
            score = 0;
        }

        return score;
    }

    public void updateScore(Long userId, String knowledgeItemName, Integer newScore) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        KnowledgeItemEntity knowledgeItem = knowledgeItemRepository.findByName(knowledgeItemName);

        if (knowledgeItem == null) {
            throw new RuntimeException("Knowledge item not found");
        }

        Integer currentScore = user.getKnowledgeItems().get(knowledgeItem);

        if (currentScore == null) {
            currentScore = 0;
        }

        user.getKnowledgeItems().put(knowledgeItem, handleUserKnowledgeITemScore(currentScore, newScore));
        userRepository.save(user);
    }

    public void updateOverallScore(Long userId, Float additionalScore) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Double currentOverallScore = user.getOverallScore();
        if (currentOverallScore == null) {
            currentOverallScore = 0.0;
        }
        float newOverallScore = currentOverallScore.floatValue() + additionalScore;
        user.setOverallScore((double) newOverallScore);
        userRepository.save(user);
    }

    public float getUserOverallScore(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Double score = user.getOverallScore();
        if (score == null) {
            score = 0.0;
        }
        return score.floatValue();
    }

    private int handleUserKnowledgeITemScore(int currentScore, int newSore) {
        if (newSore == 0) {
            return currentScore;
        }
        if (newSore == -1) {
            if (currentScore == 0) {
                return currentScore;
            } else {
                return currentScore -1;
            }
        }
        if (newSore == 1) {
            if (currentScore == 2) {
                return currentScore;
            } else {
                return currentScore + 1;
            }
        }
        return currentScore;
    }

    public List<KnowledgeItemEntity> getLowestScoreKnowledgeItems(Long userId, int limit) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getKnowledgeItems().entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public KnowledgeItemEntity getKnowledgeItemByName(String knowledgeItemName) {
        KnowledgeItemEntity knowledgeItem = knowledgeItemRepository.findByName(knowledgeItemName);
        if (knowledgeItem == null) {
            throw new RuntimeException("Knowledge item not found");
        }
        return knowledgeItem;
    }

    public List<LevelEntity> getLevelsByKnowledgeItem(KnowledgeItemEntity knowledgeItem) {
        return levelRepository.findByKnowledgeItem(knowledgeItem);
    }

    public LevelEntity getRandomLevel(List<LevelEntity> levels) {
        if (levels.isEmpty()) {
            throw new RuntimeException("No levels found for the given knowledge item");
        }
        Random random = new Random();
        return levels.get(random.nextInt(levels.size()));
    }

    public GameDTO wrapLevelInGameDTO(LevelEntity level) {
        LevelDTO levelDTO = new LevelDTO(
                level
                        .getScreenStates()
                        .stream()
                        .map(GameConverter::convertToScreenState)
                        .collect(Collectors.toList()),
                GameConverter.convertToKnowledgeItem(level.getKnowledgeItem())
        );

        return new GameDTO(Collections.singletonList(levelDTO));
    }

    public GameDTO getRandomLevelWithKnowledgeItem(String knowledgeItemName) {
        KnowledgeItemEntity knowledgeItem = getKnowledgeItemByName(knowledgeItemName);
        List<LevelEntity> levels = getLevelsByKnowledgeItem(knowledgeItem);
        LevelEntity randomLevel = getRandomLevel(levels);
        return wrapLevelInGameDTO(randomLevel);
    }

    public List<UserDTO> getTopUsersByOverallPoints(int limit) {
        /*List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "overallScore"));
        return users.stream()
                .limit(limit)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUsername(user.getUserName());
                    if (user.getOverallScore() == null) {
                        userDTO.setOverallScore(0.00);
                    } else {
                        userDTO.setOverallScore(user.getOverallScore());
                    }
                    return userDTO;
                })
                .collect(Collectors.toList());*/
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUsername(user.getUserName());
                    //userDTO.setOverallScore(user.getOverallScore());
                    if (user.getOverallScore() == null) {
                        userDTO.setOverallScore(0.00);
                    } else {
                        userDTO.setOverallScore(user.getOverallScore());
                    }
                    return userDTO;
                })
                .sorted((u1, u2) -> {
                    if (u1.getOverallScore() == null && u2.getOverallScore() == null) {
                        return 0;
                    } else if (u1.getOverallScore() == null) {
                        return 1;
                    } else if (u2.getOverallScore() == null) {
                        return -1;
                    } else {
                        return u2.getOverallScore().compareTo(u1.getOverallScore());
                    }
                })
                .limit(limit)
                .collect(Collectors.toList());
    }
}
