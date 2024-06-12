package com.fer.hr.du.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fer.hr.du.model.*;
import com.fer.hr.du.model.game.DTO.GameDTO;
import com.fer.hr.du.model.game.DTO.LevelDTO;
import com.fer.hr.du.model.game.KnowledgeItem;
import com.fer.hr.du.model.game.ScreenState;
import com.fer.hr.du.repository.*;
import com.fer.hr.du.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ScreenStateRepository screenStateRepository;

    @Mock
    private ScreenRepository screenRepository;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private KnowledgeItemRepository knowledgeItemRepository;

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveGame() {

        List<ScreenState> screenStates = new ArrayList<>(); // or whatever list you need
        KnowledgeItem knowledgeItem = new KnowledgeItem("zbrajanje"); // or the appropriate knowledge item

        LevelDTO levelDTO = new LevelDTO(screenStates, knowledgeItem);
        GameDTO gameDTO = new GameDTO(List.of(levelDTO));

        when(gameRepository.save(any(GameEntity.class))).thenReturn(new GameEntity());
        when(levelRepository.save(any(LevelEntity.class))).thenReturn(new LevelEntity());

        GameEntity savedGame = gameService.saveGame(gameDTO);

        assertNotNull(savedGame);
        verify(gameRepository, times(1)).save(any(GameEntity.class));
        verify(levelRepository, times(1)).save(any(LevelEntity.class));
    }

    @Test
    void testGetGame() {
        Long gameId = 1L;
        GameEntity gameEntity = new GameEntity();
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(gameEntity));

        GameDTO gameDTO = gameService.getGame(gameId);

        assertNotNull(gameDTO);
        verify(gameRepository, times(1)).findById(gameId);
    }

    @Test
    void testGetGameNotFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        GameDTO gameDTO = gameService.getGame(gameId);

        assertNull(gameDTO);
        verify(gameRepository, times(1)).findById(gameId);
    }
}