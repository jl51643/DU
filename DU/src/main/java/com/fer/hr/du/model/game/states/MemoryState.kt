package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.*

data class MemoryGameState(
        val cards: List<com.fer.hr.du.model.MemoryCardState> = listOf(),
        val pairs: List<Pair<Int, Int>> = listOf(),
        val progress: Float = 0f,
        val description: String = ""

) : AbstractState("MemoryGameState") {

    override fun toString(): String {
        return "MemoryGameState(cards=$cards, pairs=$pairs, progress=$progress, description='$description')"
    }
}

data class MemoryCardState(
        var isFacedUp: Boolean = false,
        var isPairedUp: Boolean = false,
        var value: String = ""
) {
    var id: Long? = null
    override fun toString(): String {
        return "MemoryCardState(value='$value', isFacedUp=$isFacedUp, isPairedUp=$isPairedUp)"
    }


}