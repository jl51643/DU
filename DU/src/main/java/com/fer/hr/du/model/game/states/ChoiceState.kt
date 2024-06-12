package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.*

data class ChoiceState(
        val choices: List<com.fer.hr.du.model.Choice> = listOf(),
        val activeChoice: Int = 0,
        val description: String = ""

) : AbstractState("ChoiceState") {

    override fun toString(): String {
        return "ChoiceState(choices=$choices, activeChoice=$activeChoice, description='$description')"
    }
}


/*
data class Choice(
        val description: String = "U dubokim, tajnovitim šumama, gde lišće šušti poput šapata starog znanja, mladi ninja ratnici se pripremaju da kroče putem hrabrosti i veštine. Među njima je i [Ime protagoniste], mladi srčani ratnik koji oseća zov avanture u svakom koraku. Sa svetlim očima punim želje za znanjem i snagom koja kuca u svakom srcu, on se sprema da kroči u školu ninji, gde će se učiti veštinama koje će mu oblikovati budućnost.",
        val buttonTitle: String = "Odaberi",
)*/
