package commanderpepper.flashcards.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import models.ui.editdeck.EditDeck
import models.ui.editdeck.EditDeckFlashCardItem
import ui.editdeck.EditDeckScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditDeckScreenPreview() {
    EditDeckScreen(
        modifier = Modifier.fillMaxSize(),
        editDeck = SAMPLE_EDIT_DECK_SCREEN,
        addNewFlashCard = {},
        markFlashCardForDeletion = {},
        saveDeck = { _, _ -> },
        onBackButton = {},
        onFlashcardSaveChanges = {})
}

val SAMPLE_EDIT_DECK_SCREEN_FLASHCARDS: List<EditDeckFlashCardItem> = listOf(
    EditDeckFlashCardItem(id = "1", front = "This is the front", back = "This is the back"),
    EditDeckFlashCardItem(
        id = "2",
        front = "This is the front",
        back = "This is the back",
        markedForDeletion = true
    ),
    EditDeckFlashCardItem(id = "3", front = "This is the front", back = "This is the back"),
    EditDeckFlashCardItem(id = "4", front = "This is the front", back = "This is the back"),
    EditDeckFlashCardItem(id = "5", front = "This is the front", back = "This is the back"),
)
private val SAMPLE_EDIT_DECK_SCREEN =
    EditDeck(deckId = "1", deckName = "Test Deck", flashCards = SAMPLE_EDIT_DECK_SCREEN_FLASHCARDS)