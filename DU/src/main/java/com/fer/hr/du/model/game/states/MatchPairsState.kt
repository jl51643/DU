package com.fer.hr.du.model.game.states

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fer.hr.du.model.AbstractState
import jakarta.persistence.*

data class MatchPairsState(
        val cards : List<com.fer.hr.du.model.PairsCard> = listOf(),
        val pairs: List<Pair<Int, Int>> = listOf(),
        val description : String = ""
) : AbstractState("MatchPairsState") {

    override fun toString(): String {
        return "MatchPairsState(cards=$cards, pairs=$pairs, description='$description')"
    }
}

data class PairsCard(
        val value: String = "",
        val isFocused: Boolean = false,
        val isMatched: Boolean = false,
) {
    var id: Long? = null
    override fun toString(): String {
        return "PairsCard(value='$value', isFocused=$isFocused, isMatched=$isMatched)"
    }
}

@Converter
class PairListConverter : AttributeConverter<List<Pair<Int, Int>>, String> {
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: List<Pair<Int, Int>>?): String {
        return attribute?.let { objectMapper.writeValueAsString(it) } ?: "[]"
    }

    override fun convertToEntityAttribute(dbData: String?): List<Pair<Int, Int>> {
        return if (dbData.isNullOrEmpty()) {
            emptyList()
        } else {
            objectMapper.readValue(dbData)
        }
    }
}