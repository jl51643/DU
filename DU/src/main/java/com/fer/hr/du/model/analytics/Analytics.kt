package com.fer.hr.du.model.analytics

import com.fer.hr.du.model.UserDTO
import com.fer.hr.du.model.game.ScreenState

class Analytics(
        val userDTO: UserDTO,
        val buttonKey: String,
        val additionalData: String?,
        val screenState: ScreenState?
) {
    override fun toString(): String {
        return "Analytics(user:$userDTO buttonKey:${buttonKey}, additionalData:${additionalData} screenState=${screenState.toString()})"
    }

}