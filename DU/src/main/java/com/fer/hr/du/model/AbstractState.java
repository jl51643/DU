package com.fer.hr.du.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fer.hr.du.model.game.states.*;
import lombok.Getter;
import lombok.Setter;

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        property = "@class"
)*/

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceState.class, name = "ChoiceState"),
        @JsonSubTypes.Type(value = CollectObjectsState.class, name = "CollectObjectsState"),
        @JsonSubTypes.Type(value = MatchPairsState.class, name = "MatchPairsState"),
        @JsonSubTypes.Type(value = MemoryGameState.class, name = "MemoryGameState"),
        @JsonSubTypes.Type(value = NarrativeState.class, name = "NarrativeState"),
        @JsonSubTypes.Type(value = QuizState.class, name = "QuizState"),
        @JsonSubTypes.Type(value = SpinningWheelScreenState.class, name = "SpinningWheelScreenState"),
        // Add other state classes here
})
public abstract class AbstractState {

    public AbstractState(String type) {
        this.type = type;
    }

    @Getter
    @Setter
    private String type;
}

