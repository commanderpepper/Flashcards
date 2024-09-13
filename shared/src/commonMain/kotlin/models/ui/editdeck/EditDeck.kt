package models.ui.editdeck

data class EditDeck(val deckName: String, val flashCards: List<EditDeckFlashCardItem>)

data class EditDeckFlashCardItem(val id: String, val front: String, val back: String, val markedForDeletion: Boolean = false)