package models.ui.deck

sealed class DeckScreenState {
    data class Success(val deckScreenDeckUI: DeckScreenDeckUI): DeckScreenState()
    data object Loading: DeckScreenState()
    data object Error: DeckScreenState()
}