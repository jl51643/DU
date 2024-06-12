package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

data class NarrativeState(
        val list: List<String> = emptyList(),
        val backgroundUrl: String,
        val narratorUrl: String
): AbstractState("NarrativeState") {

    override fun toString(): String {
        return "NarrativeState(list=$list, backgroundUrl='$backgroundUrl', narratorUrl='$narratorUrl')"
    }
}