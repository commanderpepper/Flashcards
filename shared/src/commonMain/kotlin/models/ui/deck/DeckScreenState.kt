package models.ui.deck

sealed class DeckScreenState {
    data class Success(val flashcards: List<FlashcardItem>): DeckScreenState()
    data object Loading: DeckScreenState()
    data object Error: DeckScreenState()
}