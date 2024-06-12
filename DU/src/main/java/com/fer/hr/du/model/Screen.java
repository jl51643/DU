package com.fer.hr.du.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "route"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceScreen.class, name = "choice_screen"),
        @JsonSubTypes.Type(value = MemoryGameScreen.class, name = "memory_game_screen"),
        @JsonSubTypes.Type(value = NarrativeScreen.class, name = "narrative_screen"),
        @JsonSubTypes.Type(value = QuizScreen.class, name = "quiz_screen"),
        @JsonSubTypes.Type(value = SpinningWheelScreen.class, name = "spinning_wheel_screen"),
        @JsonSubTypes.Type(value = MatchPairScreen.class, name = "match_pair_screen"),
        @JsonSubTypes.Type(value = CollectObjectsScreen.class, name = "collect_objects_screen")
})
public abstract class Screen {

    @Getter @Setter
    String route;

    public Screen(String route) {
        this.route = route;
    }
}
