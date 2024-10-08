package commanderpepper.flashcards.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import models.ui.deck.DeckScreenDeckUI
import models.ui.deck.DeckScreenState
import models.ui.deck.FlashcardItem
import ui.deck.DeckScreen

@Composable
@Preview(showBackground = true)
fun DeckScreenPreview(){
    val SAMPLE_FLASHCARDS = listOf<FlashcardItem>(FlashcardItem(front = "This is a test", back = "This is the back"))
    val SAMPLE_DECK = DeckScreenDeckUI(name = "Preview", id = "1", flashcards = SAMPLE_FLASHCARDS)
    val SAMPLE_DECK_STATE = DeckScreenState.Success(SAMPLE_DECK)
    DeckScreen(modifier = Modifier.fillMaxSize(), deckScreenState = SAMPLE_DECK_STATE, onBackButton = { /*TODO*/ }) {

    }
}