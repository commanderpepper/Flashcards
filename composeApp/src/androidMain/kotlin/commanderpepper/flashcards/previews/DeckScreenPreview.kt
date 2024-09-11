package commanderpepper.flashcards.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import models.ui.deck.FlashcardItem
import ui.deck.DeckScreen

@Composable
@Preview(showBackground = true)
fun DeckScreenPreview(){
    val SAMPLE_FLASHCARDS = listOf<FlashcardItem>(FlashcardItem(front = "This is a test", back = "This is the back"))
    DeckScreen(modifier = Modifier.fillMaxSize(), flashcards = SAMPLE_FLASHCARDS )
}