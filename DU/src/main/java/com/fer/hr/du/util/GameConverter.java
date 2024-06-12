package com.fer.hr.du.util;

import com.fer.hr.du.model.*;
import com.fer.hr.du.model.CollectableObject;
import com.fer.hr.du.model.MemoryCardState;
import com.fer.hr.du.model.PairsCard;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.model.game.DTO.LevelDTO;
import com.fer.hr.du.model.game.KnowledgeItem;
import com.fer.hr.du.model.game.ScreenState;
import com.fer.hr.du.model.game.states.*;
import kotlin.NotImplementedError;
import kotlin.Pair;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;
import java.util.stream.Collectors;

public class GameConverter {

    public static GameDTO convertToDTO(GameEntity gameEntity) {
        return new GameDTO(
                gameEntity
                        .getLevels()
                        .stream()
                        .map(GameConverter::convertToLevelDTO)
                        .collect(Collectors.toList())
        );
    }

    private static LevelDTO convertToLevelDTO(LevelEntity levelEntity) {
        return new LevelDTO(
                levelEntity
                        .getScreenStates()
                        .stream()
                        .map(GameConverter::convertToScreenState)
                        .collect(Collectors.toList()),
                convertToKnowledgeItem(levelEntity.getKnowledgeItem())
        );
    }

    public static ScreenState convertToScreenState(ScreenStateEntity screenStateEntity) {
        return new ScreenState(
                convertToScreen(screenStateEntity.getScreen()),
                convertToAbstractState(screenStateEntity.getState()),
                screenStateEntity
                        .getNextScreens()
                        .stream()
                        .map(GameConverter::convertToScreenState)
                        .collect(Collectors.toList())
        );
    }

    private static Screen convertToScreen(ScreenEntity screenEntity) {

        if (screenEntity instanceof HibernateProxy) {
            screenEntity = (ScreenEntity) Hibernate.unproxy(screenEntity);
        }

        return switch (screenEntity.getRoute()) {
            case "choice_screen" -> new ChoiceScreen(screenEntity.getRoute());
            case "memory_game_screen" -> new MemoryGameScreen(screenEntity.getRoute());
            case "narrative_screen" -> new NarrativeScreen(screenEntity.getRoute());
            case "quiz_screen" -> new QuizScreen(screenEntity.getRoute());
            case "spinning_wheel_screen" -> new SpinningWheelScreen(screenEntity.getRoute());
            case "match_pair_screen" -> new MatchPairScreen(screenEntity.getRoute());
            case "collect_objects_screen" -> new CollectObjectsScreen(screenEntity.getRoute());
            default -> throw new IllegalArgumentException("Unknown route: " + screenEntity.getRoute());
        };
    }

    private static AbstractState convertToAbstractState(AbstractStateEntity abstractStateEntity) {
        /*if (abstractStateEntity == null) {
            throw new IllegalArgumentException("Input entity cannot be null");
        }

        String stateType = abstractStateEntity.getType();*/

        if (abstractStateEntity instanceof HibernateProxy) {
            abstractStateEntity = (AbstractStateEntity) Hibernate.unproxy(abstractStateEntity);
        }

        switch (abstractStateEntity.getClass().getSimpleName()) {
            case "ChoiceStateEntity" -> {
                ChoiceStateEntity choiceEntity = (ChoiceStateEntity) abstractStateEntity;
                return convertToChoiceState(choiceEntity);
            }
            case "CollectObjectsStateEntity" -> {
                CollectObjectsStateEntity entity = (CollectObjectsStateEntity) abstractStateEntity;
                List<CollectableObject> shuffledObjects = new LinkedList<>(entity.getObjects());
                Collections.shuffle(shuffledObjects);
                return new CollectObjectsState(
                        entity.getDescription(),
                        shuffledObjects,
                        entity.getContainers());
            }
            case "MatchPairsStateEntity" -> {
                MatchPairsStateEntity matchEntity = (MatchPairsStateEntity) abstractStateEntity;
                return shuffleAndCreateMatchPairsState(matchEntity);
            }
            case "MemoryGameStateEntity" -> {
                MemoryGameStateEntity memoryEntity = (MemoryGameStateEntity) abstractStateEntity;
                return shuffleAndCreateMemoryGameState(memoryEntity);
            }
            case "NarrativeStateEntity" -> {
                NarrativeStateEntity narrativeEntity = (NarrativeStateEntity) abstractStateEntity;
                return new NarrativeState(
                        narrativeEntity.getList(),
                        narrativeEntity.getBackgroundImageId(),
                        narrativeEntity.getNarratorImageId()
                );
            }
            case "QuizStateEntity" -> {
                QuizStateEntity quizEntity = (QuizStateEntity) abstractStateEntity;
                return new QuizState(
                        quizEntity.getQuestion(),
                        quizEntity.getAnswers(),
                        quizEntity.getImageAnswers(),
                        quizEntity.getAnswersType(),
                        quizEntity.getIndexOfCorrectAnswer()
                );
            }
            case "SpinningWheelScreenStateEntity" -> {
                SpinningWheelScreenStateEntity spinEntity = (SpinningWheelScreenStateEntity) abstractStateEntity;
                return new SpinningWheelScreenState(
                        spinEntity.getListOfPrises(),
                        false,
                        -1,
                        spinEntity.getDescription());
            }
            default -> throw new NotImplementedError("Conversion for state type " + abstractStateEntity.getClass().getSimpleName() + " not implemented");
        }
    }

