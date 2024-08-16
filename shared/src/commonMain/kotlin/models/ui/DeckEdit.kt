package models.ui

data class DeckEditScreenUIState(
    val deckEditDeckNameUI: DeckEditDeckNameUI = DeckEditDeckNameUI(),
    val deckEditCardUIList: List<DeckEditCardUI> = emptyList()
)

data class DeckEditDeckNameUI(val name: String = "")
data class DeckEditCardUI(val front: String, val back: String)