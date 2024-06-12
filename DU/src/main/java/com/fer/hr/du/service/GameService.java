package com.fer.hr.du.service;

import com.fer.hr.du.model.*;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.model.game.DTO.LevelDTO;
import com.fer.hr.du.model.game.ScreenState;
import com.fer.hr.du.model.game.states.*;
import com.fer.hr.du.repository.*;
import com.fer.hr.du.util.GameConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ScreenStateRepository screenStateRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private KnowledgeItemRepository knowledgeItemRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RestTemplate restTemplate;

    //private final ConcurrentHashMap<String, Long> imageCache = new ConcurrentHashMap<>();
    private final Map<String, Long> imageCache = new HashMap<>();

    @Transactional
    public GameEntity saveGame(GameDTO gameDTO) {
        GameEntity gameEntity = new GameEntity();

        gameEntity = gameRepository.save(gameEntity);

        for (LevelDTO levelDTO : gameDTO.getLevels()) {
            LevelEntity levelEntity = new LevelEntity();
            levelEntity.setGame(gameEntity);

            if (levelDTO.getKnowledgeItem() != null) {
                String knowledgeItemName = levelDTO.getKnowledgeItem().getName();
                KnowledgeItemEntity knowledgeItemEntity = knowledgeItemRepository.findByName(knowledgeItemName);
                if (knowledgeItemEntity == null) {
                    knowledgeItemEntity = new KnowledgeItemEntity();
                    knowledgeItemEntity.setName(knowledgeItemName);
                    knowledgeItemEntity = knowledgeItemRepository.save(knowledgeItemEntity);
                }
                levelEntity.setKnowledgeItem(knowledgeItemEntity);
            }

            for (ScreenState screenState : levelDTO.getScreenStates()) {
                if (screenState != null) {
                    ScreenStateEntity screenStateEntity = saveScreenStateEntity(screenState, levelEntity, null);
                    levelEntity.getScreenStates().add(screenStateEntity);
                    screenStateEntity.setLevel(levelEntity);
                }
            }

            levelRepository.save(levelEntity);
            gameEntity.getLevels().add(levelEntity);
        }

        return gameEntity;
    }

    private ScreenStateEntity saveScreenStateEntity(ScreenState screenState, LevelEntity levelEntity, ScreenStateEntity previousScreenStateEntity) {
        ScreenStateEntity screenStateEntity = new ScreenStateEntity();
        //screenStateEntity.setLevel(levelEntity);

        screenStateEntity.setScreen(convertScreenToEntity(screenState.getScreen()));
        screenRepository.save(screenStateEntity.getScreen());

        screenStateEntity.setState(convertStateToEntity(screenState.getState()));
        stateRepository.save(screenStateEntity.getState());

        screenStateEntity.setPreviousScreenState(previousScreenStateEntity);
        screenStateRepository.save(screenStateEntity);

        for (ScreenState nextScreenState : screenState.getNextScreens()) {
            ScreenStateEntity nextScreenEntity = saveScreenStateEntity(nextScreenState, levelEntity, screenStateEntity);
            screenStateEntity.getNextScreens().add(nextScreenEntity);
        }

        return screenStateEntity;
    }

    // Conversion methods
    private ScreenEntity convertScreenToEntity(Screen screen) {
        if (screen instanceof ChoiceScreen) {
            return new ChoiceScreenEntity("choice_screen");
        } else if (screen instanceof MemoryGameScreen) {
            return new MemoryGameScreenEntity();
        } else if (screen instanceof NarrativeScreen) {
            return new NarrativeScreenEntity();
        } else if (screen instanceof QuizScreen) {
            return new QuizScreenEntity();
        } else if (screen instanceof SpinningWheelScreen) {
            return new SpinningWheelScreenEntity();
        } else if (screen instanceof MatchPairScreen) {
            return new MatchPairScreenEntity();
        } else if (screen instanceof CollectObjectsScreen) {
            return new CollectObjectsScreenEntity();
        } else {
            throw new IllegalArgumentException("Unknown screen type");
        }
    }

    private AbstractStateEntity convertStateToEntity(AbstractState state) {
        if (state instanceof ChoiceState choiceState) {
            ChoiceStateEntity entity = new ChoiceStateEntity();
            entity.setType(choiceState.getType());
            entity.setDescription(choiceState.getDescription());
            entity.setActiveChoice(choiceState.getActiveChoice());
            entity.setChoices(convertChoiceUrlToId(choiceState.getChoices()));
            return entity;
        } else if (state instanceof CollectObjectsState collectObjectsState) {
            CollectObjectsStateEntity entity = new CollectObjectsStateEntity();
            entity.setType(collectObjectsState.getType());
            entity.setDescription(collectObjectsState.getDescription());
            entity.setObjects(collectObjectsState.getObjects());
            entity.setContainers(collectObjectsState.getContainers());
            return entity;
        } else if (state instanceof MatchPairsState matchPairsState) {
            MatchPairsStateEntity entity = new MatchPairsStateEntity();
            entity.setType(matchPairsState.getType());
            entity.setDescription(matchPairsState.getDescription());
            entity.setCards(matchPairsState.getCards());
            entity.setPairs(matchPairsState.getPairs());
            return entity;
        } else if (state instanceof MemoryGameState memoryGameState) {
            MemoryGameStateEntity entity = new MemoryGameStateEntity();
            entity.setType(memoryGameState.getType());
            entity.setDescription(memoryGameState.getDescription());
            entity.setProgress(memoryGameState.getProgress());
            entity.setCards(memoryGameState.getCards());
            entity.setPairs(memoryGameState.getPairs());
            return entity;
        } else if (state instanceof NarrativeState narrativeState) {
            NarrativeStateEntity entity = new NarrativeStateEntity();
            entity.setType(narrativeState.getType());
            entity.setList(narrativeState.getList());

            // Download and save images
            String backgroundImageId = saveImageFromUrl(narrativeState.getBackgroundUrl());
            String narratorImageId = saveImageFromUrl(narrativeState.getNarratorUrl());

            entity.setBackgroundImageId(backgroundImageId);
            entity.setNarratorImageId(narratorImageId);

            return entity;
        } else if (state instanceof QuizState quizState) {
            QuizStateEntity entity = new QuizStateEntity();
            entity.setType(quizState.getType());
            entity.setQuestion(quizState.getQuestion());
            entity.setAnswers(quizState.getAnswers());
            entity.setImageAnswers(quizState.getImageAnswers());
            entity.setAnswersType(quizState.getAnswersType());
            entity.setIndexOfCorrectAnswer(quizState.getIndexOfCorrectAnswer());
            return entity;
        } else if (state instanceof SpinningWheelScreenState spinningWheelScreenState) {
            SpinningWheelScreenStateEntity entity = new SpinningWheelScreenStateEntity();
            entity.setType(spinningWheelScreenState.getType());
            entity.setDescription(spinningWheelScreenState.getDescription());
            entity.setListOfPrises(spinningWheelScreenState.getListOfPrises());
            entity.setIndexOfPrise(spinningWheelScreenState.getIndexOfPrise());
            return entity;
        } else {
            throw new IllegalArgumentException("Unknown state type");
        }
    }

    private String saveImageFromUrl(String imageUrl) {
        if (imageCache.containsKey(imageUrl)) {
            return imageCache.get(imageUrl).toString();
        }
        try {
            URI uri = new URI(imageUrl.trim());

            byte[] imageBytes = restTemplate.getForObject(uri, byte[].class);

            if (imageBytes != null) {

                ImageEntity imageEntity = new ImageEntity();
                imageEntity.setImageData(imageBytes);
                imageEntity = imageRepository.save(imageEntity);

                imageCache.put(imageUrl, imageEntity.getId());

                return imageEntity.getId().toString();
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Choice> convertChoiceUrlToId(List<Choice> choices) {
        List<Choice> updatedChoices = new ArrayList<>();

        for (Choice choice : choices) {
            String imageUrl = choice.getChoiceUrl();
            String imageId = saveImageFromUrl(imageUrl);
            Choice updatedChoice = new Choice();
            updatedChoice.setDescription(choice.getDescription());
            updatedChoice.setButtonTitle(choice.getButtonTitle());
            updatedChoice.setChoiceUrl(imageId); // Set the choiceUrl to the image ID
            updatedChoices.add(updatedChoice);
        }

        return updatedChoices;
    }


    public GameDTO getGame(Long gameId) {
        Optional<GameEntity> game = gameRepository.findById(gameId);
        GameDTO gameDTO = new GameDTO();
        if (game.isPresent()) {
            gameDTO = GameConverter.convertToDTO(game.get());
            return gameDTO;
        } else {
            return null;
        }
        // return  gameDTO;
    }
}

