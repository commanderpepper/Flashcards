package models.ui.decklist

sealed class DeckListsScreenState {
    data object Loading: DeckListsScreenState()
    data class Success(val decks: List<DeckListsItemUI>): DeckListsScreenState()
    data object Error: DeckListsScreenState()
}