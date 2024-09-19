package models.ui.deck

data class FlashcardItem(val front: String, val back: String)

data class DeckScreenDeckUI(val name: String, val id: String, val flashcards: List<FlashcardItem>)