    private static ChoiceState convertToChoiceState(ChoiceStateEntity choiceEntity) {
        return new ChoiceState(
                choiceEntity.getChoices() == null ? Collections.emptyList() : new ArrayList<>(choiceEntity.getChoices()),
                choiceEntity.getActiveChoice(),
                choiceEntity.getDescription()
        );
    }


    private static MatchPairsState shuffleAndCreateMatchPairsState(MatchPairsStateEntity matchEntity) {
        // Get the list of cards
        List<PairsCard> cards = matchEntity.getCards();

        // Create a copy of the original list to shuffle
        List<PairsCard> shuffledCards = new ArrayList<>(cards);

        // Shuffle the cards
        Collections.shuffle(shuffledCards);

        // Create a map to track the new indices of the shuffled cards
        Map<PairsCard, Integer> cardIndexMap = new HashMap<>();
        for (int i = 0; i < shuffledCards.size(); i++) {
            cardIndexMap.put(shuffledCards.get(i), i);
        }

        // Update the pairs to reflect the new indices
        List<Pair<Integer, Integer>> originalPairs = matchEntity.getPairs();
        List<Pair<Integer, Integer>> updatedPairs = new ArrayList<>();
        for (Pair<Integer, Integer> pair : originalPairs) {
            int newFirstIndex = cardIndexMap.get(cards.get(pair.getFirst()));
            int newSecondIndex = cardIndexMap.get(cards.get(pair.getSecond()));
            updatedPairs.add(new Pair<>(newFirstIndex, newSecondIndex));
        }

        // Return the new MatchPairsState with shuffled cards and updated pairs
        return new MatchPairsState(
                shuffledCards,
                updatedPairs,
                matchEntity.getDescription()
        );
    }

    private static MemoryGameState shuffleAndCreateMemoryGameState(MemoryGameStateEntity memoryGameStateEntity) {
        // Get the list of cards
        List<MemoryCardState> cards = memoryGameStateEntity.getCards();

        // Create a copy of the original list to shuffle
        List<MemoryCardState> shuffledCards = new ArrayList<>(cards);

        // Shuffle the cards
        Collections.shuffle(shuffledCards);

        // Create a map to track the new indices of the shuffled cards
        Map<MemoryCardState, Integer> cardIndexMap = new HashMap<>();
        for (int i = 0; i < shuffledCards.size(); i++) {
            cardIndexMap.put(shuffledCards.get(i), i);
        }

        // Update the pairs to reflect the new indices
        List<Pair<Integer, Integer>> originalPairs = memoryGameStateEntity.getPairs();
        List<Pair<Integer, Integer>> updatedPairs = new ArrayList<>();
        for (Pair<Integer, Integer> pair : originalPairs) {
            int newFirstIndex = cardIndexMap.get(cards.get(pair.getFirst()));
            int newSecondIndex = cardIndexMap.get(cards.get(pair.getSecond()));
            updatedPairs.add(new Pair<>(newFirstIndex, newSecondIndex));
        }

        // Return the new MatchPairsState with shuffled cards and updated pairs
        return new MemoryGameState(
                shuffledCards,
                updatedPairs,
                0f,
                memoryGameStateEntity.getDescription()
        );
    }

    public static KnowledgeItem convertToKnowledgeItem(KnowledgeItemEntity knowledgeItemEntity) {
        return new KnowledgeItem(knowledgeItemEntity.getName());
    }
}

