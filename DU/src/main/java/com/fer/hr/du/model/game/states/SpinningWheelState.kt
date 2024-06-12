package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity

data class SpinningWheelScreenState(
        val listOfPrises: List<String> = listOf(),
        val showPrise: Boolean = false,
        val indexOfPrise: Int = -1,
        val description: String
) : AbstractState("SpinningWheelScreenState") {

    override fun toString(): String {
        return "SpinningWheelScreenState(listOfPrises=$listOfPrises, showPrise=$showPrise, indexOfPrise=$indexOfPrise, description='$description')"
    }
}