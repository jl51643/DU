package com.fer.hr.du.model.game.states

import com.fer.hr.du.model.AbstractState
import jakarta.persistence.*

data class CollectObjectsState(
        val description: String = "",
        val objects: List<com.fer.hr.du.model.CollectableObject> = listOf(),
        val containers: List<com.fer.hr.du.model.CollectableContainer> = listOf()
) : AbstractState("CollectObjectsState") {

    override fun toString(): String {
        return "CollectObjectsState(description='$description', objects=$objects, containers=$containers)"
    }
}

data class CollectableObject(

        val value: String = "",
        val isDraggable: Boolean = true,
        val resetPosition: Boolean = false,
        val indexOfRightContainer: Int = 0
) {
    var id: Long? = null
    override fun toString(): String {
        return "CollectableObject(value='$value', indexOfRightContainer=$indexOfRightContainer)"
    }

}

data class CollectableContainer(
        val title: String = ""
) {
    var id: Long? = null

    override fun toString(): String {
        return "CollectableContainer(title='$title')"
    }
}