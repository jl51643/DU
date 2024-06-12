package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.game.AbstractState
import jakarta.persistence.Entity

data class LobbyState(
        val activePlayers: List<PlayerInfo> = listOf(),
        val position: Position = Position(0, 0)
) /*: AbstractState("LobbyState")*/ {
}

data class Position(
        val offsetX: Int,
        val offsetY: Int
)

data class PlayerInfo(
        val name: String
)