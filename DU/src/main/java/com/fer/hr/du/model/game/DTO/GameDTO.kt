package com.fer.hr.du.model.game.DTO

import jakarta.persistence.*

data class GameDTO(
        val levels: List<LevelDTO> = listOf()
)