package com.fer.hr.du.model.game

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*

/*@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "id")
open class Screen(
        val route: String = ""
) {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 var id: Long? = null
}

@Entity
class ChoiceScreen() : Screen("choice_screen")

@Entity
 class MemoryGameScreen() : Screen("memory_game_screen")

@Entity
 class NarrativeScreen() : Screen("narrative_screen")

@Entity
 class QuizScreen() : Screen("quiz_screen")

@Entity
 class SpinningWheelScreen() : Screen("spinning_wheel_screen")

@Entity
 class MatchPairScreen() : Screen("match_pair_screen")

@Entity
 class CollectObjectsScreen() : Screen("collect_objects_screen")*/

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
 @JsonSubTypes.Type(value = Screen.ChoiceScreen.class, name = "ChoiceScreen"),
 @JsonSubTypes.Type(value = Screen.MemoryGameScreen.class, name = "MemoryGameScreen"),
 @JsonSubTypes.Type(value = Screen.NarrativeScreen.class, name = "NarrativeScreen"),
 @JsonSubTypes.Type(value = Screen.QuizScreen.class, name = "QuizScreen"),
 @JsonSubTypes.Type(value = Screen.SpinningWheelScreen.class, name = "SpinningWheelScreen"),
 @JsonSubTypes.Type(value = Screen.MatchPairScreen.class, name = "MatchPairScreen"),
 @JsonSubTypes.Type(value = Screen.CollectObjectsScreen.class, name = "CollectObjectsScreen")
})
sealed class Screen(
        val route: String
) {

 data class ChoiceScreen(val screenRoute: String = "choice_screen") : Screen(screenRoute)

 data class MemoryGameScreen(val screenRoute: String = "memory_game_screen") : Screen(screenRoute)

 data class NarrativeScreen(val screenRoute: String = "narrative_screen") : Screen(screenRoute)

 data class QuizScreen(val screenRoute: String = "quiz_screen") : Screen(screenRoute)

 data class SpinningWheelScreen(val screenRoute: String = "spinning_wheel_screen") : Screen(screenRoute)

 data class MatchPairScreen(val screenRoute: String = "match_pair_screen") : Screen(screenRoute)

 data class CollectObjectsScreen(val screenRoute: String = "collect_objects_screen") : Screen(screenRoute)
}*/

