package models.ui.editdeck

sealed class EditDeckState {
    data object Loading: EditDeckState()
    data object Error: EditDeckState()
    data class Success(val editDeck: EditDeck): EditDeckState()
}