package com.fer.hr.du.model.game

import com.fer.hr.du.model.Screen
import com.fer.hr.du.model.AbstractState

data class ScreenState(
        var screen: Screen,
        var state: AbstractState,
        var nextScreens: List<ScreenState>
) {
    override fun toString(): String {
        return "ScreenState(screen=$screen, state=$state, nextScreens=${nextScreens.map { nextScreen -> nextScreen.screen.toString() }})"
    }
}