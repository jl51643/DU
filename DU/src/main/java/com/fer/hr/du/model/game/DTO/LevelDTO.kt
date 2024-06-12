package com.fer.hr.du.model.game.DTO

import com.fer.hr.du.model.*
import com.fer.hr.du.model.Choice
import com.fer.hr.du.model.CollectableContainer
import com.fer.hr.du.model.CollectableObject
import com.fer.hr.du.model.MemoryCardState
import com.fer.hr.du.model.PairsCard
import com.fer.hr.du.model.game.*
import com.fer.hr.du.model.game.states.*

data class LevelDTO(
        val screenStates: List<ScreenState>,
        val knowledgeItem: KnowledgeItem
) {
    /*fun a(): List<ScreenState> {
        return listOf(
                ScreenState(
                        NarrativeScreen(),
                        state = NarrativeState(
                                list = listOf(
                                        "U dubokim, tajnovitim šumama, gde lišće šušti poput šapata starog znanja, mladi ninja ratnici se pripremaju da kroče putem hrabrosti i veštine. Među njima je i [Ime protagoniste], mladi srčani ratnik koji oseća zov avanture u svakom koraku. Sa svetlim očima punim želje za znanjem i snagom koja kuca u svakom srcu, on se sprema da kroči u školu ninji, gde će se učiti veštinama koje će mu oblikovati budućnost.",
                                        "Njegova priča počinje sa svetlošću svitanja, kada se probudio u svojoj skromnoj kolibi duboko u šumi. Već od najranijih dana, [Ime protagoniste] je osećao unutrašnji nemir, kao da ga nešto vodi ka nepoznatom. Uvek je sanjao o tome da postane heroj, da zaštiti svoje selo i svet od zla koje vreba u tami.",
                                        "Sada, dok se sprema da krene na put, uzbuđenje mu je neizmerno. Ali ispod tog uzbuđenja leži i strepnja - strepnja od nepoznatog, ali i odlučnost da prevaziđe sve izazove koji mu se na tom putu nađu na putu. Sa svetlom nade u srcu i mačem spremnim za borbu, [Ime protagoniste] kroči u svoju sudbinu kao mladi ninja ratnik, spreman da nauči, raste i postane ono što je oduvek sanjao - pravi heroj."
                                ),
                                backgroundUrl = "https://miro.medium.com/v2/resize:fit:640/format:webp/0*j72GJKOI6VJF1mGq.jpg",
                                narratorUrl = "https://static.wikia.nocookie.net/poohadventures/images/4/47/Sensei_%28Clumsy_Ninja%29.png/revision/latest/scale-to-width-down/406?cb=20230830083512"
                        ),
                        listOf(
                                ScreenState(
                                        ChoiceScreen(),
                                        state = ChoiceState(
                                                choices = listOf(
                                                        Choice(),
                                                        Choice(),
                                                ),
                                                description = "Mladi ninja ratniče, odaberi svog senseija: Sensei Hiroshi nudi snagu, moćne borbene tehnike i disciplinu, ali treninzi su naporni i ima malo odmora. Sensei Akira donosi preciznost, strategiju i vještine s oružjem, no s manje fizičkog treninga i zahtjevnom mentalnom pripremom. Izbor je tvoj, odluči mudro!"
                                        ),
                                        listOf(
                                                ScreenState(
                                                        MemoryGameScreen(),
                                                        state = MemoryGameState(
                                                                cards = listOf(
                                                                        MemoryCardState().apply { value = "3 + 5" },
                                                                        MemoryCardState().apply { value = "8" },
                                                                        MemoryCardState().apply { value = "2 + 2" },
                                                                        MemoryCardState().apply { value = "4" },
                                                                ),
                                                                pairs = listOf(
                                                                        Pair(0, 1),
                                                                        Pair(2, 3)
                                                                ),
                                                                description = "Kao dio svoje obuke, mladi ninja ratnici moraju razviti svoje mentalne sposobnosti, uključujući pamćenje i matematičku preciznost. Da bi postali pravi ninje, morate uspješno riješiti seljedeće zadatke."
                                                        ),
                                                        listOf(
                                                                ScreenState(
                                                                        SpinningWheelScreen(),
                                                                        state = SpinningWheelScreenState(
                                                                                listOfPrises = mutableListOf("img_double_points",
                                                                                        "img_half_answers",
                                                                                        "img_hint",
                                                                                        "img_heart_refil"),
                                                                                description = "zavrti"
                                                                        ),
                                                                        listOf()
                                                                )
                                                        )
                                                ),
                                                ScreenState(
                                                        QuizScreen(),
                                                        state = QuizState(
                                                                question = "Mladi ninja ratnici imaju poseban trening svaki dan kako bi postali jači i brži. Svaki dan trče kroz šumu, skaču preko prepreka i vježbaju borbene vještine.\n" +
                                                                        "\n" +
                                                                        "Ako svaki ninja ratnik može preskočiti 4 prepreke u jednoj minuti, koliko će prepreka preskočiti za 6 minuta?",
                                                                answers = listOf(
                                                                        "20 prepreka",
                                                                        "22 prepreke",
                                                                        "24 prepreke",
                                                                        "26 prepreka"
                                                                ),
                                                                answersType = QuizAnswerType.WORD,
                                                                indexOfCorrectAnswer = 2
                                                        ),
                                                        listOf(
                                                                ScreenState(
                                                                        MatchPairScreen(),
                                                                        MatchPairsState(
                                                                                cards = listOf(
                                                                                        PairsCard().apply { value = "3 + 5" },
                                                                                        PairsCard().apply { value = "5" },
                                                                                        PairsCard().apply { value = "7 - 2" },
                                                                                        PairsCard().apply { value = "8" },
                                                                                        PairsCard().apply { value = "16 ÷ 4" },
                                                                                        PairsCard().apply { value = "4" }
                                                                                ),
                                                                                pairs = listOf(
                                                                                        Pair(0, 3), Pair(2, 1), Pair(4, 5)
                                                                                ),
                                                                                description = "Mladi ninja ratnici svaki dan imaju poseban trening kako bi postali najbolji. Tijekom treninga, moraju spojiti ispravne parove kako bi prošli kroz tajni prolaz. Svaki par se sastoji od zadatka i njegovog rezultata.\n" +
                                                                                        "\n" +
                                                                                        "Spojite zadatke s točnim rezultatima kako bi ninja ratnici mogli završiti svoj trening:"
                                                                        ),
                                                                        listOf(
                                                                                ScreenState(
                                                                                        CollectObjectsScreen(),
                                                                                        CollectObjectsState(
                                                                                                description = "Mladi ninja ratnici, vaša sljedeća misija je organizirati trening poligon tako da povučete objekte u odgovarajuće spremnike. Svaki objekt ima svoje matematičko svojstvo, a vaša je zadaća pronaći ispravan kontejner za svaki objekt.",
                                                                                                objects = listOf(
                                                                                                        CollectableObject().apply {
                                                                                                            value = "12"
                                                                                                            indexOfRightContainer = 1
                                                                                                        },
                                                                                                        CollectableObject().apply {
                                                                                                            value = "15"
                                                                                                            indexOfRightContainer = 0
                                                                                                        },
                                                                                                        CollectableObject().apply {
                                                                                                            value = "8"
                                                                                                            indexOfRightContainer = 1
                                                                                                        },
                                                                                                        CollectableObject().apply {
                                                                                                            value = "10"
                                                                                                            indexOfRightContainer = 0
                                                                                                        },
                                                                                                ),
                                                                                                containers = listOf(
                                                                                                        CollectableContainer().apply { title = "Brojevi djeljivi s 5" },
                                                                                                        CollectableContainer().apply { title = "Brojevi djeljivi s 4" }
                                                                                                )
                                                                                        ),
                                                                                        listOf()
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        )
    }*/
}
