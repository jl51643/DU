package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity

//@Entity
data class QuizState(
        val question: String = "",
        val answers: List<String> = listOf(),
        val imageAnswers: List<Int> = listOf(),
        val answersType: QuizAnswerType = QuizAnswerType.WORD,
        val indexOfCorrectAnswer: Int = 0
) : AbstractState("QuizState") {

    override fun toString(): String {
        return "QuizState(question='$question', answers=$answers, imageAnswers=$imageAnswers, answersType=$answersType, indexOfCorrectAnswer=$indexOfCorrectAnswer)"
    }
}

enum class QuizAnswerType {
    WORD, IMAGE
}